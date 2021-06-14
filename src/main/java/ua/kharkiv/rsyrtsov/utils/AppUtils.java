package ua.kharkiv.rsyrtsov.utils;

import ua.kharkiv.rsyrtsov.db.model.User;

import javax.servlet.http.HttpSession;

public class AppUtils {

    // Сохранить информацию пользователя в Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        // На JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Получить информацию пользователя, сохраненную в Session.
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

}
