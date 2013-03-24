package com.example.controlegastos;

import java.sql.ResultSet;

import models.Historico;
import models.Operacoes;
import models.Planilha;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class Database extends Activity {
private static SQLiteDatabase db = null;	
	
	public static void inicialize(SQLiteDatabase db){
		Database.db = db;
		//usr_usuario.create();
		Planilha.create();
		//tpl_tipo_planilha.create();
		//Operacoes.delete();
		Operacoes.create();
		//Historico.create();
		//per_periodo.create();
		//Historico.create();
		
	}
	
	private static boolean verify(){
		return db != null;
		//		if(db == null) inicialize();
	}
	
	public static void run(String query){
		if(verify()){
			db.execSQL(query);
		}
	}
	
	public static Cursor get(String query){
		if(verify()){
			Cursor c = db.rawQuery(query, null);
			c.moveToFirst();
			return c;
		}
		
		return null;
	}
	
}
