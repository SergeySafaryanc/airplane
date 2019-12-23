package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Agricultural")
public class Agricultural extends Special {

	@CsvBindByPosition(position = 5)
	public int displacement;
	@CsvBindByPosition(position = 6)
	public int sprayWight;

	public Agricultural () { };
/**
 * Set the value of displacement
 * @param newVar the new value of displacement
 */
	@Element(name = "displacement")
	public void setDisplacement (int newVar) {
	displacement = newVar;
	}

/**
 * Get the value of displacement
 * @return the value of displacement
 */
	@Element(name = "displacement")
	public int getDisplacement () {
	return displacement;
	}

/**
 * Set the value of sprayWight
 * @param newVar the new value of sprayWight
 */
	@Element(name = "sprayWight")
	public void setSprayWight (int newVar) {
	sprayWight = newVar;
	}

/**
 * Get the value of sprayWight
 * @return the value of sprayWight
 */
	@Element(name = "sprayWight")
	public int getSprayWight () {
	return sprayWight;
	}

	@Override
	public String toString() {
		return "Agricultural{" +
				"displacement=" + displacement +
				", sprayWight=" + sprayWight +
				", mission='" + mission + '\'' +
				", id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
