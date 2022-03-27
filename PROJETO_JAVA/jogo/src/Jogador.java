import java.awt.*;
import java.util.ArrayList;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public abstract class Jogador extends Thread{

    protected final String nome;
    protected int jogadas;
    protected int capturas;
    protected Controle controle;
    protected ArrayList<Point> pontos;
    protected final int PIXELS;
    protected final String tipoDePeca;

    public Jogador(String nome, String peca, Controle controle) {

        this.nome = nome;
        this.capturas = 0;
        this.controle = controle;
        this.pontos = new ArrayList<>(2);
        this.PIXELS = 50;
        this.tipoDePeca = peca;
    }

    public abstract boolean validarMovimento1();
    public abstract boolean capturar();
    public abstract void jogar();

    public boolean pecaMinha(Point ponto){
        Pedra obj = (Pedra) controle.buscarNaTab(ponto);
        if((obj!= null)){
            return (obj.getHash().equals(tipoDePeca) || obj.getHash().equals("DAMA"+tipoDePeca));
        }
        return false;
    }

    public boolean movimentoDama(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);

        boolean caso0 =  (pontoDestino.x<controle.tab.TABPOSX || pontoDestino.x>=controle.tab.TABPOSX + controle.tab.TAM
                || pontoDestino.y<controle.tab.TABPOSY || pontoDestino.y>=controle.tab.TABPOSY+controle.tab.TAM);

        boolean caso1 = Math.abs(pontoInicial.x-pontoDestino.x)==Math.abs(pontoInicial.y-pontoDestino.y);
        boolean caso2 = controle.espacoVazio(pontoDestino);

        return caso0 && caso1 && caso2;
        /*
        int passoPixelY = (pontoDestino.y-pontoInicial.y) > 0 ? 50: -50;
        int passoPixelX = (pontoDestino.x-pontoInicial.x) > 0 ? 50: -50;

        ArrayList<Boolean> avaliacao = new ArrayList<>(5);
        pontos.clear();
        pontos.add(new Point( pontoInicial.x+ passoPixelX,pontoInicial.y+passoPixelY));

        while(!controle.compararPosicoes(pontoDestino, pontos.get(pontos.size()-1))){

            if(pecaMinha(pontos.get(pontos.size()-1))){
                return false;
            }else if(controle.espacoVazio(pontos.get(pontos.size()-1))){
                avaliacao.add(false);
            }else{
                avaliacao.add(true);
            }
            pontos.get(pontos.size()-1).x += passoPixelX;
            pontos.get(pontos.size()-1).y += passoPixelY;
        }

        for(int i=0; i<avaliacao.size()-1; i++){
           if(avaliacao.get(i) && avaliacao.get(i+1)){
               return caso0 && caso1 && caso2;
           }
        }

        return false;

         */
    }

    public boolean mover(){

        Pedra obj = (Pedra) controle.buscarNaTab(pontos.get(0));

        if(obj!=null){
            if(obj.isDama() && movimentoDama()){
                obj.pedraMove(pontos.get(1));
                return true;
            }else{
                if(validarMovimento1()){
                    obj.pedraMove(pontos.get(1));
                    return true;
                }
                if(validarMovimento2()){
                    if(!pecaMinha(pontos.get(2))){
                        obj.pedraMove(pontos.get(1));
                        return capturar();
                    }
                }
            }

        }
        return false;
    }

    public boolean validarMovimento2(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);

        Point comparativo1 = new Point((pontoInicial.x+PIXELS*2), (pontoInicial.y-PIXELS*2));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS*2), (pontoInicial.y-PIXELS*2));
        Point meio1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point meio2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        Point comparativo3 = new Point((pontoInicial.x+PIXELS*2), (pontoInicial.y+PIXELS*2));
        Point comparativo4 = new Point((pontoInicial.x-PIXELS*2), (pontoInicial.y+PIXELS*2));
        Point meio3 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point meio4 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));

        boolean caso0 =(pontoDestino.x<controle.tab.TABPOSX || pontoDestino.x>=controle.tab.TABPOSX+ controle.tab.TAM)
                || ( pontoDestino.y<controle.tab.TABPOSY || pontoDestino.y>=controle.tab.TABPOSY+controle.tab.TAM );

        if(caso0 || (!controle.espacoVazio(pontoDestino))){
            return false;
        }

        boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);
        boolean caso3 = controle.compararPosicoes(pontoDestino, comparativo3);
        boolean caso4 = controle.compararPosicoes(pontoDestino, comparativo4);

        if(caso1) {
            if(!controle.espacoVazio(meio1)){
                pontos.add(new Point(meio1));
                return true;
            }
        }else if(caso2){
            if(!controle.espacoVazio(meio2)){
                pontos.add(new Point(meio2));
                return true;
            }
        }else if(caso3){
            if(!controle.espacoVazio(meio3)){
                pontos.add(new Point(meio3));
                return true;
            }
        }else if(caso4){
            if(!controle.espacoVazio(meio4)){
                pontos.add(new Point(meio4));
                return true;
            }
        }

        return false;
    }

}
