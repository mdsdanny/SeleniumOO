package com.seleniumOO.test;

import junit.framework.TestCase;
/**
 * This is the main test class of this Selenium OO Framework. Extends from junit.framework.TestCase class.
 * Classes that extends this one, should navigate to the initial url of your choice by calling
 * yourPage.navigate().to("url").
 * yourPage variable must be a child class of SOOPage Class.
 * Classes that extends this one, should implement the tearDown() Core Selenium method to close the BROWSER.
 * To close the BROWSER just call the close() Core Selenium method.
 *
 * Created by Daniela Sánchez on 10/02/2017
 */
public class SOOTest extends TestCase{

}
