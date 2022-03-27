import java.awt.*;

public class Dama extends Pedra {

    Dama(Point p, String img) {
        super(p, img);
    }

    @Override
    public void pedraMove(Point ponto){
        setPosicao(ponto);
    }

}
