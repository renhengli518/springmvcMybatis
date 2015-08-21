package commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SecurityFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		SecurityWrapper wrapper = null;
		try {// 过滤html标签
			wrapper = new SecurityWrapper(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (wrapper != null) {
			chain.doFilter(wrapper, resp);
		} else {
			chain.doFilter(req, resp);
		}

	}

	public void init(FilterConfig config) throws ServletException {
	}
}
