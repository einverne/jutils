package com.jutils.jodd;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jutils.base.Address;
import com.jutils.base.Person;
import java.util.Map;
import jodd.json.JsonArray;
import jodd.json.JsonObject;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import org.junit.Test;

/**
 * @since 2020-04-13
 */
public class JoddTest {

	private static final String INPUT = "{\"address\":[{\"city\":\"Sunrise\",\"code\":33323,\"province\":\"FL\",\"street\":\"4035 Rinehart Road\"}],\"age\":20,\"birthDay\":\"2020-01-01\",\"idNum\":10,\"name\":\"EV\",\"phoneNums\":[\"786-326-0000\"]}";

	private Person createPerson() {
		Person person = new Person();
		person.setName("EV");
		person.setBirthDay("2020-01-01");
		person.setIdNum(10);
		person.setAge(20);
		Address addr1 = new Address("FL", "Sunrise", "4035 Rinehart Road", 33323);
		person.setAddressList(Lists.newArrayList(addr1));
		person.setPhoneNums(Lists.newArrayList("786-326-0000"));
		return person;
	}

	@Test
	public void serialTest() {
		//JsonSerializer serializes objects into JSON strings
		JsonSerializer jsonSerializer = new JsonSerializer();
		Map<String, String> map = Maps.newHashMap();
		map.put("name", "Ein Verne");
		map.put("addr", "Earth");
		String serialize = jsonSerializer.serialize(map);
		System.out.println(serialize);

		Person person = createPerson();
		String result = JsonSerializer.create().serialize(person);
		System.out.println(result);

		result = jsonSerializer.deep(true).serialize(person);
		System.out.println(result);

		result = jsonSerializer.setClassMetadataName("class").serialize(person);
		System.out.println(result);

	}

	@Test
	public void parseTest() {
		Person person = JsonParser.create()
				.map("address.values", Address.class)
				.parse(INPUT, Person.class);
		System.out.println(person);
	}

	@Test
	public void parseAsObject() {
		JsonObject entries = JsonParser.create().parseAsJsonObject(INPUT);
		JsonArray address = entries.getJsonArray("address");
		String city = address.getJsonObject(0).getString("city");
		System.out.println(city);
	}
}
