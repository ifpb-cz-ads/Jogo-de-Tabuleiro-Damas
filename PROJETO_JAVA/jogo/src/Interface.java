import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Interface extends JFrame{

    Maquina maquina = new Maquina("Maquina",12);
    Jogador usuario = new Jogador("Usuario", 18,12);
    Tabuleiro tab = new Tabuleiro();
    Controle controle = new Controle();

    Interface(){
        maquina.tab = tab;
        usuario.tab = tab;
        controle.tab = tab;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Interface gráfica");
        setResizable(false);
        setVisible(true);
        setLayout(null);
        exibirTab();
        zonaDeToque();
        controle.start();
    }

    public void zonaDeToque(){
        controle.tab.labelTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usuario.jogar(getMousePosition());
                if(usuario.jogadas>maquina.jogadas) {
                    System.out.println("vez da máquina...");
                    controle.run(1);
                    maquina.jogar();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //função para exibir o tabuleiro
    public void exibirTab(){

        for(Pedra p: controle.tab.getPecas()){
            JLabel label = p.getLabel();
            Point posicao = p.getPosicao();
            label.setBounds(posicao.x, posicao.y, 50,50);
            add(label);
        }
        controle.tab.labelTab.setBounds(0,0,400,400);
        add(controle.tab.labelTab);
    }


    public static void main(String []args){

        new Interface();
    }
}


