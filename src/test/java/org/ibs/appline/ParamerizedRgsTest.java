package org.ibs.appline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ParamerizedRgsTest extends BaseTests {


    @ParameterizedTest(name = "Проверка жителя города = {3}")
    @CsvSource({"Иванов Иван Иванович,9509502956,qweqweqwe,Ижевск",
            "Петров Петр Петрович,9829502957,qweqweqwe@,Москва",
            "Маратов Марат Маратович,93109502958,@qweqweqwe,Казань"})
    void rgsTest(String fio, String tel, String mail, String city) {


        WebElement menuCompany = driver.findElement(By.xpath("//nav//li//a[text()='Компаниям']"));
        menuCompany.click();
        sleep(1000);
        WebElement menuZdorovie = driver.findElement(By.xpath("//li/span[text()='Здоровье']"));
        menuZdorovie.click();
        sleep(1000);
        WebElement menuDms = driver.findElement(By.xpath("//li/a[text()='Добровольное медицинское страхование']"));
        menuDms.click();
        WebElement titleDms = driver.findElement(By.xpath("//main//h1"));
        Assertions.assertEquals("Добровольное медицинское страхование", titleDms.getText(), "Заголовок страницы ДМС не соответствует ожидаемому");
        WebElement sendApplication = driver.findElement(By.xpath("//a/span[text()='Отправить заявку']"));
        sendApplication.click();
        sleep(1000);
        WebElement userName = driver.findElement(By.xpath("//input[@name='userName']"));
        userName.sendKeys(fio);
        WebElement userTel = driver.findElement(By.xpath("//input[@name='userTel']"));
        userTel.sendKeys(tel);
        WebElement userEmail = driver.findElement(By.xpath("//input[@name='userEmail']"));
        userEmail.sendKeys(mail);
        WebElement userCity = driver.findElement(By.xpath("//input[@class='vue-dadata__input']"));
        userCity.sendKeys(city);
        sleep(1000);
        WebElement cityDropdown = driver.findElement(By.xpath("//span[@class='vue-dadata__suggestions-item']"));
        Actions action = new Actions(driver);
        action.scrollByAmount(0, 100).build().perform();
        sleep(1000);
        cityDropdown.click();
        sleep(1000);
        WebElement checkBox = driver.findElement(By.xpath("//label[@class='checkbox']"));
        action.scrollByAmount(0, 100).build().perform();
        action.moveToElement(checkBox, -20, 0).click().build().perform();
        sleep(3000);

        Assertions.assertEquals(fio, userName.getDomProperty("value"), "ФИО введено неверно");
        Assertions.assertEquals("+7 " + "(" + tel.substring(0, 3) + ") " + tel.substring(3, 6) + "-" + tel.substring(6, 10),
                userTel.getDomProperty("value"), "Номер телефона введен неверно");
        //"+7 (950) 950-2956"
        Assertions.assertEquals(mail, userEmail.getDomProperty("value"), "Почта введена неверно");
        Assertions.assertEquals("г " + city, userCity.getDomProperty("value"), "Город введен неверно");
        WebElement checkboxChecked = driver.findElement(By.xpath("//input[@class='input']"));
        Assertions.assertTrue(checkboxChecked.isSelected(), "Чекбокс согласий не отмечен");


        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        Assertions.assertFalse(button.isEnabled(), "Кнопка доступна для нажатия");

        WebElement textError = driver.findElement(By.xpath("//span[@class='input__error text--small']"));
        Assertions.assertEquals("Введите корректный адрес электронной почты", textError.getText(), "Сообщение об ошибке ввода почты неверное");


    }


    public void sleep(int mls) {
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
