package com.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class Base_Class {

	public static WebDriver driver;//------null 

	public static WebDriver browserLaunch(String value) {
		if(value.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"\\Drive\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(value.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir")+"\\Drive\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();

		return driver;
	}

	public static void get(String url) {
		driver.get(url);
	}

	public static void getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is"+currentUrl);
	}

	public static void getTitle() {
		String title = driver.getTitle();
		System.out.println("Page title is"+title);
	}

	public static void getText(WebElement element) {
		String text = element.getText();
		System.out.println(text);
	}

	public static void getAttributevalue(WebElement element, String value) {
		String attribute = element.getAttribute(value);
		System.out.println(attribute);
	}


	public static void thread(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);

	}

	public static void takesScreenShot(String path) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File g = ts.getScreenshotAs(OutputType.FILE);
		File h = new File(path);
		FileHandler.copy(g, h);
	}


	public static void clear(WebElement element) {
		element.clear();
	}

	public static void clickonElement(WebElement element) {
		element.click();
	}

	public static void inputValueElement(WebElement element,  String value) {
		element.sendKeys(value);
	}

	public static int listSize(List<WebElement> l) {
		int size = l.size();
		return size;
	}

	public static void close() {
		driver.close();
	}

	public static void quit() {
		driver.quit();
	}

	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void simpleAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public static void confirmAlert(String action) {
		if (action.equalsIgnoreCase("accept")) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}else if (action.equalsIgnoreCase("dismiss")) {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}
	}
	public static void promptAlert(String action, String value) {
		if (action.equalsIgnoreCase("accept")) {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(value);
			alert.accept();

		}else if (action.equalsIgnoreCase("dismiss")) {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(value);
			alert.dismiss();
		}
	}


	public static void scrollUpandDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView;", element);
	}

	public static  void actionsclass(String actionName, WebElement element) {
		try {
			Actions a = new Actions(driver);
			if (actionName.equalsIgnoreCase("moveto")) {
				a.moveToElement(element).build().perform();	
			} else if (actionName.equalsIgnoreCase("clickon")) {
				a.click(element).build().perform();
			} else if (actionName.equalsIgnoreCase("doubleClick")) {
				a.contextClick(element).build().perform();
			} else if (actionName.equalsIgnoreCase("click")) {
				a.click(element).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void frameOperations(String frameType, String id , String index, WebElement element) {
		try {
			if (frameType.equalsIgnoreCase("framebyId")) {
				driver.switchTo().frame(id);
			} else if (frameType.equalsIgnoreCase("framebyIndex")) {
				int value = Integer.parseInt(index);
				driver.switchTo().frame(value);
			}else if (frameType.equalsIgnoreCase("framebyElement")) {
				driver.switchTo().frame(element);
			}else if (frameType.equalsIgnoreCase("parentFrame")) {
				driver.switchTo().parentFrame();
			}else if (frameType.equalsIgnoreCase("default")) {
				driver.switchTo().defaultContent();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void dropdown(  WebElement element ,String type,   String value) {
		Select s = new Select(element);
		if (type.equalsIgnoreCase("index")) {
			int index = Integer.parseInt(value);
			s.selectByIndex(index);	
		} else if(type.equalsIgnoreCase("value")) {
			s.selectByValue(value);}
		else if(type.equalsIgnoreCase("text")) {
			s.selectByVisibleText(value);}
	}

	public static void dropdownDeselsct( String type, WebElement element , String value) {
		Select s = new Select(element);
		if (type.equalsIgnoreCase("index")) {
			int index = Integer.parseInt(value);
			s.deselectByIndex(index);	
		} else if(type.equalsIgnoreCase("value")) {
			s.deselectByValue(value);}
		else if(type.equalsIgnoreCase("text")) {
			s.deselectByVisibleText(value);}
	}
	public static void multipledropdown(Select name, int a , int b , int c) {
		List<WebElement> alloptions = name.getOptions();
		int size = alloptions.size();
		for (int i = 0; i < size; i++) {
			if (i==a|| i==b|| i==c) {
				name.selectByIndex(i);
			}
		}
	}
	public static void selected(WebElement element) {
		element.isSelected();
	}
	public static void enabled(WebElement element) {
		element.isEnabled();
	}
	public static void elementVisible(WebElement element) {
		element.isDisplayed();
	}
	public static void getFirstoption(WebElement element) {
		Select s = new Select(element);
		WebElement firstSelectoption = s.getFirstSelectedOption();
		String text = firstSelectoption.getText();
		System.out.println(text);
	}

	public static void getalloptions(WebElement element) {
		Select s = new Select(element);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
		for (WebElement alloptions :allSelectedOptions) {
			String text = alloptions.getText();
			System.out.println(text);	
		}

	}

	public static void checkbox(WebElement element) {
		element.click();
	}
	
	public static void manageWindow() {
		driver.getWindowHandle();
	}
	public static void manageWindows() {
		driver.getWindowHandles();
	}
	public static void switchtoWindows(int index) {
		ArrayList<String> newtab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtab.get(index));
	}


             



}









