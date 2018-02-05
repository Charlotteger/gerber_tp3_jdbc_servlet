package tp1;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleExampleServlet extends HttpServlet {

	private int count;
 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log("init() called");
		count=0;
	}
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log("doGet() called");
		count++;
		response.getWriter().write("Incrementig the count: Count = "+count);
	}
 
	@Override
	public void destroy() {
		log("destroy() called");
	}	
 
}