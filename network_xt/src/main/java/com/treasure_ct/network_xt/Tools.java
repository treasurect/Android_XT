package com.treasure_ct.network_xt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Locale;

public class Tools {
    public static<T> T getInstance(Class<T> type) {
        Object o = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyHandler());
        return (T) o;
    }
    private static class MyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            UrlString annotation = method.getAnnotation(UrlString.class);
            if (annotation != null) {
                String url = String.format(Locale.CHINA, annotation.value(), args);
                Class<?> returnType = method.getReturnType();
                if (returnType.equals(NetworkTask.class)) {
                    ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
                    Type entryType = type.getActualTypeArguments()[0];
                    return new NetworkTask<>(url, (Class) entryType);
                }
            }
            return null;
        }
    }
}
