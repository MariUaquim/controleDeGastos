package com.example.controlegastos;

import models.Historico;
import models.Operacoes;

import com.example.controlegastos.R;
import com.example.controlegastos.R.array;
import com.example.controlegastos.R.id;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroGasto extends Activity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_gasto);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		
		Spinner spinner = (Spinner) findViewById(R.id.categorias_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.categoria_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastro_gastos, menu);
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
	public void validar(View view){
	    EditText editText = (EditText) findViewById(R.id.nome_gasto);
	    String nome_gasto = editText.getText().toString();
	    EditText editText2 = (EditText) findViewById(R.id.valor_gasto);
	    String nome_valor = editText2.getText().toString();
	    
	   
	    if (nome_gasto.equals("")||nome_valor.equals("")){
	    	new AlertDialog.Builder(this).setTitle("Aviso").setMessage("Você esqueceu a descricao ou o valor!").setNeutralButton("Close", null).show();
	    }
	    else{
	    	float valor = Float.parseFloat(nome_valor);
	    	Operacoes.insert(1,nome_gasto,valor,2);
	    	
	    	Intent intent = new Intent(CadastroGasto.this, TelaPrincipal.class);    	
	    	CadastroGasto.this.startActivity(intent);
	    	CadastroGasto.this.finish();

	    }
	}

}
