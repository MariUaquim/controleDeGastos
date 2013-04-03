package com.example.controlegastos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import models.Historico;
import models.Operacoes;
import models.Planilha;

import com.example.controlegastos.R;
import com.example.controlegastos.R.array;
import com.example.controlegastos.R.id;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import auxiliar.Auxiliar;

public class CadastroGasto extends Activity {
	private int codigoPlanilha;
	private EditText editData;
	private int pYear;
    private int pMonth;
    private int pDay;
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
	
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
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String data = formatter.format(calendar.getTime());
		pYear = calendar.get(Calendar.YEAR);
	    pMonth = calendar.get(Calendar.MONTH);
	    pDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		Bundle bundle = getIntent().getExtras();
		codigoPlanilha = bundle.getInt("codigoPlanilha");
		
		
		
		editData = (EditText)findViewById(R.id.dataInicio);
		editData.setText(data);
		
		Spinner spinner = (Spinner) findViewById(R.id.categorias_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categoria_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		Spinner spinnerTipoPagamento = (Spinner) findViewById(R.id.tipoPagamento);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.tipoPagamento, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTipoPagamento.setAdapter(adapter2);
		
		editData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
		 updateDisplay();
		//DatePicker dateP = (DatePicker) findViewById(R.id.datePicker1);		
		//dateP.init(pYear, pMonth, pDay, null);
	}

	private void updateDisplay() {
		editData.setText(
            new StringBuilder() 
                    .append(pDay).append("/")
                    .append(pMonth + 1).append("/")
                    .append(pYear).append(" "));
    }
	private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(editData.getText()),  Toast.LENGTH_SHORT).show();
             
    }
	private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
 
                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, 
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
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
	public void validar(View view) throws ParseException{
	    EditText editText = (EditText) findViewById(R.id.nome_gasto);
	    EditText editTextValor = (EditText) findViewById(R.id.valor_gasto);
	    EditText editTextParcela = (EditText) findViewById(R.id.edit_parcelas);
	    
	    String nome_gasto = editText.getText().toString();
	    String nome_valor = editTextValor.getText().toString();
	    String data = Auxiliar.formataData(pYear, pMonth+1, pDay);
	   
	    int parcelas = Integer.parseInt(editTextParcela.getText().toString());
	    int ativo = Auxiliar.dataMaiorQHoje(data);
	    
	    if (nome_gasto.equals("")||nome_valor.equals("")){
	    	new AlertDialog.Builder(this).setTitle("Aviso").setMessage("Você esqueceu a descricao ou o valor!").setNeutralButton("Close", null).show();
	    }
	    else{
	    	try{
		    	float valor = Float.parseFloat(nome_valor);
		    	Operacoes.insert(codigoPlanilha,nome_gasto,valor,2,data,parcelas,ativo);
		    	
	    	}catch(Exception e){
	    	}
	    	Intent intent = new Intent(CadastroGasto.this, TelaPlanilha.class);
    		intent.putExtra("codigoPlanilha", codigoPlanilha);
	    	CadastroGasto.this.startActivity(intent);
	    	CadastroGasto.this.finish();
	    }
	}
	public void Cancelar(View v){
		Intent intent;
		intent = new Intent(this, TelaPlanilha.class);
		intent.putExtra("codigoPlanilha", codigoPlanilha);
		startActivity(intent);
	}

}
