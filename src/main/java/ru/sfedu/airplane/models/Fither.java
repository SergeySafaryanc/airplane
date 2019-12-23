package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Fither")
public class Fither extends Military {

	@CsvBindByPosition(position = 5)
	public int numRocket;
	@CsvBindByPosition(position = 6)
	public String typeRocket;
	@CsvBindByPosition(position = 7)
	public int generation;

	public Fither () { };
/**
 * Set the value of numRocket
 * @param newVar the new value of numRocket
 */
	@Element(name = "NumRocket")
	public void setNumRocket (int newVar) {
	numRocket = newVar;
	}

/**
 * Get the value of numRocket
 * @return the value of numRocket
 */
	@Element(name = "NumRocket")
	public int getNumRocket () {
	return numRocket;
	}

/**
 * Set the value of typeRocket
 * @param newVar the new value of typeRocket
 */
	@Element(name = "TypeRocket")
	public void setTypeRocket (String newVar) {
	typeRocket = newVar;
	}

/**
 * Get the value of typeRocket
 * @return the value of typeRocket
 */
	@Element(name = "TypeRocket")
	public String getTypeRocket () {
	return typeRocket;
	}

/**
 * Set the value of generation
 * @param newVar the new value of generation
 */
	@Element(name = "Generation")
	public void setGeneration (int newVar) {
	generation = newVar;
	}

/**
 * Get the value of generation
 * @return the value of generation
 */
	@Element(name = "Generation")
	public int getGeneration () {
	return generation;
	}

	@Override
	public String toString() {
		return "Fither{" +
				"numRocket=" + numRocket +
				", typeRocket='" + typeRocket + '\'' +
				", generation=" + generation +
				", country='" + country + '\'' +
				", id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
