package com.example.Hibernate_crud;

import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        /* =========================
           CLEAR OLD DATA
           ========================= */
        Transaction tx = session.beginTransaction();
        session.createQuery("DELETE FROM Product").executeUpdate();
        tx.commit();

        /* =========================
           INSERT PRODUCTS
           ========================= */
        tx = session.beginTransaction();

        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setDescription("Gaming Laptop");
        p1.setPrice(72000);
        p1.setQuantity(8);
        session.save(p1);

        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setDescription("Wireless Mouse");
        p2.setPrice(500);
        p2.setQuantity(20);
        session.save(p2);

        Product p3 = new Product();
        p3.setName("Earphones");
        p3.setDescription("Wired Earphones");
        p3.setPrice(200);
        p3.setQuantity(100);
        session.save(p3);

        Product p4 = new Product();
        p4.setName("Keyboard");
        p4.setDescription("Mechanical Keyboard");
        p4.setPrice(1500);
        p4.setQuantity(15);
        session.save(p4);

        Product p5 = new Product();
        p5.setName("Monitor");
        p5.setDescription("LED Monitor");
        p5.setPrice(12000);
        p5.setQuantity(8);
        session.save(p5);

        Product p6 = new Product();
        p6.setName("USB Drive");
        p6.setDescription("32GB USB Drive");
        p6.setPrice(800);
        p6.setQuantity(30);
        session.save(p6);

        Product p7 = new Product();
        p7.setName("Webcam");
        p7.setDescription("HD Webcam");
        p7.setPrice(2500);
        p7.setQuantity(12);
        session.save(p7);

        tx.commit();
        System.out.println("Products inserted successfully\n");

        /* ==============================
           PRICE ASCENDING
           ============================== */
        tx = session.beginTransaction();

        Query qAsc = session.createQuery(
                "FROM Product p ORDER BY p.price ASC");

        List<Product> ascList = qAsc.getResultList();

        System.out.println("Products sorted by price (Ascending):");
        for (Product p : ascList) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }

        tx.commit();
        System.out.println();

        /* ==============================
           NAME CONTAINING 'lap'
           ============================== */
        tx = session.beginTransaction();

        Query qLike = session.createQuery(
                "FROM Product p WHERE lower(p.name) LIKE :key");
        qLike.setParameter("key", "%lap%");

        List<Product> lapList = qLike.getResultList();

        System.out.println("Products with name containing 'lap':");
        for (Product p : lapList) {
            System.out.println(p.getName());
        }

        tx.commit();
        System.out.println();

        /* ==============================
           TOTAL COUNT
           ============================== */
        tx = session.beginTransaction();

        Query qCount = session.createQuery(
                "SELECT COUNT(p) FROM Product p");

        long count = (long) qCount.getSingleResult();

        System.out.println("Total number of products: " + count);

        tx.commit();

        session.close();
        HibernateUtil.getSessionFactory().close();

        System.out.println("\nOperations completed successfully");
    }
}
