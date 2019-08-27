package com.sf.jjccountrycity.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class DataValidatorImpl implements DataValidator{
	private static final String ALPHA = "(.*[A-Z].*[a-z].*)|(.*[a-z].*[A-Z].*)"; // positive lookahead (?=.*[a-z])(?=.*[A-Z])
	private static final String DIGIT = "(.*\\d.*)"; // positive lookahead (?=.*\\d)
	private static final String ASCII = "^\\p{ASCII}*$";
	
	@Override
	public boolean isAlpha(String text) {
		return Pattern.compile(ALPHA).matcher(text).find();
	}

	@Override
	public boolean isDigit(String text) {
		return Pattern.compile(DIGIT).matcher(text).find();
	}

	@Override
	public boolean isAscii(String text) {
		return Pattern.compile(ASCII).matcher(text).matches();
	}

}
