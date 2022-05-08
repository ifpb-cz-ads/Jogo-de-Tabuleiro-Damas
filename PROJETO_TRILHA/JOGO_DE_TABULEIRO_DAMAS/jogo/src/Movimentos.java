import java.awt.*;

public interface Movimentos {

    boolean mover();
    boolean movimentoTipo1();
    boolean capturar();
    void jogar();

    default boolean movimentoDama(){

        Point pontoInicial = Controle.pontos.get(0);
        Point pontoDestino = Controle.pontos.get(1);

        boolean caso0 =  (pontoDestino.x<Controle.Tabuleiro.TABPOSX || pontoDestino.x>=Controle.Tabuleiro.TABPOSX + Controle.Tabuleiro.TAM
                || pontoDestino.y<Controle.Tabuleiro.TABPOSY || pontoDestino.y>=Controle.Tabuleiro.TABPOSY+Controle.Tabuleiro.TAM);

        boolean caso1 = Math.abs(pontoInicial.x-pontoDestino.x)==Math.abs(pontoInicial.y-pontoDestino.y);
        boolean caso2 = Controle.espacoVazio(pontoDestino);

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


    default boolean movimentoTipo2(){

        Point pontoInicial = Controle.pontos.get(0);
        Point pontoDestino = Controle.pontos.get(1);

        Point comparativo1 = new Point((pontoInicial.x+Controle.PIXELS*2), (pontoInicial.y-Controle.PIXELS*2));
        Point comparativo2 = new Point((pontoInicial.x-Controle.PIXELS*2), (pontoInicial.y-Controle.PIXELS*2));
        Point meio1 = new Point((pontoInicial.x+Controle.PIXELS), (pontoInicial.y-Controle.PIXELS));
        Point meio2 = new Point((pontoInicial.x-Controle.PIXELS), (pontoInicial.y-Controle.PIXELS));

        Point comparativo3 = new Point((pontoInicial.x+Controle.PIXELS*2), (pontoInicial.y+Controle.PIXELS*2));
        Point comparativo4 = new Point((pontoInicial.x-Controle.PIXELS*2), (pontoInicial.y+Controle.PIXELS*2));
        Point meio3 = new Point((pontoInicial.x+Controle.PIXELS), (pontoInicial.y+Controle.PIXELS));
        Point meio4 = new Point((pontoInicial.x-Controle.PIXELS), (pontoInicial.y+Controle.PIXELS));

        boolean caso0 =(pontoDestino.x<Controle.Tabuleiro.TABPOSX || pontoDestino.x>=Controle.Tabuleiro.TABPOSX+ Controle.Tabuleiro.TAM)
                || ( pontoDestino.y<Controle.Tabuleiro.TABPOSY || pontoDestino.y>=Controle.Tabuleiro.TABPOSY+Controle.Tabuleiro.TAM );

        if(caso0 || (!Controle.espacoVazio(pontoDestino))){
            return false;
        }

        boolean caso1 = Controle.compararPosicoes(pontoDestino, comparativo1);
        boolean caso2 = Controle.compararPosicoes(pontoDestino, comparativo2);
        boolean caso3 = Controle.compararPosicoes(pontoDestino, comparativo3);
        boolean caso4 = Controle.compararPosicoes(pontoDestino, comparativo4);

        if(caso1) {
            if(!Controle.espacoVazio(meio1)){
                Controle.pontos.add(new Point(meio1));
                return true;
            }
        }else if(caso2){
            if(!Controle.espacoVazio(meio2)){
                Controle.pontos.add(new Point(meio2));
                return true;
            }
        }else if(caso3){
            if(!Controle.espacoVazio(meio3)){
                Controle.pontos.add(new Point(meio3));
                return true;
            }
        }else if(caso4){
            if(!Controle.espacoVazio(meio4)){
                Controle.pontos.add(new Point(meio4));
                return true;
            }
        }

        return false;
    }


}
