import data.FAQTestData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;

    private final String index;
    private final String expectedAnswerText;

    public FAQTest(String index, String expectedAnswerText) {
        this.index = index;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters(name = "Тестовые данные: индекс вопроса {0}, текст ответа {1}")
    public static Object[][] getParameters() {
        return FAQTestData.FAQ_TEST_DATA;
    }

@Rule
public DriverFactory factory = new DriverFactory();

@Test
    public void testAnswerTextAfterClickOnQuestion() {
        driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnCookieButton();
        mainPage.scrollToFAQSection();
        mainPage.checkAnswerText(index, expectedAnswerText);
    }
}