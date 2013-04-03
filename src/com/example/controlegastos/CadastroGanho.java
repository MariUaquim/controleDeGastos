package com.example.controlegastos;



import com.example.controlegastos.R;
import com.example.controlegastos.R.id;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import models.Operacoes;
import models.Planilha;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class CadastroGanho extends Activity {
	private int codigoPlanilha;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_ganho);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle bundle = getIntent().getExtras();
		codigoPlanilha = bundle.getInt("codigoPlanilha");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastro_ganho, menu);
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
	public void validar(View view){
		
	    EditText editText = (EditText) findViewById(R.id.nome_ganho);
	    String nome_ganho = editText.getText().toString();
	    EditText editText2 = (EditText) findViewById(R.id.valor_ganho);
	    String nome_valor = editText2.getText().toString();
	 
	    if (nome_ganho.equals("")||nome_valor.equals("")){
	    	new AlertDialog.Builder(this).setTitle("Aviso").setMessage("Você esqueceu a descricao ou o valor!").setNeutralButton("Close", null).show();
	    }
	    else{
	    	try{
		    	float valor = Float.parseFloat(nome_valor);
		    	Operacoes.insert(codigoPlanilha,nome_ganho,valor,1,"2013-04-02",0,0);
		    	
	    	}catch(Exception e){ 
	    	}
	    	Intent intent = new Intent(CadastroGanho.this, TelaPlanilha.class);
	    	intent.putExtra("codigoPlanilha", codigoPlanilha);
	    	CadastroGanho.this.startActivity(intent);
	    	CadastroGanho.this.finish();
	    }
	}
	public void Cancelar(View v){
		Intent intent;
		intent = new Intent(this, TelaPlanilha.class);
		startActivity(intent);
	}
}
