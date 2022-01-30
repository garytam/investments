package com.gtc.investments.aop;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalHolder {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void setMap(Map<String, Object> map) {
        threadLocal.set(map);
    }

    public static Map<String, Object> getMap() {
        if(threadLocal.get() == null) {
            threadLocal.set(new HashMap<>());
        }
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();;
    }


}