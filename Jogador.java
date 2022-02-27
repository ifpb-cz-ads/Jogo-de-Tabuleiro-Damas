import java.util.Scanner;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador{
    private String nome;
    private int idade;
    protected int jogadas;
    protected int numPecas;
    
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
    
}
