
class Maquina extends Movimento{

    Maquina(){
        super();
    }

    public void mover(Tabuleiro tab, int [] posicao){

        int x, y;

        //variável para controlar o fluxo das iterações
        boolean controle = true;

        //repete até que uma condição seja satisfeita
        while(controle){
            //condição de peça sorteada ser mesmo referente à máquina
            x = (int) (Math.random()*8+2);
            y = (int) (Math.random()*8+2);

            //condição da peça selecionada ser válida
            if( tab.matchMatriz(x,y,tab.getPecaM()) && ( (y>=2) && (y<=10) )){

                //condição para saber se há os dois movimentos livres - esquerda ou direita
                if( tab.matchMatriz(x+1, y-1,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getVazio()) ){

                    //sortear para qual lado será o movimento da máquina, caso os dois lados estejam disponíveis para jogada
                    switch( (int)(Math.random()*2+1) ){
                        case 1:
                            tab.setMatriz(x+1,y-1,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());
                            break;
                        case 2:
                            tab.setMatriz(x+1,y+1,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());
                            break;
                    }
                    controle = false;

                    //condição para possibilidade de capturar peça do adversário
                }else if( tab.matchMatriz(x+2, y+2,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getPecaJ()) && tab.matchMatriz(x+2, y-2,tab.getVazio()) && tab.matchMatriz(x+1, y-1,tab.getPecaJ())){
                    //sortear para qual lado será o movimento da máquina, caso os lados estejam disponíveis para jogada
                    switch( (int)(Math.random()*2+1)){
                        case 1:
                            tab.setMatriz(x+2,y-2,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());
                            break;
                        case 2:
                            tab.setMatriz(x+2, y+2,tab.getPecaM());
                            tab.setMatriz(x,y,tab.getVazio());
                            break;
                    }
                    controle = false;

                    //escolha para qual lado de captura ir - esquerda ou direita, respectivamente...
                }else if( tab.matchMatriz(x+2, y-2,tab.getVazio()) && tab.matchMatriz(x+1, y-1,tab.getPecaJ()) ){

                    tab.setMatriz(x+2,y-2,tab.getPecaM());
                    tab.setMatriz(x+1,y-1,tab.getVazio());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;
                }else if( tab.matchMatriz(x+2, y+2,tab.getVazio()) && tab.matchMatriz(x+1, y+1,tab.getPecaJ()) ){

                    tab.setMatriz(x+2,y+2,tab.getPecaM());
                    tab.setMatriz(x+1,y+1,tab.getVazio());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;

                    //escolha para qual lado ir - esquerda ou direita, respectivamente...
                }else if( tab.matchMatriz(x+1, y-1,tab.getVazio()) ){
                    tab.setMatriz(x+1,y-1,tab.getPecaM());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;
                }else if( tab.matchMatriz(x+1,y+1,tab.getVazio()) ){
                    tab.setMatriz(x+1,y+1,tab.getPecaM());
                    tab.setMatriz(x,y,tab.getVazio());
                    controle = false;
                }
            }

        }

    }
}


