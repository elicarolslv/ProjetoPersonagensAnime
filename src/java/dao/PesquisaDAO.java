package dao;

import connection.Conexao;
import vo.Pesquisa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PesquisaDAO {
    public boolean gravar(Pesquisa pesquisa) {
        try {
            Conexao c = new Conexao();
            Connection con = c.conectar();
            
            String sql = "INSERT INTO personagens (nome, anime, foto_url) VALUES (?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pesquisa.getNome());
            ps.setString(2, pesquisa.getAnime());
            ps.setString(3, pesquisa.getFotoUrl());
            
            int res = ps.executeUpdate();
            
            c.desconectar();
            return res > 0;
        } catch (SQLException erro) {
            System.out.println("Erro na inserção: " + erro);
            return false;
        }
    }
}