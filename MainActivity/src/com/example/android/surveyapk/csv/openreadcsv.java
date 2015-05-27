package com.example.android.surveyapk.csv;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import au.com.bytecode.opencsv.CSVWriter;
import org.xmlpull.v1.XmlPullParserException;
import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.QuestionParser.*;
import com.example.android.surveyapk.QuestionTypes.*;
import com.example.android.surveyapk.directorychooser.DirectoryChooserDialog;

import com.example.android.effectivenavigation.R;


public class openreadcsv {
    public static int delta =0;
    public static long  sVersion =15; 
    public static String timeMS ="";
    public static String strTimeS ="";
    public static int timeS = 0;
    public static String korb ="";



	public void openreadcsv() {	   
		// Log.w("line ...", " achshav theline line timeNow: ");
		
	}//end 
	
	public static long readcsv(String alpha, String betha) {
		String line = "";
		String cvsSplitBy = ",";
		long longPerviRaz=1424925425721L;
		long longPervoePolzovanie=0;
		long longKonezRibalki = 1454367600000L;
		long longPosledniePolzovanie =0;
		File rootL = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
		String pathL = rootL+"/4Survey/log/";
		File xxlogFileL = new File(pathL+"log.csv");   
	    long timeNow= System.currentTimeMillis() ;
	    Date cDate = new Date(timeNow);
	    timeMS=Long.toString(timeNow); // the LastTimeMs in Ms
	    timeS = (int) (timeNow /1000) ;
		Log.w("line ...", " spez timeS: "+timeS);		
	    timeS = (int) (timeNow /1000) - 1400000000 ;
		Log.w("line ...", " spez timeS: "+timeS);
	    strTimeS=Long.toString(timeS); // the LastTimeMs in Ms
		Log.w("line ...", " spez strTimeS: "+strTimeS);
		//*******************
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
        String logTimeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds; 
        //************************    
	    String magnitMM =""; 
	    magnitMM+=timeMS; // the LastTimeMs in Ms
		magnitMM+=","+alpha;
		magnitMM+=","+betha;	
		magnitMM+=","+"SurveyAPK";	
		magnitMM+=","+logTimeString;	
		magnitMM+=","+delta;	
		
		Log.w("line ...", " lineXXX read !!!pathL: "+pathL); // sentence.replace("and", " ");
		Log.w("line ...", " achshav theline line longPosledniePolzovanie: "+longPosledniePolzovanie);
		Log.w("line ...", " achshav theline line timeNow: "+timeNow);
		Log.w("line ...", " achshav theline line longKonezRibalki: "+longKonezRibalki);
		Log.w("line ...", " achshav theline line magnitMM: "+magnitMM);


		//******PreCHETAIU*****************************************************
			if(!xxlogFileL.exists()){
				try {
					xxlogFileL.createNewFile();
					CSVWriter writer1 = new CSVWriter(new FileWriter(xxlogFileL, true));
					String [] record1 = magnitMM.split(",");
					writer1.writeNext(record1);
					writer1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//NetuLog						
		 //******CHETAIU*****************************************************
				File file = new File(pathL+"log.csv");
				StringBuilder text = new StringBuilder();
				String[] logic;
				try {
				    BufferedReader br = new BufferedReader(new FileReader(file));
		            int i=0;
				    while ((line = br.readLine()) != null) {
				        line = line.replace("\"", "");		        
						   Log.w("line ...", " line read line: "+line);
						   logic = line.split(cvsSplitBy);
						   //Log.w("line ...", " line read longPosledniePolzovanie: "+longPosledniePolzovanie);
					    	if(i==0){ // Krug Pervii !!
					    		longPervoePolzovanie = Long.valueOf(logic[0]);
					    		Log.w("line ...", " achshav theline line logic[0]: "+logic[0]);
					    		Log.w("line ...", " achshav theline line logic[1]: "+logic[1]);
					    		Log.w("line ...", " achshav theline line logic[2]: "+logic[2]);
					    		Log.w("line ...", " achshav theline line logic[3]: "+logic[3]);
					    		Log.w("line ...", " achshav theline line logic[4]: "+logic[4]);
					    		Log.w("line ...", " achshav3 theline line logic[5]: "+logic[5]);

					    	}	
					    	// Krug Poslednii 
							longPosledniePolzovanie = Long.valueOf(logic[0]);
				    		Log.w("line ...", " achshav3 theline line logic[0]: "+logic[0]);
				    		Log.w("line ...", " achshav3 theline line logic[1]: "+logic[1]);
				    		Log.w("line ...", " achshav3 theline line logic[2]: "+logic[2]);
				    		Log.w("line ...", " achshav3 theline line logic[3]: "+logic[3]);
				    		Log.w("line ...", " achshav3 theline line logic[4]: "+logic[4]);
				    		Log.w("line ...", " achshav3 theline line logic[5]: "+logic[5]);

							delta = Integer.valueOf(logic[5]);

		             i ++;
				    }
				    br.close();
				}
				catch (IOException e) {
				    //You'll need to add proper error handling here
				}// End_Chetaiu				
			   Log.w("line ...", " achshav2  longPosledniePolzovanie: "+longPosledniePolzovanie);
			   Log.w("line ...", " achshav2 timeNow: "+timeNow);
			   Log.w("line ...", " achshav2 longKonezRibalki: "+longKonezRibalki);	
			   Log.w("line ...", " achshav2 sVersion: "+sVersion);	
			   Log.w("line ...", " achshav2 delta: "+delta);				   
		   //******Praverij i Pishi log ***********************************		
		   if ( delta == 0){ // IF AA
			   if ( timeNow < longKonezRibalki ){ // IF AA
						if ( timeNow > longPosledniePolzovanie ){ // IF AAA
							// mnsk1 //mnsk1 //mnsk1 //mnsk1 //mnsk1 //mnsk1 //mnsk1 //mnsk1 //mnsk1
									try {
									  // if(!xxlogFileL.exists()){
									  // xxlogFileL.createNewFile();}	
									  //delta --;
										magnitMM =""; 
									    magnitMM+=timeMS; // the LastTimeMs in Ms
										magnitMM+=","+alpha;
										magnitMM+=","+betha;	
										magnitMM+=","+"SurveyAPK";	
										magnitMM+=","+logTimeString;	
										magnitMM+=","+delta;
										
										CSVWriter writer15 = new CSVWriter(new FileWriter(xxlogFileL, true));
										String [] record15 = magnitMM.split(",");
										writer15.writeNext(record15);
										writer15.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}							
						}else{ delta++;	}// END_IF AAA							
				 }else {  delta++; }	
			 } else { delta++; }
		return delta;	
	 }//end getCSVnow method


}//end_class
