package com.challenge.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.challenge.framework.BrowserFactory;
import com.challenge.framework.CaptureScreenShot;
import com.challenge.ui.FravegaFinderPage;

public class TestCaseFrontendClass extends TestBase{

	FravegaFinderPage fravegaPage;
	
	public TestCaseFrontendClass() {
	}
	
	@Test
	public void C01_GenerarClaseConMetodoDeTest() {		
		System.out.println("in C01_GenerarClaseConMetodoDeTest");	
		driver.get("https://www.fravega.com/");		
		fravegaPage = PageFactory.initElements(driver, FravegaFinderPage.class);			
	}
	
	@Test
	public void C02_IngresarALaHome() {
		System.out.println("in C02_IngresarALaHome");
		fravegaPage.enterTheHome();
	}
	
	@Test
	public void C03_BuscarHeladera() {
		System.out.println("in C03_BuscarHeladera");
		fravegaPage.searchHeladera("heladera");
	}
	
	@Test
	public void C04_FiltrarPorHeladerasConFreezer() {
		System.out.println("in C04_FiltrarPorHeladerasConFreezer");
		fravegaPage.filterByHeladerasConFreezer();
	}
	
	@Test
	public void C05_FiltrarPorSamsung() {
		System.out.println("in C05_FiltrarPorSamsung");
		fravegaPage.filterBySamsung();
	}
	
	@Test
	public void C06_GuardarGrillaDeResultados() {
		System.out.println("in C06_GuardarGrillaDeResultados");
		String bookingDetailsFile = System.getProperty("user.dir")+"\\"+"ScreenShots\\FravegaDetails - " + CaptureScreenShot.getDateTimeStamp()+".png";
		CaptureScreenShot.getScreenShot(BrowserFactory.getDriver(), bookingDetailsFile);
		AssertJUnit.assertTrue(fravegaPage.saveGridOfResults());
	}	
}
