package dao;

import connection.Conexao;
import vo.Pesquisa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PesquisaDAO {
    public ResultSet pesquisar(Pesquisa pesquisa) {
        try {
            Conexao c = new Conexao();
            Connection con = c.conectar();
            
            String sql = "SELECT * FROM personagens where nome LIKE? AND anime LIKE ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + pesquisa.getNome() + "%");
            ps.setString(2, "%" + pesquisa.getAnime() + "%");
   
            
            ResultSet res = ps.executeQuery();
            
            //c.desconectar();
            return res;
        } catch (SQLException erro) {
            System.out.println("Erro na inserção: " + erro);
            return null;
        }
    }
}