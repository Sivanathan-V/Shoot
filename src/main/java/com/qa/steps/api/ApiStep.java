package com.qa.steps.api;


import com.qa.engine.DriverManager;
import com.qa.engine.ProjectBase;
import com.qa.pages.api.Endpoints.Endpoints;
import com.qa.engine.api.Route;
import com.qa.pages.api.cyclos.outputPojo.booking_output_pojo.AddUser_Output_Pojo;
import com.qa.pages.api.cyclos.outputPojo.token.CreateToken_Output_Pojo;
import com.qa.pages.api.jiraPojo.outputPojo.PostBugOutputPojo;
import com.qa.pages.api.jiraPojo.payload.Payload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ApiStep extends ProjectBase {

    @Given("User adds the header")
    public void user_adds_the_header() {
        RestAssured.baseURI = properties.getProperty("baseuri");
        new Endpoints().addHeader();
    }

    @When("User add payload for basic authentication")
    public void user_add_payload_for_basic_authentication() {
        new Endpoints().addPayload(properties.getProperty("api_username"), properties.getProperty("api_password"));
    }

    @When("User send {string} request for creating token")
    public void user_send_request_for_creating_token(String reqMethod) {
        Response response = new Endpoints().responseBodyToken(reqMethod, Route.generateToken());
        customData.setResponse(response);
        customData.setStatusCode(response.getStatusCode());
    }

    @Then("User get the logtoken")
    public void user_get_the_logtoken() {
        CreateToken_Output_Pojo createTokenOutputPojo = customData.getResponse().as(CreateToken_Output_Pojo.class);
        String actualToken = createTokenOutputPojo.getToken();
        customData.setToken(actualToken);
    }

    @When("User send {string} request to booking list endpoint")
    public void user_send_request_to_booking_list_endpoint(String reqMethod) {
        Response response = new Endpoints().responseBodyTokenBooking(reqMethod);
        customData.setStatusCode(response.getStatusCode());
    }

    @When("User adds the payload for create booking {string},{string},{string},{string},{string},{string},{string}")
    public void user_adds_the_payload_for_create_booking(String firstName, String lastName, String totalPrice, String dp, String checkIn, String checkOut, String additionalNeeds) {
        int price = Integer.parseInt(totalPrice);
        boolean depositPaid = Boolean.valueOf(dp);
        new Endpoints().requestPayLoad(firstName, lastName, price, depositPaid, checkIn, checkOut, additionalNeeds);

    }

    @When("User send {string} request for creating a booking")
    public void user_send_request_for_creating_a_booking(String requestMethod) {
        Response response = new Endpoints().responseBodyTokenBooking(requestMethod);
        customData.setStatusCode(response.getStatusCode());
        AddUser_Output_Pojo addUserOutputPojo = response.as(AddUser_Output_Pojo.class);
        int bookingID = addUserOutputPojo.getBookingid();
        customData.setBookingId(String.valueOf(bookingID));
    }

    @Then("Verify the status code is {int}")
    public void verify_the_status_code_is(int expectedStatus) {
        int actualStatus = customData.getStatusCode();
        Assert.assertEquals("Verify Status Code", expectedStatus, actualStatus);
    }

    @Given("I login to cyclops demo website with wrong credentials")
    public void iLoginToCyclopsDemoWebsiteWithWrongCredentials() throws Exception {

        new DriverManager().initializeDriver(properties.getProperty("domain"));
        getDriver().get(properties.getProperty("web.url"));
        logInfo("URL launched");
        WebElement btnLogin = getDriver().findElement(By.xpath("//a[@id='login-link']"));
        btnLogin.click();
        WebElement textId = getDriver().findElement(By.xpath("//input[@type='text']"));
        textId.sendKeys(properties.getProperty("api_username"));
        WebElement textPwd = getDriver().findElement(By.xpath("//input[@type='password']"));
        textPwd.sendKeys(properties.getProperty("api_password"));
        WebElement btnSubmit = getDriver().findElement(By.xpath("//span[text()='Submit']"));
        btnSubmit.click();

        TakesScreenshot file = (TakesScreenshot) getDriver();
        File screenshotAs = file.getScreenshotAs(OutputType.FILE);
        File source = new File(properties.getProperty("screenShotPath"));
        FileUtils.copyFile(screenshotAs, source);
    }


    @When("I post defect in jira")
    public void iPostDefectInJira() {
       new Endpoints().addHeader()
                      .addBasicAuth(String.valueOf(properties.get("jira_username")), String.valueOf(properties.get("jira_password")))
                      .addBody(Payload.postBugPayload());

        Response response = new Endpoints().responseBodyToken("POST", Route.EndPointBug());
        int statusCode = response.getStatusCode();
        customData.setStatusCode(statusCode);
        PostBugOutputPojo postBugOutputPojo = response.as(PostBugOutputPojo.class);
        id = postBugOutputPojo.getId();
        customData.setId(id);
        key = postBugOutputPojo.getKey();
        customData.setKey(key);

    }

    @And("I add screenshot to the defect")
    public void iAddScreenshotToTheDefect() {
        new Endpoints().addFileHeaders("X-Atlassian-Token", "no-check",properties.getProperty("screenShotPath"))
                       .addBasicAuth(properties.getProperty("jira_username"), properties.getProperty("jira_password"));
        Response response = new Endpoints().responseBodyToken("POST", Route.EndPointBug()+customData.getId()+"/Attachments");
        int statusCode = response.statusCode();
        customData.setStatusCode(statusCode);
    }

    @Then("I should verify status code {string}")
    public void iShouldVerifyStatusCode(String code) {
        Assert.assertEquals(String.valueOf(customData.getStatusCode()),code);
    }

    @And("I should see the defect details")
    public void iShouldSeeTheDefectDetails() {
        Response response = new Endpoints().addHeader()
                       .addBasicAuth(properties.getProperty("jira_username"), properties.getProperty("jira_password"))
                       .requestType("GET",Route.EndPointBug()+customData.getId());
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        String String = response.asPrettyString();
        System.out.println(String);
    }

    @When("I delete the defect")
    public void iDeleteTheDefect() {
        Response response = new Endpoints().addHeader()
                .addBasicAuth(properties.getProperty("jira_username"), properties.getProperty("jira_password"))
                .requestType("DELETE",Route.EndPointBug()+customData.getId());
        int statusCode = response.statusCode();
        customData.setStatusCode(statusCode);
        System.out.println("Delete: "+statusCode);
    }

    @When("I update my defect")
    public void iUpdateMyDefect() {
        Response response = new Endpoints().addHeader()
                .addBasicAuth(properties.getProperty("jira_username"), properties.getProperty("jira_password"))
                .addBody(Payload.postBugPayload()).requestType("PUT", Route.EndPointBug()+customData.getId());
        int statusCode = response.getStatusCode();
        customData.setStatusCode(statusCode);
        System.out.println("Update: "+statusCode);
    }
}
