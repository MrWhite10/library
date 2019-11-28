package restLayer;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{

	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String family;

	private Set <Lend> lend;

	public User ()
	{

	}

	public User (int id , String name , String family)
	{

		this.id = id;
		this.name = name;
		this.family = family;
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

	public String getfamily ()
	{

		return family;
	}

	public void setfamily (String family)
	{

		this.family = family;
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
