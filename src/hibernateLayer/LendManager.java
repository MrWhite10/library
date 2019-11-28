package hibernateLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import restLayer.Book;
import restLayer.Lend;
import restLayer.User;

public class LendManager
{
	public static SessionFactory factory;

	public LendManager ()
	{

		try
		{
			Configuration con = new Configuration ();
			con.configure ("hibernate.cfg.xml");
			factory = con.buildSessionFactory ();
		}
		catch (Throwable ex)
		{
			System.err.println ("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError (ex);
		}
	}

	/* Method to Add the Lend */
	public void addLend (int userID , int bookID)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			Lend l = new Lend ();
			User user = (User) session.get (User.class , userID);
			Book book = (Book) session.get (Book.class , bookID);
			l.setUser (user);
			l.setBook (book);
			session.save (l);
			System.out.println ("ID: " + l.getId () + " Saved in DataBase");
			tx.commit ();
			session.flush ();

		}

		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback ();
			e.printStackTrace ();
		}
		finally
		{
			session.close ();
		}
	}

	/* Method to Print all the Land */
	public void printLends ()
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List lends = session.createQuery ("FROM Lend").list ();
			for (Iterator iterator = lends.iterator () ; iterator.hasNext () ;)
			{
				Lend lend = (Lend) iterator.next ();
				System.out.print ("ID: " + lend.getId () + "          ");
				System.out.print ("		Book ID : " + (lend.getBook ()).getId () + "               ");
				System.out.print ("		User ID : " + (lend.getUser ().getId ()));
				System.out.println ();

			}
			tx.commit ();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback ();
			e.printStackTrace ();
		}
		finally
		{
			session.close ();
		}

	}

	/* Method to READ all the Lends */
	public List <Lend> getLends ()
	{

		List <Lend> lensdList = null;
		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List lends = session.createQuery ("FROM Lend").list ();
			lensdList = new ArrayList <Lend> ();
			for (Iterator iterator = lends.iterator () ; iterator.hasNext () ;)
			{
				Lend lend = (Lend) iterator.next ();
				lensdList.add (lend);

			}
			tx.commit ();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback ();
			e.printStackTrace ();
		}
		finally
		{
			session.close ();
		}

		return lensdList;
	}

	/* Method to Delete one Lend */

	public void deleteLend (Lend lend)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			Lend lendID = (Lend) session.get (Lend.class , lend.getId ());
			session.delete (lendID);
			tx.commit ();
			System.out.println ("The ID : " + lend.getId () + " Deleted");
			printLends ();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback ();
			e.printStackTrace ();
		}
		finally
		{
			session.close ();
		}
	}
}
