package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import util.BrowserFactory;

public class AddCatagoryPage extends BrowserFactory {
	WebDriver driver;

	SoftAssert softAssert;

	public AddCatagoryPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@name='categorydata']")
	WebElement ADD_NEW_CATEGORY;
	@FindBy(xpath = "//input[@value='Add category']")
	WebElement ADD_NEW_CATEGORY_BUTTON;
	@FindBy(xpath = "select[@name='due_month']")
	public static WebElement VALIDATE_MONTH_NAMES;
	@FindBy(xpath = "//body")
	WebElement DUPLICATE_CATEGORY_MESSAGE;

	String enteredCatagory;

	// Corresponding method
	public void userShoudbeAbleToAddNewcategoryItem(String addNewItem) {
		enteredCatagory = addNewItem + generateRandomNumber(999);
		ADD_NEW_CATEGORY.sendKeys(enteredCatagory);
	}

	public void userClicksOnAddCategoryButton() {
		ADD_NEW_CATEGORY_BUTTON.click();
	}

	public void validateNewCategoryIsAdded(String addNewItem) {

		Assert.assertEquals(enteredCatagory,
				driver.findElement(By.xpath("//span[contains(text(),'" + enteredCatagory + "')]")).getText(),
				"Added new category not found");
	}

	public void selectFromDropdown(WebElement element) {
		String before_xpath = "//*[@id='extra']/select[3]/option[";
		String after_xpath = "]";
		for (int i = 2; i <= 13; i++) {
			String month = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(month);
		}
	}

	public void insertExistingCatagory() {
		ADD_NEW_CATEGORY.sendKeys(enteredCatagory);
	}

	public void validateDuplicateMsg(String expectedDuplicateMsg) {
		String duplicateMsg = DUPLICATE_CATEGORY_MESSAGE.getText();
		String[] splitedDuplicatedMsg = duplicateMsg.split(":");

		Assert.assertEquals(splitedDuplicatedMsg[0], expectedDuplicateMsg, "Doesn't match!!!");
	}

}
