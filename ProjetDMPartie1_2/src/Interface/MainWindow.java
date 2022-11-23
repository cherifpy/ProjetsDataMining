package Interface;

import Datas.DataSet;
import Partie1.AttributsPanel;
import Partie1.DatasetPanel;
import Partie1.DescDataSet;
import Partie2.Traitement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {


    private JPanel panel1, panel2,panel3;

    public static DataSet data = new DataSet();
    public static Color col1 = new Color(218, 182, 182);
    public MainWindow(int x,int y,int longeur,int hauteur){
        this.setBounds(x,y,longeur,hauteur);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Analyse du DataSet");
        int jcp = 150;
        NavBarParti1 nav = new NavBarParti1(jcp,0,longeur-jcp,50);
        NavBarGeneral navbar = new NavBarGeneral(0,0,150,hauteur);

        JPanel traitement  = new Traitement(jcp,50,longeur-jcp,hauteur-50);
        panel1 = new DatasetPanel(jcp,50,longeur-jcp,hauteur-50);
        panel2 = new DescDataSet(jcp,50,longeur-jcp,hauteur-50);
        panel3 = new AttributsPanel(jcp,50,longeur-jcp,hauteur-50);
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        traitement.setVisible(true);

        this.getContentPane().add(nav);
        this.getContentPane().add(navbar);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(traitement);
        this.setVisible(true);

    }
    private class NavBarParti1 extends JPanel {

        private JButton DataSet = new JButton("DataSet"), desc = new JButton("Description"),
                attributs = new JButton("Attributs");

        public NavBarParti1(int x,int y , int longeur,int largeur){
            this.setBounds(x,y,longeur,largeur);
            this.setLayout(new GridLayout(1,3));
            this.setBackground(col1);

            this.add(DataSet);
            this.DataSet.setBackground(null);
            this.DataSet.setForeground(null);
            this.add(desc);
            this.desc.setBackground(null);
            this.desc.setForeground(null);
            this.add(attributs);
            this.attributs.setBackground(null);
            this.attributs.setForeground(null);
            this.setVisible(true);

            this.DataSet.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel1.setVisible(true);
                    panel2.setVisible(false);
                    panel3.setVisible(false);
                }
            });

            this.desc.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel1.setVisible(false);
                    panel2.setVisible(true);
                    panel3.setVisible(false);
                }
            });

            this.attributs.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel1.setVisible(false);
                    panel2.setVisible(false);
                    panel3.setVisible(true);
                }
            });
        }
    }

    private class NavBarGeneral extends JPanel{

        JButton parti1 = new JButton("Analyse du DataSet"), parti2 = new JButton("Pre-Traitement");
        NavBarGeneral(int x,int y ,int longeur,int largeur){
            this.setBounds(x,y,longeur,largeur);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(col1);

            this.add(parti1);
            this.parti1.setBackground(null);
            this.parti1.setForeground(null);
            this.add(parti2);
            this.parti2.setBackground(null);
            this.parti2.setForeground(null);
            this.setVisible(true);
        }

    }

}
