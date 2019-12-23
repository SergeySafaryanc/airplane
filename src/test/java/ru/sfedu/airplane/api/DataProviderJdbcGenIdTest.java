package ru.sfedu.airplane.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.airplane.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static ru.sfedu.airplane.models.constants.Outcomes.COMPLETE;

public class DataProviderJdbcGenIdTest {

    DataProviderJdbc jdbc = new DataProviderJdbc();
    int maxGen = 10;
    int genId;

    @Test
    public void initDataSource() {
    }

    @Before
    public void addRecord() {
//        Bomber
        for (Object i: generate()) {
            Bomber bomber = new Bomber();
            bomber.setId(Integer.parseInt(i.toString()));
            bomber.setModel("TEST");
            bomber.setProducer("TEST");
            bomber.setMaxDistance(1000);
            bomber.setCountry("RUS");
            bomber.setNumBombs(10);
            bomber.setTypeBombs("TEST");
            List<Bomber> bombers = new ArrayList<>();
            bombers.add(bomber);
            Assert.assertEquals(jdbc.addRecord(bombers, Bomber.class).getStatus(), COMPLETE);
        }
//        Fither
        for (Object i: generate()) {
            Fither fither = new Fither();
            fither.setId(Integer.parseInt(i.toString()));
            fither.setModel("TEST");
            fither.setProducer("TEST");
            fither.setMaxDistance(100);
            fither.setCountry("RUS");
            fither.setNumRocket(100);
            fither.setTypeRocket("TEST");
            fither.setGeneration(4);
            List<Fither> fithers = new ArrayList<>();
            fithers.add(fither);
            Assert.assertEquals(jdbc.addRecord(fithers, Fither.class).getStatus(), COMPLETE);
        }
//        Agricultural
        for (Object i: generate()) {
            Agricultural agricultural = new Agricultural();
            agricultural.setId(Integer.parseInt(i.toString()));
            agricultural.setModel("TEST");
            agricultural.setProducer("TEST");
            agricultural.setMaxDistance(10);
            agricultural.setMission("agricultural");
            agricultural.setSprayWight(10);
            agricultural.setDisplacement(10);
            List<Agricultural> agriculturals = new ArrayList<>();
            agriculturals.add(agricultural);
            Assert.assertEquals(jdbc.addRecord(agriculturals, Agricultural.class).getStatus(), COMPLETE);
        }
//        FireAircraft
        for (Object i: generate()) {
            FireAircraft aircraft = new FireAircraft();
            aircraft.setId(Integer.parseInt(i.toString()));
            aircraft.setModel("TEST");
            aircraft.setProducer("TEST");
            aircraft.setMaxDistance(100);
            aircraft.setMission("FireAircraft");
            aircraft.setSprayWight(100);
            aircraft.setDisplacement(100);
            List<FireAircraft> fireAircrafts = new ArrayList<>();
            fireAircrafts.add(aircraft);
            Assert.assertEquals(jdbc.addRecord(fireAircrafts, FireAircraft.class).getStatus(), COMPLETE);
        }
//        Freight
        for (Object i: generate()) {
            Freight freight = new Freight();
            freight.setId(Integer.parseInt(i.toString()));
            freight.setModel("TEST");
            freight.setProducer("TEST");
            freight.setMaxDistance(100);
            freight.setTypeFlight("freight");
            freight.setMaxWeight(100);
            List<Freight> freights = new ArrayList<>();
            freights.add(freight);
            Assert.assertEquals(jdbc.addRecord(freights, Freight.class).getStatus(), COMPLETE);
        }
//        Passenger
        for (Object i: generate()) {
            Passenger passenger = new Passenger();
            passenger.setId(Integer.parseInt(i.toString()));
            passenger.setModel("TEST");
            passenger.setProducer("TEST");
            passenger.setMaxDistance(110);
            passenger.setTypeFlight("passenger");
            passenger.setNumPassenger(100);
            passenger.setService("TEST");
            List<Passenger> freights = new ArrayList<>();
            freights.add(passenger);
            Assert.assertEquals(jdbc.addRecord(freights, Passenger.class).getStatus(), COMPLETE);
        }
    }

    @Test
    public void getRecordById() throws SQLException {
//        Bomber
        ResultSet bomberSet = jdbc.select("SELECT id FROM Bomber WHERE Model='TEST'");
        while (bomberSet != null && bomberSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(bomberSet.getInt("id"), Bomber.class).getStatus(), COMPLETE);
        }
//        Fither
        ResultSet fitherSet = jdbc.select("SELECT id FROM Fither WHERE Model='TEST'");
        while (fitherSet != null && fitherSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(fitherSet.getInt("id"), Fither.class).getStatus(), COMPLETE);
        }
//        Agricultural
        ResultSet agrSet = jdbc.select("SELECT id FROM Agricultural WHERE Model='TEST'");
        while (agrSet != null && agrSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(agrSet.getInt("id"), Agricultural.class).getStatus(), COMPLETE);
        }
//        FireAircraft
        ResultSet aircraftSet = jdbc.select("SELECT id FROM FireAircraft WHERE Model='TEST'");
        while (aircraftSet != null && aircraftSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(aircraftSet.getInt("id"), FireAircraft.class).getStatus(), COMPLETE);
        }

//        Freight
        ResultSet freightSet = jdbc.select("SELECT id FROM Freight WHERE Model='TEST'");
        while (freightSet != null && freightSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(freightSet.getInt("id"), Freight.class).getStatus(), COMPLETE);
        }
//        Passenger
        ResultSet passengerSet = jdbc.select("SELECT id FROM Passenger WHERE Model='TEST'");
        while (passengerSet != null && passengerSet.next()) {
            Assert.assertEquals(jdbc.getRecordById(passengerSet.getInt("id"), Passenger.class).getStatus(), COMPLETE);
        }

    }

    @After
    public void delRecordById() throws SQLException {
//        Bomber
        ResultSet bomberSet = jdbc.select("SELECT id FROM Bomber WHERE Model='TEST'");
        while (bomberSet != null && bomberSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(bomberSet.getInt("id"), Bomber.class).getStatus(), COMPLETE);
        }
//        Fither
        ResultSet fitherSet = jdbc.select("SELECT id FROM Fither WHERE Model='TEST'");
        while (fitherSet != null && fitherSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(fitherSet.getInt("id"), Fither.class).getStatus(), COMPLETE);
        }
//        Agricultural
        ResultSet agrSet = jdbc.select("SELECT id FROM Agricultural WHERE Model='TEST'");
        while (agrSet != null && agrSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(agrSet.getInt("id"), Agricultural.class).getStatus(), COMPLETE);
        }
//        FireAircraft
        ResultSet aircraftSet = jdbc.select("SELECT id FROM FireAircraft WHERE Model='TEST'");
        while (aircraftSet != null && aircraftSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(aircraftSet.getInt("id"), FireAircraft.class).getStatus(), COMPLETE);
        }

//        Freight
        ResultSet freightSet = jdbc.select("SELECT id FROM Freight WHERE Model='TEST'");
        while (freightSet != null && freightSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(freightSet.getInt("id"), Freight.class).getStatus(), COMPLETE);
        }
//        Passenger
        ResultSet passengerSet = jdbc.select("SELECT id FROM Passenger WHERE Model='TEST'");
        while (passengerSet != null && passengerSet.next()) {
            Assert.assertEquals(jdbc.delRecordById(passengerSet.getInt("id"), Passenger.class).getStatus(), COMPLETE);
        }

    }

    private ArrayList generate() {
        ArrayList<Integer> listId = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        Random random = new Random();
        for (int i=1; i<=maxGen; i++) {
            genId = random.nextInt(maxGen);
            if (hashSet.add(genId)) {
                listId.add(genId);
            }
        }
        return listId;
    }

}
