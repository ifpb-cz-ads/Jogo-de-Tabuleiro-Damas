import java.awt.*;
import java.util.ArrayList;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador{

    private final String nome;
    private final int idade;
    protected int jogadas;
    protected int capturas;
    protected Controle controle;
    protected ArrayList<Point> pontos;
    protected final int PIXELS;

    public Jogador(String nome, int idade, Controle controle) {
        this.nome = nome;
        this.idade = idade;
        this.capturas = 0;
        this.controle = controle;
        this.pontos = new ArrayList<>(2);
        this.PIXELS = 50;
    }

    public boolean movimentoTipo1(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        boolean caso0 = (pontoDestino.x>=0 && pontoDestino.x<=350) && ( pontoDestino.y>=0 && pontoDestino.y<=350 ) && controle.espacoVazio(pontoDestino);
        if(!caso0){
            return false;
        }else{
            boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
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

        if(caso0){
            boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);

            if((caso1 || caso2)){
                boolean caso3 = controle.validarSelecao(comparativo3, "CPU");
                boolean caso4 = controle.validarSelecao(comparativo4, "CPU");
                if(caso3){
                    pontos.add(comparativo3);
                    return true;
                }else if(caso4){
                    pontos.add(comparativo4);
                    return  true;
                }
            }
        }
        return false;
    }

    public void capturar(){
        Pedra obj = (Pedra) controle.buscarNaTab(pontos.get(2));
        int x = (int) (Math.random()*300);
        int y = (int) (Math.random()*100)+400;
        obj.mover(new Point(x,y));
        this.capturas++;
    }

    public boolean mover(){
        Pedra obj;
        if(movimentoTipo1()){
            obj = (Pedra) controle.buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            return true;
        }else if(movimentoTipo2(false)||movimentoTipo2(true)){
            if(!controle.espacoVazio(pontos.get(2))){
                System.out.println("ponto sorteado: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
                obj = (Pedra) controle.buscarNaTab(pontos.get(0));
                obj.mover(pontos.get(1));
                capturar();
                return true;

            }
        }
        return false;
    }

    public void jogar(Point toque) {

        pontos.add(controle.pontoEmPixel(toque));
        if(pontos.size()==2){
            if( controle.validarSelecao(pontos.get(0), "GAMER") && controle.espacoVazio(pontos.get(1))){
                if(mover()){
                    System.out.println("Vez do jogador");
                    jogadas++;
                }
            }
            pontos.clear();
        }

    }

}
