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

import static ru.sfedu.airplane.models.constants.Outcomes.*;
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
    public Result getRecordById(long id, Class clazz) {

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
                return new Result(FAIL);
        }
    }

    @Override
    public Result delRecordById(long id, Class clazz) {
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
    public <T> Result updRecordById(List<T> bean, long id, Class clazz) {
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
    public Result checkPilot(long id, CheckPilot checkPilot) {
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
        return new Result(FAIL);
    }

    @Override
    public Result checkAir(long id, TypePilot type, CheckAir checkAir) {
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
        return new Result(FAIL);
    }

//    check methods

    public Result checkPilotCount(long pilotId){
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getId() == pilotId).mapToInt(Fly::getTime).sum();
            return new Result(COMPLETE, ("Flying hours - " + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result checkPilotListAir(long id){
        List list = new ArrayList();
        try {
            List<Fly> flies = read(Fly.class);
            flies.stream().filter(fly -> fly.getId() == id).forEach(fly -> {
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
            return new Result(COMPLETE, list.toString());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result checkPilotBool(long id) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean adm = pilots.stream().filter(pilot -> pilot.getId() == id).anyMatch(Pilot::isAdmission);
            if (adm) {
                return new Result(COMPLETE, Constants.CHECK_PILOT_TRUE);
            } else {
                return new Result(COMPLETE, Constants.CHECK_PILOT_FALSE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result checkAirCount(long airId, TypePilot type){
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getAirId() == airId && fly.getAirType().equals(String.valueOf(type))).count();
            return new Result(COMPLETE,  (Constants.DEPARTURES + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result checkAirListPilot(long id, TypePilot type){
        try {
            List<Fly> flies = read(Fly.class);
            List<Pilot> pilots = read(Pilot.class);
            List list = new ArrayList();
            flies.stream().filter(fly -> fly.getAirId() == id && fly.getAirType().equals(String.valueOf(type))).forEach(fly -> {
                list.add(pilots.stream().filter(pilot -> pilot.getId() == fly.getId()).collect(Collectors.toList()));
            });
            return new Result(COMPLETE, String.valueOf(list));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }


//    add methods

    public Result addFly(List<Fly> bean) {
        ArrayList<Fly> flies = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a->(
                    (getRecordById(a.getId(), Fly.class).getStatus() == FAIL)
                            && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == COMPLETE)
                            && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == COMPLETE)
                            && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == COMPLETE)
            ));
            log.debug(bean.toString());
            log.debug(exist);
            if (read(Fly.class) != null) flies.addAll(read((Fly.class)));
            flies.addAll(bean);
            if (exist) return add(flies, getConfigurationEntry(FLY.getXmlKey()));
        } catch (NullPointerException | IOException e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result addFireAircraft(List<FireAircraft> bean) {
        ArrayList<FireAircraft> fireAircrafts  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFireAircraftById(a.getId()).getStatus() == FAIL));
            if (read(FireAircraft.class) != null) fireAircrafts.addAll(read(FireAircraft.class));

            fireAircrafts.addAll(bean);
            if (exist) return add(fireAircrafts, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(FAIL);
    }

    public Result addBomber(List<Bomber> bean) {
        ArrayList<Bomber> bombers  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getBomberById(a.getId()).getStatus() == FAIL));

            if (read(Bomber.class) != null) bombers.addAll(read(Bomber.class));

            bombers.addAll(bean);
            if (exist) return add(bombers, getConfigurationEntry(BOMBER.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(FAIL);
    }

    public Result addPilot(List<Pilot> bean){
        ArrayList<Pilot> pilots  = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPilotById(a.getId()).getStatus() == FAIL)
                    && Arrays.stream(TypePilot.values()).anyMatch(typePilot -> typePilot.toString().equals(a.getTypePilot())));
            if (read(Pilot.class) != null) {
                pilots.addAll(read(Pilot.class));
            }
            pilots.addAll(bean);
            if (exist) return add(pilots, getConfigurationEntry(ClassesType.PILOT.getXmlKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(FAIL);
    }

    public Result addFreight(List<Freight> bean) {
        ArrayList<Freight> freights = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFreightById(a.getId()).getStatus() == FAIL));
            if (read(Freight.class) != null) freights.addAll(read(Freight.class));

            freights.addAll(bean);
            if (exist) return add(freights, getConfigurationEntry(FREIGHT.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result addAgricultural(List<Agricultural> bean) {
        ArrayList<Agricultural> agriculturals = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getAgriculturalById(a.getId()).getStatus() == FAIL));

            if (read(Agricultural.class) != null) agriculturals.addAll(read(Agricultural.class));

            agriculturals.addAll(bean);
            if (exist) return add(agriculturals, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result addPassenger(List<Passenger> bean) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPassengerById(a.getId()).getStatus() == FAIL));
            if (read(Passenger.class) != null) {
                passengers.addAll(read(Passenger.class));
            }
            passengers.addAll(bean);
            if (exist) return add(passengers, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result addFither(List<Fither> bean) {
        ArrayList<Fither> fithers = new ArrayList<>();
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFitherById(a.getId()).getStatus() == FAIL));
            if (read(Fither.class) != null) fithers.addAll(read(Fither.class));
            fithers.addAll(bean);
            if (exist) return add(fithers, getConfigurationEntry(FITHER.getXmlKey()));
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//    getById methods

    public Result getTypePilotById(long id, String getType) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean check = pilots.stream().anyMatch(pilot -> (pilot.getId() == id) && (pilot.getTypePilot().equals(getType.toUpperCase())));
            if (check) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getPilotById(long pilotId) {
        try {
            List<Pilot> list = read(Pilot.class);
            list.removeIf(l->l.getId() != pilotId);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Pilot>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getBomberById(long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Bomber>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getFireAircraftById(long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<FireAircraft>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getAgriculturalById(long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
//            assert list != null;
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Agricultural>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getPassengerById(long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Passenger>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getFreightById(long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Freight>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getFitherById(long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Fither>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result getFlyById(long id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l->l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL);
            } else {
                return new Result<Fly>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//    del methods

    public Result delDependency(Class clazz, long id) {
        try {
            List<Fly> flies = read(Fly.class);

            if (clazz == Pilot.class) {
                List<Pilot> pilots = read(Pilot.class);
                flies.stream().filter(fly -> fly.getId() == id).forEach(fly -> {
                    pilots.stream().filter(pilot -> pilot.getId() == id).findFirst().ifPresent(pilot -> {
                        fly.setPilotId(0);
                    });
                });
            } else {
                flies.stream().filter(fly -> (fly.getAirId() == id) && (fly.getAirType().equals(clazz.getSimpleName().toUpperCase()))).forEach(fly -> fly.setAirId(0));
            }

            add(flies, getConfigurationEntry(FLY.getXmlKey()));

            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delPilotById(long id) {
        try {
            List<Pilot> list = read(Pilot.class);
            delDependency(Pilot.class, id);
            list.removeIf(l->l.getId() == id);
            add(list, getConfigurationEntry(ClassesType.PILOT.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delFireAircraftById(long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(FireAircraft.class, id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delFitherById(long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Fither.class, id);
            add(list, getConfigurationEntry(FITHER.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delFreightById(long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Freight.class, id);
            add(list, getConfigurationEntry(FREIGHT.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delPassengerById(long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Passenger.class, id);
            add(list, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delAgriculturalById(long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            delDependency(Agricultural.class, id);
            list.removeIf(l -> l.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delBomberById(long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l->l.getId() == id);
            delDependency(Bomber.class, id);
            add(list, getConfigurationEntry(BOMBER.getXmlKey()));
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result delFlyById(long id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l->(l.getId() == id));
            add(list, getConfigurationEntry(FLY.getXmlKey()));
            return new Result(COMPLETE);
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//    upd methods

    public Result updDependency(Class clazz, long id){
        try {
            List<Fly> flies = read(Fly.class);

            if (clazz == Pilot.class) {

                List<Pilot> pilots = read(Pilot.class);
                flies.stream().filter(fly -> fly.getId() == id).forEach(fly -> {
                    pilots.stream().filter(pilot -> pilot.getId() == id).findFirst().ifPresent(pilot -> {
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

            return new Result(COMPLETE);
        } catch (Exception e){
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updPassengerById(List<Passenger> bean, long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(ClassesType.PASSENGER.getXmlKey()));
            if (addPassenger(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updFreightById(List<Freight> bean, long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FREIGHT.getXmlKey()));
            if (addFreight(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updFitherById(List<Fither> bean, long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FITHER.getXmlKey()));
            if (addFither(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updFireAircraftById(List<FireAircraft> bean, long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getXmlKey()));
            if (addFireAircraft(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updBomberById(List<Bomber> bean, long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(BOMBER.getXmlKey()));
            if (addBomber(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updAgriculturalById(List<Agricultural> bean, long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getXmlKey()));
            if (addAgricultural(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updPilotById(List<Pilot> bean, long id) {
        try {
            List<Pilot> list = read(Pilot.class);

            list.removeIf(a -> a.getId() == id);
            list.addAll(bean);

            add(list, getConfigurationEntry(PILOT.getXmlKey()));
            updDependency(Pilot.class, id);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    public Result updFlyById(List<Fly> bean, long id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(a->a.getId() == id);
            add(list, getConfigurationEntry(FLY.getXmlKey()));
            if (addFly(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//

    public <T> List read(Class clazz) {
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
            return new Result(COMPLETE, Constants.IDP_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }
}
