package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static util.EnvConfig.BASE_URL;

public class MainPage {
    private WebDriver driver;

    private final By cookieButton = By.id("rcc-confirm-button");
    private final By faqSection = By.className("Home_FAQ__3uVm4");
    private final String questionItem = "accordion__heading-%s";
    private final String answerItem = "accordion__panel-%s";
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openMainPage() {
        driver.get(BASE_URL);
    }

    public void clickOnCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void scrollToFAQSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(faqSection));
    }

    public void clickOnQuestion(String index) {
        driver.findElement(By.id(String.format(questionItem, index))).click();
    }

    public void checkAnswerText(String index, String expectedAnswerText) {
        clickOnQuestion(index);
        String actualAnswerText = driver.findElement(By.id(String.format(answerItem, index))).getText();
        Assert.assertEquals("Ошибка в тексте ответа", expectedAnswerText, actualAnswerText);
    }


    public void clickOnTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void scrollToBottomOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(bottomOrderButton));
    }

    public void clickOnBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }
}