package kpu.web.board.business;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kpu.web.board.persistence.FavcontentsDAO;

public class BoardTagHandler extends SimpleTagSupport {

	private String userSession;
	private String menu;
	private String bbsConfigFK;
	private String currentPage;
	
	public String getUserSession() {
		return userSession;
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
	
	public String getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public void doTag() throws IOException, JspException {
		JspWriter out = getJspContext().getOut();
		Document doc = null;
		
		try {
			String url = "";
			if(menu.equals("0")) {
				url = "http://www.kpu.ac.kr/front/boardlist.do?currentPage="+currentPage+"&menuGubun=1&siteGubun=14&bbsConfigFK="+bbsConfigFK+"&searchField=ALL&searchValue=&searchLowItem=ALL";
			}else if(menu.equals("1")) {
				url = "http://subweb.kpu.ac.kr/front/boardlist.do?currentPage="+currentPage+"&menuGubun=1&siteGubun=14&bbsConfigFK="+bbsConfigFK+"&searchField=ALL&searchValue=&searchLowItem=ALL";
			}
			doc = Jsoup.connect(url).get();
			//board.append(doc.toString());
			Elements table_head = doc.select("thead tr th");
			int cnt = 0;
			int writer_index = 0;
			int date_index = 0;
			for(Element table_th : table_head) {
				if(table_th.text().equals("작성자")) {
					writer_index = cnt;
				}else if(table_th.text().equals("등록일")) {
					date_index = cnt;
				}
				cnt++;
			}
			
			out.println("<div class=\"main-content container-fluid\">");
			out.println("<div class=\"page-title\">");
			out.print("<h3>");
			if(menu.equals("0")) {
				if(bbsConfigFK.equals("1")) {
					out.print("학사공지");
				}else if(bbsConfigFK.equals("357")) {
					out.print("일반공지");
				}else if(bbsConfigFK.equals("339")) {
					out.print("취업공지");
				}else if(bbsConfigFK.equals("494")) {
					out.print("장학공지");
				}else if(bbsConfigFK.equals("598")) {
					out.print("코로나 관련 공지");
				}
			}else if(menu.equals("1")) {
				if(bbsConfigFK.equals("21")) {
					out.print("게임공학부 공지");
				}else if(bbsConfigFK.equals("10")) {
					out.print("경영학부 공지");
				}else if(bbsConfigFK.equals("37")) {
					out.print("기계공학과 공지");
				}else if(bbsConfigFK.equals("45")) {
					out.print("기계설계공학과 공지");
				}else if(bbsConfigFK.equals("53")) {
					out.print("나노반도체공학과 공지");
				}else if(bbsConfigFK.equals("4")) {
					out.print("디자인학부 공지");
				}else if(bbsConfigFK.equals("61")) {
					out.print("메카트로닉스공학과 공지");
				}else if(bbsConfigFK.equals("77")) {
					out.print("생명화학공학과 공지");
				}else if(bbsConfigFK.equals("85")) {
					out.print("신소재공학과 공지");
				}else if(bbsConfigFK.equals("309")) {
					out.print("에너지,전기공학과 공지");
				}else if(bbsConfigFK.equals("93")) {
					out.print("전자공학부 공지");
				}else if(bbsConfigFK.equals("1")) {
					out.print("컴퓨터공학부 공지");
				}
			}
			out.println("</h3>");
			out.println("</div>");
			out.println("<section class=\"section\">");
			out.println("<div class=\"row mb-3\">");
			out.println("<div class=\"col-md-12\">");
			out.println("<div class=\"card\">");
			out.println("<div class=\"card-body px-0 pb-0\">");
			out.println("<div class=\"table-responsive\">");
			out.println("<table class='table mb-10' id=\"table1\">");
			out.println("<thead>");
			out.println("<tr align=\"center\">");
			if(!userSession.equals("null")) {
				out.println("<th></th>");
			}
			out.println("<th>제목</th>");
			out.println("<th>작성자</th>");
			out.println("<th>등록일</th>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			
			Elements table_all = doc.select("tbody tr");
			for (Element table_row : table_all) {
				if(table_row.selectFirst("span").text().equals("공지")) {
					continue;
				}
				Element title_element = table_row.selectFirst("a");
				String[] result = title_element.absUrl("href").split("=");
				String title = title_element.text();
				String pkid = result[result.length-1];
				out.println("<tr>");
				if(!userSession.equals("null")) {
					FavcontentsDAO favcontentsdao = new FavcontentsDAO();
					boolean favcontentschk = favcontentsdao.favchk(userSession, menu, pkid, bbsConfigFK);
					out.println("<td align=\"center\">");
					if(favcontentschk) {
						out.println("<form name=\"fav"+title_element.elementSiblingIndex()+"\" action=\"FavcontentsServlet?cmd=favdel\" method=\"post\">");
					}else {
						out.println("<form name=\"fav"+title_element.elementSiblingIndex()+"\" action=\"FavcontentsServlet?cmd=favadd\" method=\"post\">");
					}
					out.println("<input type=\"hidden\" name=\"userSession\" value="+userSession+">");
					out.println("<input type=\"hidden\" name=\"menu\" value="+menu+">");
					out.println("<input type=\"hidden\" name=\"pkid\" value="+pkid+">");
					out.println("<input type=\"hidden\" name=\"bbsConfigFK\" value="+bbsConfigFK+">");
					out.println("<input type=\"hidden\" name=\"title\" value='"+title+"'>");
					if(favcontentschk) {
						out.println("<input type=\"image\" src=\"resources/css/icons/staractive.svg\" name=\"submit \" value=\"submit\">");
					}else {
						out.println("<input type=\"image\" src=\"resources/css/icons/star.svg\" name=\"submit \" value=\"submit\">");
					}
					out.println("</form>");
					out.println("</td>");
				}
				out.println("<td><a href=\"content.jsp?menu="+menu+"&pkid="+pkid+"&bbsConfigFK="+bbsConfigFK+"\">"+title+"</a></td>");
				out.println("<td align=\"center\">"+table_row.select("td").get(writer_index).text()+"</td>");
				out.println("<td align=\"center\">"+table_row.select("td").get(date_index).text()+"</td>");
				out.println("</tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
			out.println("</div>");

			String[] pagination_element = doc.selectFirst(".raquo").attr("onClick").split("'");
			String last_page = pagination_element[pagination_element.length-2];
			int start_page = 1;
			int page_length = currentPage.length();
			if(page_length==1) {
				start_page = 0;
			}else if(page_length==2) {
				if(Integer.parseInt(currentPage.substring(1,2))==0) {
					start_page = Integer.parseInt((Integer.parseInt(currentPage.substring(0,1))-1)+"0");
				}else {
					start_page = Integer.parseInt(currentPage.substring(0,1)+"0");
				}
			}else if(page_length==3) {
				if(Integer.parseInt(currentPage.substring(2,3))==0) {
					start_page = Integer.parseInt((Integer.parseInt(currentPage.substring(0,2))-10)+"0");
				}else {
					start_page = Integer.parseInt(currentPage.substring(0,2)+"0");
				}
			}
			out.println("<nav>");
			out.println("<ul class=\"pagination pagination-primary justify-content-center\">");
			out.println("<li class=\"page-item\"><a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage=1\"><span aria-hidden=\"true\"><img class=\"m-0 p-0\" src=\"resources/css/icons/chevrons-left.svg\" width=\"16\"/></span></a></li>");
			out.println("<li class=\"page-item\">");
			if(start_page==0) {
				out.println("<a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage=1\">");
			}else {
				out.println("<a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage="+(start_page-9)+"\">");
			}
			out.println("<span aria-hidden=\"true\"><i data-feather=\"chevron-left\"></i></span>");
			out.println("</a>");
			out.println("</li>");
			for(int i=1; i<11; i++) {
				if((start_page+i)>Integer.parseInt(last_page)) {
					break;
				}
				out.print("<li class=\"page-item");
				if(Integer.parseInt(currentPage)==(start_page+i)) {
					out.print(" active");
				}
				out.println("\"><a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage="+(start_page+i)+"\">"+(start_page+i)+"</a></li>");
			}
			out.println("<li class=\"page-item\">");
			if((start_page+11)>Integer.parseInt(last_page)) {
				out.println("<a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage="+last_page+"\">");
			}else {
				out.println("<a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage="+(start_page+11)+"\">");
			}
			out.println("<span aria-hidden=\"true\"><i data-feather=\"chevron-right\"></i></span>");
			out.println("</a>");
			out.println("</li>");
			out.println("<li class=\"page-item\"><a class=\"page-link\" href=\"index.jsp?menu="+menu+"&bbsConfigFK="+bbsConfigFK+"&currentPage="+last_page+"\"><span aria-hidden=\"true\"><img class=\"m-0 p-0\" src=\"resources/css/icons/chevrons-right.svg\" width=\"16\"/></span></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</section>");
			out.println("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
