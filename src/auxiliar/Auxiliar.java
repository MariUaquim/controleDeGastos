package auxiliar;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Operacoes;

import android.database.Cursor;
import android.util.Log;

import com.example.controlegastos.Database;

public class Auxiliar {

	public static List<Operacoes> historicoMensal(int mes,int planilha){
		List<Operacoes> toReturn = new ArrayList<Operacoes>();  
		String sMes = ""+ mes/10 + mes%10;
		Log.i("mes", sMes);
		
		Cursor listOperacoes =  Database.get("select * from Operacoes where strftime('%m', ope_data) = '"+ sMes +"' and ope_codigo_pla = "+ planilha);
		
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
	public static String formataData(int pAno,int pMes,int pDia){
		String retorno;
		String ano = Integer.toString(pAno);
		String mes = Integer.toString(pMes);
		String dia = Integer.toString(pDia);
		
		mes = mes.length()<2?"0"+mes:mes;
		dia = dia.length()<2?"0"+dia:dia;
		retorno = ano +"-"+mes+"-"+dia;
		return retorno;
	}
	
	public static int dataMaiorQHoje(String dataStr) throws ParseException{
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Date data = new Date(formatter.parse(dataStr).getTime());
		
		String hojeStr = formatter.format(calendar.getTime());
		Date hoje = new Date(formatter.parse(hojeStr).getTime());
		
		return hoje.compareTo(data) > 0 ? 1 : 0;
	}
	
}
