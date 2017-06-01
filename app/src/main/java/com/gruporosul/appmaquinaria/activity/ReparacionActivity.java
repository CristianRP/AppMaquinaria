package com.gruporosul.appmaquinaria.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.R2;
import com.gruporosul.appmaquinaria.bean.Reparacion;
import com.gruporosul.appmaquinaria.bean.ReparacionBody;
import com.gruporosul.appmaquinaria.retrofit.AppMaquinariaWebAPI;
import com.gruporosul.appmaquinaria.retrofit.ServiceGenerator;
import com.philliphsu.bottomsheetpickers.date.BottomSheetDatePickerDialog;
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReparacionActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener {

    @BindView(R2.id.tvDiagnostico)
    TextView mDiagnostico;
    @BindView(R2.id.tvDiasAsignados)
    TextView mDiasAsignados;
    @BindView(R2.id.tvFechaFin)
    TextView mFechaFin;
    @BindView(R2.id.tvEstado)
    TextView mEstado;
    @BindView(R2.id.tvFechaInicio)
    TextView mFechaInicio;
    @BindView(R2.id.tvFechaParalizacion)
    TextView mFechaParalizacion;
    @BindView(R2.id.tvFecha)
    TextView mFecha;
    @BindView(R2.id.tvNombreMaquina)
    TextView mNombreMaquina;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.cardViewIngresoDatos)
    CardView mCardViewIngresoDatos;
    @BindView(R2.id.btnNew)
    Button mBtnIngresoDatos;
    @BindString(R2.string.title_activity_reparacion)
    String title_activity;
    @BindView(R.id.txlFechaParalizacion)
    TextInputLayout mFechaParalizacionText;
    @BindView(R.id.tlInicioReparacion)
    TextInputLayout mInicioReparacionText;
    @BindView(R.id.tlEstado)
    TextInputLayout mEstadoText;
    @BindView(R.id.tlFechaFin)
    TextInputLayout mFechaFinText;
    @BindView(R.id.tlDiasAsignados)
    TextInputLayout mDiasAsignadosText;
    @BindView(R.id.tlDiagnostico)
    TextInputLayout mDiagnosticoText;
    @BindView(R.id.cardViewLastRepair)
    CardView mCardViewLastRepair;
    @BindView(R.id.tvTitleCardLastRepair)
    TextView mTitleCardLastRepair;
    @BindView(R.id.constraintLayoutMain)
    ConstraintLayout mConstraintLayoutMain;
    @BindView(R.id.tvTitleNuevo)
    TextView mTitleNuevo;
    @BindView(R.id.guideLineRepair)
    Guideline mGuideLineRepair;

    private AppMaquinariaWebAPI mMaquinariaAPI;
    private ProgressDialog mProgressDialog;
    private int idReparacion;
    private int idMaq;
    private int button_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparacion);

        ButterKnife.bind(this);

        setToolbar();

        mMaquinariaAPI = ServiceGenerator.createService(AppMaquinariaWebAPI.class);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Obteniendo listado de maquinas...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        Intent listOfMachinery = getIntent();
        getLastRepair(listOfMachinery.getIntExtra("idMaquina", 0));

        mFechaParalizacionText.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_value = 0;
                createBottomSheet();
            }
        });

        mInicioReparacionText.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_value = 1;
                createBottomSheet();
            }
        });

        mFechaFinText.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_value = 2;
                createBottomSheet();
            }
        });

    }

    private BottomSheetDatePickerDialog createBottomSheet() {
        Calendar now = Calendar.getInstance();
        BottomSheetDatePickerDialog dialog = BottomSheetDatePickerDialog.newInstance(
                ReparacionActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, 10);
        dialog.setMaxDate(max);
        dialog.setYearRange(1970, 2032);
        dialog.show(getSupportFragmentManager(), ReparacionActivity.class.getSimpleName());
        return dialog;
    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(title_activity);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getLastRepair(final int idMaquina) {
        final Call<Reparacion> getRepair = mMaquinariaAPI.getListOfReparaciones(idMaquina);
        getRepair.enqueue(new Callback<Reparacion>() {
            @Override
            public void onResponse(Call<Reparacion> call, Response<Reparacion> response) {
                String error = "Ha ocurrido un problema. Contacte con el administrador";
                mProgressDialog.dismiss();
                if (!response.isSuccessful()) {
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
                    Toast.makeText(ReparacionActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(ListOfMachineryActivity.class.getSimpleName(), error);
                    return;
                }

                Log.e(ListOfMachineryActivity.class.getSimpleName() + " Success", "Listado reparacion obtenido");

                setDataReparacion(response.body());
                idReparacion = response.body().getIdReparacion();
                idMaq = response.body().getIdMaquina();

            }

            @Override
            public void onFailure(Call<Reparacion> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.e("failure", t.getMessage());
                ingresoDatosHideViews();
                idMaq = getIntent().getIntExtra("idMaquina", 0);
            }
        });
    }

    private void setDataReparacion(Reparacion reparacion) {
        mNombreMaquina.setText(reparacion.getNombreMaquina());
        mFecha.setText(String.valueOf(reparacion.getFecha()));
        mFecha.setText(String.valueOf(reparacion.getFechaParalizacion()));
        mFechaInicio.setText(String.valueOf(reparacion.getFechaInicio()));
        mFechaFin.setText(String.valueOf(reparacion.getFechaFin()));
        mEstado.setText(reparacion.getEstado());
        mDiasAsignados.setText(String.valueOf(reparacion.getDiasAsignados()));
        mDiagnostico.setText(reparacion.getDiagnostico());
    }

    @OnClick(R.id.btnNew)
    void OnClickIngresarDatos() {
        ingresoDatosHideViews();
    }

    public void ingresoDatosHideViews() {
        setTitle("Ingreso de datos");
        mBtnIngresoDatos.setVisibility(View.GONE);
        mCardViewLastRepair.setVisibility(View.GONE);
        mTitleCardLastRepair.setVisibility(View.GONE);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraintLayoutMain);
        constraintSet.setGuidelinePercent(mGuideLineRepair.getId(), 0f);
        constraintSet.applyTo(mConstraintLayoutMain);
        mCardViewIngresoDatos.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnCancel)
    void OnClickCancelDatos() {
        setTitle(title_activity);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraintLayoutMain);
        constraintSet.setGuidelinePercent(mGuideLineRepair.getId(), 50f);
        constraintSet.applyTo(mConstraintLayoutMain);
        mTitleCardLastRepair.setVisibility(View.VISIBLE);
        mBtnIngresoDatos.setVisibility(View.VISIBLE);
        mCardViewLastRepair.setVisibility(View.VISIBLE);
        mCardViewIngresoDatos.setVisibility(View.GONE);
        mTitleNuevo.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnSaveData)
    void OnClickSaveData() {
        final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        new MaterialDialog.Builder(this)
                .title("Grabar datos?")
                .content("Quieres guardar los datos de la reparación?")
                .positiveText("Grabar")
                .negativeText("Cancelar")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ReparacionBody reparacion = null;
                        try {
                            reparacion = new ReparacionBody(
                                    format.parse(mFechaParalizacionText.getEditText().getText().toString()),
                                    format.parse(mInicioReparacionText.getEditText().getText().toString()),
                                    mEstadoText.getEditText().getText().toString(),
                                    format.parse(mFechaFinText.getEditText().getText().toString()),
                                    Integer.parseInt(mDiasAsignadosText.getEditText().getText().toString()),
                                    idMaq,
                                    mDiagnosticoText.getEditText().getText().toString()
                            );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        onPostData(reparacion);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void onPostData(ReparacionBody reparacionBody) {
        Call<Void> postDatosReparacion = mMaquinariaAPI.postReparacion(reparacionBody);
        postDatosReparacion.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(ReparacionActivity.class.getSimpleName(), error);
                    } else {
                        // Errores no relacionados con el API
                        try {
                            Log.e(ReparacionActivity.class.getSimpleName(), response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(ReparacionActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(ReparacionActivity.class.getSimpleName(), error);
                    return;
                }
                Log.e(ListOfMachineryActivity.class.getSimpleName() + " Success", "Envio de datos completo");
                Toast.makeText(ReparacionActivity.this, "Datos grabados con éxito!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ReparacionActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(ReparacionActivity.class.getSimpleName() + " Fail: ", "" + t.getMessage());
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        switch (button_value) {
            case 0:
                mFechaParalizacionText.getEditText().setText(android.text.format.DateFormat.getDateFormat(this).format(cal.getTime()));
                break;
            case 1:
                mInicioReparacionText.getEditText().setText(android.text.format.DateFormat.getDateFormat(this).format(cal.getTime()));
                break;
            case 2:
                mFechaFinText.getEditText().setText(android.text.format.DateFormat.getDateFormat(this).format(cal.getTime()));
                break;
        }
    }
}
