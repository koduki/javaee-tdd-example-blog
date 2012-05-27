/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.blog.dao;

import cn.orz.pascal.blog.entity.Article;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 *
 * @author hiro
 */
@RunWith(Arquillian.class)
public class ArticleFacadeTest extends AbstractJPATest {

    public ArticleFacadeTest() {
    }

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war").
                addPackage(Article.class.getPackage()).
                addPackage(ArticleFacade.class.getPackage()).
                addAsResource("META-INF/persistence.xml").
                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    ArticleFacade articleFacade;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData(Article.class);
    }

    private void load() throws Exception {
        articleFacade.create(new Article(null, "title1", "contents1"));
        articleFacade.create(new Article(null, "title2", "contents2"));
    }

    @Test
    public void count0_Test() throws Exception {
        assertThat(articleFacade.count(), is(0));
    }

    @Test
    public void save_and_select_Test() throws Exception {
        utx.begin();
        // init and check.
        assertThat(articleFacade.count(), is(0));

        // action.
        articleFacade.create(new Article(null, "title1", "contents1"));
        assertThat(articleFacade.count(), is(1));

        articleFacade.create(new Article(null, "title2", "contents2"));
        assertThat(articleFacade.count(), is(2));

        // expected.
        List<Article> articles = simpleSort(articleFacade.findAll(), "Title");
        assertThat(articles.size(), is(2));
        assertThat(articles.get(0).getTitle(), is("title1"));
        assertThat(articles.get(0).getContents(), is("contents1"));
        assertThat(articles.get(1).getTitle(), is("title2"));
        assertThat(articles.get(1).getContents(), is("contents2"));
        utx.commit();
    }

    @Test
    public void update_Test() throws Exception {
        utx.begin();
        // init and check.
        load();
        List<Article> articles = simpleSort(articleFacade.findAll(), "Title");
        assertThat(articles.size(), is(2));
        assertThat(articles.get(0).getTitle(), is("title1"));
        assertThat(articles.get(1).getTitle(), is("title2"));

        // action
        articles.get(0).setTitle("title3");
        articleFacade.edit(articles.get(0));
        articleFacade.edit(new Article(null, "title4", "contents4"));

        // expected
        List<Article> updatedArticles = simpleSort(articleFacade.findAll(), "Title");
        assertThat(updatedArticles.size(), is(3));
        assertThat(updatedArticles.get(0).getTitle(), is("title2"));
        assertThat(updatedArticles.get(1).getTitle(), is("title3"));
        assertThat(updatedArticles.get(2).getTitle(), is("title4"));
        utx.commit();
    }
}