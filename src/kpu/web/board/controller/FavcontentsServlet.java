package kpu.web.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kpu.web.board.domain.FavcontentsVO;
import kpu.web.board.persistence.FavcontentsDAO;

/**
 * Servlet implementation class FavcontentsServlet
 */
@WebServlet("/FavcontentsServlet")
public class FavcontentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		if (cmdReq.equals("favadd")) {
			FavcontentsDAO favcontentsdao = new FavcontentsDAO();
			String userSession = (String) request.getParameter("userSession");
			String menu = (String) request.getParameter("menu");
			String pkid = (String) request.getParameter("pkid");
			String bbsConfigFK = (String) request.getParameter("bbsConfigFK");
			String title = (String) request.getParameter("title");
			
			if(favcontentsdao.favadd(userSession, menu, pkid, bbsConfigFK, title)) {
				response.sendRedirect(request.getHeader("referer"));
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('즐겨찾기 추가에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}else if(cmdReq.equals("favdel")) {
			FavcontentsDAO favcontentsdao = new FavcontentsDAO();
			String userSession = (String) request.getParameter("userSession");
			String menu = (String) request.getParameter("menu");
			String pkid = (String) request.getParameter("pkid");
			String bbsConfigFK = (String) request.getParameter("bbsConfigFK");
			
			if(favcontentsdao.favdel(userSession, menu, pkid, bbsConfigFK)) {
				response.sendRedirect(request.getHeader("referer"));
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('즐겨찾기 삭제에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}else if (cmdReq.equals("favcontents")) {
			FavcontentsDAO favcontentsdao = new FavcontentsDAO();
			HttpSession session = request.getSession();
			ArrayList<FavcontentsVO> favlist = favcontentsdao.getfavlist((String)session.getAttribute("userSession"));
			request.setAttribute("favlist", favlist);
			
			RequestDispatcher view = request.getRequestDispatcher("favcontents.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
