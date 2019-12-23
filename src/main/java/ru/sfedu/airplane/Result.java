package ru.sfedu.airplane;

import ru.sfedu.airplane.models.constants.Outcomes;

import java.util.List;

public class Result<T> {

    private Outcomes status;
    private String answer;
    private List<T> bean;

    public Result(Outcomes status) {
        this.status = status;
    }

    public Result(Outcomes status, String answer) {
        this.status = status;
        this.answer = answer;
    }

    public Result(List<T> bean, Outcomes status) {
        this.bean = bean;
        this.status = status;
    }

    public Outcomes getStatus() {
        return status;
    }

    public void setStatus(Outcomes status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<T> getBean() {
        return bean;
    }

    public void setBean(List<T> bean) {
        this.bean = bean;
    }
}
