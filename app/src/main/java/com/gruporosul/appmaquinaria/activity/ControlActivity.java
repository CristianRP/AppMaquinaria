package com.gruporosul.appmaquinaria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.R2;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class ControlActivity extends AppCompatActivity {

    @BindView(R2.id.txtMaquina)
    TextView mDescriptionMaquina;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindString(R2.string.title_activity_control)
    String titleActivity;
    @BindView(R.id.segmentedSistema)
    SegmentedGroup mSegmentedSistema;
    @BindView(R.id.segmentedCilindro)
    SegmentedGroup mSegmentedCilindro;
    @BindView(R.id.segmentedBomba)
    SegmentedGroup mSegmentedBomba;
    @BindView(R.id.segmentedRodaje)
    SegmentedGroup mSegmentedRodaje;
    @BindView(R.id.segmentedCuchilla)
    SegmentedGroup mSegmentedCuchilla;
    @BindView(R.id.segmentedLlantas)
    SegmentedGroup mSegmentedLlantas;
    @BindView(R.id.segmentedMangueras)
    SegmentedGroup mSegmentedMangueras;
    @BindView(R.id.segmentedSillones)
    SegmentedGroup mSegmentedSillones;
    @BindView(R.id.segmentedVidrios)
    SegmentedGroup mSegmentedVidrios;
    @BindView(R.id.segmentedMotor)
    SegmentedGroup mSegmentedMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ButterKnife.bind(this);

        setToolbar();

        Intent maquina = getIntent();
        mDescriptionMaquina.setText(maquina.getStringExtra("descripcion"));

    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(titleActivity);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showDialogSegmentedGroup(SegmentedGroup segmentedGroup) {
        //if (segmentedGroup.getCheckedRadioButtonId())
    }

}
