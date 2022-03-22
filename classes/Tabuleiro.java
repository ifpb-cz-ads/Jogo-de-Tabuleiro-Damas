import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//classe para criar o objeto fundamental do jogo - o tabuleiro
class Tabuleiro {

    private final ArrayList<Peao> pecas;
    private final String LIVRE;
    private final String PECA_CPU;
    private final String PECA_GAMER;

    Tabuleiro(){
        this.pecas = new ArrayList<>(24);
        this.LIVRE = " ";
        this.PECA_CPU = "C";
        this.PECA_GAMER = "G";
        iniciarTab();
    }

    public String getLIVRE() {
        return LIVRE;
    }

    public String getPECA_CPU() {
        return PECA_CPU;
    }

    public String getPECA_GAMER() {
        return PECA_GAMER;
    }

    public ArrayList<Peao> getPecas() {
        return pecas;
    }
    //função para inicializar a matriz do tabuleiro
    public void iniciarTab(){

        String imagem, hash;
        for(int x=0, pexelY=0; x<8; x++, pexelY+=50){
            for(int y=0, pexelX=0; y<8; y++, pexelX+=50){
                hash = (x<3) ? getPECA_CPU() : ( (x>4) ? getPECA_GAMER(): getLIVRE() );
                imagem = hash.equals(getPECA_GAMER()) ? "GAMER.png": "CPU.png";
                if((!hash.equals(getLIVRE()))) {
                    if (x % 2 != y % 2) {
                        pecas.add(new Peao(new Point(pexelX,pexelY), imagem));
                    }
                }else{
                    break;
                }

            }

        }
        /*
        for(int i=0;i<24; i++){
                pecas.put(""+i, new Peao(new Point(0,0), ""));
        }*/

    }


    public Point posicao(Point ponto){

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

    public void selecionarPeca(Point p){
        for(Peao obj: pecas){
            if(obj.getPosicao().x == p.x && obj.getPosicao().y == p.y) {
                switch (obj.getHash()){
                    case "CPU.png":
                    case "DAMACPU.png":
                        JOptionPane.showMessageDialog(null,"Peça do adversário!");
                        break;
                    case "GAMER.png":
                    case "DAMAGAMER.png":
                        JOptionPane.showMessageDialog(null,"Peça selecionada!");
                        break;
                    default:
                        break;
                }

            }
        }
    }
}
