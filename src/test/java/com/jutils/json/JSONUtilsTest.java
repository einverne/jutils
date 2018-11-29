package com.jutils.json;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JSONUtilsTest {
	@Data
	@NoArgsConstructor
	private static class Person {


		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		private String name;
		private int age;
	}
	private Person p = new Person("Ein Verne", 20);
	private Person p1 = new Person("Verne", 22);
	private List<Person> pList = Lists.newArrayList(p, p1);
	private String personValue = "{\"name\":\"Ein Verne\",\"age\":20}";
	private String listValue = "[{\"name\":\"Ein Verne\",\"age\":20},{\"name\":\"Verne\",\"age\":22}]";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void readValue() {
		Person person = JSONUtils.readValue(personValue, Person.class);
		Assert.assertEquals(person.getName(), p.getName());
		Assert.assertEquals(person.getAge(), p.getAge());
	}

	@Test
	public void readListValue() {
		List<Person> peopleList = JSONUtils.readListValue(listValue, Person.class);
		Assert.assertNotNull(peopleList);
		Assert.assertEquals(peopleList.size(), 2);
		Assert.assertEquals(peopleList.get(0).getName(), p.getName());
		Assert.assertEquals(peopleList.get(0).getAge(), p.getAge());
	}

	@Test
	public void readValue1() {
	}

	@Test
	public void readValue2() {
	}

	@Test
	public void writeValueAsString() {
		String jsonStr = JSONUtils.writeValueAsString(p);
		System.out.println(jsonStr);
		jsonStr = JSONUtils.writeValueAsString(pList);
		System.out.println(jsonStr);
	}

	@Test
	public void writeValueAsBytes() {
	}
}