package com.example.controlegastos;


import java.util.List;

import models.Operacoes;
import models.Planilha;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TelaPrincipal extends Activity {

	SQLiteDatabase db = null;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_principal);
		
		if(db == null){
			db = openOrCreateDatabase("controleGastosDB", Activity.MODE_PRIVATE, null);
			Database.inicialize(db);
		}
			
		TextView tv = (TextView)findViewById(R.id.txv_saldo);
		tv.setText(tv.getText() +"R$"+ Operacoes.getSaldoTotal());
		
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.lt_tela_principal);
		
		List<Planilha> lista = Planilha.findByTipo(1);
		for(Planilha pla: lista){
			TextView tvPlanilha = new TextView(this);
			tvPlanilha.setText(pla.getNome());
			tvPlanilha.setTextColor(Color.parseColor("#FFFFFF"));
			layout.addView(tvPlanilha);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_principal, menu);
		return true;
	}
	
	public void IrOpcoes(View view)
	{	
		Intent intent;
		intent = new Intent(this, Opcoes.class);
		startActivity(intent);
	}

}
