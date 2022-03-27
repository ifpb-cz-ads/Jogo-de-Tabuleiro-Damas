import java.awt.*;

public class Peao extends Pedra {
    public Peao(Point p, String img) {
        super(p, img);
    }

    Peao(){
        super();
    }

    @Override
    public void pedraMove(Point ponto){
        setPosicao(ponto);
    }

}
