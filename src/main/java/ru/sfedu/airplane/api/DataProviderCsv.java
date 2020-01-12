package ru.sfedu.airplane.api;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.airplane.Result;
import ru.sfedu.airplane.models.constants.*;
import ru.sfedu.airplane.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.sfedu.airplane.models.constants.Constants.*;
import static ru.sfedu.airplane.models.constants.Outcomes.*;
import static ru.sfedu.airplane.utils.ConfigurationUtil.getConfigurationEntry;
import static ru.sfedu.airplane.models.constants.ClassesType.*;
import static ru.sfedu.airplane.models.constants.Outcomes.FAIL;

/**
 * The type Data api csv.
 */
@SuppressWarnings("JavaDoc")
public class DataProviderCsv implements IDataProvider {

    private static Logger log = LogManager.getLogger(DataProviderCsv.class);

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
    public <T> Result addRecord(List<T> bean, Class clazz) {

        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())) {
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
                return new Result(FAIL);
        }
    }

    /**
     * @param id
     * @param clazz
     * @return
     */
    @Override
    public Result getRecordById(long id, Class clazz) {

        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())) {
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

    /**
     * @param id
     * @param clazz
     * @return
     */
    @Override
    public Result delRecordById(long id, Class clazz) {
        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())) {

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
                return new Result(FAIL);
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
    public <T> Result updRecordById(List<T> bean, long id, Class clazz) {
        switch (ClassesType.valueOf(clazz.getSimpleName().toUpperCase())) {
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
                return new Result(FAIL);
        }
    }

    /**
     * @param id
     * @param checkPilot
     * @return
     */
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

    /**
     * @param id
     * @param type
     * @param checkAir
     * @return
     */
    @Override
    public Result checkAir(long id, TypePilot type, CheckAir checkAir) {
        try {
            switch (CheckAir.valueOf(checkAir.toString().toUpperCase())) {
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

//    Check methods

    /**
     * @param pilotId
     * @return
     */
    public Result checkPilotCount(long pilotId) {
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getId() == pilotId).mapToInt(Fly::getTime).sum();
            return new Result(COMPLETE, (CHECK_PILOT_COUNT_ANSWER + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result checkPilotListAir(long id) {
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

    /**
     * @param id
     * @return
     */
    public Result checkPilotBool(long id) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean adm = pilots.stream().filter(pilot -> pilot.getId() == id).anyMatch(Pilot::isAdmission);
            if (adm) {
                return new Result(COMPLETE, CHECK_PILOT_TRUE);
            } else {
                return new Result(COMPLETE, CHECK_PILOT_FALSE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param airId
     * @param type
     * @return
     */
    public Result checkAirCount(long airId, TypePilot type) {
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getAirId() == airId && fly.getAirType().equals(String.valueOf(type))).count();
            return new Result(COMPLETE, (DEPARTURES + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @param type
     * @return
     */
    public Result checkAirListPilot(long id, TypePilot type) {
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

    /**
     * @param bean
     * @return
     */
    public Result addFly(List<Fly> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (
                    (getRecordById(a.getId(), Fly.class).getStatus() == FAIL)
                            && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == COMPLETE)
                            && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == COMPLETE)
                            && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == COMPLETE)
            ));
//            log.debug(bean.stream().anyMatch(a -> );
            log.debug(exist);
            if (exist) return add(bean, getConfigurationEntry(FLY.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addAgricultural(List<Agricultural> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getAgriculturalById(a.getId()).getStatus() == FAIL));
            if (exist) return add(bean, getConfigurationEntry(AGRICULTURAL.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addPassenger(List<Passenger> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPassengerById(a.getId()).getStatus() == FAIL));
            if (exist) return add(bean, getConfigurationEntry(PASSENGER.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addFreight(List<Freight> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFreightById(a.getId()).getStatus() == FAIL));
            if (exist) return add(bean, getConfigurationEntry(FREIGHT.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addPilot(List<Pilot> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPilotById(a.getId()).getStatus() == FAIL)
                    && Arrays.stream(TypePilot.values()).anyMatch(typePilot -> typePilot.toString().equals(a.getTypePilot())));
            if (exist) return add(bean, getConfigurationEntry(PILOT.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addBomber(List<Bomber> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getBomberById(a.getId()).getStatus() == FAIL));
            log.debug(exist);
            if (exist) return add(bean, getConfigurationEntry(BOMBER.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addFither(List<Fither> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFitherById(a.getId()).getStatus() == FAIL));
            if (exist) return add(bean, getConfigurationEntry(FITHER.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

    /**
     * @param bean
     * @return
     */
    public Result addFireAircraft(List<FireAircraft> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getFireAircraftById(a.getId()).getStatus() == FAIL));
            if (exist) return add(bean, getConfigurationEntry(FIREAIRCRAFT.getCsvKey()), true);
        } catch (NullPointerException | IOException e) {
            log.error(e);
        }
        return new Result(FAIL, Constants.IDP_ADD_FAIL);
    }

//    getById methods

    /**
     * @param id
     * @return
     */
    public Result getFlyById(long id) {
        try {
            List<Fly> list = read(Fly.class);
            assert list != null;
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Fly>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result getPilotById(long id) {
        try {
            List<Pilot> list = read(Pilot.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Pilot>(COMPLETE, list.toString());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getPassengerById(long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Passenger>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getFreightById(long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Freight>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getFireAircraftById(long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<FireAircraft>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getFitherById(long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Fither>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getAgriculturalById(long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Agricultural>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    public Result getBomberById(long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Bomber>(COMPLETE, String.valueOf(list));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @param getType
     * @return
     */
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

//    del methods

    /**
     * @param clazz
     * @param id
     * @return
     */
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

            add(flies, getConfigurationEntry(FLY.getCsvKey()), false);

            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delPilotById(long id) {
        try {
            List<Pilot> list = read(Pilot.class);
            delDependency(Pilot.class, id);
            list.removeIf(l -> l.getId() == id);
            add(list, getConfigurationEntry(PILOT.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delBomberById(long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Bomber.class, id);
            add(list, getConfigurationEntry(BOMBER.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delAgriculturalById(long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Agricultural.class, id);
            add(list, getConfigurationEntry(AGRICULTURAL.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delPassengerById(long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Passenger.class, id);
            add(list, getConfigurationEntry(PASSENGER.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delFreightById(long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Freight.class, id);
            add(list, getConfigurationEntry(FREIGHT.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delFitherById(long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Fither.class, id);
            add(list, getConfigurationEntry(FITHER.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delFireAircraftById(long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(FireAircraft.class, id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delFlyById(long id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l -> (l.getId() == id));
            add(list, getConfigurationEntry(FLY.getCsvKey()), false);
            return new Result(COMPLETE);
        } catch (IOException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//    upd methods

    /**
     * @param clazz
     * @param id
     * @return
     */
    public Result updDependency(Class clazz, long id) {
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

            add(flies, getConfigurationEntry(FLY.getCsvKey()), false);

            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updPilotById(List<Pilot> bean, long id) {
        try {
            List<Pilot> list = read(Pilot.class);

            list.removeIf(a -> a.getId() == id);
            list.addAll(bean);

            add(list, getConfigurationEntry(PILOT.getCsvKey()), false);
            updDependency(Pilot.class, id);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updAgriculturalById(List<Agricultural> bean, long id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getCsvKey()), false);
            if (addAgricultural(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updBomberById(List<Bomber> bean, long id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(BOMBER.getCsvKey()), false);
            if (addBomber(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updFitherById(List<Fither> bean, long id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FITHER.getCsvKey()), false);
            if (addFither(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updFreightById(List<Freight> bean, long id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FREIGHT.getCsvKey()), false);
            if (addFreight(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updPassengerById(List<Passenger> bean, long id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(PASSENGER.getCsvKey()), false);
            if (addPassenger(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updFireAircraftById(List<FireAircraft> bean, long id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getCsvKey()), false);
            if (addFireAircraft(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param bean
     * @param id
     * @return
     */
    public Result updFlyById(List<Fly> bean, long id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FLY.getCsvKey()), false);
            if (addFly(bean).getStatus() == COMPLETE) return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

//

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> read(Class clazz) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(getConfigurationEntry(ClassesType.valueOf(clazz.getSimpleName().toUpperCase()).getCsvKey())));
            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .withType(clazz).build();
            List<T> list = csvToBean.parse();
            return (List<T>) list;
        } catch (IOException | NullPointerException e) {
            log.error(e);
            return Collections.emptyList();
        }
    }

    /**
     * @param bean
     * @param file_path
     * @param append
     * @return
     */
    private Result add(List<?> bean, String file_path, Boolean append) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file_path, append));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withApplyQuotesToAll(false).build();

            beanToCsv.write(bean);
            writer.close();

            return new Result(COMPLETE, Constants.IDP_COMPLETE);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }
}
