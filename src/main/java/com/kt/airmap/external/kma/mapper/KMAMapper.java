package com.kt.airmap.external.kma.mapper;

import java.util.List;

import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastSpaceDataVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexDataVo;

public interface KMAMapper {
	

	Integer selectTest();
	
	List<AreaVo> selectArea();

	void insertForecastSpaceData(ForecastSpaceDataVo forecastSpaceDataVo);

	Integer selectForecastSpaceData(ForecastSpaceDataVo forecastSpaceDataVo);

	void updateForecastSpaceData(ForecastSpaceDataVo forecastSpaceDataVo);

	void insertMyT();

	void insertLifeIndexData(LifeIndexDataVo lifeIndexDataVo);
}
