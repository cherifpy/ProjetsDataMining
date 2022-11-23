package Datas;

import java.util.*;

public class DataSet {


    private ArrayList<String> Columns;
    public ArrayList<ArrayList<String>> Donnees;
    private String FilePath;
    public int nb_lignes = 0, nb_attributs = 0;

    public DataSet(){
        this.Columns = new ArrayList<>();
        Donnees = new ArrayList<>();
    }

    public void setColumns(ArrayList<String> columns){
        this.Columns = columns;
    }

    public void setFilePath(String Path){
        this.FilePath = Path;
    }

    public String getFilePath(){
        return this.FilePath;
    }
    public ArrayList<String> getColumns(){
        return this.Columns;
    }


    /*
    public void LoadData(){
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
                Interface.MainWindow.data.Donnees.add(new ArrayList<String>());
            }

            Interface.MainWindow.data.setColumns(Columns);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                i = 0;
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    liste.add(cell.toString());
                    Interface.MainWindow.data.Donnees.get(i).add(""+cell.toString());
                    i++;
                    //System.out.print(cell.toString() + "||");
                }

                model.addRow((Vector<String>) liste.clone());
                liste.clear();
                //System.out.print("\n");
            }
            Interface.MainWindow.data.nb_attributs = Interface.MainWindow.data.getColumns().size();
            Interface.MainWindow.data.nb_lignes = Interface.MainWindow.data.Donnees.get(0).size();

            DescDataSet.ActualiserFun();
            AttributsPanel.ActualiserFun();
        }

        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
*/
    public double Moyenne(ArrayList<Double> vals){
        double somme = 0;

        for(int i = 0; i<vals.size();i++){
            somme += vals.get(i);
        }
        return somme/vals.size();
    }


    public double Variance(ArrayList<Double> vals){
        double variance = 0.,moyenne = Moyenne(vals);

        for (double val: vals ) {
            variance = variance + Math.pow((val - moyenne),2);

        }

        return Math.sqrt(variance/vals.size());
    }
    public double Median(ArrayList<Double> vals){

        if(vals.size()%2 != 0){
            return (double) vals.get(vals.size()/2);
        }else{
            return (double) (vals.get(vals.size()/2) + vals.get((vals.size()/2) + 1))/2;
        }
    }

    public String MedianStr(ArrayList<String> vals){
        return vals.get(vals.size()/2);
    }

    public double Mode(ArrayList<Double> vals){

        if(vals.size() == 0){
            return 0;
        }
        int max_c = 0,count = 0; double val = vals.get(0),mood = -1;

        for(int i = 0; i<vals.size();i++){
            if(vals.get(i) == val){
                count++;
            }else{
                if(max_c < count){
                    mood = val;
                    max_c = count;
                }
                count = 1;
                val = vals.get(i);
            }
        }
        return mood;
    }
    public String ModeStr(ArrayList<String> vals){

        if(vals.size() == 0){
            return "Null";
        }
        int max_c = 0,count = 0;
        String val = vals.get(0), mood = "";

        for(int i = 0; i<vals.size();i++){
            if(vals.get(i).equals(val)){
                count++;
            }else{
                if(max_c < count){
                    mood = val;
                    max_c = count;
                }
                count = 1;
                val = vals.get(i);
            }
        }
        return mood;
    }
    public ArrayList<Double> Quartiles(ArrayList<Double> vals){

        ArrayList<Double> q = new ArrayList<>();

        q.add(vals.get(0));

        int index = vals.size()/4;

        if(vals.size() % 4 == 0){
            q.add(vals.get(index));
        }else{
            q.add((vals.get(index) + vals.get(index + 1))/2);
        }

        index = vals.size() / 2;
        if(vals.size() % 2 == 0){
            q.add(vals.get(index));
        }else{
            q.add((vals.get(index) + vals.get(index + 1))/2);
        }

        index = (int) (vals.size() * 0.75);
        if((vals.size() % 0.75) == 0){
            q.add(vals.get(index));
        }else{
            q.add((vals.get(index) + vals.get(index + 1))/2);
        }

        q.add(vals.get(vals.size()-1));


        return q;
    }

    public ArrayList<String> Quartiles(int indexData){

        ArrayList<String> q = new ArrayList<>();
        ArrayList<String> vals = this.Donnees.get(indexData);
        q.add(vals.get(0));

        int index = vals.size()/4;
        q.add(vals.get(index));

        index = (int) vals.size() / 2;
        q.add(vals.get(index));

        index = (int) (vals.size() * 0.75);
        q.add(vals.get(index));

        q.add(vals.get(vals.size()-1));

        return q;
    }
    public int ValsManquantes(ArrayList<String> vals){

        int nb_manquantes =0;
        for(String str: vals){
            if(str.length()==0){
                nb_manquantes++;
            }
        }
        return nb_manquantes;
    }

    public double Max(ArrayList<Double> vals){
        return vals.get(vals.size()-1);
    }

    public double Min(ArrayList<Double> vals){
        return vals.get(0);
    }


    //PreTraitement

    //====================================================Elimination des donneesManquantes

    public void ReplaceUNKNOWN(int NumAtt){
        for(int i = 0; i<nb_lignes;i++){
            if(Donnees.get(NumAtt).get(i).length() == 0){
                Donnees.get(NumAtt).set(i,"UNKNOWN");
            }
        }
    }

    public void ReplaceMODE(int NumAtt){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }
        for(int i = 0; i<nb_lignes;i++){
            if(Donnees.get(NumAtt).get(i).length() == 0){
                Donnees.get(NumAtt).set(i,""+Mode(vals));
            }
        }
    }


    public void ReplaceMOYENNE(int NumAtt){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }
        for(int i = 0; i<nb_lignes;i++){
            if(Donnees.get(NumAtt).get(i).length() == 0){
                Donnees.get(NumAtt).set(i,""+Moyenne(vals));
            }
        }
    }

    //====================================================Reduction
    public void ReductionV(int NumAtt){
        Donnees.remove(NumAtt);
        Columns.remove(NumAtt);
        nb_attributs -- ;
    }

    public void ReductionR(int NumLigne) {
        for(ArrayList<String> list : Donnees){
            list.remove(NumLigne);
        }

        nb_lignes -- ;
    }

    //====================================================Normalisation

    public void MinMax(int NumAtt, double new_min,double new_max){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }

        ArrayList<Double> newvals = new ArrayList<>();
        double NormalVal = 0., min = Min(vals), max = Max(vals);

        for(int i = 0;i < vals.size();i++){
            NormalVal = ((vals.get(i) - min)/(max-min))*(new_max-new_min) + new_min;
            Donnees.get(NumAtt).set(i,""+NormalVal);
        }
    }


    public void Z_Score(int NumAtt){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }

        ArrayList<Double> newvals = new ArrayList<>();
        double NormalVal = 0.;
        double moyenn = Moyenne(vals),variance = Variance(vals);
        for(int i = 0;i < vals.size();i++){
            NormalVal = (vals.get(i) - moyenn)/variance;
            Donnees.get(NumAtt).set(i,""+NormalVal);
        }
    }

    //====================================================Discrétisation
    public void EqualWidthDiscrit(int NumAtt, int N, double degPrec){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }

        double k = 1 + (10/3) * Math.log10(N);
        double min =Collections.min(vals), max = Collections.max(vals);
        double w = (  max - min + degPrec) / k;
        int i = 1;
        while(i < k){
            for(int j = 0; j < vals.size();j++){
                if(vals.get(j) >= (min + (w*(i-1))) && vals.get(j) < (min+ w*i)){
                    Donnees.get(NumAtt).set(j,""+i);
                }
            }
            i++;
        }

    }

    public void InteFreqEgal(int NumAtt, int nbInterv){
        ArrayList<Double> vals = new ArrayList<Double>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }
        /*
        vals.sort(new Comparator<Double>() {
            @Override
            public int compare(Double t1, Double t2) {
                return t1>t2 ? 1 : t2 > t1 ? -1 : 0;
            }
        });
        */

        int nb_class = 0;
        int index = Donnees.get(NumAtt).size() / nbInterv,j = 0;
        while(j<nbInterv){
            for(int i = 0; i < Donnees.get(NumAtt).size();i++){

            }
        }

    }



    public void DiscritMethode_1(int NumAtt){
        ArrayList<Double> vals = new ArrayList<>();

        for(String str:Donnees.get(NumAtt)){
            if(str.length() != 0){
                vals.add(Double.parseDouble(str));
            }
        }
    }

    public void CodageString(int NumAtt){

        ArrayList<String> classes = new ArrayList<>(),
        vals = Donnees.get(NumAtt);

        for (int i = 0;i < vals.size();i++) {
            if (!classes.contains(vals.get(i))) {
                classes.add(vals.get(i));
            }
            vals.set(i, String.valueOf(classes.indexOf(vals.get(i))));
        }

    }

}
