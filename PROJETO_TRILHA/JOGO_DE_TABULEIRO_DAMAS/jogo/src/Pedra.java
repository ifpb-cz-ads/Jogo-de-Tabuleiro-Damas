import javax.swing.*;
import java.awt.*;

public class Pedra extends JLabel{
    protected String hash;
    protected JLabel labelPedra;
    protected int TAM;

    Pedra(Point posicao, String img) {
        this.hash = img;
        this.TAM = 50;
        this.labelPedra = new JLabel(new ImageIcon(getClass().getResource("/imagens/"+hash+".png")));
        this.labelPedra.setBounds(posicao.x, posicao.y, this.TAM, this.TAM);
    }

    public String getHash(){
        return this.hash;
    }

    public boolean isDama(){
        return (hash.equals("DAMAGAMER") || hash.equals("DAMACPU"));
    }

    public boolean tornarDama(){
        return (this.labelPedra.getLocation().y == 100 && hash.equals("GAMER")) || (this.labelPedra.getLocation().y == 450 && hash.equals("CPU"));

    }

    public void pedraMove(Point ponto){
        setPosicao(ponto);
    }

    public JLabel getLabel() {
        return labelPedra;
    }

    public void setImage() {
        hash = "DAMA"+this.hash;
        this.labelPedra.setIcon(new ImageIcon(getClass().getResource("/imagens/"+hash+".png")));
    }

    public Point getPosicao() {
        return this.labelPedra.getLocation();
    }

    public void setPosicao(Point posicao) {
        this.labelPedra.setLocation(posicao);
    }



}
