package com.jutils.notify;

import java.util.ArrayList;
import java.util.List;

public class MyList {

	private List<String> list;

	public MyList() {
		this.list = new ArrayList<>();
	}

	public void add(String var) {
		list.add(var);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyList myList = new MyList();
		new ThreadListA(myList).start();
		new ThreadListB(myList).start();
	}
}
