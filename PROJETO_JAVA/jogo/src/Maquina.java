import java.awt.*;
import java.util.ArrayList;

public class Maquina {

    private final String nome;
    protected int jogadas;
    protected int capturas;
    protected Controle controle;
    protected ArrayList<Point> pontos;
    protected final int PIXELS;
    public Maquina(String nome, Controle controle) {
        this.nome = nome;
        this.capturas = 0;
        this.controle = controle;
        this.pontos = new ArrayList<>(2);
        this.PIXELS = 50;
    }

    public boolean movimentoTipo1(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));
        boolean caso0 = (pontoDestino.x>=0 && pontoDestino.x<=350) && ( pontoDestino.y>=0 && pontoDestino.y<=350 ) && controle.espacoVazio(pontoDestino);

        if(!caso0){
            return false;
        }
        boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);
        return ( caso1 || caso2 );
    }

    public boolean movimentoTipo2(boolean sinal){

        final int OPERADOR = sinal ? PIXELS: PIXELS*(-1);
        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+OPERADOR*2), (pontoInicial.y+OPERADOR*2));
        Point comparativo2 = new Point((pontoInicial.x-OPERADOR*2), (pontoInicial.y+OPERADOR*2));
        Point comparativo3 = new Point((pontoInicial.x+OPERADOR), (pontoInicial.y+OPERADOR));
        Point comparativo4 = new Point((pontoInicial.x-OPERADOR), (pontoInicial.y+OPERADOR));

        boolean caso0 = pontoDestino.x>=0 && pontoDestino.x<=350 && pontoDestino.y>=0 && pontoDestino.y<=350 && controle.espacoVazio(pontoDestino);

        if(!caso0){
            return false;
        }
        boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);

        if((caso1 || caso2)){
            boolean caso3 = controle.validarSelecao(comparativo3, "GAMER");
            boolean caso4 = controle.validarSelecao(comparativo4, "GAMER");
            if(caso3){
                pontos.add(comparativo3);
                return true;
            }else if(caso4){
                pontos.add(comparativo4);
                return  true;
            }
        }
        return false;
    }

    public void capturar(){
        Pedra obj = (Pedra) controle.buscarNaTab(pontos.get(2));
        int x = (int) (Math.random()*100)+400;
        int y = (int) (Math.random()*250)+50;
        obj.mover(new Point(x,y));
        this.capturas++;
    }

    public boolean mover(){

        Pedra obj;
        int randomico = (int) (Math.random()*5+1);
        switch (randomico){
            case 1:
                if(movimentoTipo1()){
                    System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
                    obj = (Pedra) controle.buscarNaTab(pontos.get(0));
                    obj.mover(pontos.get(1));
                    return true;
                }
                break;
            case 2:
            case 3:
                if(movimentoTipo2(false)){
                    if(!controle.espacoVazio(pontos.get(2))){
                        System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
                        obj = (Pedra) controle.buscarNaTab(pontos.get(0));
                        obj.mover(pontos.get(1));
                        capturar();
                        return true;
                    }
                }
                break;
            case 4:
            case 5:
                if(movimentoTipo2(true)){
                    if(!controle.espacoVazio(pontos.get(2))){
                        System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
                        obj = (Pedra) controle.buscarNaTab(pontos.get(0));
                        obj.mover(pontos.get(1));
                        capturar();
                        return true;
                    }
                }
                break;
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

        boolean fluxo=false;
        final int PIXELS=50;
        //jogo aleatorio da máquina
        while (!fluxo){
            pontos.clear();
            pontos.add(sortearPixels());
            if(controle.validarSelecao(pontos.get(0), "CPU")){
                switch ((int) (Math.random()*6+1)){
                    case 1:
                        pontos.add(new Point(pontos.get(0).x-PIXELS, pontos.get(0).y+PIXELS));
                        break;
                    case 2:
                        pontos.add(new Point(pontos.get(0).x+PIXELS, pontos.get(0).y+PIXELS));
                        break;
                    case 3:
                        pontos.add(new Point(pontos.get(0).x-PIXELS*2, pontos.get(0).y+PIXELS*2));
                        break;
                    case 4:
                        pontos.add(new Point(pontos.get(0).x+PIXELS*2, pontos.get(0).y+PIXELS*2));
                        break;
                    case 5:
                        pontos.add(new Point(pontos.get(0).x-PIXELS*2, pontos.get(0).y-PIXELS*2));
                        break;
                    case 6:
                        pontos.add(new Point(pontos.get(0).x+PIXELS*2, pontos.get(0).y-PIXELS*2));
                        break;
                }

                fluxo = mover();
            }
        }
        pontos.clear();
        jogadas++;
    }

}


