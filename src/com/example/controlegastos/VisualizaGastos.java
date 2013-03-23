package com.example.controlegastos;

import java.util.List;


import models.Operacoes;

import com.example.controlegastos.R;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class VisualizaGastos extends Activity {
	
	@Override
	@SuppressLint("NewApi")

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiza_gastos);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_ver_gasto);
		
		List<Operacoes> lista = Operacoes.findByPlanilha(1,2);
		int j = 0;
		while(lista.size() > j){
			Operacoes ope = lista.get(j);
			LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LinearLayout layouthHor = new LinearLayout(this);
			TextView tv=new TextView(this);
			TextView tvValor=new TextView(this);
			
			layouthHor.setLayoutParams(lparams);
			tv.setLayoutParams(lparams);
			tvValor.setLayoutParams(lparams);
			
			tv.setText(ope.getDescricao());
			tv.setTextColor(Color.parseColor("#FFFFFF"));
			tvValor.setTextColor(Color.parseColor("#FFFFFF"));
			tvValor.setText(" - R$"+ Float.toString(ope.getValor()));
			layouthHor.setOrientation(LinearLayout.HORIZONTAL) ;
			
			layouthHor.addView(tv);
			layouthHor.addView(tvValor);
			layout.addView(layouthHor);
			j++;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_visualiza_gastos, menu);
		return true;
	}

}
