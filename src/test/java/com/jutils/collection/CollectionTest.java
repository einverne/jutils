package com.jutils.collection;

import com.google.common.collect.Lists;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * CollectionTest
 */
public class CollectionTest {

    @Test
    public void testCollections() {
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < 20; i += 2) {
            l1.add(i);
        }

        List<Integer> l2 = new ArrayList<>();
        l2.add(0);
        for (int i = 1; i < 20; i += 2) {
            l2.add(i);
        }
        List<Integer> l3 = CollectionUtils.collate(l1, l2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(l3);

        Collection<Integer> l4 = CollectionUtils.select(l1, new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer integer) {
                return integer > 10;
            }
        });
        System.out.println(l4);

        Collection<Integer> union = CollectionUtils.union(l1, l2);
        System.out.println(union);

        Collection<Integer> intersection = CollectionUtils.intersection(l1, l2);
        System.out.println(intersection);
    }
}
