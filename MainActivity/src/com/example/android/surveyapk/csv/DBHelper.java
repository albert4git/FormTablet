package com.example.android.surveyapk.csv;

import com.example.android.surveyapk.MainActivity;

import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  
import android.util.Log;
 
public class DBHelper extends SQLiteOpenHelper {  
   private static final String DB_NAME = "svtst.db";  
   private static final int DB_VERSION = 2;  
         
   private static class UserTable {  
       private static final String NAME = "user";  
       private static final String COL_ID = "id";  
       private static final String COL_USERNAME = "username";  
       private static final String COL_PASSWORD = "password";  
   }   
   private SQLiteDatabase db;  
  // Constructor to simplify Business logic access to the repository   
   public DBHelper(Context context) {  
       super(context, DB_NAME, null, DB_VERSION);  
       this.db = this.getWritableDatabase();  
   }  
 
   @Override  
   public void onCreate(SQLiteDatabase db) {                      
	  // db.execSQL("DELETE FROM sv_history WHERE Age > 0");
	  // db.execSQL(" DROP TABLE sv_history");	
	  // "CREATE TABLE `sv_history` (`SER` TEXT,`TimeMS` REAL,`Age` INTEGER,`Gender` TEXT,`AGroup` TEXT,`Study` TEXT,`SubjectIs` TEXT,`Treatment`	TEXT,`Repetition` TEXT,`Comment` TEXT,`Date` TEXT,`SName` TEXT,`Diagnose` TEXT,`Survay`	TEXT,`Score` INTEGER)"
   Log.w("Seide", "13 spic onCreate " );
   db.execSQL(
	"CREATE TABLE `sv1_history` ( `_ID` INT PRIMARY KEY  NOT NULL, `SER`	TEXT, `TimeMS`	REAL, `Age`	INTEGER, `Gender`	TEXT, `RGroup`	TEXT, `Study`	TEXT, `Subject`	TEXT, `Treatment`	TEXT, `Repetition`	TEXT, `Pharmacology`	TEXT, `Dose`	TEXT, `Comment`	TEXT, `Date`	TEXT, `SName`	TEXT, `Diagnose`	TEXT, `Survay`	TEXT, `Score`	INTEGER)"
    );  
   Log.w("Seide", "14 spic onCreate " );
   }//end onCreate  
 
     
   public String sqlIn(String qIn, String cIn) { 
	   //db.execSQL("CREATE TABLE IF NOT EXISTS `sv1_history` ( `_ID` INT PRIMARY KEY  NOT NULL, `SER`	TEXT, `TimeMS`	REAL, `Age`	INTEGER, `Gender`	TEXT, `RGroup`	TEXT, `Study`	TEXT, `Subject`	TEXT, `Treatment`	TEXT, `Repetition`	TEXT, `Pharmacology`	TEXT, `Dose`	TEXT, `Comment`	TEXT, `Date`	TEXT, `SName`	TEXT, `Diagnose`	TEXT, `Survay`	TEXT, `Score`	INTEGER)" );  
       //****************** ****** *******
	       // db.execSQL("DELETE FROM sv_history WHERE Age > 0");
	       Log.w("Seide", "15 spic onCreate "+cIn );
           db.execSQL( cIn ); 
    	   Log.w("Seide", "16 spic sqlIn "+qIn );
	       db.execSQL( qIn );    
		   Log.w("Seide", "16.1 spic sqlIn ");
       //****************** ***** ********
       String[] qline;  
       Cursor cursor;   
       qline = new String[5];  
      
       String query = "SELECT * FROM sv1_history";
       cursor = db.rawQuery(query, null);
       
       if (cursor.moveToFirst()) {  
    	   do {
           qline[0] = cursor.getString(0);  
           qline[1] = cursor.getString(1);  
           qline[3] = cursor.getString(13);  
           qline[4] = cursor.getString(14);  

           // Log.w("Seide", "spic 0:"+qline[0]);
           // Log.w("Seide", "spic 1:"+qline[1]+"");
           // Log.w("Seide", "spic 3:"+qline[3]+"");
           // Log.w("Seide", "spic 4:"+qline[4]+"");

           } while (cursor.moveToNext());

       }
       cursor.close(); 
	   // db.execSQL("DELETE FROM sv_history WHERE Age > 0");
	   Log.w("Seide", "17 spic END sqlIn " );

	return qline[0];
   }  
 
   @Override  
   public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {  
       // Later when you change the DB_VERSION   
               // This code will be invoked to bring your database  
               // Upto the correct specification  
   }  
}  