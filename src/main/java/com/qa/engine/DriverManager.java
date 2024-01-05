package com.qa.engine;

import com.qa.engine.mobile.CapabilitiesManager;
import com.qa.engine.mobile.ServerManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.InvalidServerInstanceException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class DriverManager extends ProjectBase {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver(String domain) throws Exception {
        WebDriver driver = null;
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        switch (domain.toUpperCase()) {
            case "WEB":
                WebDriverManager.edgedriver().setup();
                EdgeOptions option = new EdgeOptions();
                option.addArguments("--start-maximized");
                option.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(option);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                DriverManager.driver.set(driver);
                this.setDriver(driver);
                break;
            case "MOBILE":
                try {
                    new ServerManager().startServer();
                } catch (InvalidServerInstanceException e) {
                    throw new InvalidServerInstanceException(e.getMessage());
                }

                customData.setPlatformName(params.getPlatformName());
                try {
                    log().info("initializing Appium driver...");
                    switch (params.getPlatformName()) {
                        case "Android":
                            driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCapabilities());
                            //driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), new CapabilitiesManager().getCapabilities());
                            break;
                        case "iOS":
                            driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), new CapabilitiesManager().getCapabilities());
                            break;
                    }
                    if (driver == null) {
                        throw new Exception("driver is null.");
                    }
                    log().info("Driver is initialized");
                    DriverManager.driver.set(driver);
                    this.setDriver(driver);
                } catch (IOException e) {
                    e.printStackTrace();
                    log().fatal("Driver initialization failed.");
                    throw e;
                }
                break;
            default:
                log().info("Wrong domain is entered");
        }
    }
}
