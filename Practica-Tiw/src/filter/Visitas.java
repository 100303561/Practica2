package filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
*  ContadorVisitas
*/
@WebFilter("/Index.jsp")
public class Visitas implements Filter {
  private int contador;

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // increase counter by one
    contador++;

    // Imprimimos contador
   
    ServletContext app = request.getServletContext();
	app.setAttribute("contador", contador);
    //siguiente 
    chain.doFilter(request, response);
  }

  public void init(FilterConfig fConfig) throws ServletException {
    //reset
    contador = 1500;
  }
}

//en el jsp debemos hacer El numero de visitas del producto es:  <%=request.getAttribute("counter")%>