/**
 * 
 */
package ddc.bm.api;

import ddc.bm.app.AppTask;
import ddc.bm.app.Auth;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author dellecave
 */
@Path("/api/login")
public class Login {
	@Context
	private ServletContext context;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	@Context
	private HttpHeaders headers;
	@Context
	ContainerResponseContext responseContext;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticateUserByGet(@QueryParam("u") final String username, @QueryParam("p") final String password) {
		return doAuthenticate(username, password);
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticateUserByPost(@FormParam("u") String username, @FormParam("p") String password) {
		return doAuthenticate(username, password);
	}

	private Response doAuthenticate(final String username, final String password) {
		try {
//			Objects.requireNonNull(username);
//			Objects.requireNonNull(password);
			AppTask<String, String> a = new AppTask<String, String>() {
				@Override
				protected String run(String domain) throws Throwable {
					return Auth.getToken(domain, username, password);
				}
			};
			a.execute(request.getServerName());
			response.addCookie(new Cookie("token", a.getResult()));
			// response.addHeader(HttpHeaders.AUTHORIZATION, a.getResult());
			return ApiHelper.instance().build(a);

		} catch (Throwable e) {
			return Response.status(Response.Status.UNAUTHORIZED.ordinal(), "Unauthorized").build();
		}
	}

}