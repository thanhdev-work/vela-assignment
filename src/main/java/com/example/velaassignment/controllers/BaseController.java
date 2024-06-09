package com.example.velaassignment.controllers;

import com.example.velaassignment.payload.res.BaseResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity<?> ok(Object data) {
        return ResponseEntity.ok(new BaseResponse(new BaseResponse.Meta(200, "Success"), data));
    }

    public ResponseEntity<?> badReq(Object data) {
        return ResponseEntity.badRequest().body(new BaseResponse(new BaseResponse.Meta(400, "Bad request"), data));
    }

    public ResponseEntity<?> badReq(int code, Object data) {
        return ResponseEntity.badRequest().body(new BaseResponse(new BaseResponse.Meta(code, "Bad request"), data));
    }

    public ResponseEntity<?> internalErr(String message) {
        return ResponseEntity.status(500).body(new BaseResponse(new BaseResponse.Meta(500, "Internal Error"), message));
    }
}
