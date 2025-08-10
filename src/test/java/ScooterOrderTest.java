import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;
import static util.ScooterColor.*;

@RunWith(Parameterized.class)
public class ScooterOrderTest {

    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String when;
    private final String duration;
    private final String color;
    private final String comment;

    public ScooterOrderTest(String firstName, String lastName, String address, String metro, String phoneNumber,
                            String when, String duration, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.when = when;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters1() {
        return new Object[][]{
                {"Марина", "Сергеева", "пр-т Вернадского, 88", "Юго-Западная", "+79888888888", "21.08.2025", "сутки", BLACK, "Привет!"},
                {"Катя", "Алексеева", "ул.Усачева, 62", "Спортивная", "+79889999999", "27.08.2025", "трое суток", GREY, "Позвоните мне, пожалуйста, за полчаса"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testOrderScooterByTopButton()  {
        driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnCookieButton();
        mainPage.clickOnTopOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.sendInfoAboutUser(firstName, lastName, address, metro, phoneNumber);
        orderPage.sendInfoAboutScooter(when, duration, color, comment);
        orderPage.confirmOrder();
        orderPage.checkSuccessMessage();
    }

    @Test
    public void testOrderScooterByBottomButton() {
        driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnCookieButton();
        mainPage.scrollToBottomOrderButton();
        mainPage.clickOnBottomOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.sendInfoAboutUser(firstName, lastName, address, metro, phoneNumber);
        orderPage.sendInfoAboutScooter(when, duration, color, comment);
        orderPage.confirmOrder();
        orderPage.checkSuccessMessage();
    }
}