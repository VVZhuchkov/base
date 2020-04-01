package com.github.vvzhuchkov.dao.impl;

import com.github.vvzhuchkov.dao.AuthUserDao;
import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.model.Department;
import com.github.vvzhuchkov.model.Position;
import com.github.vvzhuchkov.model.Sphere;

import java.util.HashMap;
import java.util.Map;

public class DefaultAuthUserDao implements AuthUserDao {
    private Map<Integer, AuthUser> userById;
    public static volatile AuthUserDao instance;

    public DefaultAuthUserDao(){
        this.userById=new HashMap<Integer, AuthUser>();
        this.userById.putIfAbsent(5200005, new AuthUser(5200005, "5","Vladimir", "Zhuchkov", Department.OITS, Position.HEAD, Sphere.ALL));
        this.userById.putIfAbsent(5200030, new AuthUser(5200030, "30","Alexander", "Surovyev", Department.OMIA, Position.HEAD, Sphere.ALL));
        this.userById.putIfAbsent(5200015, new AuthUser(5200015, "15","Alexey", "Lopatin", Department.OITS, Position.ENGINEER, Sphere.DUMPER));
        this.userById.putIfAbsent(5200010, new AuthUser(5200010, "10","Boris", "Ketov", Department.OMIA, Position.ENGINEER, Sphere.CARGOPASS));
    }

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
                        synchronized (AuthUserDao.class) {
                            localInstance = instance;
                            if (localInstance == null) {
                                instance = localInstance = new DefaultAuthUserDao();
                }
            }
        }
        return localInstance;
    }

        @Override
        public Map<Integer, AuthUser> getEmployees(){
        return userById;
    }
}
