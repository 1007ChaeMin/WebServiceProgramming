package kpu.web.board.business;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HeaderTagHandler extends SimpleTagSupport {
	
	private String userSession;
	
	public String getUserSession() {
		return userSession;
	}
	
	public void setUserSession(String user_session) {
		this.userSession = user_session;
	}
	
	public void doTag() throws IOException, JspException {
		JspWriter out = getJspContext().getOut();
		out.println("<nav class=\"navbar navbar-header navbar-expand navbar-light\">");
		out.println("<a class=\"sidebar-toggler\" href=\"#\"><span class=\"navbar-toggler-icon\"></span></a>");
		out.println("<button class=\"btn navbar-toggler\" type=\"button\" data-toggle=\"collapse\"");
		out.println("data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
		out.println("aria-label=\"Toggle navigation\">");
		out.println("<span class=\"navbar-toggler-icon\"></span>");
		out.println("</button>");
		if(userSession.equals("null")) {
			out.println("<div class=\"navbar-nav d-flex align-items-center navbar-light ml-auto\">");
			out.println("<form style=\"display:inline\" action=\"MemberServlet?cmd=signin\" method=\"post\">");
			out.println("<input style=\"display:inline; width:25%\" type=\"text\" name=\"id\" class=\"form-control round m-1\" placeholder=\"ID\">");
			out.println("<input style=\"display:inline; width:25%\"type=\"password\" name=\"passwd\" class=\"form-control round m-1\" placeholder=\"Password\">");
			out.println("<input style=\"display:inline; width:20%\"type=\"submit\" class=\"btn btn-outline-primary round m-1\" value=\"Sign-In\">");
			out.println("<a href=\"MemberServlet?cmd=signup\"><button style=\"display:inline; width:20%\" type=\"button\" class=\"btn btn-outline-success round m-1\">Sign-Up</button></a>");
			out.println("</form>");
			out.println("</div>");
		}else {
			out.println("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
			out.println("<ul class=\"navbar-nav d-flex align-items-center navbar-light ml-auto\">");
			out.println("<li class=\"dropdown\">");
			out.println("<a href=\"#\" data-toggle=\"dropdown\"");
			out.println(" class=\"nav-link dropdown-toggle nav-link-lg nav-link-user\">");
			out.println("<div class=\"d-none d-md-block d-lg-inline-block\"><u>"+userSession+"님</u>, 환영합니다.</div>");
			out.println("</a>");
			out.println("<div class=\"dropdown-menu dropdown-menu-right\">");
			out.println("<a class=\"dropdown-item\" href=\"MemberServlet?cmd=account\"><i data-feather=\"user\"></i> Account</a>");
			out.println("<div class=\"dropdown-divider\"></div>");
			out.println("<a class=\"dropdown-item\" href=\"MemberServlet?cmd=logout\"><i data-feather=\"log-out\"></i> Logout</a>");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
			out.println("</div>");
		}
		out.println("</nav>");
	}
}
