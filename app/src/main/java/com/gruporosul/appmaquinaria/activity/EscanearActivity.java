package com.gruporosul.appmaquinaria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.bean.Maquina;
import com.gruporosul.appmaquinaria.retrofit.AppMaquinariaWebAPI;
import com.gruporosul.appmaquinaria.retrofit.ServiceGenerator;
import com.gruporosul.appmaquinaria.util.Constants;

import java.io.IOException;

import butterknife.BindString;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscanearActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    @BindString(R.string.loading_maquinas)
    String loadingText;
    private ZBarScannerView mScannerView;
    private AppMaquinariaWebAPI mMaquinariaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        mScannerView.startCamera();
        setContentView(mScannerView);
        mMaquinariaAPI = ServiceGenerator.createService(AppMaquinariaWebAPI.class);
    }

    @Override
    public void handleResult(Result result) {
        Log.v(ResumenMaquinaActivity.class.getSimpleName(), result.getContents()); // Prints scan results
        Log.v(ResumenMaquinaActivity.class.getSimpleName(), result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);

        getMaquina(result.getContents());
        Constants.showDialog(this, loadingText);
    }

    private void getMaquina(String codigoMaquina) {
        Call<Maquina> getMaquinaByCodigo = mMaquinariaAPI.getMaquinaEscaneada(codigoMaquina);
        getMaquinaByCodigo.enqueue(new Callback<Maquina>() {
            @Override
            public void onResponse(Call<Maquina> call, Response<Maquina> response) {
                Constants.dismissDialog();
                if (!response.isSuccessful()) {
                    String error = "Maquina no encontrada o codigo no registrado. Consulte el inventario de maquinaria.";
                    if (response.raw().code() == 404) {
                        Toast.makeText(EscanearActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.e(LoginActivity.class.getSimpleName(), error);
                        return;
                    }
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(EscanearActivity.class.getSimpleName(), error);
                    } else {
                        try {
                            // Errores no relacionados con el API
                            Log.e(EscanearActivity.class.getSimpleName(), response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(EscanearActivity.this, error, Toast.LENGTH_SHORT).show();

                    //Log.e(LoginActivity.class.getSimpleName(), " " +);
                    Log.e(LoginActivity.class.getSimpleName(), error);
                    return;
                }
                Maquina mMaquina = response.body();
                Log.e(LoginActivity.class.getSimpleName(), " " + mMaquina.getTipo());

                Intent resumen = new Intent(EscanearActivity.this, ControlActivity.class);
                resumen.putExtra("descripcion", mMaquina.getTipo());
                startActivity(resumen);
            }

            @Override
            public void onFailure(Call<Maquina> call, Throwable t) {
                Log.e(LoginActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

}
