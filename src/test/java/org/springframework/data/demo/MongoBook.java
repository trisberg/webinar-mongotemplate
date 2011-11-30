package org.springframework.data.demo;

import org.springframework.data.demo.domain.Book;

public class MongoBook extends Book {

	@Override
	public String toString() {
		return "MongoBook :: " + super.toString();
	}

}
