package com.example.webapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String mainNumber;
    private String targetNumber;
    private int talkTime;
    private Date date;

    public Call(String mainNumber, String targetNumber, int talkTime, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.mainNumber = mainNumber;
        this.targetNumber = targetNumber;
        this.talkTime = talkTime;
        this.date = formatter.parse(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return talkTime == call.talkTime &&
                id.equals(call.id) &&
                Objects.equals(mainNumber, call.mainNumber) &&
                Objects.equals(targetNumber, call.targetNumber) &&
                Objects.equals(date, call.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mainNumber, targetNumber, talkTime, date);
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", mainNumber='" + mainNumber + '\'' +
                ", targetNumber='" + targetNumber + '\'' +
                ", talkTime=" + talkTime +
                ", data=" + date +
                '}';
    }
}
