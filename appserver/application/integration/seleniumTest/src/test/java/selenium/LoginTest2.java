package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoginTest2 {

    Properties prop = new Properties();
    InputStream input = null;
    String url;

    @Test
    public void acceso() throws Exception {
        // Recogemos la URL que pasamos por comando
        url = System.getProperty("url").toString();
        System.out.println(url);
        /* Lo dejo comentado por si interesa leerlo desde un archivo de configuracion
        // Leemos la url del archivo de configuración
        try {
            input = new FileInputStream("urlTestFuncionales.properties");
            prop.load(input);
            url = (prop.getProperty("url"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        */

        //Configuramos el entorno
        System.setProperty("webdriver.chrome.driver","chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        WebDriver driver = new ChromeDriver(options);
        //Ejecutamos el test
        String urlCompleta = url + "/login";
        System.out.println(urlCompleta);
        driver.get(urlCompleta);
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        // Realizo una captura de pantalla para ver en modo no gráfico lo que obtenemos
        /*
        File scrFile = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("/tmp/imagenSinExploradorlogin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("uggo1802Z./");
        driver.findElement(By.id("rememberMe")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Conta")).click();
        driver.findElement(By.linkText("Sair")).click();
        driver.quit();
    }
}
