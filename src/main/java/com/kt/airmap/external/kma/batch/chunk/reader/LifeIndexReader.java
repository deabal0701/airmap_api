package com.kt.airmap.external.kma.batch.chunk.reader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.LifeIndexBatchService;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

public class LifeIndexReader implements ItemReader<LifeIndexVo>{

	
	private final Iterator<LifeIndexVo> data;
		
		public LifeIndexReader(List<LifeIndexVo> data) {
			this.data = data.iterator();
		}
		
	
		@Override
		public LifeIndexVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if(this.data.hasNext()){
				return this.data.next();
			}else{
				return null;
			}
		}
	
	
//	private final List<LifeIndexVo> dataList;
//		
//		public LifeIndexReader(List<LifeIndexVo> list) {
//			this.dataList = list;
//		}
//		private int count=0;
//	
//		@Override
//		public LifeIndexVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//		
//			if(count < dataList.size()){
//				return dataList.get(count++);
//			}else{
//				count=0;
//			}
//			return null;
//		}
}
