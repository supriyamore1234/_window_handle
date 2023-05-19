package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.awt.*;
///
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase10() throws InterruptedException, IOException {
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        // switch to frame by name of frame "iframeResult"
        driver.switchTo().frame("iframeResult");
        // click on try it by xpath "//button[text()='Try it']"
        WebElement tryitbutton = driver.findElement(By.xpath("//button[text()='Try it']"));
        tryitbutton.click();
        String Parent_id = driver.getWindowHandle();
        Set<String> Child_id = driver.getWindowHandles();

        // Now iterate using Iterator
        for (String a : Child_id) {
            // it will print IDs of both window
            System.out.println(a);

            // condition to change the focus of selenium
            if (Parent_id.equals(a)) {
            } else { // to change focus on new window
                driver.switchTo().window(a);
                Thread.sleep(2000);
                String currenturl = driver.getCurrentUrl();
                System.out.println("CURRENT URL OF PAGE IS : " + currenturl);
                String getText = driver.getTitle();
                System.out.println("Title Of Page is : " + getText);

            }
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "\\src\\main\\Screenshots1.jpg");
        FileUtils.copyFile(src, dest);
    }

}
