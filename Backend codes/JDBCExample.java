//STEP 1. Import required packages
import java.sql.*;
import java.io.*;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class JDBCExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/mysql";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null,stmt1=null;
   BufferedWriter bw = null;
	FileWriter fw = null;
   ResultSet rs=null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      stmt1=conn.createStatement();

      String sql = "SELECT * FROM profile";
      rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
        // int id  = rs.getInt("");
         
        // String last = rs.getString("last");
	String FILENAME = "/home/shreyas/scripts/demo-test.arff";
	String content = "@relation events\n@attribute 'age' numeric\n@attribute 'prof' {Teacher,Student,Doctor,Advocate,Physician,Dietitian,Technician,Accountant,Laborer,Pharmacist,other}\n@attribute 'gender' {male,female}\n@attribute 'city' {Mumbai,Delhi,Bengaluru,Hydrabad,Ahmedabad,Chennai,Kolkata,Surat,Pune,Lucknow,other-city,other-foreign-city}\n@attribute 'country' {US,China,Japan,Germany,UK,France,India,Italy,Brazil,Canada,other}\n@attribute 'category' {casual,formal,sports,english,spainish,italian,nokia,micromax,samsung,landscape,pop music,adventure,Tom & Jerry,Painting,Eric Thomas}\n@data\n";
	fw = new FileWriter(FILENAME);
	bw = new BufferedWriter(fw);
	bw.write(content);
	String email = rs.getString("email");
	String str = rs.getString("age");
	str=str+",";
	bw.write(str);
	str = rs.getString("profession");
	str=str+",";
	bw.write(str);
	str = rs.getString("gender");
	str=str+",";
	bw.write(str);
	str = rs.getString("city");
	str=str+",";
	bw.write(str);
	str = rs.getString("country");
	str=str+",";
	bw.write(str);
	str = "?";
	bw.write(str);
	bw.close();
	fw.close();
         //Display values
         //System.out.print("ID: " + id);
         //System.out.print(", Age: " + age);
         //System.out.print(", First: " + first);
	Classifier j48tree = new J48();
        Instances train = new Instances(new BufferedReader(new FileReader("demo.arff")));
        int lastIndex = train.numAttributes() - 1;
            
        train.setClassIndex(lastIndex);
            
        Instances test = new Instances(new BufferedReader(new FileReader("demo-test.arff")));
        test.setClassIndex(lastIndex);
            
        j48tree.buildClassifier(train);
	String classname="";
            
        for(int i=0; i<test.numInstances(); i++) {
            
        double index = j48tree.classifyInstance(test.instance(i));
        classname = train.attribute(lastIndex).value((int)index);
        //System.out.println(className);
        }
	System.out.println(email);
	System.out.println(classname);
	sql = "UPDATE profile " + "SET recommend = '"+classname+"' WHERE email = '"+email+"'";
        stmt1.executeUpdate(sql);
        
      }
      rs.close();
      

      
   }
catch (IOException e) {

			e.printStackTrace();
			

		}
catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      
      try{
	
         if(stmt!=null)
         { 
            conn.close(); 
            }
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }
      catch(SQLException se){
         se.printStackTrace();
      }//end finally try
/*try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}*/

   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample
