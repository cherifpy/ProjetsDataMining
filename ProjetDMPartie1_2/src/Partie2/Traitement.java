package Partie2;

import javax.swing.*;
import java.awt.*;

public class Traitement extends JPanel {


    private JPanel valManq = new JPanel(), valAbb = new JPanel(), descri = new JPanel(),
            normal = new JPanel(), reduc = new JPanel();

    private ButtonGroup nrmlGrp = new ButtonGroup(), descGrp = new ButtonGroup();
    private JRadioButton minmax = new JRadioButton("Methode Min-Max"),
            zscor = new JRadioButton("Methode Z-Score"),
            desc1 = new JRadioButton("Classes effectifs egales"),
            desc2 = new JRadioButton("Classes amplittudes");


    public Traitement(int x, int y, int longeur,int hauteur){
        this.setBounds(x,y,longeur,hauteur);
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        //BorderFactory.createTitledBorder("Quartiles")

        this.add(valAbb);
        //this.valAbb.setBackground(new Color(78, 193, 211));
        this.valAbb.setBounds(50,30,1000,80);
        this.valAbb.setBorder(BorderFactory.createTitledBorder("Valeurs Abberantes"));

        this.add(valManq);
        //this.valManq.setBackground(new Color(78, 193, 211));
        this.valManq.setBounds(50,120,1000,80);
        this.valManq.setBorder(BorderFactory.createTitledBorder("Valeurs Manqauntes"));

        descGrp.add(desc1);
        descGrp.add(desc2);
        this.add(descri);
        //this.descri.setBackground(new Color(78, 193, 211));
        this.descri.setBounds(50,210,1000,80);
        this.descri.setBorder(BorderFactory.createTitledBorder("Descritisation"));


        nrmlGrp.add(minmax);
        nrmlGrp.add(zscor);
        this.add(normal);
        this.normal.setBackground(null);
        this.normal.setBounds(50,300,1000,80);
        this.normal.setBorder(BorderFactory.createTitledBorder("Normalisation"));

        normal.setLayout(null);
        normal.add(minmax);
        minmax.setBounds(20,30,150,30);
        normal.add(zscor);
        zscor.setBounds(250,30,150,30);

        this.add(reduc);
        //this.reduc.setBackground(new Color(78, 193, 211));
        this.reduc.setBounds(50,300,1000,80);
        this.reduc.setBorder(BorderFactory.createTitledBorder("Reduction Horisentale/Verticale"));

    }
}
