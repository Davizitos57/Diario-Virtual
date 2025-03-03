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
    public void adicionarEntrada(String texto){
        if(usuarioAtual != null){
            EntradaDiario entrada = new EntradaDiario(texto, usuarioAtual);
            entradas.add(entrada);
            System.out.println("Entrada adicionada pelo perfil " + usuarioAtual.getUsername());
        }
        else{
            System.out.println("Nenhum usuário está logado, tente novamente mais tarde.\n");
        }
    }

    //Visualiza as entradas do usuário atualmente logado
    public void visualizarEntradas(){
        boolean encontrou = false;
        for(EntradaDiario entrada : entradas){
            if(entrada.getAutor().equals(usuarioAtual)){
                if(!encontrou){
                    System.out.println("\nEntradas do Diário: ");
                    encontrou = true;
                }
                System.out.println(entrada);
            }
        }
        if(!encontrou){
            System.out.println("\nVocê não possui entradas no diário");
        }
    }

    //Pesquisa entradas do usuario atual atraves de palavras-chave
    public void pesquisarEntradas(String palavraChave){
        boolean encontrou = false;
        for(EntradaDiario entrada : entradas){
            if(entrada.getAutor().equals(username) && entrada.getAutor().toLowerCase().contains(palavraChave.toLowerCase())){
                System.out.println("\nEntradas encontradas: ");
                encontrou = true;
            }
            System.out.println(entrada);
        }
        if(!encontrou){
            System.out.println("Nenhuma entrada encontrada com a palavra-chave: " + palavraChave);
        }
    }

    //Visualiza todas as entradas no diário (acessível apenas pelo administrador)
    public void visualizarEntradasAdmin(){
        if(entradas.isEmpty()){
            System.out.println("\nO Diário está vázio.");
        }
        else{
            System.out.println("\nEntradas do Diário: ");
            entradas.forEach(System.out::println);
        }
    }

    //Substitui a última entrada de um Usuario específico (acessível apenas pelo administrador)
    public void substituirEntradasAdmin(String usuario, String novaEntrada){
        for(int i = entradas.size() - 1; i>=0; i--){
            EntradaDiario entrada = entradas.get(i);
            if(entrada.getAutor().getUsername().equals(usuario)){
                entrada.setTexto(novaEntrada);
                System.out.println("\nEntrada editada com sucesso.");
                return;
            }
        }
        System.out.println("Usuário inválido ou não há entradas para sobrescrever.");
    }

    //Deleta todas as entradas de um Usuario específico (acessível apenas pelo administrador)
    public void deletarEntradasAdmin(String usuario){
        entradas.removeIf(entrada -> entrada.getAutor().getUsername().equals(usuario));
        System.out.pritnln("Entradas removidas");
    }

    //Sobrescreve a última entrada de um Usuario específico (acessível apenas pelo administrador)
    public void sobrescreverEntradasAdmin(String usuario, String novaEntrada){
        boolean nomeDoUsuarioExiste = false;
        for(int i = entradas.size() - 1; i>=0; i--){
            EntradaDiario entrada = entradas.get(i);
            if(entrada.getAutor().getUsername().equals(usuario)){
                entrada.setTexto(entrada.getTexto() + " " + novaEntrada);
                nomeDoUsuarioExiste = true;
                System.out.println("\nEntrada editada com sucesso.");
                break;
            }
        }
        if(!nomeDoUsuarioExiste){
            System.out.println("\nUsuário inválido.");
        }
    }

    //Conta o número de palavras de uma entrada
    public int contarPalavrasEntrada(String entrada){
        if(entrada == null || entrada.isEmpty()){
            return 0;
        }
        return entrada.split("\\s+").length;
    }

    //Conta o número total de palavras nas entradas do Usuario atual
    public int contarPalavrasTotais(){
        return entradas.stream()
                .filter(entrada -> entrada.getAutor().equals(usuarioAtual))
                .mapToInt(entrada -> contarPalavrasEntrada(entrada.getTexto()))
                .sum();
    }
}

