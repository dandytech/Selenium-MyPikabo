package Greenmouse.Pikaboo.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.Pikaboo.testComponents.BaseTest;



public class AdminCreateWasteMgTruck {
	
WebDriver driver;
	
	
	
	@Test
	public void adminCreateWasteMg() throws InterruptedException, IOException {
		
		BaseTest invoke = new BaseTest();
		invoke.initializeDriver();
		invoke.adminLauch();
		invoke.adminLogin();
		invoke.adminAddWasteMgTruck();
		
		invoke.tearDown();

	}


}
