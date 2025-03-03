package ClassesDiario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Classe principal do Diario
public class Diario {
    //Lista de entradas do diário
    private List<EntradaDiario> entradas;
    private List<Usuario> usuarios;
    //Usuario atualmente conectado
    private static Usuario usuarioAtual = null;

    //Construtor da classe Diario
    public Diario(){
        this.entradas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        //Criacao de uma usuario administrador padrao
        Usuario admin = new Usuario("adm", "adm", true);
        usuarios.add(admin);
    }

    //Classe interna representando uma entrada do diario
    public class EntradaDiario{
        private String texto;
        private Usuario autor;
        private String dataHora;

        //Construtor da classe EntradaDiario
        public EntradaDiario(String entradas, Usuario autor){
            this.texto = entradas;
            this.autor = autor;
            //Captura a data e hora atual em um formato especificado
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatador = DataTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.dataHora = agora.format(formatador);
        }
        //Define o texto da entrada
        public void setTexto(String texto) {
            this.texto = texto;
        }
        //Retorna o texto da entrada
        public String getTexto() {
            return texto;
        }
        //Retorna o autor da entrada
        public Usuario getAutor() {
            return autor;
        }
        //Retorna uma representação em string da entrada
        public String toString() {
            return dataHora + " - " + autor.getUsername() + ": " + texto;
        }
    }
}

