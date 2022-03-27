import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

//classe para criar o objeto fundamental do jogo - o tabuleiro
public class Tabuleiro {

    private final ArrayList<Pedra> pecas;
    JLabel labelTab;
    protected final int TABPOSX=100;
    protected final int TABPOSY=100;
    protected final int TAM=400;
    Tabuleiro(){
        this.labelTab = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagens/TABULEIRO.jpg"))));
        this.labelTab.setBounds(TABPOSX, TABPOSY , TAM, TAM);
        this.pecas = new ArrayList<>(24);
        iniciarTab();
    }

    public ArrayList<Pedra> getPecas() {
        return pecas;
    }

    //função para inicializar a matriz do tabuleiro
    public void iniciarTab(){
        for(int y=0, pexelX=TABPOSX; y<8; y++, pexelX+=50){
            for(int x=0, pexelY=TABPOSY; x<8; x++, pexelY+=50){
                if(x<3 && (x % 2 != y % 2)){
                    pecas.add(new Peao(new Point(pexelX,pexelY),"CPU"));
                }if(x>4 && (x % 2 != y % 2)){
                    pecas.add(new Peao(new Point(pexelX,pexelY),"GAMER"));
                }
            }
        }
    }

}
