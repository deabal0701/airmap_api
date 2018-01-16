package com.kt.airmap.external.kma.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.airmap.Const;
import com.kt.airmap.base.adaptor.KMALocAdaptorService;
import com.kt.airmap.base.common.geoUtil.GridXYToLatLngConverter;
import com.kt.airmap.base.common.geoUtil.LatXLngY;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.Area;
import com.kt.airmap.external.kma.vo.AreaVo;

@Service
public class LocationCodeService {

	private static final Logger logger = LoggerFactory.getLogger(LocationCodeService.class);
 	
	@Autowired
	protected KMALocAdaptorService kmaLocAdaptorService;
	
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	//KEY정보
    private final String KEY_CODE = "code";
    private final String KEY_VALUE = "value";
    private final String KEY_X = "x";
    private final String KEY_Y = "y";
    
    //각각의 노드를 저장할 리스트
    private List<Area> topList = null;
    private List<Area> mdlList = null;
    private List<Area> leafList = null;
   
	public void locationCode(String stdDateTime) {
	
		topList = new ArrayList<Area>();
		mdlList = new ArrayList<Area>();
		leafList = new ArrayList<Area>();
		 
	    JSONArray jsonArray =null;
		
	    kMAMapperDao.delLocationCode();
	     
	    String topStr = (String) this.kmaLocAdaptorService.get(Const.KMA_LOCATION_CODE_TOP_URI, null, String.class);
	   
	    jsonArray = (JSONArray) JSONValue.parse(topStr.toString());
	   
	    parseJSON(topList, jsonArray, null, null,null,null, false);
        
        //중간 노드
        for(Area dto : topList) {
        	String midStr = (String) this.kmaLocAdaptorService.get(String.format(Const.KMA_LOCATION_CODE_MDL_URI,dto.getCode()), null, String.class);
        	jsonArray = (JSONArray) JSONValue.parse(midStr);
        	parseJSON(mdlList, jsonArray, dto.getCode(), dto.getName(),null, null, false);
        }
        
        //최하 노드
        for(Area dto : mdlList) {
        	String leafStr   = (String) this.kmaLocAdaptorService.get(String.format(Const.KMA_LOCATION_CODE_LEAF_URI,dto.getCode()), null, String.class);
          	jsonArray = (JSONArray) JSONValue.parse(leafStr);
          	parseJSON(leafList, jsonArray, dto.getCode(), dto.getName(), dto.getParentCode(),dto.getParentName() ,true);
        }     
    
	}
	
	private List<Area> parseJSON(List<Area> list, JSONArray array, String parentCode, String parentName,
			String pParentCode, String pParentName, boolean isLast) {

		JSONObject data = null;
		Area area = null;
		for (int i = 0; i < array.size(); i++) {
			data = (JSONObject) array.get(i);

			if (!isLast) {
				// 최상, 중간 노드
				area = new Area(data.get(KEY_CODE).toString(), data.get(KEY_VALUE).toString(), parentCode,
						parentName, pParentCode, pParentName);

			} else {
				// 최하 노드
				area = new Area(data.get(KEY_CODE).toString(), data.get(KEY_VALUE).toString(), parentCode,
						parentName, pParentCode, pParentName, data.get(KEY_X).toString(), data.get(KEY_Y).toString());

				AreaVo areaVo = new AreaVo();
				areaVo.setBstorCd(data.get(KEY_CODE).toString());
				areaVo.setCityNm(pParentName);
				areaVo.setGuNm(parentName);
				areaVo.setDongNm(data.get(KEY_VALUE).toString());
				areaVo.setXcrdVal(Integer.parseInt((String) data.get(KEY_X)));
				areaVo.setYcrdVal(Integer.parseInt((String) data.get(KEY_Y)));
				areaVo.setUseYn("Y"); //default
				LatXLngY latXLngY = GridXYToLatLngConverter.convertGRID_GPS(GridXYToLatLngConverter.TO_GPS, Integer.parseInt((String) data.get(KEY_X)), Integer.parseInt((String) data.get(KEY_Y)));
				
				areaVo.setLatitVal(latXLngY.lat);
				areaVo.setLngitVal(latXLngY.lng);
				
				kMAMapperDao.addLocationCode(areaVo);
				
			}
			list.add(area);
			logger.debug(area.toString());

		}
		return list;
	}
}
