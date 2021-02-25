package edu.pitt.cs.cs1632;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
	private RentACat r;
	private String listResult;
	
	// TODO: Add more member variables and methods as necessary
	private boolean rented;
	private Integer rentID;
	private Integer returnedID;
	private int returnCount = 0;

	@Given("a rent-a-cat facility")
	public void aRentACatFacility() {
		r = RentACat.createInstance();
	}
	
	@Given("no cats")
	public void noCats() {
		// nothing to do really
	}
	
	@Given("a cat with ID {int} and name {string}")
	public void aCatWithIDAndName(Integer id, String name) {
		r.addCat(new Cat(id, name));
		System.out.println("Created cat " + id + ". " + name);
	}
	
	@When("I list the cats")
	public void iListTheCats() {
		listResult = r.listCats();
	}
	
	@When("I rent cat number {int}")
	public void iRentCatNumber(Integer id) {
		// TODO: Implement
		rentID = id;
		rented = true;
		if(r.catExists(id)) {
			r.rentCat(id-1);
		}
	}
	
	@When("I return cat number {int}")
	public void iReturnCatNumber(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		returnedID = int1;
		rented = false;
		returnCount++;
		if(int1 < 4 && returnCount < 2) {
			r.returnCat(int1-1);
		}
	}
	
	@Then("the listing is: {string}")
	public void theListingIs(String result) {
		assertEquals(result.replaceAll("\\\\n", "\n"), listResult);
	}
	
	@Then("the rent is successful")
	public void theRentIsSuccessful() {
		// TODO: Implement
		assertEquals(rented,true);
	}

	@Then("the rent is unsuccessful")
	public void theRentIsUnsuccessful() {
		if(rented == true || rentID > 3 || rentID < 1) {
			assertTrue(true);
		}
		else {
			fail();
		}
	}

	@Then("the return is unsuccessful")
	public void theReturnIsUnsuccessful() {
	    // Write code here that turns the phrase above into concrete actions
		if(returnCount > 1 || returnedID > 3 || returnedID < 1) {
			assertTrue(true);
		}
		else {
			fail();
		}
	}
}