package com.shine.java8;

import com.sun.org.apache.xpath.internal.compiler.FunctionTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {
//        Predicate<String> predicate = (String s) -> !s.isEmpty();
//        List<String> list = new ArrayList<>();
//        list.add("");
//        list.add("123");
//
//        list = filter(list, predicate);
//        System.out.println(list.size());
        List<Integer> list = map(Arrays.asList("lam", "in", "java"), (String s) -> s.length());
        list.forEach( i -> {
            System.out.println(i);
        });
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> ans = new ArrayList<>();
        for (T s : list) {
            if (predicate.test(s)) {
                ans.add(s);
            }
        }
        return ans;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> ans = new ArrayList<>();
        for (T s : list) {
            ans.add(function.apply(s));
        }
        return ans;
    }
}


@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}
