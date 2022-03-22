import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Janela extends JFrame{

    Tabuleiro tab = new Tabuleiro();
    JLabel labelTab = new JLabel(new ImageIcon("TABULEIRO.jpg"));

    Janela(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Interface gráfica");
        setResizable(false);
        setVisible(true);
        setLayout(null);
        carregarTab();
        zonaDeToque();
    }

    //função para exibir o tabuleiro
    public void carregarTab(){

        for(Peao p: tab.getPecas()){
            JLabel label = p.getLabelPedra();
            Point posicao = p.getPosicao();
            label.setBounds(posicao.x, posicao.y, 50,50);
            add(label);
        }

        labelTab.setBounds(0,0,400,400);
        add(labelTab);

    }
    public void zonaDeToque(){
        labelTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point ponto = tab.posicao(getMousePosition());
                tab.selecionarPeca(ponto);
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

    public static void main(String []args){

        new Janela();

        /*Tabuleiro tab = new Tabuleiro();
        System.out.println(tab.getPecas().size());
        tab.getPecas().clear();
        System.out.println(tab.getPecas().size());
        tab.iniciarTab();
        System.out.println(tab.getPecas().size());*/

    }
}

