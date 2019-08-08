package com.sf.jjccountrycity.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.jjccountrycity.domain.Country;
import com.sf.jjccountrycity.service.DataProcessor;

@RestController
public class ResultController {
	
	@Autowired
	private DataProcessor dataProcessor;
	
	@GetMapping("/result")
	public Collection<Country> getResults() {
		return dataProcessor.generateResults();
	}
}
