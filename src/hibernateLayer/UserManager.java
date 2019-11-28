package hibernateLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import restLayer.User;

public class UserManager
{

	public static SessionFactory factory;

	public UserManager () // for DB Connector
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

	/* Method to Add the User */
	public void addUser (User pUser)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{

			session.save (pUser);
			System.out.println ("ID: " + pUser.getId () + " , Name:  " + pUser.getName () + " , Family:  "
					+ pUser.getfamily () + " Saved in DataBase");
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

	/* Method to Print all the Users */
	public void printUsers ()
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List users = session.createQuery ("FROM User").list ();
			for (Iterator iterator = users.iterator () ; iterator.hasNext () ;)
			{
				User user = (User) iterator.next ();
				System.out.print ("ID: " + user.getId () + "          ");
				System.out.print ("		First Name: " + user.getName () + "               ");
				System.out.print ("		Last Name: " + user.getfamily ());
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

	/* Method to READ all the Users */
	public List <User> getUsers ()
	{

		List <User> userList = null;
		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List users = session.createQuery ("FROM User").list ();
			userList = new ArrayList <User> ();
			for (Iterator iterator = users.iterator () ; iterator.hasNext () ;)
			{
				User user = (User) iterator.next ();
				userList.add (user);

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

		return userList;
	}

	/* Method to Delete one User */

	public void deleteUser (User user)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			User userID = (User) session.get (User.class , user.getId ());
			session.delete (userID);
			tx.commit ();
			System.out.println ("The ID : " + user.getId () + " Deleted");
			printUsers ();
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
