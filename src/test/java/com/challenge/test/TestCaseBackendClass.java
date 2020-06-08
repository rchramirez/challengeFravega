package com.challenge.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;
import com.challenge.framework.ApiMain;

import io.restassured.response.Response;

public class TestCaseBackendClass extends ApiMain{

	String query = "lagunitas";
	String filter = "Lagunitas Brewing Co";
	String path = "state";
	String search = "California";
	String idCalifornia = "";
	
	@Test
	public void C01_ObtenerListaCerveceriaQueContengaTextoLagunitas() {		
		List<String> list = getListContain(getResponseOf(query));
		for(String name : list) {
			System.out.println(name);
			AssertJUnit.assertTrue(name.toLowerCase().contains(query));
		}
	}
	
	@Test
	public void C02_ObtenerListaConValorLagunitasBrewingCo() {
		List<String> list = getListName(getResponseOf(query),filter);
		System.out.println(list);
	}
	
	@Test
	public void C03_ObtenerDetalleDeCadaCerveceria() {
		List<String> list = getListId(getResponseOf(query),filter);
		idCalifornia = getIdOf(list, path, search);
		AssertJUnit.assertTrue(!idCalifornia.isEmpty());
	}
	
	@Test
	public void C04_AssertarResultado() {
		System.out.println("ID: " + idCalifornia);
		Response response = getResponseId(idCalifornia);
		AssertJUnit.assertEquals(idCalifornia,"761");
		AssertJUnit.assertEquals(response.path("name"),"Lagunitas Brewing Co");
		AssertJUnit.assertEquals(response.path("street"),"1280 N McDowell Blvd");
		AssertJUnit.assertEquals(response.path("phone"),"7077694495");
	}
	
	@Test
	public void C05_CrearReporteConResultados() {
		createReportTest();
	}
}