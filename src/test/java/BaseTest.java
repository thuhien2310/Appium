

        import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.MobileElement;
        import io.appium.java_client.android.AndroidDriver;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;

        import java.net.MalformedURLException;
        import java.net.URL;


        public class BaseTest
{
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    //Real Device
/*
    @BeforeMethod

    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Galaxy J2 Prime");
        caps.setCapability("udid", "4200224bfec9c377"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "6.0.1");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.tripi.android");
        caps.setCapability("appActivity", "com.tripi.android.ui.SplashActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 20);
    }
*/
    //Virtual Device

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Galaxy Nexus API 24");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.tripi.android");
        caps.setCapability("appActivity", "com.tripi.android.ui.SplashActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 20);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}


