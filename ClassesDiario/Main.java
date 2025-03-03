package ClassesDiario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Inicializando scanner, objetos e variáveis
        Scanner scanner = new Scanner(System.in);
        Diario diario = new Diario();

        //Variavel responsável por manter o usuário logado, ou não logado
        Usuario usuarioLogado = null;

        //Variavel que confirma informações de login e controla o looo do menu 1
        boolean autenticado_usuario = false;

        //Variavel que controla o loop dos menus
        boolean rodando = true;

        //Loop geral
        while(rodando){
            //Loop do Menu 1
            while(!autenticado_usuario){
                //Opções do Menu 1
                System.out.println("Olá, seja bem-vindo ao Diário Virtual!");
                System.out.println("1. Registrar novo usuário");
                System.out.println("2. Login");
                System.out.println("3. Sair do Diário");
                System.out.print("Escolha uma opção: ");
                String opcao1 = scanner.nextLine();

                switch(opcao1){
                    case "1":

                        break
                }
            }
        }
    }
}
