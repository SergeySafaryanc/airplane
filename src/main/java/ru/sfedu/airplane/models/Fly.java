package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Fly")
public class Fly {

    @CsvBindByPosition(position = 0)
    private long id;
    @CsvBindByPosition(position = 1)
    private long airId;
    @CsvBindByPosition(position = 2)
    private String airType;
    @CsvBindByPosition(position = 3)
    private long pilotId;
    @CsvBindByPosition(position = 4)
    private int time;


    @Element(name = "id")
    public long getId() {
        return id;
    }

    @Element(name = "id")
    public void setId(long flyId) {
        this.id = flyId;
    }

    @Element(name = "airId")
    public long getAirId() {
        return airId;
    }

    @Element(name = "airId")
    public void setAirId(long airId) {
        this.airId = airId;
    }

    @Element(name = "airType")
    public String getAirType() {
        return airType;
    }

    @Element(name = "airType")
    public void setAirType(String airType) {
        this.airType = airType;
    }

    @Element(name = "pilotId")
    public long getPilotId() {
        return pilotId;
    }

    @Element(name = "pilotId")
    public void setPilotId(long pilotId) {
        this.pilotId = pilotId;
    }

    @Element(name = "time")
    public int getTime() {
        return time;
    }

    @Element(name = "time")
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Fly{" +
                "id=" + id +
                ", airId=" + airId +
                ", airType='" + airType + '\'' +
                ", pilotId=" + pilotId +
                ", time=" + time +
                '}';
    }
}
