package classes;


public class Produto {
    private int id;
    private String nome;
    private String fabricante;
    private String lote;
    private double custo;
    private String categoria;
    private String publico;
    private String fornecedor;
    private double preco;
    private int quantidade;

    // Construtor
    public Produto(int id, String nome, String fabricante, String lote, double custo, String categoria, 
                   String publico, String fornecedor, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.lote = lote;
        this.custo = custo;
        this.categoria = categoria;
        this.publico = publico;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
