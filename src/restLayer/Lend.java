package restLayer;

public class Lend
{
	private int id;

	private User user;
	private Book book;

	public Lend ()
	{

	}

	public Lend (int id)
	{

		this.id = id;
	}

	public int getId ()
	{

		return id;
	}

	public void setId (int id)
	{

		this.id = id;
	}

	public User getUser ()
	{

		return user;
	}

	public void setUser (User user)
	{

		this.user = user;
	}

	public Book getBook ()
	{

		return book;
	}

	public void setBook (Book book)
	{

		this.book = book;
	}
}
