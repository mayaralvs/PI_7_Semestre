package controller;

import model.Compra;
import java.sql.*;
import java.util.ArrayList;

public class CompraDAO {
    private Compra model;

    public CompraDAO(Compra model) {
        this.model = model;
    }

    // Método para carregar produtos
    public void carregarProdutos() {
        ArrayList<String> listaProdutos = new ArrayList<>();
        ArrayList<Double> listaPrecos = new ArrayList<>();
        ArrayList<Integer> listaIds = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT id, nome, preco FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaIds.add(rs.getInt("id"));
                listaProdutos.add(rs.getString("nome"));
                listaPrecos.add(rs.getDouble("preco"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.setListaProdutos(listaProdutos);
        model.setListaPrecos(listaPrecos);
        model.setListaIds(listaIds);
    }

    // Método para carregar fabricantes
    public void carregarFabricantes() {
        ArrayList<String> listaFabricantes = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT fabricante FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaFabricantes.add(rs.getString("fabricante"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.setListaFabricantes(listaFabricantes);
    }

    // Método para carregar categorias
    public void carregarCategorias() {
        ArrayList<String> listaCategorias = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT categoria FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaCategorias.add(rs.getString("categoria"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.setListaCategorias(listaCategorias);
    }

    // Método para carregar fornecedores
    public void carregarFornecedores() {
        ArrayList<String> listaFornecedores = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT fornecedor FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaFornecedores.add(rs.getString("fornecedor"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.setListaFornecedores(listaFornecedores);
    }

    // Método para carregar públicos
    public void carregarPublicos() {
        ArrayList<String> listaPublicos = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT publico FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaPublicos.add(rs.getString("publico"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.setListaPublicos(listaPublicos);
    }
}
