package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static util.EnvConfig.EXPLICITLY_TIMEOUT;
import static util.ScooterColor.BLACK;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By firstNameField = By.xpath(".//div[@class='Order_Form__17u6u']/div[1]/input");
    private final By lastNameField = By.xpath(".//div[@class='Order_Form__17u6u']/div[2]/input");
    private final By addressField = By.xpath(".//div[@class='Order_Form__17u6u']/div[3]/input");
    private final By metroField = By.cssSelector(".select-search__input");
    private final By metroChoice = By.cssSelector(".select-search__select");
    private final By phoneNumberField = By.xpath(".//div[@class='Order_Form__17u6u']/div[5]/input");
    private final By goOnButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By whenField = By.xpath(".//div[@class='react-datepicker__input-container']/input");
    private final By durationDropdown = By.cssSelector(".Dropdown-arrow");
    private final String durationItem = ".//div[@class='Dropdown-menu']//div[text()='%s']";
    private final By colorChoiceBlack = By.id("black");
    private final By colorChoiceGrey = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By makeOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    private final By successMessage = By.xpath(".//div[contains(text(),'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_TIMEOUT));
    }


    public void sendFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void sendLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void sendAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void sendMetro(String metro) {
        driver.findElement(metroField).sendKeys(metro);
        wait.until(ExpectedConditions.elementToBeClickable(metroChoice));
        driver.findElement(metroChoice).click();
    }

    public void sendPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickOnGoOnButton() {
        wait.until(ExpectedConditions.elementToBeClickable(goOnButton));
        driver.findElement(goOnButton).click();
    }

    public void sendInfoAboutUser(String firstName, String lastName, String address, String metro, String phoneNumber) {
        sendFirstName(firstName);
        sendLastName(lastName);
        sendAddress(address);
        sendMetro(metro);
        sendPhoneNumber(phoneNumber);
        clickOnGoOnButton();
    }


    public void sendWhen (String when) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(whenField));
        WebElement element = driver.findElement(whenField);
        element.sendKeys(when);
        element.click();
    }

    public void sendDuration (String duration)  {
        driver.findElement(durationDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(durationItem, duration))));
        driver.findElement(By.xpath(String.format(durationItem, duration))).click();
    }

    public void chooseColorBlack() {
        driver.findElement(colorChoiceBlack).click();
    }

    public void chooseColorGrey() {
        driver.findElement(colorChoiceGrey).click();
    }

    public void chooseColor(String color) {
        if (BLACK.equals(color)) {
            chooseColorBlack();
        } else {
            chooseColorGrey();
        }
    }

    public void sendComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOnMakeOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(makeOrderButton));
        driver.findElement(makeOrderButton).click();
    }

    public void sendInfoAboutScooter(String when, String duration, String color, String comment) {
        sendWhen(when);
        sendDuration(duration);
        chooseColor(color);
        sendComment(comment);
        clickOnMakeOrderButton();
    }


    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }


    public boolean gotSuccessMessage() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void checkSuccessMessage() {
        Assert.assertTrue("Не получено подтверждение успешного оформления заказа", gotSuccessMessage());
    }
}