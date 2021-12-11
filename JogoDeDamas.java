package jogodedamas;

import java.util.Random;
import java.util.Scanner;
//import javax.swing.JFrame;

/**
 *
 * @author Átylla
 */

public class JogoDeDamas {
    /*
     public static void janela(){
        JFrame jan = new JFrame();
        jan.setTitle("Jogo de tabuleiro - Damas");
        jan.setSize(600, 600);
        jan.setLocationRelativeTo(null);
        jan.setResizable(false);
        jan.setVisible(true);
        jan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
    
    //função para inicializar a matriz do tabuleiro
    public static void iniciarTab(String tabuleiro[][], String m, String j){
        for(int x=0; x<10; x++){
            for(int y=0; y<10; y++){
                if(x==0||x==9){
                    tabuleiro[x][y] = "*";
                }
                if(y==0||y==9){
                    tabuleiro[x][y] = "*";
                }
            }
        }
        //for aninhados para inicializar a matriz do tabuleiro
        for(int x=1; x<=8; x++){  
            if(x<=3){
                //for para preencher o lado do jogador-máquina
                for(int y=1; y<=8; y++){
                    if((y%2)==(x%2)){
                        tabuleiro[x][y] = m;
                    }else{
                        tabuleiro[x][y] = " ";
                    }
                }
            }else{
                if(x>=6){
                    //for para preencher o lado do jogador em disputa
                    for(int y=1; y<=8; y++){
                        if((y%2)==(x%2)){
                            tabuleiro[x][y] = j;
                        }else{
                            tabuleiro[x][y] = " ";
                        }
                    }
                }else{
                    //espaço intermediário de disposição incial do jogo 
                    for(int y=1; y<=8; y++){
                        tabuleiro[x][y] = " ";
                    }
                }
                }
   
        }
    }
    //função para exibir o tabuleiro
    public static void exibeTab(String[][] tab){
        
        System.out.print("tabuleiro:\n\t");
        for(int coluna = 1; coluna <=8; coluna++){
                System.out.print("   " + coluna);
        }
        for(int i=1; i<=8; i++){
            System.out.print("\n\t");
       
            for(int c=1; c<=8; c++){
                System.out.print("----");
            }            
            System.out.print("\n\t"+ i);
            for(int j=1; j<=8; j++){
                System.out.print("| "+tab[i][j]+" ");
            }
            
        }
    }
    //função de movimento do adversário-máquina, com movimentos pseudoaleatórios...
    public static void maquina(String tab[][], String m){
        Random ran = new Random();
        
        for(int x=1; x<=8; x++){
            for(int y=1; y<=8; y++){
                if(tab[x][y].equals(m)){
                    if(tab[x+1][y-1].equals(" ") && tab[x+1][y+1].equals(" ")){
                        switch(ran.nextInt(2)){
                            case 1:
                                tab[x+1][y-1] = tab[x][y];
                                tab[x][y] = " ";
                                x = y = 9;
                                break;
                            case 0:
                                tab[x+1][y+1] = tab[x][y];
                                tab[x][y] = " ";
                                x = y = 9;
                                break;
                        }
                    }else if(tab[x+1][y-1].equals(" ")){
                            tab[x+1][y-1] = tab[x][y];
                            tab[x][y] = " ";
                            x = y = 9;
                    }else if(tab[x+1][y+1].equals(" ")){
                        tab[x+1][y+1] = tab[x][y];
                        tab[x][y] = " ";
                        x = y = 9;
                    }
                    
                }
            }
        }
    }
    
    //função movimento do jogador
    public static void mover(String tab[][], int x, int y, String m, String j){
        
        String teste = tab[x][y];
        if(teste.equals(" ")||(teste.equals(m))){
            System.out.println("\n\n\tSeleção de peça errada!\n\n");
            System.out.println("Suas peças são as de caracteres "+ j);
        }else{
            System.out.println("\nPeça selecionada!\n\nMovimento:\nEsquerda - 1\nDireita - 0");
            Scanner entrada = new Scanner(System.in);
            int mov = entrada.nextInt();
            switch(mov){
                case 1:
                    if(x == 1 || y == 1){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                    }else if(tab[x-1][y-1].equals(m)&& tab[x-2][y-2].equals(m)){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                    }else if(tab[x-1][y-1].equals(" ")){
                        tab[x-1][y-1] = tab[x][y];
                        tab[x][y] = " ";
                    }else if( tab[x-2][y-2].equals(" ") ){
                        tab[x-2][y-2] = tab[x][y];
                        tab[x][y] = " ";
                        tab[x-1][y-1] = " ";
                    }
                    break;
                case 0:
                    if(x == 1 || y == 8){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                    }else if(tab[x-1][y+1].equals(m)&& tab[x-2][y+2].equals(m)){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                    }else if(tab[x-1][y+1].equals(" ")){
                        tab[x-1][y+1] = tab[x][y];
                        tab[x][y] = " ";
                    }else if( tab[x-2][y+2].equals(" ") ){
                        tab[x-2][y+2] = tab[x][y];
                        tab[x][y] = " ";
                        tab[x-1][y+1] = " ";
                    }
                    break;
                default:
                    System.out.println("\n\nDados inválidos!\n\nRepita o processo...\n\n");
                    break;
            }
        }
        
    }
    //função que chama os outros métodos e cria a interatividade do jogo
    public static void jogar(){
        String [][] tab = new String[10][10];
        String controle, m, j;
        int coluna, linha;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("@ - # - $ - % - & - *");

        System.out.println("Digite o caractere do adversário:");
        m = entrada.next();
        System.out.println("Digite o seu caractere:");
        j = entrada.next();
        
        iniciarTab(tab, m, j);
        exibeTab(tab);
        
        do{
            
            System.out.println("\n\nPosição da peça:");
            System.out.print("\nLinha: ");
            linha = entrada.nextInt();
            
            System.out.print("Coluna: ");
            coluna = entrada.nextInt();
            
            
            mover(tab, linha, coluna, m, j);
            System.out.println("\n\nSua jogada:\n\n");
            exibeTab(tab);
            
            maquina(tab, m);
            System.out.println("\n\nJogada do adversário:\n\n");
            exibeTab(tab);
            
            System.out.print("\n\nDigite 'sair' ou 'continuar'\n\nOpção: ");
            controle = entrada.next();
            
        }while(!controle.equalsIgnoreCase("sair"));
        
    }
    //função menu para ser chamada no método main
    public static void funcaoMenu(){
        
        System.out.print("\t***Jogo de Damas***\n\n");
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("\n1 - jogar\n0 - sair\nOpção: ");
        
        String opcao = entrada.nextLine();
        
        switch(opcao){
            case "1":
                jogar();
                break;
            case "0":
                System.out.println("\n\nPrograma encerrado...\n\n");
                break;
            default:
                System.out.println("\n\nDados inválidos!\nTente novamente\n");
                funcaoMenu();
                break;
        }
    }
   
    public static void main(String[] args) {
        funcaoMenu();
        
    }
    
}
