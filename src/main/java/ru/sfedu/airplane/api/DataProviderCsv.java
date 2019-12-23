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
    public Result delRecordById(int id, Class clazz) {
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
        return new Result(FAIL);
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
    private Result checkPilotCount(int pilotId) {
        try {
            List<Fly> flies = read(Fly.class);
            int count = flies.stream().filter(fly -> fly.getPilotId() == pilotId).mapToInt(Fly::getTime).sum();
            return new Result(Outcomes.COMPLETE, (CHECK_PILOT_COUNT_ANSWER + count));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result checkPilotListAir(int id) {
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
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result checkPilotBool(int id) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean adm = pilots.stream().filter(pilot -> pilot.getPilotId() == id).anyMatch(Pilot::isAdmission);
            if (adm) {
                return new Result(Outcomes.COMPLETE, CHECK_PILOT_TRUE);
            } else {
                return new Result(Outcomes.COMPLETE, CHECK_PILOT_FALSE);
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
    private Result checkAirCount(int airId, TypePilot type) {
        try {
            List<Fly> flies = read(Fly.class);
            long count = flies.stream().filter(fly -> fly.getAirId() == airId && fly.getAirType().equals(String.valueOf(type))).count();
            return new Result(Outcomes.COMPLETE, (DEPARTURES + count));
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
    private Result checkAirListPilot(int id, TypePilot type) {
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
                    (getRecordById(a.getFlyId(), Fly.class).getStatus() == FAIL)
                            && (getRecordById(a.getPilotId(), Pilot.class).getStatus() == Outcomes.COMPLETE)
                            && (getRecordById(a.getAirId(), ClassesType.valueOf(a.getAirType().toUpperCase()).getClazz()).getStatus() == Outcomes.COMPLETE)
                            && (getTypePilotById(a.getPilotId(), a.getAirType().toUpperCase()).getStatus() == Outcomes.COMPLETE)
            ));
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
    private Result addAgricultural(List<Agricultural> bean) {
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
    private Result addPassenger(List<Passenger> bean) {
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
    private Result addFreight(List<Freight> bean) {
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
    private Result addPilot(List<Pilot> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getPilotById(a.getPilotId()).getStatus() == FAIL)
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
    private Result addBomber(List<Bomber> bean) {
        try {
            boolean exist = bean.stream().anyMatch(a -> (getBomberById(a.getId()).getStatus() == FAIL));
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
    private Result addFither(List<Fither> bean) {
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
    private Result addFireAircraft(List<FireAircraft> bean) {
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
    private Result getFlyById(int id) {
        try {
            List<Fly> list = read(Fly.class);
            assert list != null;
            list.removeIf(l -> l.getFlyId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Fly>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getPilotById(int id) {
        try {
            List<Pilot> list = read(Pilot.class);
            list.removeIf(l -> l.getPilotId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Pilot>(Outcomes.COMPLETE, list.toString());
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
    private Result getPassengerById(int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Passenger>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getFreightById(int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Freight>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getFireAircraftById(int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<FireAircraft>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getFitherById(int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Fither>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getAgriculturalById(int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Agricultural>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getBomberById(int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l -> l.getId() != id);
            if (list.isEmpty()) {
                return new Result(FAIL, IDP_READ_ERROR);
            } else {
                return new Result<Bomber>(Outcomes.COMPLETE, String.valueOf(list));
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
    private Result getTypePilotById(int id, String getType) {
        try {
            List<Pilot> pilots = read(Pilot.class);
            boolean check = pilots.stream().anyMatch(pilot -> (pilot.getPilotId() == id) && (pilot.getTypePilot().equals(getType.toUpperCase())));
            if (check) return new Result(Outcomes.COMPLETE);
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

            add(flies, getConfigurationEntry(FLY.getCsvKey()), false);

            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delPilotById(int id) {
        try {
            List<Pilot> list = read(Pilot.class);
            delDependency(Pilot.class, id);
            list.removeIf(l -> l.getPilotId() == id);
            add(list, getConfigurationEntry(PILOT.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delBomberById(int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Bomber.class, id);
            add(list, getConfigurationEntry(BOMBER.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delAgriculturalById(int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Agricultural.class, id);
            add(list, getConfigurationEntry(AGRICULTURAL.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delPassengerById(int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Passenger.class, id);
            add(list, getConfigurationEntry(PASSENGER.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delFreightById(int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Freight.class, id);
            add(list, getConfigurationEntry(FREIGHT.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delFitherById(int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(Fither.class, id);
            add(list, getConfigurationEntry(FITHER.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delFireAircraftById(int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(l -> l.getId() == id);
            delDependency(FireAircraft.class, id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(FAIL);
    }

    /**
     * @param id
     * @return
     */
    private Result delFlyById(int id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(l -> (l.getFlyId() == id));
            add(list, getConfigurationEntry(FLY.getCsvKey()), false);
            return new Result(Outcomes.COMPLETE);
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
    private Result updDependency(Class clazz, int id) {
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

            add(flies, getConfigurationEntry(FLY.getCsvKey()), false);

            return new Result(Outcomes.COMPLETE);
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
    private Result updPilotById(List<Pilot> bean, int id) {
        try {
            List<Pilot> list = read(Pilot.class);

            list.removeIf(a -> a.getPilotId() == id);
            list.addAll(bean);

            add(list, getConfigurationEntry(PILOT.getCsvKey()), false);
            updDependency(Pilot.class, id);
            return new Result(Outcomes.COMPLETE);
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
    private Result updAgriculturalById(List<Agricultural> bean, int id) {
        try {
            List<Agricultural> list = read(Agricultural.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(AGRICULTURAL.getCsvKey()), false);
            if (addAgricultural(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updBomberById(List<Bomber> bean, int id) {
        try {
            List<Bomber> list = read(Bomber.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(BOMBER.getCsvKey()), false);
            if (addBomber(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updFitherById(List<Fither> bean, int id) {
        try {
            List<Fither> list = read(Fither.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FITHER.getCsvKey()), false);
            if (addFither(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updFreightById(List<Freight> bean, int id) {
        try {
            List<Freight> list = read(Freight.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FREIGHT.getCsvKey()), false);
            if (addFreight(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updPassengerById(List<Passenger> bean, int id) {
        try {
            List<Passenger> list = read(Passenger.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(PASSENGER.getCsvKey()), false);
            if (addPassenger(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updFireAircraftById(List<FireAircraft> bean, int id) {
        try {
            List<FireAircraft> list = read(FireAircraft.class);
            list.removeIf(a -> a.getId() == id);
            add(list, getConfigurationEntry(FIREAIRCRAFT.getCsvKey()), false);
            if (addFireAircraft(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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
    private Result updFlyById(List<Fly> bean, int id) {
        try {
            List<Fly> list = read(Fly.class);
            list.removeIf(a -> a.getFlyId() == id);
            add(list, getConfigurationEntry(FLY.getCsvKey()), false);
            if (addFly(bean).getStatus() == Outcomes.COMPLETE) return new Result(Outcomes.COMPLETE);
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

            return new Result(Outcomes.COMPLETE, Constants.IDP_COMPLETE);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.error(e);
        }
        return new Result(FAIL);
    }
}
