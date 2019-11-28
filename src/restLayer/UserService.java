package restLayer;

import hibernateLayer.UserManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

@Path ("/UserService")
public class UserService
{
	
	@POST
	@Path ("/users")
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public void createUser (User user)
	{
		
		UserManager um = new UserManager ();
		um.addUser (user);
	}
	
	@GET
	@Path ("/users")
	@Consumes (MediaType.APPLICATION_JSON)
	public void getUsers ()
	{
		
		UserManager um = new UserManager ();
		um.printUsers ();
	}
	
	@DELETE
	@Path ("/users")
	@Consumes (MediaType.APPLICATION_JSON)
	public void deleteUser (User userid)
	{
		
		UserManager um = new UserManager ();
		um.deleteUser (userid);
		
	}
	
}
