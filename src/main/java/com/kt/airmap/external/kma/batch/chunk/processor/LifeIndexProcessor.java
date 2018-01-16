package com.kt.airmap.external.kma.batch.chunk.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

public class LifeIndexProcessor implements ItemProcessor<LifeIndexVo, LifeIndexVo>{

	private static final Logger log = LoggerFactory.getLogger(LifeIndexProcessor.class);
	
	@Override
	public LifeIndexVo process(LifeIndexVo lifeIndexVo) throws Exception {
		
        log.info("Converting (" + lifeIndexVo + ") into (" + lifeIndexVo + ")");

        return lifeIndexVo;
	}

}
