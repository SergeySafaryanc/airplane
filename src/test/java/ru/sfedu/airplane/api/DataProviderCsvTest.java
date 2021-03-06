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
import static ru.sfedu.airplane.models.constants.Outcomes.*;

/**
 * The type Data api csv test.
 */
public class DataProviderCsvTest {

    /**
     * The Csv.
     */
    IDataProvider csv = new DataProviderCsv();

    /**
     * Init data source.
     */
    @Test
    public void initDataSource() {
    }

    /**
     * Add record.
     */
    @Before
    public void addRecord() {
        for (int i=1; i<=10; i++) {
            Pilot pilot = new Pilot();
            pilot.setId(i);
            pilot.setFirstName(firstName[i-1]);
            pilot.setLastName(lastName[i-1]);
            pilot.setTypePilot(type[i-1]);
            pilot.setAdmission(true);
            List<Pilot> pilots = new ArrayList<>();
            pilots.add(pilot);
            Assert.assertEquals(csv.addRecord(pilots, Pilot.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(bombers, Bomber.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(fithers, Fither.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(agriculturals, Agricultural.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(fireAircrafts, FireAircraft.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(freights, Freight.class).getStatus(), COMPLETE);
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
            Assert.assertEquals(csv.addRecord(freights, Passenger.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=10; i++) {
            Fly fly = new Fly();
            fly.setId(i);
            fly.setTime(timeFly[i-1]);
            fly.setPilotId(pilotId[i-1]);
            fly.setAirType(typePilot[i-1]);
            fly.setAirId(airId[i-1]);
            List<Fly> flies = new ArrayList<>();
            flies.add(fly);
            Assert.assertEquals(csv.addRecord(flies, Fly.class).getStatus(), COMPLETE);
        }
    }

    /**
     * Gets record by id.
     */
    @Test
    public void getRecordById() {
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(csv.getRecordById(i, Fly.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Fly.class).getAnswer());
        }
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(csv.getRecordById(i, Pilot.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Pilot.class).getAnswer());
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(csv.getRecordById(i, Bomber.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Bomber.class).getAnswer());
        }
        for (int i=1; i<=7; i++) {
            Assert.assertEquals(csv.getRecordById(i, Fither.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Fither.class).getAnswer());
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(csv.getRecordById(i, Agricultural.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Agricultural.class).getAnswer());
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(csv.getRecordById(i, FireAircraft.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, FireAircraft.class).getAnswer());
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(csv.getRecordById(i, Freight.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Freight.class).getAnswer());
        }
        for (int i=1; i<=4; i++) {
            Assert.assertEquals(csv.getRecordById(i, Passenger.class).getStatus(), COMPLETE);
            System.out.println(csv.getRecordById(i, Passenger.class).getAnswer());
        }
    }

    /**
     * Del record by id.
     */
    @After
    public void delRecordById() {
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(csv.delRecordById(i, Fly.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=10; i++) {
            Assert.assertEquals(csv.delRecordById(i, Pilot.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(csv.delRecordById(i, Bomber.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=7; i++) {
            Assert.assertEquals(csv.delRecordById(i, Fither.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(csv.delRecordById(i, Agricultural.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=2; i++) {
            Assert.assertEquals(csv.delRecordById(i, FireAircraft.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=8; i++) {
            Assert.assertEquals(csv.delRecordById(i, Freight.class).getStatus(), COMPLETE);
        }
        for (int i=1; i<=4; i++) {
            Assert.assertEquals(csv.delRecordById(i, Passenger.class).getStatus(), COMPLETE);
        }
    }

    /**
     * Upd record by id.
     */
    @Test
    public void updRecordById() {

    }

    /**
     * Check pilot.
     */
    @Test
    public void checkPilot() {
        Assert.assertEquals(csv.checkPilot(1, CheckPilot.COUNT).getStatus(), COMPLETE);
        System.out.println(csv.checkPilot(1, CheckPilot.COUNT).getAnswer());
        Assert.assertEquals(csv.checkPilot(1, CheckPilot.LIST).getStatus(), COMPLETE);
        System.out.println(csv.checkPilot(1, CheckPilot.LIST).getAnswer());
        Assert.assertEquals(csv.checkPilot(1, CheckPilot.CHECK).getStatus(), COMPLETE);
        System.out.println(csv.checkPilot(1, CheckPilot.CHECK).getAnswer());
    }

    /**
     * Check air.
     */
    @Test
    public void checkAir() {
        Assert.assertEquals(csv.checkAir(1, TypePilot.BOMBER, CheckAir.COUNT).getStatus(), COMPLETE);
        System.out.println(csv.checkAir(1, TypePilot.BOMBER, CheckAir.COUNT).getAnswer());
        Assert.assertEquals(csv.checkAir(1, TypePilot.BOMBER, CheckAir.PILOT).getStatus(), COMPLETE);
        System.out.println(csv.checkAir(1, TypePilot.BOMBER, CheckAir.PILOT).getAnswer());
    }
}