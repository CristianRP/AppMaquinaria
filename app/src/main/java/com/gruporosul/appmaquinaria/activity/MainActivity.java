package com.gruporosul.appmaquinaria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gruporosul.appmaquinaria.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String app_name;

    public static final int CONTROL = 0;
    public static final int REPARACIONES = 1;
    public static final int SERVICIOS = 2;
    public static final int RENDIMIENTOS = 3;
    public static final String MODULO = "MODULO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();

    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnRepairs)
    void repairsModule() {
        Intent repairs = new Intent(MainActivity.this, ListOfMachineryActivity.class);
        repairs.putExtra(MODULO, REPARACIONES);
        startActivity(repairs);
    }

    @OnClick(R.id.btnCheckEnginery)
    void checkModule() {
        Intent control = new Intent(MainActivity.this, ResumenMaquinaActivity.class);
        control.putExtra(MODULO, CONTROL);
        startActivity(control);
    }

    @OnClick(R.id.btnPerformance)
    void performanceModule() {
        Intent perfomance = new Intent(MainActivity.this, ListOfMachineryActivity.class);
        perfomance.putExtra(MODULO, RENDIMIENTOS);
        startActivity(perfomance);
    }

    @OnClick(R.id.btnServices)
    void servicesModule() {
        Intent services = new Intent(MainActivity.this, ListOfMachineryActivity.class);
        services.putExtra(MODULO, SERVICIOS);
        startActivity(services);
    }
}
