/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arm;



import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailSender {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
      System.setProperty("webdriver.chrome.driver","C:/Users/Fayokemi.Adepitan/Downloads/chromedriver_win32/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }
  
  @Test
  public void mailSender() {
    driver.get("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    driver.manage().window().setSize(new Dimension(945, 1132));
    driver.findElement(By.id("identifierId")).sendKeys("armtestfayo21");
    driver.findElement(By.cssSelector(".VfPpkd-LgbsSe-OWXEXe-k8QpJ > .VfPpkd-vQzf8d")).click();
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("P@ssword2021");
    driver.findElement(By.cssSelector(".VfPpkd-LgbsSe-OWXEXe-k8QpJ > .VfPpkd-vQzf8d")).click();
    driver.findElement(By.cssSelector(".gb_Pe")).click();
    driver.switchTo().frame(0);
    vars.put("window_handles", driver.getWindowHandles());
    driver.findElement(By.cssSelector(".u4RcUd > .j1ei8c:nth-child(7) .MrEfLc")).click();
    vars.put("win3499", waitForWindow(2000));
    driver.switchTo().window(vars.get("win3499").toString());
    driver.findElement(By.cssSelector(".T-I-KE")).click();
    driver.findElement(By.name("to")).click();
    driver.findElement(By.name("to")).sendKeys("armtestfayo21@gmail.com");
    
    new Actions(driver).moveToElement(driver.findElement(By.name("subjectbox"))).click().build().perform();
//    driver.findElement(By.name("subjectbox")).click();
    driver.findElement(By.name("subjectbox")).sendKeys("Hi");
    driver.findElement(By.xpath("//td[2]/div[2]/div")).click();
    {
      WebElement element = driver.findElement(By.xpath("//td[2]/div[2]/div"));
      js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = 'Automated Testing'}", element);
    }
    driver.findElement(By.xpath("//div[4]/table/tbody/tr/td/div/div[2]/div")).click();
    driver.findElement(By.cssSelector(".BltHke")).click();
//    driver.findElement(By.cssSelector("#\\3A d0")).click();
    driver.findElement(By.xpath("//td[4]/div[2]")).click();
    assertThat(driver.findElement(By.xpath("//h3/span/span/span")).getText(), is("arm test"));
    assertThat(driver.findElement(By.xpath("//div[2]/div/div[2]/div/h2")).getText(), is("Hi"));
  }
}
