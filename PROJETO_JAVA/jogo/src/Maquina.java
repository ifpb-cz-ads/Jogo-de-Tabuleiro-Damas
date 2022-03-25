import java.awt.*;

public class Maquina extends Controle {

    private final String nome;
    protected int jogadas;
    protected int numPecas;

    public Maquina(String nome, int numPecas) {
        this.nome = nome;
        this.numPecas = numPecas;
    }

    public boolean movimentoTipo1(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));

        boolean caso0 = (pontoDestino.x>=0 && pontoDestino.x<=350) && ( pontoDestino.y>=0 && pontoDestino.y<=350 ) && espacoVazio(pontoDestino);

        if(!caso0){
            return false;
        }
        boolean caso1 = compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = compararPosicoes(pontoDestino, comparativo2);
        return ( caso1 || caso2 );
    }

    public boolean movimentoTipo2(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS*2), (pontoInicial.y+PIXELS*2));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS*2), (pontoInicial.y+PIXELS*2));
        Point comparativo3 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point comparativo4 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));

        boolean caso0 = pontoDestino.x>=0 && pontoDestino.x<=350 && pontoDestino.y>=0 && pontoDestino.y<=350 && espacoVazio(pontoDestino);

        if(!caso0){
            return false;
        }
        boolean caso1 = compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = compararPosicoes(pontoDestino, comparativo2);

        if((caso1 || caso2)){
            boolean caso3 = validarSelecao(comparativo3, "GAMER");
            boolean caso4 = validarSelecao(comparativo4, "GAMER");
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

    public boolean mover(){
        Pedra obj = new Peao();
        if(movimentoTipo1()){
            System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
            obj = (Pedra) buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            return true;
        }else if(movimentoTipo2()){
            System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
            obj = (Pedra) buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            obj = (Pedra) buscarNaTab(pontos.get(2));
            obj.mover(new Point(450,0));
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


