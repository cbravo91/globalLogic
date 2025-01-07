package com.evualuation.globallogic.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneDTO {

    private Long number;

    @JsonProperty("citycode")
    private Integer cityCode;

    @JsonProperty("contrycode")
    private String countryCode;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getCitycode() {
        return cityCode;
    }

    public void setCitycode(Integer citycode) {
        this.cityCode = citycode;
    }

    public String getContrycode() {
        return countryCode;
    }

    public void setContrycode(String contrycode) {
        this.countryCode = contrycode;
    }
}
