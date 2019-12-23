package ru.sfedu.airplane.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Fly")
public class Fly {

    @CsvBindByPosition(position = 0)
    private int flyId;
    @CsvBindByPosition(position = 1)
    private int airId;
    @CsvBindByPosition(position = 2)
    private String airType;
    @CsvBindByPosition(position = 3)
    private int pilotId;
    @CsvBindByPosition(position = 4)
    private int time;


    @Element(name = "flyId")
    public int getFlyId() {
        return flyId;
    }

    @Element(name = "flyId")
    public void setFlyId(int flyId) {
        this.flyId = flyId;
    }

    @Element(name = "airId")
    public int getAirId() {
        return airId;
    }

    @Element(name = "airId")
    public void setAirId(int airId) {
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
    public int getPilotId() {
        return pilotId;
    }

    @Element(name = "pilotId")
    public void setPilotId(int pilotId) {
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
                "flyId=" + flyId +
                ", airId=" + airId +
                ", airType='" + airType + '\'' +
                ", pilotId=" + pilotId +
                ", time=" + time +
                '}';
    }
}
