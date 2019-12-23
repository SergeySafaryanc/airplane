package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Special")
public class Special extends Airplane {

	@CsvBindByPosition(position = 4)
	public String mission;
	
	public Special () { };
/**
 * Set the value of mission
 * @param newVar the new value of mission
 */
	@Element(name = "Mission")
	public void setMission (String newVar) {
	mission = newVar;
	}

/**
 * Get the value of mission
 * @return the value of mission
 */
	@Element(name = "Mission")
	public String getMission () {
	return mission;
	}

	@Override
	public String toString() {
		return super.toString() + " Mission = " + getMission();
	}
}
