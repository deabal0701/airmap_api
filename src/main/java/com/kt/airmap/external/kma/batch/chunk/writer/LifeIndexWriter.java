package com.kt.airmap.external.kma.batch.chunk.writer;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

 
public class LifeIndexWriter implements ItemWriter<LifeIndexVo> {
     
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	

	@Override
	public void write(List<? extends LifeIndexVo> list) throws Exception {
	 	
		@SuppressWarnings("unchecked")
		Iterator<LifeIndexVo> iterator = (Iterator<LifeIndexVo>) list.iterator();
		while(iterator.hasNext()){
		 	
 			LifeIndexVo lifeIndexVo = iterator.next();
 			kMAMapperDao.addLifeIndex(lifeIndexVo);
		
		}
	}

}
