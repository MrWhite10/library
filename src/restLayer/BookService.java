package restLayer;

import hibernateLayer.BookManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path ("/BookService")
public class BookService
{
	
	@POST
	@Path ("/books")
	@Consumes (MediaType.APPLICATION_JSON)
	public void createBook (Book book)
	{
		
		BookManager bm = new BookManager ();
		bm.addBook (book);
	}
	
	@GET
	@Path ("/books")
	@Consumes (MediaType.APPLICATION_JSON)
	public void getBooks ()
	{
		
		BookManager bm = new BookManager ();
		bm.printBooks ();
		
	}
	
	@DELETE
	@Path ("/books")
	@Consumes (MediaType.APPLICATION_JSON)
	public void deleteBooks (Book bookid)
	{
		
		BookManager bm = new BookManager ();
		bm.deleteBook (bookid);
	}
}
