package ClassesDiario;

public class Usuario {
    private String username;
    private String senha;
    private boolean isAdmin;

    //Cria um construtor para o Usuario atrelando-o a um username, senha e caso senha ADM verificando a veracidade
    public Usuario(String username, String senha, boolean isAdmin) {
        this.username = username;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    //Retorna o valor booleano de ADM, checando se é verdadeiro ou falso
    public boolean isAdmin() {
        return isAdmin;
    }

    //Retorna o valor string de username, comparando se é igual a string escrita pelo Usuario
    public String getUsername() {
        return username;
    }

    //Retorna o valor de string de senha, comparando se é igual a string escrita pelo Usuario
    public String getSenha() {
        return senha;
    }
}