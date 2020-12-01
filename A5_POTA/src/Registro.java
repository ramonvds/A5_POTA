
public class Registro {	
	String id;
	String cpf;
	String nome;
	String sobrenome;
	String sexo;
	String dataadm;
	String pais;
	String diagnostico;
	
	public Registro(String id, String cpf, String nome, String sobrenome, String sexo, String dataadm, String pais,
			String diagnostico) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataadm = dataadm;
		this.pais = pais;
		this.diagnostico = diagnostico;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataadm() {
		return dataadm;
	}
	public void setDataadm(String dataadm) {
		this.dataadm = dataadm;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
}
