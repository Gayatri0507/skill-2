package com.example.Hibernate_crud;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        /* =========================
           INSERT PRODUCTS
           ========================= */
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

        tx.commit();
        System.out.println("Insertion successfully completed\n");

        /* =========================
           RETRIEVE PRODUCT BY ID
           ========================= */
        session.beginTransaction();
        Product product = session.get(Product.class, p1.getId());
        if (product != null) {
            System.out.println("Product Retrieved by ID:");
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        }
        session.getTransaction().commit();
        System.out.println();

        /* =========================
           UPDATE PRODUCT
           ========================= */
        session.beginTransaction();
        Product updateProduct = session.get(Product.class, p3.getId());
        if (updateProduct != null) {
            updateProduct.setPrice(150);
            updateProduct.setQuantity(300);
            session.update(updateProduct);

            System.out.println("Updated product details:");
            System.out.println("ID: " + updateProduct.getId());
            System.out.println("Name: " + updateProduct.getName());
            System.out.println("Price: " + updateProduct.getPrice());
            System.out.println("Quantity: " + updateProduct.getQuantity());
        }
        session.getTransaction().commit();
        System.out.println();

        /* =========================
           DELETE PRODUCT
           ========================= */
        session.beginTransaction();
        Product deleteProduct = session.get(Product.class, p2.getId());
        if (deleteProduct != null) {
            session.delete(deleteProduct);

            System.out.println("Product discontinued:");
            System.out.println("ID: " + deleteProduct.getId());
            System.out.println("Name: " + deleteProduct.getName());
            System.out.println("Product deleted successfully");
        }
        session.getTransaction().commit();

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}