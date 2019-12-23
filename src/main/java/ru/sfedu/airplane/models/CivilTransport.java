package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CivilTransport")
public class CivilTransport extends Airplane {
	
	@CsvBindByPosition(position = 5)
	public String typeFlight;
	
	public CivilTransport () { };
/**
 * Set the value of typeFlight
 * @param newVar the new value of typeFlight
 */
	@Element(name = "TypeFlight")
	public void setTypeFlight (String newVar) {
	typeFlight = newVar;
	}

/**
 * Get the value of typeFlight
 * @return the value of typeFlight
 */
	@Element(name = "TypeFlight")
	public String getTypeFlight () {
	return typeFlight;
	}

	@Override
	public String toString() {
		return super.toString() + " TypeFlight = " + getTypeFlight();
	}
}
