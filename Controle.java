import java.util.Scanner;

//classe para responsável por todo o controle do jogo (movimentos, escolhas, navegado nas opções)
public class Controle{
    
    private String sentinela, coluna, linha;
    private Scanner entrada;
    
    Controle(){
        this.sentinela = "";
        this.coluna = "";
        this.linha = "";
        this.entrada = new Scanner(System.in);
    }
    
    Controle(String sentinela, String coluna, String linha){
        this.sentinela = sentinela;
        this.coluna = coluna;
        this.linha = linha;
        this.entrada = new Scanner(System.in);
    }

    public String input(){
		return this.entrada.next();
    }
    
    public void configurar(Tabuleiro tab){
        
        System.out.println("\n\tCONFIGURAÇÃO DE TABULEIRO");
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("Exemplos: @ - # - X - O");

        System.out.print("\nCaractere do adversário:");
        tab.setPecaM(entrada.next());
        System.out.print("\nSeu caractere:");
        tab.setPecaJ(entrada.next());
        
        if(tab.getPecaM().equals("*") || tab.getPecaJ().equals("*") ||tab.getPecaM().equals(tab.getPecaJ())){
            System.out.println("\n\nCaractere indisponivel!!Tente outro...\n\n");
            configurar(tab);
            
        }else{
            tab.iniciarTab();
            System.out.print("\n\t\t*********TABULEIRO**********\n");
            tab.exibirTab();
            System.out.print("\n1 - voltar ao menu\n2 - mudar configuração\nopcao: ");

            if(entrada.next().equals("2")){
                configurar(tab);
            }
        }
        
    }
    
    public boolean validarPeca(Tabuleiro tab, int [] posicao){
        int x = posicao[0];
        int y = posicao[1];
        
        if(x<=2 || x>9 || tab.matchMatriz(x,y,tab.getVazio())|| tab.matchMatriz(x,y,tab.getPecaM()) ){
            return false;
        }else if( tab.matchMatriz(x-1, y-1,tab.getPecaJ()) && tab.matchMatriz(x-1, y+1,tab.getPecaJ()) ){
            return false;
        }else if( (!tab.matchMatriz(x-1, y-1,tab.getVazio()) ) && (!tab.matchMatriz(x-1, y+1,tab.getVazio()) ) && (!tab.matchMatriz(x-1, y-2,tab.getVazio()) ) && (!tab.matchMatriz(x-1, y+2,tab.getVazio()) ) ){
            return false;
        }else{
            return true;
        }
    }
    
    public int[] selecionarPeca(){
        System.out.println("\nPosição da peça:");
        System.out.print("Linha: ");
        linha = entrada.next();
        
        System.out.print("Coluna: ");
        coluna = entrada.next();
        
        int x = Integer.parseInt(linha)+1;
        int y = Integer.parseInt(coluna)+1;
        
        return new int []{x, y};
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

                maquina.mover(tab);
                System.out.print("\nJogada do adversário:");
                tab.exibirTab();
               
            }else{
            
                System.out.println("\n\n\tMovimento inválido!");
                System.out.print("\nTente novamente...\n");
                
            }
            
        System.out.print("\n1 - 'mover'\n0 - 'encerrar partida'\nOpção: ");
        sentinela = entrada.next();
        
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
