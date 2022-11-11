package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement confirm = $("[data-test-id=action-transfer]");
    private final SelenideElement cancel = $("[data-test-id=action-cancel]");
    private final SelenideElement error = $("[data-test-id=error-notification]");

    public TransferPage() {
    }

    public void sendMoney(DataHelper.CardInfo fromCard, int moneyAmount) {
        from.setValue(fromCard.getNumber());
        amount.setValue(String.valueOf(moneyAmount));
        confirm.click();
    }

    public SelenideElement getErrorElement() {
        return error;
    }

    public DashboardPage cancel() {
        cancel.click();
        return new DashboardPage();
    }
}