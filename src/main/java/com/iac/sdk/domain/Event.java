package com.iac.sdk.domain;

import java.io.Serializable;

public class Event implements Serializable {

    private Long id;
    private String name;
    private String type;
    private String version;
    private String deviceType;
    private String deviceFamily;
    private String os;
    private String country;
    private String city;
    private String carrier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event withId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event withName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Event withType(String type) {
        this.type = type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Event withVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Event withDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getDeviceFamily() {
        return deviceFamily;
    }

    public void setDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    public Event withDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
        return this;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Event withOs(String deviceOs) {
        this.os = deviceOs;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Event withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Event withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Event withCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceFamily='" + deviceFamily + '\'' +
                ", os='" + os + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", carrier='" + carrier + '\'' +
                '}';
    }
}
