package com.startcaft.mvc.controller.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyDateConvert implements Converter<String, Date> {

	public Date convert(String strDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
