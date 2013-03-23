package com.example.controlegastos;

import java.util.List;

import com.example.controlegastos.R;
import com.example.controlegastos.R.id;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import models.Operacoes;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
@SuppressLint({ "NewApi", "ResourceAsColor" })

public class VisualizaGanhos extends Activity {

	@SuppressLint({ "NewApi", "ResourceAsColor" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiza_ganhos);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_ver_ganho);
		
		List<Operacoes> lista = Operacoes.findByPlanilha(1,1);
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
		getMenuInflater().inflate(R.menu.activity_visualiza_ganhos, menu);
		return true;
	}

}
