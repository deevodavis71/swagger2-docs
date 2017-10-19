package com.sjd.swaggertest;

import com.fasterxml.jackson.annotation.JsonView;
import com.sjd.swaggertest.dto.ResourceDTO;
import com.sjd.swaggertest.views.Views;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(code = 500, message = "Internal server error")})
    public String getTestMessage() {
        log.debug("Called getTestMessage ...");
        return "Test Message : " + testingMessage;
    }

    @RequestMapping(path = "/testJsonView", method = RequestMethod.POST)
    @ApiOperation(value = "Returns all of the defined capabilities for an order", notes = "Returns all capabilities (supports paging)", response = ResourceDTO.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created the object", response = ResourceDTO.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    @JsonView(Views.Read.class)
    public ResponseEntity<ResourceDTO> write(@JsonView(Views.Write.class) @RequestBody ResourceDTO dto) {

        System.out.println("Received : " + dto);

        ResourceDTO ret = new ResourceDTO();
        ret.setOnRead(999);
        ret.setOnWrite(888);
        ret.setGlobal("ACK");

        return new ResponseEntity<>(ret, HttpStatus.CREATED);
    }
}