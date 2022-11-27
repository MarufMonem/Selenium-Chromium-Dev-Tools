import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

public class geoLocation {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map<String, Object> loc = new HashMap<>();
        loc.put("latitude", 40);
        loc.put("longitude", 3);
        loc.put("accuracy", 1);


        driver.executeCdpCommand("Emulation.setGeolocationOverride", loc);
//        driver.get("https://www.google.com/");
//        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
//        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();

//        String title =driver.findElement(By.className("our-story-card-title")).getText();
//        System.out.println(title);

        //netflix doesnt work this case

        driver.get("https://my-location.org/");
        System.out.println(driver.findElement(By.id("address")).getText());

    }
}
