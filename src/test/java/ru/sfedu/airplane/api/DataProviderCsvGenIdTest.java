package ru.sfedu.airplane.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.models.constants.Outcomes;

import java.util.*;

import static ru.sfedu.airplane.models.constants.Outcomes.*;

public class DataProviderCsvGenIdTest {

    DataProviderCsv csv = new DataProviderCsv();
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
            bomber.setId(Long.parseLong(i.toString()));
            bomber.setModel("TEST");
            bomber.setProducer("TEST");
            bomber.setMaxDistance(1000);
            bomber.setCountry("RUS");
            bomber.setNumBombs(10);
            bomber.setTypeBombs("TEST");
            List<Bomber> bombers = new ArrayList<>();
            bombers.add(bomber);
            System.out.println(bombers.toString());
            Assert.assertEquals(csv.addRecord(bombers, Bomber.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(fithers, Fither.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(agriculturals, Agricultural.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(fireAircrafts, FireAircraft.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(freights, Freight.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(freights, Passenger.class).getStatus(), COMPLETE);
        }
    }

    @Test
    public void getRecordById() {
        //        Bomber
        List<Bomber> bombers = csv.read(Bomber.class);
        bombers.forEach(bomber -> {
            if (bomber.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(bomber.getId(), Bomber.class).getStatus(), COMPLETE);
            }
        });
//        Fither
        List<Fither> fithers = csv.read(Fither.class);
        fithers.forEach(fither -> {
            if (fither.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(fither.getId(), Fither.class).getStatus(), COMPLETE);
            }
        });
//        Agricultural
        List<Agricultural> agriculturals = csv.read(Agricultural.class);
        agriculturals.forEach(agricultural -> {
            if (agricultural.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(agricultural.getId(), Agricultural.class).getStatus(), COMPLETE);
            }
        });
//        FireAircraft
        List<FireAircraft> fireAircrafts = csv.read(FireAircraft.class);
        fireAircrafts.forEach(fireAircraft -> {
            if (fireAircraft.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(fireAircraft.getId(), FireAircraft.class).getStatus(), COMPLETE);
            }
        });
//        Freight
        List<Freight> freights = csv.read(Freight.class);
        freights.forEach(freight -> {
            if (freight.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(freight.getId(), Freight.class).getStatus(), COMPLETE);
            }
        });
//        Passenger
        List<Passenger> passengers = csv.read(Passenger.class);
        passengers.forEach(passenger -> {
            if (passenger.getModel().equals("TEST")) {
                Assert.assertEquals(csv.getRecordById(passenger.getId(), Passenger.class).getStatus(), COMPLETE);
            }
        });
    }

    @After
    public void delRecord() {
//        Bomber
        List<Bomber> bombers = csv.read(Bomber.class);
        bombers.forEach(bomber -> {
            if (bomber.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(bomber.getId(), Bomber.class).getStatus(), COMPLETE);
            }
        });
//        Fither
        List<Fither> fithers = csv.read(Fither.class);
        fithers.forEach(fither -> {
            if (fither.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(fither.getId(), Fither.class).getStatus(), COMPLETE);
            }
        });
//        Agricultural
        List<Agricultural> agriculturals = csv.read(Agricultural.class);
        agriculturals.forEach(agricultural -> {
            if (agricultural.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(agricultural.getId(), Agricultural.class).getStatus(), COMPLETE);
            }
        });
//        FireAircraft
        List<FireAircraft> fireAircrafts = csv.read(FireAircraft.class);
        fireAircrafts.forEach(fireAircraft -> {
            if (fireAircraft.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(fireAircraft.getId(), FireAircraft.class).getStatus(), COMPLETE);
            }
        });
//        Freight
        List<Freight> freights = csv.read(Freight.class);
        freights.forEach(freight -> {
            if (freight.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(freight.getId(), Freight.class).getStatus(), COMPLETE);
            }
        });
//        Passenger
        List<Passenger> passengers = csv.read(Passenger.class);
        passengers.forEach(passenger -> {
            if (passenger.getModel().equals("TEST")) {
                Assert.assertEquals(csv.delRecordById(passenger.getId(), Passenger.class).getStatus(), COMPLETE);
            }
        });
    }

//    private int getMaxId(Class clazz) {
//        int maxId;
//        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())) {
//            case AGRICULTURAL:
//                List<Agricultural> agriculturals = csv.read(Agricultural.class);
//                maxId = agriculturals.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case BOMBER:
//                List<Bomber> bombers = csv.read(Bomber.class);
//                maxId = bombers.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case FIREAIRCRAFT:
//                List<FireAircraft> aircrafts = csv.read(FireAircraft.class);
//                maxId = aircrafts.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case FITHER:
//                List<Fither> fithers = csv.read(Fither.class);
//                maxId = fithers.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case FREIGHT:
//                List<Freight> freights = csv.read(Freight.class);
//                maxId = freights.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case PASSENGER:
//                List<Passenger> passengers = csv.read(Passenger.class);
//                maxId = passengers.stream().mapToInt(Airplane::getId).max().getAsInt();
//                break;
//            case PILOT:
//                List<Pilot> pilots = csv.read(Pilot.class);
//                maxId = pilots.stream().mapToInt(Pilot::getPilotId).max().getAsInt();
//                break;
//            default:
//                maxId = 0;
//        }
//        return maxId;
//    }

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
