package com.qa.pages.api.Endpoints;

import com.qa.pages.api.cyclos.payLoads.BookingPayloads;
import com.qa.pages.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

import static com.qa.engine.ProjectBase.logError;
import static com.qa.engine.ProjectBase.logInfo;
import static com.qa.engine.api.Route.booking;

public class Endpoints extends BasePage {

    @Override
    protected void waitForAppToLoad() {}

    public Endpoints addHeader(){
        try {
            reqSpec = RestAssured.given().header("Content-Type", "application/json");
            logInfo("Adding header: " + reqSpec);
        } catch (Exception e) {
            logError("Unable to add header: " + e.getMessage());
        }
        return this;
    }

    public void addPayload(String username, String password){
        addBody(new BookingPayloads().createTokenPayload(username, password));
    }

    public Response responseBodyToken(String type, String method){
        return requestType(type, method);
    }

    public Response responseBodyTokenBooking(String type){
        return requestType(type, booking());
    }

    public void requestPayLoad(String firstname, String lastname, int price, boolean dp, String checkIn, String checkOut, String additionalneeds){
        addBody(new BookingPayloads().bookUserPayload(firstname, lastname, price, dp, checkIn, checkOut, additionalneeds));
    }

    public Endpoints addBasicAuth(String userName, String passWord) {
        reqSpec = reqSpec.auth().preemptive().basic(userName, passWord);
        return this;
    }

    public Endpoints addBody(Object body) {
        try {
            reqSpec = reqSpec.body(body);
            logInfo("Adding body : " + reqSpec);
        } catch (Exception e) {
            logError("Unable to add object body : " + e.getMessage());
        }
        return this;
    }

    public Endpoints addFileHeaders(String key, String value, String filepath){
        reqSpec = RestAssured.given().header(key, value).multiPart("file", new File(filepath));
        return this;
    }

    public String getReqBodyAsPrettyString() {
        String asPrettyString = response.asPrettyString();
        return asPrettyString;
    }

    public Response requestType(String type, String endPoint) {

        try {
            switch (type) {
                case "GET":
                    response = reqSpec.log().all().get(endPoint);
                    break;
                case "POST":
                    response = reqSpec.log().all().post(endPoint);
                    break;
                case "PUT":
                    response = reqSpec.log().all().put(endPoint);
                    break;
                case "DELETE":
                    response = reqSpec.log().all().delete(endPoint);
                    break;
                default:
                    break;
            }
            logInfo("sending request to the endPoint : " + response);
        } catch (Exception e) {
            logError("Unable to send request to the endPoint : " + e.getMessage());
        }
        return response;
    }
}
