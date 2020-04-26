package filter;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/main")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forward("login", rq, ((HttpServletResponse) servletResponse));
        }
        filterChain.doFilter(rq, servletResponse);
    }
}
