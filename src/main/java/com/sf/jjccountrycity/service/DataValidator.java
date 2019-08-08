package com.sf.jjccountrycity.service;

public interface DataValidator {
	public boolean isAlpha(String text);
	public boolean isDigit(String text);
	public boolean isAscii(String text);
}
