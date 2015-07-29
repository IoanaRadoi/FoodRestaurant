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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ioana.Radoi
 */
@Entity
@Table(name = "comanda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComandaDB.findAll", query = "SELECT c FROM ComandaDB c"),
    @NamedQuery(name = "ComandaDB.findById", query = "SELECT c FROM ComandaDB c WHERE c.id = :id"),
    @NamedQuery(name = "ComandaDB.findByTimp", query = "SELECT c FROM ComandaDB c WHERE c.timp = :timp"),
    @NamedQuery(name = "ComandaDB.findByIdMasa", query = "SELECT c FROM ComandaDB c WHERE c.idMasa = :idMasa"),
    @NamedQuery(name = "ComandaDB.findByIdUser", query = "SELECT c FROM ComandaDB c WHERE c.idUser = :idUser")})
public class ComandaDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "timp")
    private Double timp;
    @Column(name = "id_masa")
    private Integer idMasa;
    @Column(name = "id_user")
    private Integer idUser;

    public ComandaDB() {
    }

    public ComandaDB(Integer id) {
        this.id = id;
    }

    public ComandaDB(Integer id, Double timp, Integer idMasa, Integer idUser) {
        this.id = id;
        this.timp = timp;
        this.idMasa = idMasa;
        this.idUser = idUser;
    }    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTimp() {
        return timp;
    }

    public void setTimp(Double timp) {
        this.timp = timp;
    }

    public Integer getIdMasa() {
        return idMasa;
    }

    public void setIdMasa(Integer idMasa) {
        this.idMasa = idMasa;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof ComandaDB)) {
            return false;
        }
        ComandaDB other = (ComandaDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ComandaDB[ id=" + id + " ]";
    }
    
}
