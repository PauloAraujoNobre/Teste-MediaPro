package com.media.pro.controller;

import static org.springframework.http.ResponseEntity.status;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.media.pro.model.CompetitionList;
import com.media.pro.model.Highlight;
import com.media.pro.model.HighlightExtend;
import com.media.pro.service.FootService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/v1/foot")
public class FootController 
{
	@Autowired private FootService footService;
	@Autowired protected ObjectMapper jsonMapper;
	
	@GetMapping("/list-all-data")
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET}, allowedHeaders = "*")
    public ResponseEntity<?> requestDataListFromFootApi()
    {        
		try {
			CompetitionList response = footService.requestFootApi();
			return status(HttpStatus.OK).body(response);
		} catch (IOException e) {
			log.info("GET - /v1/foot/list-all-data - Error request data from foot api");
			e.printStackTrace();
		}
		
        return status(HttpStatus.BAD_REQUEST).body("Error request data from foot api");
    }
	
	@GetMapping("/list")
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET}, allowedHeaders = "*")
    public ResponseEntity<?> requestCompetitionListFromFootApi()
    {        
		try {
			ArrayList<String> response = footService.getCompetitionStringFromData();
			return status(HttpStatus.OK).body(response);
		} catch (IOException e) {
			log.info("GET - /v1/foot/list - Error request data from foot api");
			e.printStackTrace();
		}
		
        return status(HttpStatus.BAD_REQUEST).body("Error request data from foot api");
    }
	
	@GetMapping("/highlight/{competitionName}")
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET}, allowedHeaders = "*")
    public ResponseEntity<?> requestHighlightFromFootApi(@PathVariable String competitionName)
    {     
		try {
			Highlight response = footService.getHighlightFromData(competitionName);
			return status(HttpStatus.OK).body(response);
		} catch (IOException e) {
			log.info("GET - /v1/foot/highlight/{competitionName} - Error request data from foot api");
			e.printStackTrace();
		}
		
        return status(HttpStatus.BAD_REQUEST).body("Error request data from foot api");
    }
	
	@GetMapping("/highlight/{competitionName}/{highlightIndex}")
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET}, allowedHeaders = "*")
    public ResponseEntity<?> requestHighlightFromFootApi(@PathVariable String competitionName,
    		@PathVariable int highlightIndex)
    {     
		try {
			HighlightExtend response = footService.getHighlightExtendFromData(competitionName,
					highlightIndex);
			return status(HttpStatus.OK).body(response);
		} catch (IOException e) {
			log.info("GET - /v1/foot/highlight/{competitionName}/{highlightIndex} - Error request data from foot api");
			e.printStackTrace();
		}
		
        return status(HttpStatus.BAD_REQUEST).body("Error request data from foot api");
    }
}