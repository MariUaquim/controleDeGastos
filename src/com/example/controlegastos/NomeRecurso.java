package com.example.controlegastos;

import com.example.controlegastos.R;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NomeRecurso extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nome_recurso);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nome_recurso, menu);
		return true;
	}

}
