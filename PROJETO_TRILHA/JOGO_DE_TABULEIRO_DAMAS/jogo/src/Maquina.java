import java.awt.*;

public class Maquina implements Movimentos{

    protected int jogadas;
    protected int capturas;
    protected final int PIXELS;

    Maquina() {
        this.jogadas = 0;
        this.capturas = 0;
        this.PIXELS = 50;
    }

    public Point sortearPixels(){
        int x, y;
        x = (int) ((Math.random()*8))*PIXELS+ Controle.Tabuleiro.TABPOSX;
        y = (int) ((Math.random()*8))*PIXELS+ Controle.Tabuleiro.TABPOSY;
        return new Point(x, y);
    }

    public boolean mover(){

        Pedra obj = (Pedra) Controle.buscarNaTab(Controle.pontos.get(0));

        if(obj!=null){
            if(obj.isDama() && movimentoDama()){
                obj.pedraMove(Controle.pontos.get(1));
                return true;
            }else{
                if(movimentoTipo1()){
                    obj.pedraMove(Controle.pontos.get(1));
                    return true;
                }
                if(movimentoTipo2()){
                    if(!pecaMinha(Controle.pontos.get(2))){
                        obj.pedraMove(Controle.pontos.get(1));
                        return capturar();
                    }
                }
            }

        }
        return false;
    }

    //jogo aleatorio da máquina
    @Override
    public void jogar() {

        boolean fluxo=false;
        while (!fluxo){
            Controle.pontos.clear();
            Controle.pontos.add(sortearPixels());
            if(pecaMinha(Controle.pontos.get(0))){
                switch((int) (Math.random()*6+1)){
                    case 1:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x+PIXELS, Controle.pontos.get(0).y+PIXELS));
                        break;
                    case 2:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x-PIXELS, Controle.pontos.get(0).y+PIXELS));
                        break;
                    case 3:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x+PIXELS*2, Controle.pontos.get(0).y+PIXELS*2));
                        break;
                    case 4:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x-PIXELS*2, Controle.pontos.get(0).y+PIXELS*2));
                        break;
                    case 5:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x-PIXELS*2, Controle.pontos.get(0).y-PIXELS*2));
                        break;
                    case 6:
                        Controle.pontos.add(new Point(Controle.pontos.get(0).x+PIXELS*2, Controle.pontos.get(0).y-PIXELS*2));
                        break;
                }
                fluxo = mover();
            }
        }
        System.out.println("ponto no tabuleiro: ("+ Controle.pontos.get(1).x +" , "+ Controle.pontos.get(1).y+" )");
        Controle.pontos.clear();
        jogadas++;
    }

    @Override
    public boolean capturar(){

        Pedra obj = (Pedra) Controle.buscarNaTab(Controle.pontos.get(2));
        if(obj!=null&&(!Controle.espacoVazio(Controle.pontos.get(2)))){
            obj.pedraMove(new Point(500,50));
            this.capturas++;
            return true;
        }
        return false;
    }
    @Override
    public boolean movimentoTipo1(){

        Point pontoInicial = Controle.pontos.get(0);
        Point pontoDestino = Controle.pontos.get(Controle.pontos.size()-1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));

        boolean caso0 =  (pontoDestino.x<Controle.Tabuleiro.TABPOSX || pontoDestino.x>=Controle.Tabuleiro.TABPOSX + Controle.Tabuleiro.TAM
                || pontoDestino.y<Controle.Tabuleiro.TABPOSY || pontoDestino.y>=Controle.Tabuleiro.TABPOSY+Controle.Tabuleiro.TAM);

        if(caso0 || (!Controle.espacoVazio(pontoDestino))){
            return false;
        }else{
            boolean caso1 = Controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = Controle.compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
    }

    public boolean pecaMinha(Point ponto){
        Pedra obj = (Pedra) Controle.buscarNaTab(ponto);
        if((obj!= null)){
            return (obj.getHash().equals("CPU") || obj.getHash().equals("DAMACPU"));
        }
        return false;
    }


}


