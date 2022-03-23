import java.awt.*;
import java.util.ArrayList;

//classe controle do jogo
public class Controle{
    protected int cliques;
    protected boolean selecao;
    protected ArrayList<Point> pontos;

    Controle(){
        this.selecao = false;
        this.cliques = 0;
        this.pontos = new ArrayList<>(0);
    }

    public void configurar(){

    }

    public boolean validarMovimento(){

        return !compararPosicoes(pontos.get(0), pontos.get(1));
    }

    public boolean compararPosicoes(Point p1, Point p2){
        return (p1.x == p2.x && p1.y == p2.y);
    }

    //método para iniciar uma partida
    public void jogar(){
    }

    public  void comandosRegras(){

        System.out.print("\n\n\t\t****COMANDOS****\n");
        System.out.print("**seguindo as coordenadas do tabuleiro, deve-se escolher a posição da peça**\n");
        System.out.print("**escolhe-se o número da linha, depois o da coluna - dispostos de 1 a 8, linhas e colunas, respectivamente**\n");
        System.out.print("**após peça selecionada, deve-se escolher para qual lado movê-la**\n");
        System.out.print("**tecla 1 - escolher movimento à esquerda**\n**tecla 2 - escolher movimento à direita**\n");
        System.out.print("\n\t\t****REGRAS****\n**só é permitido um unico movimento por vez, em direção à base adversária**\n");
        System.out.print("**seguindo a disposição inicial das peças, move-se para esquerda ou direita, em direção diagonal**\n");
        System.out.print("**ao chegar na última linha, a peça torna-se Dama, passa a ter mais libertade de movimentos**\n");
        System.out.print("**próposito do jogo é capturar todas as peças do adversário**\n\n");

    }

}

