package com.gruporosul.appmaquinaria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.R2;
import com.gruporosul.appmaquinaria.bean.control.ControlBody;
import com.gruporosul.appmaquinaria.retrofit.AppMaquinariaWebAPI;
import com.gruporosul.appmaquinaria.retrofit.ServiceGenerator;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlActivity extends AppCompatActivity {

    @BindView(R2.id.txtMaquina)
    TextView mDescriptionMaquina;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindString(R2.string.title_activity_control)
    String titleActivity;
    @BindView(R2.id.segmentedSistema)
    SegmentedGroup mSegmentedSistema;
    @BindView(R2.id.segmentedCilindro)
    SegmentedGroup mSegmentedCilindro;
    @BindView(R2.id.segmentedBomba)
    SegmentedGroup mSegmentedBomba;
    @BindView(R2.id.segmentedRodaje)
    SegmentedGroup mSegmentedRodaje;
    @BindView(R2.id.segmentedCuchilla)
    SegmentedGroup mSegmentedCuchilla;
    @BindView(R2.id.segmentedLlantas)
    SegmentedGroup mSegmentedLlantas;
    @BindView(R2.id.segmentedMangueras)
    SegmentedGroup mSegmentedMangueras;
    @BindView(R2.id.segmentedSillones)
    SegmentedGroup mSegmentedSillones;
    @BindView(R2.id.segmentedVidrios)
    SegmentedGroup mSegmentedVidrios;
    @BindView(R2.id.segmentedMotor)
    SegmentedGroup mSegmentedMotor;
    @BindString(R2.string.title_dialog)
    String titleDialog;
    @BindString(R2.string.hint_dialog)
    String hintDialog;
    private String result;
    private String comentariosSistema;
    private String comentariosCilindro;
    private String comentariosBomba;
    private String comentariosRodaje;
    private String comentariosCuchilla;
    private String comentariosLlantas;
    private String comentariosMangueras;
    private String comentariosSillones;
    private String comentariosVidrios;
    private String comentariosMotor;

    private AppMaquinariaWebAPI mAppMaquinariaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ButterKnife.bind(this);

        mAppMaquinariaAPI = ServiceGenerator.createService(AppMaquinariaWebAPI.class);

        setToolbar();

        Intent maquina = getIntent();
        mDescriptionMaquina.setText(maquina.getStringExtra("descripcion"));

        showDialogSegmentedGroup(mSegmentedSistema);
        showDialogSegmentedGroup(mSegmentedCilindro);
        showDialogSegmentedGroup(mSegmentedBomba);
        showDialogSegmentedGroup(mSegmentedRodaje);
        showDialogSegmentedGroup(mSegmentedCuchilla);
        showDialogSegmentedGroup(mSegmentedLlantas);
        showDialogSegmentedGroup(mSegmentedMangueras);
        showDialogSegmentedGroup(mSegmentedSillones);
        showDialogSegmentedGroup(mSegmentedVidrios);
        showDialogSegmentedGroup(mSegmentedMotor);

    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(titleActivity);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showDialogSegmentedGroup(final SegmentedGroup segmentedGroup) {
        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.e(ControlActivity.class.getSimpleName(), " tag: " + getResources().getResourceEntryName(segmentedGroup.getId()));
                View radioButton = group.findViewById(checkedId);
                int index = group.indexOfChild(radioButton);
                RadioButton rb = (RadioButton) group.getChildAt(index);
                if (!isNotAplicable(rb.getText().toString())) {
                    showInputDialog(segmentedGroup, rb.getText().toString());
                }
                Log.e(ControlActivity.class.getSimpleName(), " rb: " + rb.getText().toString());
            }
        });
    }

    private void showInputDialog(final SegmentedGroup segmentedGroup, final String rb) {
        new MaterialDialog.Builder(ControlActivity.this)
                .title(titleDialog)
                .positiveText("Aceptar")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(hintDialog, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        switch (getResources().getResourceEntryName(segmentedGroup.getId()).toUpperCase()) {
                            case "SEGMENTEDSISTEMA":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosSistema = input.toString();
                                    } else {
                                        comentariosSistema = rb;
                                    }
                                } else {
                                    comentariosSistema = "N/A";
                                }
                                break;
                            case "SEGMENTEDCILINDRO":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosCilindro = input.toString();
                                    } else {
                                        comentariosCilindro = rb;
                                    }
                                } else {
                                    comentariosCilindro = "N/A";
                                }
                                break;
                            case "SEGMENTEDBOMBA":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosBomba = input.toString();
                                    } else {
                                        comentariosBomba = rb;
                                    }
                                } else {
                                    comentariosBomba = "N/A";
                                }
                                break;
                            case "SEGMENTEDRODAJE":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosRodaje = input.toString();
                                    } else {
                                        comentariosRodaje = rb;
                                    }
                                } else {
                                    comentariosRodaje = "N/A";
                                }
                                break;
                            case "SEGMENTEDCUCHILLA":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosCuchilla = input.toString();
                                    } else {
                                        comentariosCuchilla = rb;
                                    }
                                } else {
                                    comentariosCuchilla = "N/A";
                                }
                                break;
                            case "SEGMENTEDLLANTAS":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosLlantas = input.toString();
                                    } else {
                                        comentariosLlantas = rb;
                                    }
                                } else {
                                    comentariosLlantas = "N/A";
                                }
                                break;
                            case "SEGMENTEDMANGUERAS":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosMangueras = input.toString();
                                    } else {
                                        comentariosMangueras = rb;
                                    }
                                } else {
                                    comentariosMangueras = "N/A";
                                }
                                break;
                            case "SEGMENTEDSILLONES":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosSillones = input.toString();
                                    } else {
                                        comentariosSillones = rb;
                                    }
                                } else {
                                    comentariosSillones = "N/A";
                                }
                                break;
                            case "SEGMENTEDVIDRIOS":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosVidrios = input.toString();
                                    } else {
                                        comentariosVidrios = rb;
                                    }
                                } else {
                                    comentariosVidrios = "N/A";
                                }
                                break;
                            case "SEGMENTEDMOTOR":
                                if (!isNotAplicable(rb)) {
                                    if (!input.toString().isEmpty()) {
                                        comentariosMotor = input.toString();
                                    } else {
                                        comentariosMotor = rb;
                                    }
                                } else {
                                    comentariosMotor = "N/A";
                                }
                                break;
                        }
                    }
                })
                .show();
    }

    private boolean isNotAplicable(String op) {
        return op.toUpperCase().equals("N/A");
    }

    @OnClick(R.id.btnEnviar)
    void OnClickEnviar() {
        Log.e(ControlActivity.class.getSimpleName(), " value: " + comentariosSistema);
        Log.e(ControlActivity.class.getSimpleName(), " value: " + comentariosMotor);
    }

    public void sendControlToWS(ControlBody controlBody) {
        Call<Void> postControl = mAppMaquinariaAPI.postChequeo(controlBody);
        postControl.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
