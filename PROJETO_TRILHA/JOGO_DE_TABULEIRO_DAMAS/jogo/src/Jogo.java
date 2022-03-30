import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jogo{

    Interface janela;
    Maquina maquina;
    Jogador usuario;
    JLabel labelMaquina = new JLabel("MAQUINA");
    JLabel labelUsuario = new JLabel("VOCÊ");
    JLabel labelTab = new JLabel(new ImageIcon(getClass().getResource("/imagens/TABULEIRO.jpg")));

    Jogo(){
        this.maquina = new Maquina();
        this.usuario = new Jogador("Usuario");
        this.janela = new Interface();
        Controle.Tabuleiro.iniciarTab();
        carregarJanela();
        zonaDeToque();
    }


    public void zonaDeToque(){
        labelTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(@NotNull MouseEvent e) {
                Controle.pontos.add(Controle.pontoEmPixel(janela.getMousePosition()));
            }

            @Override
            public void mouseReleased(@NotNull MouseEvent e) {
                Controle.pontos.add(Controle.pontoEmPixel(janela.getMousePosition()));
                usuario.jogar();
                if(usuario.jogadas>maquina.jogadas){
                    System.out.println("Vez da máquina...");
                    maquina.jogar();
                    Controle.atualizarTab();
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

        for(Pedra p: Controle.Tabuleiro.pecas){
            Point posicao = p.getPosicao();
            JLabel label = p.getLabel();
            label.setBounds(posicao.x, posicao.y, 50,50);
            janela.add(label);
        }
        labelTab.setBounds(Controle.Tabuleiro.TABPOSX, Controle.Tabuleiro.TABPOSY , Controle.Tabuleiro.TAM, Controle.Tabuleiro.TAM);
        janela.add(labelTab);
    }

    public static void main(String []args){

        new Jogo();

    }
}
