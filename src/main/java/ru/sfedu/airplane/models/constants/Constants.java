package ru.sfedu.airplane.models.constants;

public class Constants {

    public static final String CU_KEY = "config";
    public static final String DEFAULT_CONFIG_PATH = "/config.properties";
    public static final String FULL_PATH = "src/main/resources/config.properties";

//    properties key

    public static final String AGRICULTURAL_CSV = "ru.sfedu.airplane.AGRICULTURAL_CSV";
    public static final String PILOT_CSV = "ru.sfedu.airplane.PILOT_CSV";
    public static final String BOMBER_CSV = "ru.sfedu.airplane.BOMBER_CSV";
    public static final String FITHER_CSV = "ru.sfedu.airplane.FITHER_CSV";
    public static final String FIRE_AIRCRAFT_CSV = "ru.sfedu.airplane.FIRE_AIRCRAFT_CSV";
    public static final String FREIGHT_CSV = "ru.sfedu.airplane.FREIGHT_CSV";
    public static final String PASSENGER_CSV = "ru.sfedu.airplane.PASSENGER_CSV";
    public static final String FLY_CSV = "ru.sfedu.airplane.FLY_CSV";

    public static final String AGRICULTURAL_XML = "ru.sfedu.airplane.AGRICULTURAL_XML";
    public static final String PILOT_XML = "ru.sfedu.airplane.PILOT_XML";
    public static final String BOMBER_XML = "ru.sfedu.airplane.BOMBER_XML";
    public static final String FITHER_XML = "ru.sfedu.airplane.FITHER_XML";
    public static final String FIRE_AIRCRAFT_XML = "ru.sfedu.airplane.FIRE_AIRCRAFT_XML";
    public static final String FREIGHT_XML = "ru.sfedu.airplane.FREIGHT_XML";
    public static final String PASSENGER_XML = "ru.sfedu.airplane.PASSENGER_XML";
    public static final String FLY_XML = "ru.sfedu.airplane.FLY_XML";

    public static final String JDBC_DRIVER = "ru.sfedu.airplane.jdbcDriver";
    public static final String DB_CONNECT = "ru.sfedu.airplane.dbConn";
    public static final String DB_USER = "ru.sfedu.airplane.dbUsr";
    public static final String DB_PASS = "ru.sfedu.airplane.dbPass";

//    table column

    public static final String FLY_FLY_ID = "FlyId";
    public static final String FLY_AIR_ID = "AirId";
    public static final String FLY_AIR_TYPE = "AirType";
    public static final String FLY_PILOT_ID = "PilotId";
    public static final String FLY_TIME = "Time";

    public static final String PILOT_PILOT_ID = "PilotId";
    public static final String PILOT_FIRST_NAME = "FirstName";
    public static final String PILOT_LAST_NAME = "LastName";
    public static final String PILOT_TYPE_PILOT = "TypePilot";
    public static final String PILOT_ADMISSION = "Admission";

    public static final String BOMBER_ID = "Id";
    public static final String BOMBER_MODEL = "Model";
    public static final String BOMBER_PRODUCER = "Producer";
    public static final String BOMBER_MAX_DISTANCE = "MaxDistance";
    public static final String BOMBER_COUNTRY = "Country";
    public static final String BOMBER_NUM_BOMBS = "NumBombs";
    public static final String BOMBER_TYPE_BOMBS = "TypeBombs";

    public static final String FITHER_ID = "Id";
    public static final String FITHER_MODEL = "Model";
    public static final String FITHER_PRODUCER = "Producer";
    public static final String FITHER_MAX_DISTANCE = "MaxDistance";
    public static final String FITHER_COUNTRY = "Country";
    public static final String FITHER_NUM_ROCKET = "NumRocket";
    public static final String FITHER_TYPE_ROCKET = "TypeRocket";
    public static final String FITHER_GENERATION = "Generation";

    public static final String FIRE_AIRCRAFT_ID = "Id";
    public static final String FIRE_AIRCRAFT_MODEL = "Model";
    public static final String FIRE_AIRCRAFT_PRODUCER = "Producer";
    public static final String FIRE_AIRCRAFT_MAX_DISTANCE = "MaxDistance";
    public static final String FIRE_AIRCRAFT_MISSION = "Mission";
    public static final String FIRE_AIRCRAFT_DISPLACEMENT = "Displacement";
    public static final String FIRE_AIRCRAFT_SPRAY_WIGHT = "SprayWight";

    public static final String AGRICULTURAL_ID = "Id";
    public static final String AGRICULTURAL_MODEL = "Model";
    public static final String AGRICULTURAL_PRODUCER = "Producer";
    public static final String AGRICULTURAL_MAX_DISTANCE = "MaxDistance";
    public static final String AGRICULTURAL_MISSION = "Mission";
    public static final String AGRICULTURAL_DISPLACEMENT = "Displacement";
    public static final String AGRICULTURAL_SPRAY_WIGHT = "SprayWight";

    public static final String FREIGHT_ID = "Id";
    public static final String FREIGHT_MODEL = "Model";
    public static final String FREIGHT_PRODUCER = "Producer";
    public static final String FREIGHT_MAX_DISTANCE = "MaxDistance";
    public static final String FREIGHT_TYPE_FLIGHT = "TypeFlight";
    public static final String FREIGHT_MAX_WEIGHT = "MaxWeight";

    public static final String PASSENGER_ID = "Id";
    public static final String PASSENGER_MODEL = "Model";
    public static final String PASSENGER_PRODUCER = "Producer";
    public static final String PASSENGER_MAX_DISTANCE = "MaxDistance";
    public static final String PASSENGER_TYPE_FLIGHT = "TypeFlight";
    public static final String PASSENGER_NUM_PASSENGER = "NumPassenger";
    public static final String PASSENGER_SERVICE = "Service";

//    Sql query

    public static final String SELECT_ALL_FLY = "SELECT * FROM Fly WHERE flyId=%d";
    public static final String SELECT_ALL_PILOT = "SELECT * FROM Pilot WHERE pilotId=%d;";
    public static final String SELECT_ALL_BOMBER = "SELECT * FROM Bomber WHERE Id=%d;";
    public static final String SELECT_ALL_FITHER = "SELECT * FROM Fither WHERE Id=%d;";
    public static final String SELECT_ALL_FIRE_AIRCRAFT = "SELECT * FROM FireAircraft WHERE Id=%d;";
    public static final String SELECT_ALL_AGRICULTURAL = "SELECT * FROM Agricultural WHERE Id=%d;";
    public static final String SELECT_ALL_FREIGHT = "SELECT * FROM Freight WHERE Id=%d;";
    public static final String SELECT_ALL_PASSENGER = "SELECT * FROM Passenger WHERE Id=%d;";

    public static final String SELECT_PILOT_BY_FLY = "SELECT airId, airType FROM Fly WHERE pilotId=%d";
    public static final String SELECT_PILOT = "SELECT typePilot FROM Pilot WHERE pilotId=%d LIMIT 1";

    public static final String SELECT_COUNT_FLY = "SELECT COUNT(*) FROM Fly WHERE airId=%d AND airType='%s'";
    public static final String SELECT_SUM_TIME_FLY = "SELECT SUM(time) as tt FROM Fly WHERE pilotId=%d";
    public static final String SELECT_COUNT_PILOT = "SELECT count(*) FROM Pilot WHERE pilotId=%d and typePilot='%s'";

    public static final String SELECT_PILOT_JOIN_FLY = "SELECT Pilot.* FROM Fly INNER JOIN Pilot ON Fly.pilotId=Pilot.pilotId WHERE Fly.airType='%s' AND Fly.airId=%d";
    public static final String SELECT_AGRICULTURAL_JOIN_FLY = "SELECT Agricultural.* FROM Fly INNER JOIN Agricultural ON Fly.airId = Agricultural.id WHERE Fly.pilotId=%d";
    public static final String SELECT_BOMBER_JOIN_FLY = "SELECT Bomber.* FROM Fly INNER JOIN Bomber ON Fly.airId = Bomber.id WHERE Fly.pilotId=%d";
    public static final String SELECT_FIRE_AIRCRAFT_JOIN_FLY = "SELECT FireAircraft.* FROM Fly INNER JOIN FireAircraft ON Fly.airId = FireAircraft.id WHERE Fly.pilotId=%d";
    public static final String SELECT_FITHER_JOIN_FLY = "SELECT Fither.* FROM Fly INNER JOIN Fither ON Fly.airId = Fither.id WHERE Fly.pilotId=%d";
    public static final String SELECT_FREIGHT_JOIN_FLY = "SELECT Freight.* FROM Fly INNER JOIN Freight ON Fly.airId = Freight.id WHERE Fly.pilotId=%d";
    public static final String SELECT_PASSENGER_JOIN_FLY = "SELECT Passenger.* FROM Fly INNER JOIN Passenger ON Fly.airId = Passenger.id WHERE Fly.pilotId=%d";

    public static final String INSERT_FLY = "INSERT INTO Fly VALUES (%d, %d, '%s', %d, %d);";
    public static final String INSERT_PILOT = "INSERT INTO Pilot VALUES (%d, '%s', '%s', '%s', '%s');";
    public static final String INSERT_BOMBER = "INSERT INTO Bomber VALUES (%d, '%s', '%s', %d, '%s', %d, '%s');";
    public static final String INSERT_FITHER = "INSERT INTO Fither VALUES (%d, '%s', '%s', %d, '%s', %d, '%s', %d);";
    public static final String INSERT_FIRE_AIRCRAFT = "INSERT INTO FireAircraft VALUES (%d, '%s', '%s', %d, '%s', %d, %d);";
    public static final String INSERT_AGRICULTURAL = "INSERT INTO Agricultural VALUES (%d, '%s', '%s', %d, '%s', %d, %d);";
    public static final String INSERT_FREIGHT = "INSERT INTO Freight VALUES (%d, '%s', '%s', %d, '%s', %d);";
    public static final String INSERT_PASSENGER = "INSERT INTO Passenger VALUES (%d, '%s', '%s', %d, '%s', %d, '%s');";

    public static final String UPDATE_FLY_PILOT = "UPDATE Fly SET pilotId=0 WHERE pilotId=%d;";
    public static final String UPDATE_FLY_AIR_FIREAIRCRAFT = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='FIREAIRCRAFT';";
    public static final String UPDATE_FLY_AIR_FITHER = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='FITHER';";
    public static final String UPDATE_FLY_AIR_BOMBER = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='BOMBER';";
    public static final String UPDATE_FLY_AIR_FREIGHT = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='FREIGHT';";
    public static final String UPDATE_FLY_AIR_PASSENGER = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='PASSENGER';";
    public static final String UPDATE_FLY_AIR_AGRICULTURAL = "UPDATE Fly SET airId=0 WHERE airId=%d AND airType='AGRICULTURAL';";
    public static final String UPDATE_FLY_BY_ID = "UPDATE Fly SET airId=%d, airType='%s', pilotId=%d, time=%d WHERE flyId=%d;";

    public static final String UPDATE_PILOT = "UPDATE Pilot SET firstName='%s', lastName='%s' WHERE pilotId=%d";
    public static final String UPDATE_PILOT_TYPE = "UPDATE Pilot SET firstName='%s', lastName='%s', typePilot='%s' WHERE pilotId=%d";
    public static final String UPDATE_PASSENGER = "UPDATE Passenger SET model='%s' , producer='%s', maxDistance=%d, typeFlight='%s', numPassenger=%d, service='%s' WHERE id=%d;";
    public static final String UPDATE_FREIGHT = "UPDATE Freight SET model='%s' , producer='%s', maxDistance=%d, typeFlight='%s', maxWeight=%d WHERE id=%d;";
    public static final String UPDATE_FITHER = "UPDATE Fither SET model='%s' , producer='%s', maxDistance=%d, country='%s', numRocket=%d, typeRocket='%s', generation=%d WHERE id=%d;";
    public static final String UPDATE_FIRE_AIRCRAFT = "UPDATE FireAircraft SET model='%s' , producer='%s', maxDistance=%d, mission='%s', displacement=%d, sprayWight=%d WHERE id=%d;";
    public static final String UPDATE_BOMBER = "UPDATE Bomber SET model='%s' , producer='%s', maxDistance=%d, country='%s', numBombs=%d, typeBombs='%s' WHERE id=%d;";
    public static final String UPDATE_AGRICULTURAL = "UPDATE Agricultural SET model='%s' , producer='%s', maxDistance=%d, mission='%s', displacement=%d, sprayWight=%d WHERE id=%d;";

    public static final String DELETE_PILOT = "DELETE FROM Pilot WHERE pilotId=%d;";
    public static final String DELETE_FIREAIRCRAFT = "DELETE FROM FireAircraft WHERE Id=%d;";
    public static final String DELETE_FLY = "DELETE FROM Fly WHERE flyId=%d";
    public static final String DELETE_FITHER = "DELETE FROM Fither WHERE Id=%d;";
    public static final String DELETE_FREIGHT = "DELETE FROM Freight WHERE Id=%d;";
    public static final String DELETE_PASSENGER = "DELETE FROM Passenger WHERE Id=%d;";
    public static final String DELETE_AGRICULTURAL = "DELETE FROM Agricultural WHERE Id=%d;";
    public static final String DELETE_BOMBER = "DELETE FROM Bomber WHERE Id=%d;";

    public static final String SPLIT = "\\W+";
    public static final String GET_ID = "\\d+";

//    answer

    public static final String CLI_ERROR = "Does not exists";
    public static final String CLI_PARSER_ERROR = "Invalid number of arguments";

    public static final String IDP_ADD_FAIL = "Write error";
    public static final String IDP_READ_ERROR = "Record not found";
    public static final String IDP_COMPLETE = "Operation completed";
    public static final String IDP_FAIL = "Operation failed";

    public static final String CHECK_PILOT_COUNT_ANSWER = "Flying hours - ";
    public static final String CHECK_PILOT_TRUE = "Pilot is admitted";
    public static final String CHECK_PILOT_FALSE = "Pilot is not admitted";
    public static final String DEPARTURES = "Departures - ";

}
