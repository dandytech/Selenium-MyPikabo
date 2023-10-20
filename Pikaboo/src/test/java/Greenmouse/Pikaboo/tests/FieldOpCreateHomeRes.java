package Greenmouse.Pikaboo.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.Pikaboo.testComponents.BaseTest;


public class FieldOpCreateHomeRes {
	WebDriver driver;
	
	@Test
	public void fieldOpAddHome() throws InterruptedException, IOException {
	
			BaseTest invoke = new BaseTest();
			invoke.initializeDriver();
			invoke.fieldOpLaunch();
			invoke.fieldOpLogin();
			Thread.sleep(5000);
			invoke.fieldAddHouse();
			
			//invoke.tearDown();
	}

}
