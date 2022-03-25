import java.awt.*;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador extends Controle {

    private final String nome;
    private final int idade;
    protected int jogadas;
    protected int numPecas;

    public Jogador(String nome, int idade, int numPecas) {
        this.nome = nome;
        this.idade = idade;
        this.numPecas = numPecas;

    }

    public boolean movimentoTipo1(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        boolean caso0 = (pontoDestino.x>=0 && pontoDestino.x<=350) && ( pontoDestino.y>=0 && pontoDestino.y<=350 ) && espacoVazio(pontoDestino);
        if(!caso0){
            return false;
        }else{
            boolean caso1 = compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
    }

    public boolean movimentoTipo2(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS*2), (pontoInicial.y-PIXELS*2));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS*2), (pontoInicial.y-PIXELS*2));
        Point comparativo3 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo4 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        boolean caso0 = pontoDestino.x>=0 && pontoDestino.x<=350 && pontoDestino.y>=0 && pontoDestino.y<=350 && espacoVazio(pontoDestino);

        if(caso0){
            boolean caso1 = compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = compararPosicoes(pontoDestino, comparativo2);

            if((caso1 || caso2)){
                boolean caso3 = validarSelecao(comparativo3, "CPU");
                boolean caso4 = validarSelecao(comparativo4, "CPU");
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

    public boolean mover(){
        Pedra obj;
        if(movimentoTipo1()){
            obj = (Pedra) buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            return true;
        }else if(movimentoTipo2()){
            obj = (Pedra) buscarNaTab(pontos.get(0));
            obj.mover(pontos.get(1));
            obj = (Pedra) buscarNaTab(pontos.get(2));
            obj.mover(new Point(0,450));
            return true;
        }
        return false;
    }

    public void jogar(Point toque) {

        pontos.add(pontoEmPixel(toque));
        if( validarSelecao(pontos.get(0), "GAMER") ){
            if(pontos.size()>1){
                System.out.println("Vez do jogador");
                mover();
                jogadas++;
                pontos.clear();
            }
        }

    }

}
