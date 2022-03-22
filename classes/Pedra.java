import javax.swing.*;
import java.awt.*;

public abstract class Pedra {
    private Point posicao;
    private final String hash;
    private ImageIcon imagem;
    private JLabel labelPedra;
    private int TAM = 50;

    public Pedra(Point posicao, String img) {
        this.posicao = posicao;
        this.hash = img;
        this.imagem = new ImageIcon(hash);
        this.labelPedra = new JLabel(this.imagem);
    }

    public String getHash() {
        return hash;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public int getTAM() {
        return TAM;
    }

    public void setTAM(int TAM) {
        this.TAM = TAM;
    }

    public JLabel getLabelPedra() {
        return labelPedra;
    }

    public void setLabelPedra(JLabel labelPedra) {
        this.labelPedra = labelPedra;
    }

    public abstract void mover();

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

}
