package com.gruporosul.appmaquinaria.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.adapter.MaquinariaAdapter;
import com.gruporosul.appmaquinaria.bean.Maquina;
import com.gruporosul.appmaquinaria.bean.Tipo;
import com.gruporosul.appmaquinaria.retrofit.AppMaquinariaWebAPI;
import com.gruporosul.appmaquinaria.retrofit.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gruporosul.appmaquinaria.activity.MainActivity.CONTROL;
import static com.gruporosul.appmaquinaria.activity.MainActivity.MODULO;
import static com.gruporosul.appmaquinaria.activity.MainActivity.RENDIMIENTOS;
import static com.gruporosul.appmaquinaria.activity.MainActivity.REPARACIONES;
import static com.gruporosul.appmaquinaria.activity.MainActivity.SERVICIOS;


public class ListOfMachineryActivity extends AppCompatActivity
        implements MaquinariaAdapter.OnItemClickListener {

    @BindView(R.id.recyclerMaquinaria)
    RecyclerView mRecyclerMaquinaria;
    @BindView(R.id.spinnerFilter)
    Spinner mSpinnerFiler;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.title_activity_list_of_machinery)
    String title_activity;

    private AppMaquinariaWebAPI mMaquinariaAPI;
    private ProgressDialog mProgressDialog;
    private MaquinariaAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    final List<String> tipos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_machinery);
        ButterKnife.bind(this);

        setToolbar();

        mMaquinariaAPI = ServiceGenerator.createService(AppMaquinariaWebAPI.class);

        mRecyclerMaquinaria.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerMaquinaria.setLayoutManager(mLayoutManager);

        mRecyclerMaquinaria.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Obteniendo listado de maquinas...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        getListOfTypes();
        getListOfMaquinaria();

    }

    public void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle("");
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getListOfMaquinaria() {
        Call<List<Maquina>> getMaquinaria = mMaquinariaAPI.getListOfMaquinaria();
        getMaquinaria.enqueue(new Callback<List<Maquina>>() {
            @Override
            public void onResponse(final Call<List<Maquina>> call, final Response<List<Maquina>> response) {
                mProgressDialog.dismiss();
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
                    Toast.makeText(ListOfMachineryActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(ListOfMachineryActivity.class.getSimpleName(), error);
                    return;
                }
                Log.e(ListOfMachineryActivity.class.getSimpleName() + " Success", "Listado maquinaria obtenido");

                setAdapter(response.body());

                Maquina.MAQUINARIA = response.body();

                final List<Maquina> filteredList = new ArrayList<>();
                mSpinnerFiler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        filteredList.clear();
                        if (adapterView.getItemIdAtPosition(i) == 0) {
                            setAdapter(response.body());
                            return;
                        }
                        for (Maquina m : response.body()) {
                            if (m.getTipo().equals(adapterView.getItemAtPosition(i))) {
                                filteredList.add(m);
                            }
                        }
                        setAdapter(filteredList);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        setAdapter(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Maquina>> call, Throwable t) {
                Log.e(ListOfMachineryActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

    private void getListOfTypes() {
        Call<List<Tipo>> getTipos = mMaquinariaAPI.getListOfType();
        getTipos.enqueue(new Callback<List<Tipo>>() {
            @Override
            public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response) {
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
                    Toast.makeText(ListOfMachineryActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(ListOfMachineryActivity.class.getSimpleName(), error);
                    return;
                }

                final List<Tipo> listadoTipos = new ArrayList<>();
                for (Tipo t : response.body()) {
                    listadoTipos.add(t);
                }

                tipos.add("Filtrar por tipo");
                for (Tipo t : listadoTipos) {
                    tipos.add(t.getDescripcion());
                }

                ArrayAdapter<String> filterAdapter =
                        new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,
                                tipos
                        );
                mSpinnerFiler.setAdapter(filterAdapter);
                filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }

            @Override
            public void onFailure(Call<List<Tipo>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(MaquinariaAdapter.ViewHolder item, int position) {
        Maquina maquina = Maquina.MAQUINARIA.get(position);
        switch (getIntent().getIntExtra(MODULO, 0)) {
            case CONTROL: 
                break;
            case REPARACIONES:
                Intent reparacion = new Intent(ListOfMachineryActivity.this, ReparacionActivity.class);
                reparacion.putExtra("idMaquina", maquina.getIdMaquina());
                startActivity(reparacion);
                break;
            case SERVICIOS:
                break;
            case RENDIMIENTOS:
                break;
            default:
                Toast.makeText(this, "Error interno! Consulte con el administrador", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setAdapter(List<Maquina> list) {
        mAdapter = new MaquinariaAdapter(list, ListOfMachineryActivity.this);
        mAdapter.setHasStableIds(true);
        mAdapter.setOnItemClickListener(ListOfMachineryActivity.this);
        mRecyclerMaquinaria.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
