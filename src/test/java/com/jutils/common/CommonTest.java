package com.jutils.common;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author einverne
 * @since 2019-12-06
 */
public class CommonTest {

	@Test
	public void testClone() throws CloneNotSupportedException {
		Person bob = Person.builder().age(12).name("Bob").build();

		Book windBook = Book.builder().name("Wind").build();
		Person alex = Person.builder().age(10).name("Alex")
				.cources(Lists.newArrayList("Math", "English"))
				.scores(new int[]{80, 90})
				.friends(Lists.newArrayList(bob)).build();
		alex.addBook(windBook);
		Object alex1 = alex.clone();
		Assert.assertEquals(alex, alex1);
	}

	@Data
	@Builder
	public static class Book {

		private String name;
	}

	@Data
	@Builder
	public static class Person implements Cloneable {

		private int age;
		private String name;
		private List<String> cources;
		private int[] scores;
		private List<Person> friends;
		private Book[] ownBooks = new Book[10];

		public void addBook(Book b) {
			if (ownBooks.length < 9) {
				ownBooks[ownBooks.length - 1] = b;
			}
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

}
