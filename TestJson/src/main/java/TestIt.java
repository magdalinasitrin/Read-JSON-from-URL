import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class TestIt {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		JSONObject json;
		
		System.out.println("Моля въведете ЕГН или ЕИК:");
		String num=sc.nextLine();
		
		while(num.toCharArray().length!=10&&num.toCharArray().length!=9) {
			System.out.println("Невалидно ЕГН/ЕИК. Моля въведете различен номер:");
			num=sc.nextLine();}
		
		if(num.toCharArray().length==9) {
			try {
				json = readJsonFromUrl("https://services.legal-tech.bg/public/companies?eik="+num);
			    String ime=(String) json.getJSONObject("payload").get("name");
			    System.out.println(ime);
			} catch (Exception e) {
				e.printStackTrace();}
		}
		
		else if(num.toCharArray().length==10) {
			try {
				json = readJsonFromUrl("https://services.legal-tech.bg/public/people?egn="+num);
			    String ime=(String) json.getJSONObject("payload").get("name");
			    System.out.println(ime);
			} catch (Exception e) {
				e.printStackTrace();}}
		
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	
	

}
