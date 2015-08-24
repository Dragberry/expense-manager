package net.dragberry.expman.rs.webservice;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class TestService {
	
	@GET
	@Produces(value = "text/plain")
	public String testService() {
		return "test service";
	}
	
	@GET
	@Path("/list")
	@Produces("text/xml")
	public List<String> getList() {
		return Arrays.asList(new String[] {"One", "Two", "Three"});
	}

}
