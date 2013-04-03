package com.example.controlegastos;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class TelaPlanilha extends Activity{
	private int codigoPlanilha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_planilha);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle bundle = getIntent().getExtras();
		codigoPlanilha = bundle.getInt("codigoPlanilha");
		Planilha pla = Planilha.findByCodigo(codigoPlanilha);
		
		TextView tvNome = (TextView)findViewById(R.id.txv_nome_planilha);
		tvNome.setText(pla.getNome());
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_planilha);
		ImprimirHistorico(savedInstanceState,pla,layout);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_planilha, menu);
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
	
	public void IrCadastrarGanho(View v){
		Intent intent;
		intent = new Intent(this, CadastroGanho.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	} 
	public void IrCadastrarGasto(View v){
		Intent intent;
		intent = new Intent(this, CadastroGasto.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}
	public void irRelatorio(View v){
		Intent intent;
		intent = new Intent(this, Relatorios.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}
	
	public void Voltar(View v){
		Intent intent;
		intent = new Intent(this, TelaPrincipal.class);
		startActivity(intent);
	}
	//imprimir as 5 últimas operações realizadas na planilha
	public void ImprimirHistorico(Bundle B, Planilha pla,LinearLayout layout){
	
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		List<Operacoes> lista = Operacoes.findByPlanilha(pla.getCodigo());
		
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
			layout.addView(layouthHor);
		}
		
		TextView tv=new TextView(this);
		tv.setLayoutParams(lparams);
		tv.setText("------------------------");
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		TextView tvSaldo=new TextView(this);
		tvSaldo.setLayoutParams(lparams);
		tvSaldo.setText("Saldo : R$"+ Operacoes.getSaldoTotalPlanilha(codigoPlanilha));
		tvSaldo.setTextColor(Color.parseColor("#FFFFFF"));
		
		layout.addView(tv);
		layout.addView(tvSaldo);
	}

}
