import java.awt.*;

public class Maquina extends Controle {

    private final String nome;
    protected int jogadas;
    protected int numPecas;

    public Maquina(String nome, int numPecas) {
        this.nome = nome;
        this.numPecas = numPecas;
    }

    public boolean validarMovimento(){

        boolean caso0 = espacoVazio(pontos.get(1));
        boolean caso1 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
        boolean caso2 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
        Pedra obj = (Pedra) buscarNaTab(new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
        boolean caso3 = obj!= null && (obj.hash.equals("GAMER") || obj.hash.equals("DAMAGAMER"));
        boolean caso4 = caso3 && compararPosicoes(pontos.get(1), new Point(pontos.get(0).x+PIXELS*2, pontos.get(0).y+PIXELS*2));
        obj = (Pedra) buscarNaTab(new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
        boolean caso5 = obj!= null && (obj.hash.equals("GAMER") || obj.hash.equals("DAMAGAMER"));
        boolean caso6 = caso5 && compararPosicoes(pontos.get(1), new Point(pontos.get(0).x-PIXELS*2, pontos.get(0).y+PIXELS*2));
        boolean caso7 = (pontos.get(1).x>=0 && pontos.get(1).x<=350) && ( pontos.get(1).y>=0 && pontos.get(1).y<=350 );
        return ( (caso1 || caso2) && caso0 || (caso4 || caso6)&&caso0 ) && caso7;

    }

    public boolean mover(){
        if(validarMovimento()){
            System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
            Pedra obj = (Pedra) buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            return true;
        }
        return false;
    }

    public Point sortearPixels(){
        int x, y;
        x = (int) ((Math.random()*8))*PIXELS;
        y = (int) ((Math.random()*8))*PIXELS;
        return new Point(x, y);
    }


    public void jogar() {

        pontos.clear();
        boolean fluxo=false;
        final int PIXELS=50;
        //jogo aleatorio da máquina
        while (!fluxo){
            pontos.add(sortearPixels());
            if(validarSelecao(pontos.get(0), "CPU")){
                switch ((int) (Math.random()*2+1)){
                    case 1:
                        pontos.add(new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
                        fluxo = mover();
                        break;
                    case 2:
                        pontos.add(new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
                        fluxo = mover();
                        break;
                    default:
                        fluxo = numPecas>0;
                        break;
                }
            }
            pontos.clear();
        }
        jogadas++;
    }

}


