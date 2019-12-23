package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Military")
public class Military extends Airplane {

	@CsvBindByPosition(position = 4)
	public String country;

	public Military () { };
/**
 * Set the value of country
 * @param newVar the new value of country
 */
	@Element(name = "Country")
	public void setCountry (String newVar) {
	country = newVar;
	}

/**
 * Get the value of country
 * @return the value of country
 */
	@Element(name = "Country")
	public String getCountry () {
	return country;
	}

	@Override
	public String toString() {
		return super.toString() + " Country = " + getCountry();
	}
}
