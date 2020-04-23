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
import Entity.Customer;
import Entity.Sale;
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
public class SaleRepository implements Serializable {

    public SaleRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sale sale) {
        if (sale.getSaleitemCollection() == null) {
            sale.setSaleitemCollection(new ArrayList<Saleitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customerID = sale.getCustomerID();
            if (customerID != null) {
                customerID = em.getReference(customerID.getClass(), customerID.getId());
                sale.setCustomerID(customerID);
            }
            Collection<Saleitem> attachedSaleitemCollection = new ArrayList<Saleitem>();
            for (Saleitem saleitemCollectionSaleitemToAttach : sale.getSaleitemCollection()) {
                saleitemCollectionSaleitemToAttach = em.getReference(saleitemCollectionSaleitemToAttach.getClass(), saleitemCollectionSaleitemToAttach.getId());
                attachedSaleitemCollection.add(saleitemCollectionSaleitemToAttach);
            }
            sale.setSaleitemCollection(attachedSaleitemCollection);
            em.persist(sale);
            if (customerID != null) {
                customerID.getSaleCollection().add(sale);
                customerID = em.merge(customerID);
            }
            for (Saleitem saleitemCollectionSaleitem : sale.getSaleitemCollection()) {
                Sale oldSaleIDOfSaleitemCollectionSaleitem = saleitemCollectionSaleitem.getSaleID();
                saleitemCollectionSaleitem.setSaleID(sale);
                saleitemCollectionSaleitem = em.merge(saleitemCollectionSaleitem);
                if (oldSaleIDOfSaleitemCollectionSaleitem != null) {
                    oldSaleIDOfSaleitemCollectionSaleitem.getSaleitemCollection().remove(saleitemCollectionSaleitem);
                    oldSaleIDOfSaleitemCollectionSaleitem = em.merge(oldSaleIDOfSaleitemCollectionSaleitem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sale sale) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sale persistentSale = em.find(Sale.class, sale.getId());
            Customer customerIDOld = persistentSale.getCustomerID();
            Customer customerIDNew = sale.getCustomerID();
            Collection<Saleitem> saleitemCollectionOld = persistentSale.getSaleitemCollection();
            Collection<Saleitem> saleitemCollectionNew = sale.getSaleitemCollection();
            if (customerIDNew != null) {
                customerIDNew = em.getReference(customerIDNew.getClass(), customerIDNew.getId());
                sale.setCustomerID(customerIDNew);
            }
            Collection<Saleitem> attachedSaleitemCollectionNew = new ArrayList<Saleitem>();
            for (Saleitem saleitemCollectionNewSaleitemToAttach : saleitemCollectionNew) {
                saleitemCollectionNewSaleitemToAttach = em.getReference(saleitemCollectionNewSaleitemToAttach.getClass(), saleitemCollectionNewSaleitemToAttach.getId());
                attachedSaleitemCollectionNew.add(saleitemCollectionNewSaleitemToAttach);
            }
            saleitemCollectionNew = attachedSaleitemCollectionNew;
            sale.setSaleitemCollection(saleitemCollectionNew);
            sale = em.merge(sale);
            if (customerIDOld != null && !customerIDOld.equals(customerIDNew)) {
                customerIDOld.getSaleCollection().remove(sale);
                customerIDOld = em.merge(customerIDOld);
            }
            if (customerIDNew != null && !customerIDNew.equals(customerIDOld)) {
                customerIDNew.getSaleCollection().add(sale);
                customerIDNew = em.merge(customerIDNew);
            }
            for (Saleitem saleitemCollectionOldSaleitem : saleitemCollectionOld) {
                if (!saleitemCollectionNew.contains(saleitemCollectionOldSaleitem)) {
                    saleitemCollectionOldSaleitem.setSaleID(null);
                    saleitemCollectionOldSaleitem = em.merge(saleitemCollectionOldSaleitem);
                }
            }
            for (Saleitem saleitemCollectionNewSaleitem : saleitemCollectionNew) {
                if (!saleitemCollectionOld.contains(saleitemCollectionNewSaleitem)) {
                    Sale oldSaleIDOfSaleitemCollectionNewSaleitem = saleitemCollectionNewSaleitem.getSaleID();
                    saleitemCollectionNewSaleitem.setSaleID(sale);
                    saleitemCollectionNewSaleitem = em.merge(saleitemCollectionNewSaleitem);
                    if (oldSaleIDOfSaleitemCollectionNewSaleitem != null && !oldSaleIDOfSaleitemCollectionNewSaleitem.equals(sale)) {
                        oldSaleIDOfSaleitemCollectionNewSaleitem.getSaleitemCollection().remove(saleitemCollectionNewSaleitem);
                        oldSaleIDOfSaleitemCollectionNewSaleitem = em.merge(oldSaleIDOfSaleitemCollectionNewSaleitem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sale.getId();
                if (findSale(id) == null) {
                    throw new NonexistentEntityException("The sale with id " + id + " no longer exists.");
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
            Sale sale;
            try {
                sale = em.getReference(Sale.class, id);
                sale.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sale with id " + id + " no longer exists.", enfe);
            }
            Customer customerID = sale.getCustomerID();
            if (customerID != null) {
                customerID.getSaleCollection().remove(sale);
                customerID = em.merge(customerID);
            }
            Collection<Saleitem> saleitemCollection = sale.getSaleitemCollection();
            for (Saleitem saleitemCollectionSaleitem : saleitemCollection) {
                saleitemCollectionSaleitem.setSaleID(null);
                saleitemCollectionSaleitem = em.merge(saleitemCollectionSaleitem);
            }
            em.remove(sale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sale> findSaleEntities() {
        return findSaleEntities(true, -1, -1);
    }

    public List<Sale> findSaleEntities(int maxResults, int firstResult) {
        return findSaleEntities(false, maxResults, firstResult);
    }

    private List<Sale> findSaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sale.class));
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

    public Sale findSale(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sale.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sale> rt = cq.from(Sale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
