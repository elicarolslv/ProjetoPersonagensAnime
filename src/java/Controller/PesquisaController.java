package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Pesquisa;
import dao.PesquisaDAO;

public class PesquisaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String anime = request.getParameter("anime");
        String fotoUrl = request.getParameter("foto_url");

        Pesquisa p = new Pesquisa();
        p.setNome(nome);
        p.setAnime(anime);
        p.setFotoUrl(fotoUrl);

        PesquisaDAO dao = new PesquisaDAO();
        boolean sucesso = dao.gravar(p);

        response.setContentType("text/html;charset=UTF-8");
        if (sucesso) {
            response.getWriter().println("<h1>Salvo com sucesso!</h1>");
        } else {
            response.getWriter().println("<h1>Erro ao salvar.</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
}