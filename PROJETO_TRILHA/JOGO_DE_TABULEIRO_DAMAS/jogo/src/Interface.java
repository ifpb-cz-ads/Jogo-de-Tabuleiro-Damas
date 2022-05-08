import javax.swing.*;

public class Interface extends JFrame{

    Interface(){
        criarTela();
    }
    public void criarTela(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setTitle("JOGO DE TABULEIRO - DAMAS");
        setResizable(false);
        setVisible(true);
        setLayout(null);
    }

}


