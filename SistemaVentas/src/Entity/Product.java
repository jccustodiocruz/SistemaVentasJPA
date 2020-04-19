/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByStock", query = "SELECT p FROM Product p WHERE p.stock = :stock")})
public class Product extends BaseEntity implements Serializable {

    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Float price;
    @Column(name = "Stock")
    private Integer stock;
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID")
    @ManyToOne
    private Category categoryID;
    @JoinColumn(name = "ProviderID", referencedColumnName = "ID")
    @ManyToOne
    private Provider providerID;
    @OneToMany(mappedBy = "productID")
    private Collection<Saleitem> saleitemCollection;

    public Product() {
    }

    public Product(String name, float price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Provider getProviderID() {
        return providerID;
    }

    public void setProviderID(Provider providerID) {
        this.providerID = providerID;
    }

    @XmlTransient
    public Collection<Saleitem> getSaleitemCollection() {
        return saleitemCollection;
    }

    public void setSaleitemCollection(Collection<Saleitem> saleitemCollection) {
        this.saleitemCollection = saleitemCollection;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
