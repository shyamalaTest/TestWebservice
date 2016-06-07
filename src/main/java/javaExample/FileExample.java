package javaExample;

import java.io.File;
import java.io.IOException;

public class FileExample {
		 public static void main(String args[]){
			 String date = "19052016";
			 String filePath = "E:\\Shyamala_Working";
			    File f = new File(filePath+date);
			    try{
			        if(f.exists()==false){
			            f.mkdir();
			        System.out.println("Directory Created");
			        
			        
			        boolean flag = false;

			     // create File object
			     File stockFile = new File(filePath+date+"Current_Status.txt");
			   //  File stockFile_02 = new File("E://Shyamala_Working/TimeSheet"+date+".ods");

			     try {
			         flag = stockFile.createNewFile();
			        // flag = stockFile_02.createNewFile();
			     } catch (IOException ioe) {
			          System.out.println("Error while Creating File in Java" + ioe);
			     }

			     System.out.println("stock file" + stockFile.getPath() + " created ");

			     }   
			        
			        
			        
			        
			        else{
			        System.out.println("Directory is not created");
			        }
			    }catch(Exception e){
			        e.printStackTrace();
			       }
			    }
		}


