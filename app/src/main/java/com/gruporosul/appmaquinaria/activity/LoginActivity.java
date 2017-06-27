package com.gruporosul.appmaquinaria.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.R2;
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
    @BindView(R2.id.etPassword)
    EditText etPassword;
    @BindView(R2.id.etUserName)
    EditText etUserName;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private AppMaquinariaWebAPI mMaquinariaAPI;
    private String[] permissions = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };
    int PERMISSION_ALL = 1;

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

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_ALL);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @OnClick(R.id.btnLogin)
    void OnClickLogin() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        /*Log.e("Login:", "  " + etPassword.getText().toString());
        SupervisorBody supervisorBody = new SupervisorBody(etUserName.getText().toString(),
                Encryptor.encrypt(etPassword.getText().toString(), Constants.ENCRYPTOR_KEY));
        Log.e("Login:", " Encrypted: " + Encryptor.encrypt(etPassword.getText().toString(), Constants.ENCRYPTOR_KEY));
        Log.e("Login: ", " Decrypted: " + Encryptor.decrypt(Encryptor.encrypt(etPassword.getText().toString(), Constants.ENCRYPTOR_KEY), Constants.ENCRYPTOR_KEY));
        loginRequest(supervisorBody);*/
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
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    return;
                }

                SupervisorResponse supervisor = response.body();

                Log.e(LoginActivity.class.getSimpleName(), " " + supervisor.getNombre());

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }

            @Override
            public void onFailure(Call<SupervisorResponse> call, Throwable t) {
                Log.e(LoginActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

}
