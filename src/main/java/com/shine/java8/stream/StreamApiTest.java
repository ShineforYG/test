package com.shine.java8.stream;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamApiTest {
    public static void main(String[] args) {
//        String[] names = new String[]{
//                "123", "456", "789"
//        };
//        List<String> list = Arrays.asList(names);
//        Stream<String> stream_1 = list.stream();
//
//        Stream<String> stream_2 = Arrays.stream(names);
//
//        Stream<String> stream_3 = Stream.of("123", "456", "789");

//        Stream<String> stream = Files.lines(Paths.get("/123.txt"));

//        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

//        Stream.iterate(new Pair<>(0L, 1L),
//                pair -> new Pair<>(pair.getValue(), pair.getKey() + pair.getValue()))
//                .limit(50)
//                .forEach(pair -> System.out.println(pair.getKey()));

//        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 5, 6, 7, 7);
//        stream.filter(integer -> integer != 5).forEach(System.out::println);
//        stream.distinct().forEach(System.out::println);
//        stream.limit(3).forEach(System.out::println);
//        stream.skip(3).forEach(System.out::println);


//        List<Project> list = Project.buildData();
//        Stream<Project> stream = list.stream();
//        stream.map(project -> project.getName()).forEach(System.out::println);

        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        list.stream()
                .map(line->line.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
