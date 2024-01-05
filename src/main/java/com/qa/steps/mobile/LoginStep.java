package com.qa.steps.mobile;

import com.qa.engine.DriverManager;
import com.qa.engine.ProjectBase;
import com.qa.pages.BasePage;
import com.qa.pages.mobile.DemoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStep extends ProjectBase {
    @Given("I have launched the Demo app")
    public void iHaveLaunchedTheDemoApp() {
        try {
            if (getDriver() != null)
                getDriver().quit();
            new DriverManager().initializeDriver(properties.getProperty("domain"));
            logInfo("Demo app is launched");
        } catch (Exception e) {
            logInfo("Error while launching the Demo app");
            throw new RuntimeException(e);
        }
    }
   @When("I accept user licence")
    public void iAcceptUserLicence() throws InterruptedException {
        Thread.sleep(500);
        new DemoPage().licence();
    }

    @And("I enter login credentials")
    public void iEnterLoginCredentials() throws InterruptedException {
        DemoPage demo = new DemoPage();
        Thread.sleep(500);
        demo.loginButton();

        Thread.sleep(1000);
        new BasePage() {@Override protected void waitForAppToLoad() {}}.tapOnPosition(600, 1000);

        Thread.sleep(1500);
        demo.username("11111").password("11111").login();
    }

    @And("I select flight details")
    public void iSelectFlightDetails() throws InterruptedException{
        DemoPage demo = new DemoPage();
        Thread.sleep(1500);
        demo.spinnerFrom();

        Thread.sleep(1500);
        demo.spinnerFromList();

        Thread.sleep(1500);
        demo.spinnerTo();

        Thread.sleep(1500);
        demo.spinnerToList();

        Thread.sleep(1500);
        demo.returnTrip();

        Thread.sleep(1500);
        demo.spinnerFlight();

        Thread.sleep(1500);
        demo.spinnerFlightList();

        Thread.sleep(1500);
        demo.searchFlight();
    }

    @Then("I should see the confirmation message")
    public void iShouldSeeTheConfirmationMessage() throws InterruptedException{
        Thread.sleep(1500);
        Assert.assertEquals("Booking Confirm...", new DemoPage().message(),"Booking confirmation message is not displaying");
    }
}
