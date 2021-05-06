package com.crypto.cryptoassignment.util;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestActions {
    private static RequestSpecification request;
    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        RestActions.environment = environment;
    }

    public static void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(environment.getProperty("base.url"));
        requestSpecBuilder.setContentType(ContentType.JSON);
        request = RestAssured.given().spec(requestSpecBuilder.build());
    }

    public List<?> getDataAsList(String endPoint, Class<?> className, String instrumentName, String timeframe) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(APIConstants.INSTRUMENT_NAME, instrumentName);
        // As timeframe variable is empty for Trades
        if (!timeframe.isEmpty()) {
            requestParams.put(APIConstants.TIMEFRAME, timeframe);
        }
        Response response = request.params(requestParams).get(endPoint);
        return response.jsonPath().getList(APIConstants.DATA_JSON_PATH, className);
    }
}
