import java.awt.*;

public class Dama extends Pedra {

    Dama(Point p, String img) {
        super(p, img);
    }

    Dama(){
        super();
    }

    @Override
    public void mover(Point p) {
        if (status) {
            System.out.println("Movendo dama...");
            setPosicao(p);
        }
    }
}
