package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddCatagoryPage;
import util.BrowserFactory;

public class AddCatagoryTest {
	WebDriver driver;
	AddCatagoryPage catagoryPage;
	String addCatagory = "robin";
	String expectedDuplicateMsg = "The category you want to add already exists";

	@BeforeTest
	public void setUp() {
		driver = BrowserFactory.initialization();
	}

	@Test(priority=1)
	public void userShouldBeAbleToAddNewCategoryItem() {
		catagoryPage = PageFactory.initElements(driver, AddCatagoryPage.class);
		catagoryPage.userShoudbeAbleToAddNewcategoryItem(addCatagory);
		catagoryPage.userClicksOnAddCategoryButton();
		catagoryPage.validateNewCategoryIsAdded(addCatagory);
		
		
	}

	@Test(priority=2)
	public void userShouldBeAbleToSeeNameOfTheMonths() {
		catagoryPage.selectFromDropdown(AddCatagoryPage.VALIDATE_MONTH_NAMES);
	}
    
	@Test(priority=3)
	  public void duplicateCatagoryInsertion() {
	  catagoryPage.insertExistingCatagory();
	  catagoryPage.userClicksOnAddCategoryButton();
	  catagoryPage.validateDuplicateMsg(expectedDuplicateMsg);
	  }
	 

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
