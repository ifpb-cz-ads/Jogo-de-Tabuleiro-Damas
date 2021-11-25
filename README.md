/* descrição:
A ideia e o propósito dessa empreitada é desenvolver um jogo simples, onde a elucubração e idealização inferem num simulacro de um jogo clássico: de tabuleiro, o famigerado jogo de Damas. Partindo desse pressuposto de desenvolvimento, o intuito é de colocar em prática essa ideia usando os conceitos de Programação Orientada a Objetos, tais como: encapsulamento de dados, herança e polimorfismo; esses constituindo o cerne e o objetivo principal da disciplina. 
Portanto, seguindo essa lógica, a resolução passa por utilização de ferramentas adquiridas para realização, essas já dispostas pela linguagem Java e suas bibliotecas, a GUI (ainda por se definir a mais adequada) além do ambiente de desenvolvimento, o qual será o NetBeans. A parte gráfica, para mais especificidade, terá sua renderização por meio de bibliotecas Swing e AWT. 
*/

package jogodedamas;

/**
 *
 * @author Átylla
 */
public class JogoDeDamas {


    public static void main(String[] args) {
        
        //declarando as variáveis para implementar o tabuleiro do jogo
        char caractereM = '@', caractereJ = '#';
        
        //declarando a mat para implementar o tabuleiro do jogo
        char tabuleiro [][] = new char[32][32];
        
        System.out.print("\t***Jogo de Damas***\n\n");
        
        //for aninhados para inicializar a matriz do tabuleiro
        for(int x=0; x<8; x++){
            if(x<3){
                //for para preencher o lado do jogador-máquina
                for(int y=0; y<8; y++){
                    if((y%2)==(x%2)){
                        tabuleiro[x][y] = caractereM;
                    }else{
                        tabuleiro[x][y] = ' ';
                    }
                }
            }else{
                if(x>4){
                    //for para preencher o lado do jogador em disputa
                    for(int y=0; y<8; y++){
                        if((y%2)==(x%2)){
                            tabuleiro[x][y] = caractereJ;
                        }else{
                            tabuleiro[x][y] = ' ';
                        }
                    }
                }else{
                    //espaço intermediário de disposição incial do jogo 
                    for(int y=0; y<8; y++){
                        tabuleiro[x][y] = ' ';
                    }
                }
                }
   
        }
        
        
        System.out.println("tabuleiro:\n\n");
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(" " + tabuleiro[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("\n\n");
    }
    
}


