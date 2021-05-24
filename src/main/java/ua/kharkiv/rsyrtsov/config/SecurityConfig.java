package ua.kharkiv.rsyrtsov.config;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

    public class SecurityConfig {

        public static final String ROLE_CLIENT = "CLIENT";
        public static final String ROLE_MASTER = "MASTER";
        public static final String ROLE_ADMIN = "ADMIN";

        // String: Role
        // List<String>: urlPatterns.
        private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

        static {
            init();
        }

        private static void init() {

            // Конфигурация для роли "CLIENT".
            List<String> urlPatterns1 = new ArrayList<String>();

            urlPatterns1.add("/controller?command=record");
            //urlPatterns1.add("/services");

            mapConfig.put(ROLE_CLIENT, urlPatterns1);

            // Конфигурация для роли "MASTER".
            List<String> urlPatterns2 = new ArrayList<String>();

            urlPatterns2.add("/schedule");

            mapConfig.put(ROLE_MASTER, urlPatterns2);

            // Конфигурация для роли "ADMIN".
            List<String> urlPatterns3 = new ArrayList<String>();

            urlPatterns3.add("/recordslist");

            mapConfig.put(ROLE_ADMIN, urlPatterns3);
        }

        public static Set<String> getAllAppRoles() {
            return mapConfig.keySet();
        }

        public static List<String> getUrlPatternsForRole(String role) {
            return mapConfig.get(role);
        }

    }

