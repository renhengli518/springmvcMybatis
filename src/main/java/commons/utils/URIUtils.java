package commons.utils;

import javax.servlet.http.HttpServletRequest;

public class URIUtils {

	public static String getFullUri(HttpServletRequest request) {
		/*String uri = request.getScheme() + "://" + request.getServerName() + 
				(("http".equals(request.getScheme()) && request.getServerPort() == 80) || ("https".equals(request.getScheme()) && request.getServerPort() == 443) ? "" : ":" + request.getServerPort()) + 
				request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");*/
		String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
		return uri;
	}
}
