package com.qa.steps;

import com.qa.engine.ProjectBase;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.MediaEntityBuilder;

public class Hooks extends ProjectBase {

    public static PrintStream printStream;

    @Before
    public void sessionIn(Scenario scenario) {
        String scenarioName = scenario.getName().contains(" ") ? scenario.getName().replace(" ", "") : scenario.getName();

        String fileName = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))
                .replace("-", "");

        try {
            printStream = new PrintStream("target/runLogs/" + scenarioName.replace("\"", "") + "_" + fileName + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        logInfo("//===============Session In=======================//\n");
        logInfo("Test Execution started at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss")));
        logInfo("Scenario '" + scenario.getName() + "' is executing..");
    }

    @After
    public void sessionOut(Scenario scenario) {
        boolean failed = scenario.isFailed();

        if (!failed)
            logInfo("^^^^Test successfully completed^^^^", scenario.getSourceTagNames().iterator().next());
        else logError("\n^^^^Test completed with failures - check report for failure^^^^\n");

        logInfo("Test Execution Completed at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss")) + "\n");
        logInfo("//===============Session Out=======================//\n\n");
        printStream.close();
    }

    @After
    public void afterScenario(final Scenario scenario) {
        Date day = new Date();
        String date = new SimpleDateFormat("dd-MMM-YY HH-mm").format(day);
        if (getDriver() != null) {
            try {
                if (scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                    Allure.addAttachment("FailedScreenshot", new ByteArrayInputStream(screenshot));
                }
            } catch (Exception e) {
                logError("Error taking screenshot: " + e);
            }
        }
    }

}

