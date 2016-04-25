package snapcity.model;

public class Evento {
	private Integer id;
	private String foto;
	private String descricao;
	private String tag;
	private double longintude;
	private double latitude;
	private String datahora;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setLongintude(double longintude) {
		this.longintude = longintude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}

	public void getId(Integer id) {
		this.id = id;
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

	public double getLongintude() {
		return longintude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getDatahora() {
		return datahora;
	}

}
