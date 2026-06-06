package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{
	//We can invoke parent class constructor by using super keyword
	
	//construtor		
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	//Locator
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	/*@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement radiobtnSubscribeYes;*/
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btnprivacyPolicyTrue;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMessage;
	
	
	
	//Action Methods
	public void setFirstName(String firstName)
	{
		txtFirstname.sendKeys(firstName);
	}
	
	public void setLastName(String lastName)
	{
		txtLastname.sendKeys(lastName);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephoneNo)
	{
		txtTelephone.sendKeys(telephoneNo);
	}
	
	public void setPassword(String Password)
	{
		txtPassword.sendKeys(Password);
	}
	
	public void setConfirmPassword(String Password)
	{
		txtConfirmPassword.sendKeys(Password);
	}
	
	/*public void clickYesOnSubscribBtn()
	{
		radiobtnSubscribeYes.click();
	}*/
	
	public void setPrivacyPoliciesToYes()
	{
		btnprivacyPolicyTrue.click();
	}
	
	public void clickContinueBtn()
	{
		//Solu1
		btnContinue.click();
		
		//Solu2
		//btnContinue.submit();
		
		//Solu3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//Solu4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//Solu5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Solu6
		//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue).click();
	}
	
	/*public void isconfirmationMessageDis()
	{
		confirmationMessage.getTagName();
	}*/
	
	public String getConfirmationMsg() {
		{
			try {
				return (confirmationMessage.getText());
			}catch (Exception e) {
				return (e.getMessage());
			}
		}
	}
}
