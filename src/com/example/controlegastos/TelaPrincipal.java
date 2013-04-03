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
		for(final Planilha pla: lista){
			TextView tvPlanilha = new TextView(this);
			tvPlanilha.setText(pla.getNome());
			tvPlanilha.setTextColor(Color.parseColor("#FFFFFF"));
			tvPlanilha.setTextSize(20);
			tvPlanilha.setOnClickListener(new TextView.OnClickListener() {  
		        public void onClick(View v)
	            {
		        	IrPlanilha(v,pla.getCodigo());
	            }
			});
			tvPlanilha.setClickable(true);
			layout.addView(tvPlanilha);
		}
	}

	protected void onResume() {
		super.onResume();
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_principal, menu);
		return true;
	}
	public void IrPlanilha(View view,int codigoPlanilha)
	{	
		Intent intent;
		intent = new Intent(this, TelaPlanilha.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}
	
	public void IrOpcoes(View view)
	{	
		Intent intent;
		intent = new Intent(this, Opcoes.class);
		startActivity(intent);
	}

}

