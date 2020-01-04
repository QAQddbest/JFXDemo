package oliverdd.utils.beans;

import javafx.scene.control.Alert;

public class Data {
    private String day;
    private String date;
    private String week;
    private String wea;
    private String wea_img;
    private int air;

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getWeek() {
        return week;
    }

    public String getWea() {
        return wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public int getAir() {
        return air;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getAir_level() {
        return air_level;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public String getTem1() {
        return tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public String getTem3() {
        return tem3;
    }

    public String[] getWin() {
        return win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public Hour[] getHours() {
        return hours;
    }

    private int humidity;
    private String air_level;
    private String air_tips;
    private Alarm alarm;
    private String tem1;
    private String tem2;
    private String tem3;
    private String[] win;
    private String win_speed;
    private Hour[] hours; //4 hours
    private Index[] index;

    public Index[] getIndex() {
        return index;
    }
}
