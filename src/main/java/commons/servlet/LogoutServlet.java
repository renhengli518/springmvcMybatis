package commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public class LogoutServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		String callback = request.getParameter("callback");
		//System.out.println("callback=" + callback);
		if (StringUtils.isNotBlank(callback)){
			response.getWriter().write(callback + "(true)");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
