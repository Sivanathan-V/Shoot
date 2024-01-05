package com.qa.steps.ui;

import com.qa.engine.DriverManager;
import com.qa.engine.ProjectBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.qa.pages.ui.LoginPage;

public class LoginStep extends ProjectBase {

    @Given("User is on the Cyclos LoginPage")
    public void userIsOnTheCyclosLoginPage() throws Exception {
        new DriverManager().initializeDriver(properties.getProperty("domain"));
        getDriver().get(properties.getProperty("web.url"));
        logInfo("URL launched");
    }

    @When("User should login {string}")
    public void userShouldLogin(String username) {
        new LoginPage().userName(username).passWord(properties.getProperty(username+".password")).login();
        logInfo("Username: "+username+" entered");
    }

    @Then("User should verify after Login success message {string}")
    public void userShouldVerifyAfterLoginSuccessMessage(String value) {
        Assert.assertEquals(new LoginPage().getDetails(), value);
    }

}
