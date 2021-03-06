package com.example.android.surveyapk.QuestionTypes;
import android.R.*;
import android.os.Environment;
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
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.MainActivity;
import com.example.android.surveyapk.clock.CustomDigitalClock;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class LabelClock extends InputElement {
private static final CustomDigitalClock CustomDigitalClock = null;
public String text;
	public LabelClock(Question question) {
		super(question);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View display(Activity context) {
		TextView label = new TextView(context);
		label.setText(text);
	    label.setTextSize(25);
        label.setPadding(5, 4, 3, 3);
        //label.setPadding(left, top, right, bottom)
        label.setGravity(Gravity.LEFT );
        //label.setGravity(Gravity.CENTER );

        label.setTextColor(Color.WHITE);

        // label.setBackgroundResource(R.drawable.b_cub); //SUPER oder
        // label.setBackgroundResource(R.drawable.g_cub45); //SUPER oder
        //label.setBackgroundColor(Color.rgb(80, 80, 80)); //color 
        // label.setBackgroundColor(Color.BLUE);
        label.setBackgroundResource(R.drawable.borderg); //SUPER oder
        //?label.setHeight(46);
                  
	   	//LabelClock	lb=new LabelClock(question);

        //-- CustomDigitalClock dc = (CustomDigitalClock);
        //-- dc.setBackgroundResource(R.drawable.borderg); //SUPER oder
		//return dc;
        //  DigitalClock digital = (DigitalClock) context.findViewById(R.id.digital_clock);

	 return label;

	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
		// TODO Auto-generated method stub
	}
	

	@Override
	public String writeData( ) {
		return "";
	}
	public String writeDataToPdf( ) {
		// text="addCell( "+text+")";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
        String TimeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds; 	
        
        MainActivity.alphaDef+=  ", `"+question.name+"-Clock"+"` TEXT";
        MainActivity.alphaName+=", "+question.name+"-Clock";
        MainActivity.alphaVal+=",'"+val+"'";
        
		return TimeString;
	}
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
    	//val=String.valueOf(progress);
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
		Log.w(">>>999 Radio int validate(xxx):", "albertRadioTest: "+albertRadioTest);
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
	
