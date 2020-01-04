package oliverdd.utils.beans;

public class WeaData {
    private String cityid;
    private String update_time;
    private String city;
    private String cityEn;

    public String getCityid() {
        return cityid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getCity() {
        return city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public Data[] getData() {
        return data;
    }

    private String country;
    private String countryEn;
    Data[] data;// 6 datas
}
