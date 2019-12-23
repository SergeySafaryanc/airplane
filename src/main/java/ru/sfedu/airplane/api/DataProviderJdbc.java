package ru.sfedu.airplane.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.airplane.Result;
import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.models.constants.CheckAir;
import ru.sfedu.airplane.models.constants.CheckPilot;
import ru.sfedu.airplane.models.constants.ClassesType;
import ru.sfedu.airplane.models.constants.Outcomes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.sfedu.airplane.models.constants.Constants.*;
import static ru.sfedu.airplane.utils.ConfigurationUtil.getConfigurationEntry;

/**
 * The type Data api jdbc.
 */
@SuppressWarnings("JavaDoc")
public class DataProviderJdbc implements IDataProvider {

    private static Logger log = LogManager.getLogger(DataProviderJdbc.class);
    private Connection connection;

    @Override
    public void initDataSource() {

    }

    /**
     * @param bean
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> Result addRecord(List<T> bean, Class clazz){

        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())){
            case PILOT:
                return addPilot((List<Pilot>) bean);
            case BOMBER:
                return addBomber((List<Bomber>) bean);
            case FIREAIRCRAFT:
                return addFireAircraft((List<FireAircraft>) bean);
            case FITHER:
                return addFither((List<Fither>) bean);
            case FREIGHT:
                return addFreight((List<Freight>) bean);
            case PASSENGER:
                return addPassenger((List<Passenger>) bean);
            case AGRICULTURAL:
                return addAgricultural((List<Agricultural>) bean);
            case FLY:
                return addFly((List<Fly>) bean);
            default:
                return null;
        }
    }

    /**
     * @param id
     * @param clazz
     * @return
     */
    @Override
    public Result getRecordById(int id, Class clazz) {

        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())){
            case PILOT:
                return getPilotById(id);
            case BOMBER:
                return getBomberById(id);
            case FIREAIRCRAFT:
                return getFireAircraftById(id);
            case FITHER:
                return getFitherById(id);
            case FREIGHT:
                return getFreightById(id);
            case PASSENGER:
                return getPassengerById(id);
            case AGRICULTURAL:
                return getAgriculturalById(id);
            case FLY:
                return getFlyById(id);
            default:
                return new Result(Outcomes.FAIL);
        }
    }

    /**
     * @param id
     * @param clazz
     * @return
     */
    @Override
    public Result delRecordById(int id, Class clazz) {
        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())){

            case PILOT:
                return delPilotById(id);
            case BOMBER:
                return delBomberById(id);
            case AGRICULTURAL:
                return delAgriculturalById(id);
            case PASSENGER:
                return delPassengerById(id);
            case FREIGHT:
                return delFreightById(id);
            case FITHER:
                return delFitherById(id);
            case FIREAIRCRAFT:
                return delFireAircraftById(id);
            case FLY:
                return delFlyById(id);
            default:
                return null;
        }
    }

    /**
     * @param bean
     * @param id
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> Result updRecordById(List<T> bean, int id, Class clazz) {
        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())){

            case AGRICULTURAL:
                return updAgriculturalById((List<Agricultural>) bean, id);
            case BOMBER:
                return updBomberById((List<Bomber>) bean, id);
            case FIREAIRCRAFT:
                return updFireAircraftById((List<FireAircraft>) bean, id);
            case FITHER:
                return updFitherById((List<Fither>) bean, id);
            case FREIGHT:
                return updFreightById((List<Freight>) bean, id);
            case PASSENGER:
                return updPassengerById((List<Passenger>) bean, id);
            case PILOT:
                return updPilotById((List<Pilot>) bean, id);
            case FLY:
                return updFlyById((List<Fly>) bean, id);
            default:
                return null;
        }
    }

    /**
     * @param id
     * @param checkPilot
     * @return
     */
    @Override
    public Result checkPilot(int id, CheckPilot checkPilot) {
        try {
            switch (CheckPilot.valueOf(checkPilot.toString().toUpperCase())) {
                case COUNT:
                    return checkPilotCount(id);
                case LIST:
                    return checkPilotListAir(id);
                case CHECK:
                    return checkPilotBool(id);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param id
     * @param type
     * @param checkAir
     * @return
     */
    @Override
    public Result checkAir(int id, TypePilot type, CheckAir checkAir) {
        try {
            switch (CheckAir.valueOf(checkAir.toString().toUpperCase())){
                case COUNT:
                    return checkAirCount(id, type);
                case PILOT:
                    return checkAirListPilot(id, type);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    check methods

    /**
     * @param id
     * @param type
     * @return
     */
    private Result checkAirListPilot(int id, TypePilot type) {
        try {
            ResultSet res = select(String.format(SELECT_PILOT_JOIN_FLY, type, id));

            if (res != null && res.next()) {
                Pilot pilot = new Pilot();
                pilot.setPilotId(res.getInt(PILOT_PILOT_ID));
                pilot.setFirstName(res.getString(PILOT_FIRST_NAME));
                pilot.setLastName(res.getString(PILOT_LAST_NAME));
                pilot.setTypePilot(res.getString(PILOT_TYPE_PILOT));
                pilot.setAdmission(res.getBoolean(PILOT_ADMISSION));
                List<Pilot> pilots = new ArrayList<>();
                pilots.add(pilot);
                return new Result(Outcomes.COMPLETE, String.valueOf(pilots));
            }

            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param id
     * @param type
     * @return
     */
    private Result checkAirCount(int id, TypePilot type) {
        try {
            ResultSet res = select(String.format(SELECT_COUNT_FLY, id, type));

            if (res != null && res.next()) return new Result(Outcomes.COMPLETE, String.valueOf(DEPARTURES + res.getRow()));

        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result checkPilotListAir(int id) {
        String airType = null;
        try {
            ResultSet res = select(String.format(SELECT_PILOT_BY_FLY, id));

            if (res != null && res.next()) airType = res.getString(FLY_AIR_TYPE);

            switch (ClassesType.valueOf(airType.toUpperCase())) {
                case AGRICULTURAL:
                    res = select(String.format(SELECT_AGRICULTURAL_JOIN_FLY, id));
                    List<Agricultural> agriculturals = new ArrayList<>();
                    while (res.next()) {
                        Agricultural agricultural = new Agricultural();
                        agricultural.setId(res.getInt(AGRICULTURAL_ID));
                        agricultural.setModel(res.getString(AGRICULTURAL_MODEL));
                        agricultural.setProducer(res.getString(AGRICULTURAL_PRODUCER));
                        agricultural.setMaxDistance(res.getInt(AGRICULTURAL_MAX_DISTANCE));
                        agricultural.setMission(res.getString(AGRICULTURAL_MAX_DISTANCE));
                        agricultural.setDisplacement(res.getInt(AGRICULTURAL_DISPLACEMENT));
                        agricultural.setSprayWight(res.getInt(AGRICULTURAL_SPRAY_WIGHT));
                        agriculturals.add(agricultural);
                    }
                    return new Result(Outcomes.COMPLETE, agriculturals.toString());
                case BOMBER:
                    res = select(String.format(SELECT_BOMBER_JOIN_FLY, id));
                    List<Bomber> bombers = new ArrayList<>();
                    while (res.next()) {
                        Bomber bomber = new Bomber();
                        bomber.setId(res.getInt(BOMBER_ID));
                        bomber.setModel(res.getString(BOMBER_MODEL));
                        bomber.setProducer(res.getString(BOMBER_PRODUCER));
                        bomber.setMaxDistance(res.getInt(BOMBER_MAX_DISTANCE));
                        bomber.setCountry(res.getString(BOMBER_COUNTRY));
                        bomber.setNumBombs(res.getInt(BOMBER_NUM_BOMBS));
                        bomber.setTypeBombs(res.getString(BOMBER_TYPE_BOMBS));
                        bombers.add(bomber);
                    }
                    return new Result(Outcomes.COMPLETE, bombers.toString());
                case FIREAIRCRAFT:
                    res = select(String.format(SELECT_FIRE_AIRCRAFT_JOIN_FLY, id));
                    List<FireAircraft> fireAircrafts = new ArrayList<>();
                    while (res.next()) {
                        FireAircraft aircraft = new FireAircraft();
                        aircraft.setId(res.getInt(FIRE_AIRCRAFT_ID));
                        aircraft.setModel(res.getString(FIRE_AIRCRAFT_MODEL));
                        aircraft.setProducer(res.getString(FIRE_AIRCRAFT_PRODUCER));
                        aircraft.setMaxDistance(res.getInt(FIRE_AIRCRAFT_MAX_DISTANCE));
                        aircraft.setMission(res.getString(FIRE_AIRCRAFT_MISSION));
                        aircraft.setDisplacement(res.getInt(FIRE_AIRCRAFT_DISPLACEMENT));
                        aircraft.setSprayWight(res.getInt(FIRE_AIRCRAFT_SPRAY_WIGHT));
                        fireAircrafts.add(aircraft);
                    }
                    return new Result(Outcomes.COMPLETE, fireAircrafts.toString());
                case FITHER:
                    res = select(String.format(SELECT_FITHER_JOIN_FLY, id));
                    List<Fither> fithers = new ArrayList<>();
                    while (res.next()) {
                        Fither fither = new Fither();
                        fither.setId(res.getInt(FITHER_ID));
                        fither.setModel(res.getString(FITHER_MODEL));
                        fither.setProducer(res.getString(FITHER_PRODUCER));
                        fither.setMaxDistance(res.getInt(FITHER_MAX_DISTANCE));
                        fither.setCountry(res.getString(FITHER_COUNTRY));
                        fither.setNumRocket(res.getInt(FITHER_NUM_ROCKET));
                        fither.setTypeRocket(res.getString(FITHER_TYPE_ROCKET));
                        fither.setGeneration(res.getInt(FITHER_GENERATION));
                        fithers.add(fither);
                    }
                    return new Result(Outcomes.COMPLETE, fithers.toString());
                case FREIGHT:
                    res = select(String.format(SELECT_FREIGHT_JOIN_FLY, id));
                    List<Freight> freights = new ArrayList<>();
                    while (res.next()) {
                        Freight freight = new Freight();
                        freight.setId(res.getInt(FREIGHT_ID));
                        freight.setModel(res.getString(FREIGHT_MODEL));
                        freight.setProducer(res.getString(FREIGHT_PRODUCER));
                        freight.setMaxDistance(res.getInt(FREIGHT_MAX_DISTANCE));
                        freight.setTypeFlight(res.getString(FREIGHT_TYPE_FLIGHT));
                        freight.setMaxWeight(res.getInt(FREIGHT_MAX_WEIGHT));
                        freights.add(freight);
                    }
                    return new Result(Outcomes.COMPLETE, freights.toString());
                case PASSENGER:
                    res = select(String.format(SELECT_PASSENGER_JOIN_FLY, id));
                    List<Passenger> passengers = new ArrayList<>();
                    while (res.next()) {
                        Passenger passenger = new Passenger();
                        passenger.setId(res.getInt(PASSENGER_ID));
                        passenger.setModel(res.getString(PASSENGER_MODEL));
                        passenger.setProducer(res.getString(PASSENGER_PRODUCER));
                        passenger.setMaxDistance(res.getInt(PASSENGER_MAX_DISTANCE));
                        passenger.setTypeFlight(res.getString(PASSENGER_TYPE_FLIGHT));
                        passenger.setNumPassenger(res.getInt(PASSENGER_NUM_PASSENGER));
                        passenger.setService(res.getString(PASSENGER_SERVICE));
                        passengers.add(passenger);
                    }
                    return new Result(Outcomes.COMPLETE, passengers.toString());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result checkPilotCount(int id) {
        try {
            ResultSet res = select(String.format(SELECT_SUM_TIME_FLY, id));

            if (res != null && res.next()) {
                return new Result(Outcomes.COMPLETE, String.valueOf(CHECK_PILOT_COUNT_ANSWER + res.getInt("tt")));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result checkPilotBool(int id){
        try {
            ResultSet res = select(String.format(SELECT_ALL_PILOT, id));

            if (res != null && res.next()){
                if (res.getBoolean(PILOT_ADMISSION)){
                    return new Result(Outcomes.COMPLETE, CHECK_PILOT_TRUE);
                } else {
                    return new Result(Outcomes.COMPLETE, CHECK_PILOT_FALSE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    add methods

    /**
     * @param bean
     * @return
     */
    private Result addFly(List<Fly> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a ->
                    (getRecordById(a.getFlyId(), Fly.class).getStatus() == Outcomes.FAIL)
                    && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == Outcomes.COMPLETE)
                    && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == Outcomes.COMPLETE)
                    && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == Outcomes.COMPLETE)
            );
            if (exist) {
                boolean d = bean.stream().anyMatch(a->(execute(String.format(INSERT_FLY, a.getFlyId(), a.getAirId(),
                        a.getAirType(), a.getPilotId(), a.getTime())).getStatus() == Outcomes.COMPLETE));
                if (d) {
                    return new Result(Outcomes.COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param bean
     * @return
     */
    private Result addAgricultural(List<Agricultural> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_AGRICULTURAL, a.getId(), a.getModel(), a.getProducer(), a.getMaxDistance(), a.getMission(), a.getDisplacement(), a.getSprayWight())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param bean
     * @return
     */
    private Result addPassenger(List<Passenger> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_PASSENGER, a.getId(), a.getModel(), a.getProducer(), a.getMaxDistance(), a.getTypeFlight(), a.getNumPassenger(), a.getService())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param bean
     * @return
     */
    private Result addFreight(List<Freight> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_FREIGHT, a.getId(), a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getTypeFlight(), a.getMaxWeight())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    /**
     * @param bean
     * @return
     */
    private Result addFither(List<Fither> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_FITHER, a.getId(), a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getCountry(), a.getNumRocket(), a.getTypeRocket(), a.getGeneration())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addFireAircraft(List<FireAircraft> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_FIRE_AIRCRAFT, a.getId(),
                    a.getModel(), a.getProducer(), a.getMaxDistance(), a.getMission(), a.getDisplacement(), a.getSprayWight())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addBomber(List<Bomber> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(INSERT_BOMBER, a.getId(), a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getCountry(), a.getNumBombs(), a.getTypeBombs())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addPilot(List<Pilot> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (Arrays.stream(TypePilot.values()).anyMatch(typePilot -> typePilot.toString().equals(a.getTypePilot())))
                    && (execute(String.format(INSERT_PILOT, a.getPilotId(), a.getFirstName(), a.getLastName(), a.getTypePilot(), a.isAdmission())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    getById

    private Result getTypePilotById(int id, String getType) {
        try {
            ResultSet res = select(String.format(SELECT_COUNT_PILOT, id, getType.toUpperCase()));

            if (res != null && res.next()) {
                int count = res.getRow();

                if (count != 0) {
                    return new Result(Outcomes.COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFlyById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_FLY, id));

            if (res != null && res.next()) {

                Fly fly = new Fly();

                fly.setFlyId(res.getInt(FLY_FLY_ID));
                fly.setAirId(Integer.parseInt(res.getString(FLY_AIR_ID)));
                fly.setAirType(res.getString(FLY_AIR_TYPE));
                fly.setPilotId(Integer.parseInt(res.getString(FLY_PILOT_ID)));
                fly.setTime(Integer.parseInt(res.getString(FLY_TIME)));

                List<Fly> flies = new ArrayList<>();
                flies.add(fly);

                return new Result<Fly>(Outcomes.COMPLETE, String.valueOf(flies));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getPilotTypeById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_PILOT, id));
            if (res != null && res.next()) return new Result(Outcomes.COMPLETE, String.valueOf(res.getString(PILOT_TYPE_PILOT)));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }


    private Result getPilotById(int pilotId) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_PILOT, pilotId));

            if (res != null && res.next()) {

                Pilot pilot = new Pilot();

                pilot.setPilotId(res.getInt(PILOT_PILOT_ID));
                pilot.setFirstName(res.getString(PILOT_FIRST_NAME));
                pilot.setLastName(res.getString(PILOT_LAST_NAME));
                pilot.setTypePilot(res.getString(PILOT_TYPE_PILOT));

                List<Pilot> pilots = new ArrayList<>();
                pilots.add(pilot);

                return new Result<Pilot>(Outcomes.COMPLETE, String.valueOf(pilots));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getAgriculturalById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_AGRICULTURAL, id));

            if (res != null && res.next()) {
                Agricultural agricultural = new Agricultural();

                agricultural.setId(res.getInt(AGRICULTURAL_ID));
                agricultural.setModel(res.getString(AGRICULTURAL_MODEL));
                agricultural.setProducer(res.getString(AGRICULTURAL_PRODUCER));
                agricultural.setMaxDistance(res.getInt(AGRICULTURAL_MAX_DISTANCE));
                agricultural.setMission(res.getString(AGRICULTURAL_MISSION));
                agricultural.setDisplacement(res.getInt(AGRICULTURAL_DISPLACEMENT));
                agricultural.setSprayWight(res.getInt(AGRICULTURAL_SPRAY_WIGHT));

                List<Agricultural> agriculturals = new ArrayList<>();
                agriculturals.add(agricultural);

                return new Result<Agricultural>(Outcomes.COMPLETE, agriculturals.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getPassengerById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_PASSENGER, id));

            if (res != null && res.next()) {
                Passenger passenger = new Passenger();

                passenger.setId(res.getInt(PASSENGER_ID));
                passenger.setModel(res.getString(PASSENGER_MODEL));
                passenger.setProducer(res.getString(PASSENGER_PRODUCER));
                passenger.setMaxDistance(res.getInt(PASSENGER_MAX_DISTANCE));
                passenger.setTypeFlight(res.getString(PASSENGER_TYPE_FLIGHT));
                passenger.setNumPassenger(res.getInt(PASSENGER_NUM_PASSENGER));
                passenger.setService(res.getString(PASSENGER_SERVICE));

                List<Passenger> passengers = new ArrayList<>();
                passengers.add(passenger);

                return new Result<Passenger>(Outcomes.COMPLETE, passengers.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFreightById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_FREIGHT, id));

            if (res != null && res.next()) {
                Freight freight = new Freight();

                freight.setId(res.getInt(FREIGHT_ID));
                freight.setModel(res.getString(FREIGHT_MODEL));
                freight.setProducer(res.getString(FREIGHT_PRODUCER));
                freight.setMaxDistance(res.getInt(FREIGHT_MAX_DISTANCE));
                freight.setTypeFlight(res.getString(FREIGHT_TYPE_FLIGHT));
                freight.setMaxWeight(res.getInt(FREIGHT_MAX_WEIGHT));

                List<Freight> freights = new ArrayList<>();
                freights.add(freight);

                return new Result<Freight>(Outcomes.COMPLETE, freights.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFitherById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_FITHER, id));

            if (res != null && res.next()) {
                Fither fither = new Fither();

                fither.setId(res.getInt(FITHER_ID));
                fither.setModel(res.getString(FITHER_MODEL));
                fither.setProducer(res.getString(FITHER_PRODUCER));
                fither.setMaxDistance(res.getInt(FITHER_MAX_DISTANCE));
                fither.setCountry(res.getString(FITHER_COUNTRY));
                fither.setNumRocket(res.getInt(FITHER_NUM_ROCKET));
                fither.setTypeRocket(res.getString(FITHER_TYPE_ROCKET));
                fither.setGeneration(res.getInt(FITHER_GENERATION));

                List<Fither> fithers = new ArrayList<>();
                fithers.add(fither);

                return new Result<Fither>(Outcomes.COMPLETE,  fithers.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFireAircraftById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_FIRE_AIRCRAFT, id));

            if (res != null && res.next()) {
                FireAircraft fireAircraft = new FireAircraft();

                fireAircraft.setId(res.getInt(FIRE_AIRCRAFT_ID));
                fireAircraft.setModel(res.getString(FIRE_AIRCRAFT_MODEL));
                fireAircraft.setProducer(res.getString(FIRE_AIRCRAFT_PRODUCER));
                fireAircraft.setMaxDistance(res.getInt(FIRE_AIRCRAFT_MAX_DISTANCE));
                fireAircraft.setMission(res.getString(FIRE_AIRCRAFT_MISSION));
                fireAircraft.setDisplacement(res.getInt(FIRE_AIRCRAFT_DISPLACEMENT));
                fireAircraft.setSprayWight(res.getInt(FIRE_AIRCRAFT_SPRAY_WIGHT));

                List<FireAircraft> fireAircrafts = new ArrayList<>();
                fireAircrafts.add(fireAircraft);

                return new Result<FireAircraft>(Outcomes.COMPLETE, fireAircrafts.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getBomberById(int id) {
        try {
            ResultSet res = select(String.format(SELECT_ALL_BOMBER, id));

            if (res != null && res.next()) {
                Bomber bomber = new Bomber();

                bomber.setId(res.getInt(BOMBER_ID));
                bomber.setModel(res.getString(BOMBER_MODEL));
                bomber.setProducer(res.getString(BOMBER_PRODUCER));
                bomber.setMaxDistance(res.getInt(BOMBER_MAX_DISTANCE));
                bomber.setCountry(res.getString(BOMBER_COUNTRY));
                bomber.setNumBombs(res.getInt(BOMBER_NUM_BOMBS));
                bomber.setTypeBombs(res.getString(BOMBER_NUM_BOMBS));

                List<Bomber> bombers = new ArrayList<>();
                bombers.add(bomber);

                return new Result<Bomber>(Outcomes.COMPLETE, bombers.toString());
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    del methods

    private Result delPilotById(int id) {
        try {
            boolean a = execute(String.format(DELETE_PILOT, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_PILOT, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFireAircraftById(int id) {
        try {
            boolean a = execute(String.format(DELETE_FIREAIRCRAFT, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_FIREAIRCRAFT, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFlyById(int id) {
        try {
            boolean a = execute(String.format(DELETE_FLY, id)).getStatus() == Outcomes.COMPLETE;
            if (a) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFitherById(int id) {
        try {
            boolean a = execute(String.format(DELETE_FITHER, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_FITHER, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFreightById(int id) {
        try {
            boolean a = execute(String.format(DELETE_FREIGHT, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_FREIGHT, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delPassengerById(int id) {
        try {
            boolean a = execute(String.format(DELETE_PASSENGER, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_PASSENGER, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delAgriculturalById(int id) {
        try {
            boolean a = execute(String.format(DELETE_AGRICULTURAL, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_AGRICULTURAL, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delBomberById(int id) {
        try {
            boolean a = execute(String.format(DELETE_BOMBER, id)).getStatus() == Outcomes.COMPLETE;
            boolean b = execute(String.format(UPDATE_FLY_AIR_BOMBER, id)).getStatus() == Outcomes.COMPLETE;
            if (a == b) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    updById methods

    private Result updFlyById(List<Fly> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a ->
                    (getRecordById(a.getFlyId(), Fly.class).getStatus() == Outcomes.COMPLETE)
                            && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == Outcomes.COMPLETE)
                            && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == Outcomes.COMPLETE)
                            && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == Outcomes.COMPLETE)
            );
            if (exist) {
                boolean d = bean.stream().anyMatch(a -> (execute(String.format(UPDATE_FLY_BY_ID, a.getAirId(),
                        a.getAirType(), a.getPilotId(), a.getTime(), a.getFlyId())).getStatus() == Outcomes.COMPLETE));
                if (d) {
                    return new Result(Outcomes.COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    private Generic updDependency()

    private Result updPilotById(List<Pilot> bean, int id) {
        String typePilot = "";
        try {
            ResultSet res = select(String.format(SELECT_PILOT, id));

            if (res != null && res.next()) {
                typePilot = res.getString(PILOT_TYPE_PILOT);
            }

            String finalTypePilot = typePilot;
            bean.forEach(b -> {
                if (b.getTypePilot().toUpperCase().equals(finalTypePilot.toUpperCase())) {
                    execute(String.format(UPDATE_PILOT, b.getFirstName(), b.getLastName(), id));
                } else {
                    execute(String.format(UPDATE_PILOT_TYPE, b.getFirstName(), b.getLastName(), b.getTypePilot().toUpperCase(), id));
//                    execute(String.format("UPDATE Pilot SET firstName='%s', lastName='%s', typePilot='%s' WHERE pilotId=%d", b.getFirstName(), b.getLastName(), b.getTypePilot().toUpperCase(), id));
                }
            });
            return new Result(Outcomes.COMPLETE);
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updPassengerById(List<Passenger> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(UPDATE_PASSENGER, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getTypeFlight(), a.getNumPassenger(), a.getService(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFreightById(List<Freight> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(UPDATE_FREIGHT, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getTypeFlight(), a.getMaxWeight(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFitherById(List<Fither> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(UPDATE_FITHER, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getCountry(), a.getNumRocket(), a.getTypeRocket(), a.getGeneration(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFireAircraftById(List<FireAircraft> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (execute(String.format(UPDATE_FIRE_AIRCRAFT, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getMission(), a.getDisplacement(), a.getSprayWight(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updBomberById(List<Bomber> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a ->(execute(String.format(UPDATE_BOMBER, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getCountry(), a.getNumBombs(), a.getTypeBombs(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updAgriculturalById(List<Agricultural> bean, int id) {
        try {
            boolean exist = bean.stream().anyMatch(a ->(execute(String.format(UPDATE_AGRICULTURAL, a.getModel(),
                    a.getProducer(), a.getMaxDistance(), a.getMission(), a.getDisplacement(), a.getSprayWight(), a.getId())).getStatus() == Outcomes.COMPLETE));
            if (exist) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(getConfigurationEntry(JDBC_DRIVER));
        connection = DriverManager.getConnection(
                getConfigurationEntry(DB_CONNECT),
                getConfigurationEntry(DB_USER),
                getConfigurationEntry(DB_PASS)
        );
        return connection;
    }

//

    private Result execute(String sql) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.executeUpdate();
            getConnection().close();
            return new Result(Outcomes.COMPLETE, IDP_COMPLETE);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error(e);
            return new Result(Outcomes.FAIL, IDP_FAIL);
        }
    }

    public ResultSet select(String sql){
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            getConnection().close();
            return statement.executeQuery();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error(e);
        }
        return null;
    }
}
