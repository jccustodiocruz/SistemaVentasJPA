/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entity.BaseEntity;
import Entity.Customer;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Sale;
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
public class CustomerRepository implements Serializable {

    public CustomerRepository(){
        this.emf = Persistence.createEntityManagerFactory("SistemaVentasPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) {
        if (customer.getSaleCollection() == null) {
            customer.setSaleCollection(new ArrayList<Sale>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Sale> attachedSaleCollection = new ArrayList<Sale>();
            for (Sale saleCollectionSaleToAttach : customer.getSaleCollection()) {
                saleCollectionSaleToAttach = em.getReference(saleCollectionSaleToAttach.getClass(), saleCollectionSaleToAttach.getId());
                attachedSaleCollection.add(saleCollectionSaleToAttach);
            }
            customer.setSaleCollection(attachedSaleCollection);
            em.persist(customer);
            for (Sale saleCollectionSale : customer.getSaleCollection()) {
                Customer oldCustomerIDOfSaleCollectionSale = saleCollectionSale.getCustomerID();
                saleCollectionSale.setCustomerID(customer);
                saleCollectionSale = em.merge(saleCollectionSale);
                if (oldCustomerIDOfSaleCollectionSale != null) {
                    oldCustomerIDOfSaleCollectionSale.getSaleCollection().remove(saleCollectionSale);
                    oldCustomerIDOfSaleCollectionSale = em.merge(oldCustomerIDOfSaleCollectionSale);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getId());
            Collection<Sale> saleCollectionOld = persistentCustomer.getSaleCollection();
            Collection<Sale> saleCollectionNew = customer.getSaleCollection();
            Collection<Sale> attachedSaleCollectionNew = new ArrayList<Sale>();
            for (Sale saleCollectionNewSaleToAttach : saleCollectionNew) {
                saleCollectionNewSaleToAttach = em.getReference(saleCollectionNewSaleToAttach.getClass(), saleCollectionNewSaleToAttach.getId());
                attachedSaleCollectionNew.add(saleCollectionNewSaleToAttach);
            }
            saleCollectionNew = attachedSaleCollectionNew;
            customer.setSaleCollection(saleCollectionNew);
            customer = em.merge(customer);
            for (Sale saleCollectionOldSale : saleCollectionOld) {
                if (!saleCollectionNew.contains(saleCollectionOldSale)) {
                    saleCollectionOldSale.setCustomerID(null);
                    saleCollectionOldSale = em.merge(saleCollectionOldSale);
                }
            }
            for (Sale saleCollectionNewSale : saleCollectionNew) {
                if (!saleCollectionOld.contains(saleCollectionNewSale)) {
                    Customer oldCustomerIDOfSaleCollectionNewSale = saleCollectionNewSale.getCustomerID();
                    saleCollectionNewSale.setCustomerID(customer);
                    saleCollectionNewSale = em.merge(saleCollectionNewSale);
                    if (oldCustomerIDOfSaleCollectionNewSale != null && !oldCustomerIDOfSaleCollectionNewSale.equals(customer)) {
                        oldCustomerIDOfSaleCollectionNewSale.getSaleCollection().remove(saleCollectionNewSale);
                        oldCustomerIDOfSaleCollectionNewSale = em.merge(oldCustomerIDOfSaleCollectionNewSale);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customer.getId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            Collection<Sale> saleCollection = customer.getSaleCollection();
            for (Sale saleCollectionSale : saleCollection) {
                saleCollectionSale.setCustomerID(null);
                saleCollectionSale = em.merge(saleCollectionSale);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
