package com.kt.airmap.external.kma.mapper;

import java.util.List;

import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastGripVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

public interface KMAMapper {
	

	Integer selectTest();
	
	List<AreaVo> selectArea(String selType);

	void insertLifeIndex(LifeIndexVo lifeIndexVo);

	void insertLocationCode(AreaVo areaVo);

	void deleteLocationCode();

	void insertForecastGrip(ForecastGripVo forecastGripVo);
}
