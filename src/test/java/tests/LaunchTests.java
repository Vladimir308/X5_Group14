package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Tag("x5test")
@Feature("X5 web site tests")
@Story("UI tests")
public class LaunchTests extends TestBase {

    @Test
    @DisplayName("Open and verify main page")
    public void checkMainPageOpen() {
        step("Open main page", () -> {
            mainPage.openMainPage();
        });
    }

    @ValueSource(strings = {
            "Компания",
            "Покупателю",
            "Пресс-центр",
            "Карьера"
    })
    @ParameterizedTest(name = "{index}: {0}")
    public void checkNavigation(String value) {
        step("Verify 'Company' section", () -> {
            mainPage.openMainPage();
            $(byText(value)).click();
            $("[aria-label='breadcrumbs']").shouldHave(Condition.text(value));
        });
    }

    @Test
    @DisplayName("inconsistency with the expected")
    public void negativeNavigationTests() {
        mainPage.openMainPage();
        $(byText("Партнёрам")).click();
        $("[aria-label='breadcrumbs']").shouldHave(text("Партнерам"));
    }

    @Test
    @DisplayName("inconsistency with the expected")
    public void negativeNavigationTests1() {
        mainPage.openMainPage();
        $(byText("Акционерам и инвесторам")).click();
        $("[aria-label='breadcrumbs']").shouldHave(text("Инвесторам"));
    }

    @Test
    @DisplayName("Verify address")
    public void verifyAddress() {
        mainPage
                .openMainPage()
                .checkAddress(testData.address);
    }

    @Test
    @DisplayName("Verify phone numbers")
    public void verifyPhoneNumber() {
        mainPage
                .openMainPage()
                .checkPhone(testData.phoneNumber_1)
                .checkPhone(testData.phoneNumber_2);
    }

    @Test
    @DisplayName("Verify X5 VK")
    @Disabled
    public void checkX5Vk() {
        mainPage
                .openMainPage()
                .checkVkPage();
    }

    @Test
    @DisplayName("Verify X5 Dzen")
    public void checkX5Dzen() {
        mainPage
                .openMainPage()
                .checkDzen();
    }
}