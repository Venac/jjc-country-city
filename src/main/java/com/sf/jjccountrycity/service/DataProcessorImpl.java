package com.sf.jjccountrycity.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.jjccountrycity.domain.City;
import com.sf.jjccountrycity.domain.Country;

@Service
public class DataProcessorImpl implements DataProcessor {

	private static final String VALID = "VALID";
	private static final String INVALID = "INVALID";
	private static final String S = File.separator;
	private static final String FileToRead = new StringBuilder(System.getProperty("user.dir")).append(S).append("src")
			.append(S).append("main").append(S).append("resources").append(S).append("FileToRead.txt").toString();
	private static final String ValidationResults = new StringBuilder(System.getProperty("user.dir")).append(S)
			.append("src").append(S).append("main").append(S).append("resources").append(S)
			.append("ValidationResults.txt").toString();
	
	@Autowired
	private DataValidator dataValidator;
	
	@Override
	public Collection<Country> generateResults() {
		Collection<String> lines = validateAndWriteDataToFile(readDataFromFile());
		Collection<Country> countries = new LinkedHashSet<>();
		for (String line : lines) {
			line = line.substring(0, line.length() - 1);
			String[] countryCitySplit = line.split(";");
			String cityName = countryCitySplit[1].split("=")[0];
			String cityPopulation = countryCitySplit[1].split("=")[1];
			Country country = new Country(countryCitySplit[0]);
			if (!countries.contains(country)) {
				countries.add(country);
			}
			for (Country added : countries) {
				if (added.equals(country)) {
					added.getCities().add(new City(cityName, cityPopulation));
					break;
				}
			}
		}
		return countries;
	}
	
	/* 
	 * helper methods
	 */

	private Collection<String> readDataFromFile() {
		Collection<String> cities = new LinkedHashSet<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(FileToRead)))) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				System.out.println(line);
				cities.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cities;
	}

	private Collection<String> validateAndWriteDataToFile(Collection<String> lines) {
		Collection<String> results = new LinkedHashSet<>();
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ValidationResults))) {
			String processed;
			for (String line : lines) {
				if (dataValidator.isAlpha(line) && dataValidator.isDigit(line) && dataValidator.isAscii(line)) {
					processed = new StringBuilder().append("{").append(line).append(VALID).append("}\n").toString();
					results.add(line);
				} else {
					processed = new StringBuilder().append("{").append(line).append(INVALID).append("}\n").toString();
				}
				bufferedWriter.write(processed);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

}
