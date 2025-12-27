package com.example.Hibernate_crud;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAO {

    // INSERT
    public void saveProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();
        session.close();
    }

    // UPDATE
    public void updateProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(p);

        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            session.delete(p);
        }

        tx.commit();
        session.close();
    }

    // READ (LIST)
    public List<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Product> list =
                session.createQuery("from Product", Product.class).list();

        session.close();
        return list;
    }

	public Product getProductById1(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
