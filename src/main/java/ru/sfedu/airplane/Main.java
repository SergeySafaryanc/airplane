package ru.sfedu.airplane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.api.DataProviderCsv;
import ru.sfedu.airplane.api.DataProviderJdbc;
import ru.sfedu.airplane.api.DataProviderXml;
import ru.sfedu.airplane.api.IDataProvider;
import ru.sfedu.airplane.models.constants.CheckAir;
import ru.sfedu.airplane.models.constants.CheckPilot;
import ru.sfedu.airplane.models.constants.Constants;
import ru.sfedu.airplane.models.constants.KeysForCli;
import ru.sfedu.airplane.utils.ConfigurationUtil;

import java.util.*;

import static ru.sfedu.airplane.models.constants.Constants.*;
import static ru.sfedu.airplane.models.constants.KeysForCli.ADD;
import static ru.sfedu.airplane.models.constants.KeysForCli.UPD;

/**
 * The type Main.
 */
public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);
    /**
     * The constant CONFIG_PATH.
     */
    public static String CONFIG_PATH = System.getProperty(CU_KEY, DEFAULT_CONFIG_PATH);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);
            String[] str = scanner.nextLine().toUpperCase().split(Constants.SPLIT, 3);

            String ds = str[0];
            String op = str[1];
            String cs = str[2];

            IDataProvider dataProvider = defineDataSource(ds);
            System.out.println(getAnswer(dataProvider, op, cs));
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static IDataProvider defineDataSource(String key){
        switch (KeysForCli.valueOf(key.toUpperCase())) {
            case CSV:
                return new DataProviderCsv();
            case XML:
                return new DataProviderXml();
            case DB:
                return new DataProviderJdbc();
            default:
                logger.info(CLI_ERROR);
        }
        return null;
    }

    /**
     * @param dataProvider
     * @param key
     * @param str
     * @return
     */
    private static String getAnswer(IDataProvider dataProvider, String key, String str) {
        TypePilot pilot;
        try {
            switch (KeysForCli.valueOf(key.toUpperCase())) {
                case ADDFLY:
                    return fly(dataProvider, str, ADD);
                case ADDPILOT:
                    return pilot(dataProvider, str, ADD);
                case ADDBOMBER:
                    return bomber(dataProvider, str, ADD);
                case ADDAGRICULTURAL:
                    return agricultural(dataProvider, str, ADD);
                case ADDFIREAIRCRAFT:
                    return fireAircraft(dataProvider, str, ADD);
                case ADDFITHER:
                    return fither(dataProvider, str, ADD);
                case ADDFREIGHT:
                    return freight(dataProvider, str, ADD);
                case ADDPASSENGER:
                    return passenger(dataProvider, str, ADD);
                case GETFLY:
                    return dataProvider.getFlyById(Integer.parseInt(str)).getAnswer();
                case GETPILOT:
                    return dataProvider.getPilotById(Integer.parseInt(str)).getAnswer();
                case GETBOMBER:
                    return dataProvider.getBomberById(Integer.parseInt(str)).getAnswer();
                case GETAGRICULTURAL:
                    return dataProvider.getAgriculturalById(Integer.parseInt(str)).getAnswer();
                case GETFIREAIRCRAFT:
                    return dataProvider.getFireAircraftById(Integer.parseInt(str)).getAnswer();
                case GETFITHER:
                    return dataProvider.getFitherById(Integer.parseInt(str)).getAnswer();
                case GETFREIGHT:
                    return dataProvider.getFreightById(Integer.parseInt(str)).getAnswer();
                case GETPASSENGER:
                    return dataProvider.getPassengerById(Integer.parseInt(str)).getAnswer();
                case DELFLY:
                    return dataProvider.delFlyById(Integer.parseInt(str)).getAnswer();
                case DELPILOT:
                    return dataProvider.delPilotById(Integer.parseInt(str)).getAnswer();
                case DELBOMBER:
                    return dataProvider.delBomberById(Integer.parseInt(str)).getAnswer();
                case DELAGRICULTURAL:
                    return dataProvider.delAgriculturalById(Integer.parseInt(str)).getAnswer();
                case DELFIREAIRCRAFT:
                    return dataProvider.delFireAircraftById(Integer.parseInt(str)).getAnswer();
                case DELFITHER:
                    return dataProvider.delFitherById(Integer.parseInt(str)).getAnswer();
                case DELFREIGHT:
                    return dataProvider.delFreightById(Integer.parseInt(str)).getAnswer();
                case DELPASSENGER:
                    return dataProvider.delPassengerById(Integer.parseInt(str)).getAnswer();
                case UPDFLY:
                    return fly(dataProvider, str, UPD);
                case UPDPILOT:
                    return pilot(dataProvider, str, UPD);
                case UPDBOMBER:
                    return bomber(dataProvider, str, UPD);
                case UPDAGRICULTURAL:
                    return agricultural(dataProvider, str, UPD);
                case UPDFIREAIRCRAFT:
                    return fireAircraft(dataProvider, str, UPD);
                case UPDFITHER:
                    return fither(dataProvider, str, UPD);
                case UPDFREIGHT:
                    return freight(dataProvider, str, UPD);
                case UPDPASSENGER:
                    return passenger(dataProvider, str, UPD);
                case CHECKPILOTCOUNT:
                    return dataProvider.checkPilot(Integer.parseInt(str), CheckPilot.COUNT).getAnswer();
                case CHECKPILOTLIST:
                    return dataProvider.checkPilot(Integer.parseInt(str), CheckPilot.LIST).getAnswer();
                case CHECKPILOTCHECK:
                    return dataProvider.checkPilot(Integer.parseInt(str), CheckPilot.CHECK).getAnswer();
                case CHECKAIRCOUNT:
                    pilot = TypePilot.valueOf(parserType(str));
                    return dataProvider.checkAir(Integer.parseInt(str.split(Constants.GET_ID)[0]), pilot, CheckAir.COUNT).getAnswer();
                case CHECKAIRLIST:
                    pilot = TypePilot.valueOf(parserType(str));
                    return dataProvider.checkAir(Integer.parseInt(str.split(Constants.GET_ID)[0]), pilot, CheckAir.PILOT).getAnswer();
            }
        } catch (IllegalArgumentException iLAE) {
            logger.info(CLI_ERROR);
        }
        return "";
    }

//

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String fly(IDataProvider dataProvider, String values, KeysForCli key) {
        Fly fly = new Fly();
        String[] valueFly = parser(values, 5, Constants.SPLIT);

        if (valueFly == null) return "";

        fly.setId(Long.parseLong(valueFly[0]));
        fly.setAirId(Long.parseLong(valueFly[1]));
        fly.setAirType(valueFly[2]);
        fly.setPilotId(Long.parseLong(valueFly[3]));
        fly.setTime(Integer.parseInt(valueFly[4]));

        List<Fly> flies = new ArrayList<>();
        flies.add(fly);

        if (key == ADD) {
            return dataProvider.addFly(flies).getAnswer();
        } else {
            return dataProvider.updFlyById(flies, Long.parseLong(valueFly[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String pilot(IDataProvider dataProvider, String values, KeysForCli key) {
        Pilot pilot = new Pilot();
        String[] valuePilot = parser(values,5, Constants.SPLIT);

        if (valuePilot == null) return "";

        pilot.setId(Integer.parseInt(valuePilot[0]));
        pilot.setFirstName(valuePilot[1]);
        pilot.setLastName(valuePilot[2]);
        pilot.setTypePilot(valuePilot[3]);
        pilot.setAdmission(Boolean.parseBoolean(valuePilot[4]));

        List<Pilot> pilots = new ArrayList<>();
        pilots.add(pilot);
        if (key == ADD) {
            return dataProvider.addPilot(pilots).getAnswer();
        } else {
            return dataProvider.updPilotById(pilots, Long.parseLong(valuePilot[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String bomber(IDataProvider dataProvider, String values, KeysForCli key) {
        Bomber bomber = new Bomber();
        String[] value = parser(values, 7, Constants.SPLIT);

        if (value == null) return "";

        bomber.setId(Integer.parseInt(value[0]));
        bomber.setModel(value[1]);
        bomber.setProducer(value[2]);
        bomber.setMaxDistance(Integer.parseInt(value[3]));
        bomber.setCountry(value[4]);
        bomber.setNumBombs(Integer.parseInt(value[5]));
        bomber.setTypeBombs(value[6]);

        List<Bomber> list = new ArrayList<>();
        list.add(bomber);

        if (key == ADD) {
            return dataProvider.addBomber(list).getAnswer();
        } else {
            return dataProvider.updBomberById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String agricultural(IDataProvider dataProvider, String values, KeysForCli key) {
        Agricultural agricultural = new Agricultural();
        String[] value = parser(values, 7, Constants.SPLIT);

        if (value == null) return "";

        agricultural.setId(Integer.parseInt(value[0]));
        agricultural.setModel(value[1]);
        agricultural.setProducer(value[2]);
        agricultural.setMaxDistance(Integer.parseInt(value[3]));
        agricultural.setMission(value[4]);
        agricultural.setDisplacement(Integer.parseInt(value[5]));
        agricultural.setSprayWight(Integer.parseInt(value[6]));

        List<Agricultural> list = new ArrayList<>();
        list.add(agricultural);

        if (key == ADD) {
            return dataProvider.addAgricultural(list).getAnswer();
        } else {
            return dataProvider.updAgriculturalById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String fireAircraft(IDataProvider dataProvider, String values, KeysForCli key) {
        FireAircraft aircraft = new FireAircraft();
        String[] value = parser(values, 7, Constants.SPLIT);

        if (value == null) return "";

        aircraft.setId(Integer.parseInt(value[0]));
        aircraft.setModel(value[1]);
        aircraft.setProducer(value[2]);
        aircraft.setMaxDistance(Integer.parseInt(value[3]));
        aircraft.setMission(value[4]);
        aircraft.setDisplacement(Integer.parseInt(value[5]));
        aircraft.setSprayWight(Integer.parseInt(value[6]));

        List<FireAircraft> list = new ArrayList<>();
        list.add(aircraft);

        if (key == ADD) {
            return dataProvider.addFireAircraft(list).getAnswer();
        } else {
            return dataProvider.updFireAircraftById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String fither(IDataProvider dataProvider, String values, KeysForCli key) {
        Fither fither = new Fither();
        String[] value = parser(values, 8, Constants.SPLIT);

        if (value == null) return "";

        fither.setId(Integer.parseInt(value[0]));
        fither.setModel(value[1]);
        fither.setProducer(value[2]);
        fither.setMaxDistance(Integer.parseInt(value[3]));
        fither.setCountry(value[4]);
        fither.setNumRocket(Integer.parseInt(value[5]));
        fither.setTypeRocket(value[6]);
        fither.setGeneration(Integer.parseInt(value[7]));

        List<Fither> list = new ArrayList<>();
        list.add(fither);

        if (key == ADD) {
            return dataProvider.addFither(list).getAnswer();
        } else {
            return dataProvider.updFitherById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String freight(IDataProvider dataProvider, String values, KeysForCli key) {
        Freight freight = new Freight();
        String[] value = parser(values, 6, Constants.SPLIT);

        if (value == null) return "";

        freight.setId(Integer.parseInt(value[0]));
        freight.setModel(value[1]);
        freight.setProducer(value[2]);
        freight.setMaxWeight(Integer.parseInt(value[3]));
        freight.setTypeFlight(value[4]);
        freight.setMaxWeight(Integer.parseInt(value[5]));

        List<Freight> list = new ArrayList<>();
        list.add(freight);

        if (key == ADD) {
            return dataProvider.addFreight(list).getAnswer();
        } else {
            return dataProvider.updFreightById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

    /**
     * @param dataProvider
     * @param values
     * @param key
     * @return
     */
    private static String passenger(IDataProvider dataProvider, String values, KeysForCli key) {
        Passenger passenger = new Passenger();
        String[] value = parser(values, 7, Constants.SPLIT);

        if (value == null) return "";

        passenger.setId(Integer.parseInt(value[0]));
        passenger.setModel(value[1]);
        passenger.setProducer(value[2]);
        passenger.setMaxDistance(Integer.parseInt(value[3]));
        passenger.setTypeFlight(value[4]);
        passenger.setNumPassenger(Integer.parseInt(value[5]));
        passenger.setService(value[6]);

        List<Passenger> list = new ArrayList<>();
        list.add(passenger);

        if (key == ADD) {
            return dataProvider.addPassenger(list).getAnswer();
        } else {
            return dataProvider.updPassengerById(list, Long.parseLong(value[0])).getAnswer();
        }
    }

//    other

    /**
     * @param values
     * @param count
     * @param split
     * @return
     */
    private static String[] parser(String values, int count, String split) {
        String[] value = values.split(split);

        if (value.length != count) {
            logger.error(CLI_PARSER_ERROR);
            return null;
        }
        return value;
    }

    /**
     * @param type
     * @return
     */
    private static String parserType(String type) {
        String[] value = type.split(Constants.SPLIT);
        if (TypePilot.valueOf(value[1].toUpperCase()).equals(value[1].toUpperCase())) {
            return value[1].toUpperCase();
        }
        return null;
    }
}
