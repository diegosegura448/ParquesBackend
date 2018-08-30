/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.iteria.parquesbackend;

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
 * @author Usuario1
 */
@Entity
@Table(name = "PARKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parks.findAll", query = "SELECT p FROM Parks p")
    , @NamedQuery(name = "Parks.findById", query = "SELECT p FROM Parks p WHERE p.id = :id")
    , @NamedQuery(name = "Parks.findByName", query = "SELECT p FROM Parks p WHERE p.name = :name")
    , @NamedQuery(name = "Parks.findByState", query = "SELECT p FROM Parks p WHERE p.state = :state")
    , @NamedQuery(name = "Parks.findByCapacity", query = "SELECT p FROM Parks p WHERE p.capacity = :capacity")
    , @NamedQuery(name = "Parks.findByStatus", query = "SELECT p FROM Parks p WHERE p.status = :status")})
public class Parks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CAPACITY")
    private String capacity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STATUS")
    private String status;

    public Parks() {
    }

    public Parks(String id) {
        this.id = id;
    }

    public Parks(String id, String name, String state, String capacity, String status) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.capacity = capacity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Parks)) {
            return false;
        }
        Parks other = (Parks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.iteria.parquesbackend.Parks[ id=" + id + " ]";
    }
    
}
