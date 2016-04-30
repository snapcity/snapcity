package snapcity.model;

public class Evento {
	private Integer id;
	private String foto;
	private String descricao;
	private String tag;
	private double longintude;
	private double latitude;
	private String datahora;
	
	public Evento(){
		
	}

	public Evento(Integer id, String foto, String descricao, String tag,
			double longintude, double latitude, String datahora) {
		super();
		this.id = id;
		this.foto = foto;
		this.descricao = descricao;
		this.tag = tag;
		this.longintude = longintude;
		this.latitude = latitude;
		this.datahora = datahora;
	}

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
	

	public Integer getId() {
		return id;
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
