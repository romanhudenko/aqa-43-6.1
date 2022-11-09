package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement confirm = $("[data-test-id=action-transfer]");
    private final SelenideElement cancel = $("[data-test-id=action-cancel]");

    public TransferPage() {
    }

    public void sendMoney(DataHelper.CardInfo fromCard, int moneyAmount) {
        from.setValue(fromCard.getNumber());
        amount.setValue(String.valueOf(moneyAmount));
        confirm.click();
    }

    public void cancel() {
        cancel.click();
    }
}