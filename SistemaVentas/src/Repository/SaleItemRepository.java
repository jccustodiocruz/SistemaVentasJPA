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
import Entity.Product;
import Entity.Sale;
import Entity.Saleitem;
import Repository.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juanc
 */
public class SaleItemRepository implements Serializable {

    public SaleItemRepository() {
        this.emf = Persistence.createEntityManagerFactory("SistemaVentasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Saleitem saleitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product productID = saleitem.getProductID();
            if (productID != null) {
                productID = em.getReference(productID.getClass(), productID.getId());
                saleitem.setProductID(productID);
            }
            Sale saleID = saleitem.getSaleID();
            if (saleID != null) {
                saleID = em.getReference(saleID.getClass(), saleID.getId());
                saleitem.setSaleID(saleID);
            }
            em.persist(saleitem);
            if (productID != null) {
                productID.getSaleitemCollection().add(saleitem);
                productID = em.merge(productID);
            }
            if (saleID != null) {
                saleID.getSaleitemCollection().add(saleitem);
                saleID = em.merge(saleID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Saleitem saleitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saleitem persistentSaleitem = em.find(Saleitem.class, saleitem.getId());
            Product productIDOld = persistentSaleitem.getProductID();
            Product productIDNew = saleitem.getProductID();
            Sale saleIDOld = persistentSaleitem.getSaleID();
            Sale saleIDNew = saleitem.getSaleID();
            if (productIDNew != null) {
                productIDNew = em.getReference(productIDNew.getClass(), productIDNew.getId());
                saleitem.setProductID(productIDNew);
            }
            if (saleIDNew != null) {
                saleIDNew = em.getReference(saleIDNew.getClass(), saleIDNew.getId());
                saleitem.setSaleID(saleIDNew);
            }
            saleitem = em.merge(saleitem);
            if (productIDOld != null && !productIDOld.equals(productIDNew)) {
                productIDOld.getSaleitemCollection().remove(saleitem);
                productIDOld = em.merge(productIDOld);
            }
            if (productIDNew != null && !productIDNew.equals(productIDOld)) {
                productIDNew.getSaleitemCollection().add(saleitem);
                productIDNew = em.merge(productIDNew);
            }
            if (saleIDOld != null && !saleIDOld.equals(saleIDNew)) {
                saleIDOld.getSaleitemCollection().remove(saleitem);
                saleIDOld = em.merge(saleIDOld);
            }
            if (saleIDNew != null && !saleIDNew.equals(saleIDOld)) {
                saleIDNew.getSaleitemCollection().add(saleitem);
                saleIDNew = em.merge(saleIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = saleitem.getId();
                if (findSaleitem(id) == null) {
                    throw new NonexistentEntityException("The saleitem with id " + id + " no longer exists.");
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
            Saleitem saleitem;
            try {
                saleitem = em.getReference(Saleitem.class, id);
                saleitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saleitem with id " + id + " no longer exists.", enfe);
            }
            Product productID = saleitem.getProductID();
            if (productID != null) {
                productID.getSaleitemCollection().remove(saleitem);
                productID = em.merge(productID);
            }
            Sale saleID = saleitem.getSaleID();
            if (saleID != null) {
                saleID.getSaleitemCollection().remove(saleitem);
                saleID = em.merge(saleID);
            }
            em.remove(saleitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Saleitem> findSaleitemEntities() {
        return findSaleitemEntities(true, -1, -1);
    }

    public List<Saleitem> findSaleitemEntities(int maxResults, int firstResult) {
        return findSaleitemEntities(false, maxResults, firstResult);
    }

    private List<Saleitem> findSaleitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Saleitem.class));
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

    public Saleitem findSaleitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Saleitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Saleitem> rt = cq.from(Saleitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
