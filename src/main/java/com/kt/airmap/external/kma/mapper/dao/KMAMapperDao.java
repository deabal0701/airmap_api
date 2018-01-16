package com.kt.airmap.external.kma.mapper.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kt.airmap.external.kma.mapper.KMAMapper;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastGripVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

@Repository
public class KMAMapperDao {
	
	@Autowired
	KMAMapper kMAMapper;


	public Integer getTest() {
		return  kMAMapper.selectTest();
	}

	public List<AreaVo> getArea(String selType) {
		return kMAMapper.selectArea(selType);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addLifeIndex(LifeIndexVo lifeIndexVo) {
		kMAMapper.insertLifeIndex(lifeIndexVo);
	}

	public void addLocationCode(AreaVo areaVo) {
		kMAMapper.insertLocationCode(areaVo);
	}

	public void delLocationCode() {
		kMAMapper.deleteLocationCode();
	}

	public void addForecastGrip(ForecastGripVo forecastGripVo) {
		kMAMapper.insertForecastGrip(forecastGripVo);
	}

	public List<Map<String, Object>> getBatchProperties() {
		return kMAMapper.selectBatchProperties();
	
	}

}
