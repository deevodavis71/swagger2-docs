package com.sjd.swaggertest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

	@Value("${testing.message:LocalValue}")
	private String testingMessage;

	@RequestMapping(path = "/getTestMessage", method = RequestMethod.GET)
	@ApiOperation(value = "Returns all of the defined capabilities for an order", notes = "Returns all capabilities (supports paging)", response = String.class, produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of capabilities", response = String.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public String getTestMessage() {
		log.debug("Called getTestMessage ...");
		return "Test Message : " + testingMessage;
	}
}