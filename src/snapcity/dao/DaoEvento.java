package snapcity.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.*;

import snapcity.dao.banco.ConectionFactory;
import snapcity.model.Evento;
import snapcity.model.Usuario;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class DaoEvento {

	Connection c = null;
	Statement stmt = null;
		
	// mostra todos evento registrados
	public void mostrarEvento() {

		try {
			c = ConectionFactory.getConnection();
			//ConectionFactory.getConnection().setAutoCommit(false);;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM eventos;");
			while (rs.next()) {
				int id = rs.getInt("idEventos");
				String foto = rs.getString("foto");
				String descricao = rs.getString("descricao");
				String tags = rs.getString("tags");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				String datahora = rs.getString("datahora");
				int id_usuario = rs.getInt("id_usuario");
				System.out.println("ID = " + id);
				System.out.println("FOTO = " + foto);
				System.out.println("DESCRICAO = " + descricao);
				System.out.println("TAGS = " + tags);
				System.out.println("LATITUDE = " + latitude);
				System.out.println("LONGITUDE = " + longitude);
				System.out.println("DATA DE CRIACAO = " + datahora);
				System.out.println("Usuario = " + id_usuario);
				System.out.println();
			}
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostraEventos com sucesso");
	}

	//Busca eventos por descricao
	public void buscaEvento(String eventos) {

		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from eventos where descricao like  '%"+ eventos + "%';");
			while (rs.next()) {
				int id = rs.getInt("idEventos");
				String foto = rs.getString("foto");
				String descricao = rs.getString("descricao");
				String tags = rs.getString("tags");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				String datahora = rs.getString("datahora");
				int id_usuario = rs.getInt("id_usuario");
				 
				System.out.println("ID = " + id);
				System.out.println("FOTO = " + decodeToImage(foto));
				System.out.println("DESCRICAO = " + descricao);
				System.out.println("TAGS = " + tags);
				System.out.println("LATITUDE = " + latitude);
				System.out.println("LONGITUDE = " + longitude);
				System.out.println("DATA DE CRIACAO = " + datahora);
				System.out.println("Usuario = " + id_usuario);
				System.out.println();
			}
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com buscaEventos com sucesso");
	}
	
	//Método para excluir um evento pelo id
	public void excluiEvento(int id) {    
	      try {  
	    	  c = ConectionFactory.getConnection(); 
	    	  c.setAutoCommit(false);
	    	  stmt = c.createStatement();
		      String sql = "DELETE FROM eventos WHERE eventos.\"idEventos\" = '" + id + "';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      }catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com exluirEventos com sucesso");
	      
	   }
	
	//Atualiza eventos, datahora é atualizado por uma var timestamp que pega data e hora atual.
	public void atualizaEvento(int id,String foto,String descricao,Float latitude,Float longitude,String tags) {    
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  c.setAutoCommit(false);
	    	  Timestamp datahora = new Timestamp(System.currentTimeMillis());
	    	  stmt = c.createStatement();
		      String sql = "UPDATE eventos set foto = '"+ foto +"',descricao = '"+ descricao +"',latitude = '"+ latitude +"',longitude = '"+ longitude +"', tags = '"+ tags +"',datahora = '"+ datahora +"' where idEventos ='"+ id +"';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com atualizaEventos com sucesso");
	      
	   } 
	
	//Insere eventos e datahota insere data e hora atual timestamp
	public void insereEvento(String foto,String descricao,double latitude,double longitude,int id_usuario,String tags){
		 try{
			 c = ConectionFactory.getConnection();
			 c.setAutoCommit(false);
			Timestamp datahora = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
			String sql = "INSERT INTO eventos (foto,descricao,latitude,longitude,id_usuario,tags,datahora)"
			+" values ('"+foto+"','"+descricao+"','"+latitude+"','"+longitude+"','"+id_usuario+"','"+tags+"','"+datahora+"');";
			 
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      System.out.println("Usuario foi criado com sucesso");
    }
	
	//Encode de imagem base64	
	
		public static String encodeToString(String ender) {
	        String imageString = null;

	        try {
	        	File file = new File(ender);
	    		FileInputStream imageInFile = new FileInputStream(file);
	    		byte imageData[] = new byte[(int)file.length()];
	    		imageInFile.read(imageData);
	        	
	            BASE64Encoder encoder = new BASE64Encoder();
	            imageString = encoder.encode(imageData);         
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return imageString;
	    }
	
	//Decode da imagem 
	public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
	
	// para o Json
	public String toJson (String foto, String descricao, String tags,String latitude, String longitude){
		JSONObject evento = new JSONObject();
		
		Timestamp datacriaco = new Timestamp(System.currentTimeMillis());
		
		evento.put("foto", foto);
		evento.put("descricao", descricao);
		evento.put("tags", tags);
		evento.put("latitude", latitude);
		evento.put("longitude", longitude);
		evento.put("datacriaco", datacriaco);
		
		
		String eventoJson = evento.toString();
		
		
		return eventoJson;
	}
	
	public void fromJson(String strJson, int idUsuario){
			//	Evento evento = new Evento();
				
				JSONObject eventoJson = new JSONObject(strJson);
				String foto = eventoJson.getString("foto");
				String descricao = eventoJson.getString("descricao");
				String tags = eventoJson.getString("tags");
				String latitude = eventoJson.getString("latitude");
				String longitude = eventoJson.getString("longitude");
				String datacriacao = eventoJson.getString("datacriacao");
				
				System.out.println("Descricao: "+descricao);	
				System.out.println("tags: "+tags);
				System.out.println("latitude: "+latitude);
				System.out.println("Longitude: "+longitude);
				System.out.println("data de criacao: "+datacriacao);
				System.out.println("Foto: "+foto);
				
				double longi = Double.parseDouble(longitude);
				double lati = Double.parseDouble(latitude);
				
				insereEvento(foto = foto.replaceAll("[n]","\n"), descricao, lati, longi, idUsuario, tags);
		
	}

}
