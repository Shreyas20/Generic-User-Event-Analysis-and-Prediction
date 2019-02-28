import os
from pymongo import MongoClient
connection = MongoClient("localhost:27017")
db = connection.test.Generic
#db1=connection.test.Filter
student_record = {}
flag = True
results = db.find({'prof':{'$eq':'Student'}})

print('+-+-+-+-+-+-+-+-+-+-+-+-+-+-')

for record in results:
# print out the document
	print(record['image_name']+record['subcategory']+record['category']+record['price']+record['date']+record['name']+record['age']+record['prof']+record['gender']+record['city']+record['country']+record['email'])
	#db.update({ 'flag': 'false' },{'$set': { 'flag': 'true' } });
	
	val1=record['image_name']
	val2=record['subcategory']
	val3=record['category']
	val4=record['price']
	val5=record['date']
	val6=record['name']
	val7=record['age']
	val8=record['prof']
	val9=record['gender']
	val10=record['city']
	val11=record['country']
	val12=record['email']
	
	curl1="curl -g -XPOST http://localhost:9200/students/shopping -d \'{ \"Image Name\":\""
	curl2="\" , \"Sub-Category\": \""
	curl3="\" , \"Category\": \""
	curl4="\" , \"Price\": "
	curl5=" , \"Date\": \""
	curl6="\" , \"Name\": \""
	curl7="\" , \"Age\": "
	curl8=" , \"Profession\": \""
	curl9="\" , \"Gender\": \""
	curl10="\" , \"City\": \""
	curl11="\" , \"Country\": \""
	curl12="\" , \"Email Id\": \""
	#curl16="\" , \"\": \""
	curl13="\"  }\'"
	
	cmd4=curl1+val1+curl2+val2+curl3+val3+curl4+val4+curl5+val5+curl6+val6+curl7+val7+curl8+val8+curl9+val9+curl10+val10+curl11+val11+curl12+val12+curl13
	os.system(cmd4)
	
 
print()
connection.close()
