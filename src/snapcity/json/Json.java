package snapcity.json;
import org.json.JSONException;
import org.json.JSONObject;

public class Json{
	
	 public static void main(String[] args) throws JSONException {
		 
		 String json_str = "{\"titulo\":\"Os Arquivos JSON\",\"ano\":1998, \"genero\":\"Ficção\"}";
		 
		 JSONObject my_obj = new JSONObject(json_str);
		 
		 String titulo = my_obj.getString("titulo");
		 Integer ano = my_obj.getInt("ano");
		 String genero = my_obj.getString("genero");
		 
		 System.out.println("titulo: " + titulo);
		 System.out.println("ano: " + ano);
		 System.out.println("genero: " + genero);
	 }
	
	
}