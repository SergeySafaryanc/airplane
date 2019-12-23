package ru.sfedu.airplane.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.airplane.models.constants.CheckAir;
import ru.sfedu.airplane.models.constants.CheckPilot;
import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.models.constants.Outcomes;

import java.util.ArrayList;
import java.util.List;

import static ru.sfedu.airplane.api.Fill.*;


public class DataProviderJdbcTest {

    IDataProvider db = new DataProviderJdbc();

    @Test
    public void initDataSource() {
    }

    @Before
    public void addRecord() {
        for (int i=1; i<=10; i++) {
            Pilot pilot = new Pilot();
            pilot.setPilotId(i);
            pilot.setFirstName(firstName[i-1]);
            pilot.setLastName(lastName[i-1]);
            pilot.setTypePilot(type[i-1]);
            pilot.setAdmission(true);
            List<Pilot> pilots = new ArrayList<>();
            pilots.add(pilot);
            Assert.assertEquals(db.addRecord(pilots, Pilot.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Bomber bomber = new Bomber();
            bomber.setId(i);
            bomber.setModel(BombModel[i-1]);
            bomber.setProducer(BombProd[i-1]);
            bomber.setMaxDistance(maxDistanceBomb[i-1]);
            bomber.setCountry("RUS");
            bomber.setNumBombs(numBomb[i-1]);
            bomber.setTypeBombs(typeBomb[i-1]);
            List<Bomber> bombers = new ArrayList<>();
            bombers.add(bomber);
            Assert.assertEquals(db.addRecord(bombers, Bomber.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=7; i++) {
            Fither fither = new Fither();
            fither.setId(i);
            fither.setModel(FitherModel[i-1]);
            fither.setProducer(FitherProd[i-1]);
            fither.setMaxDistance(maxDistanceFither[i-1]);
            fither.setCountry("RUS");
            fither.setNumRocket(numFither[i-1]);
            fither.setTypeRocket(typeFither[i-1]);
            fither.setGeneration(4);
            List<Fither> fithers = new ArrayList<>();
            fithers.add(fither);
            Assert.assertEquals(db.addRecord(fithers, Fither.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            Agricultural agricultural = new Agricultural();
            agricultural.setId(i);
            agricultural.setModel(AgrModel[i-1]);
            agricultural.setProducer(AgrProd[i-1]);
            agricultural.setMaxDistance(maxDistanceAgr[i-1]);
            agricultural.setMission("agricultural");
            agricultural.setSprayWight(spray[i-1]);
            agricultural.setDisplacement(disp[i-1]);
            List<Agricultural> agriculturals = new ArrayList<>();
            agriculturals.add(agricultural);
            Assert.assertEquals(db.addRecord(agriculturals, Agricultural.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            FireAircraft aircraft = new FireAircraft();
            aircraft.setId(i);
            aircraft.setModel(FAModel[i-1]);
            aircraft.setProducer(FAProd[i-1]);
            aircraft.setMaxDistance(maxDistanceAgr[i-1]);
            aircraft.setMission("FireAircraft");
            aircraft.setSprayWight(spray[i-1]);
            aircraft.setDisplacement(disp[i-1]);
            List<FireAircraft> fireAircrafts = new ArrayList<>();
            fireAircrafts.add(aircraft);
            Assert.assertEquals(db.addRecord(fireAircrafts, FireAircraft.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Freight freight = new Freight();
            freight.setId(i);
            freight.setModel(FreightModel[i-1]);
            freight.setProducer(FreightProd[i-1]);
            freight.setMaxDistance(maxDistanceFreight[i-1]);
            freight.setTypeFlight("freight");
            freight.setMaxWeight(maxW[i-1]);
            List<Freight> freights = new ArrayList<>();
            freights.add(freight);
            Assert.assertEquals(db.addRecord(freights, Freight.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=4; i++) {
            Passenger passenger = new Passenger();
            passenger.setId(i);
            passenger.setModel(PasModel[i-1]);
            passenger.setProducer(PasProd[i-1]);
            passenger.setMaxDistance(maxDistancePas[i-1]);
            passenger.setTypeFlight("passenger");
            passenger.setNumPassenger(numPass[i-1]);
            passenger.setService(serv[i-1]);
            List<Passenger> freights = new ArrayList<>();
            freights.add(passenger);
            Assert.assertEquals(db.addRecord(freights, Passenger.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=10; i++) {
            Fly fly = new Fly();
            fly.setFlyId(i);
            fly.setTime(timeFly[i-1]);
            fly.setPilotId(pilotId[i-1]);
            fly.setAirType(typePilot[i-1]);
            fly.setAirId(airId[i-1]);
            List<Fly> flies = new ArrayList<>();
            flies.add(fly);
            Assert.assertEquals(db.addRecord(flies, Fly.class).getStatus(), Outcomes.COMPLETE);
        }
    }

    @Test
    public void getRecordById() {
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(db.getRecordById(i, Fly.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Fly.class).getAnswer());
        }
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(db.getRecordById(i, Pilot.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Pilot.class).getAnswer());
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(db.getRecordById(i, Bomber.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Bomber.class).getAnswer());
        }
        for (int i=1; i<=7; i++) {
            Assert.assertEquals(db.getRecordById(i, Fither.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Fither.class).getAnswer());
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(db.getRecordById(i, Agricultural.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Agricultural.class).getAnswer());
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(db.getRecordById(i, FireAircraft.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, FireAircraft.class).getAnswer());
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(db.getRecordById(i, Freight.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Freight.class).getAnswer());
        }
        for (int i=1; i<=4; i++) {
            Assert.assertEquals(db.getRecordById(i, Passenger.class).getStatus(), Outcomes.COMPLETE);
            System.out.println(db.getRecordById(i, Passenger.class).getAnswer());
        }
    }

    @After
    public void delRecordById() {
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(db.delRecordById(i, Fly.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(db.delRecordById(i, Pilot.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(db.delRecordById(i, Bomber.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=7; i++) {
            Assert.assertEquals(db.delRecordById(i, Fither.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(db.delRecordById(i, Agricultural.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(db.delRecordById(i, FireAircraft.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(db.delRecordById(i, Freight.class).getStatus(), Outcomes.COMPLETE);
        }
        for (int i=1; i<=4; i++) {
            Assert.assertEquals(db.delRecordById(i, Passenger.class).getStatus(), Outcomes.COMPLETE);
        }
    }

    @Test
    public void updRecordById() {

    }

    @Test
    public void checkPilot() {
        Assert.assertEquals(db.checkPilot(1, CheckPilot.COUNT).getStatus(), Outcomes.COMPLETE);
        System.out.println(db.checkPilot(1, CheckPilot.COUNT).getAnswer());
        Assert.assertEquals(db.checkPilot(1, CheckPilot.LIST).getStatus(), Outcomes.COMPLETE);
        System.out.println(db.checkPilot(1, CheckPilot.LIST).getAnswer());
        Assert.assertEquals(db.checkPilot(1, CheckPilot.CHECK).getStatus(), Outcomes.COMPLETE);
        System.out.println(db.checkPilot(1, CheckPilot.CHECK).getAnswer());
    }

    @Test
    public void checkAir() {
        Assert.assertEquals(db.checkAir(1, TypePilot.BOMBER, CheckAir.COUNT).getStatus(), Outcomes.COMPLETE);
        System.out.println(db.checkAir(1, TypePilot.BOMBER, CheckAir.COUNT).getAnswer());
        Assert.assertEquals(db.checkAir(1, TypePilot.BOMBER, CheckAir.PILOT).getStatus(), Outcomes.COMPLETE);
        System.out.println(db.checkAir(1, TypePilot.BOMBER, CheckAir.PILOT).getAnswer());
    }
}