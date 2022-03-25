import java.awt.*;

public class Peao extends Pedra {
    public Peao(Point p, String img) {
        super(p, img);
    }

    Peao(){
        super();
    }

    public boolean isDama(){
        if(( posicao.y == 0 && hash.equals("GAMER") || posicao.y == 350 && hash.equals("CPU")) ){
            return  true;
        }
        return false;
    }

    @Override
    public void mover(Point p) {
        if(status){
            System.out.println("Movendo peao...");
            setPosicao(p);

        }
    }
}
