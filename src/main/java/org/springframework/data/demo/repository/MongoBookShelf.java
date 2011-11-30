package org.springframework.data.demo.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.domain.Author;
import org.springframework.data.demo.domain.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoBookShelf implements BookShelf {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void add(Book book) {
		lookUpAuthor(book);
		mongoTemplate.insert(book);
	}

	@Override
	public void save(Book book) {
		lookUpAuthor(book);
		mongoTemplate.save(book);
	}

	@Override
	public Book find(String isbn) {
		Query query = new Query(Criteria.where("isbn").is(isbn));
		return mongoTemplate.findOne(query, Book.class);
	}

	@Override
	public void remove(String isbn) {
		Query query = new Query(Criteria.where("isbn").is(isbn));
		mongoTemplate.remove(query, Book.class);
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = mongoTemplate.findAll(Book.class); 
		return Collections.unmodifiableList(books);
	}

	@Override
	public List<Book> findByCategoriesOrYear(Set<String> categories, String year) {
		String[] categoriesToMatch;
		if (categories == null) {
			categoriesToMatch = new String[] {};
		}
		else {
			categoriesToMatch = categories.toArray(new String[categories.size()]);
		}
		Date startDate = null;
		if (year != null && year.length() == 4) {
			DateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
			try {
				startDate = formatter.parse(year + "-01-01");
			} catch (ParseException e) {}
		}
		Criteria searchCriteria = null;
		if (startDate != null) {
			searchCriteria = Criteria.where("published").gte(startDate);
		}
		else {
			searchCriteria = new Criteria();
		}
		if (categoriesToMatch.length > 0) {		
			searchCriteria.and("categories").in((Object[])categoriesToMatch);
		}
		Query query = new Query(searchCriteria);
		return mongoTemplate.find(query, Book.class);
	}

	private void lookUpAuthor(Book book) {
		if (book.getAuthor() != null) {
			Query query = new Query(Criteria.where("name").is(book.getAuthor().getName()));
			Author existing = mongoTemplate.findOne(query ,Author.class);
			if (existing != null) {
				book.setAuthor(existing);
			}
			else {
				mongoTemplate.insert(book.getAuthor());
			}
		}
	}
}
