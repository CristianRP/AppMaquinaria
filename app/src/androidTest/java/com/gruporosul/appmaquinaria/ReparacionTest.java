package com.gruporosul.appmaquinaria;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gruporosul.appmaquinaria.activity.ReparacionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Cristian Ramírez on 24/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */

@RunWith(AndroidJUnit4.class)
public class ReparacionTest {

    private String mFechaParalizacion;
    private String mInicioReparacion;
    private String mEstado;
    private String mFechaFin;
    private int mDiasAsignados;
    private int mIdMaquina;
    private String mDiagnostico;

    @Rule
    public ActivityTestRule<ReparacionActivity> mReparacionActivityTestRule =
            new ActivityTestRule<>(ReparacionActivity.class);

    @Before
    public void initVariables() throws ParseException {
        mFechaParalizacion = "20/05/2017";
        mInicioReparacion = "21/05/2017";
        mEstado = "En reparacion";
        mFechaFin = "24/05/2017";
        mDiasAsignados = 4;
        mIdMaquina = 5;
        mDiagnostico = "Reparacion de prueba";
    }

    @Test
    public void test1SendPostData() {
        onView(withId(R.id.txlFechaParalizacion)).perform(typeText(mFechaParalizacion));
        onView(withId(R.id.tlInicioReparacion)).perform(typeText(mInicioReparacion));
        onView(withId(R.id.tlEstado)).perform(typeText(mEstado));
        onView(withId(R.id.tlFechaFin)).perform(typeText(mFechaFin));
        onView(withId(R.id.tlDiasAsignados)).perform(typeText(String.valueOf(mDiasAsignados)));
        onView(withId(R.id.tlDiagnostico)).perform(typeText(mDiagnostico));
        closeSoftKeyboard();
        onView(withId(R.id.btnSaveData)).perform(click());

    }



}
