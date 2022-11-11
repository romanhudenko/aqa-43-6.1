package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final ElementsCollection buttons = $$(".list__item div button");

    private final SelenideElement refresh = $("[data-test-id=action-reload]");

    private final SelenideElement main = $("[data-test-id=dashboard]");
    public DashboardPage() {
    }

    public int getCardBalance(DataHelper.CardInfo card) {
        String selector = "[data-test-id=\"" + card.getId() + "\"]";
        var text = $(selector).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        var start = text.indexOf(balanceStart);
        String balanceFinish = " р.";
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage startTransfer(int cardNumber) {
        buttons.get(cardNumber).click();
        return new TransferPage();
    }

    public void refresh() {
        refresh.click();
    }

    public SelenideElement getMainElement() {
        return main;
    }
}