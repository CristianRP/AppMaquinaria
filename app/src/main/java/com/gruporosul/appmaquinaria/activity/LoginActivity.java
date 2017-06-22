package com.gruporosul.appmaquinaria.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.bean.login.SupervisorBody;
import com.gruporosul.appmaquinaria.bean.login.SupervisorResponse;
import com.gruporosul.appmaquinaria.retrofit.AppMaquinariaWebAPI;
import com.gruporosul.appmaquinaria.retrofit.ServiceGenerator;
import com.gruporosul.appmaquinaria.util.GlideApp;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.backgroundLogin)
    ImageView mBackgroundLogin;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private AppMaquinariaWebAPI mMaquinariaAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMaquinariaAPI = ServiceGenerator.createService(AppMaquinariaWebAPI.class);

        GlideApp
                .with(this)
                .load(R.drawable.login_screen)
                .into(mBackgroundLogin);

    }

    @OnClick(R.id.btnLogin)
    void OnClickLogin() {
        SupervisorBody supervisorBody = new SupervisorBody(etUserName.getText().toString(),
                etPassword.getText().toString());
        loginRequest(supervisorBody);
    }

    private void loginRequest(SupervisorBody supervisorBody) {
        Call<SupervisorResponse> getSupervisorResponse = mMaquinariaAPI.getLoginResponse(supervisorBody.getUsername(), supervisorBody.getPassword());
        getSupervisorResponse.enqueue(new Callback<SupervisorResponse>() {
            @Override
            public void onResponse(Call<SupervisorResponse> call, Response<SupervisorResponse> response) {
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte con el administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(ListOfMachineryActivity.class.getSimpleName(), error);
                    } else {
                        try {
                            // Errores no relacionados con el API
                            Log.e(ListOfMachineryActivity.class.getSimpleName(), response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(LoginActivity.class.getSimpleName(), error);
                    return;
                }

                SupervisorResponse supervisor = response.body();

                Log.e(LoginActivity.class.getSimpleName(), " " + supervisor.getNombre());


            }

            @Override
            public void onFailure(Call<SupervisorResponse> call, Throwable t) {
                Log.e(LoginActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

}
