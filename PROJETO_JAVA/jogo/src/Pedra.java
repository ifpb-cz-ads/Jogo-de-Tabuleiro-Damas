import javax.swing.*;
import java.awt.*;

public abstract class Pedra extends JLabel{
    protected Point posicao;
    protected final String hash;
    protected ImageIcon imagem;
    protected JLabel labelPedra;
    protected int TAM;
    protected boolean status;

    public Pedra(Point posicao, String img) {
        this.posicao = posicao;
        this.hash = img;
        this.TAM = 50;
        this.status = false;
        this.imagem = new ImageIcon(getClass().getResource("/imagens/"+hash+".png"));
        this.labelPedra = new JLabel(this.imagem);
        this.labelPedra.setBounds(posicao.x, posicao.y, this.TAM, this.TAM);
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public JLabel getLabel() {
        return labelPedra;
    }

    public void setLabel() {
        this.labelPedra.setBounds(posicao.x, posicao.y, this.TAM, this.TAM);
    }

    public abstract void mover(Point p);

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao.x = posicao.x;
        this.posicao.y = posicao.y;
    }

}
