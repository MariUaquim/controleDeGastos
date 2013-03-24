package com.example.controlegastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class Opcoes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_opcoes);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
	public void finishDialog(View v) {
        Opcoes.this.finish();
    }
	
	public void IrCadastrarPlanilha(View v){
		Intent intent;
		intent = new Intent(this, CadastrarPlanilha.class);
		startActivity(intent);
	}
	
	public void IrVisualizarPoupancas(View v){
		Intent intent;
		intent = new Intent(this, VisualizaPoupancas.class);
		startActivity(intent);
	}

}
