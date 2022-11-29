import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.ConnectionType;

import java.util.Optional;

public class NetworkLoadingFailed {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //        First we need enable the network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        manipulating network items
        devTools.send(Network.emulateNetworkConditions(true, 3000,20000, 100000, Optional.of(ConnectionType.CELLULAR2G)));
//        parameters
//        1: network would not be active


//        Failed to load the website
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println( loadingFailed.getErrorText());
            System.out.println( loadingFailed.getTimestamp());

        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();


    }
}
