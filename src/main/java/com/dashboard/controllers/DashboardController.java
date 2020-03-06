package com.dashboard.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dashboard.modal.DataModal;
import com.dashboard.service.DashboardService;

@Controller
public class DashboardController {

	@Autowired
	private DashboardService dsService;
	
	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value="/getData", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, DataModal> getData() {
		
		Map<String, DataModal> result = dsService.getCSVData();
		return result;
	}
}
