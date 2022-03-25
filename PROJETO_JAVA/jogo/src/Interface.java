import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Interface extends JFrame{
    JLabel labelMaquina = new JLabel("MAQUINA");
    JLabel labelUsuario = new JLabel("VOCÊ");
    Controle controle = new Controle();
    Maquina maquina = new Maquina("Maquina", controle);
    Jogador usuario = new Jogador("Usuario", 18, controle);

    Interface(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("JOGO DE TABULEIRO - DAMAS");
        setResizable(false);
        setVisible(true);
        setLayout(null);
        labelMaquina.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        labelMaquina.setBounds(450,0, 200, 50);
        add(labelMaquina);
        labelUsuario.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        labelUsuario.setBounds(150,400, 200, 50);
        add(labelUsuario);
        exibirTab();
        zonaDeToque();
    }

    public void zonaDeToque(){
        controle.tab.labelTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseReleased(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                usuario.jogar(getMousePosition());
                if(usuario.jogadas>maquina.jogadas) {
                    System.out.println("vez da máquina...");
                    maquina.jogar();
                    controle.atualizarTab();
                }
            }

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

        Interface janela = new Interface();

    }
}


