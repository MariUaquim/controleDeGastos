package com.example.controlegastos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import models.Operacoes;

import com.example.controlegastos.R;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class OperacoesPlanilha extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operacoes_planilha);
		SQLiteDatabase db = openOrCreateDatabase("controleGastosDB", Activity.MODE_PRIVATE, null);
		Database.inicialize(db);
		
		TextView tv = (TextView)findViewById(R.id.txv_saldo);
		tv.setText("R$"+ Operacoes.getSaldoTotal());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_operacoes_planilha, menu);
		return true;
	}
	
	//Este metodo muda a Activity atual para a alvo. O parametro eh um enum. Se uma nova
	//tela for adicionada, ela deve ser adicionada no enum Telas tambem.
	public static int lsiSaldo = 0;
		
	public void TrocaTela(Telas aoTela)
	{
		Intent intent;
		switch(aoTela)
		{
		case AlterarSaldo:
			intent = new Intent(this, AlterarSaldo.class);
			break;
		case CadastroGanho:
			intent = new Intent(this, CadastroGanho.class);
			break;
		case CadastroGasto:
			intent = new Intent(this, CadastroGasto.class);
			break;
		case Relatorios:
			intent = new Intent(this, Relatorios.class);
			break;
		case VisualizaGanhos:
			intent = new Intent(this, VisualizaGanhos.class);
			break;
		case VisualizaGastos:
			intent = new Intent(this, VisualizaGastos.class);
			break;
		default:
			intent = null;
			break;
		}
		startActivity(intent);
	}
		
	public void IrAlterarSaldo(View view)
	{		
		TrocaTela(Telas.AlterarSaldo);
	}
		
	public void IrCadastroGanho(View view)
	{
		TrocaTela(Telas.CadastroGanho);
	}
		
	public void IrCadastroGasto(View view)
	{
		TrocaTela(Telas.CadastroGasto);
	}
		
	public void IrRelatorios(View view)
	{
		TrocaTela(Telas.Relatorios);
	}
		
	public void IrVisualizaGanhos(View view)
	{
		TrocaTela(Telas.VisualizaGanhos);
	}
		
	public void IrVisualizaGastos(View view)
	{
		TrocaTela(Telas.VisualizaGastos);
	}

}
