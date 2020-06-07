package com.challenge.ui;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FravegaFinderPage {

	WebDriver driver;
	
	public FravegaFinderPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how=How.XPATH,using=".//*[@placeholder='Buscar productos']")
	@CacheLookup
		WebElement searchBox;
	@FindBy(how=How.XPATH,using=".//*[@name='subcategoryAggregation' and contains(text(), 'Heladeras')]")
	@CacheLookup
		WebElement filterFridge;
	@FindBy(how=How.XPATH,using=".//label[contains(text(), 'Heladeras con')]")
	@CacheLookup
		WebElement filterFridgeWith;
	@FindBy(how=How.XPATH,using=".//label[contains(text(), 'Samsung')]")
	@CacheLookup
		WebElement filterBrand;
	@FindBy(how=How.XPATH,using=".//*[@name='itemsGrid']/*/*/*/*/*/h4")
	@CacheLookup
		List<WebElement> listTitle;
	@FindBy(how=How.XPATH,using=".//*[@name='totalResult']/span")
	@CacheLookup
		WebElement result;
	@FindBy(how=How.XPATH,using=".//*[@name='breadcrumb']/ul/li/a[@cursor='default']")
	@CacheLookup
		WebElement breadcrumb;
	
	public void enterTheHome() {
		driver.get("https://www.fravega.com/");
	}
	
	public void searchHeladera(String fridge) {
		sleep(2);
		searchBox.clear();
		searchBox.sendKeys(fridge);
		searchBox.submit();
		sleep(5);
	}
	
	public void filterByHeladerasConFreezer() {
		filterFridge.click();
		sleep(5);
		scrollTo(filterFridgeWith);
		sleep(5);
		filterFridgeWith.click();
		sleep(5);
	}
	
	public void filterBySamsung() {
		filterBrand.click();
		sleep(5);
	}
	
	public boolean saveGridOfResults() {
		int count = Integer.parseInt(result.getText());
//		boolean breadcrumbContain = breadcrumb.getText().contains("Heladera con Freezer");
		boolean contain = true;
		for(WebElement element : listTitle) {
			System.out.println(element.getText());
			contain = contain && element.getText().contains("Samsung");
		}		
		System.out.println(count + " = " + listTitle.size());
		sleep(5);
		return count == listTitle.size() && contain;
	}	
	
	private void scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	private static void sleep(int seconds) {
		try {Thread.sleep(seconds * 1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
}
