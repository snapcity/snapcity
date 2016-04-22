package snapcity.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Vector;
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

import snapcity.model.*;
import snapcity.dao.banco.ConectionFactory;
import snapcity.model.Usuario;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class DaoEvento {

	Connection c = null;
	Statement stmt = null;
		
	// mostra todos evento registrados
	/**
	 * 
	 * @return
	 */
	public Vector<Evento> mostrarEvento() {
		
		Vector<Evento> resultados = new Vector<Evento>();  

		try {
			c = ConectionFactory.getConnection();
			//ConectionFactory.getConnection().setAutoCommit(false);;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM eventos;");
			Evento temp = new Evento();
			while (rs.next()) {
				temp.setId(rs.getInt("idEventos"));
				temp.setFoto(rs.getString("foto"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setTag(rs.getString("tags"));
				temp.setLatitude(rs.getDouble("latitude"));
				temp.setLongintude(rs.getDouble("longitude"));
				temp.setDatahora(rs.getString("datahora"));
				resultados.add(temp);

			}
			stmt.close();
			return resultados;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostraEventos com sucesso");
		return resultados;
	}

	//Busca eventos por descricao
	public Vector<Evento> buscaEvento(Evento evento, String descricao) {
		
		Vector<Evento> resultados = new Vector<Evento>();
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from eventos where descricao like  '%"+ descricao + "%';");
			while (rs.next()) {
				Evento temp = new Evento();
				temp.setId(rs.getInt("idEventos"));
				temp.setFoto(rs.getString("foto"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setTag(rs.getString("tags"));
				temp.setLatitude(rs.getDouble("latitude"));
				temp.setLongintude(rs.getDouble("longitude"));
				temp.setDatahora(rs.getString("datahora"));
				resultados.add(temp);
				 
			}
			
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com buscaEventos com sucesso");
		return resultados;
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
	public void atualizaEvento(Evento evento, Integer id) {    
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  c.setAutoCommit(false);
	    	  Timestamp datahora = new Timestamp(System.currentTimeMillis());
	    	  stmt = c.createStatement();
		      String sql = "UPDATE eventos set foto = '"+ evento.getFoto() +
		    		  "',descricao = '"+ evento.getDescricao()+"',latitude = '"+
		    		  evento.getLatitude() +"',longitude = '"+ evento.getLongintude() +
		    		  "', tags = '"+ evento.getTag() +"',datahora = '"+ datahora+"'"
		    		  		+ " where eventos.\"idEventos\" = '" + id + "';";
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
	public void insereEvento(Evento evento, Usuario usuario){
		 try{
			 c = ConectionFactory.getConnection();
			 c.setAutoCommit(false);
			Timestamp datahora = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
			String sql = "INSERT INTO eventos (foto,descricao,latitude,longitude,id_usuario,tags,datahora)"
			+" values ('"+evento.getFoto()+"','"+evento.getDescricao()+"','"+evento.getLatitude()+"','"+evento.getLongintude()+"','"+usuario.getId()+"','"+evento.getTag()+"','"+datahora+"');";
			 
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
	
	public Vector<Evento> fromJson(String strJson){
		
		Vector<Evento> resultados = new Vector<Evento>();
		Evento temp = new Evento();
		
		JSONObject evento = new JSONObject(strJson);
			
		
		temp.setDescricao(evento.getString("descricao"));
		temp.setTag(evento.getString("tags"));
		temp.setLatitude(evento.getDouble("latitude"));
		temp.setLongintude(evento.getDouble("longitude"));
		temp.setDatahora(evento.getString("datacriacao"));
		temp.setFoto(evento.getString("foto"));
		resultados.add(temp);
		
	
		
		return resultados;
		
	}

}
