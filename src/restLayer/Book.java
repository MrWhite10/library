package restLayer;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book
{
	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String author;

	private Set <Lend> lend;

	public Book ()
	{
		
	}

	public int getId ()
	{

		return id;
	}

	public void setId (int id)
	{

		this.id = id;
	}

	public String getName ()
	{

		return name;
	}

	public void setName (String name)
	{

		this.name = name;
	}

	public String getAuthor ()
	{

		return author;
	}

	public void setAuthor (String author)
	{

		this.author = author;
	}

	public Set <Lend> getLend ()
	{

		return lend;
	}

	public void setLend (Set <Lend> lend)
	{

		this.lend = lend;
	}

}
