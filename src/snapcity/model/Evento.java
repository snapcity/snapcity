package snapcity.model;

public class Evento {
	private String foto;
	private String descricao;
	private String tag;
	private Integer longintude;
	private Integer latitude;
	private String datahora;
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public void setLongintude(Integer longintude) {
		this.longintude = longintude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}
	
	public String getFoto() {
		return foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getTag() {
		return tag;
	}
	public Integer getLongintude() {
		return longintude;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public String getDatahora() {
		return datahora;
	}
	
	
	

}
