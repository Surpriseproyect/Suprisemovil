package com.example.surprise.API;

public class ApiResponse {
    private boolean error;
    private int status;
    private Body body;

    public boolean isError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public Body getBody() {
        return body;
    }

    public static class Body {
        private String token;
        private String rol;

        public String getToken() {
            return token;
        }

        public String getRol() {
            return rol;
        }
    }
}
