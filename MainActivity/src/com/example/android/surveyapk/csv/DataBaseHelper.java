package com.example.android.surveyapk.csv;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	 
    private Context context;


	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
    

	// Logcat tag
    private static final String LOG = "DatabaseHelper"; 
    private static final int DATABASE_VERSION = 1; 
    private static final String DATABASE_NAME = "svdb";
    private static final String DB_NAME = "svdb";
    private static String DB_PATH = "/data/data/effectivenavigation/databases/";
    
    
    private void copyDataBase() throws IOException{    	 
    	//Open your local db as the input stream
    	InputStream myInput = (InputStream) context.getAssets().open(DB_NAME);    	 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;    	 
    	//Open the empty db as the output stream
    	FileOutputStream myOutput = new FileOutputStream(outFileName);    	 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    	myOutput.write(buffer, 0, length);
    	}
    	 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    	 
    	}
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
	   private boolean checkDataBase(){   
			   SQLiteDatabase checkDB = null;    
			   try{
			   String myPath = DB_PATH + DB_NAME;
			   checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);   
			   }catch(SQLiteException e){    
			   //database does't exist yet.   
			   }
			    
			   if(checkDB != null){    
			   checkDB.close();    
			   }    
			   return checkDB != null ? true : false;
	   }
    
    
    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
   public void createDataBase() throws IOException{    
	   boolean dbExist = checkDataBase();	    
	   if(dbExist){
	   //do nothing - database already exist
	   }else{	    
	   //By calling this method and empty database will be created into the default system path
	   //of your application so we are gonna be able to overwrite that database with our database.
	   this.getReadableDatabase();	    
	   try {	    
	   copyDataBase();	    
	   } catch (IOException e) {	    
	   throw new Error("Error copying database");	    
	   }
	   }
    
   }
    

 
    // Table Names
    private static final String TABLE_TODO = "todos";
    private static final String TABLE_TAG = "tags";
    private static final String TABLE_TODO_TAG = "todo_tags";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at"; 
    // NOTES Table - column nmaes
    private static final String KEY_TODO = "todo";
    private static final String KEY_STATUS = "status";
    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name"; 
    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";
 
    // Table Create Statements
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TODO
            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    // Tag table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")"; 
    // todo_tag table create statement
    private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_TODO_TAG);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_TAG);
 
        // create new tables
        onCreate(db);
    }
}