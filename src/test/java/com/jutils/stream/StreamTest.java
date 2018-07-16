package com.jutils.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by einverne on 7/15/18.
 */
public class StreamTest {

    @Test
    public void testConcurrentModification() {
        List<String> list = Lists.newArrayList("one", "two");
        list.stream().forEach(s -> {
            /**
             * 抛出 java.util.ConcurrentModificationException
             * 流可以从非线程安全的集合中创建，当流的管道执行的时候，非concurrent数据源不应该被改变
             */
            list.add("three");
        });
    }

    @Test
    public void testConcurent() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(Arrays.asList("one", "two"));
        list.stream().forEach(s -> {
            list.add("three");
            System.out.println(list);
        });
    }

    @Test
    public void testDistinct() {
        List<String> list = Arrays.asList("a", "b", "a", "c").stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testfilter() {
        List<Integer> list = IntStream.range(1, 10)
                .filter(i -> i % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list); // [2, 4, 6, 8]
    }

    @Test
    public void testMap() {
        List<Integer> list = Stream.of("A", "B", "C")
                .map(c -> c.hashCode())
                .collect(Collectors.toList());
        System.out.println(list); // [65, 66, 67]
    }

    @Test
    public void testSorted() {
        List<String> list = Arrays.asList("ac", "ab", "bc", "dc", "ad", "ea").stream()
                .sorted((a, b) -> {
                    if (a.charAt(0) == b.charAt(0)) {
                        return a.substring(1).compareTo(b.substring(1));
                    } else {
                        return b.charAt(0) - a.charAt(0);
                    }
                }).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testMatch() {
        System.out.println(Stream.of(1, 2, 3, 4, 5).allMatch(i -> i > 0)); //true
        System.out.println(Stream.of(1, 2, 3, 4, 5).anyMatch(i -> i > 0)); //true
        System.out.println(Stream.of(1, 2, 3, 4, 5).noneMatch(i -> i > 0)); //false
        System.out.println(Stream.<Integer>empty().allMatch(i -> i > 0)); //true
        System.out.println(Stream.<Integer>empty().anyMatch(i -> i > 0)); //false
        System.out.println(Stream.<Integer>empty().noneMatch(i -> i > 0)); //true
    }

    @Test
    public void testFlatMap() {
        List<List<String>> lists = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        List<String> collect = lists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(collect); // [a, b, c, d]
    }

    @Test
    public void testCount() {
        long count = Stream.of(1, 2, 3, 4).count();
        System.out.println(count);
    }

    @Test
    public void testOrderMatter() {
        System.out.println("---------------------first----------------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println("---------------------second----------------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

    }
}
