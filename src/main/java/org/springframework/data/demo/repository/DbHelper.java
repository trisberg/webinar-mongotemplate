package org.springframework.data.demo.repository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.demo.domain.Author;
import org.springframework.data.demo.domain.Book;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Component
public class DbHelper {

	@Autowired
	MongoTemplate mongoTemplate;

	public String getDump(Class<?> entityClass) {
		final StringBuilder mongoData = new StringBuilder();
		mongoTemplate.execute(entityClass, 
			new CollectionCallback<String>() {
				public String doInCollection(DBCollection collection) throws MongoException, DataAccessException {
					for (DBObject dbo : collection.find()) {
						mongoData.append(dbo.toString());
						mongoData.append(" ");
					}
					return null;
				}
			});
		String ret = "";
		if (mongoData.length() > 0) {
			ret = mongoTemplate.getCollectionName(entityClass) + " collection: " + mongoData.toString();
		}
		return ret;
	}
	
	public void clear() {
		if (mongoTemplate.collectionExists(Book.class)) {
			mongoTemplate.dropCollection(Book.class);
		}
		if (mongoTemplate.collectionExists(Author.class)) {
			mongoTemplate.dropCollection(Author.class);
		}
	}
	
	public void populate() {
		saveBook("Spring in Action", "Craig Walls", "1935182358", new BigDecimal("49.99"), "2011-06-29", "Java", "Spring");
		saveBook("MongoDB: The Definitive Guide", "Kristina Chodorow", "1449381561", new BigDecimal("39.99"), "2010-09-24", "Mongo DB");
		saveBook("The Definitive Guide to MongoDB", "Eelco Plugge", "1430230517", new BigDecimal("44.99"), "2010-10-26", "Mongo DB");
		saveBook("Spring Integration in Action", "Mark Fisher", "1935182439", new BigDecimal("49.99"), "2011-11-28", "Java", "Spring");
		saveBook("Professional Java Development with the Spring Framework", "Rod Johnsson", "0764574833", new BigDecimal("39.99"), "2005-07-08", "Java", "Spring");
		saveBook("Programming Ruby 1.9", "Dave Thomas", "1934356085", new BigDecimal("49.95"), "2009-04-28", "Ruby");
	}
	
	private void saveBook(String title, String authorName, String isbn, BigDecimal price, String published, String... categories) {
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPrice(price);
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(published);
		} catch (ParseException e) {}
		book.setPublished(date);
		book.setCategories(new HashSet<String>(Arrays.asList(categories)));
		Author auth = new Author();
		auth.setName(authorName);
		mongoTemplate.insert(auth);
		book.setAuthor(auth);
		mongoTemplate.insert(book);
	}
	
}
