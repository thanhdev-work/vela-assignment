package com.example.velaassignment.exception;

import org.omg.CORBA.UNKNOWN;

public enum ExceptionEnum {
    PRODUCT_NOT_FOUND(40014, "Product not found"),
    PRODUCT_NOT_ITEM_DELETE(40015, "Product not item delete"),

    UNKNOWN(50000, "Unknown");

    private final int code;
    private final String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static ExceptionEnum of(String enumName) {
        for (ExceptionEnum e : ExceptionEnum.values()) {
            if (e.name().equals(enumName)) {
                return e;
            }
        }
        return UNKNOWN;
    }

}
