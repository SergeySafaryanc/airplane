package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Passenger")
public class Passenger extends CivilTransport {

	@CsvBindByPosition(position = 6)
	public int numPassenger;
	@CsvBindByPosition(position = 7)
	public String service;

	public Passenger () { };
/**
 * Set the value of numPassenger
 * @param newVar the new value of numPassenger
 */
	@Element(name = "NumPassenger")
	public void setNumPassenger (int newVar) {
	numPassenger = newVar;
	}

/**
 * Get the value of numPassenger
 * @return the value of numPassenger
 */
	@Element(name = "NumPassenger")
	public int getNumPassenger () {
	return numPassenger;
	}

/**
 * Set the value of service
 * @param newVar the new value of service
 */
	@Element(name = "Service")
	public void setService (String newVar) {
	service = newVar;
	}

/**
 * Get the value of service
 * @return the value of service
 */
	@Element(name = "Service")
	public String getService () {
	return service;
	}

	@Override
	public String toString() {
		return "Passenger{" +
				"numPassenger=" + numPassenger +
				", service='" + service + '\'' +
				", typeFlight='" + typeFlight + '\'' +
				", id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
