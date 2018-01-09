package com.kt.airmap.api.sif.master.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.airmap.api.sif.master.service.DeviceService;
import com.kt.airmap.api.sif.master.vo.Device;
import com.kt.airmap.base.message.BaseResponse;
import com.kt.airmap.base.mvc.message.Messages;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/master/v1.0/device/")
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	
	@ApiOperation(value = "장치 조회", notes = "장치정보를 조회한다.", httpMethod = "GET", response = Device.class)
    @RequestMapping(value="/{sequence}", method=GET)
	private BaseResponse getDevice(@ApiParam(name="sequence", value="장치 일련번호", required=true)
							 @PathVariable Long sequence,
							 @ApiParam(name="targetSequence", value="서비스대상 일련번호", required=true)
							 @RequestParam(value="targetSequence", required= true) Long targetSequence,
							 @ApiIgnore
							 Messages messages) {
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("sequence", sequence);
		parameter.put("targetSequence", targetSequence);
		BaseResponse response = deviceService.getDevice(parameter);

		//messages.addMessage(Const.SUCCESS, null);
		return response;
	}
	
	
	
}
