package models;


import java.text.DateFormat;
import java.util.Date;

import com.example.controlegastos.Database;

import android.database.Cursor;

public class Historico {
	private String data;
	private int codOperacao;
	private float valor;
	private int codigoPlanilha;
	
	public  Historico(int codigoPlanilha,int codOperacao, float valor, String data){
		this.codOperacao = codOperacao;
		this.valor = valor;
		this.codigoPlanilha = codigoPlanilha;
		this.data = data;
	}
	public Historico(Cursor c){
		data = DateFormat.getDateInstance().format(c.getInt(c.getColumnIndex("his_data")));
		codOperacao = c.getInt(c.getColumnIndex("his_codigo_ope"));
		codigoPlanilha = c.getInt(c.getColumnIndex("his_codigo_pla"));
		valor = c.getFloat(c.getColumnIndex("his_valor"));
	}
	public String getData() {
		return data;
	}
	public int getCodigoPlanilha() {
		return codigoPlanilha;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getValor() {
		return valor;
	}

	public void setNome(int codOperacao) {
		this.codOperacao = codOperacao;
	}

	public int getOperacao() {
		return codOperacao;
	}

	public static void insert(int codOperacao, int cod_planilha, float valor){
	
		String query = "insert into Historico(his_codigo_ope,his_codigo_pla,his_valor,his_data) values ('"+codOperacao+"',"+cod_planilha+","+valor+",getdate())";
		Database.run(query);

	}
	public static Historico findByData(Date data, int cod_planilha){
		Historico toReturn;
		Cursor c = Database.get("select * from Historico where his_data = '"+ data+"' and his_codigo_pla = "+ cod_planilha);
		try{
			toReturn = new Historico(c);
		}
		catch(Exception e){
			toReturn = null;
		}
		c.close();
		return toReturn;
	}
	
	public static void create(){
		String query = "create table if not exists Historico (his_codigo_pla int , his_codigo_ope int, his_data datetime, his_valor float)";
		Database.run(query);
	}

}
