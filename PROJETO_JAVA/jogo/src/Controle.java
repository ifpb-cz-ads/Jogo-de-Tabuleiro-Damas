import java.awt.*;
import java.util.ArrayList;

//classe controle do jogo
public class Controle {
    protected Tabuleiro tab;
    protected int cliques;
    protected boolean movimento;

    Controle(){
        this.tab = new Tabuleiro();
        this.movimento = false;
        this.cliques = 0;
    }

    public boolean validarSelecao(Point ponto, String hash){
        Pedra obj = (Pedra) buscarNaTab(ponto);
        if((obj!= null)){
            return (obj.hash.equals(hash) || obj.hash.equals("DAMA"+hash));
        }
        return false;
    }

    public Object buscarNaTab(Point ponto){
        for(Pedra i: tab.getPecas()){
            if(compararPosicoes(i.getPosicao(), ponto)) {
                return i;
            }
        }
        return null;
    }

    public void atualizarTab(){
        for(Pedra i: tab.getPecas()){
            i.setLabel();
        }
    }

    public Point pontoEmPixel(Point ponto){

        int limiteX=0, limiteY=0;
        Point p = new Point();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if((ponto.y >= limiteY) && (ponto.y < limiteY + 80) && (ponto.x >= limiteX) && (ponto.x < limiteX + 50)) {
                    p.x = limiteX;
                    p.y = limiteY;
                    i=j=8;
                }
                limiteX += 50;
            }
            limiteX = 0;
            limiteY += 50;
        }
        return p;
    }

    public boolean espacoVazio(Point p){
        Pedra obj = (Pedra) buscarNaTab(p);
        return obj==null;
    }

    public boolean compararPosicoes(Point p1, Point p2){
        return (p1.x == p2.x && p1.y == p2.y);
    }

}



