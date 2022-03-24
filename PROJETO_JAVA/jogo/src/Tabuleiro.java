import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

//classe para criar o objeto fundamental do jogo - o tabuleiro
public class Tabuleiro {

    private final ArrayList<Pedra> pecas;
    private final String HASH_LIVRE;
    JLabel labelTab;

    Tabuleiro(){
        this.labelTab = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagens/TABULEIRO.jpg"))));
        this.pecas = new ArrayList<>(24);
        this.HASH_LIVRE = " ";
        iniciarTab();
    }

    public ArrayList<Pedra> getPecas() {
        return pecas;
    }


    //função para inicializar a matriz do tabuleiro
    public void iniciarTab(){
        String hash;
        for(int x=0, pexelY=0; x<8; x++, pexelY+=50){
            for(int y=0, pexelX=0; y<8; y++, pexelX+=50){
                hash = (x<3) ? "CPU" : ( (x>4) ? "GAMER": HASH_LIVRE );
                if((!hash.equals(HASH_LIVRE))) {
                    if (x % 2 != y % 2) {
                        pecas.add(new Peao(new Point(pexelX,pexelY),hash));
                    }
                }else{
                    break;
                }
            }
        }
    }

}
