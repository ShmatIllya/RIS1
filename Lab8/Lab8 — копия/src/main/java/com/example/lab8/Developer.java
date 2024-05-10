package com.example.lab8;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customers")
@Entity
public class Developer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private int budget;

    public Developer() {
    }
    public Developer(String firstName, String lastName, String adress, int budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = adress;
        this.budget = budget;
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
    @XmlElement(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @XmlElement(name="budget")
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * toString method (optional)
     */
    @Override
    public String toString() {
        return "Developer:\n" +
                "id: " + id +
                "\nFirst Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Address: " + address + "\n" +
                "Budget: " + budget + "\n";
    }

}
