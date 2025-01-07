package com.evualuation.globallogic.models;

import jakarta.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    private Long number;
    private Integer cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn (name="user_uuid")
    private User user;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getCitycode() {
        return cityCode;
    }

    public void setCitycode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getContrycode() {
        return countryCode;
    }

    public void setContrycode(String contrycode) {
        this.countryCode = contrycode;
    }
}
