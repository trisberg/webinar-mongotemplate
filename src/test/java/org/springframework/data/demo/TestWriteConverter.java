package org.springframework.data.demo;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestWriteConverter implements Converter<BigDecimal, DBObject> {

	public DBObject convert(BigDecimal source) {
		DBObject dbo = new BasicDBObject();
		dbo.put("value", source.toString());
		System.out.println("*** " + dbo);
		return dbo;
	}

}