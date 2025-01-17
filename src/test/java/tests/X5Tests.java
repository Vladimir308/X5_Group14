package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import testdata.Language;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("testX5")
public class X5Tests extends TestBase {

    @EnumSource(Language.class)
    @ParameterizedTest(name = "{index}: Проверка навигации для языка {0}")
    @DisplayName("Соответствие ожидаемому")
    public void checkNavigations(Language language) {
        String url = language == Language.RU ? "https://www.x5.ru/en/" : "https://www.x5.ru/ru/";
        step("Открыть главную страницу X5Group", () -> {
            open(url);
            $$(".header__lang").find(text(language.name())).click();
        });
        for (String description : language.descriptions) {
            step("Кликнуть на раздел: " + description, () -> {
                System.out.println("Текущий язык: " + language.name() + ", раздел: " + description);
                $(byText(description)).shouldBe(visible).click();
            });
            step("Проверка совпадения для: " + description, () -> {
                System.out.println("Текущий URL: " + WebDriverRunner.url());
                $("[aria-label='breadcrumbs']").shouldHave(text(description));
            });
        }
    }

    @Test
    public void CheckNavigation() {
        mainPage.openMainPageEn()
                .acceptCookie()
                .checkInvestor();
    }

    @Test
    public void CheckNavigation1() {
        mainPage.openMainPageEn()
                .acceptCookie()
                .checkPressCentre();
    }

    @Test
    public void Otzyv() {
        mainPage.openMainPageRu()
                .acceptCookie()
                .roleChoose();
        dialogPage.checkDialog();
    }

    @Test
    public void Commercia() {
        mainPage.openMainPageRu()
                .acceptCookie()
                .roleChoose();
        commerciaPage.checkCommercia();
    }

    @Test
    public void Pokupka() {
        mainPage.openMainPageRu()
                .acceptCookie()
                .roleChoose();
        pokupatelPage.checkPokupka();
    }

    @Test
    public void Linia() {
        mainPage.openMainPageRu()
                .acceptCookie()
                .roleChoose();
        liniaPage.checkLinia();
    }

    @Test
    @DisplayName("Переход на страницу, Обращение на Горячую линию по этике")
    public void Sotrudnik() {
        mainPage.openMainPageRu()
                .acceptCookie()
                .roleChoose();
        sotrudnikPage.checkSotrudnik();
    }
}