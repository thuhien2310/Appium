import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.util.Calendar;
import java.util.List;

import utils.TimeUtils;

public class FlightTestCases extends BaseTest
{
    @Test
    @Parameters({"from_airport", "to_airport", "from_date_add", "to_date_add", "adult_num", "child_num", "infant_num"})
    public void SearchFlight(String from_airport, String to_airport, int from_date_add, int to_date_add, int adult_num, int child_num, int infant_num) throws InterruptedException
    {
        System.out.println("-----Thuc hien viec tim kiem ve may bay tu "+from_airport+" den "+to_airport+".Tong so hanh khach: "+(adult_num+child_num+infant_num));
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/scrolling_background"))).click();

        //Click and pass Tutorial
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/next"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/next"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.tripi.android:id/done"))).click();

        // Tap chon nut Ve may bay
        MobileElement flightSearch = driver.findElement(By.id("com.tripi.android:id/doSearchFlights"));
        flightSearch.click();
        // Tap chon vao search field, nhap search key
        MobileElement searchField = driver.findElement(By.id("com.tripi.android:id/searchField"));
        searchField.sendKeys(from_airport);
        Thread.sleep(1000);
        // The tro den khung ket qua goi y
        MobileElement listLocations = driver.findElement(By.id("com.tripi.android:id/listLocation"));
        // khai bao mot mang danh sach ket qua
        List<MobileElement> localtions = listLocations.findElements(By.className("android.widget.RelativeLayout"));
        // duyet tung phan tu trong mang
        for (MobileElement element : localtions) {
            // dung try/catch de bat neu co loi xay ra, bao cho may khong can bao loi neu co loi xay ra trong qua trinh duyet
            try {
                String text = element.findElement(By.id("com.tripi.android:id/tvSubtitle2")).getText();
                if (text.equals(from_airport)) {
                    element.click();
                    break;
                }
            } catch (Exception e) {
            }
        }
        //nhap diem den
        MobileElement arrivalStation = driver.findElement(By.id("com.tripi.android:id/destinationAirport"));
        arrivalStation.click();
        MobileElement searchField2 = driver.findElement(By.id("com.tripi.android:id/searchField"));
        searchField2.sendKeys(to_airport);
        Thread.sleep(1000);
        // The tro den khung ket qua goi y
        MobileElement listLocations2 = driver.findElement(By.id("com.tripi.android:id/listLocation"));
        // khai bao mot mang danh sach ket qua
        List<MobileElement> localtions2 = listLocations2.findElements(By.className("android.widget.RelativeLayout"));
        // duyet tung phan tu trong mang
        for (MobileElement element : localtions2) {
            // dung try/catch de bat neu co loi xay ra, bao cho may khong can bao loi neu co loi xay ra trong qua trinh duyet
            try {
                String text2 = element.findElement(By.id("com.tripi.android:id/tvSubtitle2")).getText();
                if (text2.equals(to_airport)) {
                    element.click();
                    break;
                }
            } catch (Exception e) {
            }
        }

        // Tim element so nguoi
        MobileElement GuestDiv = driver.findElement(By.id("com.tripi.android:id/lnTotalGuest"));
        GuestDiv.click();
        MobileElement adultNum = driver.findElement(By.id("com.tripi.android:id/btnAdultsPlus"));
        for (int i = 1; i < adult_num; i++) {
            adultNum.click();
        }
        TimeUtils.sleep(2);
        MobileElement childNum = driver.findElement(By.id("com.tripi.android:id/btnChildPlus"));
        for (int i = 0; i < child_num; i++) {
            childNum.click();
        }
        TimeUtils.sleep(2);
        MobileElement infantNum = driver.findElement(By.id("com.tripi.android:id/btnBabyPlus"));
        for (int i = 0; i < infant_num; i++) {
            infantNum.click();
        }
        TimeUtils.sleep(2);


        // Click vao nut Hoan tat chon so nguoi
        MobileElement DoneButtonDiv = driver.findElement(By.id("com.tripi.android:id/btnDone"));
        DoneButtonDiv.click();

        //CHON NGAY DI/NGAY VE
        // Tim khung ngay di/ngay ve
        MobileElement DateDiv = driver.findElement(By.id("com.tripi.android:id/ticketDayContainer"));

        // Tim element ngay di va chon truong ngay di, element ngay di la phan tu dau tien cua mang
        List<MobileElement> dateFields =  DateDiv.findElements(By.className("android.widget.LinearLayout"));
        MobileElement FromDateDiv = dateFields.get(0);
        FromDateDiv.click();

        //Tinh toan ngay di theo bien so truyen vao
        Calendar now = Calendar.getInstance();
        int monthOfNow = now.get(Calendar.MONTH) + 1;

        System.out.println("Ngay hien tai " + now.get(Calendar.DATE) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR));
        now.add(Calendar.DATE, from_date_add);
        int from_date = now.get(Calendar.DATE);
        int from_month = now.get(Calendar.MONTH) + 1;

        System.out.println("Ngay khoi hanh chieu di muon tim kiem " + now.get(Calendar.DATE) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR));

        // Chon ngay di
        // neu thang khoi hanh bang thang hien tai
        MobileElement tableCheckinDate =driver.findElement(By.id("com.tripi.android:id/month_view"));
        List<MobileElement> days =tableCheckinDate.findElements(By.className("android.view.View"));
        MobileElement monthOfCalendarDiv = driver.findElement(By.id("com.tripi.android:id/tv_header_date_start"));
        String monthOfCalendarChain = monthOfCalendarDiv.getText();

        int index = monthOfCalendarChain.lastIndexOf('g');
        String monthOfCalendarChaina = monthOfCalendarChain.substring(index+2);
        System.out.println("monthOfCalendarChaina "+monthOfCalendarChaina);

        String[] monthOfCalendarObArray =  monthOfCalendarChaina.split(" " );

        int monthOfCalendar = Integer.parseInt(monthOfCalendarObArray[0]);
        System.out.println("monthOfCalendarObArray[0]"+monthOfCalendarObArray[0]);




        if (from_month == monthOfCalendar)
        {

            for (MobileElement day : days)
            {
                int datef = Integer.parseInt(day.getText());
                if (from_date == datef)
                {
                    day.click();
                    TimeUtils.sleep(5);
                    break;
                }
            }

        }
        // neu thang khoi hanh lon hon thang hien tai
        else if (from_month > monthOfCalendar)
        {
            int clicktime = from_month - monthOfCalendar;

            MobileElement upMonth = tableCheckinDate.findElement(By.id("com.tripi.android:id/next"));
            for (int i = 0; i < clicktime; i++)
            {
                //chuyen den thang tiep theo trong calendar
                upMonth.click();
            }
            TimeUtils.sleep(2);

            for (MobileElement day : days)
            {
                int datef = Integer.parseInt(day.getText());
                if (from_date == datef) {
                    day.click();
                    TimeUtils.sleep(5);
                    break;
                }
            }
         //   System.out.println("Ngay di: " + from_date + "/" + (now.get(Calendar.MONTH) + 1));

        }

        // An nut chon sau khi chon xong ngay di
        MobileElement selectButton = driver.findElement(By.id("com.tripi.android:id/btnApply"));
        selectButton.click();
        TimeUtils.sleep(2);

        // Tim element ngay ve va click vao truong ngay ve
        MobileElement DateDiv2 = driver.findElement(By.id("com.tripi.android:id/ticketDayContainer"));
        List<MobileElement> dateFields2 =  DateDiv2.findElements(By.className("android.widget.LinearLayout"));
        MobileElement ToDateDiv2 = dateFields2.get(2);
        ToDateDiv2.click();

        //Tinh toan ngay ve theo bien so truyen vao
        //lay ngay hien tai
        Calendar nowup = Calendar.getInstance();

        System.out.println("Ngay hien tai " + nowup.get(Calendar.DATE) + "-" + (nowup.get(Calendar.MONTH) + 1) + "-" + nowup.get(Calendar.YEAR));


        //Tinh lai ngay khoi hanh
        nowup.add(Calendar.DATE, to_date_add);

        System.out.println("Ngay khoi hanh chieu ve mong muon tim kiem " + nowup.get(Calendar.DATE) + "-" + (nowup.get(Calendar.MONTH) + 1) + "-" + nowup.get(Calendar.YEAR));
        //lay ngay khoi hanh
        int to_date = nowup.get(Calendar.DATE);
        //lay thang khoi hanh
        int to_month = nowup.get(Calendar.MONTH) + 1;


        MobileElement tableCheckoutDate =driver.findElement(By.id("com.tripi.android:id/month_view"));
        List<MobileElement> checkoutDays =tableCheckoutDate.findElements(By.className("android.view.View"));

        MobileElement monthOfCalendarIbDiv = driver.findElement(By.id("com.tripi.android:id/tv_header_date_start"));
        String monthOfCalendarIbChain = monthOfCalendarIbDiv.getText();
        System.out.println("monthOfCalendarIbChain "+monthOfCalendarIbChain);

        int indexib = monthOfCalendarIbChain.lastIndexOf('g');
        String monthOfCalendarIbChaina = monthOfCalendarIbChain.substring(indexib+2);
        System.out.println("monthOfCalendarIbChaina "+monthOfCalendarIbChaina);

        String[] monthOfCalendarIbArray =  monthOfCalendarIbChaina.split(" " );

        int monthOfCalendarIb = Integer.parseInt(monthOfCalendarIbArray[0]);
        System.out.println("monthOfCalendarIbArray[0]"+monthOfCalendarIbArray[0]);



        if (to_month == monthOfCalendarIb)
        {
            for (MobileElement day2 : checkoutDays) {

                int date2 = Integer.parseInt(day2.getText());
                if (to_date == date2) {
                    day2.click();
                    TimeUtils.sleep(5);
                    break;
                }
            }

        }
        //neu thang khoi hanh lon hon thang hien tai
        else if (to_month > monthOfCalendarIb)
        {
            int clicktime = to_month - monthOfCalendarIb;

            MobileElement upMonth2 = driver.findElement(By.id("com.tripi.android:id/next"));
            for (int i = 0; i < clicktime; i++)
            {
                //Next den thang tiep theo tren calendar
                upMonth2.click();
            }
            TimeUtils.sleep(2);
            for (MobileElement day3 : checkoutDays)
            {
                int date3 = Integer.parseInt(day3.getText());
                if (to_date == date3)
                {
                    day3.click();
                    TimeUtils.sleep(5);
                    break;
                }
            }

        }

        //An nut chon ngay ve
        MobileElement selectButton2 = driver.findElement(By.id("com.tripi.android:id/btnApply"));
        selectButton2.click();
        //---------------------------------


        // An nut Tim kiem
        MobileElement SearchButtonDiv = driver.findElement(By.id("com.tripi.android:id/btnSearchFlightTicket"));
        SearchButtonDiv.click();
        TimeUtils.sleep(20);
        System.out.println("Da thuc hien tim kiem xong");

    }



    //------------------------------------------- Method 2---Kiem tra danh sach ve tra ve co du ve cua 3 hang VNA, VJ, JS hay khong
    @Parameters({"from_airport_checkairlines","to_airport_checkairlines","from_date_checkairlines_add", "to_date_checkairlines_add","adult_num_checkairlines","child_num_checkairlines","infant_num_checkairlines"})
    @Test(priority = 2, enabled = true)
    public void CheckEnoughThreeAirlines(String from_airport_checkairlines, String to_airport_checkairlines, int from_date_checkairlines_add, int to_date_checkairlines_add, int adult_num_checkairlines, int child_num_checkairlines, int infant_num_checkairlines) throws InterruptedException
    {
        System.out.println("2. Kiem tra danh sach ve tra ve co du ve cua 3 hang VNA, VJ, JS hay khong");
        SearchFlight(from_airport_checkairlines, to_airport_checkairlines, from_date_checkairlines_add, to_date_checkairlines_add, adult_num_checkairlines,child_num_checkairlines,infant_num_checkairlines);
        // Check ket qua
        // Check outBoundTickets
        // kiểm tra danh sách vé chiều đi có đủ vé của 3 hãng VJ, JS, VNA hay không?

        MobileElement outBoundTicketsDiv = driver.findElement(By.id("com.tripi.android:id/listTickets"));
        List<MobileElement> outboundTickets = outBoundTicketsDiv.findElements(By.className("android.widget.FrameLayout"));
        int jetstarNum = 0;
        int vietjetNum = 0;
        int vnairline = 0;
        System.out.println("Danh sach ve chieu di:");
        for (MobileElement ticket : outboundTickets)
        {
            MobileElement logo = ticket.findElement(By.id("com.tripi.android:id/ticketCode"));
            String agency = logo.getText();
            System.out.println(agency);
            if (agency.contains("Jetstar"))
            {
                jetstarNum++;
            } else if (agency.contains("VietJet"))
            {
                vietjetNum++;
            } else if (agency.contains("Vietnam"))
            {
                vnairline++;
            }
        }
        int numOfTickets = outboundTickets.size();

        // Check xem so luong xem > 0 ?
        //	System.out.println("Vietject number: " + vietjetNum);
        Assert.assertEquals(numOfTickets > 0, true);
        Assert.assertEquals(vietjetNum > 0, true);
        Assert.assertEquals(vnairline > 0, true);
        Assert.assertEquals(jetstarNum > 0, true);
        TimeUtils.sleep(5);


        MobileElement inBoundTicketsDiv = driver.findElement(By.id("com.tripi.android:id/listTickets"));
        List<MobileElement> inboundTickets = inBoundTicketsDiv.findElements(By.className("android.widget.FrameLayout"));

        int jetstarNumib = 0;
        int vietjetNumib = 0;
        int vnairlineib = 0;
        System.out.println("Danh sach ve chieu ve:");
        for (MobileElement ticketib : inboundTickets)
        {
            MobileElement logoib = ticketib.findElement(By.id("com.tripi.android:id/ticketCode"));
            String agencyib = logoib.getText();
            System.out.println(agencyib);
            if (agencyib.contains("Jetstar")) {
                jetstarNumib++;
            } else if (agencyib.contains("VietJet")) {
                vietjetNumib++;
            } else if (agencyib.contains("Vietnam")) {
                vnairlineib++;
            }
        }

        int numOfTicketsib = inboundTickets.size();

        // Check xem so luong xem > 0 ?
        Assert.assertEquals(numOfTicketsib > 0, true);
        Assert.assertEquals(vietjetNumib > 0, true);
        Assert.assertEquals(vnairlineib > 0, true);
        Assert.assertEquals(jetstarNumib > 0, true);
        System.out.println("==> PASSED");
        TimeUtils.sleep(5);
    }

}