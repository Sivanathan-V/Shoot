package com.qa.engine.mobile;

import com.qa.engine.GlobalParams;
import com.qa.engine.ProjectBase;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class CapabilitiesManager extends ProjectBase {

    public DesiredCapabilities getCapabilities() throws IOException {
        GlobalParams params = new GlobalParams();

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, params.getPlatformVersion());
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());
            caps.setCapability("shouldTerminateApp", true);
            caps.setCapability("unicodeKeyboard", true);
            caps.setCapability("resetKeyboard", true);
            caps.setCapability("appWaitDuration ", 30000);
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("autoAcceptAlerts", true);


            switch (params.getPlatformName()) {
                case "Android" -> {
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
//                    caps.setCapability("systemPort", params.getSystemPort());
//                    caps.setCapability("chromeDriverPort", params.getChromeDriverPort());

                    caps.setCapability("appPackage", properties.getProperty("demo.appPackage"));
                    caps.setCapability("appActivity", properties.getProperty("demo.appActivity"));
                }
                case "iOS" -> {
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("iOSAutomationName"));

                    caps.setCapability("appPackage", properties.getProperty("demo.appPackage"));
                    caps.setCapability("appActivity", properties.getProperty("demo.appActivity"));
                }
            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            log().fatal("Failed to load capabilities.");
            throw e;
        }
    }
}
