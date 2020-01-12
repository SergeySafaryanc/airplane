package ru.sfedu.airplane.models;


import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Airplane")
public class Airplane {

	@CsvBindByPosition(position = 0)
	public long id;
	@CsvBindByPosition(position = 1)
	public String model;
	@CsvBindByPosition(position = 2)
	public String producer;
	@CsvBindByPosition(position = 3)
	public int maxDistance;
	
	public Airplane () { };
/**
 * Set the value of id
 * @param newVar the new value of id
 */
	@Element(name = "Id")
	public void setId (long newVar) {
	id = newVar;
	}

/**
 * Get the value of id
 * @return the value of id
 */
	@Element(name = "Id")
	public long getId () {
	return id;
	}

/**
 * Set the value of model
 * @param newVar the new value of model
 */
	@Element(name = "Model")
	public void setModel (String newVar) {
	model = newVar;
	}

/**
 * Get the value of model
 * @return the value of model
 */
	@Element(name = "Model")
	public String getModel () {
	return model;
	}

/**
 * Set the value of producer
 * @param newVar the new value of producer
 */
	@Element(name = "Producer")
	public void setProducer (String newVar) {
	producer = newVar;
	}

/**
 * Get the value of producer
 * @return the value of producer
 */
	@Element(name = "Producer")
	public String getProducer () {
	return producer;
	}

/**
 * Set the value of maxDistance
 * @param newVar the new value of maxDistance
 */
	@Element(name = "MaxDistance")
	public void setMaxDistance (int newVar) {
	maxDistance = newVar;
	}

/**
 * Get the value of maxDistance
 * @return the value of maxDistance
 */
	@Element(name = "MaxDistance")
	public int getMaxDistance () {
	return maxDistance;
	}

	@Override
	public String toString() {
		return "Airplane{" +
				"id=" + id +
				", model='" + model + '\'' +
				", producer='" + producer + '\'' +
				", maxDistance=" + maxDistance +
				'}';
	}
}
