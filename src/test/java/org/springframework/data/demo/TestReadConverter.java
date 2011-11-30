package org.springframework.data.demo;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

public class TestReadConverter  implements Converter<DBObject, BigDecimal> {

	public BigDecimal convert(DBObject source) {
		BigDecimal r = new BigDecimal((String) source.get("value"));
		System.out.println("*** " + r);
		return r;
	}
}