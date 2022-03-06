
//classe para criar o objeto fundamental do jogo - o tabuleiro
public class Tabuleiro{
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
         
        for(int x=0; x<matriz.length; x++){
            for(int y=0; y<matriz[x].length; y++){
                
                //condição para preencher as bordas delimitadoras do tabuleiro
                if(x<=1 || x>=10 || y<=1 || y>=10){
                    matriz[x][y] = "*";
                }else{
                    //condição para preencher o restante com espaço vazio
                    matriz[x][y] = " ";
                }
                //condição para preencher o lado do jogador-máquina
                if((x<5) && (y%2==x%2)){
                    this.matriz[x][y] = this.pecaM;
                }
                //condição para preencher o lado do jogador disputante
                if((x>6) && (y%2==x%2)){
                    this.matriz[x][y] = this.pecaJ;
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
