package net.rest.customerdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;



@Entity
@Table(name = "customers2")
@Getter
@Setter
@ToString
@XmlRootElement
public class Customer extends BaseEntity {
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "otchestvo")
    private String otchestvo;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "passport_series")
    private String passportSeries;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "home_town")
    private String homeTown;

    @Column(name = "fact_adress")
    private String factAdress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "work_status")
    private Boolean workStatus;

    @Column(name = "hierarchy_status")
    private String hierarchyStatus;

    @Column(name = "propiska_adress")
    private String propiskaAdress;

    @Column(name = "citizenship")
    private String citizenship;

    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getOtchestvo()
    {
        return otchestvo;
    }
    public String getBirthDate()
    {
        return birthDate;
    }
    public String getPassportSeries()
    {
        return passportSeries;
    }
    public String getPassportNumber()
    {
        return passportNumber;
    }
    public String getHomeTown()
    {
        return homeTown;
    }
    public String getFactAdress()
    {
        return factAdress;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getEmail()
    {
        return email;
    }
    public Boolean getWorkStatus()
    {
        return workStatus;
    }
    public String getHierarchyStatus()
    {
        return hierarchyStatus;
    }
    public String getPropiskaAdress()
    {
        return propiskaAdress;
    }
    public String getCitizenship()
    {
        return citizenship;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
        System.out.println(this.firstName);
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
        System.out.println(this.lastName);
    }
    public void setOtchestvo(String otchestvo)
    {
        this.otchestvo = otchestvo;
        System.out.println(this.otchestvo);
    }
    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
        System.out.println(this.birthDate);
    }
    public void setPassportSeries(String passportSeries)
    {
        this.passportSeries = passportSeries;
        System.out.println(this.passportSeries);
    }
    public void setPassportNumber(String passportNumber)
    {
        this.passportNumber = passportNumber;
        System.out.println(this.passportNumber);
    }
    public void setHomeTown(String homeTown)
    {
        this.homeTown = homeTown;
        System.out.println(this.homeTown);
    }
    public void setFactAdress(String factAdress)
    {
        this.factAdress = factAdress;
        System.out.println(this.factAdress);
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
        System.out.println(this.phone);
    }
    public void setEmail(String email)
    {
        this.email = email;
        System.out.println(this.email);
    }
    public void setWorkStatus(Boolean workStatus)
    {
        this.workStatus = workStatus;
        System.out.println(this.workStatus);
    }
    public void setHierarchyStatus(String hierarchyStatus)
    {
        this.hierarchyStatus = hierarchyStatus;
        System.out.println(this.hierarchyStatus);
    }
    public void setPropiskaAdress(String propiskaAdress)
    {
        this.propiskaAdress = propiskaAdress;
        System.out.println(this.propiskaAdress);
    }
    public void setCitizenship(String citizenship)
    {
        this.citizenship = citizenship;
        System.out.println(this.citizenship);
    }
}

