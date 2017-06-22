package com.gruporosul.appmaquinaria.bean.login;

/**
 * Created by Cristian Ramírez on 19/06/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class SupervisorBody {
    private String username;
    private String password;

    public SupervisorBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
