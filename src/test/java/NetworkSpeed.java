import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.ConnectionType;

import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //        First we need enable the network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        manipulating network items
        devTools.send(Network.emulateNetworkConditions(false, 3000,20000, 100000, Optional.of(ConnectionType.CELLULAR2G)));
//        parameters
//        1: network would be active
//        2. There would be a 3s delay in fetching results
//        3. The speed of download: 20000 bytes/sec
//        3. The speed of upload: 100000 bytes/sec
//        4. Optional field: network speed 2g

//        capturing the time
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

//        end time
        long endTime = System.currentTimeMillis();
        System.out.println("In 2g: The whole process took: 14599");
        System.out.println("The whole process took: " + (endTime-startTime));
        System.out.println("Without any network controls: The whole process took: 570");
    }
}
