package restLayer;

import hibernateLayer.LendManager;
import hibernateLayer.UserManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path ("/LendService")
public class LendService
{
	@POST
	@Path ("/lends")
	@Consumes (MediaType.APPLICATION_JSON)
	public void createLends (@QueryParam ("userid") Integer userId , @QueryParam ("bookid") Integer bookId)
	{

		LendManager lm = new LendManager ();
		lm.addLend (userId , bookId);
		System.out.println (userId + "   " + bookId);
	}
	
	@GET
	@Path ("/lends")
	@Consumes (MediaType.APPLICATION_JSON)
	public void getLends ()
	{
		LendManager lm = new LendManager ();
		lm.printLends ();
	}

	@DELETE
	@Path ("/lends")
	@Consumes (MediaType.APPLICATION_JSON)
	public void deleteUser (Lend lendID)
	{

		LendManager lm = new LendManager ();
		lm.deleteLend (lendID);
	}

}
