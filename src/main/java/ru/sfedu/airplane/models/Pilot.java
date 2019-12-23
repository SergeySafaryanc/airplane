package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Pilot")
public class Pilot {

	@CsvBindByPosition(position = 0)
	public int pilotId;
	@CsvBindByPosition(position = 1)
	public String firstName;
	@CsvBindByPosition(position = 2)
	public String lastName;
	@CsvBindByPosition(position = 3)
	public String typePilot;
	@CsvBindByPosition(position = 4)
	public boolean admission;

	public Pilot () { };
/**
 * Set the value of pilotId
 * @param newVar the new value of pilotId
 */
	@Element(name = "PilotId")
	public void setPilotId (int newVar) {
	pilotId = newVar;
	}

/**
 * Get the value of pilotId
 * @return the value of pilotId
 */
	@Element(name = "PilotId")
	public int getPilotId () {
	return pilotId;
	}

/**
 * Set the value of firstName
 * @param newVar the new value of firstName
 */
	@Element(name = "FirstName")
	public void setFirstName (String newVar) {
	firstName = newVar;
	}

/**
 * Get the value of firstName
 * @return the value of firstName
 */
	@Element(name = "FirstName")
	public String getFirstName () {
	return firstName;
	}

/**
 * Set the value of lastName
 * @param newVar the new value of lastName
 */
	@Element(name = "LastName")
	public void setLastName (String newVar) {
	lastName = newVar;
	}

/**
 * Get the value of lastName
 * @return the value of lastName
 */
	@Element(name = "LastName")
	public String getLastName () {
	return lastName;
	}

	@Element(name = "TypePilot")
	public void setTypePilot (String newVar) {
		typePilot = newVar;
	}

	@Element(name = "TypePilot")
	public String getTypePilot() {
		return typePilot;
	}

	@Element(name = "Admission")
	public void setAdmission(boolean admission) {
		this.admission = admission;
	}

	@Element(name = "Admission")
	public boolean isAdmission() {
		return admission;
	}

	@Override
	public String toString() {
		return "Pilot{" +
				"pilotId=" + pilotId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", typePilot='" + typePilot + '\'' +
				", admission=" + admission +
				'}';
	}
}
