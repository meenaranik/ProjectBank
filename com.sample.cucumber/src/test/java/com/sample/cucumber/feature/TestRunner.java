package com.sample.cucumber.feature;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Feature"
		,glue={"com.stepdefinition"}
		,format= {"pretty"}
		,monochrome=true
		)
 
public class TestRunner {
 
}