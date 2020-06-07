package com.challenge.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.TestNG;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiMain {
	
	final String apiUrl = "https://api.openbrewerydb.org/breweries/";
	final String apiUrlWithAutocomplete = apiUrl + "autocomplete";
	
	public Response getResponseOf(String query) {
		RestAssured.baseURI = apiUrlWithAutocomplete;
		return RestAssured.given().
				param("query", query).when().get("/");
	}
	
	public List<String> getListContain(Response response) {
		return response.jsonPath().getList("name");
	}
	
	public List<String> getListName(Response response, String name) {
		return response.jsonPath().getList("findAll {it.name == '" + name + "'}");
	}
	
	public List<String> getListId(Response response, String name) {
		return response.jsonPath().getList("findAll {it.name == '" + name + "'}.id");
	}
	
	public Response getResponseId(String id) {
		RestAssured.baseURI = apiUrl+id;
		return RestAssured.get();
	}
	
	public String getPathOf(Response response, String path) {
		return response.path(path);
	}
	
	public String getIdOf(List<String> list, String path, String search) {
		String id = "";
		for(String keyId : list) {
			System.out.println("ID: " + keyId);
			Response response = getResponseId(keyId);
			String state = getPathOf(response, path);
			System.out.println("DETAIL: " + response.asString());
			if(state.equals(search)) {
				id = keyId;		
			}
		}
		return id;
	}
	
	@SuppressWarnings("deprecation")
	public void createReportTest() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    	Date date = new Date();
    	String fecha = dateFormat.format(date);
		String output = System.getProperty("user.dir")+"\\Reportes\\Reporte - " + fecha;
		TestNG.getDefault().setOutputDirectory(output);
		Path path = Paths.get(output);
		System.out.println(output);
        if (!Files.exists(path)) {
            try {
				Files.createDirectory(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Se crea directorio de ejecucion");
        } else {
            
            System.out.println("Directorio de ejecucion ya existe");
        }
	}
}
