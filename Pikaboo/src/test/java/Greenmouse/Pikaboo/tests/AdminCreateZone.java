package Greenmouse.Pikaboo.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.Pikaboo.testComponents.BaseTest;


public class AdminCreateZone {
	
	WebDriver driver;
	
	@Test
	public void adminAddZone() throws InterruptedException, IOException {
		BaseTest invoke = new BaseTest();
		invoke.initializeDriver();
		invoke.adminLauch();
		invoke.adminLogin();
		invoke.adminAddZones();
		
		invoke.tearDown();
	}

}
