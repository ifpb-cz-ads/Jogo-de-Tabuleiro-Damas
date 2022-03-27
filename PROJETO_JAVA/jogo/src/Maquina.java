import java.awt.*;

public class Maquina extends Jogador{

    public Maquina(String nome, Controle controle) {
       super(nome, "CPU",controle);
    }

    public Point sortearPixels(){
        int x, y;
        x = (int) ((Math.random()*8))*PIXELS+controle.tab.TABPOSX;
        y = (int) ((Math.random()*8))*PIXELS+controle.tab.TABPOSY;
        return new Point(x, y);
    }

    //jogo aleatorio da máquina
    public void jogar() {

        boolean fluxo=false;
        while (!fluxo){
            this.pontos.clear();
            this.pontos.add(sortearPixels());
            if(pecaMinha(pontos.get(0))){
                switch((int) (Math.random()*6+1)){
                    case 1:
                        this.pontos.add(new Point(this.pontos.get(0).x+PIXELS, this.pontos.get(0).y+PIXELS));
                        break;
                    case 2:
                        this.pontos.add(new Point(this.pontos.get(0).x-PIXELS, this.pontos.get(0).y+PIXELS));
                        break;
                    case 3:
                        this.pontos.add(new Point(this.pontos.get(0).x+PIXELS*2, this.pontos.get(0).y+PIXELS*2));
                        break;
                    case 4:
                        this.pontos.add(new Point(this.pontos.get(0).x-PIXELS*2, this.pontos.get(0).y+PIXELS*2));
                        break;
                    case 5:
                        this.pontos.add(new Point(this.pontos.get(0).x-PIXELS*2, this.pontos.get(0).y-PIXELS*2));
                        break;
                    case 6:
                        this.pontos.add(new Point(this.pontos.get(0).x+PIXELS*2, this.pontos.get(0).y-PIXELS*2));
                        break;
                }
                fluxo = mover();
            }
        }
        System.out.println("ponto no tabuleiro: ("+ pontos.get(1).x +" , "+ pontos.get(1).y+" )");
        this.pontos.clear();
        jogadas++;
    }

    @Override
    public boolean capturar(){

        Pedra obj = (Pedra) controle.buscarNaTab(pontos.get(2));
        if((!controle.espacoVazio(pontos.get(2)))){
            obj.pedraMove(new Point(500,50));
            this.capturas++;
            return true;
        }
        return false;
    }
    @Override
    public boolean validarMovimento1(){

        Point pontoInicial = this.pontos.get(0);
        Point pontoDestino = this.pontos.get(pontos.size()-1);
        Point comparativo1 = new Point((pontoInicial.x+PIXELS), (pontoInicial.y+PIXELS));
        Point comparativo2 = new Point((pontoInicial.x-PIXELS), (pontoInicial.y+PIXELS));

        boolean caso0 =  (pontoDestino.x<controle.tab.TABPOSX || pontoDestino.x>=controle.tab.TABPOSX + controle.tab.TAM
                || pontoDestino.y<controle.tab.TABPOSY || pontoDestino.y>=controle.tab.TABPOSY+controle.tab.TAM);

        if(caso0 || (!controle.espacoVazio(pontoDestino))){
            return false;
        }else{
            boolean caso1 = controle.compararPosicoes(pontoDestino, comparativo1);
            boolean caso2 = controle.compararPosicoes(pontoDestino, comparativo2);
            return ( caso1 || caso2 );
        }
    }


}


