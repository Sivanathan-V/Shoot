package com.qa.engine;
import com.qa.steps.Hooks;
import com.qa.utils.CustomData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.Properties;
import static com.qa.engine.DriverManager.driver;

public abstract class ProjectBase {
    protected static Properties properties;
    protected static CustomData customData = new CustomData();

    protected static String id;
    protected static String key;
    protected static String text;
    protected static int issueId;
    public WebDriver getDriver(){
        return driver.get();
    }

    public static Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public static void logInfo(String text) {
        log().info(text);
        Hooks.printStream.print("\n" + text);
    }

    public static void logInfo(String text, Object vars) {
        log().info(text, vars);
        Hooks.printStream.print("\n" + text);
    }

    public static void logError(String text) {
        log().error(text);
        Hooks.printStream.print("\n" + text);
    }
}
