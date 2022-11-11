package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Critical path")
    void transferTest() {
        LoginPage login = new LoginPage();
        VerificationPage verification = login.validLogin(DataHelper.getAuthInfo());
        DashboardPage dashboard = verification.validVerify(DataHelper.getVerificationCode());
        DataHelper.CardInfo card1 = DataHelper.getBankCard(0);
        DataHelper.CardInfo card2 = DataHelper.getBankCard(1);
        int currentBalance1 = dashboard.getCardBalance(card1);
        int currentBalance2 = dashboard.getCardBalance(card2);
        TransferPage transfer = dashboard.startTransfer(0);
        transfer.sendMoney(DataHelper.getBankCard(1), 100);
        int updatedBalance1 = dashboard.getCardBalance(card1);
        int updatedBalance2 = dashboard.getCardBalance(card2);
        Assertions.assertEquals(currentBalance1 + 100, updatedBalance1);
        Assertions.assertEquals(currentBalance2 - 100, updatedBalance2);
    }

    @Test
    @DisplayName("Should get error message about transfer to not existing card")
    void cardNotFoundTest() {
        LoginPage login = new LoginPage();
        VerificationPage verification = login.validLogin(DataHelper.getAuthInfo());
        DashboardPage dashboard = verification.validVerify(DataHelper.getVerificationCode());
        TransferPage transfer = dashboard.startTransfer(0);
        transfer.sendMoney(DataHelper.getInvalidCard(), 100);
        transfer.getErrorElement().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should forbid sending money if it makes negative balance")
    void negativeBalanceTest() {
        LoginPage login = new LoginPage();
        VerificationPage verification = login.validLogin(DataHelper.getAuthInfo());
        DashboardPage dashboard = verification.validVerify(DataHelper.getVerificationCode());
        DataHelper.CardInfo card = DataHelper.getBankCard(1);
        int currentBalance = dashboard.getCardBalance(card);
        TransferPage transfer = dashboard.startTransfer(0);
        transfer.sendMoney(card, currentBalance + 1);
        transfer.getErrorElement().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should refresh page with same card data")
    void refreshButtonTest() {
        LoginPage login = new LoginPage();
        VerificationPage verification = login.validLogin(DataHelper.getAuthInfo());
        DashboardPage dashboard = verification.validVerify(DataHelper.getVerificationCode());
        DataHelper.CardInfo card = DataHelper.getBankCard(1);
        int currentBalance = dashboard.getCardBalance(card);
        dashboard.refresh();
        int updatedBalance = dashboard.getCardBalance(card);
        Assertions.assertEquals(currentBalance, updatedBalance);
    }

    @Test
    @DisplayName("Should return to dashboard")
    void cancelButtonTest() {
        LoginPage login = new LoginPage();
        VerificationPage verification = login.validLogin(DataHelper.getAuthInfo());
        TransferPage transfer = verification.validVerify(DataHelper.getVerificationCode()).startTransfer(0);
        DashboardPage dashboard = transfer.cancel();
        dashboard.getMainElement().shouldBe(Condition.visible);
    }
}