package com.example.demo;

public class JwtContextHolder {
    private static final ThreadLocal<String> jwtHolder = new ThreadLocal<>();

    public static void setJwt(String jwt) {
        jwtHolder.set(jwt);
    }

    public static String getJwt() {
        return jwtHolder.get();
    }

    public static void clear() {
        jwtHolder.remove();
    }
}
