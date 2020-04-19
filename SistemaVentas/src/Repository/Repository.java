/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entity.BaseEntity;

/**
 *
 * @author juanc
 * @param <T>
 */
public interface Repository<T extends BaseEntity> {

    T find(int id);

    void create(T entity);

    void destroy(int id);

    void edit(T entity);
}
