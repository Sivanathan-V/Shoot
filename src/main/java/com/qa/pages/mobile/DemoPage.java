package com.qa.pages.mobile;

import com.qa.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoPage extends BasePage {

    @FindBy(id = "com.pcloudy.appiumdemo:id/accept")
    protected WebElement licence;

    @FindBy(id = "com.pcloudy.appiumdemo:id/ecLoginButton")
    protected WebElement loginButton;

    @FindBy(xpath = "//*[contains(@resource-id,\"username\")]")
    protected WebElement username;

    @FindBy(xpath = "//*[contains(@resource-id,\"password\")]")
    protected WebElement password;

    @FindBy(xpath = "//*[contains(@resource-id,\"loginbtn\")]")
    protected WebElement login;

    @FindBy(id = "com.pcloudy.appiumdemo:id/spinnerfrom")
    protected WebElement spinnerFrom;

    @FindBy(xpath = "//*[@resource-id='android:id/select_dialog_listview']//android.widget.CheckedTextView[7]")
    protected WebElement spinnerFromSelect;

    @FindBy(id = "com.pcloudy.appiumdemo:id/spinnerto")
    protected WebElement spinnerTo;

    @FindBy(xpath = "//*[@resource-id='android:id/select_dialog_listview']//android.widget.CheckedTextView[8]")
    protected WebElement spinnerToSelect;

    @FindBy(id = "com.pcloudy.appiumdemo:id/returnTrip")
    protected WebElement returnTrip;

    @FindBy(id = "com.pcloudy.appiumdemo:id/spinnerflight")
    protected WebElement spinnerFlight;

    @FindBy(xpath = "//*[@resource-id='android:id/select_dialog_listview']//android.widget.CheckedTextView[3]")
    protected WebElement spinnerFlightList;

    @FindBy(id = "com.pcloudy.appiumdemo:id/searchFlights")
    protected WebElement searchFlight;

    @FindBy(id = "com.pcloudy.appiumdemo:id/Sea")
    protected WebElement message;

    @Override
    protected void waitForAppToLoad() {
    }

    public void licence() {
        click(licence);
    }

    public void loginButton() {
        click(loginButton);
    }

    public DemoPage username(String value) {
        clickAndSendKeys(password, value);
        return this;
    }

    public DemoPage password(String value) {
        clickAndSendKeys(password, value);
        return this;
    }

    public void login() {
        click(login);
    }

    public void spinnerFrom() {
        tapOnPosition(spinnerFrom.getLocation().x, spinnerFrom.getLocation().y);
        //click(spinnerFrom);
    }

    public void spinnerFromList() {
        click(spinnerFromSelect);
    }

    public void spinnerTo() {
        click(spinnerTo);
    }

    public void spinnerToList() {
        click(spinnerToSelect);
    }

    public void returnTrip() {
        click(returnTrip);
    }

    public void spinnerFlight() {
        click(spinnerFlight);
    }

    public void spinnerFlightList() {
        click(spinnerFlightList);
    }

    public void searchFlight() {
        click(searchFlight);
    }

    public String message() {
        return getAttribute(message, "text");
    }
}


