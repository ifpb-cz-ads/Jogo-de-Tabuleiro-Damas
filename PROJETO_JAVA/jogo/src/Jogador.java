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

    public void capturar(Point ponto){

        Pedra obj = (Pedra) buscarNaTab(ponto);
        obj.setStatus(false);

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
            return ( caso1 || caso2 ) ;
        }
    }

    public boolean movimentoTipo2(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point pontoMedio1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point pontoMedio2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo1 = new Point((pontoInicial.x+PIXELS*2), (pontoInicial.y-PIXELS*2));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS*2), (pontoInicial.y-PIXELS*2));

        boolean caso0 = (pontoDestino.x>=0 && pontoDestino.x<=350) && ( pontoDestino.y>=0 && pontoDestino.y<=350 ) && espacoVazio(pontoDestino);

        if(caso0){
            boolean caso1 = compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = compararPosicoes(pontoDestino, comparativo2);
            boolean caso3 = validarSelecao(pontoMedio1, "CPU");
            boolean caso4 = validarSelecao(pontoMedio2, "CPU");
            boolean captura1 = caso1&&caso3;
            boolean captura2 = caso2&&caso4;

            if(captura1){
                capturar(pontoMedio1);
            }else if(captura2){
                capturar(pontoMedio2);
            }
            return (caso1 || caso2);
        }
        return false;
    }

    public boolean validarMovimento(){

        boolean mov1 = movimentoTipo1();
        boolean mov2 = movimentoTipo2();

        return (mov2 || mov1);
    }

    public void mover(){

        if( validarSelecao(pontos.get(0), "GAMER") ){
            if (validarMovimento()) {
                Pedra obj = (Pedra) buscarNaTab(pontos.get(0));
                obj.mover(pontos.get(1));
                jogadas++;
            }
        }
    }

    public void jogar(Point toque) {

        pontos.add(pontoEmPixel(toque));
        if(pontos.size()==2){
            System.out.println("Vez do jogador");
            mover();
            pontos.clear();
        }
    }

}
