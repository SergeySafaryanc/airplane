package ru.sfedu.airplane.api;

import java.util.List;

import ru.sfedu.airplane.models.constants.CheckAir;
import ru.sfedu.airplane.models.constants.CheckPilot;
import ru.sfedu.airplane.models.TypePilot;
import ru.sfedu.airplane.Result;

public interface IDataProvider {

    public void initDataSource();
    public <T> Result addRecord(List<T> bean, Class clazz);
    public Result getRecordById(int id, Class clazz);
    public Result delRecordById(int id, Class clazz);
    public <T> Result updRecordById(List<T> bean, int id, Class clazz);

    public Result checkPilot(int id, CheckPilot checkPilot);
    public Result checkAir(int id, TypePilot type, CheckAir checkAir);
}
