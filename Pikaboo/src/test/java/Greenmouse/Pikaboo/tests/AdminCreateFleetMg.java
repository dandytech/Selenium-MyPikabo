package Greenmouse.Pikaboo.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.Pikaboo.testComponents.BaseTest;



public class AdminCreateFleetMg{
	WebDriver driver;
	
	
	
	@Test
	public void adminCreateFleetMg() throws InterruptedException, IOException {
		
		BaseTest invoke = new BaseTest();
		invoke.initializeDriver();
		invoke.adminLauch();
		invoke.adminLogin();
		invoke.adminAddFleetManager();
		
		//invoke.tearDown();

	}

}
