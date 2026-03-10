package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String bd;
    private final String usuario;
    private final String senha;
    private Connection con;
    
    public Conexao() {
        bd = "personagem_anime";   // Nome do banco que criamos
        usuario = "root";        // Ajuste se seu MySQL tiver outro usuário
        senha = "";              // Defina a senha do seu MySQL se houver
        con = null;
    }

    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + bd
                       + "?useSSL=false&useTimezone=true&serverTimezone=UTC&characterEncoding=UTF-8";
            
            con = DriverManager.getConnection(url, usuario, senha);
            return con;
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado: " + erro);
            return null;
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar: " + erro);
            return null;
        }
    }   
    
    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexão encerrada com sucesso!");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao encerrar a conexão: " + erro);
        }
    }
}
