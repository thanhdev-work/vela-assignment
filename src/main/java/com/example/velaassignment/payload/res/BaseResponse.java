package com.example.velaassignment.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {
    private Meta meta;
    private Object data;

    @Getter
    @Setter
    public static class Meta {
        private int code;
        private String message;

        public Meta(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}
