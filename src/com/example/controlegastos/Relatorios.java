package com.example.controlegastos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.text.format.DateFormat;

@SuppressLint("NewApi")
public class Relatorios extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatorios);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_relatorios);
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		List<Operacoes> lista = Operacoes.findByPlanilha(1);
		int j = 0;
		while(lista.size() > j){
			Operacoes ope = lista.get(j);
			
			LinearLayout layouthHor = new LinearLayout(this);
			TextView tv = new TextView(this);
			TextView tvValor = new TextView(this);
			TextView tvData = new TextView(this);
			
			layouthHor.setLayoutParams(lparams);
			tv.setLayoutParams(lparams);
			tvValor.setLayoutParams(lparams);
			tvData.setLayoutParams(lparams);

			tvData.setText(ope.getData()+" ... ");
			tvData.setTextColor(Color.parseColor("#FFFFFF"));
			tv.setText(ope.getDescricao());
			tv.setTextColor(Color.parseColor("#FFFFFF"));
			if (ope.getTipoOperacao() == 2){
				tvValor.setTextColor(Color.parseColor("#FF3333"));
			}else{
				tvValor.setTextColor(Color.parseColor("#FFFFFF"));
			}
			tvValor.setText(" R$"+ Float.toString(ope.getValor()));
			layouthHor.setOrientation(LinearLayout.HORIZONTAL) ;
			
			layouthHor.addView(tvData);
			layouthHor.addView(tv);
			layouthHor.addView(tvValor);
			layout.addView(layouthHor);
			j++;
		}
		
		TextView tv=new TextView(this);
		tv.setLayoutParams(lparams);
		tv.setText("------------------------");
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		TextView tvSaldo=new TextView(this);
		tvSaldo.setLayoutParams(lparams);
		tvSaldo.setText("Saldo : R$"+ Operacoes.getSaldoTotal());
		tvSaldo.setTextColor(Color.parseColor("#FFFFFF"));
		
		layout.addView(tv);
		layout.addView(tvSaldo);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_relatorios, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
