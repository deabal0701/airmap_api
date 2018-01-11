package com.kt.airmap.external.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.AreaVo;

@Service
public class ExtCommService {

	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	public List<AreaVo> getAreaList(String type){
		  
		List<AreaVo> areaListVo =  kMAMapperDao.getArea(type);
		if(areaListVo == null){
			//Todo : 지역데이터 없다는 Exception 처리
			return null;
		}else{
 			System.out.println("areaVo.size() : "+areaListVo.size());
 			return areaListVo;
 		}
	}
}
