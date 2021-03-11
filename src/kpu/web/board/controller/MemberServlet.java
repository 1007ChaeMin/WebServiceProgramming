package kpu.web.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kpu.web.board.domain.MemberVO;
import kpu.web.board.persistence.MemberDAO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		HttpSession session = request.getSession();
				
		if (cmdReq.equals("signup")) {
			response.sendRedirect("register.jsp");
		}else if(session.getAttribute("userSession")==null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용해주세요.'); history.back();</script>");
		}else if (cmdReq.equals("logout")) {
			session.removeAttribute("userSession");
			response.sendRedirect("index.jsp");
		}else if (cmdReq.equals("account")) {
			MemberDAO memberDao = new MemberDAO();
			MemberVO memberVO = memberDao.info((String)session.getAttribute("userSession"));
			
			request.setAttribute("member", memberVO);
			
			RequestDispatcher view = request.getRequestDispatcher("account.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String message = "";
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		HttpSession session = request.getSession();
		
		if (cmdReq.equals("signin")) {
			MemberDAO memberDao = new MemberDAO();
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			if (memberDao.signin(id, passwd)) {
				session.setAttribute("userSession", id);
				
				response.sendRedirect("index.jsp");
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}else if (cmdReq.equals("signup")) {
			MemberVO memberVO = new MemberVO();

			memberVO.setId(request.getParameter("id"));
			memberVO.setPasswd(request.getParameter("passwd"));
			memberVO.setUsername(request.getParameter("username"));
			memberVO.setSnum(request.getParameter("snum"));
			memberVO.setEmail(request.getParameter("email"));
			memberVO.setDepart(request.getParameter("depart"));

			MemberDAO memberDao = new MemberDAO();
			if (memberDao.signup(memberVO)) {
				message = memberVO.getUsername()+"님, 가입 축하합니다.";
				request.setAttribute("message", message);

				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('가입 실패입니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}else if (cmdReq.equals("account")) {
			MemberVO memberVO = new MemberVO();

			memberVO.setId(request.getParameter("id"));
			memberVO.setPasswd(request.getParameter("passwd"));
			memberVO.setUsername(request.getParameter("username"));
			memberVO.setSnum(request.getParameter("snum"));
			memberVO.setEmail(request.getParameter("email"));
			memberVO.setDepart(request.getParameter("depart"));

			MemberDAO memberDao = new MemberDAO();
			if (memberDao.update(memberVO)) {
				message = memberVO.getUsername()+"님, 수정이 완료되었습니다.";
				request.setAttribute("message", message);
				
				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정을 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}else if (cmdReq.equals("del")) {
			MemberDAO memberDao = new MemberDAO();
			if (memberDao.delete((String)session.getAttribute("userSession"))) {
				message = "회원탈퇴가 완료되었습니다.";
				session.removeAttribute("userSession");
				request.setAttribute("message", message);
				
				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원탈퇴에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
			}
		}
	}
}
