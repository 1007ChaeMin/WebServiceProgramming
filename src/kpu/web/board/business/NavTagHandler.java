package kpu.web.board.business;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NavTagHandler extends SimpleTagSupport {
	
	private String userSession;
	private String menu;
	private String bbsConfigFK;
	
	public String getUserSession() {
		return bbsConfigFK;
	}
	
	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public String getBbsConfigFK() {
		return bbsConfigFK;
	}
	
	public void setBbsConfigFK(String bbsConfigFK) {
		this.bbsConfigFK = bbsConfigFK;
	}
	
	public void doTag() throws IOException, JspException {
		JspWriter out = getJspContext().getOut();
		
		out.println("<div id=\"sidebar\"");
		if(!bbsConfigFK.equals("-1")) {
			out.println("class='active'");
		}
		out.println(">");
		out.println("<div class=\"sidebar-wrapper active\">");
		
		out.println("<a href=\"index.jsp\">");
		out.println("<div class=\"sidebar-header pl-4 pt-4\">");
		out.println("<img src=\"resources/css/icons/kpu.gif\" style=\"object-fit:contain\" alt=\"\">");
		out.println("</div>");
		out.println("</a>");
		
		out.println("<div class=\"sidebar-menu\">");
		out.println("<ul class=\"menu\">");
		
		out.println("<li class='sidebar-title pt-0'>Main Menu</li>");
		out.print("<li class=\"sidebar-item"); if(menu.equals("0")&&bbsConfigFK.equals("1")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"index.jsp?menu=0&bbsConfigFK=1&currentPage=1\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>학사공지</span>");
		out.println("</a>");
		out.println("</li>");
		out.print("<li class=\"sidebar-item"); if(menu.equals("0")&&bbsConfigFK.equals("357")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"index.jsp?menu=0&bbsConfigFK=357&currentPage=1\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>일반공지</span>");
		out.println("</a>");
		out.println("</li>");
		out.print("<li class=\"sidebar-item"); if(menu.equals("0")&&bbsConfigFK.equals("339")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"index.jsp?menu=0&bbsConfigFK=339&currentPage=1\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>취업공지</span>");
		out.println("</a>");
		out.println("</li>");
		out.print("<li class=\"sidebar-item"); if(menu.equals("0")&&bbsConfigFK.equals("494")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"index.jsp?menu=0&bbsConfigFK=494&currentPage=1\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>장학공지</span>");
		out.println("</a>");
		out.println("</li>");
		out.print("<li class=\"sidebar-item"); if(menu.equals("0")&&bbsConfigFK.equals("598")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"index.jsp?menu=0&bbsConfigFK=598&currentPage=1\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>코로나 관련 공지</span>");
		out.println("</a>");
		out.println("</li>");
		
		out.println("<li class='sidebar-title'>User Menu</li>");
		
		if(!userSession.equals("null")) {
			out.print("<li class=\"sidebar-item"); if(menu.equals("2")) {out.print(" active" );} out.println("\">");
			out.println("<a href=\"FavcontentsServlet?cmd=favcontents\" class='sidebar-link'>");
			out.println("<i data-feather=\"star\" width=\"20\"></i>");
			out.println("<span>즐겨찾기</span>");
			out.println("</a>");
			out.println("</li>");
		}
		out.println("<li class=\"sidebar-item has-sub"); if(menu.equals("1")){ out.print(" active");} out.println("\">");
		out.println("<a href=\"#\" class='sidebar-link'>");
		out.println("<i data-feather=\"file-text\" width=\"20\"></i>");
		out.println("<span>학과 공지</span>");
		out.println("</a>");
		out.println("<ul class=\"submenu"); if(menu.equals("1")){ out.print(" active");} out.println("\">");
		
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=21&currentPage=1\">게임공학부</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=10&currentPage=1\">경영학부</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=37&currentPage=1\">기계공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=45&currentPage=1\">기계설계공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=53&currentPage=1\">나노반도체공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=4&currentPage=1\">디자인학부</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=61&currentPage=1\">메카트로닉스공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=77&currentPage=1\">생명화학공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=85&currentPage=1\">신소재공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=309&currentPage=1\">에너지,전기공학과</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=93&currentPage=1\">전자공학부</a>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href=\"index.jsp?menu=1&bbsConfigFK=1&currentPage=1\">컴퓨터공학부</a>");
		out.println("</li>");
		
		out.println("</ul>");
		out.println("</div>");
		out.println("<button class=\"sidebar-toggler btn x\"><i data-feather=\"x\"></i></button>");
		out.println("</div>");
		out.println("</div>");
	}
}
