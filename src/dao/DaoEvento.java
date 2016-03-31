package dao;

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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.banco.ConectionFactory;

public class DaoEvento {

	Connection c = null;
	Statement stmt = null;
		
	// mostra todos evento registrados
	public void mostrarEventos() {

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
	public void buscaEventos(String eventos) {

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
	public void excluiEventos(int id) {    
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
	public void atualizaEventos(int id,String foto,String descricao,Float latitude,Float longitude,String tags) {    
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
	public void insereEventos(String foto,String descricao,double latitude,double longitude,int id_usuario,String tags){
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
	
//encode de imagem
	
	public static String encodeToString(byte[] image) {
        String imageString = null;

        try {
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(image);         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
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

}
