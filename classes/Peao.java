import java.awt.*;

public class Peao extends Pedra{
    public Peao(Point p, String img) {
        super(p, img);
    }

    @Override
    public void mover() {
        System.out.println("Movendo peao...");
    }
}
