package jogodedamas;

import java.util.Scanner;

/**
 *
 * @author Átylla
 */


//classe para responsável por todo o controle do jogo (movimentos, escolhas, navegado nas opções)
class Controle{
    
    String sentinela;
    String coluna, linha;
    Scanner entrada = new Scanner(System.in);
    
     //função para inicializar a matriz do tabuleiro
    public void iniciarTab(Tabuleiro tab){
         
        //margem do tabuleiro
        for(int x=0; x<tab.matriz[0].length; x++){
            for(int y=0; y<tab.matriz[0].length; y++){
                    tab.matriz[x][y] = "*";
            }
        }
        //for aninhados para inicializar a matriz do tabuleiro
        for(int x=2; x<tab.matriz[0].length-2; x++){
            for(int y=2; y<tab.matriz[0].length-2; y++){
                //condição para preencher o lado do jogador-máquina
                if((x<=4) && (y%2==x%2)){
                    tab.matriz[x][y] = tab.pecaM;
                //condição para preencher o lado do jogador disputante
                }else if((x>=7) && (y%2==x%2)){
                    tab.matriz[x][y] = tab.pecaJ;
                }else{
                    //espaço intermediário de disposição incial do jogo 
                    tab.matriz[x][y] = tab.vazio;
                }
                
            }
        }
    }
    
    //função para exibir o tabuleiro
    public  void exibirTab(Tabuleiro tab){
        
        //exibir as colunas
        System.out.print("\n\t\t     ");
        for(int c = 1; c <=8; c++){
                System.out.print(c+"     ");
        }
        for(int i=2; i<tab.matriz[0].length-2; i++){
            System.out.print("\n\t\t----------------------------------------------------------");
            //exibir as linhas
            System.out.print("\n\t\t"+ (i-1));
            for(int j=2; j<tab.matriz[0].length-2; j++){
                System.out.print("|  "+tab.matriz[i][j]+"  ");
            }
            
        }
    }
    
    public  void configurar(Tabuleiro tab){
        
        
        
        System.out.println("\n\tCONFIGURAÇÃO DE TABULEIRO");
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("Exemplos: @ - # - X - O");

        System.out.print("\nCaractere do adversário:");
        tab.pecaM = entrada.next();
        System.out.print("\nSeu caractere:");
        tab.pecaJ = entrada.next();
        
        if(tab.pecaM.equals("*") || tab.pecaJ.equals("*") ||tab.pecaM.equals(tab.pecaJ)){
            System.out.println("\n\nCaractere indisponível!!Tente outro...\n\n");
            configurar(tab);
            
        }else{
            iniciarTab(tab);
            System.out.print("\n\t\t*********TABULEIRO**********\n");
            exibirTab(tab);
            System.out.print("\n1 - voltar ao menu\n2 - mudar configuração\nopção: ");
            

            if(entrada.next().equals("2")){
                configurar(tab);
            }
        }
        
        
        
    }
    
    public void jogar(Tabuleiro tab, Jogador jogador, Jogador maquina){
        
        
           
        System.out.println("\nINÍCIO DE PARTIDA!!!!");
        System.out.print("\t\t*********TABULEIRO**********\n");
        exibirTab(tab);
        
        do{
            
            System.out.println("\nPosição da peça:");
            System.out.print("Linha: ");
            linha = entrada.next();
            
            System.out.print("Coluna: ");
            coluna = entrada.next();
            
            //teste de ocorrência do movimento - válido ou não
            if(jogador.mover(tab, Integer.parseInt(linha)+1, Integer.parseInt(coluna)+1)){
                
                System.out.print("\nSua jogada:");
                exibirTab(tab);

                maquina.mover(tab);
                System.out.print("\nJogada do adversário:");
                exibirTab(tab);
                
            }else{
                System.out.println("\n\n\tMovimento inválido!");
                System.out.println("Suas peças são: "+ tab.pecaJ);
                System.out.print("\nTente novamente...\n");
                exibirTab(tab);
            }
            
        System.out.print("\n1 - 'mover'\n0 - 'encerrar partida'\nOpção: ");
        sentinela = entrada.next();
        
        }while(!sentinela.equals("0"));
        
    }
    
    public  void comandosRegras(Tabuleiro tab){
        
        System.out.print("\n****COMANDO E REGRAS DO JOGO****\n");
        System.out.print("\n\t\t*********TABULEIRO**********\n");
        exibirTab(tab);
        
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

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
class Jogador{
    String nome;
    int idade;
    int jogadas;
    String corPeca;
    String tipoPeca;
    int numPecas = 8;
    
     //função para movimento das peças do jogador
    public boolean mover(Tabuleiro tab, int x, int y){
        
        //teste para saber se na posição selecionada encontra-se a peça do jogador 
        if( x<=2 || x>9 || tab.matriz[x][y].equals(tab.vazio)|| tab.matriz[x][y].equals(tab.pecaM) ){
            
            return false;
        }else{
            System.out.print("\t\tPeça selecionada!\n1 - Esquerda\n2 - Direita\nMovimento:");
            Scanner entrada = new Scanner(System.in);
            String mov = entrada.next();
            //teste de seleção de movimento do jogador
            switch(mov){
                //seleção para esquerda
                case "1":
                    if(y <= 2 || tab.matriz[x-1][y-1].equals(tab.pecaM) && tab.matriz[x-2][y-2].equals(tab.pecaM)){
                        return false;
                    }else if(tab.matriz[x-1][y-1].equals(tab.vazio)){
                        tab.matriz[x-1][y-1] = tab.pecaJ;
                        tab.matriz[x][y] = tab.vazio;
                    }else if( tab.matriz[x-2][y-2].equals(tab.vazio) ){
                        tab.matriz[x-2][y-2] = tab.pecaJ;
                        tab.matriz[x-1][y-1] = tab.vazio;
                        tab.matriz[x][y] = tab.vazio;
                    }
                    break;
                //seleção para direita
                case "2":
                    if(y >= 9 || tab.matriz[x-1][y+1].equals(tab.pecaM) && tab.matriz[x-2][y+2].equals(tab.pecaM) ){
                        return false;
                    }else if(tab.matriz[x-1][y+1].equals(tab.vazio)){
                        tab.matriz[x-1][y+1] = tab.pecaJ;
                        tab.matriz[x][y] = tab.vazio;
                    }else if( tab.matriz[x-2][y+2].equals(tab.vazio) ){
                        tab.matriz[x-2][y+2] = tab.pecaJ;
                        tab.matriz[x-1][y+1] = tab.vazio;
                        tab.matriz[x][y] = tab.vazio;
                    }
                    break;
                default:
                    return false;
            }
        }
        
        //função ocorreu normalmente...
        return true;
    }
    //método mover sobrecarregado, sem indicar as posições como argumentos, este refere-se à maquina
    public void mover(Tabuleiro tab){
        
        int x, y;
        //variável para controlar o fluxo das iterações
        boolean controle = true;
        
        //repete até que uma condição seja satisfeita
        while(controle){
            //condição de peça sorteada ser mesmo referente à máquina
            x = (int) (Math.random()*8+2);
            y = (int) (Math.random()*8+2);
            
            //condição da peça selecionada ser válida
            if( tab.matriz[x][y].equals(tab.pecaM) && ( (y>=2) && (y<tab.matriz.length-2)) ){
                
                //condição para saber se há os dois movimentos livres - esquerda ou direita 
                if( tab.matriz[x+1][y-1].equals(tab.vazio) && tab.matriz[x+1][y+1].equals(tab.vazio) ){
                    
                    //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada    
                    switch( (int)(Math.random()*2) ){
                        case 1:
                            tab.matriz[x+1][y-1] = tab.pecaM;
                            tab.matriz[x][y] = tab.vazio;
                            break;
                        case 0:
                            tab.matriz[x+1][y+1] = tab.pecaM;
                            tab.matriz[x][y] = tab.vazio;
                            break;
                    }
                    controle = false;
                
                //condição para possibilidade de capturar peça do adversário 
                }else if( tab.matriz[x+2][y+2].equals(tab.vazio)&&tab.matriz[x+1][y+1].equals(tab.pecaJ) && tab.matriz[x+2][y-2].equals(tab.vazio)&&tab.matriz[x+1][y-1].equals(tab.pecaJ) ){
                        //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada    
                        switch( (int)(Math.random()*2+1)){
                                case 1:
                                    tab.matriz[x+2][y-2] = tab.pecaM;
                                    tab.matriz[x][y] = tab.vazio;
                                    break;
                                case 2:
                                    tab.matriz[x+2][y+2] = tab.pecaM;
                                    tab.matriz[x][y] = tab.vazio;
                                    break;
                        }
                        controle = false;
                        
                //escolha para qual lado de captura ir - esquerda ou direita, respectivamente...
                }else if( tab.matriz[x+2][y-2].equals(tab.vazio)&&tab.matriz[x+1][y-1].equals(tab.pecaJ) ){

                        tab.matriz[x+2][y-2] = tab.pecaM;
                        tab.matriz[x+1][y-1] = tab.vazio;
                        tab.matriz[x][y] = tab.vazio;
                        controle = false;
                }else if( tab.matriz[x+2][y+2].equals(tab.vazio)&& tab.matriz[x+1][y+1].equals(tab.pecaJ) ){

                    tab.matriz[x+2][y+2] = tab.pecaM;
                    tab.matriz[x+1][y+1] = tab.vazio;
                    tab.matriz[x][y] = tab.vazio;
                    controle = false;
                    
                //escolha para qual lado ir - esquerda ou direita, respectivamente...
                }else if( tab.matriz[x+1][y-1].equals(tab.vazio) ){
                        tab.matriz[x+1][y-1] = tab.pecaM;
                        tab.matriz[x][y] = tab.vazio;
                        controle = false;
                }else if( tab.matriz[x+1][y+1].equals(tab.vazio) ){
                    tab.matriz[x+1][y+1] = tab.pecaM;
                    tab.matriz[x][y] = tab.vazio;
                    controle = false;
                }
            }
            
        }
            
    }
    
}
//classe para criar o objeto fundamental do jogo - o tabuleiro
class Tabuleiro{
    String [][] matriz = new String[12][12];
    String pecaM = "#", pecaJ = "@";
    final String vazio = "  ";
        
        
}
//esta classe é par ao jogo em si, onde todos os objetos fazem parte contituinte :)
public class JogoDeDamas {
    
    Jogador jogador = new Jogador();
    Jogador maquina = new Jogador();
    Tabuleiro tab = new Tabuleiro();
    Controle controle = new Controle();
    String opcao;
    
    public void menu(){
        
        controle.iniciarTab(this.tab);

        do{
            System.out.print("\t\t***JOGO DE TABULEIRO - DAMAS***\n");

            System.out.print("MENU\n1 - jogar\n2 - configurar tabuleiro\n3 - mostrar tabuleiro");
            System.out.print("\n4 - comandos e regras\n0 - sair\n\nOpção: ");
            opcao = controle.entrada.next();
            
            switch(opcao){
                case "1":
                    controle.jogar(this.tab, jogador, maquina);
                    controle.iniciarTab(tab);
                    System.out.print("\n\t\tPARTIDA ENCERRADA...");
                    break;
                case "2":
                    controle.configurar(this.tab);
                    break;
                case "3":
                    System.out.print("\n\t\t*********TABULEIRO**********\n");
                    controle.exibirTab(this.tab);
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
        }while(!this.opcao.equals("0"));
    }
    
    public static void main(String[] args) {
        
        JogoDeDamas jogo = new JogoDeDamas();
               
        jogo.menu();
        
    }
    
}
