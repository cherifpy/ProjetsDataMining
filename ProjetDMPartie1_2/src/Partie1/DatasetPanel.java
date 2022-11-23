package Partie1;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import Interface.MainWindow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatasetPanel extends JPanel {
    String path;
    private JButton loadB = new JButton("Charger le DataSet"),
                    modif = new JButton("Modifier"),
                    save = new JButton("Enregister"),
                    annuler = new JButton("Annuler"),
                    select = new JButton("Select");

    static DefaultTableModel model = new DefaultTableModel();

    private JTable dataset = new JTable(model);
    private JLabel LabelPath = new JLabel();
    private JScrollPane tabPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    File df;
    public DatasetPanel(int x,int y,int longeur,int hauteur){
        this.setBounds(x,y,longeur,hauteur);
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        this.add(loadB);
        loadB.setBounds(800,20,250,30);
        loadB.setBackground(null);
        loadB.setEnabled(false);

        this.add(LabelPath);
        LabelPath.setBounds(50,20,400,30);
        this.LabelPath.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, MainWindow.col1));

        this.add(select);
        select.setBounds(460,20,150,30);
        select.setBackground(null);

        this.add(tabPanel);
        tabPanel.setBounds(50,70,longeur-100,400);
        tabPanel.setBackground(null);
        tabPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataset.setEnabled(false);
        tabPanel.setViewportView(dataset);


        this.add(modif);
        modif.setBounds(50,480,250,30);
        modif.setBackground(null);

        this.add(annuler);
        annuler.setBounds(800,480,250,30);
        annuler.setBackground(null);
        this.annuler.setBackground(new Color(134, 47, 45));

        this.add(save);
        save.setBounds(520,480,250,30);
        save.setBackground(null);
        this.save.setBackground(new Color(45, 134, 45));

        this.setVisible(true);

        this.loadB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadData();
            }
        });

        this.select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LuncheSelecter();
            }
        });

        this.modif.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dataset.setEnabled(true);
            }
        });



        this.annuler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadData();


            }
        });

        this.save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                export();
                LoadData();
            }
        });

    }

    public void LunchData(){

    }
    public void LuncheSelecter(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
        fc.setFileFilter(fnef);
        fc.setDialogTitle("Selectionner un Dataset");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fc.showDialog(this,"Select");

        if(res == JFileChooser.APPROVE_OPTION){
            df = fc.getSelectedFile();
            path = df.getAbsolutePath();
            LabelPath.setText(path);
            loadB.setEnabled(true);
            MainWindow.data.setFilePath(path);
        }
    }

    private void LoadData(){
        try {
            //df = new File("/home/cherif/Documents/Data M/tools/Dataset1_ HR-EmployeeAttrition.xlsx");
            FileInputStream fis = new FileInputStream(df);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> rowIterator = sheet.iterator();
            Iterator<Cell> colName = rowIterator.next().cellIterator();
            Vector<String> liste = new Vector<>();
            ArrayList<String> Columns = new ArrayList<String>();
            String coll;int i = 0,j;
            while(colName.hasNext()){
                coll = colName.next().getStringCellValue();
                model.addColumn(coll);
                Columns.add(coll);
                MainWindow.data.Donnees.add(new ArrayList<String>());
            }

            MainWindow.data.setColumns(Columns);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                i = 0;
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    liste.add(cell.toString());
                    MainWindow.data.Donnees.get(i).add(""+cell.toString());
                    i++;
                    //System.out.print(cell.toString() + "||");
                }

                model.addRow((Vector<String>) liste.clone());
                liste.clear();
                //System.out.print("\n");
            }
            MainWindow.data.nb_attributs = MainWindow.data.getColumns().size();
            MainWindow.data.nb_lignes = MainWindow.data.Donnees.get(0).size();

            DescDataSet.ActualiserFun();
            AttributsPanel.ActualiserFun();
        }

        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void export(){
        try
        {
            File df = new File("/home/cherif/Documents/Data M/test/Dataset1_ HR-EmployeeAttritionModifier.xlsx");
            TableModel m = model;
            FileWriter fw = new FileWriter(df);
            for(int i = 0; i < m.getColumnCount(); i++){
                fw.write(m.getColumnName(i) + "\t");
                //System.out.println(m.getColumnName(i));
            }

            fw.write("\n");

            for(int i=0; i < m.getRowCount(); i++) {
                for(int j=0; j < m.getColumnCount(); j++) {
                    //System.out.print(m.getValueAt(i,j).toString()+"\t");
                    fw.write(m.getValueAt(i,j).toString()+"\t");
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch(IOException e){ System.out.println(e); }
    }
}
