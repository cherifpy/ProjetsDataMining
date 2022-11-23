

L = [["A"],["A","B"]]

if ["A","B"] in L : print("yes")


t = ((1,2),(1,2))

print(tuple(L))

    print(k)
        for element in ListCondidat.keys():
            if element in L:
                print(f"{element} : {ListCondidat[element]}")
        
"""
import pandas as pd

class Item:

    def __init__(self,val1,val2):

        self.val1 = val1
        self.val2 = val2

class Transaction:
    

    def __init__(self,Nom:str, ItemSet:list):

        self.Nom = Nom
        self.ItemSet = ItemSet



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
    k = 2
    list_final = []
    ListCondidat = ConstruireL1(TransacDict)

    while len(ListCondidat)>1:
        #print(ListCondidat)
        
        L = []
        for itemset in ListCondidat.keys():
            if ListCondidat[itemset] >= supp_min:
                    
                    L.append(itemset)
                
        
        print(f"Liste L{k-1}")
        for elem in L:
            print(elem)

        ListCondidat = {}
        list_final.append(L)
        NewItem = []

        count = 0
        for i in range(0,len(L)):
            for j in range(i+1,len(L)):
                count +=1
                for item in L[i]:   
                    print("item",item)
                    if item not in NewItem:
                        NewItem.append(item)
    
                for item in L[j]:
                    if item not in NewItem:
                        NewItem.append(item)
                        
                print("newitem", NewItem)
                # tuple(NewItem) not in ListCondidat and len(tuple(NewItem)) == k:
                ListCondidat[tuple(NewItem)] = CalculeSupport(tuple(NewItem),TransacDict)
                NewItem = []
        
        print(f"C{k}================== {count}")
        count = 0
        for elem in ListCondidat.keys():
                print(elem)

        print(f"Fin C{k}")
        #print(len(ListCondidat))
        k+=1
    #list_final.append(ListCondidat.popitem())
    return list_final

        
        
            
                


        
if __name__ == "__main__":

    TransacDict = readDataSet()

    L1 = ConstruireL1(TransacDict)

    for elem in L1.keys():
        print(f"{elem} : {L1[elem]}")

    x = Apriori(TransacDict,4)

    #print(x)
    #for trans in TransacDict.keys():
    #    print(f"{trans} : {TransacDict[trans]}")
    



"""