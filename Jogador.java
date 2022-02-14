import java.util.Scanner;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador{
    private String nome;
    private int idade;
    private int jogadas;
    private int numPecas;
    
    Jogador(){
        
    }
    
    Jogador(String nome, int idade, int jogadas, int numPecas){
        this.nome = nome;
        this.idade = idade;
        this.jogadas = jogadas;
        this.numPecas = numPecas;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    public int getIdade(){
        return this.idade;
    }
    
    public void setJogadas(){
        this.jogadas++;
    }
    
    public void setJogadas(int zerar){
        this.jogadas = 0;
    }
    
    public int getJogadas(){
        return this.jogadas;
    }
    
    public void setNumPecas(){
        this.numPecas--;
    }
    
    public void setNumPecas(int iniciar){
        this.numPecas = 8;
    }
    
    public int getNumPecas(){
        return this.numPecas;
    }
    
     //função para movimento das peças do jogador
    public boolean mover(Tabuleiro tab, int x, int y){
        
        //teste para saber se na posição selecionada encontra-se a peça do jogador 
        if( x<=2 || x>9 || tab.matchMatriz(x,y,tab.getVazio())|| tab.matchMatriz(x,y,tab.getPecaM()) ){
            
            return false;
        }else{
            System.out.print("\t\tPeça selecionada!\n1 - Esquerda\n2 - Direita\nMovimento:");
            Scanner entrada = new Scanner(System.in);
            String mov = entrada.next();
            //teste de seleção de movimento do jogador
            switch(mov){
                //seleção para esquerda
                case "1":
                    if(y <= 2 || tab.matchMatriz(x-1, y-1,tab.getPecaM()) && tab.matchMatriz(x-2, y-2,tab.getPecaM())){
                        return false;
                    }else if( tab.matchMatriz(x-1, y-1,tab.getVazio() )){
                        tab.setMatriz(x-1,y-1,tab.getPecaJ());
                        tab.setMatriz(x,y,tab.getVazio());
                    }else if( tab.matchMatriz(x-2, y-2,tab.getVazio() )){
                        tab.setMatriz(x-2,y-2,tab.getPecaJ());
                        tab.setMatriz(x-1,y-1,tab.getVazio());
                        tab.setMatriz(x,y,tab.getVazio());
                    }
                    break;
                //seleção para direita
                case "2":
                    if(y >= 9 || tab.matchMatriz(x-1, y+1,tab.getPecaM()) && tab.matchMatriz(x-2, y+2,tab.getPecaM()) ){
                        return false;
                    }else if(tab.matchMatriz(x-1, y+1,tab.getVazio())){
                        tab.setMatriz(x-1,y+1,tab.getPecaJ());
                        tab.setMatriz(x,y,tab.getVazio());
                    }else if( tab.matchMatriz(x-2, y+2,tab.getVazio()) ){
                        tab.setMatriz(x-2,y+2,tab.getPecaJ());
                        tab.setMatriz(x-1,y+1,tab.getVazio());
                        tab.setMatriz(x,y,tab.getVazio());
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
        
        int x=0, y=0;
        
        //variável para controlar o fluxo das iterações
        boolean controle = true;
        
        //repete até que uma condição seja satisfeita
        while(controle){
            //condição de peça sorteada ser mesmo referente à máquina
            x = (int) (Math.random()*8+2);
            y = (int) (Math.random()*8+2);
            
            //condição da peça selecionada ser válida
            if( tab.matchMatriz(x,y,tab.getPecaM()) && ( (y>=2) && (y<=10) )){
                
                //condição para saber se há os dois movimentos livres - esquerda ou direita 
                if( tab.matchMatriz(x+1, y-1,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getVazio()) ){
                    
                    //sortear para qual lado será o movimento da máquina, caso os dois lados estejam disponíveis para jogada    
                    switch( (int)(Math.random()*2+1) ){
                        case 1:
                            tab.setMatriz(x+1,y-1,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());
                            break;
                        case 2:
                            tab.setMatriz(x+1,y+1,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());;
                            break;
                    }
                    controle = false;
                
                //condição para possibilidade de capturar peça do adversário 
                }else if( tab.matchMatriz(x+2, y+2,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getPecaJ()) && tab.matchMatriz(x+2, y-2,tab.getVazio()) && tab.matchMatriz(x+1, y-1,tab.getPecaJ())){
                        //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada    
                        switch( (int)(Math.random()*2+1)){
                                case 1:
                                    tab.setMatriz(x+2,y-2,tab.getPecaM());
                                    tab.setMatriz(x,y,tab.getVazio());
                                    break;
                                case 2:
                                    tab.setMatriz(x+2, y+2,tab.getPecaM());
                                    tab.setMatriz(x,y,tab.getVazio());
                                    break;
                        }
                        controle = false;
                        
                //escolha para qual lado de captura ir - esquerda ou direita, respectivamente...
                }else if( tab.matchMatriz(x+2, y-2,tab.getVazio()) && tab.matchMatriz(x+1, y-1,tab.getPecaJ()) ){

                        tab.setMatriz(x+2,y-2,tab.getPecaM());
                        tab.setMatriz(x+1,y-1,tab.getVazio());
                        tab.setMatriz(x,y,tab.getVazio());
                        controle = false;
                }else if( tab.matchMatriz(x+2, y+2,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getPecaJ()) ){

                    tab.setMatriz(x+2,y+2,tab.getPecaM());
                    tab.setMatriz(x+1,y+1,tab.getVazio());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;
                    
                //escolha para qual lado ir - esquerda ou direita, respectivamente...
                }else if( tab.matchMatriz(x+1, y-1,tab.getVazio()) ){
                        tab.setMatriz(x+1,y-1,tab.getPecaM());
                        tab.setMatriz(x,y,tab.getVazio());
                        controle = false;
                }else if( tab.matchMatriz(x+1,y+1,tab.getVazio()) ){
                    tab.setMatriz(x+1,y+1,tab.getPecaM());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;
                }
            }
            
        }
            
    }
    
}