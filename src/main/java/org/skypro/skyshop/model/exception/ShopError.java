package org.skypro.skyshop.model.exception;

public final class ShopError {
    private final int code;
    private final String message;

    public ShopError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Код ошибка: " + code + " Описание: " + message;
    }
}
