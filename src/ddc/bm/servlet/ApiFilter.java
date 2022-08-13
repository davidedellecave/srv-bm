package ddc.bm.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


public class ApiFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
//		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		
		Map<String, Object> filterContext = new HashMap<>();
		processBefore(filterContext, request, response);
		chain.doFilter(request, response);
		processAfter(filterContext, request, response);
	}
	
	
	private void processBefore(Map<String, Object> ctx, ServletRequest request, ServletResponse response) {
//		Environment.setDomain(request.getServerName());
		
//		ctx.put("chron", cron);
	}

	private void processAfter(Map<String, Object> ctx, ServletRequest request, ServletResponse response) {
//		Chronometer cron = (Chronometer) ctx.get("chron");
//		logger.info("Http elapsed:[" + cron.toString() + "]");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}