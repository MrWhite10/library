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

public class BookManager
{
	public static SessionFactory factory;

	public BookManager ()
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

	/* Method to Add the Book */
	public void addBook (Book book)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{

			session.save (book);
			System.out.println ("ID: " + book.getId () + " , Name:  " + book.getName () + " , Family:  "
					+ book.getAuthor () + " Saved in DataBase");
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

	/* Method to Print all the Books */
	public void printBooks ()
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List books = session.createQuery ("FROM Book").list ();
			// userList = new ArrayList<User>();
			for (Iterator iterator = books.iterator () ; iterator.hasNext () ;)
			{
				Book book = (Book) iterator.next ();
				System.out.print ("ID: " + book.getId () + "          ");
				System.out.print ("		First Name: " + book.getName () + "               ");
				System.out.print ("		Author: " + book.getAuthor ());
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

		// return userList;
	}

	/* Method to READ all the Books */
	public List <Book> getUsers ()
	{

		List <Book> bookList = null;
		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			List books = session.createQuery ("FROM Book").list ();
			bookList = new ArrayList <Book> ();
			for (Iterator iterator = books.iterator () ; iterator.hasNext () ;)
			{
				Book book = (Book) iterator.next ();
				bookList.add (book);

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

		return bookList;
	}

	/* Method to Delete one Book */

	public void deleteBook (Book book)
	{

		Session session = factory.openSession ();
		Transaction tx = session.beginTransaction ();
		try
		{
			Book bookID = (Book) session.get (Book.class , book.getId ());
			session.delete (bookID);
			tx.commit ();
			System.out.println ("The ID : " + book.getId () + " Deleted");
			printBooks ();
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
