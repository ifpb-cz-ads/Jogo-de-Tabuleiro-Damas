import java.awt.*;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador implements Movimentos{

    protected final String nome;
    protected int jogadas;
    protected int capturas;
    protected final int PIXELS;

    public Jogador(String nome) {
        this.nome = nome;
        this.capturas = 0;
        this.PIXELS = 50;
        this.jogadas =0;
    }

    @Override
    public void jogar() {
        if(Controle.pontos.size()==2 && pecaMinha(Controle.pontos.get(0)) && Controle.espacoVazio(Controle.pontos.get(1))){
            if(mover()){
                System.out.println("Vez do jogador");
                System.out.println("ponto no tabuleiro: ("+ Controle.pontos.get(Controle.pontos.size()-1).x +" , "+ Controle.pontos.get(Controle.pontos.size()-1).y+" )");
                jogadas++;
            }
        }
        Controle.pontos.clear();
    }

    public boolean pecaMinha(Point ponto){
        Pedra obj = (Pedra) Controle.buscarNaTab(ponto);
        if((obj!= null)){
            return (obj.getHash().equals("GAMER") || obj.getHash().equals("DAMAGAMER"));
        }
        return false;
    }

    @Override
    public boolean capturar(){
        Pedra obj = (Pedra) Controle.buscarNaTab(Controle.pontos.get(2));
        if(obj!=null&&(!Controle.espacoVazio(Controle.pontos.get(2)))){
            obj.pedraMove(new Point(100,500));
            this.capturas++;
            return true;
        }
        return false;
    }

    @Override
    public boolean movimentoTipo1(){

        Point pontoInicial = Controle.pontos.get(0);
        Point pontoDestino = Controle.pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        boolean caso0 =  (pontoDestino.x<Controle.Tabuleiro.TABPOSX || pontoDestino.x>=Controle.Tabuleiro.TABPOSX + Controle.Tabuleiro.TAM)
                || ( pontoDestino.y<Controle.Tabuleiro.TABPOSY || pontoDestino.y>=Controle.Tabuleiro.TABPOSY+Controle.Tabuleiro.TAM);

        if(caso0 || (!Controle.espacoVazio(pontoDestino))){
            return false;
        }else{
            boolean caso1 = Controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = Controle.compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
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

}
