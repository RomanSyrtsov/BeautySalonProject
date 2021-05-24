package ua.kharkiv.rsyrtsov.filter;

import ua.kharkiv.rsyrtsov.controller.authorization.UserRoleRequestWrapper;
import ua.kharkiv.rsyrtsov.db.Role;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.utils.AppUtils;
import ua.kharkiv.rsyrtsov.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String servletPath = req.getServletPath() + "?"+req.getQueryString();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        User loginedUser = AppUtils.getLoginedUser(req.getSession());

        if (servletPath.equals("/controller?command=login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = req;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getLogin();

            // Роли (Role).
            List<String> roles = new ArrayList<>();
            roles.add(Role.getRole(loginedUser));

            // Старый пакет request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, req);
        }

        // Страницы требующие входа в систему.
        if (SecurityUtils.isSecurityPage(req)) {

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (loginedUser == null) {

                String requestUri = req.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(req.getSession(), requestUri);

                resp.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }
}
