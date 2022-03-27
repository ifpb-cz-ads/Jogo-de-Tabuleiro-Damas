import java.awt.*;

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Usuario extends Jogador {

    protected final int idade;

    Usuario(String nome, int idade, Controle controle) {
        super(nome, "GAMER",controle);
        this.idade = idade;
    }

    @Override
    public boolean capturar(){
        Pedra obj = (Pedra) controle.buscarNaTab(pontos.get(2));
        if((!controle.espacoVazio(pontos.get(2)))){
            obj.pedraMove(new Point(100,500));
            this.capturas++;
            return true;
        }
        return false;
    }

    @Override
    public boolean validarMovimento1(){

        Point pontoInicial = pontos.get(0);
        Point pontoDestino = pontos.get(1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y-PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y-PIXELS));

        boolean caso0 =  (pontoDestino.x<controle.tab.TABPOSX || pontoDestino.x>=controle.tab.TABPOSX + controle.tab.TAM)
                || ( pontoDestino.y<controle.tab.TABPOSY || pontoDestino.y>=controle.tab.TABPOSY+controle.tab.TAM);

        if(caso0 || (!controle.espacoVazio(pontoDestino))){
            return false;
        }else{
            boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
    }

    @Override
    public void jogar() {
        if(pontos.size()==2 && pecaMinha(pontos.get(0)) && controle.espacoVazio(pontos.get(1))){
            if(mover()){
                System.out.println("Vez do jogador");
                System.out.println("ponto no tabuleiro: ("+ pontos.get(pontos.size()-1).x +" , "+ pontos.get(pontos.size()-1).y+" )");
                jogadas++;
            }
        }
        pontos.clear();
    }
}

