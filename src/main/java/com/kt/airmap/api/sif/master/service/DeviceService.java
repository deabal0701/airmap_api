package com.kt.airmap.api.sif.master.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.airmap.api.sif.master.service.external.MasterDeviceService;
import com.kt.airmap.base.exception.DataNotFoundException;
import com.kt.airmap.base.message.BaseResponse;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;

@Service
public class DeviceService {

	@Autowired
	MasterDeviceService masterDeviceService;

	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	public BaseResponse getDevice(Map<String, Object> parameter) {

		BaseResponse response = masterDeviceService.getServiceDevice(parameter);
		if(response == null){
			throw new DataNotFoundException();
		}

		if(true){
			throw new DataNotFoundException();
		}

		return response;
	}

	@Transactional
	public String addTest() {
	
		kMAMapperDao.addTest();
		
		kMAMapperDao.addTest();
		
		if(true){
			throw new DataNotFoundException(); 
		}
		
		kMAMapperDao.addTest();
		
		return "aa";
		
	}
	
}
