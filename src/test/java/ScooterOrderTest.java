import data.ScooterOrderTestData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;

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

    @Parameterized.Parameters(name = "Тестовые даныне: имя {0}, фамилия {1}, адрес {2}, станция метро {3}, номер телефона {4}, дата доставки {5}, срок аренды {6}, цвет самоката {7}, комментарий для курьера {8}")
    public static Object[][] getParameters() {
        return ScooterOrderTestData.ORDER_TEST_DATA;
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