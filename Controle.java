package com.company;

import java.util.Scanner;

//classe para responsável por todo o controle do jogo (movimentos, escolhas, navegado nas opções)
class Controle{

    private String sentinela, coluna, linha;

    Controle(){
        this.sentinela = "";
        this.coluna = "";
        this.linha = "";

    }

    public String input(){

        return new Scanner(System.in).next();
    }

    public void configurar(Tabuleiro tab){

        System.out.println("\n\tCONFIGURAÇÃO DE TABULEIRO");
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("Exemplos: @ - # - X - O");

        System.out.print("\nCaractere do adversário:");
        tab.setPecaM(input());
        System.out.print("\nSeu caractere:");
        tab.setPecaJ(input());

        if(tab.getPecaM().equals("*") || tab.getPecaJ().equals("*") ||tab.getPecaM().equals(tab.getPecaJ())){
            System.out.println("\n\nCaractere indisponivel!!Tente outro...\n\n");
            configurar(tab);

        }else{
            tab.iniciarTab();
            System.out.print("\n\t\t*********TABULEIRO**********\n");
            tab.exibirTab();
            System.out.print("\n1 - voltar ao menu\n2 - mudar configuração\nopcao: ");

            if(input().equals("2")){
                configurar(tab);
            }
        }

    }

    public boolean validarPeca(Tabuleiro tab, int [] posicao){
        int x = posicao[0];
        int y = posicao[1];

        if(x<=2 || x>9 || tab.matchMatriz(x,y,tab.getVazio())|| tab.matchMatriz(x,y,tab.getPecaM()) ){
            return false;
        }
        if( tab.matchMatriz(x-1, y-1,tab.getPecaJ()) && tab.matchMatriz(x-1, y+1,tab.getPecaJ()) ){
            return false;
        }
        return tab.matchMatriz(x - 2, y - 2, tab.getVazio()) || tab.matchMatriz(x - 2, y + 2, tab.getVazio());
    }

    public int[] selecionarPeca(){
        System.out.println("\nPosição da peça:");
        System.out.print("Linha: ");
        this.linha = input();

        System.out.print("Coluna: ");
        this.coluna = input();

        return new int []{Integer.parseInt(linha)+1, Integer.parseInt(coluna)+1};
    }

    //método para iniciar uma partida
    public void jogar(Tabuleiro tab, Jogador jogador, Maquina maquina){

        System.out.println("\n\t\t\t\t****INICIO DE PARTIDA****\n");

        do{
            System.out.print("\t\t\t\t*********TABULEIRO**********\n");
            tab.exibirTab();
            int [] posicao = selecionarPeca();

            //teste para saber se na posição selecionada encontra-se a peça do jogador
            if( validarPeca(tab, posicao)){
                System.out.print("\t\tPeça selecionada!");
                jogador.mover(tab, posicao);

                System.out.print("\nSua jogada:");
                tab.exibirTab();

                maquina.mover(tab, posicao);
                System.out.print("\nJogada do adversário:");
                tab.exibirTab();

            }else{

                System.out.println("\n\n\tMovimento inválido!");
                System.out.print("\nTente novamente...\n");

            }

            System.out.print("\n1 - 'mover'\n0 - 'encerrar partida'\nOpção: ");
            sentinela = input();

        }while(!sentinela.equals("0"));

    }

    public  void comandosRegras(Tabuleiro tab){

        System.out.print("\n****COMANDO E REGRAS DO JOGO****\n");
        System.out.print("\n\t\t\t\t*********TABULEIRO**********\n");
        tab.exibirTab();

        System.out.print("\n\n\t\t****COMANDOS****\n");
        System.out.print("**seguindo as coordenadas do tabuleiro, deve-se escolher a posição da peça**\n");
        System.out.print("**escolhe-se o número da linha, depois o da coluna - dispostos de 1 a 8, linhas e colunas, respectivamente**\n");
        System.out.print("**após peça selecionada, deve-se escolher para qual lado movê-la**\n");
        System.out.print("**tecla 1 - escolher movimento à esquerda**\n**tecla 2 - escolher movimento à direita**\n");
        System.out.print("\n\t\t****REGRAS****\n**só é permitido um unico movimento por vez, em direção à base adversária**\n");
        System.out.print("**seguindo a disposição inicial das peças, move-se para esquerda ou direita, em direção diagonal**\n");
        System.out.print("**ao chegar na última linha, a peça torna-se Dama, passa a ter mais libertade de movimentos**\n");
        System.out.print("**próposito do jogo é capturar todas as peças do adversário**\n\n");

    }

}

