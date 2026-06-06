package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest ******");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();		
		logger.info("Clicked on MyAccount Link ");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link ");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated email
		regpage.setTelephone(randomeNumber());
		
		//String password=randomAlphaNumeric();
		String password=randomeAlphaNumeric(); //You can use static password or dynamic password
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPoliciesToYes();
		regpage.clickContinueBtn();
		
		logger.info("Validating exepected message...");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) //Incorrect Tittle given intentionally
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");
			logger.debug("Debug Logs..");
			Assert.assertTrue(false);
		}
		}
		catch (Exception e)
		{			
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest ******");
	}
}
