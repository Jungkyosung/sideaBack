package sidea.version.nudge.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //요청 주소에 /something이 있다면 로그인 페이지로 리다이렉트

        if (request.getRequestURI().indexOf("/something") >= 0 && request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "로그인 후 사용하실 수 있습니다.");
            response.sendRedirect("/login");
            return false;
        } else {
            return true;
        }
    }
}
