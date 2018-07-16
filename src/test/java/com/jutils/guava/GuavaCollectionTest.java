package com.jutils.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by einverne on 7/15/18.
 */
public class GuavaCollectionTest {

    @Test
    public void testMultiset() {
        String testStr = "a b c d a c de";
        HashMultiset<String> wordsCnt = HashMultiset.create();
        wordsCnt.addAll(Splitter.on(" ").splitToList(testStr));

        String fiveEle = "e";
        if (!wordsCnt.contains(fiveEle)) {
            wordsCnt.add(fiveEle, 3);
        }
        wordsCnt.remove(fiveEle, 1);
        wordsCnt.setCount(fiveEle, 2, 10);
        System.out.println("-------------elementSet()-----------------");
        for (String ele : wordsCnt.elementSet()) {
            System.out.println("element:" + ele + " count: " + wordsCnt.count(ele));
        }

        System.out.println("-------------entrySet()-----------------");
        for (Multiset.Entry<String> entry : wordsCnt.entrySet()) {
            System.out.println("element: " + entry.getElement() + " count: " + entry.getCount());
        }

        Iterator<String> iter = wordsCnt.iterator();
        while (iter.hasNext()) {
            System.out.println("element: " + iter.next());
        }
        // same as the following
        for (String aWordsCnt : wordsCnt) {
            System.out.println("element: " + aWordsCnt);
        }
    }

    @Test
    public void testMultimap() {
        ListMultimap<String, Integer> treeListMultimap = MultimapBuilder.treeKeys().arrayListValues().build();

        treeListMultimap.put("No1", 1);
    }

    @Test
    public void testTable() {
        HashBasedTable<String, Integer, String> table = HashBasedTable.create();
        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                table.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }

        System.out.println(table);
        System.out.println(table.row("A"));
        System.out.println(table.column(1));

        for (String row : table.rowMap().keySet()) {
            System.out.println("row: " + row);
            Map<Integer, String> curRow = table.rowMap().get(row);
            for (Integer i : curRow.keySet()) {
                System.out.print("column: " + i + " value: " + curRow.get(i) + " ");
            }
            System.out.print("\n");
        }

        Set<String> strings = table.rowKeySet();
        for (String r : strings) {
            System.out.println("rows: " + r);
        }
    }
}
