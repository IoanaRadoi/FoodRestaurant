/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ioana.Radoi
 */
@Entity
@Table(name = "produs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdusDB.findAll", query = "SELECT p FROM ProdusDB p"),
    @NamedQuery(name = "ProdusDB.findById", query = "SELECT p FROM ProdusDB p WHERE p.id = :id"),
    @NamedQuery(name = "ProdusDB.findByNume", query = "SELECT p FROM ProdusDB p WHERE p.nume = :nume"),
    @NamedQuery(name = "ProdusDB.findByPret", query = "SELECT p FROM ProdusDB p WHERE p.pret = :pret"),
    @NamedQuery(name = "ProdusDB.findByContinut", query = "SELECT p FROM ProdusDB p WHERE p.continut = :continut"),
    @NamedQuery(name = "ProdusDB.findByPicture", query = "SELECT p FROM ProdusDB p WHERE p.picture = :picture")})
public class ProdusDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nume")
    private String nume;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pret")
    private Double pret;
    @Size(max = 45)
    @Column(name = "continut")
    private String continut;
    @Size(max = 45)
    @Column(name = "picture")
    private String picture;

    public ProdusDB() {
    }

    public ProdusDB(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
        if (!(object instanceof ProdusDB)) {
            return false;
        }
        ProdusDB other = (ProdusDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ProdusDB[ id=" + id + " ]";
    }
    
}
