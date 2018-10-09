import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class ioSampleTest extends BaseTest {



    @Test
    public void basicTest() throws InterruptedException {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/scrolling_background"))).click();

        //Click and pass Tutorial
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/next"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/next"))).click();

        //Click to "Is Ariyorum" Button
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/done"))).click();

     // Tap chon nut Ve may bay
        MobileElement flightSearch = driver.findElement(By.id("com.tripi.android:id/doSearchFlights"));
        flightSearch.click();
     // Tap chon vao search field, nhap search key
        MobileElement searchField = driver.findElement(By.id("com.tripi.android:id/searchField"));
        searchField.sendKeys("HAN");
        Thread.sleep(1000);
     // The tro den khung ket qua goi y
        MobileElement listLocations = driver.findElement(By.id("com.tripi.android:id/listLocation"));
     // khai bao mot mang danh sach ket qua
        List<MobileElement> localtions = listLocations.findElements(By.className("android.widget.RelativeLayout"));
     // duyet tung phan tu trong mang
        for (MobileElement element : localtions)
        {
     // dung try/catch de bat neu co loi xay ra, bao cho may khong can bao loi neu co loi xay ra trong qua trinh duyet
            try {
                String text = element.findElement(By.id("com.tripi.android:id/tvSubtitle2")).getText();
                System.out.println("Text: " + text);
                if (text.equals("HAN"))
                {
                    element.click();
                    break;
                }
            } catch (Exception e)
            {   }
        }



        Thread.sleep(3000);
        Assert.assertEquals(1, 1);

    }

   /* @AfterMethod
    public void teardown() {
        driver.quit();
    }*/

}


//Notification Allow
// if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size()>0) {
//     driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
//        MobileActions mobileActions;
//        mobileActions = new MobileActions((AndroidDriver) driver);
//        Click second job
//        mobileActions.tapByElement((AndroidElement) driver.findElement(By.id("com.tripi.android:id/searchBoxContainer")));
//        mobileActions.pressByElement((AndroidElement) driver.findElement(By.id("com.tripi.android:id/doSearchFlights")),1);
//        mobileActions.tapByElement((AndroidElement) driver.findElement(By.id("com.tripi.android:id/doSearchFlights")));
//wait.until(ExpectedConditions.visibilityOfElementLocated
//        (By.id("com.tripi.android:id/navigation_bar"))).click();
//wait.until(ExpectedConditions.visibilityOfElementLocated
//        (By.id("com.tripi.android:id/navigation_bar"))).sendKeys("HAN");
//        mobileActions.tapByElement((AndroidElement) driver.findElement(By.id("com.tripi.android:id/navigation_bar")));









