package com.company;

//esta classe é par ao jogo em si, onde todos os objetos fazem parte contituinte :)
public class Jogo {

    final private Jogador jogador;
    final private Maquina maquina;
    final private Tabuleiro tab;
    final private Controle controle;

    Jogo(){
        this.jogador = new Jogador();
        this.tab = new Tabuleiro();
        this.maquina = new Maquina();
        this.controle = new Controle();
    }

    public void menu(){
        String opcao;
        do{
            System.out.print("\t\t***JOGO DE TABULEIRO - DAMAS***\n");

            System.out.print("MENU\n1 - jogar\n2 - configurar tabuleiro\n3 - mostrar tabuleiro");
            System.out.print("\n4 - comandos e regras\n0 - sair\n\nOpção: ");
            opcao = controle.input();

            switch(opcao){
                case "1":
                    controle.jogar(this.tab, this.jogador, this.maquina);
                    tab.iniciarTab();
                    System.out.print("\n\t\t\tPARTIDA ENCERRADA...");
                    break;
                case "2":
                    controle.configurar(this.tab);
                    break;
                case "3":
                    System.out.print("\n\t\t\t\t*********TABULEIRO**********\n");
                    tab.exibirTab();
                    break;
                case "4":
                    controle.comandosRegras(this.tab);
                    break;
                case "0":
                    System.out.print("\n\nPrograma encerrado...\n\n");
                    break;
                default:
                    System.out.print("\n\nDados inválidos!\nTente novamente\n\n");
                    break;
            }
            System.out.print("\n\n");
        }while(!opcao.equals("0"));
    }

    public static void main(String[] args) {

        new Jogo().menu();

    }
}
