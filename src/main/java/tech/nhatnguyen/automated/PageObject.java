package tech.nhatnguyen.automated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject{
    public  WebDriver driver;
    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
}
