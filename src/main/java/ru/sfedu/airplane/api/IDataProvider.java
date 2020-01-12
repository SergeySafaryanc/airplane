package ru.sfedu.airplane.api;

import java.util.List;

import ru.sfedu.airplane.models.*;
import ru.sfedu.airplane.models.constants.CheckAir;
import ru.sfedu.airplane.models.constants.CheckPilot;
import ru.sfedu.airplane.Result;

public interface IDataProvider {

    public void initDataSource();

//    Generalized method

    public <T> Result addRecord(List<T> bean, Class clazz);
    public Result getRecordById(long id, Class clazz);
    public Result delRecordById(long id, Class clazz);
    public <T> Result updRecordById(List<T> bean, long id, Class clazz);

//  Pilot
    public Result addPilot(List<Pilot> bean);
    public Result getPilotById(long id);
    public Result delPilotById(long id);
    public Result updPilotById(List<Pilot> bean, long id);

//  Fly
    public Result addFly(List<Fly> bean);
    public Result getFlyById(long id);
    public Result delFlyById(long id);
    public Result updFlyById(List<Fly> bean, long id);

//    Airplane

//    Bomber
    public Result addBomber(List<Bomber> bean);
    public Result getBomberById(long id);
    public Result delBomberById(long id);
    public Result updBomberById(List<Bomber> bean, long id);

//    Fither
    public Result addFither(List<Fither> bean);
    public Result getFitherById(long id);
    public Result delFitherById(long id);
    public Result updFitherById(List<Fither> bean, long id);

//    FireAircraft
    public Result addFireAircraft(List<FireAircraft> bean);
    public Result getFireAircraftById(long id);
    public Result delFireAircraftById(long id);
    public Result updFireAircraftById(List<FireAircraft> bean, long id);

//    Agricultural
    public Result addAgricultural(List<Agricultural> bean);
    public Result getAgriculturalById(long id);
    public Result delAgriculturalById(long id);
    public Result updAgriculturalById(List<Agricultural> bean, long id);

//    Freight
    public Result addFreight(List<Freight> bean);
    public Result getFreightById(long id);
    public Result delFreightById(long id);
    public Result updFreightById(List<Freight> bean, long id);

//    Passenger
    public Result addPassenger(List<Passenger> bean);
    public Result getPassengerById(long id);
    public Result delPassengerById(long id);
    public Result updPassengerById(List<Passenger> bean, long id);


    public Result checkPilot(long id, CheckPilot checkPilot);
    public Result checkAir(long id, TypePilot type, CheckAir checkAir);
}
