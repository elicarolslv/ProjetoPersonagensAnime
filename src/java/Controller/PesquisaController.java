package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.Pesquisa;
import dao.PesquisaDAO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "PesquisaController", urlPatterns = {"/PesquisaController"})
public class PesquisaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String anime = request.getParameter("anime");

        Pesquisa p = new Pesquisa();
        p.setNome(nome);
        p.setAnime(anime);

        PesquisaDAO dao = new PesquisaDAO();
        ResultSet rs = dao.pesquisar(p);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // ESSENCIAL: Enviar o cabeçalho com o link para o CSS
            out.println("<html>");
            out.println("<head>");
            out.println("   <title>Resultado da Pesquisa</title>");
            out.println("   <link rel='stylesheet' href='css/style.css'>"); 
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Personagens Encontrados</h1>");
            
            // Container pai para ativar o Flexbox
            out.println("<div class='container-resultados'>");

            while (rs.next()) {
                String nomePersonagem = rs.getString("nome");
                String nomeAnime = rs.getString("anime");
                String urlFoto = rs.getString("foto_url");

                out.println("<div class='card-personagem'>");
                out.println("   <img src='" + urlFoto + "'>");
                out.println("   <p><strong>Nome:</strong> " + nomePersonagem + "</p>");
                out.println("   <p><strong>Anime:</strong> " + nomeAnime + "</p>");
                out.println("</div>");
            }
            
            out.println("</div>"); // Fecha container-resultados

            out.println("<br><br>");
            out.println("<a href='index.html' class='btn-voltar'>Voltar para a Pesquisa</a>");
            
            out.println("</body></html>");

        } catch (SQLException e) {
            out.println("Erro ao processar busca: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
}
    


