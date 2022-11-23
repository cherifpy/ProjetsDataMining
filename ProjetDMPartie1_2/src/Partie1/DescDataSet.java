package Partie1;

import Interface.MainWindow;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class DescDataSet extends JPanel {


    private JLabel lechant = new JLabel("Echantillon :"),
                    lnbligne = new JLabel("Nb Lignes :"),
                    lnbatt = new JLabel("Nb Attributs :");

    private JLabel echant = new JLabel();
    private static JLabel nbligne = new JLabel();
    private static JLabel nbatt = new JLabel();

    private static DefaultTableModel modelTab = new DefaultTableModel();
    private JTable data = new JTable(modelTab);
    private JScrollPane panel = new JScrollPane(data);

    private static DefaultTableModel model = new DefaultTableModel();
    private JTable tabeAtt = new JTable(model);
    private JScrollPane panel2 = new JScrollPane(tabeAtt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    private JButton actualiser = new JButton("Actualiser"), diagramme = new JButton("Diagramme de dispersion");
    private static JComboBox<String> att1 = new JComboBox<>();
    private static JComboBox<String> att2 = new JComboBox<>();
    private JButton generat = new JButton("Genere");
    private JPanel panel3 = new JPanel();

    Color col1 = new Color(218, 182, 182);

    public DescDataSet(int x,int y,int longeur,int hauteur){
        this.setBounds(x,y,longeur,hauteur);
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        this.add(lechant);
        this.lechant.setBounds(50,10,100,30);

        this.add(panel);
        this.panel.setBounds(50,50,1000,200);
        data.setEnabled(false);

        this.add(lnbligne);
        this.lnbligne.setBounds(50,300,100,30);
        this.add(nbligne);
        this.nbligne.setBounds(150,300,100,30);
        this.nbligne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));


        this.add(lnbatt);
        this.lnbatt.setBounds(50,400,100,30);
        this.add(nbatt);
        this.nbatt.setBounds(150,400,100,30);
        this.nbatt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col1));

        //this.add(actualiser);
        this.actualiser.setBounds(450,480,200,30);
        this.actualiser.setBackground(null);

        this.add(panel2);
        this.panel2.setBounds(300,300,400,150);
        this.model.addColumn("Attributs");
        this.model.addColumn("Type");

        this.add(panel3);
        panel3.setLayout(null);
        panel3.setBounds(750, 300,300,200);
        panel3.setBorder(BorderFactory.createTitledBorder("Diagramme de Despersion"));
        panel3.setBackground(null);
        panel3.add(att1);
        this.att1.setBounds(20,30,260,30);
        panel3.add(att2);
        this.att2.setBounds(20,90,260,30);
        panel3.add(generat);
        this.generat.setBounds(20,150,260,30);
        this.generat.setBackground(null);

        this.generat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index1 = att1.getSelectedIndex(),
                        index2 = att2.getSelectedIndex();

                if(index1 == index2){
                    System.out.println("Changer votre choix");
                }else{
                    ArrayList<Double> vals1 = new ArrayList<Double>(),vals2 = new ArrayList<>();
                    for(int i = 0; i< MainWindow.data.nb_lignes; i++){
                        if(MainWindow.data.Donnees.get(index1).get(i).length() == 0){
                            vals1.add(0.);
                        }else{
                            vals1.add(Double.parseDouble(MainWindow.data.Donnees.get(index1).get(i)));
                        }
                        if(MainWindow.data.Donnees.get(index2).get(i).length() == 0){
                            vals2.add(0.);
                        }else{
                            vals2.add(Double.parseDouble(MainWindow.data.Donnees.get(index2).get(i)));
                        }
                    }


                    XYSeriesCollection idf_dataset = new XYSeriesCollection();

                    XYSeries idf_xyserie = new XYSeries("");
                    for(int index = 0;index< vals1.size();index++){
                        idf_xyserie.add(vals1.get(index),vals2.get(index));
                    }

                    idf_dataset.addSeries(idf_xyserie);

                    JFreeChart dag_Desp = ChartFactory.createScatterPlot("Diagramme Despersion",""+att1.getSelectedItem(),
                            ""+att2.getSelectedItem(),idf_dataset, PlotOrientation.VERTICAL,true,true,false);


                    XYPlot idf_plot = (XYPlot) dag_Desp.getPlot();
                    idf_plot.setBackgroundPaint(Color.lightGray);
                    idf_plot.setDomainGridlinePaint(Color.white);
                    idf_plot.setRangeGridlinePaint(Color.white);
                    /*ValueAxis idf_axis_x = idf_plot.getDomainAxis();
                    ValueAxis idf_axis_y = idf_plot.getRangeAxis();

                    XYBarRenderer idf_render = new XYBarRenderer();
                    idf_plot.setRenderer(idf_render);*/

                    ChartPanel panel = new ChartPanel(dag_Desp);
                    JFrame fram = new JFrame();
                    fram.setLayout(null);
                    fram.add(panel);
                    panel.setBounds(0,0,600,600);
                    fram.setBounds(0,0,650,650);
                    fram.setVisible(true);
                }
            }
        });
        this.actualiser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                for(String att : MainWindow.data.getColumns()){
                    modelTab.addColumn(att);
                    att1.addItem(att);
                    att2.addItem(att);
                }
                Vector RowData = new Vector<String>();
                for(int i = 0;i < 11;i++){
                    for(int j = 0; j< MainWindow.data.getColumns().size(); j++) {
                        RowData.add(MainWindow.data.Donnees.get(j).get(i));
                    }
                    modelTab.addRow((Vector<?>) RowData.clone());
                    RowData.clear();
                }
                nbatt.setText(MainWindow.data.getColumns().size()+ " Attributs");
                nbligne.setText(MainWindow.data.Donnees.get(0).size()+" Lignes");

                for(int i = 0; i< MainWindow.data.getColumns().size(); i++){
                    RowData.add(MainWindow.data.getColumns().get(i));
                    try{
                        double x = Double.parseDouble(MainWindow.data.Donnees.get(i).get(0));
                        RowData.add("Numerique");
                    }catch (Exception ee){
                        RowData.add("Alphabetique");
                    }

                    model.addRow((Vector<?>) RowData.clone());
                    RowData.clear();
                }
            }
        });


    }

    public static void ActualiserFun(){
        for(String att : MainWindow.data.getColumns()){
            modelTab.addColumn(att);
            att1.addItem(att);
            att2.addItem(att);
        }
        Vector RowData = new Vector<String>();
        for(int i = 0;i < 11;i++){
            for(int j = 0; j< MainWindow.data.getColumns().size(); j++) {
                RowData.add(MainWindow.data.Donnees.get(j).get(i));
            }
            modelTab.addRow((Vector<?>) RowData.clone());
            RowData.clear();
        }
        nbatt.setText(MainWindow.data.getColumns().size()+ " Attributs");
        nbligne.setText(MainWindow.data.Donnees.get(0).size()+" Lignes");

        for(int i = 0; i< MainWindow.data.getColumns().size(); i++){
            RowData.add(MainWindow.data.getColumns().get(i));
            try{
                double x = Double.parseDouble(MainWindow.data.Donnees.get(i).get(0));
                RowData.add("Numerique");
            }catch (Exception ee){
                RowData.add("Alphabetique");
            }

            model.addRow((Vector<?>) RowData.clone());
            RowData.clear();
        }
    }
}
