package org.xinyo.util;

import javax.servlet.http.HttpSession;

import static org.xinyo.WebSecurityConfig.SESSION_KEY;

public class AuthUtils {

    public static boolean canEdit(HttpSession session){
        if (session.getAttribute(SESSION_KEY) != null)
            return true;
        return false;
    }
}
