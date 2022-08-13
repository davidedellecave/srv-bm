package ddc.bm.api;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ddc.bm.app.FeatureName;
import ddc.bm.app.SecurityAnnotation.SecureFeature;
import jakarta.annotation.Priority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@SecureFeature({})
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Context
	private SecurityContext securityContext;
	@Context
	private HttpServletRequest request;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// // Get the resource class which matches with the requested URL
		// // Extract the roles declared by it
//		Class<?> resourceClass = resourceInfo.getResourceClass();
//		List<FeatureName> list = extractFeatures(resourceClass);
//		for (FeatureName f : list) {
//			if (!Auth.instance().isFeatureEnabled("tenant", securityContext.getUserPrincipal().getName(), f.toString())) {
//				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//			}
//		}
		//
		// // Get the resource method which matches with the requested URL
		// // Extract the roles declared by it
		// Method resourceMethod = resourceInfo.getResourceMethod();
		// List<Role> methodRoles = extractRoles(resourceMethod);
		//
		// try {
		//
		// // Check if the user is allowed to execute the method
		// // The method annotations override the class annotations
		// if (methodRoles.isEmpty()) {
		// checkPermissions(classRoles);
		// } else {
		// checkPermissions(methodRoles);
		// }
		//
		// } catch (Exception e) {
		// requestContext.abortWith(
		// Response.status(Response.Status.FORBIDDEN).build());
		// }
	}

	// Extract the roles from the annotated element
	private List<FeatureName> extractFeatures(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return new ArrayList<FeatureName>();
		} else {
			SecureFeature secured = annotatedElement.getAnnotation(SecureFeature.class);
			if (secured == null) {
				return new ArrayList<FeatureName>();
			} else {
				FeatureName[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}

	// private void checkPermissions(List<Role> allowedRoles) throws Exception {
	// // Check if the user contains one of the allowed roles
	// // Throw an Exception if the user has not permission to execute the
	// method
	// }
}