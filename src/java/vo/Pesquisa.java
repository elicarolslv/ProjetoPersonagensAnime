package vo;

public class Pesquisa {
    
     private String nome;
     private String anime;
     private String fotoUrl; // Novo atributo para a imagem
     
     public String getNome(){
         return nome;
     }
     
     public String getAnime(){
         return anime;
     }

     // Getter para a foto

     
     public void setNome(String nome) {
         this.nome = nome;
     }
     
     public void setAnime(String anime){
         this.anime = anime;
     }
     
   
}