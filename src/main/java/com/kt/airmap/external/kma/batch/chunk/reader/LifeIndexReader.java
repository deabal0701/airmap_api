package com.kt.airmap.external.kma.batch.chunk.reader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.LifeIndexBatchService;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

public class LifeIndexReader implements ItemReader<LifeIndexVo>{

	
//	private final Iterator<LifeIndexVo> data;
//		
//		public LifeIndexReader(List<LifeIndexVo> data) {
//			this.data = data.iterator();
//		}
//	
//		@Override
//		public LifeIndexVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//			if(this.data.hasNext()){
//				return this.data.next();
//			}else{
//				return null;
//			}
//		}
	
	
	
	LifeIndexBatchService lifeIndexBatchService;

	private Iterator<LifeIndexVo> lifeIndexData = null;
	
	public LifeIndexReader(LifeIndexBatchService lifeIndexBatchService) {
		this.lifeIndexBatchService= lifeIndexBatchService;
	}
		
	@BeforeStep
    public void beforeStep(final StepExecution stepExecution) throws Exception {
		lifeIndexData = fetchLifeIndexFromAPI();
    }

	
	@Override
	public LifeIndexVo read() throws Exception {

		LifeIndexVo nextLifeIndexData = null;
		
		if(lifeIndexData.hasNext()){
			nextLifeIndexData = lifeIndexData.next();
		}else{
			return null;
		}
		return nextLifeIndexData;
		
	}
	
	
	public Iterator<LifeIndexVo> fetchLifeIndexFromAPI() {
   	List<LifeIndexVo> response =lifeIndexBatchService.lifeIndex("2018011012", Const.KMA_LIFE_WEATHER_WINTER_URI);
	    return response.iterator();
    }

}
