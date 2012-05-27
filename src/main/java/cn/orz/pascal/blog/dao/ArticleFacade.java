/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.blog.dao;

import cn.orz.pascal.blog.entity.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hiro
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "cn.orz.pascal_Blog_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    public List<Article> findRecently(int size) {
        String jpql = "SELECT a FROM Article a ORDER BY a.updatedAt DESC";
        TypedQuery<Article> query = em.createQuery(jpql, Article.class).
                setMaxResults(size);

        return query.getResultList();
    }
}
