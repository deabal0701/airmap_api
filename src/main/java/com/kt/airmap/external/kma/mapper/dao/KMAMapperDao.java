package com.kt.airmap.external.kma.mapper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kt.airmap.external.kma.mapper.KMAMapper;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastSpaceDataVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexDataVo;

@Repository
public class KMAMapperDao {
	
	@Autowired
	KMAMapper kMAMapper;


	public Integer getTest() {
		return  kMAMapper.selectTest();
	}

	public List<AreaVo> getArea() {
		return kMAMapper.selectArea();
	}
	

	public void addForecastSpaceData(ForecastSpaceDataVo forecastSpaceDataVo) throws Exception {
		
			Integer existCnt = kMAMapper.selectForecastSpaceData(forecastSpaceDataVo);
		
			if(existCnt > 0){
				kMAMapper.updateForecastSpaceData(forecastSpaceDataVo);
			}else{
				kMAMapper.insertForecastSpaceData(forecastSpaceDataVo);
			}
		
	}

	public String addTest() {
		kMAMapper.insertMyT();
		return "aaa";
	}

	public void addLifeIndexData(LifeIndexDataVo lifeIndexDataVo) {
		kMAMapper.insertLifeIndexData(lifeIndexDataVo);
	}

	public void addLocationCode(AreaVo areaVo) {
		
		kMAMapper.insertLocationCode(areaVo);
		
	}

	public void delLocationCode() {
		kMAMapper.deleteLocationCode();
		
	}


}
