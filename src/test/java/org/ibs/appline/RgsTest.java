package org.ibs.appline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class RgsTest extends BaseTests {


    @Test
    void rgsTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        driver.get("https://www.rgs.ru/");

        WebElement menuCompany = driver.findElement(By.xpath("//nav//li//a[text()='Компаниям']"));
        menuCompany.click();
        sleep(1000);
        WebElement menuZdorovie = driver.findElement(By.xpath("//li/span[text()='Здоровье']"));
        menuZdorovie.click();
        sleep(1000);
        WebElement menuDms = driver.findElement(By.xpath("//li/a[text()='Добровольное медицинское страхование']"));
        menuDms.click();
        WebElement titleDms = driver.findElement(By.xpath("//main//h1"));
        Assertions.assertEquals("Добровольное медицинское страхование", titleDms.getText(),"Заголовок страницы ДМС не соответствует ожидаемому");
        WebElement sendApplication = driver.findElement(By.xpath("//a/span[text()='Отправить заявку']"));
        sendApplication.click();
        sleep(1000);
        WebElement userName = driver.findElement(By.xpath("//input[@name='userName']"));
        userName.sendKeys("Иванов Иван Иванович");
        WebElement userTel = driver.findElement(By.xpath("//input[@name='userTel']"));
        userTel.sendKeys("9509502956");
        WebElement userEmail = driver.findElement(By.xpath("//input[@name='userEmail']"));
        userEmail.sendKeys("qweqweqwe");
        WebElement city = driver.findElement(By.xpath("//input[@class='vue-dadata__input']"));
        city.sendKeys("Ижевск");
        WebElement cityDropdown = driver.findElement(By.xpath("//span[contains(., 'г Ижевск')] "));
        cityDropdown.click();
        WebElement checkBox = driver.findElement(By.xpath("//label[@class='checkbox']"));
        checkBox.click();

        Assertions.assertEquals("Иванов Иван Иванович", userName.getText(), "ФИО введено неверно");
        Assertions.assertEquals("9509502956", userTel.getText(),"Номер телефона введен неверно");
        Assertions.assertEquals("qweqweqwe", userEmail.getText(),"Почта введена неверно");
        Assertions.assertEquals("Ижевск", city.getText(),"Город введен неверно" );
        Assertions.assertTrue(checkBox.isSelected(),"Чекбокс согласий не отмечен");


        driver.quit();
    }


    public void sleep (int mls) {
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
