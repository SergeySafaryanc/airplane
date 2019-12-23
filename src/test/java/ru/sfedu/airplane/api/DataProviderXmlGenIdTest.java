package ru.sfedu.airplane.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.models.constants.Outcomes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class DataProviderXmlGenIdTest {

    DataProviderXml xml = new DataProviderXml();
    int maxGen = 100;
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
            Assert.assertEquals(xml.addRecord(bombers, Bomber.class).getStatus(), Outcomes.COMPLETE);
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
            Assert.assertEquals(xml.addRecord(fithers, Fither.class).getStatus(), Outcomes.COMPLETE);
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
            Assert.assertEquals(xml.addRecord(agriculturals, Agricultural.class).getStatus(), Outcomes.COMPLETE);
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
            Assert.assertEquals(xml.addRecord(fireAircrafts, FireAircraft.class).getStatus(), Outcomes.COMPLETE);
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
            Assert.assertEquals(xml.addRecord(freights, Freight.class).getStatus(), Outcomes.COMPLETE);
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
            Assert.assertEquals(xml.addRecord(freights, Passenger.class).getStatus(), Outcomes.COMPLETE);
        }
    }

    @Test
    public void getRecordById() {
        //        Bomber
        List<Bomber> bombers = xml.read(Bomber.class);
        bombers.forEach(bomber -> {
            if (bomber.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(bomber.getId(), Bomber.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Fither
        List<Fither> fithers = xml.read(Fither.class);
        fithers.forEach(fither -> {
            if (fither.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(fither.getId(), Fither.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Agricultural
        List<Agricultural> agriculturals = xml.read(Agricultural.class);
        agriculturals.forEach(agricultural -> {
            if (agricultural.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(agricultural.getId(), Agricultural.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        FireAircraft
        List<FireAircraft> fireAircrafts = xml.read(FireAircraft.class);
        fireAircrafts.forEach(fireAircraft -> {
            if (fireAircraft.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(fireAircraft.getId(), FireAircraft.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Freight
        List<Freight> freights = xml.read(Freight.class);
        freights.forEach(freight -> {
            if (freight.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(freight.getId(), Freight.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Passenger
        List<Passenger> passengers = xml.read(Passenger.class);
        passengers.forEach(passenger -> {
            if (passenger.getModel().equals("TEST")) {
                Assert.assertEquals(xml.getRecordById(passenger.getId(), Passenger.class).getStatus(), Outcomes.COMPLETE);
            }
        });
    }

    @After
    public void delRecord() {
//        Bomber
        List<Bomber> bombers = xml.read(Bomber.class);
        bombers.forEach(bomber -> {
            if (bomber.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(bomber.getId(), Bomber.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Fither
        List<Fither> fithers = xml.read(Fither.class);
        fithers.forEach(fither -> {
            if (fither.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(fither.getId(), Fither.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Agricultural
        List<Agricultural> agriculturals = xml.read(Agricultural.class);
        agriculturals.forEach(agricultural -> {
            if (agricultural.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(agricultural.getId(), Agricultural.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        FireAircraft
        List<FireAircraft> fireAircrafts = xml.read(FireAircraft.class);
        fireAircrafts.forEach(fireAircraft -> {
            if (fireAircraft.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(fireAircraft.getId(), FireAircraft.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Freight
        List<Freight> freights = xml.read(Freight.class);
        freights.forEach(freight -> {
            if (freight.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(freight.getId(), Freight.class).getStatus(), Outcomes.COMPLETE);
            }
        });
//        Passenger
        List<Passenger> passengers = xml.read(Passenger.class);
        passengers.forEach(passenger -> {
            if (passenger.getModel().equals("TEST")) {
                Assert.assertEquals(xml.delRecordById(passenger.getId(), Passenger.class).getStatus(), Outcomes.COMPLETE);
            }
        });
    }


    private ArrayList generate() {
        ArrayList<Integer> listId = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        Random random = new Random();
//        int maxId = getMaxId(clazz);
        for (int i=1; i<=maxGen; i++) {
            genId = random.nextInt(maxGen);
            if (hashSet.add(genId)) {
                listId.add(genId);
            }
        }
        return listId;
    }
}
