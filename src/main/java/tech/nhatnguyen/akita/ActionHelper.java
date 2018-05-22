package tech.nhatnguyen.akita;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import tech.nhatnguyen.nihonsupittsu.Base;

import static tech.nhatnguyen.akita.FunctionHelper.*;
import static tech.nhatnguyen.shiba.StringHelper.convertXpath;


public class ActionHelper extends Base {

    public static void clearAndType(WebElement element, String value) {
        waitFor(element);
        element.clear();
        element.sendKeys(value);
    }

    public static void hover(WebElement element) {
        waitFor(element);
        org.openqa.selenium.interactions.Actions act =
                new org.openqa.selenium.interactions.Actions(driver);
        act.moveToElement(element).build().perform();

    }

    public static void moveToThenClick(WebElement element) {
        waitFor(element);
        Coordinates coordinate = ((Locatable) element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        element.click();

    }

    public static void click(WebElement element) {
        waitFor(element);
        element.click();
    }

    public static void click(String xpath) {
        waitFor(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void typeKey(WebElement element, String keyword) {
        waitFor(element);
        element.sendKeys(keyword);
    }

    public static void selectOption(String xpath, String option) {
        xpath = convertXpath(xpath, option);
        click(xpath);
    }

    public static void alertHandle(String ok_or_cancel) {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        if (ok_or_cancel.toUpperCase().equals("OK") || ok_or_cancel.
                toUpperCase().equals("ACCEPT")) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }
    public void hitEnter(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public static void jsClicker(WebElement element) {
        waitFor(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void leaveInputFieldBlank(WebElement element) {
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.click(element).build().perform();
    }

    public static void moveMouseToElement(WebElement element) {
        waitFor(element);
        org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
        act.moveToElement(element).build().perform();
    }


    public static void switchtoNextTab() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public static void switchToFrame(WebElement iframe) {
        waitFor(iframe);
        driver.switchTo().frame(iframe);
    }

    public static void switchToMainWeb() {
        driver.switchTo().defaultContent();
    }

    public static void switchToFrame(String id) {
        driver.switchTo().frame(id);
    }

}
