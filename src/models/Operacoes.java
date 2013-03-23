package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.controlegastos.Database;

import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.TextView;

public class Operacoes {
	private int codigo;
	private String descricao;
	private float valor;
	private int codigoPlanilha;
	private int tipoOperacao;
	private String data;
	
	public Operacoes(Cursor c){
		codigo = c.getInt(c.getColumnIndex("ope_codigo"));
		descricao = c.getString(c.getColumnIndex("ope_descricao"));
		codigoPlanilha = c.getInt(c.getColumnIndex("ope_codigo_pla"));
		valor = c.getFloat(c.getColumnIndex("ope_valor"));
		tipoOperacao = c.getInt(c.getColumnIndex("ope_codigo_top"));
		data = c.getString(c.getColumnIndex("ope_data"));
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	public int getTipoOperacao() {
		return tipoOperacao;
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

	public void setNome(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	public String getData() {
		return data;
	}

	public static boolean insert(int codigoPlanilha,String descricao, float valor, int tipoOperacao){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar =  Calendar.getInstance();
		String data = formatter.format(calendar.getTime());
		
		if (findByNome(descricao)==null){
			String query = "insert into Operacoes(ope_descricao,ope_codigo_pla,ope_valor, ope_codigo_top, ope_data) values ('"+descricao+"',"+codigoPlanilha+","+valor+","+tipoOperacao+",'"+data+"')";
			Database.run(query);
			return true;
		}
		return false;
	}
	public static void delete(){
		
		String query = "DROP TABLE Operacoes";
		Database.run(query);
	
	}
	public static Operacoes findByCodigo(int codigo){
		Operacoes toReturn;
		Cursor c = Database.get("select * from Operacoes where ope_codigo = "+ codigo);
		try{
			toReturn = new Operacoes(c);
		}
		catch(Exception e){
			toReturn = null;
		}
		c.close();	
		return toReturn;
	}
	
	public static Operacoes findByNome(String descricao){
		Operacoes toReturn;
		Cursor c = Database.get("select * from Operacoes where ope_descricao = '"+ descricao +"'");
		
		try{
			toReturn = new Operacoes(c);
		}
		catch(Exception e){
			toReturn = null;
		}
			
		c.close();	
		return toReturn;
	}
	public static Operacoes findByTipoOperacao(int tipoOperacao){
		Operacoes toReturn;
		Cursor c = Database.get("select * from Operacoes where ope_codigo_top = "+ tipoOperacao );
		try{
			toReturn = new Operacoes(c);
		}
		catch(Exception e){
			toReturn = null;
		}
		c.close();
		return toReturn;
	}
	public static List<Operacoes> findByPlanilha(int planilha,int tipoOperacao){
		List<Operacoes> toReturn = new ArrayList<Operacoes>();  
		Cursor listOperacoes =  Database.get("select * from Operacoes where ope_codigo_pla = "+ planilha +" and ope_codigo_top = "+ tipoOperacao);
		
		
		try{
			while(!listOperacoes.isAfterLast()){
				   Operacoes ope = new Operacoes(listOperacoes);  	  
				   toReturn.add(ope); 
				   listOperacoes.moveToNext();
			}	
		}catch(Exception e){
			toReturn = null;
		}
		
		listOperacoes.close();
		return toReturn;
	}
	public static List<Operacoes> findByPlanilha(int planilha){
		List<Operacoes> toReturn = new ArrayList<Operacoes>();  
		Cursor listOperacoes =  Database.get("select * from Operacoes where ope_codigo_pla = "+ planilha );
		
		
		try{
			while(!listOperacoes.isAfterLast()){
				   Operacoes ope = new Operacoes(listOperacoes);  	  
				   toReturn.add(ope); 
				   listOperacoes.moveToNext();
			}	
		}catch(Exception e){
			toReturn = null;
		}
		
		listOperacoes.close();
		return toReturn;
	}
	public static String getSaldoTotal(){
		String toReturn;  
		Cursor c =  Database.get("select sum(ope_valor) as soma from Operacoes where ope_codigo_top = 1" );
		Cursor c2 =  Database.get("select sum(ope_valor) as soma from Operacoes where ope_codigo_top = 2" );
		
		
		try{
			float aux= c.getFloat(c.getColumnIndex("soma")) - c2.getFloat(c2.getColumnIndex("soma"));
			toReturn = String.valueOf(aux);
			
		}catch(Exception e){
			toReturn = "0";
		}
		
		c.close();
		c2.close();
		return toReturn;
	}
	public static void create(){
		String query = "create table if not exists Operacoes (ope_codigo int identify(1,1) primary key, ope_descricao varchar, ope_codigo_pla int, ope_valor float, ope_codigo_top int, ope_data date)";
		Database.run(query);
	}
}
