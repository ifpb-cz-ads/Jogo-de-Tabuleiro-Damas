import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jogo{

    Interface janela;
    Controle controle;
    Maquina maquina;
    Usuario usuario;
    JLabel labelMaquina = new JLabel("MAQUINA");
    JLabel labelUsuario = new JLabel("VOCÊ");

    Jogo(){
        this.controle = new Controle();
        this.maquina = new Maquina("Maquina", controle);
        this.usuario = new Usuario("Usuario", 18, controle);
        this.janela = new Interface();
        carregarJanela();
        zonaDeToque();
    }


    public void zonaDeToque(){
        controle.tab.labelTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                controle.botao = janela.getMousePosition();
                if(controle.botao!=null){
                    usuario.pontos.add(controle.pontoEmPixel(controle.botao));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controle.botao = janela.getMousePosition();
                if(controle.botao!=null){
                    usuario.pontos.add(controle.pontoEmPixel(controle.botao));
                    usuario.jogar();
                    if(usuario.jogadas>maquina.jogadas){
                        System.out.println("Vez da máquina...");
                        maquina.jogar();
                        controle.atualizarTab();
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //função para exibir o tabuleiro
    public void carregarJanela(){

        labelMaquina.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        labelMaquina.setBounds(300,0, 200, 50);
        janela.add(labelMaquina);
        labelUsuario.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        labelUsuario.setBounds(300,600, 200, 50);
        janela.add(labelUsuario);

        for(Pedra p: controle.tab.getPecas()){
            Point posicao = p.getPosicao();
            JLabel label = p.getLabel();
            label.setBounds(posicao.x, posicao.y, 50,50);
            janela.add(label);
        }

        janela.add(controle.tab.labelTab);
    }

    public static void main(String []args){

        new Jogo();

    }
}
