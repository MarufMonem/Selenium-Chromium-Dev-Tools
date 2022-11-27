import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.Request;
import org.openqa.selenium.devtools.v107.network.model.Response;

import java.util.Optional;

public class NetworkLogsActivity {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            System.out.println("Sent: " + req.getUrl());
        });

//        we need to wait for the browser to receieve the response
        devTools.addListener(Network.responseReceived(), response -> {
            Response res = response.getResponse();
            System.out.println("Response: " + res.getUrl() + " - " + res.getStatus());
        });

//        request events
//        before request is being sent we can tag them and also get all the info

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@data-target='#exampleModal']")).click();


    }
}
