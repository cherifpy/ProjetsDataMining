package Partie1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import Interface.MainWindow;
public class AttributsPanel extends JPanel {

    private static JComboBox<String> listeAtt = new JComboBox<>();
    private JButton select = new JButton("Selectionner"),
            boitMoustache = new JButton("Boite a Moustache"),
            hist = new JButton("Histogramme"),
            actualiser = new JButton("Actualiser");

    private JLabel Ltype = new JLabel("Type :"), Lnom  = new JLabel("Nom :"),
            Lmoy  = new JLabel("Moyenne :"), Lmed  = new JLabel("Median :"),
            Lmod = new JLabel("Mode :"), Lmax =  new JLabel("Maximum :"),
            Lmin  = new JLabel("Minimum :"), LValManq = new JLabel("Nb Val Manquantes :"),
            lq0  = new JLabel("Q0 :"),lq1 = new JLabel("Q1 :"),lq2 = new JLabel("Q2 :")
            ,lq3 = new JLabel("Q3 :"), lq4 = new JLabel("Q4 :"),
            loutl = new JLabel("Outliers haut :"),loutl2 = new JLabel("Outliers bas :");

    private JLabel type = new JLabel(), nom  = new JLabel(), moy  = new JLabel(),
            med  = new JLabel(), mod = new JLabel(), max =  new JLabel(), min  = new JLabel(),
            ValManq = new JLabel(), q0 = new JLabel(),q1 = new JLabel(),q2 = new JLabel(),q3 = new JLabel(),
            q4 = new JLabel(),out = new JLabel(), out2 = new JLabel();



    private JPanel quartiles = new JPanel();


    Color col1 = MainWindow.col1;
    public AttributsPanel(int x, int y, int longeur,int hauteur){
        this.setBounds(x,y,longeur,hauteur);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(listeAtt);
        this.listeAtt.setBounds(370,20,200,30);
        this.listeAtt.setBackground(null);
        //this.listeAtt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(select);
        this.select.setBounds(580,20,150,30);
        this.select.setBackground(null);

        this.setVisible(true);

        //this.add(actualiser);
        this.actualiser.setBounds(60,20,150,30);
        this.actualiser.setBackground(null);


        this.add(Ltype);
        this.Ltype.setBounds(60,80,50,30);
        this.add(type);
        this.type.setBounds(120,80,150,30);
        this.type.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));


        this.add(Lnom);
        this.Lnom.setBounds(350,80,50,30);
        this.add(nom);
        this.nom.setBounds(410,80,150,30);
        this.nom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(Lmoy);
        this.Lmoy.setBounds(60,180,80,30);
        this.add(moy);
        this.moy.setBounds(150,180,120,30);
        this.moy.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));


        this.add(Lmed);
        this.Lmed.setBounds(350,180,80,30);
        this.add(med);
        this.med.setBounds(440,180,120,30);
        this.med.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(Lmod);
        this.Lmod.setBounds(60,280,100,30);
        this.add(mod);
        this.mod.setBounds(150,280,120,30);
        this.mod.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(Lmax);
        this.Lmax.setBounds(350,280,100,30);
        this.add(max);
        this.max.setBounds(440,280,120,30);
        this.max.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(Lmin);
        this.Lmin.setBounds(60,380,100,30);
        this.add(min);
        this.min.setBounds(150,380,120,30);
        this.min.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(loutl);
        this.loutl.setBounds(40,480,120,30);
        this.add(out);
        this.out.setBounds(150,480,170,30);
        this.out.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(LValManq);
        this.LValManq.setBounds(320,380,170,30);
        this.add(ValManq);
        this.ValManq.setBounds(480,380,80,30);
        this.ValManq.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(loutl2);
        this.loutl2.setBounds(350,480,100,30);
        this.add(out2);
        this.out2.setBounds(450,480,170,30);
        this.out2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.add(quartiles);
        this.quartiles.setLayout(null);
        this.quartiles.setBackground(Color.WHITE);
        this.quartiles.setBounds(650,80,300,350);
        this.quartiles.setBorder(BorderFactory.createTitledBorder("Quartiles"));

        this.quartiles.add(lq0);
        this.lq0.setBounds(50,20,50,30);
        this.quartiles.add(q0);
        this.q0.setBounds(100,20,150,30);
        this.q0.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.quartiles.add(lq1);
        this.lq1.setBounds(50,80,50,30);
        this.quartiles.add(q1);
        this.q1.setBounds(100,80,150,30);
        this.q1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.quartiles.add(lq2);
        this.lq2.setBounds(50,150,50,30);
        this.quartiles.add(q2);
        this.q2.setBounds(100,150,150,30);
        this.q2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.quartiles.add(lq3);
        this.lq3.setBounds(50,220,50,30);
        this.quartiles.add(q3);
        this.q3.setBounds(100,220,150,30);
        this.q3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        this.quartiles.add(lq4);
        this.lq4.setBounds(50,290,50,30);
        this.quartiles.add(q4);
        this.q4.setBounds(100,290,150,30);
        this.q4.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));


        this.add(boitMoustache);
        this.boitMoustache.setBounds(650,450,300,30);
        this.boitMoustache.setBackground(null);

        this.add(hist);
        this.hist.setBounds(650,500,300,30);
        this.hist.setBackground(null);
        /*this.add();this.add();this.add();this.add();this.add();this.add();this.add();*/
        this.actualiser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(String att: MainWindow.data.getColumns()){
                    listeAtt.addItem(att);
                    listeAtt.setSelectedIndex(0);
                }
            }
        });

        this.select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listeAtt.getSelectedIndex();
                nom.setText(MainWindow.data.getColumns().get(index));
                boolean isNumber = true;
                try{
                    Double.parseDouble(MainWindow.data.Donnees.get(index).get(0));
                }catch (Exception ee){
                    isNumber = false;
                }

                if(isNumber){
                    type.setText("Numerique");
                    ArrayList<Double> vals = new ArrayList<>();
                    for(String str : MainWindow.data.Donnees.get(index)){
                        if(str.length() != 0){
                            vals.add(Double.parseDouble(str));
                        }
                    }
                    vals.sort(new Comparator<Double>() {
                        @Override
                        public int compare(Double t1, Double t2) {
                            return t1>t2 ? 1 : t2 > t1 ? -1 : 0;
                        }
                    });
                    moy.setText(""+ MainWindow.data.Moyenne(vals));
                    med.setText(""+ MainWindow.data.Median(vals));
                    mod.setText(""+ MainWindow.data.Mode(vals));
                    max.setText(""+ MainWindow.data.Max(vals));
                    min.setText(""+ MainWindow.data.Min(vals));
                    ValManq.setText(""+ MainWindow.data.ValsManquantes(MainWindow.data.Donnees.get(index)));
                    ArrayList<Double> Quars = MainWindow.data.Quartiles(vals);
                    q0.setText(""+Quars.get(0));
                    q1.setText(""+Quars.get(1));
                    q2.setText(""+Quars.get(2));
                    q3.setText(""+Quars.get(3));
                    q4.setText(""+Quars.get(4));

                    double IQR = Quars.get(3) - Quars.get(1);
                    if (Quars.get(3) + (1.5*IQR ) < Quars.get(4) ){
                        out.setText("Valeurs sup a "+ (Quars.get(3) - (1.5*IQR )));
                    }else{
                        out.setText("Outliers Exist pas");
                    }
                    if (Quars.get(1) - (1.5*IQR ) > Quars.get(0) ){
                        out2.setText("Valeurs inf a "+ (Quars.get(1) + (1.5*IQR )));
                    } else{
                        out2.setText("Outliers Exist pas");
                    }

                }else{
                    type.setText("Alphabetique");
                    ArrayList<String> vals = new ArrayList<>();
                    for(String str : MainWindow.data.Donnees.get(index)){
                        if(str.length() != 0){
                            vals.add(str);
                        }
                    }

                    vals.sort(new Comparator<String>() {
                        @Override
                        public int compare(String t1, String t2) {
                            return t1.compareTo(t2);
                        }
                    });
                    moy.setText("NaN");
                    med.setText(""+ MainWindow.data.MedianStr(vals));
                    mod.setText(""+ MainWindow.data.ModeStr(vals));
                    max.setText("NaN");
                    min.setText("NaN");
                    ValManq.setText(""+ MainWindow.data.ValsManquantes(MainWindow.data.Donnees.get(index)));
                    ArrayList<String> Quars = MainWindow.data.Quartiles(index);
                    q0.setText(""+Quars.get(0));
                    q1.setText(""+Quars.get(1));
                    q2.setText(""+Quars.get(2));
                    q3.setText(""+Quars.get(3));
                    q4.setText(""+Quars.get(4));
                    out.setText("Null");
                    out2.setText("Null");
                }

            }
        });

        this.boitMoustache.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listeAtt.getSelectedIndex();
                ArrayList<Double> vals = new ArrayList<>();
                for(String str : MainWindow.data.Donnees.get(index)){
                    if(str.length() != 0){
                        vals.add(Double.parseDouble(str));
                    }
                }
                DefaultBoxAndWhiskerXYDataset idf_dataset = new DefaultBoxAndWhiskerXYDataset("");
                BoxAndWhiskerItem idf_data_item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(vals);
                idf_dataset.add(new Date(),idf_data_item);

                JFreeChart idf_box_plot = ChartFactory.createBoxAndWhiskerChart("BoxPlot de  l'attribut "+nom.getText(),nom.getText(),"Valeurs",idf_dataset,true);

                ChartPanel idf_panel = new ChartPanel(idf_box_plot);

                JFrame idf_frame = new JFrame("Boite A moustache");
                idf_frame.setBounds(150,150,500,500);
                idf_frame.add(idf_panel);
                idf_frame.setVisible(true);



            }
        });

        this.hist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listeAtt.getSelectedIndex();
                double[] vals = new double[1470];
                int i = 0;
                for(String str : MainWindow.data.Donnees.get(index)){
                    if(str.length() != 0){
                        vals[i] = Double.parseDouble(str);
                        i++;
                    }
                }
                ArrayList<Double> val = new ArrayList<Double>();
                HistogramDataset idf_dataset = new HistogramDataset();
                idf_dataset.setType(HistogramType.FREQUENCY);
                idf_dataset.addSeries("",vals,10);

                JFreeChart idf_histo = ChartFactory.createHistogram("Histogram",nom.getText(),"Frequence",idf_dataset,
                        PlotOrientation.VERTICAL,true,true,true);

                ChartPanel idf_panel = new ChartPanel(idf_histo);
                JFrame idf_frame = new JFrame("Histogram de l'attribut "+nom.getText());
                idf_frame.setBounds(150,150,500,500);
                idf_frame.add(idf_panel);
                idf_frame.setVisible(true);
            }
        });
    }

    public static void ActualiserFun(){
        for(String att: MainWindow.data.getColumns()){
            listeAtt.addItem(att);

        }
        listeAtt.setSelectedIndex(0);
    }
}




