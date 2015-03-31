/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author oracle
 */
@Entity
@Table(name = "COUNTRIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Countries c"),
    @NamedQuery(name = "Countries.findByCountryId", query = "SELECT c FROM Countries c WHERE c.countryId = :countryId"),
    @NamedQuery(name = "Countries.findByCountryName", query = "SELECT c FROM Countries c WHERE c.countryName = :countryName")})
public class Countries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "COUNTRY_ID")
    private String countryId;
    @Size(max = 40)
    @Column(name = "COUNTRY_NAME")
    private String countryName;

  static SessionFactory sessionFactory ;
    
    
    public Countries() {
    }

    public Countries(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countries)) {
            return false;
        }
        Countries other = (Countries) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Countries[ countryId=" + countryId + " ]";
    }
    
   
    public boolean saveCountry(Countries a){
        Session session = null ;
        boolean noErrors = true;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        try {
            System.out.println("bob");
            session = sessionFactory.openSession();
             System.out.println("bob1");
            session.beginTransaction();
             System.out.println("bob2");
            session.saveOrUpdate(a);
            
           System.out.println("bob3");
            session.getTransaction().commit();
           System.out.println("commited");
        }catch (Exception e){
            if (session != null){
                session.getTransaction().rollback();
            }
            noErrors = false;
             System.out.println(e.toString());
                
        }finally {
           System.out.println(noErrors);
            if (session != null ){
                session.close();
            }
        }
        
        
    return noErrors ;  
        
    }
    
    
}
