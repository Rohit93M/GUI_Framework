package com.crm.comcast.orgtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.generic_utilities.BaseClass;

public class CreateOrgTest extends BaseClass {
	
	@Test
	public void createOrg() {
		System.out.println("execute createOrg and verify");
	}
	
	@Test
	public void createOrgWithIndustry() {
		System.out.println("execute createOrgWithIndustry and verify");
	}
	
	
}
