

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
class Jogador extends Movimento{
    private String nome;
    private int idade;
    protected int jogadas;
    protected int numPecas;

    Jogador(){
        this.nome = "fulano";
        this.idade = 18;
        this.jogadas = 0;
        this.numPecas = 8;
    }

    Jogador(String nome, int idade, int jogadas, int numPecas){
        this.nome = nome;
        this.idade = idade;
        this.jogadas = jogadas;
        this.numPecas = numPecas;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setJogadas(){
        this.jogadas++;
    }

    public void setJogadas(int zerar){
        this.jogadas = 0;
    }

    public int getJogadas(){
        return this.jogadas;
    }

    public void setNumPecas(){
        this.numPecas--;
    }

    public void setNumPecas(int iniciar){
        this.numPecas = 8;
    }

    public int getNumPecas(){
        return this.numPecas;
    }


    public boolean paraEsquerda(Tabuleiro tab, int [] posicao){
        int x = posicao[0];
        int y = posicao[1];

        if(y <= 2 || tab.matchMatriz(x-1, y-1,tab.getPecaM()) && tab.matchMatriz(x-2, y-2,tab.getPecaM()) ){
            return false;
        }else if( tab.matchMatriz(x-1, y-1,tab.getVazio() )){
            tab.setMatriz(x-1,y-1,tab.getPecaJ());
            tab.setMatriz(x,y,tab.getVazio());
        }else if( tab.matchMatriz(x-2, y-2,tab.getVazio() )){
            tab.setMatriz(x-2,y-2,tab.getPecaJ());
            tab.setMatriz(x-1,y-1,tab.getVazio());
            tab.setMatriz(x,y,tab.getVazio());
        }
        return true;
    }

    public boolean paraDireita(Tabuleiro tab, int [] posicao){
        int x = posicao[0];
        int y = posicao[1];

        if(y >= 9 || tab.matchMatriz(x-1, y+1,tab.getPecaM()) && tab.matchMatriz(x-2, y+2,tab.getPecaM()) ){
            return false;
        }else if(tab.matchMatriz(x-1, y+1,tab.getVazio())){
            tab.setMatriz(x-1,y+1,tab.getPecaJ());
            tab.setMatriz(x,y,tab.getVazio());
        }else if( tab.matchMatriz(x-2, y+2,tab.getVazio()) ){
            tab.setMatriz(x-2,y+2,tab.getPecaJ());
            tab.setMatriz(x-1,y+1,tab.getVazio());
            tab.setMatriz(x,y,tab.getVazio());
        }
        return true;
    }


    //função para movimento das peças do jogador
    public void mover(Tabuleiro tab, int [] posicao){

        Scanner entrada = new Scanner(System.in);
        boolean fluxo = true;

        do{
            System.out.print("\n1 - Esquerda\n2 - Direita\nMovimento:");

            //teste de seleção de movimento do jogador
            switch(entrada.next()){
                //seleção para esquerda
                case "1":
                    if(paraEsquerda(tab, posicao)){
                        fluxo = false;
                    }else{
                        System.out.println("Movimento inválido!\nTente outro...");

                    }
                    break;
                    //seleção para direita
                case "2":
                    if(paraDireita(tab, posicao)){
                        fluxo = false;

                    }else{
                        System.out.println("Movimento inválido!\nTente outro...");

                    }
                    break;
                default:
                    System.out.println("Movimento inválido!\nTente outro...");
                    break;
            }
        }while(fluxo);
    }

}
