import pandas as pd
from itertools import combinations

def Traiter(df):

    for i in range(len(df)):
        
        if df.loc[i, "definition"] == "nan":
            df.loc[i, "definition"] = "hd"

    return df


def readDataSet():

    df = pd.read_excel("/home/cherif/Documents/Data M/tools/DataSet2.xlsx")

    df = Traiter(df)

    TransacDict = {}
    
    for i in range(len(df)):
        watcher = df.loc[i,"Watcher "]
        if watcher not in TransacDict.keys():
            TransacDict[watcher] = []
        if (df.loc[i, "videoCategoryLabel"],df.loc[i, "definition"]) not in TransacDict[watcher]:
            TransacDict[watcher].append((df.loc[i, "videoCategoryLabel"],df.loc[i, "definition"]))

    
    return TransacDict


def ConstruireL1(TransactionDict):

    item_supp = {}
    for transaction in TransactionDict.keys():
        for item in TransactionDict[transaction]:
            if  tuple([item]) not in item_supp.keys():
                item_supp[tuple([item])] = 0

            item_supp[tuple([item])] +=1
    
    return item_supp

def CalculeSupport(Itemset: tuple, TransactionDict : dict)-> int:

    b = True
    support = 0

    for transaction in TransactionDict.keys():

        for item in Itemset:
            if item not in TransactionDict[transaction]:
                b = False

        if b == True:
            support+=1
        
        b = True

    return support


def CheckInList(ItemSet,L):

    for i_set in L:
        if len(ItemSet) == len(i_set):
            for i in range(len(i_set)):
                if i_set[i] != ItemSet[i]:
                    b = False
                    
    return False
    
def Apriori(TransacDict, supp_min):

    L = []
    k = 1
    
    list_final = []
    ListCondidat = ConstruireL1(TransacDict)

    while len(ListCondidat)>1:
        L = []
        print(f"===Debut C{k}")
        for itemset,support in ListCondidat.items():
            print(f"{itemset} : {support}")
        print(f"Fin C{k}")
        for itemset in ListCondidat.keys():
            if ListCondidat[itemset] >= supp_min:
                    L.append(itemset)

        print(f"===Debut L{k}")
        for itemset,support in ListCondidat.items():
            if itemset in L:
                print(f"{itemset} : {support}")
        print(f"Fin L{k}")
        ListCondidat = {}

        for itemset in L:
            list_final.append(itemset)
        #list_final.append(L)
        

        NewItem = []

        count = 0
        for i in range(0,len(L)):
            for j in range(i+1,len(L)):
                count +=1
                for item in L[i]:   
                    if item not in NewItem:
                        NewItem.append(item)
    
                for item in L[j]:
                    if item not in NewItem:
                        NewItem.append(item)
                        
                
                # tuple(NewItem) not in ListCondidat and len(tuple(NewItem)) == k:
                ListCondidat[tuple(NewItem)] = CalculeSupport(tuple(NewItem),TransacDict)
                NewItem = []
        k+=1
    
    return list_final


def getReglesAssociation(listItemSet = []):

    regles = {}
    t1,t2 = 0,0
    listBut = []
    
    for item in listItemSet:

        if len(list(item)) == 1:
            regles[item] = [[]]
        else :

            temp = list(item)
            
            for nb_item in range(1, len(temp)+1):

                for antecedent in combinations(temp,nb_item ):
                    if antecedent in regles.keys():
                        regles[antecedent].append([x for x in temp if x not in list(antecedent)])
                    else :
                        regles[antecedent] = [[x for x in temp if x not in list(antecedent)]]

    
    return regles


def VerifierRegles(min_conf = 0, Regles : dict = {}, Transactions={}):
    ListeRegles = Regles
    for PartieGauche in ListeRegles.keys():
        t1 = FreqDansTransactions(list(PartieGauche),Transactions)

        for index, PartieDroite in enumerate(ListeRegles[PartieGauche]):

            t2 = FreqDansTransactions(list(PartieDroite),Transactions)

            if (t2/t1)*100 > min_conf: 
                del ListeRegles[PartieGauche][index]
                                  
    return ListeRegles


def FreqDansTransactions(ItemSet:list, Transactions):

    freq = 0
    for t in Transactions.keys():
        exist = False

        for item in ItemSet:
            if item in Transactions[t]:
                exist = True
            else: exist = False
        
        if exist == True : freq+=1
    
    return freq


def Recomandation(ItemSetPreference, Regles):

    recommandation = []
    ListPrefrence = []
    for i in range(1, len(ItemSetPreference)+1):
        ListPrefrence += combinations(ItemSetPreference,i) 
        
    print(ListPrefrence)
    for item in ListPrefrence:
        if item in Regles.keys():
            for PartieGauche in Regles[item]:
                for itemgauche in PartieGauche :
                    if itemgauche not in recommandation:
                        recommandation.append(itemgauche)
    
    return recommandation
    #sghori 
        


                


        
if __name__ == "__main__":

    TransacDict = readDataSet()



    L1 = ConstruireL1(TransacDict)

    for elem in L1.keys():
        print(f"{elem} : {L1[elem]}")

    ItemFrequents = Apriori(TransacDict,4)
    print("====================Liste Item Fequents:")
    for itemset in ItemFrequents:
        print(itemset)

    print("====================:\n\n")
    

    regles = getReglesAssociation(listItemSet=ItemFrequents)
    #print(x)

    print("====================Liste Regles d'association:")
    for regle in regles.keys():
        print(f"{regle} : {regles[regle]}")

    print("====================:\n\n")
    

    reglesavecconfiance = VerifierRegles(40,regles,TransacDict)
    #print(x)

    print("====================Liste Regles d'association avec confiance > 40:")
    for regle in reglesavecconfiance.keys():
        print(f"{regle} : {reglesavecconfiance[regle]}")


    print("====================:\n\n")
    

    recommandation = Recomandation([('People & Blogs', 'sd'), ('Science & Technology', 'hd')],reglesavecconfiance)
    #print(x)

    print("====================Recommandation:")
    for regle in recommandation:
        print(f"{regle}")

    
    

        
    


