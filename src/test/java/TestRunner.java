import com.qa.engine.DriverManager;
import com.qa.engine.GlobalParams;
import com.qa.engine.mobile.ServerManager;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

@CucumberOptions(features = {"target/classes/features"},
        glue = {"com.qa.steps"},
        plugin = {"json:target/cucumber-results/output.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger LOGGER = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

    @BeforeClass(
            alwaysRun = true
    )
    @Override
    public void setUpClass(ITestContext context) {
        try {
            new File("target", "runLogs").mkdirs();

            GlobalParams params = new GlobalParams();
            params.initializeGlobalParams();

            super.setUpClass(context);
        } catch (Throwable e) {
            LOGGER.error("Class setup failed with the following error\n", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        try {
            super.runScenario(pickleWrapper, featureWrapper);
        } catch (Throwable e) {
            LOGGER.error("Scenario failed with the following error\n", e);
            throw e;
        }
    }

    @AfterClass
    public static void quit() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager = new ServerManager();
        if (serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
    }
}
