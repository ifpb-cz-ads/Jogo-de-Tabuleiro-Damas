import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//classe controle do jogo
public class Controle {
    static final int PIXELS = 50;
    static ArrayList<Point> pontos = new ArrayList<>(2);

    Controle(){}

    //classe para criar o objeto fundamental do jogo - o tabuleiro
    public static class Tabuleiro {

        final static ArrayList<Pedra> pecas = new ArrayList<>(24);
        final static int TABPOSX=100;
        final static int TABPOSY=100;
        final static int TAM=400;

        Tabuleiro(){}

        //função para inicializar a matriz do tabuleiro
        public static void iniciarTab(){
            for(int y=0, pexelX=TABPOSX; y<8; y++, pexelX+=50){
                for(int x=0, pexelY=TABPOSY; x<8; x++, pexelY+=50){
                    if(x<3 && (x % 2 != y % 2)){
                        pecas.add(new Peao(new Point(pexelX,pexelY),"CPU"));
                    }if(x>4 && (x % 2 != y % 2)){
                        pecas.add(new Peao(new Point(pexelX,pexelY),"GAMER"));
                    }
                }
            }
        }


    }

    public static Object buscarNaTab(Point ponto){
        for(Pedra i: Tabuleiro.pecas){
            if(compararPosicoes(i.getPosicao(), ponto)) {
                return i;
            }
        }
        return null;
    }

    public static void atualizarTab(){

        for(Pedra i: Tabuleiro.pecas){
            if(i.tornarDama()){
                i.setImage();
            }
        }

    }
    public static Point pontoEmPixel(Point ponto){

        int limiteX= Tabuleiro.TABPOSX, limiteY=Tabuleiro.TABPOSY;
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
            limiteX = Tabuleiro.TABPOSX;
            limiteY += 50;
        }
        return p;
    }

    public static boolean espacoVazio(Point p){
        Pedra obj = (Pedra) buscarNaTab(p);
        return obj==null;
    }

    public static boolean compararPosicoes(Point p1, Point p2){
        return (p1.x == p2.x && p1.y == p2.y);
    }

}



