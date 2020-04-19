/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "saleitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saleitem.findAll", query = "SELECT s FROM Saleitem s"),
    @NamedQuery(name = "Saleitem.findById", query = "SELECT s FROM Saleitem s WHERE s.id = :id"),
    @NamedQuery(name = "Saleitem.findByPrice", query = "SELECT s FROM Saleitem s WHERE s.price = :price"),
    @NamedQuery(name = "Saleitem.findByQuantity", query = "SELECT s FROM Saleitem s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Saleitem.findByTotal", query = "SELECT s FROM Saleitem s WHERE s.total = :total")})
public class Saleitem extends BaseEntity implements Serializable {
    
    @Column(name = "Price")
    private Float price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Total")
    private Float total;
    @JoinColumn(name = "ProductID", referencedColumnName = "ID")
    @ManyToOne
    private Product productID;
    @JoinColumn(name = "SaleID", referencedColumnName = "ID")
    @ManyToOne
    private Sale saleID;

    public Saleitem() {
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Sale getSaleID() {
        return saleID;
    }

    public void setSaleID(Sale saleID) {
        this.saleID = saleID;
    }
}
