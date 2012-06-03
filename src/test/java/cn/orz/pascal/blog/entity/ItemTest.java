/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.blog.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import javax.xml.bind.JAXB;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author hiro
 */
public class ItemTest {

    public ItemTest() {
    }

    @Test
    public void transrationTest() throws FileNotFoundException {
        Item item = new Item();
        item.setId(1L);
        item.setName("user name");
        item.setNumber(1024);
        item.setCreatedAt(new Date());
        item.setUpdatedAt(new Date());

        JAXB.marshal(item, new FileOutputStream("/tmp/b.xml"));
        Item item2 = JAXB.unmarshal(new FileInputStream("/tmp/b.xml"), Item.class);
        System.out.println(item2.toString());
    }
}
