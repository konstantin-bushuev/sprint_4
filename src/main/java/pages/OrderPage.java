package pages;

import org.junit.Assert;
import org.openqa.selenium.*;

import static util.ScooterColor.BLACK;

public class OrderPage {
    private WebDriver driver;

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
    }


    public void sendFirstName(String firstName) {
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
        driver.findElement(metroChoice).click();
    }

    public void sendPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickOnGoOnButton() {
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
        WebElement element = driver.findElement(whenField);
        element.sendKeys(when);
        element.click();
    }

    public void sendDuration (String duration)  {
        driver.findElement(durationDropdown).click();
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