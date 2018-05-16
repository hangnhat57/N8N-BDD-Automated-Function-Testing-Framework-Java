package tech.nhatnguyen.automated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject extends Base{
    public PageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
