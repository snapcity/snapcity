package snapcity.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.json.*;

import snapcity.model.*;
import snapcity.dao.banco.ConectionFactory;
import snapcity.model.Usuario;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DaoEvento {

	Connection c = null;
	Statement stmt = null;

	/**
	 * Retorna todos os eventos cadastros.
	 * @return Lista de {@link Evento} cadastros.
	 */
	public List<Evento> mostrarEvento() {

		ArrayList<Evento> eventos = new ArrayList<Evento>();

		try {
			c = ConectionFactory.getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM eventos;");

			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("idEventos"));
				evento.setFoto(rs.getString("foto"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setTag(rs.getString("tags"));
				evento.setLatitude(rs.getDouble("latitude"));
				evento.setLongitude(rs.getDouble("longitude"));
				evento.setDatahora(rs.getString("datahora"));				
				eventos.add(evento);
			}
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Operacao com mostraEventos com sucesso");
		return eventos;
	}

	//Busca eventos por descricao
	public Evento buscaEvento(int idevento) {


		try {

			Evento evento = new Evento();

			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from eventos where eventos.\"idEventos\"="+ idevento + ";");
			while (rs.next()) {

				int id = rs.getInt("idEventos");
				String foto = rs.getString("foto");
				String descricao = rs.getString("descricao");
				String tags = rs.getString("tags");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");
				String datahora = rs.getString("datahora");		

				evento.setId(id);
				evento.setDescricao(descricao);
				evento.setFoto(foto);
				evento.setTag(tags);
				evento.setLatitude(latitude);
				evento.setLongitude(longitude);
				evento.setDatahora(datahora);

			}

			rs.close();
			c.close();
			stmt.close();

			System.out.println("Operacao com buscaEventos com sucesso");

			return evento;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}

	//Método para excluir um evento pelo id
	public void excluiEvento(Evento evento) {    
		try {  
			c = ConectionFactory.getConnection(); 
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE FROM eventos WHERE eventos.\"idEventos\" = '" + evento.getId() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			c.close();
			stmt.close();

			System.out.println("Operacao com exluirEventos com sucesso");

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}

	//Atualiza eventos, datahora é atualizado por uma var timestamp que pega data e hora atual.
	public void atualizaEvento(Evento evento) {    
		try {  
			c = ConectionFactory.getConnection();
			c.setAutoCommit(false);
			Timestamp datahora = new Timestamp(System.currentTimeMillis());
			stmt = c.createStatement();
			String sql = "UPDATE eventos set foto = '"+ evento.getFoto() +
					"',descricao = '"+ evento.getDescricao()+"',latitude = '"+
					evento.getLatitude() +"',longitude = '"+ evento.getLongitude() +
					"', tags = '"+ evento.getTag() +"',datahora = '"+datahora+"'"
					+ " where eventos.\"idEventos\" = '" + evento.getId() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			c.close();
			stmt.close();
			System.out.println("Operacao com atualizaEventos com sucesso");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	} 

	//Insere eventos e datahota insere data e hora atual timestamp
	public void insereEvento(Evento evento, Usuario usuario){
		try{
			c = ConectionFactory.getConnection();
			c.setAutoCommit(false);
			Timestamp datahora = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
			String sql = "INSERT INTO eventos (foto,descricao,latitude,longitude,id_usuario,tags,datahora)"
					+" values ('"+evento.getFoto()+"','"+evento.getDescricao()+"','"+evento.getLatitude()+"','"+evento.getLongitude()+"','"+usuario.getId()+"','"+evento.getTag()+"','"+datahora+"');";

			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("Evento foi criado com sucesso");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
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
			
			imageInFile.close();
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
	//	public String toJson (String foto, String descricao, String tags,String latitude, String longitude){
	//		JSONObject evento = new JSONObject();
	//		
	//		Timestamp datacriaco = new Timestamp(System.currentTimeMillis());
	//		
	//		evento.put("foto", foto);
	//		evento.put("descricao", descricao);
	//		evento.put("tags", tags);
	//		evento.put("latitude", latitude);
	//		evento.put("longitude", longitude);
	//		evento.put("datacriaco", datacriaco);
	//		
	//		
	//		String eventoJson = evento.toString();
	//		
	//		
	//		return eventoJson;
	//	}

	public static  String toJson( Evento evento){
		JSONObject obj = new JSONObject();
		obj.put("descricao", evento.getDescricao());
		obj.put("foto", evento.getFoto());
		obj.put("longitude", evento.getLongitude());
		obj.put("latitude", evento.getLatitude());
		obj.put("tags", evento.getTag());
		obj.put("datacriaco", evento.getDatahora());
		return obj.toString();
	}

	public Evento fromJson(String strJson){
		JSONObject eventoJson = new JSONObject(strJson);
		String descricao = eventoJson.getString("descricao");
		String tags = eventoJson.getString("tags");
		Double latitude = eventoJson.getDouble("latitude");
		Double longitude = eventoJson.getDouble("longitude");
		String datacriacao = eventoJson.getString("datacriacao");
		String foto = eventoJson.getString("foto");

		Evento evento = new Evento();
		evento.setDescricao(descricao);
		evento.setTag(tags);
		evento.setLatitude(latitude);
		evento.setLongitude(longitude);
		evento.setDatahora(datacriacao);
		evento.setFoto(foto);

		return evento;
	}



}