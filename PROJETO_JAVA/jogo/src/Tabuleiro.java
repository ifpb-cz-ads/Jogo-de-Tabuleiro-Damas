import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//classe para criar o objeto fundamental do jogo - o tabuleiro
public class Tabuleiro {

    private final ArrayList<Pedra> pecas;
    public JList lista;
    private final String HASH_LIVRE;
    private final String HASH_CPU;
    private final String HASH_GAMER;
    private final String HASH_DAMACPU;
    private final String HASH_DAMAGAMER;
    protected Controle controle;

    Tabuleiro(){
        this.pecas = new ArrayList<>(24);
        this.lista = new JList();
        this.HASH_LIVRE = " ";
        this.HASH_CPU = "CPU";
        this.HASH_GAMER = "GAMER";
        this.HASH_DAMACPU = "DAMACPU";
        this.HASH_DAMAGAMER = "DAMAGAMER";
        this.controle = new Controle();
        iniciarTab();
    }

    public ArrayList<Pedra> getPecas() {
        return pecas;
    }
    //função para inicializar a matriz do tabuleiro
    public void iniciarTab(){
        String hash;
        for(int x=0, pexelY=0; x<8; x++, pexelY+=50){
            for(int y=0, pexelX=0; y<8; y++, pexelX+=50){
                hash = (x<3) ? HASH_CPU : ( (x>4) ? HASH_GAMER: HASH_LIVRE );
                if((!hash.equals(HASH_LIVRE))) {
                    if (x % 2 != y % 2) {
                        pecas.add(new Peao(new Point(pexelX,pexelY),hash));
                    }
                }else{
                    break;
                }
            }
        }
    }

    public boolean selecionarPedra(Point ponto){

            for (Pedra i : pecas) {
                if (controle.compararPosicoes(i.getPosicao(), ponto)) {
                    switch (i.getHash()) {
                        case "GAMER":
                        case "DAMAGAMER":
                            i.setStatus(true);
                            return true;
                        default:
                    }
                }
            }
       return false;
    }

    public Pedra buscarNaTab(Point ponto){
        for(Pedra i: pecas){
            if(controle.compararPosicoes(i.getPosicao(), ponto)) {
                return i;
            }
        }
        return new Peao(new Point(),"");
    }

    public void atualizarTab(){
        for(Pedra i: pecas){
            i.setLabel();
            i.setStatus(false);
        }
    }

    public Point pontoEmPexel(Point ponto){

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

}
