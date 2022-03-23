//classe modelo para criar os objetos jogador e máquina (os elementos da interação do jogo)
public class Jogador implements Movimentos{

    private final String nome;
    private final int idade;
    protected int jogadas;
    protected int numPecas;

    public Jogador(String nome, int idade, int jogadas, int numPecas) {
        this.nome = nome;
        this.idade = idade;
        this.jogadas = jogadas;
        this.numPecas = numPecas;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public int getNumPecas() {
        return numPecas;
    }

    public void setNumPecas(int numPecas) {
        this.numPecas = numPecas;
    }

    @Override
    public void selecionarPedra() {
        System.out.println();
    }

    @Override
    public void moverPedra() {
        System.out.println();
    }
}
