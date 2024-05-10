package com.example.lab8;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement(name="customers")
@Entity
public class Developer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String otchestvo;
    private Date birthDate;
    private String passportSeries;
    private String passportNumber;
    private String homeTown;
    private String factAdress;
    private String phone;
    private String email;
    private Boolean workStatus;
    private String hierarchyStatus;
    private String propiskaAdress;
    private String citizenship;

    @XmlElement(name = "citizenship")
    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @XmlElement(name = "propiskaAdress")
    public String getPropiskaAdress() {
        return propiskaAdress;
    }

    public void setPropiskaAdress(String propiskaAdress) {
        this.propiskaAdress = propiskaAdress;
    }

    @XmlElement(name = "hierarchyStatus")
    public String getHierarchyStatus() {
        return hierarchyStatus;
    }

    public void setHierarchyStatus(String hierarchyStatus) {
        this.hierarchyStatus = hierarchyStatus;
    }

    @XmlElement(name = "workStatus")
    public Boolean getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Boolean workStatus) {
        this.workStatus = workStatus;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "factAdress")
    public String getFactAdress() {
        return factAdress;
    }

    public void setFactAdress(String factAdress) {
        this.factAdress = factAdress;
    }

    @XmlElement(name = "homeTown")
    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    @XmlElement(name = "passportNumber")
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @XmlElement(name = "passportSeries")
    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    @XmlElement(name = "birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @XmlElement(name = "otchestvo")
    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public Developer() {
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @XmlElement(name="firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement(name="lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * toString method (optional)
     */

}
