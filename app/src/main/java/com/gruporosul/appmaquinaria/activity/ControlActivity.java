package com.gruporosul.appmaquinaria.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.R2;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ControlActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindString(R2.string.title_activity_control)
    String titleActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ButterKnife.bind(this);

        setToolbar();

    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(titleActivity);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
