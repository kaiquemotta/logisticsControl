package com.logistica.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rota")
public class RotaController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

//	@Autowired
//	private RotaService rotaService;

	@RequestMapping("/")
	public String index() {

		return ("Hello World");
	}
	
	
	

}
