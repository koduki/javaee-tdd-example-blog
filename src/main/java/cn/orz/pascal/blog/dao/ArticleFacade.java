/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.blog.dao;

import cn.orz.pascal.blog.entity.Article;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        List<Article> result = new ArrayList<Article>(size);
        for (int i = 90; i < 100; i++) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

                Article article = new Article(null, "title" + i, "contents" + i);
                article.setCreatedAt(df.parse("2012/04/1"));
                article.setUpdatedAt(df.parse((2012 + i) + "/04/1"));
                result.add(article);
            } catch (ParseException ex) {
                Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
