package model;

import java.util.ArrayList;

public class Compra {
    private ArrayList<String> listaProdutos;
    private ArrayList<Double> listaPrecos;
    private ArrayList<Integer> listaIds;
    private ArrayList<String> listaFabricantes;
    private ArrayList<String> listaCategorias;
    private ArrayList<String> listaFornecedores;
    private ArrayList<String> listaPublicos;

    // Getters e Setters
    public ArrayList<String> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<String> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ArrayList<Double> getListaPrecos() {
        return listaPrecos;
    }

    public void setListaPrecos(ArrayList<Double> listaPrecos) {
        this.listaPrecos = listaPrecos;
    }

    public ArrayList<Integer> getListaIds() {
        return listaIds;
    }

    public void setListaIds(ArrayList<Integer> listaIds) {
        this.listaIds = listaIds;
    }

    public ArrayList<String> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(ArrayList<String> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }

    public ArrayList<String> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<String> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public ArrayList<String> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(ArrayList<String> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public ArrayList<String> getListaPublicos() {
        return listaPublicos;
    }

    public void setListaPublicos(ArrayList<String> listaPublicos) {
        this.listaPublicos = listaPublicos;
    }
}
