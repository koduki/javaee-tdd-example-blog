/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.blog.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author hiro
 */
public class AbstractJPATest {

    public AbstractJPATest() {
    }
    @PersistenceContext
    EntityManager em;
    @Inject
    UserTransaction utx;

    /**
     * delete entites record.
     *
     * @param <T> entity type.
     * @param entity entity type class.
     * @throws Exception
     */
    protected <T> void clearData(Class<T> entity) throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from " + entity.getSimpleName()).executeUpdate();
        utx.commit();
    }

    /**
     * simle list sort by property.
     *
     * @param <T> entity type.
     * @param list base list.
     * @param property sort key property name.
     * @return sorted new list.
     */
    protected <T> List<T> simpleSort(List<T> list, final String property) {
        List<T> result = new ArrayList<T>(list);

        Collections.sort(result, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                try {
                    Method m = o1.getClass().getMethod("get" + property, null);
                    Comparable value1 = (Comparable) m.invoke(o1, null);
                    Comparable value2 = (Comparable) m.invoke(o2, null);

                    return value1.compareTo(value2);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (SecurityException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalArgumentException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return result;
    }
}
