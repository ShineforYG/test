package com.shine.java8.stream;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Project> list = Project.buildData();

        List<Project> ans = list.stream()
                .filter(project -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + " : First : " + project.getName());
                    return project.getStars() > 1000;
                }).filter(project -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + " : Second : " + project.getName());
                    return project.getStars() > 1000;
                }).collect(Collectors.toList());

        long count = list.stream()
                .filter( project -> project.getStars() > 1000)
                .distinct()
                .count();
        System.out.println(count);
//        ans.stream().forEach(project -> System.out.println(project.toString()));
    }
}
