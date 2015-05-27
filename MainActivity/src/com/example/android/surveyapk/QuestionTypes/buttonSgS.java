package com.example.android.surveyapk.QuestionTypes;
import android.R.*;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.MainActivity;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

public class buttonSgS extends  InputElement  implements TextWatcher
{	
	Question question;
	public String defaultText;
	public String name=null;

	EditText textbox;
	TextBox textBox2; // Add !!
	Button btn3;
	public buttonSgS(Question question) {
		super(question);
		this.question=question;
		
	}

	@Override
	public View display(Activity context) {
		// Toast.makeText(context, " textbox :", Toast.LENGTH_LONG).show(); 			 
		  Button myButton = new Button(context);
          myButton.setText("Speichern");
          myButton.setTextSize(25);
          //myButton.setBackgroundResource(R.drawable.border3); //SUPER oder       
		 
		 // =======================================================================
          myButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	             // toast 
	        		Toast toast = Toast.makeText(v.getContext(), "Gespeichert", Toast.LENGTH_SHORT);
	    		    TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
	    			toast.setGravity(Gravity.TOP, 0, 0);
	    		    toast.getView().setBackgroundColor(Color.GREEN);
	    		    toast.getView().setPadding(10, 10, 10, 10);
	    		    text.setTextColor(Color.WHITE);
	    		    text.setTextSize(24); 
	    			toast.show();
	        	    //	=====================	      
	        	    
	        		View v2 = v.getRootView();
	        		//no View v2 = v.findViewById(R.id.mainlayout);
	        	    Bitmap bitmap = Bitmap.createBitmap(v2.getWidth(), v2.getHeight(), Config.ARGB_8888);
	        	    Canvas canvas = new Canvas(bitmap);
	        	    v2.draw(canvas);
	        	    // return bitmap;
                    File sdCard = Environment.getExternalStorageDirectory();
                    File rootA = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
                    String pathA = rootA+"/4Survey/outbox/pdfbox/";
                    File directory = new File(pathA);
                    // directory.mkdirs();
                    Random rn1 = new Random();
                    int A = rn1.nextInt(100000);                       
                    String filename = "sg"+".jpg";
                    File yourFile = new File(pathA, filename);
	            		if(yourFile.delete()){
	                		Log.w("del", "  delete: "+pathA );
	            		}else{
	                		Log.w("del", "  not delete: "+pathA );
	            		}

                    FileOutputStream out;
					try {
						out = new FileOutputStream(yourFile, true);
	                    bitmap.compress(Bitmap.CompressFormat.PNG, 90,out);
		                    try {
								out.flush();
			                    out.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    Toast.makeText(v.getContext(), "ok", Toast.LENGTH_SHORT);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	    
	            	}
	        	});	// reset listener !
		 
	     return myButton;

	      //---------------------------------------------------------------------------------------
		  // String tIMG = "tpj/dv_hand.jpg";
          // String url = Environment.getExternalStorageDirectory().getAbsolutePath()+"/4Survey/"+tIMG;          
          // File imgFile = new  File(url);
          // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
          // ImageView myImage = new ImageView(context);
          // myImage.setImageBitmap(myBitmap);
          // myImage.setLayoutParams(new GridView.LayoutParams(70, 70));
          // return myImage;
	      //---------------------------------------------------------------------------------------


	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
		// TODO Auto-generated method stub
	}
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
    	//val=String.valueOf(progress);
    }

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		val=textbox.getText().toString();
		System.out.println(val);
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}


	@Override
	public String writeData() {
		String outBuf = "";
		outBuf+=this.val;
		return outBuf;
	}

	@Override
	public String writeDataToPdf() {
		String outBuf = "";
		outBuf+=this.val;

        MainActivity.alphaDef+=  ", `"+question.name+"-"+name+"` REAL";
        MainActivity.alphaName+=", "+question.name+"-"+name;
        MainActivity.alphaVal+=",'"+val+"'";
		 Log.w("ANGARA", "  ANGARA2 MainActivity.alphaName: "+MainActivity.alphaName );
		 Log.w("ANGARA", "  ANGARA2 MainActivity.alphaVal: "+MainActivity.alphaVal );
		
		return outBuf;
	}
	
	/*	*/
	@Override
	public int validate()
	{
	// Log.w("Radio boolean validate()77:", "this.isValidCount:"+this.isValidCount);
	// return this.isValidCount;
	  int tst=1;
	  return tst;

	}
	@Override
	public int validate(String albertRadioTest)
	{	//ToDo	
		int isValidNr =1 ;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: "+albertRadioTest);
		// return this.isValidCount;
		// return isValidCount; 
		return isValidNr; 

	}
	@Override
	public String validate(String albertRadioTest,String albertRadioName, Activity context )
	{		
		// this.isGroupValidated ??? ------------------------------------------------------------------------------------------------------------------------------
		Toast myToast = Toast.makeText(context, "007 albertRadioTest:"+albertRadioTest, Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.RIGHT, 0, 0);
		myToast.show(); 
		//=-----------------------------------------
		String stringBox=albertRadioTest;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: "+albertRadioTest);
		return stringBox; 

	}
}
