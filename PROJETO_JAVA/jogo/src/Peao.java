import java.awt.*;

public class Peao extends Pedra {
    public Peao(Point p, String img) {
        super(p, img);
    }

    Peao(){
        super();
    }

    @Override
    public void mover(Point p) {
        if(status){
            System.out.println("Movendo peao...");
            setPosicao(p);
        }
    }
}
