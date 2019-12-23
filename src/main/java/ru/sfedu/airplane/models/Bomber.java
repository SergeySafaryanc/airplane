package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Bomber")
public class Bomber extends Military {

	@CsvBindByPosition(position = 5)
	public int numBombs;
	@CsvBindByPosition(position = 6)
	public String typeBombs;

	public Bomber () { };
/**
 * Set the value of numBombs
 * @param newVar the new value of numBombs
 */
	@Element(name = "NumBombs")
	public void setNumBombs (int newVar) {
	numBombs = newVar;
	}

/**
 * Get the value of numBombs
 * @return the value of numBombs
 */
	@Element(name = "NumBombs")
	public int getNumBombs () {
	return numBombs;
	}

/**
 * Set the value of typeBombs
 * @param newVar the new value of typeBombs
 */
	@Element(name = "TypeBombs")
	public void setTypeBombs (String newVar) {
	typeBombs = newVar;
	}

/**
 * Get the value of typeBombs
 * @return the value of typeBombs
 */
	@Element(name = "TypeBombs")
	public String getTypeBombs () {
	return typeBombs;
	}

	@Override
	public String toString() {
		return "Bomber{" +
				"numBombs=" + numBombs +
				", typeBombs='" + typeBombs + '\'' +
				", country='" + country + '\'' +
				", id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
