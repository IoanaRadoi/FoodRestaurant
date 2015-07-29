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
@Table(name = "com_prod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComProdDB.findAll", query = "SELECT c FROM ComProdDB c"),
    @NamedQuery(name = "ComProdDB.findById", query = "SELECT c FROM ComProdDB c WHERE c.id = :id"),
    @NamedQuery(name = "ComProdDB.findByIdCom", query = "SELECT c FROM ComProdDB c WHERE c.idCom = :idCom"),
    @NamedQuery(name = "ComProdDB.findByIdProd", query = "SELECT c FROM ComProdDB c WHERE c.idProd = :idProd")})
public class ComProdDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_com")
    private Integer idCom;
    @Column(name = "id_prod")
    private Integer idProd;

    public ComProdDB() {
    }

    public ComProdDB(Integer id, Integer idCom, Integer idProd) {
        this.id = id;
        this.idCom = idCom;
        this.idProd = idProd;
    }
    
    

    public ComProdDB(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCom() {
        return idCom;
    }

    public void setIdCom(Integer idCom) {
        this.idCom = idCom;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
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
        if (!(object instanceof ComProdDB)) {
            return false;
        }
        ComProdDB other = (ComProdDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ComProdDB[ id=" + id + " ]";
    }
    
}
