package com.jutils.stream;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

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

	@Test
	public void testCollectAdvance() {
		List<Person> persons =
			Arrays.asList(
				new Person("Max", 18),
				new Person("Peter", 23),
				new Person("Pamela", 23),
				new Person("David", 12));

		Supplier<Stream<Person>> supplier = () -> persons.stream();

		Set<Person> nameWithP = supplier.get()
			.filter(p -> p.name.startsWith("P"))
			.collect(Collectors.toSet());
		System.out.println(nameWithP);  // [Pamela, Peter]

		Map<Integer, List<Person>> groupByAge = supplier.get()
			.collect(Collectors.groupingBy(p -> p.age));
		System.out.println(groupByAge);

		Double averageAge = supplier.get()
			.collect(Collectors.averagingInt(p -> p.age));
		System.out.println(averageAge);

		IntSummaryStatistics ageSummary = supplier.get()
			.collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(ageSummary);

		String phrase = supplier.get()
			.filter(p -> p.age >= 18)
			.map(p -> p.name)
			.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println(phrase);

		Map<Integer, String> map = supplier.get()
			.collect(Collectors.toMap(
				p -> p.age,
				p -> p.name,
				(name1, name2) -> name1 + ";" + name2
			));
		System.out.println(map);

		// 如果要实现自己的 collector
		Collector<Person, StringJoiner, String> personStringJoinerStringCollector = Collector.of(
			() -> new StringJoiner(" | ", "[ ", " ]"),          // supplier
			(j, p) -> j.add(p.name.toUpperCase()),  // accumulator
			(j1, j2) -> j1.merge(j2),               // combiner
			StringJoiner::toString                  // finisher
		);
		String personStr = supplier.get().collect(personStringJoinerStringCollector);
		System.out.println(personStr);
	}

	@Test
	public void testFlatMapAdvanced() {
		List<Foo> foos = Lists.newArrayList();

		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));
		foos.forEach(f -> IntStream.range(1, 4)
			.forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		Supplier<Stream<Foo>> supplier = foos::stream;
		supplier.get()
			.flatMap(f -> f.bars.stream())
			.forEach(b -> System.out.println(b.name));

		List<Bar> list = supplier.get()
			.flatMap(f -> f.bars.stream())
			.collect(Collectors.toList());
		System.out.println(list);
	}

	@Test
	public void testReduceAdvanced() {
		List<Person> persons =
			Arrays.asList(
				new Person("Max", 18),
				new Person("Peter", 23),
				new Person("Pamela", 23),
				new Person("David", 12));

		Supplier<Stream<Person>> supplier = persons::stream;
		supplier.get().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
			.ifPresent(System.out::println);

		Person finalPerson = supplier.get()
			.reduce(new Person("", 0), (p1, p2) -> {
				p1.age += p2.age;
				p1.name = p1.name.concat(p2.name);
				return p1;
			});
		System.out.println(finalPerson);

		Integer totalAge = supplier.get()
			.reduce(
				0,
				(sum, p) -> {
					System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
					return sum += p.age;
				},
				(sum1, sum2) -> {
					System.out.format("combiner: sum1=%s; sum2=%s", sum1, sum2);
					return sum1 + sum2;
				}
			);
		System.out.println(totalAge);
	}

	class Person {

		String name;
		int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
		}
	}

	class Foo {

		String name;
		List<Bar> bars = new ArrayList<>();

		Foo(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Foo{" +
				"name='" + name + '\'' +
				", bars=" + bars +
				'}';
		}
	}

	class Bar {

		String name;

		Bar(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Bar{" +
				"name='" + name + '\'' +
				'}';
		}
	}


}
