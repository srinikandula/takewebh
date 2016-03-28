package com.web.h;

/**
 * Created by skandula on 3/27/16.
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;


public class ManageStock {
    public static void main(String[] args) {

        creteStock();
        //findStock();

    }

    private static void creteStock() {
        System.out.println("Hibernate many to many (XML Mapping)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");

        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        session.save(stock);

        session.getTransaction().commit();
        System.out.println("Done");
    }
    public static void findStock(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query =  session.createQuery("From Stock");
        List<Stock> stocks = query.list();
        for(Stock stock:stocks){
            System.out.println(stock.getStockCode());
            Set<Category> categories =  stock.getCategories();
            System.out.println("catrgories size" +categories.size());
        }
        session.getTransaction().commit();
        System.out.println("Done");
    }

}