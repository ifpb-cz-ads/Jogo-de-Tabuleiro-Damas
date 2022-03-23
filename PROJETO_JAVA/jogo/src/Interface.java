import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class Interface extends JFrame{

    Tabuleiro tab = new Tabuleiro();
    JLabel labelTab = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagens/TABULEIRO.jpg"))));

    Interface(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Interface gráfica");
        setResizable(false);
        setVisible(true);
        setLayout(null);
        exibirTab();
        zonaDeToque();
    }

    //função para exibir o tabuleiro
    public void exibirTab(){

        for(Pedra p: tab.getPecas()){
            JLabel label = p.getLabel();
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

                Point ponto = tab.pontoEmPexel(getMousePosition());
                tab.controle.pontos.add(ponto);
                if(tab.controle.pontos.size()==2){
                    if(tab.selecionarPedra(tab.controle.pontos.get(0))&& tab.controle.validarMovimento()){
                        Pedra pedra = tab.buscarNaTab(tab.controle.pontos.get(0));
                        pedra.setPosicao(tab.controle.pontos.get(1));
                        tab.atualizarTab();
                    }
                    tab.controle.pontos.clear();
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

    public static void main(String []args){

        new Interface();

    }
}


