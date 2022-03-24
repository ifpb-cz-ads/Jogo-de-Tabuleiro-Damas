import java.awt.*;

public class Maquina extends Controle {

    private final String nome;
    protected int jogadas;
    protected int numPecas;
    final int PIXELS=50;

    public Maquina(String nome, int numPecas) {
        this.nome = nome;
        this.numPecas = numPecas;
    }

    public boolean validarMovimento(){

        boolean caso1 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
        boolean caso2 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
        boolean caso3 = espacoVazio(pontos.get(1));
        boolean caso4 = (pontos.get(1).x>=0 && pontos.get(1).x<=350) && ( pontos.get(1).y>=0 && pontos.get(1).y<=350 );
        return ((caso1 || caso2) && caso3 && caso4);

    }

    public boolean mover(){
        if(validarMovimento()){
            System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
            Pedra pedra = (Pedra) buscarNaTab(pontos.get(0));
            pedra.setStatus(true);
            pedra.mover(pontos.get(1));
            return true;
        }
        return false;
    }

    public Point sortearPixels(){
        int x, y;
        x = (int) ((Math.random()*7) +1)*PIXELS;
        y = (int) ((Math.random()*6) +1)*PIXELS;
        return new Point(x, y);
    }


    public void jogar() {

        pontos.clear();
        boolean fluxo=true;
        final int PIXELS=50;
        //jogo aleatorio da máquina
        while (fluxo){
            Pedra obj = (Pedra) buscarNaTab(sortearPixels());
            if(obj!= null && obj.hash.equals("CPU")){
                pontos.add(new Point(obj.getPosicao()));
                switch ((int) (Math.random()*2+1)){
                    case 1:
                        pontos.add(new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
                        fluxo = !mover();
                        break;
                    case 2:
                        pontos.add(new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
                        fluxo = !mover();
                        break;
                    default:
                        fluxo = numPecas>0;
                        break;
                }
            }
            pontos.clear();
        }
        jogadas++;
        atualizarTab();

    }

}


