package com.gruporosul.appmaquinaria.retrofit;

import com.gruporosul.appmaquinaria.bean.Maquina;
import com.gruporosul.appmaquinaria.bean.Reparacion;
import com.gruporosul.appmaquinaria.bean.ReparacionBody;
import com.gruporosul.appmaquinaria.bean.Tipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Cristian Ramírez on 16/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public interface AppMaquinariaWebAPI {

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

}
