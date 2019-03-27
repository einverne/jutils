package com.jutils.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import java.io.IOException;
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

	@Test
	public void testRenameFieldGson() {
		String originStr = "{\"familyName\":\"Ein\",\"age\":20,\"salary\":1000.0}";
		EmployeeGson employee = new Gson().fromJson(originStr, EmployeeGson.class);
		System.out.println(employee);
	}
	@Test
	public void testRenameFieldFastjson() {
		String originStr = "{\"familyName\":\"Ein\",\"age\":20,\"salary\":1000.0}";
		EmployeeFastjson employee = JSON.parseObject(originStr, EmployeeFastjson.class);
		System.out.println(employee);
	}
	@Test
	public void testRenameFieldJackson() throws IOException {
		String originStr = "{\"familyName\":\"Ein\",\"age\":20,\"salary\":1000.0}";
		EmployeeJackson employeeJackson = new ObjectMapper()
				.readValue(originStr, EmployeeJackson.class);
		System.out.println(employeeJackson);
	}
}