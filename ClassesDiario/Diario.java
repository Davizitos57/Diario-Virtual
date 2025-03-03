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
}
