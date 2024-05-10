/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "colleagues")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colleagues.findAll", query = "SELECT c FROM Colleagues c"),
    @NamedQuery(name = "Colleagues.findById", query = "SELECT c FROM Colleagues c WHERE c.id = :id"),
    @NamedQuery(name = "Colleagues.findByFirstname", query = "SELECT c FROM Colleagues c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Colleagues.findByLastname", query = "SELECT c FROM Colleagues c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Colleagues.findByTitle", query = "SELECT c FROM Colleagues c WHERE c.title = :title"),
    @NamedQuery(name = "Colleagues.findByDepartment", query = "SELECT c FROM Colleagues c WHERE c.department = :department"),
    @NamedQuery(name = "Colleagues.findByEmail", query = "SELECT c FROM Colleagues c WHERE c.email = :email")})
public class Colleagues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 30)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 10)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 20)
    @Column(name = "DEPARTMENT")
    private String department;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "EMAIL")
    private String email;

    public Colleagues() {
    }

    public Colleagues(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colleagues)) {
            return false;
        }
        Colleagues other = (Colleagues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Colleagues[ id=" + id + " ]";
    }
    
}
