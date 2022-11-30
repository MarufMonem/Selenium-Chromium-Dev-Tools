import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuth {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

//      Written in java predicate format
       Predicate<URI> uriPredicate = uri-> uri.getHost().contains("httpbin.org"); //domain
//        The below code enables the driver to handle authentication. Casting
//        The authetication details would pass when the URI matches with the domain specified above
        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
       driver.get("http://httpbin.org/basic-auth/foo/bar");
    }
}
