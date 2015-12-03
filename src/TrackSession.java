

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TrackSession
 */
@WebServlet("/TrackSession")
public class TrackSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	     // Create a session object if it is already not  created.
	      HttpSession session = request.getSession(true);
	      // Get session creation time.
	      Date createdTime = new Date(session.getCreationTime());
	      // Get last access time of this web page.
	      Date lastAccessTime = 
	                        new Date(session.getLastAccessedTime());

	      String title = "-----------Welcome Back to my website-----------";
	      Integer visitCount = new Integer(0);
	      String visitCountKey = new String("visitCount");
	      String userIDKey = new String("userID");
	      String userID = new String("ABCD");

	      // Check if this is new visitor.
	      if (session.isNew()){
	         title = "Welcome to my website";
	         session.setAttribute(userIDKey, userID);
	      } else {
	         visitCount = (Integer)session.getAttribute(visitCountKey);
	         visitCount = visitCount + 1;
	         userID = (String)session.getAttribute(userIDKey);
	      }
	      session.setAttribute(visitCountKey,  visitCount);

	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      
	      // Some other http/user information
	      Locale userPreferredLocale = request.getLocale();
	      Enumeration<Locale> userPreferredLocales = request.getLocales();
	      String userAgent = request.getHeader("User-Agent");
	      String ipaddress = request.getRemoteAddr();

	      
	      out.println(title + "<br/>");     
	      out.println("The Session is created at: " + createdTime + "<br/>");
	      out.println("Session ID: " + session.getId() + "<br/>");
	      out.println("You have visited this page " + visitCount + " times" + "<br/>");
	      out.println("You visited this page at " + lastAccessTime + "<br/>");
	      out.println("Your Locale is: " + userPreferredLocale.toString() + "<br/>");
	      out.println("Your Browser is: " + userAgent + "<br/>");
	      out.println("You IP address is: " + ipaddress + "<br/>");		      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
