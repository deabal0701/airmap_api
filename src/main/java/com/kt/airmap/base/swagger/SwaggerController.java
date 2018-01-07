package com.kt.airmap.base.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mangofactory.swagger.annotations.ApiIgnore;

@Controller
public class SwaggerController {
			
	@ApiIgnore
	@RequestMapping(value = "/swagger")
	public ModelAndView ui(ModelAndView mav) {
		System.out.println("=============> swagger called");
		mav.setViewName("swagger-ui/index");
	        return mav;
	}
}
