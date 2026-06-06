package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j


public class BaseClass {	
	//Whatever the methods required for all the testcases, we will keep it in "Base Class".
	//Note: "BaseClass" - We created for "Re-Usability". And we can Avoid Duplication.
public static WebDriver driver;
public Logger logger; //Log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties"); //Path of property file
		p=new Properties(); //created object of perperties file
		p.load(file);			
		
		logger=LogManager.getLogger(this.getClass()); //Log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
				DesiredCapabilities capabilities=new DesiredCapabilities();
				//os
				if(os.equalsIgnoreCase("windows"))
				{
					capabilities.setPlatform(Platform.WIN10);					
				}		
				else if(os.equalsIgnoreCase("mac"))
				{
					capabilities.setPlatform(Platform.MAC);
				}else
				{
					System.out.println("No matching os");
					return;
				}
				
				//borwser
				switch (br.toLowerCase()) 
				{
				case "chrome" : capabilities.setBrowserName("chrome"); break;
				case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
				default : System.out.println("No matching browser"); return;
				}	
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase()) 
			{
				case "chrome" : driver=new ChromeDriver();break;
				case "edge" : driver=new EdgeDriver();break;
				case "firefox" : driver=new FirefoxDriver();break;
				default : System.out.println("Invalid Browser name..");return;
			}			
		}				
		driver.manage().deleteAllCookies(); //It will delete all cookies if u strored. Better to have this method
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL2")); //reading URL from "Properties file".
		driver.manage().window().maximize();		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}
	
	
	//Note: Below 3 method concept is Very Very importent whenyour designing Framework-better lern it
		//Everytime a new random_String Email will be generated
		public String randomeString()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		}
		
		//Everytime a new random_Number will be generated
			public String randomeNumber()
			{
				String generatednumber=RandomStringUtils.randomNumeric(10);
				return generatednumber;
			}
			
		//Everytime a new random_Alpha-Numeric will be generated
			public String randomeAlphaNumeric()
			{
				String generatedstring=RandomStringUtils.randomAlphabetic(3); 
				String generatednumber=RandomStringUtils.randomNumeric(4);
				return generatedstring+"@*"+generatednumber;
			}
			
			//Capturing screenshot of Failed testcase
			public String captureScreen(String tname) {
				String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
				TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				
				String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp + ".png";
				File targetFile=new File(targetFilePath);
				
				sourceFile.renameTo(targetFile);
				
				return targetFilePath;
			}
}
