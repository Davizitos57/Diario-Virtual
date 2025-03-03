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

    //Registra um novo usuario no sistema
    public void registrarUsuario(String username, String senha, boolean isAdmin){
        boolean nomeDoUsuarioExiste = false;
        //Verifica se o nome de usuário já existe no sistema
        for(Usuario user : usuarios){
            if(user.getUsername().equals(username)){
                System.out.println("\nEsse nome de usuario já está sendo utilizado, por favor escolha outro\n");
                return;
            }
        }
        //Caso contrario, adiciona o novo usuario a lista de usuarios do sistema
        Usuario novoUsuario = new Usuario(username, senha, isAdmin);
        usuarios.add(novoUsuario);
        System.out.println("\nUsuario registrado com sucesso!\n");
    }

    //Autentica um novo usuario com base em nome de usuario e senha
    public Usuario autenticarUsuario(String username, String senha){
        for(Usuario user : usuarios){
            if(user.getUsername().equals(username) && user.getSenha().equals(senha)){
                return user;
            }
        }
        return null;
    }

    //Realiza o login de um usuario
    public static void login(Usuario usuario){
        usuarioAtual = usuario;
        System.out.prinln("Usuário " + usuario.getUsername() + " foi logado com sucesso");
    }

    //Realiza o logout do usuario atual
    public static void logout(){
        if(usuarioAtual != null){
            System.out.println("Usuário " + usuarioAtual.getUsername() + " saiu do Diário\n");
            usuarioAtual = null;
        }
        else{
            System.out.println("Não há usuário logado no Diário\n");
        }
    }

    //Adiciona uma nova entrada ao diário
}

