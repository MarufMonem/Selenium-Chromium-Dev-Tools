import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.ConnectionType;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.Optional;

public class ConsoleLogCapture {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

//       In real life write this under the testFailure in TESTNG listeners

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

//        All the logs would be stored in the variable
        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
//        Extracting entries
        List<LogEntry> logs =  entry.getAll();

        System.out.println("------------------------PRINTING LOGS--------------------------------");
//      Printing the lgos
        for (LogEntry e :logs
        ) {
            System.out.println(e.getMessage());
        }

        System.out.println("------END OF LOGS--------");

    }
}
