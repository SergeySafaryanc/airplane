package ru.sfedu.airplane.models.constants;

import ru.sfedu.airplane.models.*;

import static ru.sfedu.airplane.models.constants.Constants.*;

public enum ClassesType {

    AGRICULTURAL(AGRICULTURAL_CSV, AGRICULTURAL_XML, Agricultural.class),
    BOMBER(BOMBER_CSV, BOMBER_XML, Bomber.class),
    FIREAIRCRAFT(FIRE_AIRCRAFT_CSV, FIRE_AIRCRAFT_XML, FireAircraft.class),
    FITHER(FITHER_CSV, FITHER_XML, Fither.class),
    FREIGHT(FREIGHT_CSV, FREIGHT_XML, Freight.class),
    PASSENGER(PASSENGER_CSV, PASSENGER_XML, Passenger.class),
    PILOT(PILOT_CSV, PILOT_XML, Pilot.class),
    FLY(FLY_CSV, FLY_XML, Fly.class);

    private final String csvKey;
    private final String xmlKey;
    private final Class clazz;

    ClassesType(String csvKey, String xmlKey, Class clazz) {
        this.csvKey = csvKey;
        this.xmlKey = xmlKey;
        this.clazz = clazz;
    }

    public String getXmlKey() {
        return xmlKey;
    }

    public String getCsvKey() {
        return csvKey;
    }

    public Class getClazz() { return clazz; }
}
