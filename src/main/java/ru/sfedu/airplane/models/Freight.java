package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Freight")
public class Freight extends CivilTransport {

	@CsvBindByPosition(position = 5)
	public int maxWeight;

	public Freight () { };
/**
 * Set the value of maxWeight
 * @param newVar the new value of maxWeight
 */
	@Element(name = "MaxWeight")
	public void setMaxWeight (int newVar) {
	maxWeight = newVar;
	}

/**
 * Get the value of maxWeight
 * @return the value of maxWeight
 */
	@Element(name = "MaxWeight")
	public int getMaxWeight () {
	return maxWeight;
	}

	@Override
	public String toString() {
		return "Freight{" +
				"maxWeight=" + maxWeight +
				", typeFlight='" + typeFlight + '\'' +
				", id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
