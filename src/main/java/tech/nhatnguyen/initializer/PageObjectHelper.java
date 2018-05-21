package tech.nhatnguyen.initializer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectHelper {
    public WebDriver driver;

    public PageObjectHelper(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
