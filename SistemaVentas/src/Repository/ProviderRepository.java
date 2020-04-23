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
import Entity.Provider;
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
public class ProviderRepository implements Serializable {

    public ProviderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provider provider) {
        if (provider.getProductCollection() == null) {
            provider.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : provider.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            provider.setProductCollection(attachedProductCollection);
            em.persist(provider);
            for (Product productCollectionProduct : provider.getProductCollection()) {
                Provider oldProviderIDOfProductCollectionProduct = productCollectionProduct.getProviderID();
                productCollectionProduct.setProviderID(provider);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldProviderIDOfProductCollectionProduct != null) {
                    oldProviderIDOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldProviderIDOfProductCollectionProduct = em.merge(oldProviderIDOfProductCollectionProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provider provider) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provider persistentProvider = em.find(Provider.class, provider.getId());
            Collection<Product> productCollectionOld = persistentProvider.getProductCollection();
            Collection<Product> productCollectionNew = provider.getProductCollection();
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            provider.setProductCollection(productCollectionNew);
            provider = em.merge(provider);
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setProviderID(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Provider oldProviderIDOfProductCollectionNewProduct = productCollectionNewProduct.getProviderID();
                    productCollectionNewProduct.setProviderID(provider);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldProviderIDOfProductCollectionNewProduct != null && !oldProviderIDOfProductCollectionNewProduct.equals(provider)) {
                        oldProviderIDOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldProviderIDOfProductCollectionNewProduct = em.merge(oldProviderIDOfProductCollectionNewProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provider.getId();
                if (findProvider(id) == null) {
                    throw new NonexistentEntityException("The provider with id " + id + " no longer exists.");
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
            Provider provider;
            try {
                provider = em.getReference(Provider.class, id);
                provider.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provider with id " + id + " no longer exists.", enfe);
            }
            Collection<Product> productCollection = provider.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setProviderID(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(provider);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provider> findProviderEntities() {
        return findProviderEntities(true, -1, -1);
    }

    public List<Provider> findProviderEntities(int maxResults, int firstResult) {
        return findProviderEntities(false, maxResults, firstResult);
    }

    private List<Provider> findProviderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provider.class));
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

    public Provider findProvider(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provider.class, id);
        } finally {
            em.close();
        }
    }

    public int getProviderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provider> rt = cq.from(Provider.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
