package com.kt.airmap.api.sif.master.service.external;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.airmap.Const;
import com.kt.airmap.base.adaptor.MasterApiAdaptorService;
import com.kt.airmap.base.message.BaseResponse;



/**
 * @author deabal
 *
 *
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : MasterDeviceService.java
 * 3. Package  : com.kt.iot.api.master.servicetarget.service
 * 4. Comment  : 
 * 5. Creator  : deabal
 * 6. Date     : 2017. 3. 15. 오후 4:20:08
 * </PRE>
 *
 */
@Service
@SuppressWarnings("unchecked")
public class MasterDeviceService {
	
	@Autowired
	private MasterApiAdaptorService masterApiService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName : getServiceDevice
	 * 2. ClassName  : ServiceDeviceService
	 * 3. Comment   : 
	 * 4. Creator  : deabal
	 * 5. Date     : 2017. 7. 28. 오후 1:34:01
	 * </PRE>
	 *   @return BaseResponse
	 *   @param parameter
	 *   @return
	 */
	@SuppressWarnings("unchecked")
	public BaseResponse getServiceDevice(Map<String, Object> parameter) {

		Map<String, String> targetParam = new HashMap<String, String>();
		targetParam.put("targetSequence", String.valueOf(parameter.get("targetSequence")));
	        
		BaseResponse response = masterApiService.get("/v1.1/devices/" + parameter.get("sequence"), targetParam, BaseResponse.class);
		if (response.getResponseCode().equals(Const.RESPONSE_CODE_OK)) {
			return response;
		}
		throw new RuntimeException();
	}
	
	

}
