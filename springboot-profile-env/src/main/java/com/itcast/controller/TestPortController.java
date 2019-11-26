package com.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzzl.iservice.order.manifest.IManifestPortCodeService;

@RestController
public class TestPortController {
	
	@Autowired
	IManifestPortCodeService  manifestPortCodeService;
	
	@GetMapping("/sayHello")
	public String sysHello(){
		return "hello zy";
	}
	
	@GetMapping("/hi")
	public String delPortId(){
		manifestPortCodeService.delByPortCode(58847);
		
		
		return "hello zy";
	}

}
