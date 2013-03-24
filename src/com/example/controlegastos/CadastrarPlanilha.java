package com.example.controlegastos;

import models.Operacoes;
import models.Planilha;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class CadastrarPlanilha extends Activity {

	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_planilha);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		visibilidadeMeta(new View(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_planilha, menu);
		
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
	
	public void visibilidadeMeta(View v){
		int visibility = View.INVISIBLE;
		if (v.getId() == R.id.radio1){
			visibility = View.VISIBLE;
		}

		((EditText) findViewById(R.id.edit_meta)).setVisibility(visibility);
		((TextView) findViewById(R.id.txv_meta)).setVisibility(visibility);
	}
	
	public void validar(View view){
	    EditText editText = (EditText) findViewById(R.id.edt_nome_planilha);
	    String nomePlanilha = editText.getText().toString();
	    RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup1);

	    int tipoPlanilha = 1;
	    float meta = 0;
	    if(radio.getCheckedRadioButtonId() == R.id.radio1){
	    	tipoPlanilha = 2;
	    	meta = Float.parseFloat(((EditText) findViewById(R.id.edit_meta)).getText().toString());
	    }
	   
	    if (nomePlanilha.equals("")){
	    	new AlertDialog.Builder(this).setTitle("Aviso").setMessage("codigo: "+ tipoPlanilha).setNeutralButton("Close", null).show();
	    }
	    else{
	    	Planilha.insert(nomePlanilha,tipoPlanilha,meta);
	    	Intent intent = new Intent(CadastrarPlanilha.this, TelaPrincipal.class);    	
	    	CadastrarPlanilha.this.startActivity(intent);
	    	CadastrarPlanilha.this.finish();

	    }
	}

}
