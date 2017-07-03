package com.gruporosul.appmaquinaria.retrofit;

import com.gruporosul.appmaquinaria.bean.control.ControlBody;
import com.gruporosul.appmaquinaria.bean.Maquina;
import com.gruporosul.appmaquinaria.bean.Reparacion;
import com.gruporosul.appmaquinaria.bean.ReparacionBody;
import com.gruporosul.appmaquinaria.bean.Tipo;
import com.gruporosul.appmaquinaria.bean.login.SupervisorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cristian Ramírez on 16/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public interface AppMaquinariaWebAPI {

    @GET("api/Supervisor")
    Call<SupervisorResponse> getLoginResponse(@Query("username") String username, @Query("password") String password);

    @GET("api/Maquinaria")
    Call<List<Maquina>> getListOfMaquinaria();

    @GET("api/Tipo")
    Call<List<Tipo>> getListOfType();

    @GET("api/Reparacion/{id}")
    Call<Reparacion> getListOfReparaciones(
            @Path("id") int id
    );

    @POST("api/Reparacion")
    Call<Void> postReparacion(@Body ReparacionBody reparacionBody);

    @POST("api/Chequeo")
    Call<Void> postChequeo(@Body ControlBody controlBody);

    @GET("api/Maquinaria")
    Call<Maquina> getMaquinaEscaneada(@Query("codigoMaquina") String codigoMaquina);

}
