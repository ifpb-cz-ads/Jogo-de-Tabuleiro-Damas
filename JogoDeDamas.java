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
            for(int y=1; y<=8; y++){
                
                //condição para preencher o lado do jogador-máquina
                if((x<=3) && (y%2==x%2)){
                    tabuleiro[x][y] = m;
                    
                //condição para preencher o lado do jogador disputante
                }else if((x>=6) && (y%2==x%2)){
                    tabuleiro[x][y] = j;
                }else{
                    
                    //espaço intermediário de disposição incial do jogo 
                    tabuleiro[x][y] = " ";
                }
                
            }
        }
    }
    //função para exibir o tabuleiro
    public static void exibeTab(String[][] tab){
        
        //exibir as colunas
        System.out.print("tabuleiro:\n\t");
        for(int coluna = 1; coluna <=8; coluna++){
                System.out.print("   " + coluna);
        }
        for(int i=1; i<=8; i++){
            System.out.print("\n\t");
       
            for(int c=1; c<=8; c++){
                System.out.print("----");
            }   
            //exibir as linhas
            System.out.print("\n\t"+ i);
            for(int j=1; j<=8; j++){
                System.out.print("| "+tab[i][j]+" ");
            }
            
        }
    }
    
    public static void configurar(String [] caracteres){
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("@ - # - $ - % - & - *");

        System.out.println("Digite o caractere do adversário:");
        caracteres[0] = entrada.next();
        System.out.println("Digite o seu caractere:");
        caracteres[1] = entrada.next();
        
    }
    
    public static void maquina(String tab[][], String m, String j){
        
        Random ran = new Random();
        int x = ran.nextInt(8)+1;
        int y = ran.nextInt(8)+1;
        
        //condição de peça sorteada ser mesmo referente à máquina
        if(tab[x][y].equals(m)&&(y>1&&y<7)){
            
            if( tab[x+1][y-1].equals(" ") || tab[x+1][y+1].equals(" ") ){
                
                if( tab[x+1][y-1].equals(" ") && tab[x+1][y+1].equals(" ") ){
                    //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada    
                    switch(ran.nextInt(2)){
                        case 1:
                            tab[x+1][y-1] = m;
                            tab[x][y] = " ";
                            break;
                        case 0:
                            tab[x+1][y+1] = m;
                            tab[x][y] = " ";
                            break;
                    }
                //escolha para qual lado ir - esquerda ou direita, respectivamente...
                }else if( tab[x+1][y-1].equals(" ") ){
                        tab[x+1][y-1] = m;
                        tab[x][y] = " ";
                    
                        }else{
                            tab[x+1][y+1] = m;
                            tab[x][y] = " ";
                        }
            //condição para possibilidade de capturar peça do adversário 
            }else if( tab[x+2][y+2].equals(" ")&&tab[x+1][y+1].equals(j) && tab[x+2][y-2].equals(" ")&&tab[x+1][y-1].equals(j) ){
                        //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada    
                        switch(ran.nextInt(2)){
                                case 1:
                                    tab[x+2][y-2] = m;
                                    tab[x][y] = " ";
                                    break;
                                case 0:
                                    tab[x+2][y+2] = m;
                                    tab[x][y] = " ";
                                    break;
                        }

                    }else if( tab[x+2][y-2].equals(" ")&&tab[x+1][y-1].equals(j) ){

                                tab[x+2][y-2] = m;
                                tab[x+1][y-1] = " ";
                                tab[x][y] = " ";

                    }else if( tab[x+2][y+2].equals(" ")&& tab[x+1][y+1].equals(j) ){

                        tab[x+2][y+2] = m;
                        tab[x+1][y+1] = " ";
                        tab[x][y] = " ";

                    }else{
                        //caso não for encontrado espaço para o movimento, usar a recursividade para novas iterações
                        maquina(tab, m, j);
                    }
            
        //caso não for encontrado a peça, usar a recursividade para novas iterações
        }else{
             maquina(tab, m, j);
        }
        
    //fim da função...
    }
    
    //função para movimento das peças do jogador
    public static boolean mover(String tab[][], int x, int y, String m, String j){
        
        //teste para saber se na posição selecionada encontra-se a peça do jogador 
        if(tab[x][y].equals(" ")|| tab[x][y].equals(m)|| x == 1){
            System.out.println("\n\n\tSeleção de peça errada!\n\n");
            System.out.println("Suas peças são as de caracteres "+ j);
            return false;
        }else{
            System.out.println("\nPeça selecionada!\n\nMovimento:\nEsquerda - 1\nDireita - 2");
            Scanner entrada = new Scanner(System.in);
            String mov = entrada.next();
            //teste de seleção de movimento do jogador
            switch(mov){
                //seleção para esquerda
                case "1":
                    if(y == 1 || tab[x-1][y-1].equals(m) && tab[x-2][y-2].equals(m)){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                        return false;
                    }else if(tab[x-1][y-1].equals(" ")){
                        tab[x-1][y-1] = j;
                        tab[x][y] = " ";
                    }else if( tab[x-2][y-2].equals(" ") ){
                        tab[x-2][y-2] = j;
                        tab[x-1][y-1] = " ";
                        tab[x][y] = " ";
                    }
                    break;
                //seleção para direita
                case "2":
                    if(y == 8 || tab[x-1][y+1].equals(m) && tab[x-2][y+2].equals(m) ){
                        System.out.println("\n\n\tMovimento inválido!\n\n");
                        return false;
                    }else if(tab[x-1][y+1].equals(" ")){
                        tab[x-1][y+1] = j;
                        tab[x][y] = " ";
                    }else if( tab[x-2][y+2].equals(" ") ){
                        tab[x-2][y+2] = j;
                        tab[x-1][y+1] = " ";
                        tab[x][y] = " ";
                    }
                    break;
                default:
                    System.out.println("\n\nDados inválidos!\n\n");
                    return false;
            }
        }
        
        //função ocorreu normalmente...
        return true;
    }
    
    public static void jogar(String [][] tab, String m, String j){
        
        String controle;
        int coluna, linha;
        Scanner entrada = new Scanner(System.in);
        
        iniciarTab(tab, m, j);
        exibeTab(tab);
        
        do{
            
            System.out.println("\n\nPosição da peça:");
            System.out.print("\nLinha: ");
            linha = entrada.nextInt();
            
            System.out.print("Coluna: ");
            coluna = entrada.nextInt();
            
            
            if(mover(tab, linha, coluna, m, j)){
                
                System.out.println("\n\nSua jogada:\n\n");
                exibeTab(tab);

                maquina(tab, m, j);
                System.out.println("\n\nJogada do adversário:\n\n");
                exibeTab(tab);
                
            }else{
                System.out.println("\n\nRepita o processo...");
                exibeTab(tab);
            }
            
        System.out.print("\n\n0 - 'sair'\n1- 'continuar'\n\nOpção: ");
        controle = entrada.next();
        
        }while(!controle.equals("0"));
        
    }
    
    public static void comandosRegras(String [][] tab){
        
        System.out.println("****\tCOMANDO E REGRAS DO JOGO****\n\n");
        
        iniciarTab(tab, "#", "@");
        exibeTab(tab);
        
        System.out.println("\n\n****COMANDOS:\n");
        System.out.println("**Seguindo as coordenadas do tabuleiro, deve-se escolher a posição da peça****\n");
        System.out.println("**escolhe-se o número da linha, depois o da coluna - dispostos de 1 a 8, linhas e colunas, respectivamente**\n");
        System.out.println("**após peça selecionada, deve-se escolher para qual lado movê-la**\n");
        System.out.println("**tecla 1 - escolha mover à esquerda\n**tecla 2 - escolhe mover à direita**\n");
        System.out.println("**REGRAS:\num movimento por vez, em direção à base adversária**\n");
        System.out.println("**ao chegar na última linha, a peça torna-se Dama, passa a ter mais libertade de movimentos**\n");
        System.out.println("**próposito do jogo é capturar todas as peças do adversário**\n");
        
    }
    
    public static void funcaoMenu(){
        
        String [][] tab = new String[10][10];
        String [] c = new String[2];
        c[0] = "#";
        c[1] = "@";
        
        iniciarTab(tab, c[0], c[1]);
        
        System.out.print("\n\n\t***Jogo de Damas***\n");
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("\nMENU\n\t1 - jogar\n\t2 - configurar tabuleiro\n\t3 - mostrar tabuleiro");
        System.out.print("\n\t4 - comandos e regras\n\t0 - sair\nOpção: ");
        
        String opcao = entrada.nextLine();
        
        switch(opcao){
            case "1":
                jogar(tab, c[0], c[1]);
                funcaoMenu();
                break;
            case "2":
                configurar(c);
                System.out.println("\n\n\t*****TABULEIRO*****\n\n");
                exibeTab(tab);
                funcaoMenu();
                break;
            case "3":
                System.out.println("\n\n\t*****TABULEIRO*****\n\n");
                exibeTab(tab);
                funcaoMenu();
                break;
            case "4":
                comandosRegras(tab);
                funcaoMenu();
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
