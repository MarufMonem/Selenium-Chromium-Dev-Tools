import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.emulation.Emulation;

import java.util.Optional;

public class MobileElumatorTest {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "X:\\Self improvement\\Selenium Udemy\\driver\\chromedriver");

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
//Object needs to be created to access dev tools
        DevTools devtools =  driver.getDevTools();
//        to use dev tools we need to create sessions
        devtools.createSession();
//      Send commands to CDP methods -> CDP methods will invoke and get access to chrome dev tools
//       Trying to set the window size: https://chromedevtools.github.io/devtools-protocol/1-3/Emulation/#method-setDeviceMetricsOverride
        devtools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true, Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(), Optional.empty() ));
//        not all parameters are mandatory so to address them we need to pass a value:Optional.empty()
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.className("navbar-toggler")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Library")).click();

//        After tests are done it would go to full screen
    }
}
