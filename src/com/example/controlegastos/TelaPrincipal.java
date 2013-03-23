package com.example.controlegastos;


import models.Operacoes;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TelaPrincipal extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_principal);
		SQLiteDatabase db = openOrCreateDatabase("controleGastosDB", Activity.MODE_PRIVATE, null);
		Database.inicialize(db);
		
		TextView tv = (TextView)findViewById(R.id.txv_saldo);
		tv.setText(tv.getText() +"R$"+ Operacoes.getSaldoTotal());
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
