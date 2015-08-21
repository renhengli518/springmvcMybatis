package commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCacheControlFilter implements Filter {

	private String ajax_header = "XMLHttpRequest";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String header = req.getHeader("X-Requested-With");
		if (header != null && header.equals(ajax_header))
			resp.addHeader("Cache-Control", "max-age=0");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {

	}
}
