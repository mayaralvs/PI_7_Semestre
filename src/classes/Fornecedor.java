package classes;

public class Fornecedor {
	// Atributos do fornecedor
	private String id;
	private String nome;
	private String telefone;
	private String email;
	private String cnpj;
	private String logradouro;
	private String numero;
	private String cidade;
	private String uf;
	private String cep;
	private String complemento;

	// Construtor completo (com todos os atributos)
	public Fornecedor(String id, String nome, String telefone, String email, String cnpj, String logradouro,
			String numero, String cidade, String uf, String cep, String complemento) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.complemento = complemento;
	}

	// Getters e Setters para cada atributo
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	// Método toString para exibir informações do fornecedor
	@Override
	public String toString() {
		return "Fornecedor{" + "id='" + id + '\'' + ", nome='" + nome + '\'' + ", telefone='" + telefone + '\''
				+ ", email='" + email + '\'' + ", cnpj='" + cnpj + '\'' + ", logradouro='" + logradouro + '\''
				+ ", numero='" + numero + '\'' + ", cidade='" + cidade + '\'' + ", uf='" + uf + '\'' + ", cep='" + cep
				+ '\'' + ", complemento='" + complemento + '\'' + '}';
	}
}
