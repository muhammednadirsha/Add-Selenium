import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Set;

public class ExtensionValidate {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/test/Extutils/chromedriver.exe");

        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("src/test/Extutils/Exploratory-Testing-Chrome-Extension_v2.1.4.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY,opt);

        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        JavascriptExecutor js= (JavascriptExecutor) driver;
        driver.get("http://demo.automationtesting.in/Register.html");
        Thread.sleep(4000);
        js.executeScript("window.open('');");

        String mainWindow = "";
        Set<String> windows = driver.getWindowHandles();
        for(String window:windows){
            if(!driver.switchTo().window(window).getTitle().equals("Register")){
                driver.get("chrome-extension://khigmghadjljgjpamimgjjmpmlbgmekj/popup.html");
                driver.findElement(By.xpath("//button[@id='NoteBtn']")).click();
                driver.findElement(By.xpath("//textarea[@id='newNoteDescription']")).sendKeys("This is a test data");
                Thread.sleep(3000);
                driver.findElement(By.xpath("//button[@id='addNewNoteBtn']")).click();
                Thread.sleep(3000);
            }
            else {
                mainWindow = window;
            }
        }
        driver.switchTo().window(mainWindow);
        Thread.sleep(5000);
        driver.quit();
    }
}
