/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "sale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findById", query = "SELECT s FROM Sale s WHERE s.id = :id"),
    @NamedQuery(name = "Sale.findByDate", query = "SELECT s FROM Sale s WHERE s.date = :date"),
    @NamedQuery(name = "Sale.findByDiscount", query = "SELECT s FROM Sale s WHERE s.discount = :discount"),
    @NamedQuery(name = "Sale.findByTotal", query = "SELECT s FROM Sale s WHERE s.total = :total")})
public class Sale extends BaseEntity implements Serializable {

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "Discount")
    private Float discount;
    @Column(name = "Total")
    private Float total;
    @JoinColumn(name = "CustomerID", referencedColumnName = "ID")
    @ManyToOne
    private Customer customerID;
    @OneToMany(mappedBy = "saleID")
    private Collection<Saleitem> saleitemCollection;

    public Sale() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    @XmlTransient
    public Collection<Saleitem> getSaleitemCollection() {
        return saleitemCollection;
    }

    public void setSaleitemCollection(Collection<Saleitem> saleitemCollection) {
        this.saleitemCollection = saleitemCollection;
    }
}
