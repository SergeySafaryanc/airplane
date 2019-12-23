package ru.sfedu.airplane.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.airplane.Result;
import ru.sfedu.airplane.models.constants.*;
import ru.sfedu.airplane.models.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static ru.sfedu.airplane.utils.ConfigurationUtil.getConfigurationEntry;
import static ru.sfedu.airplane.models.constants.ClassesType.*;

public class DataProviderXml implements IDataProvider {

    private static Logger log = LogManager.getLogger(DataProviderXml.class);

    @Override
    public void initDataSource() {

    }

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

    private Result checkPilotCount(int pilotId){
        try {
            List<Fly> flies = read(Fly.class);
            int count = flies.stream().filter(fly -> fly.getPilotId() == pilotId).mapToInt(Fly::getTime).sum();
            return new Result(Outcomes.COMPLETE, ("Flying hours - " + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result checkPilotListAir(int id){
        List list = new ArrayList();
        try {
            List<Fly> flies = read(Fly.class);
            flies.stream().filter(fly -> fly.getPilotId() == id).forEach(fly -> {
                switch (ClassesType.valueOf(fly.getAirType().toUpperCase())) {
                    case AGRICULTURAL:
                        List<Agricultural> agriculturals = read(Agricultural.class);
                        list.add(agriculturals.stream().filter(agricultural -> agricultural.getId() == fly.getAirId()).collect(Collectors.toList()));
                        break;
                    case BOMBER:
                        List<Bomber> bombers = read(Bomber.class);
                        list.add(bombers.stream().filter(bomber -> bomber.getId() == fly.getAirId()).collect(Collectors.toList()));
                        break;
                    case FIREAIRCRAFT:
                        List<FireAircraft> fireAircrafts = read(FireAircraft.class);
                        list.add(fireAircrafts.stream().filter(fireAircraft -> fireAircraft.getId() == fly.getAirId()).collect(Collectors.toList()));
                        break;
                    case FITHER:
                        List<Fither> fithers = read(Fither.class);
                        list.add(fithers.stream().filter(fither -> fither.getId() == fly.getAirId()).collect(Collectors.toList()));
                        break;
                    case FREIGHT:
                        List<Freight> freights = read(Freight.class);
                        list.add(freights.stream().filter(freight -> freight.getId() == fly.getAirId()).collect(Collectors.toList()));
                        break;
                    case PASSENGER:
                        List<Passenger> passengers = read(Passenger.class);
                        list.add(passengers.stream().filter(passenger -> passenger.getId() == fly.getAirId()).distinct().collect(Collectors.toList()));
                        break;
                }
            });
            return new Result(Outcomes.COMPLETE, list.toString());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result checkPilotBool(int id) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean adm = pilots.stream().filter(pilot -> pilot.getPilotId() == id).anyMatch(Pilot::isAdmission);
            if (adm) {
                return new Result(Outcomes.COMPLETE, Constants.CHECK_PILOT_TRUE);
            } else {
                return new Result(Outcomes.COMPLETE, Constants.CHECK_PILOT_FALSE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result checkAirCount(int airId, TypePilot type){
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getAirId() == airId && fly.getAirType().equals(String.valueOf(type))).count();
            return new Result(Outcomes.COMPLETE,  (Constants.DEPARTURES + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result checkAirListPilot(int id, TypePilot type){
        try {
            List<Fly> flies = read(Fly.class);
            List<Pilot> pilots = read(Pilot.class);
            List list = new ArrayList();
            flies.stream().filter(fly -> fly.getAirId() == id && fly.getAirType().equals(String.valueOf(type))).forEach(fly -> {
                list.add(pilots.stream().filter(pilot -> pilot.getPilotId() == fly.getPilotId()).collect(Collectors.toList()));
            });
            return new Result(Outcomes.COMPLETE, String.valueOf(list));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }


//    add methods

    private Result addFly(List<Fly> bean) {
        ArrayList<Fly> flies = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a->(
                    (getRecordById(a.getFlyId(), Fly.class).getStatus() == Outcomes.FAIL)
                            && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == Outcomes.COMPLETE)
                            && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == Outcomes.COMPLETE)
                            && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == Outcomes.COMPLETE)
            ));
            if (read(Fly.class) != null) flies.addAll(read((Fly.class)));
            flies.addAll(bean);
            if (exist) return add(flies, getConfigurationEntry(FLY.getXmlKey()));
        } catch (NullPointerException | IOException e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addFireAircraft(List<FireAircraft> bean) {
        ArrayList<FireAircraft> fireAircrafts  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFireAircraftById(a.getId()).getStatus() == Outcomes.FAIL));
            if (read(FireAircraft.class) != null) fireAircrafts.addAll(read(FireAircraft.class));

            fireAircrafts.addAll(bean);
            if (exist) return add(fireAircrafts, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addBomber(List<Bomber> bean) {
        ArrayList<Bomber> bombers  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getBomberById(a.getId()).getStatus() == Outcomes.FAIL));

            if (read(Bomber.class) != null) bombers.addAll(read(Bomber.class));

            bombers.addAll(bean);
            if (exist) return add(bombers, getConfigurationEntry(BOMBER.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addPilot(List<Pilot> bean){
        ArrayList<Pilot> pilots  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPilotById(a.getPilotId()).getStatus() == Outcomes.FAIL)
                    && Arrays.stream(TypePilot.values()).anyMatch(typePilot -> typePilot.toString().equals(a.getTypePilot())));
            if (read(Pilot.class) != null) {
                pilots.addAll(read(Pilot.class));
            }
            pilots.addAll(bean);
            if (exist) return add(pilots, getConfigurationEntry(ClassesType.PILOT.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addFreight(List<Freight> bean) {
        ArrayList<Freight> freights = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFreightById(a.getId()).getStatus() == Outcomes.FAIL));
            if (read(Freight.class) != null) freights.addAll(read(Freight.class));

            freights.addAll(bean);
            if (exist) return add(freights, getConfigurationEntry(FREIGHT.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addAgricultural(List<Agricultural> bean) {
        ArrayList<Agricultural> agriculturals = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getAgriculturalById(a.getId()).getStatus() == Outcomes.FAIL));

            if (read(Agricultural.class) != null) agriculturals.addAll(read(Agricultural.class));

            agriculturals.addAll(bean);
            if (exist) return add(agriculturals, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addPassenger(List<Passenger> bean) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPassengerById(a.getId()).getStatus() == Outcomes.FAIL));
            if (read(Passenger.class) != null) {
                passengers.addAll(read(Passenger.class));
            }
            passengers.addAll(bean);
            if (exist) return add(passengers, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result addFither(List<Fither> bean) {
        ArrayList<Fither> fithers = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFitherById(a.getId()).getStatus() == Outcomes.FAIL));
            if (read(Fither.class) != null) fithers.addAll(read(Fither.class));
            fithers.addAll(bean);
            if (exist) return add(fithers, getConfigurationEntry(FITHER.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    getById methods

    private Result getTypePilotById(int id, String getType) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean check = pilots.stream().anyMatch(pilot -> (pilot.getPilotId() == id) && (pilot.getTypePilot().equals(getType.toUpperCase())));
            if (check) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getPilotById(int pilotId) {
        try {
            List<Pilot> list = read(Pilot.class);
            list.removeIf(l->l.getPilotId() != pilotId);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Pilot>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getBomberById(int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Bomber>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFireAircraftById(int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<FireAircraft>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getAgriculturalById(int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
//            assert list != null;
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Agricultural>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getPassengerById(int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Passenger>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFreightById(int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Freight>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFitherById(int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Fither>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result getFlyById(int id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l->l.getFlyId() != id);
            if (list.isEmpty()) {
                return new Result(Outcomes.FAIL);
            } else {
                return new Result<Fly>(Outcomes.COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    del methods

    private Result delDependency(Class clazz, int id) {
        try {
            List<Fly> flies = read(Fly.class);

            if (clazz == Pilot.class) {
                List<Pilot> pilots = read(Pilot.class);
                flies.stream().filter(fly -> fly.getPilotId() == id).forEach(fly -> {
                    pilots.stream().filter(pilot -> pilot.getPilotId() == id).findFirst().ifPresent(pilot -> {
                        fly.setPilotId(0);
                    });
                });
            } else {
                flies.stream().filter(fly -> (fly.getAirId() == id) && (fly.getAirType().equals(clazz.getSimpleName().toUpperCase()))).forEach(fly -> fly.setAirId(0));
            }

            add(flies, getConfigurationEntry(FLY.getXmlKey()));

            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delPilotById(int id) {
        try {
            List<Pilot> list = read(Pilot.class);
            delDependency(Pilot.class, id);
            list.removeIf(l->l.getPilotId() == id);
            add(list, getConfigurationEntry(ClassesType.PILOT.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFireAircraftById(int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(FireAircraft.class, id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFitherById(int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Fither.class, id);
            add(list, getConfigurationEntry(FITHER.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFreightById(int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Freight.class, id);
            add(list, getConfigurationEntry(FREIGHT.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delPassengerById(int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Passenger.class, id);
            add(list, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delAgriculturalById(int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            delDependency(Agricultural.class, id);
            list.removeIf(l -> l.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delBomberById(int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l->l.getId() == id);
            delDependency(Bomber.class, id);
            add(list, getConfigurationEntry(BOMBER.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result delFlyById(int id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l->(l.getFlyId() == id));
            add(list, getConfigurationEntry(FLY.getXmlKey()));
            return new Result(Outcomes.COMPLETE);
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//    upd methods

    private Result updDependency(Class clazz, int id){
        try {
            List<Fly> flies = read(Fly.class);

            if (clazz == Pilot.class) {

                List<Pilot> pilots = read(Pilot.class);
                flies.stream().filter(fly -> fly.getPilotId() == id).forEach(fly -> {
                    pilots.stream().filter(pilot -> pilot.getPilotId() == id).findFirst().ifPresent(pilot -> {
                        if (ClassesType.valueOf(pilot.getTypePilot().toUpperCase()) != ClassesType.valueOf(fly.getAirType().toUpperCase())) {
                            fly.setPilotId(0);
                        } else {
                            fly.setPilotId(id);
                        }
                    });
                });
            } else {
                flies.stream().filter(fly -> (fly.getAirId() == id) && (fly.getAirType().equals(clazz.getSimpleName().toUpperCase()))).forEach(fly -> fly.setAirId(0));
            }

            add(flies, getConfigurationEntry(FLY.getXmlKey()));

            return new Result(Outcomes.COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updPassengerById(List<Passenger> bean, int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
            if (addPassenger(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFreightById(List<Freight> bean, int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FREIGHT.getXmlKey()));
            if (addFreight(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFitherById(List<Fither> bean, int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FITHER.getXmlKey()));
            if (addFither(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFireAircraftById(List<FireAircraft> bean, int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
            if (addFireAircraft(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updBomberById(List<Bomber> bean, int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(BOMBER.getXmlKey()));
            if (addBomber(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updAgriculturalById(List<Agricultural> bean, int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
            if (addAgricultural(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updPilotById(List<Pilot> bean, int id) {
        try {
            List<Pilot> list = read(Pilot.class);

            list.removeIf(a -> a.getPilotId() == id);
            list.addAll(bean);

            add(list, getConfigurationEntry(PILOT.getXmlKey()));
            updDependency(Pilot.class, id);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

    private Result updFlyById(List<Fly> bean, int id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(a->a.getFlyId() == id);
            add(list, getConfigurationEntry(FLY.getXmlKey()));
            if (addFly(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }

//

    public  <T> List read(Class clazz) {
        try {
            Reader reader = new FileReader(getConfigurationEntry(ClassesType.valueOf(clazz.getSimpleName().toUpperCase()).getXmlKey()));
            Serializer serializer = new Persister();

            XmlWrap wrap = serializer.read(XmlWrap.class, reader);

            if (wrap.getList() == null) wrap.setList(Collections.emptyList());

            return wrap.getList();
        } catch (Exception e) {
            log.error(e);
        }
        return Collections.emptyList();
    }

    private Result add(List<?> bean, String file_path) {
        try {
            FileWriter writer = new FileWriter(file_path);
            Serializer serializer = new Persister();

            XmlWrap xmlWrap = new XmlWrap();
            xmlWrap.setList(bean);

            serializer.write(xmlWrap, writer);
            return new Result(Outcomes.COMPLETE, Constants.IDP_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Outcomes.FAIL);
    }
}
