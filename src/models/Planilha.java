package models;

import com.example.controlegastos.Database;

import android.database.Cursor;



public class Planilha {
	private int codigo;
	private int tipo;
	private String nome;
	private float meta;

	
	public  Planilha(int tipo,String nome, float meta){
		this.nome = nome;
		this.meta = meta;
		this.tipo = tipo;
		
	}
	public Planilha(Cursor c){
		
		codigo = c.getInt(c.getColumnIndex("pla_codigo"));
		nome = c.getString(c.getColumnIndex("pla_codigo"));
		tipo = c.getInt(c.getColumnIndex("pla_codigo_tpl"));
		meta = c.getFloat(c.getColumnIndex("pla_meta"));
	}
	public int getCodigo() {
		return codigo;
	}
	public int getTipo() {
		return tipo;
	}
	public void setMeta(float meta) {
		this.meta = meta;
	}

	public float getMeta() {
		return meta;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public  String getNome() {
		return nome;
	}

	public static void insert(String nome, int tipo, float meta){
		if(findByNome(nome)== null){
			String query = "insert into Planilha(pla_nome,pla_codigo_tpl,pla_meta) values ('"+nome+"',"+tipo+","+meta+")";
			Database.run(query);
		}
	}
	public static void delete(int codigo){
		
		String query = "delete from Planilha where pla_codigo = "+codigo+"";
		Database.run(query);
	
	}
	public static Planilha findByCodigo(int codigo){
		Planilha toReturn;
		Cursor c = Database.get("select * from Planilha where pla_codigo = "+ codigo);
		try{
			toReturn = new Planilha(c);
		}
		catch(Exception e){
			toReturn = null;
		}
		c.close();	
		return toReturn;
	}
	
	public static Planilha findByNome(String nome){
		Planilha toReturn;
		Cursor c = Database.get("select * from Planilha where pla_nome = '"+ nome +"'");
		try{
			toReturn = new Planilha(c);
		}
		catch(Exception e){
			toReturn = null;
		}
		c.close();	
		return toReturn;
	}
	public static void create(){
		String query = "create table if not exists Planilha (pla_codigo int identify(1,1) primary key, pla_nome varchar, pla_codigo_tpl int, pla_meta float)";
		Database.run(query);
	}
}
