package com.greatlearning.crmapp_demo.service;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.greatlearning.crmapp_demo.entity.Customer;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



@Repository
public class CustomerServicecrm implements CustomerService {


	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	CustomerServicecrm(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}


	}





	@Transactional
	public List<Customer> findAll() {
		//		Session session;
		//
		//		try {
		//		    session = sessionFactory.getCurrentSession();
		//		} catch (HibernateException e) {
		//		    session = sessionFactory.openSession();
		//		}
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Customer> customers=session.createQuery("from Customer").list();

		tx.commit();


		return customers;
	}

	@Transactional
	public Customer findById(int id) {

		Customer customer = new Customer();

		//		Session session;
		//
		//		try {
		//		    session = sessionFactory.getCurrentSession();
		//		} catch (HibernateException e) {
		//		    session = sessionFactory.openSession();
		//		}
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, id);

		tx.commit();


		return customer;
	}

	@Transactional
	public void save(Customer theCustomer) {

		//		Session session;
		//
		//		try {
		//		    session = sessionFactory.getCurrentSession();
		//		} catch (HibernateException e) {
		//		    session = sessionFactory.openSession();
		//		}
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theCustomer);


		tx.commit();


	}
	@Transactional
	public void deleteById(int id) {

		//		Session session;
		//
		//		try {
		//		    session = sessionFactory.getCurrentSession();
		//		} catch (HibernateException e) {
		//		    session = sessionFactory.openSession();
		//		}
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();

	}

}