package com.bacon.gamefiles.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class ReflectionUtils {
    @SneakyThrows
    public static Object callMethod(Object holder, String name) {
        Method method = holder.getClass().getMethod(name);
        return method.invoke(holder);
    }
}
