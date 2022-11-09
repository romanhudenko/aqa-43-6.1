package ru.netology.web.data;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String id;
        String number;
    }

    public static CardInfo getBankCard(int index) {
        List<CardInfo> cards = new ArrayList<>();
        cards.add(new CardInfo("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001"));
        cards.add(new CardInfo("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002"));
        return cards.get(index);
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo("", "5559 0000 0000 0003");
    }
}
