package acciones;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession misession = (HttpSession) request.getSession();
		
		if (misession.getAttribute("admin") != null) {
			// invalidamos sesion y redirigir a login admin
			request.getSession().invalidate();
			response.sendRedirect("LoginAdmin.jsp");
		} else {
			// invalidamos sesion y redirigir a login admin
			request.getSession().invalidate();
			response.sendRedirect("Login.jsp");
		}
		

	}

}
