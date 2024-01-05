package com.qa.pages.ui;

import com.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[@id='login-link']")
    protected WebElement btnLoginLink;
    @FindBy(xpath = "//input[@type='text']")
    protected WebElement txtUserName;
    @FindBy(xpath = "//input[@type='password']")
    protected WebElement txtPassword;
    @FindBy(xpath = "//span[text()='Submit']")
    protected WebElement btnLogin;
    @FindBy(xpath = "//div[text()='  Demo user ']")
    protected WebElement txtUserVerification;

    @Override
    protected void waitForAppToLoad() {
    }
    public LoginPage userName(String value) {
        click(btnLoginLink);
        sendKeys(txtUserName, value);
        return this;
    }

    public LoginPage passWord(String value) {
        sendKeys(txtPassword, value);
        return this;
    }

    public void login() {
        click(btnLogin);
    }

    public String getDetails() {
        return txtUserVerification.getText();
    }

}
