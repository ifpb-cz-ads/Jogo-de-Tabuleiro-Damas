import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

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


    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
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

    public JLabel getLabel() {
        return labelPedra;
    }

    public void setLabel() {
        this.labelPedra.setBounds(posicao.x, posicao.y, this.TAM, this.TAM);
    }

    public abstract void mover();

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {

        this.posicao.x = posicao.x;
        this.posicao.y = posicao.y;
    }

}
