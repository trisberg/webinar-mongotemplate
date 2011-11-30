package org.springframework.data.demo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.domain.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class MongoTemplateTest {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Test
	public void testSomething() {
		System.out.println(mongoTemplate.getDb().getCollectionNames());
	}

	@Test
	public void testSubClasses() {
		JavaBook jb = new JavaBook();
		jb.setTitle("Spring in Action");
		jb.setIsbn("0987609876");
		MongoBook mb = new MongoBook();
		mb.setTitle("Mongo in Action");
		mb.setIsbn("1234512345");
		mongoTemplate.save(jb, "test");
		mongoTemplate.save(mb, "test");
		
		Query q = new Query(Criteria.where("isbn").in("1234512345", "0987609876"));
		
		List<Book> lb = mongoTemplate.find(q, Book.class, "test");
		
		System.out.println("==> " + lb);
		
	}

	@Test
	public void testCustomConverters() {
		
		Book b = new Book();
		b.setIsbn("0987654321");
		b.setPrice(new BigDecimal("22.45"));
		
		mongoTemplate.save(b);
		
		Book b2 = mongoTemplate.findById("0987654321", Book.class);
		
		System.out.println("--> " + b2);
	}

}
