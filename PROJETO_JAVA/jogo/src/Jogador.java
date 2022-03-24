import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

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

    public boolean validarMovimento(){

        boolean caso1 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x+50, pontos.get(0).y-50));
        boolean caso2 = compararPosicoes(pontos.get(1), new Point(pontos.get(0).x-50, pontos.get(0).y-50));
        boolean caso3 = espacoVazio(pontos.get(1));
        return ((caso1 || caso2) && caso3);

    }

    public void jogar(Point toque) {

        pontos.add(pontoEmPixel(toque));
        if(pontos.size()==2){
            System.out.println("Vez do jogador");
            if(selecionarPedra(pontos.get(0))) {
                if (validarMovimento()) {
                    Pedra pedra = (Pedra) buscarNaTab(pontos.get(0));
                    pedra.setStatus(true);
                    pedra.mover(pontos.get(1));
                    atualizarTab();
                    jogadas++;
                }
            }
            pontos.clear();
        }
    }
}
