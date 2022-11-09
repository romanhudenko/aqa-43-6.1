package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $(By.name("login"));
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement loginButton = $("[data-test-id=\"action-login\"]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
