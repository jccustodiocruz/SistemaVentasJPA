/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Category;
import Entity.Product;
import Entity.Provider;
import Entity.Saleitem;
import Repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juanc
 */
public class ProductRepository implements Serializable {

    public ProductRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getSaleitemCollection() == null) {
            product.setSaleitemCollection(new ArrayList<Saleitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category categoryID = product.getCategoryID();
            if (categoryID != null) {
                categoryID = em.getReference(categoryID.getClass(), categoryID.getId());
                product.setCategoryID(categoryID);
            }
            Provider providerID = product.getProviderID();
            if (providerID != null) {
                providerID = em.getReference(providerID.getClass(), providerID.getId());
                product.setProviderID(providerID);
            }
            Collection<Saleitem> attachedSaleitemCollection = new ArrayList<Saleitem>();
            for (Saleitem saleitemCollectionSaleitemToAttach : product.getSaleitemCollection()) {
                saleitemCollectionSaleitemToAttach = em.getReference(saleitemCollectionSaleitemToAttach.getClass(), saleitemCollectionSaleitemToAttach.getId());
                attachedSaleitemCollection.add(saleitemCollectionSaleitemToAttach);
            }
            product.setSaleitemCollection(attachedSaleitemCollection);
            em.persist(product);
            if (categoryID != null) {
                categoryID.getProductCollection().add(product);
                categoryID = em.merge(categoryID);
            }
            if (providerID != null) {
                providerID.getProductCollection().add(product);
                providerID = em.merge(providerID);
            }
            for (Saleitem saleitemCollectionSaleitem : product.getSaleitemCollection()) {
                Product oldProductIDOfSaleitemCollectionSaleitem = saleitemCollectionSaleitem.getProductID();
                saleitemCollectionSaleitem.setProductID(product);
                saleitemCollectionSaleitem = em.merge(saleitemCollectionSaleitem);
                if (oldProductIDOfSaleitemCollectionSaleitem != null) {
                    oldProductIDOfSaleitemCollectionSaleitem.getSaleitemCollection().remove(saleitemCollectionSaleitem);
                    oldProductIDOfSaleitemCollectionSaleitem = em.merge(oldProductIDOfSaleitemCollectionSaleitem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Category categoryIDOld = persistentProduct.getCategoryID();
            Category categoryIDNew = product.getCategoryID();
            Provider providerIDOld = persistentProduct.getProviderID();
            Provider providerIDNew = product.getProviderID();
            Collection<Saleitem> saleitemCollectionOld = persistentProduct.getSaleitemCollection();
            Collection<Saleitem> saleitemCollectionNew = product.getSaleitemCollection();
            if (categoryIDNew != null) {
                categoryIDNew = em.getReference(categoryIDNew.getClass(), categoryIDNew.getId());
                product.setCategoryID(categoryIDNew);
            }
            if (providerIDNew != null) {
                providerIDNew = em.getReference(providerIDNew.getClass(), providerIDNew.getId());
                product.setProviderID(providerIDNew);
            }
            Collection<Saleitem> attachedSaleitemCollectionNew = new ArrayList<Saleitem>();
            for (Saleitem saleitemCollectionNewSaleitemToAttach : saleitemCollectionNew) {
                saleitemCollectionNewSaleitemToAttach = em.getReference(saleitemCollectionNewSaleitemToAttach.getClass(), saleitemCollectionNewSaleitemToAttach.getId());
                attachedSaleitemCollectionNew.add(saleitemCollectionNewSaleitemToAttach);
            }
            saleitemCollectionNew = attachedSaleitemCollectionNew;
            product.setSaleitemCollection(saleitemCollectionNew);
            product = em.merge(product);
            if (categoryIDOld != null && !categoryIDOld.equals(categoryIDNew)) {
                categoryIDOld.getProductCollection().remove(product);
                categoryIDOld = em.merge(categoryIDOld);
            }
            if (categoryIDNew != null && !categoryIDNew.equals(categoryIDOld)) {
                categoryIDNew.getProductCollection().add(product);
                categoryIDNew = em.merge(categoryIDNew);
            }
            if (providerIDOld != null && !providerIDOld.equals(providerIDNew)) {
                providerIDOld.getProductCollection().remove(product);
                providerIDOld = em.merge(providerIDOld);
            }
            if (providerIDNew != null && !providerIDNew.equals(providerIDOld)) {
                providerIDNew.getProductCollection().add(product);
                providerIDNew = em.merge(providerIDNew);
            }
            for (Saleitem saleitemCollectionOldSaleitem : saleitemCollectionOld) {
                if (!saleitemCollectionNew.contains(saleitemCollectionOldSaleitem)) {
                    saleitemCollectionOldSaleitem.setProductID(null);
                    saleitemCollectionOldSaleitem = em.merge(saleitemCollectionOldSaleitem);
                }
            }
            for (Saleitem saleitemCollectionNewSaleitem : saleitemCollectionNew) {
                if (!saleitemCollectionOld.contains(saleitemCollectionNewSaleitem)) {
                    Product oldProductIDOfSaleitemCollectionNewSaleitem = saleitemCollectionNewSaleitem.getProductID();
                    saleitemCollectionNewSaleitem.setProductID(product);
                    saleitemCollectionNewSaleitem = em.merge(saleitemCollectionNewSaleitem);
                    if (oldProductIDOfSaleitemCollectionNewSaleitem != null && !oldProductIDOfSaleitemCollectionNewSaleitem.equals(product)) {
                        oldProductIDOfSaleitemCollectionNewSaleitem.getSaleitemCollection().remove(saleitemCollectionNewSaleitem);
                        oldProductIDOfSaleitemCollectionNewSaleitem = em.merge(oldProductIDOfSaleitemCollectionNewSaleitem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            Category categoryID = product.getCategoryID();
            if (categoryID != null) {
                categoryID.getProductCollection().remove(product);
                categoryID = em.merge(categoryID);
            }
            Provider providerID = product.getProviderID();
            if (providerID != null) {
                providerID.getProductCollection().remove(product);
                providerID = em.merge(providerID);
            }
            Collection<Saleitem> saleitemCollection = product.getSaleitemCollection();
            for (Saleitem saleitemCollectionSaleitem : saleitemCollection) {
                saleitemCollectionSaleitem.setProductID(null);
                saleitemCollectionSaleitem = em.merge(saleitemCollectionSaleitem);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
