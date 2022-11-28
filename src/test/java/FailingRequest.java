import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.fetch.Fetch;
import org.openqa.selenium.devtools.v107.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v107.network.model.ErrorReason;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//video221
public class FailingRequest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
//        URL we are targeting: https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty
//        parameter1: URL -> we are targeting the url that goes to a page named GetBook
//        parameter others: We are saying value is absent take whatever is present
//        We are first make it a list then wrap it with Optional. This is how the method is designed by CDP
        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty() )));

        devTools.send(Fetch.enable(patterns, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request -> {
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

    }
}
