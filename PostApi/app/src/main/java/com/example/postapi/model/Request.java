package com.example.postapi.model;

public class Request {
    String nonce;
    String mode;

    public Request(String nonce, String mode) {
        this.nonce = nonce;
        this.mode = mode;
    }
}
