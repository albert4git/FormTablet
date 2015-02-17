package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import java.util.ArrayList;
import java.util.List;

import com.example.android.effectivenavigation.R;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class Label extends InputElement {
public String text;
	public Label(Question question) {
		super(question);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View display(Activity context) {
		TextView label = new TextView(context);
		label.setText(text);
	    label.setTextSize(25);
        label.setPadding(1, 0, 0, 1);
        label.setGravity(Gravity.CENTER );

        // label.setBackgroundResource(R.drawable.b_cub); //SUPER oder
        label.setBackgroundResource(R.drawable.g_cub45); //SUPER oder
        //label.setBackgroundColor(Color.rgb(80, 80, 80)); //color 
        // label.setBackgroundColor(Color.rgb(125, 209,110));
        // label.setBackgroundColor(Color.BLUE);
        // label.setBackgroundColor(Color.BLUE);
        //?label.setHeight(46);
                  
           
		return label;
	}



	@Override
	public String writeData( ) {
		return "";
	}
	public String writeDataToPdf( ) {
		return text;
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
	
