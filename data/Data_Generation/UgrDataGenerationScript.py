# -*- coding: utf-8 -*-
"""
Created on Tue Mar 10 18:11:25 2020

@author: Ugur


"""
#C:\Users\Uğur Keskin\Desktop\Staj\CoronaWork


import pandas as pd
import numpy as np

#Data generation için alinan hazir datalar
_Datacities = pd.read_csv("SpainCities.csv")
_Datanames= pd.read_csv("Randomnames.csv")
_Dataadresses = pd.read_csv("MOCK_DATA.csv")


#%% Rastgele veri üretimi için yazdiğim Fonksiyonlar
from random import randint,choice
import inspect, re
import datetime 
import string
import secrets
import time
def generatePass(count,maxLength=15):
    arr = []
    alphabet = string.ascii_letters + string.digits
    for i in range(count):
        password = ''.join(secrets.choice(alphabet) for i in range(maxLength))
        arr.append(password)
    return arr    

def varname(p):
  for line in inspect.getframeinfo(inspect.currentframe().f_back)[3]:
    m = re.search(r'\bvarname\s*\(\s*([A-Za-z_][A-Za-z0-9_]*)\s*\)', line)
    if m:
      return m.group(1)



def randomEmail(count):
    arr = []
    letters = string.ascii_lowercase
    mails = ["hotmail","gmail","protonmail"]
    for i in range(count):
        word = ''
        word = '{}@{}.com'.format(''.join(choice(letters) for j in range(randint(6,15))),choice(mails))
        arr.append(word)    
    return arr                

def generateArr(arr,N,unique=False,multiple=False,limit=0): #Generate random elements from given array, Cant create unique array of arrays
    n= len(arr)
    tarr = arr.copy()
    newarr = []
    
    if(limit ==0):
        limit = n
    assert  not(n < N and unique==True), "WHEN UNIQUE NUMBER GENERATING, POSSIBLE VALUES MUST LOWER OR EQUAL THEN ELEMENT COUNT "

    for i in range(0,N):

        if(multiple):
            newarr.append(generateArr(arr,randint(1,limit),unique=True))
        else:    
            if(unique):
                index = randint(0,n-i-1)
                newarr.append(tarr[index])
                tarr.remove(tarr[index])
            else:
                index = randint(0,n-1)
                newarr.append(tarr[index])
    return newarr
def randomN(count,n,unique=True,asStrEqualUnique=True,asStr=False,intType="int64",multiple=False,limit=2): #Generate n digit number
    if(multiple):
        return generateArr(randomN(count,n,unique,asStrEqualUnique,asStr,intType,multiple=False).tolist(),count,multiple=True,limit=limit,unique=False)
    if(asStrEqualUnique):
        asStr=unique
    if(asStr):
        arr = np.zeros(count,dtype=object)
        for i in range(0,count):
            string = ""
            while(unique and (string in arr or string =="")):
                string=""
                for j in range(0,n):
                    string= string + str(randint(0,9))
            arr[i]=string    

    else:        
        assert not(pow(10,n) < count and unique==True), "WHEN UNIQUE NUMBER GENERATING, 10^N MUST BE BIGGER THAN COUNT "
        
        
        arr = np.empty(count,dtype=intType)
        for i in range(0,count):
            range_start = 10**(n-1)
            range_end = (10**n)-1
            val =   randint(range_start, range_end)
            while(unique and val in arr):
                val = randint(range_start, range_end)
            arr[i]=val  
 
    return arr
def random_date_generator(start_date, range_in_days,N): #Generate random date
    arr = []
    for i in range(0,N):
        days_to_add = np.arange(0, range_in_days)
        arr.append(np.datetime64(start_date) + np.random.choice(days_to_add))
    return arr    

def randomTF(case1,case2,N): #Generate random True False
    arr = np.random.randint(2,size=N)
    mapFunc = lambda x: case1 if x==1 else case2
    mapFunc=np.vectorize(mapFunc)
    return mapFunc(arr)

def weakEntity(column1,posvalc1,column2,valc2,count):
    
    
    
    pass

def split():
    print("\n____________________________________________________________________________________\n")
    pass

def prinit(caption,x):
    print("\t\t"+caption[:-1].upper()+"\n")
    print(x.head(3))
    print("\n\n{}".format(x.dtypes))
    split()
    
def generateBit(count,n,allZero=False):
    arr = np.empty(count,dtype=object)
    bits = ["0","1"]
    if(allZero):
        bits=["0"]
    for i in range(0,count):
        arr[i] = "".join(generateArr(bits,n))
    return arr



def strIndex(s,index,value):
    s = list(s)
    s[index]= value
    s = ''.join(s)
    return s

def generateTs(count,unique=True,onlyHour=True):
    arr = []
    hour =8
    assert count<24, "24 SAAT DERS OLAMAZ "
    for i in range(count):
        hour = hour+1
        if(onlyHour):
            stringg = hour
        else:    
            stringg =  ("{:0=2d}:00:00".format(hour))
        arr.append(stringg)
    return arr
#%%LOGIN
    
 
PCount = 50 #Person Count
Login_= pd.DataFrame(data={"email":randomEmail(PCount),
                            "password":generatePass(PCount),
                            "authorization_level": np.random.randint(0,3,size=PCount)
                          }
                     )

prinit(varname(Login_),Login_)

#%% ŞUBE
split()

SCount=40 #Sube Count
_Sosyal_Olanaklar_Dizi = ["Cep Sinema","Kapalı Spor Salonu","Açık Spor Salonu", "Yüzme Havuzu", "Halısaha"]
_Toplu_Tasima_Dizi = ["Tramvay","Tren","Otobüs","Minibüs"]
_Ozel_Arac_Dizi = ["Servis", "Otomobil","Kamyon"]
Sube_= pd.DataFrame(data={"name":_Datacities["city"][:SCount].copy(),
                          "phone_number":randomN(SCount,10,multiple=True),
                          "fax": randomN(SCount,10,multiple=True),
                          "address": _Datacities["city"][:SCount].copy(),
                          "public_transport":generateArr(_Toplu_Tasima_Dizi, SCount,multiple=True),
                          "facilities": generateArr(_Sosyal_Olanaklar_Dizi, SCount,multiple=True) ,
                          "private_transport":  generateArr(_Ozel_Arac_Dizi, SCount,multiple=True)
                          }
                   )
prinit(varname(Sube_),Sube_)
#%% FİZİKSEL SINIFLAR

ZCount=SCount*3 #fiZiksel Count
Sinif_= pd.DataFrame(data={"name":randomN(ZCount,3),
                            "capacity":np.random.randint(8,size=ZCount)+10
                          }
                     )

    

prinit(varname(Sinif_),Sinif_)
#%% KİŞİ

_Datanames = _Datanames[:PCount]
Kisi_ = pd.DataFrame(data={"id":randomN(PCount,11)})
Kisi_["phone_number"]=randomN(PCount,10,multiple=True,limit=2)
Kisi_["home_number"]=randomN(PCount,10,multiple=True,limit=1)
Kisi_["work_addr"]=_Datacities["city"][:SCount].copy()

Kisi_ = pd.concat([Kisi_, _Datanames,_Dataadresses[:PCount]],axis=1)
Kisi_.drop(columns=["Phone"],inplace=True)


Kisi_.rename(columns={"First Name":"fname","Last Name":"lname","email":"home_addr"},inplace=True)


prinit(varname(Kisi_),Kisi_)
#%%Ogretmen
OgCount = PCount-20
Ogr_ = pd.DataFrame(data={"id":Kisi_["id"].values[0:OgCount],
                          "start_date":random_date_generator('2012-01-15', 10,OgCount),
                          "known_languages":generateArr(["English","Spanish","French","Turkish"],OgCount,multiple=True),
                           "pworking_hours": generateBit(OgCount, 168,allZero=True)
                           })
prinit(varname(Ogr_),Ogr_)

#%% KURS
KCount = 40 #Kurs Count
Kurs_ = pd.DataFrame(data={"id":randomN(KCount,2),
                           "language":generateArr(["English","Spanish","French","Turkish"],KCount),
                          "name":generateArr(["A","B","C"],KCount),
                          "price":generateArr([30,40,50,60],KCount)
                          })
    
prinit(varname(Kurs_),Kurs_)
#%% DERS
DCount = OgCount*3 #Ders Count
Ders_ = pd.DataFrame(data={"name":generateArr(["Speaking","Listening","Reading","Writing"],DCount)})

prinit(varname(Ders_),Ders_)


#%% FATURA

FCount = PCount #Fatura Count

Fatura_ = pd.DataFrame(data={"id":randomN(FCount,11),
                            "invoice_date":random_date_generator('2020-01-15', 5, FCount),
                            "total":randomN(FCount,3,unique=False)

                            })
prinit(varname(Fatura_),Fatura_)
#! Total = Faturanin bağli olduğu ödemelerin toplami?
#%% ÖDEME

OCount = FCount*2 #Odeme Count

Odeme_ = pd.DataFrame(data={"id":randomN(OCount,4,unique=False),
                            "pay_date":random_date_generator('2020-01-15', 5, OCount),
                            "amount":randomN(OCount,2,unique=False),
                            })

prinit(varname(Odeme_),Odeme_)


#%%----------------------------------------------------------------------------------------------------------------------
#%% İLİŞKİLER


"""
DERS    N-(  Verilir  )-1  ÖĞRETMEN
DERS    N-(Gerçekleşir)-1  SINIF
DERS    N=(  Bulunur  )-1  KURS
SINIF   N-(  Bulunur  )-1  ŞUBE
ŞUBE    N-(Çalişabilir)-N  ÖĞRETMEN
ŞUBE    N-(  Çalişir  )-N  KİŞİ
FATURA  N-(   Keser   )-1  KİŞİ
ÖDEME   N=(   Aittir  )-1  FATURA

KURS    N-(   Sales   )-1 KİŞİ
                |
                N
             FATURA  

"""

#%% 2 Dataframe arasindaki ilişkiyi kurmak için yazdiğim fonksiyonlar
def N4One(dfN,dfOne,FeatureName,newfname=" "):
    if(newfname==" "):
        newfname = FeatureName
        
    values = dfOne[FeatureName].values
    dfN[newfname]= generateArr(values, dfN.shape[0])
    
def N4N(dfN1,dfN2,Feature1,Feature2,newfname1=" ",newfname2=" "):
    if(newfname1==" "):
        newfname1 = Feature1
    if(newfname2==" "):
        newfname2 = Feature2    
    Count = max(dfN1.shape[0],dfN2.shape[0])
    new = pd.DataFrame(data={newfname1:generateArr(dfN1[Feature1].values,Count),
                             newfname2:generateArr(dfN2[Feature2].values,Count)
                            })
    return new
    
    
#%% Fiziksel SINIF-SUBE

N4One                   (Ders_,Sinif_,"name","classroom_id")
N4One                   (Ders_,Ogr_,"id","instructor_id")
N4One                   (Sinif_,Sube_,"name","branch_name")
N4One                   (Fatura_,Kisi_,"id","executo_id")
N4One                   (Odeme_,Fatura_,"id","invoice_id")
 
CalisabilirOgr_ = N4N   (Sube_, Ogr_,"name","id","branch_name","instructor_id")
Calisir_        = N4N   (Sube_,Kisi_,"name","id","branch_name","person_id")
Sales_          = N4N   (Fatura_,Kurs_,"id","id","invoice_number","course_id")



prinit                  (varname(Ders_),Ders_)
prinit                  (varname(Kisi_),Kisi_)
prinit                  (varname(Sinif_),Sinif_)
prinit                  (varname(Sube_),Sube_)
prinit                  (varname(Odeme_),Odeme_)
prinit                  (varname(Fatura_),Fatura_)
prinit                  (varname(Kurs_),Kurs_)
prinit                  (varname(Calisir_),Calisir_)
prinit                  (varname(CalisabilirOgr_),CalisabilirOgr_)


#%% Fatura tutarları
for i in range(Fatura_.shape[0]):
    OdemeSum = 0
    odemes = Odeme_[Odeme_["invoice_id"]==Fatura_["id"][i]]["amount"].values
    for tutar in odemes:
        OdemeSum = OdemeSum + tutar      
    Fatura_.loc[i,"total"]= OdemeSum
    
#%% Sales düzeltmesi
Scount =Sales_.shape[0]
Sales_["total"]= randomN(Scount,3,unique=False)
Sales_["max_no_of_payments"] = np.random.randint(1,12,Scount)
Sales_["customer_id"]=generateArr(Kisi_["id"].values,Scount)    

for i in range(Scount):
    faturaSum = 0
    faturas = Fatura_[Fatura_["id"]==Sales_["invoice_number"][i]]["total"].values
    for tutar in faturas:
        faturaSum = faturaSum + tutar      
    Sales_.loc[i,"total"]= faturaSum-randint(0,int(faturaSum/2))    
prinit(varname(Sales_),Sales_)
#%%Login üdzeltmesi
Login_["person_id"] = Kisi_["id"].values
i = 0;
for person_id in Login_["person_id"].values:
    if(person_id in Ogr_["id"].values):
        Login_.loc[i,"authorization_level"] = 1
    elif(Login_["authorization_level"][i]==0):    
        Login_.loc[i,"authorization_level"]= choice([0,2])
    i = i+1    
        

#%% Ders Düzenleme
Ders_["instructor_id"]=generateArr(Ogr_["id"].values,DCount)
Ders_["lesson_ts"] = 0

Subeler = Sube_["name"].values
Ders_["lesson_date"]=random_date_generator('2021-01-15', 5, DCount)

for i in range(0,Subeler.shape[0]):
    Siniff = Sinif_[Sinif_["branch_name"]==Subeler[i]]
    if(Siniff.size>0):
        Ders_.loc[Ders_.classroom_id.isin(Siniff["name"]),"lesson_ts"]=generateTs(Ders_.loc[Ders_.classroom_id.isin(Siniff["name"]),"lesson_ts"].size)

Ders_["course_no"] = generateArr(Kurs_["id"].values,DCount)


#%%Ogretmen Duzeltmesi
for i in range(Ders_.shape[0]):
    DersSaat = int(Ders_["lesson_ts"][i])
    DersGun = Ders_["lesson_date"][i].weekday()
    stringg = Ogr_[Ogr_["id"]==Ders_["instructor_id"][i]]["pworking_hours"].values[0]
    stringg = strIndex(stringg,DersGun*24+DersSaat, '1') 
    Ogr_.loc[Ogr_["id"]==Ders_["instructor_id"][i],"pworking_hours"]= stringg
#%%Ders Format DÜzenleme
for i in range(Ders_.shape[0]):
    Ders_.loc[i,"lesson_ts"]= "{:02d}:00:00".format(int(Ders_["lesson_ts"][i]))

#%%SIRA DÜZENLEMELERİ
Kisi_ = Kisi_[["id","fname","lname","phone_number","home_number","home_addr","work_addr"]]    
Login_ = Login_[["email","password","person_id","authorization_level"]]
Ders_ = Ders_[["name","course_no","instructor_id","classroom_id","lesson_date","lesson_ts"]]
Sales_ = Sales_[["invoice_number","customer_id","course_id","total","max_no_of_payments"]]

    

#%% CSV Convert
    
path = "Course_Automation_Data/"
Login_.to_csv(path+"Login.csv",index=False)
Ders_.to_csv(path+"Ders.csv",index=False)
Ogr_.to_csv(path+"Ogr.csv",index=False)
Kisi_.to_csv(path+"Kisi.csv",index=False)
Sinif_.to_csv(path+"Sinif.csv",index=False)
Sube_.to_csv(path+"Sube.csv",index=False)
Odeme_.to_csv(path+"Odeme.csv",index=False)
Fatura_.to_csv(path+"Fatura.csv",index=False)
Kurs_.to_csv(path+"Kurs.csv",index=False)
Calisir_.to_csv(path+"Calisir.csv",index=False)   
CalisabilirOgr_.to_csv(path+"CalisabilirOgr.csv",index=False)
Sales_.to_csv(path+"Sales.csv",index=False)
#%%IMPORT FOR EDİT
import pandas as pd
import numpy as np
path = "Course_Automation_Data/"

Login_= pd.read_csv(path+"Login.csv" )
Ders_  = pd.read_csv(path+"Ders.csv" )
Ogr_  = pd.read_csv(path+"Ogr.csv" )
Kisi_  = pd.read_csv(path+"Kisi.csv" )
Sinif_  = pd.read_csv(path+"Sinif.csv" )
Sube_  = pd.read_csv(path+"Sube.csv" )
Odeme_  = pd.read_csv(path+"Odeme.csv" )
Fatura_  = pd.read_csv(path+"Fatura.csv" )
Kurs_  = pd.read_csv(path+"Kurs.csv" )
Calisir_  = pd.read_csv(path+"Calisir.csv" )   
CalisabilirOgr_  = pd.read_csv(path+"CalisabilirOgr.csv" )
Sales_  = pd.read_csv(path+"Sales.csv" )

#%% PWORKING HOURS TO OCTAL FORMAT
def conv(a):
    strr = ""
    for i in range(int(168/8)):
        strr = strr + (chr(int(a[i:i+8],2)))
    
    return strr
def conv2(a):
    strr = ""
    for i in range(int(168/4)):
        strr = strr + (hex(int(a[i*4:i*4+4],2)))[2:]
   # strr = strr+"'"     
    print(strr)
    print(len(strr))
    return strr

conv(Ogr_.pworking_hours.values[4])    
conv2(Ogr_.pworking_hours.values[1])   
for i in range(Ogr_.shape[0]):
    Ogr_.loc[i,"pworking_hours"] = conv2(Ogr_.pworking_hours.values[i])
Ogr_.to_csv(path+"pWorkingHex.csv",index=False)
#%%Tırnak Düzenlemesi
def Edit(pandas_,feature):
    for i in range(len(pandas_[feature].values)):
        pandas_.loc[i,feature] = pandas_[feature].values[i].replace("['","[")
        pandas_.loc[i,feature] = pandas_[feature].values[i].replace("']","]")
        pandas_.loc[i,feature] = pandas_[feature].values[i].replace("', '","], [")
        pandas_.loc[i,feature] = "{" + pandas_[feature].values[i] + "}"
Edit(Kisi_,"phone_number")        
Edit(Kisi_,"home_number")        
Edit(Ogr_,"known_languages")    
Edit(Sube_,"phone_number")        
Edit(Sube_,"fax")        
Edit(Sube_,"public_transport")        
Edit(Sube_,"private_transport")        
Edit(Sube_,"facilities")        

#%% CSV Convert 2
    
path = "Course_Automation_Data/"
Login_.to_csv(path+"Login.csv",index=False)
Ders_.to_csv(path+"Ders.csv",index=False)
Ogr_.to_csv(path+"Ogr.csv",index=False)
Kisi_.to_csv(path+"Kisi.csv",index=False)
Sinif_.to_csv(path+"Sinif.csv",index=False)
Sube_.to_csv(path+"Sube.csv",index=False)
Odeme_.to_csv(path+"Odeme.csv",index=False)
Fatura_.to_csv(path+"Fatura.csv",index=False)
Kurs_.to_csv(path+"Kurs.csv",index=False)
Calisir_.to_csv(path+"Calisir.csv",index=False)   
CalisabilirOgr_.to_csv(path+"CalisabilirOgr.csv",index=False)
Sales_.to_csv(path+"Sales.csv",index=False)