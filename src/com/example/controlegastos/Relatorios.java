package com.example.controlegastos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Operacoes;
import models.Planilha;

import com.example.controlegastos.R;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import auxiliar.Auxiliar;

@SuppressLint("NewApi")
public class Relatorios extends Activity {
	private int codigoPlanilha;
	private LinearLayout layout;
	private List<Operacoes> lista;
	private ArrayList<LinearLayout> layouts = new ArrayList<LinearLayout>();
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatorios);
		
		Bundle bundle = getIntent().getExtras();
		codigoPlanilha = bundle.getInt("codigoPlanilha");
        
		Spinner spinner = (Spinner) findViewById(R.id.mesesOptions);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.mesesOptions, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		Calendar calendar =  Calendar.getInstance();
		spinner.setSelection(calendar.get(Calendar.MONTH));
		
		layout = (LinearLayout) this.findViewById(R.id.layout_relatorios);
		lista = Auxiliar.historicoMensal(calendar.get(Calendar.MONTH)+1, codigoPlanilha);
	
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				lista = Auxiliar.historicoMensal(position+1, codigoPlanilha);
				monthSelected(savedInstanceState, lista);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void monthSelected(Bundle b,List<Operacoes> lista) {
		imprimirHistorico(b, lista,layout);
	}

	public void voltar(View v){
		Intent intent;
		intent = new Intent(this, TelaPlanilha.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}
	
	public void setarHistorico(View v){
		Intent intent;
		intent = new Intent(this, TelaPlanilha.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}
	
	public void imprimirHistorico(Bundle B, List<Operacoes> lista,LinearLayout layout){
		while(!layouts.isEmpty()){
			layout.removeView(layouts.remove(0));
		}
	 
		
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		for(int j=0;(lista.size() > j) && (j < 6);j++){
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
			layouts.add(layouthHor);
		}
		LinearLayout layouthHor = new LinearLayout(this);
		layouthHor.setLayoutParams(lparams);
		layouthHor.setOrientation(LinearLayout.VERTICAL);
		TextView tv=new TextView(this);
		tv.setLayoutParams(lparams);
		tv.setText("------------------------");
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		TextView tvSaldo=new TextView(this);
		tvSaldo.setLayoutParams(lparams);
		tvSaldo.setText("Saldo : R$"+ Operacoes.getSaldoTotalPlanilha(codigoPlanilha));
		tvSaldo.setTextColor(Color.parseColor("#FFFFFF"));
		
		layouthHor.addView(tv);
		layouthHor.addView(tvSaldo);
		layouts.add(layouthHor);
		
		for(LinearLayout l: layouts){
			layout.addView(l);
		}
		
	}
	
}
