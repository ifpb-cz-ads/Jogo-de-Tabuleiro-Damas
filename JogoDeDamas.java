import java.util.Scanner;

/**
 *
 * @author Átylla
**/


//classe para responsável por todo o controle do jogo (movimentos, escolhas, navegado nas opções)
class Controle{
    
    String sentinela, coluna, linha;
    Scanner entrada;
    
    Controle(){
        
    }
    
    Controle(String sentinela, String coluna, String linha){
        this.sentinela = sentinela;
        this.coluna = coluna;
        this.linha = linha;
        this.entrada = new Scanner(System.in);
    }
    
    public  void configurar(Tabuleiro tab){
        
        System.out.println("\n\tCONFIGURAÇÃO DE TABULEIRO");
        System.out.println("\n\nDefina as peças do tabuleiro:");
        System.out.println("Exemplos: @ - # - X - O");

        System.out.print("\nCaractere do adversário:");
        tab.setPecaM(entrada.next());
        System.out.print("\nSeu caractere:");
        tab.setPecaJ(entrada.next());
        
        if(tab.getPecaM().equals("*") || tab.getPecaJ().equals("*") ||tab.getPecaM().equals(tab.getPecaJ())){
            System.out.println("\n\nCaractere indisponível!!Tente outro...\n\n");
            configurar(tab);
            
        }else{
            tab.iniciarTab();
            System.out.print("\n\t\t*********TABULEIRO**********\n");
            tab.exibirTab();
            System.out.print("\n1 - voltar ao menu\n2 - mudar configuração\nopção: ");

            if(entrada.next().equals("2")){
                configurar(tab);
            }
        }
        
    }
    //método para iniciar uma partida
    public void jogar(Tabuleiro tab, Jogador jogador, Jogador maquina){
        
        System.out.println("\n\t\t\t\t****INÍCIO DE PARTIDA****\n");
        System.out.print("\t\t\t\t*********TABULEIRO**********\n");
        tab.exibirTab();
        
        do{
            
            System.out.println("\nPosição da peça:");
            System.out.print("Linha: ");
            linha = entrada.next();
            
            System.out.print("Coluna: ");
            coluna = entrada.next();
            
            //teste de ocorrência do movimento - válido ou não
            if(jogador.mover(tab, Integer.parseInt(linha)+1, Integer.parseInt(coluna)+1)){
                
                System.out.print("\nSua jogada:");
                tab.exibirTab();

                maquina.mover(tab);
                System.out.print("\nJogada do adversário:");
                tab.exibirTab();
                
            }else{
                System.out.println("\n\n\tMovimento inválido!");
                System.out.println("Suas peças são: "+ tab.getPecaJ());
                System.out.print("\nTente novamente...\n");
                tab.exibirTab();
            }
            
        System.out.print("\n1 - 'mover'\n0 - 'encerrar partida'\nOpção: ");
        sentinela = entrada.next();
        
        }while(!sentinela.equals("0"));
        
    }
    
    public  void comandosRegras(Tabuleiro tab){
        
        System.out.print("\n****COMANDO E REGRAS DO JOGO****\n");
        System.out.print("\n\t\t\t\t*********TABULEIRO**********\n");
        tab.exibirTab();
        
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

//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
class Jogador{
    private String nome;
    private int idade;
    private int jogadas;
    private int numPecas;
    
    Jogador(){
        
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
    
     //função para movimento das peças do jogador
    public boolean mover(Tabuleiro tab, int x, int y){
        
        //teste para saber se na posição selecionada encontra-se a peça do jogador 
        if( x<=2 || x>9 || tab.matchMatriz(x,y,tab.getVazio())|| tab.matchMatriz(x,y,tab.getPecaM()) ){
            
            return false;
        }else{
            System.out.print("\t\tPeça selecionada!\n1 - Esquerda\n2 - Direita\nMovimento:");
            Scanner entrada = new Scanner(System.in);
            String mov = entrada.next();
            //teste de seleção de movimento do jogador
            switch(mov){
                //seleção para esquerda
                case "1":
                    if(y <= 2 || tab.matchMatriz(x-1, y-1,tab.getPecaM()) && tab.matchMatriz(x-2, y-2,tab.getPecaM())){
                        return false;
                    }else if( tab.matchMatriz(x-1, y-1,tab.getVazio() )){
                        tab.setMatriz(x-1,y-1,tab.getPecaJ());
                        tab.setMatriz(x,y,tab.getVazio());
                    }else if( tab.matchMatriz(x-2, y-2,tab.getVazio() )){
                        tab.setMatriz(x-2,y-2,tab.getPecaJ());
                        tab.setMatriz(x-1,y-1,tab.getVazio());
                        tab.setMatriz(x,y,tab.getVazio());
                    }
                    break;
                //seleção para direita
                case "2":
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
                    break;
                default:
                    return false;
            }
        }
        
        //função ocorreu normalmente...
        return true;
    }
    //método mover sobrecarregado, sem indicar as posições como argumentos, este refere-se à maquina
    public void mover(Tabuleiro tab){
        
        int x=0, y=0;
        
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
                            tab.setMatriz(x,y,tab.getVazio());;
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
//classe para criar o objeto fundamental do jogo - o tabuleiro
class Tabuleiro{
    private String [][] matriz;
    private String pecaM, pecaJ;
    private final String vazio;
        
    Tabuleiro(String j, String m, String vazio){
        this.matriz = new String[12][12];
        this.vazio = vazio;
        this.pecaJ = j;
        this.pecaM = m;
        iniciarTab();
    }
    //métodos acessadores das peças
    public String getPecaJ(){
        return this.pecaJ;
    }
    
    //métodos acessadores das peças
    public String getVazio(){
        return this.vazio;
    }
    
    public String getPecaM(){
        return this.pecaM;
    }
    //métodos modificadores das peças
    public void setPecaJ(String pecaJ){
        this.pecaJ = pecaJ;
    }
    
    public void setPecaM(String pecaM){
        this.pecaM = pecaM;
    }
    //método acessador da matriz
    public String getMatriz(int posX, int posY){
        return this.matriz[posX][posY];
    }
    //método modificador da matriz
    public void setMatriz(int posX, int posY, String peca){
        this.matriz[posX][posY] = peca;
    }
    
    public boolean matchMatriz(int posX, int posY, String peca){
        return matriz[posX][posY].equals(peca);
    }
    
    //função para inicializar a matriz do tabuleiro
    public void iniciarTab(){
         
        //margem do tabuleiro
        for(int x=0; x<this.matriz[0].length; x++){
            for(int y=0; y<this.matriz[0].length; y++){
                    this.matriz[x][y] = "*";
            }
        }
        //for aninhados para inicializar a matriz do tabuleiro
        for(int x=2; x<this.matriz[0].length-2; x++){
            for(int y=2; y<this.matriz[0].length-2; y++){
                //condição para preencher o lado do jogador-máquina
                if((x<=4) && (y%2==x%2)){
                    this.matriz[x][y] = this.pecaM;
                //condição para preencher o lado do jogador disputante
                }else if((x>=7) && (y%2==x%2)){
                    this.matriz[x][y] = this.pecaJ;
                }else{
                    //espaço intermediário de disposição incial do jogo 
                    this.matriz[x][y] = this.vazio;
                }
                
            }
        }
    }
    
    //função para exibir o tabuleiro
    public void exibirTab(){
        
        //exibir as colunas
        System.out.print("\n\t\t    ");
        for(int c = 1; c <=8; c++){
                System.out.print(c+"     ");
        }
        for(int i=2; i<this.matriz[0].length-2; i++){
            System.out.print("\n\t\t--------------------------------------------------");
            //exibir as linhas
            System.out.print("\n\t\t"+ (i-1));
            for(int j=2; j<this.matriz[0].length-2; j++){
                System.out.print("|  "+ this.matriz[i][j] +"  ");
            }
            
        }
    }
    
}
//esta classe é par ao jogo em si, onde todos os objetos fazem parte contituinte :)
class JogoDeDamas {
    
    private Jogador jogador;
    private Jogador maquina;
    private Tabuleiro tab;
    private Controle controle;
    private String opcao;
    
    JogoDeDamas(){
        
    }
    
    JogoDeDamas(String jogador, String maquina){
        this.jogador = new Jogador(jogador,18,0,8);
        this.maquina = new Jogador(maquina,18,0,8);
        this.tab = new Tabuleiro("@", "#", " ");
        this.controle = new Controle(null, null, null);
        this.opcao = null;
    }
    
    public void menu(){

        do{
            System.out.print("\t\t***JOGO DE TABULEIRO - DAMAS***\n");

            System.out.print("MENU\n1 - jogar\n2 - configurar tabuleiro\n3 - mostrar tabuleiro");
            System.out.print("\n4 - comandos e regras\n0 - sair\n\nOpção: ");
            opcao = controle.entrada.next();
            
            switch(opcao){
                case "1":
                    controle.jogar(this.tab, this.jogador, this.maquina);
                    tab.iniciarTab();
                    System.out.print("\n\t\t\tPARTIDA ENCERRADA...");
                    break;
                case "2":
                    controle.configurar(this.tab);
                    break;
                case "3":
                    System.out.print("\n\t\t\t\t*********TABULEIRO**********\n");
                    tab.exibirTab();
                    break;
                case "4":
                    controle.comandosRegras(this.tab);
                    break;
                case "0":
                    System.out.print("\n\nPrograma encerrado...\n\n");
                    break;
                default:
                    System.out.print("\n\nDados inválidos!\nTente novamente\n\n");
                    break;
            }
            System.out.print("\n\n");
        }while(!this.opcao.equals("0"));
    }
    
    public static void main(String[] args) {
        
        new JogoDeDamas("jogador", "maquina").menu();
        
    }
}
