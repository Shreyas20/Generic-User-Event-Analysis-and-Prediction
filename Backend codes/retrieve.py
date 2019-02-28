import os
import sys
from pymongo import MongoClient
connection = MongoClient("localhost:27017")
db = connection.test.Generic
#db1=connection.test.Filter

flag = True


results = db.find({}) 
f=open("dataset.arff","w+")
f.write("@relation events\n")
f.write("@attribute 'age' real\n")
f.write("@attribute 'prof' {Teacher,Student,Doctor,Advocate,Physician,Dietitian,Technician,Accountant,Laborer,Pharmacist,other}\n")
f.write("@attribute 'gender' {male,female}\n")
f.write("@attribute 'city' {Mumbai,Delhi,Bengaluru,Hydrabad,Ahmedabad,Chennai,Kolkata,Surat,Pune,Lucknow,other-city,other-foreign-city}\n")
f.write("@attribute 'country' {US,China,Japan,Germany,UK,France,India,Italy,Brazil,Canada,other}\n")
f.write("@attribute 'category' {casual,formal,sports,english,spainish,italian,nokia,micromax,samsung,landscape,pop music,adventure,Tom & Jerry,Painting,Eric Thomas}\n")
f.write("@data\n")
print('+-+-+-+-+-+-+-+-+-+-+-+-+-+-')


for record in results:
# print out the document
	f.write(record['age']+",")
	f.write(record['prof']+",")
	f.write(record['gender']+",")
	f.write(record['city']+",")
	f.write(record['country']+",")
	f.write(record['subcategory'])
	f.write("\n")
	print(record['name'])
	
f.close()
connection.close()
