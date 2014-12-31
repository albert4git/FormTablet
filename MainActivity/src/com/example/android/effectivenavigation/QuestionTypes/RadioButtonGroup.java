package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.res.Resources;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class RadioButtonGroup implements OnClickListener 
{
	List <ArrayList<Radio>> groups;
	List <InputElement> childs;
	private static RadioButtonGroup ref;
	public static int statBoxG = 37; //TEST 
	public static String statBoxName = "aaa"; //TEST
	public static String statBoxText = "aaa"; //TEST
	public static String statBoxCoef = "aaa"; //TEST
	public static String statBoxS3 = "aaa"; //TEST
	public static String statBoxS4 = "aaa"; //TEST



	
	public RadioButtonGroup() {
		Log.w("__399__ RBG constructor:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
	    groups=new ArrayList<ArrayList<Radio>>();
	}
	

	public void addToGroup(Radio radio)
	{
		int g=radio.group;
		Log.w("__377__ RBG addToGroup:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
		
		if(groups.size()<g) 
		{
			for (int i = 0; i<g; i++) {
				groups.add(new ArrayList<Radio>());

			}
		
		}
		groups.get(g-1).add(radio);
		
	}


	@Override
	public void onClick(View v) {
		Radio radioCont;
		RadioButton radio;
		radio= (RadioButton) v;
		radioCont=(Radio) v.getTag();
		List<Radio> group=groups.get(radioCont.group-1);
        Log.w("-321 RBG onClick:", "1. radioCont.val:"+radioCont.val);
        int j=0;
		for (Radio myradio : group) {
	        Log.w("--2 RBG onClick:", "2A. myradio.isGroupValidated:"+myradio.isGroupValidated+" j:"+j);
	        Log.w("-23 RBG onClick:", "2. myradio.radio.getId:"+myradio.radio.getId()+" j:"+j);
			myradio.isGroupValidated=true;   // WoZu
			myradio.radio.setChecked(false); // WoZu
			myradio.val="false";             // WoZu
	        Log.w("-24 RBG onClick:", "2B. myradio.isGroupValidated:"+myradio.isGroupValidated+" j:"+j);
			
			j++;
		}
        Log.w("--- RBG onClick:", "4. radioCont.val:"+radioCont.val);
		//----------------------------XXX------------------------------------------		
		radio.setChecked(true); //WoZu
		radioCont.val="true";
		Log.w("-325 RBG onClick:", "radioCont.val:"+radioCont.val);
		Log.w("-326 RBG onClick:", "radioCont.id:"+radioCont.id);
		Log.w("-327 RBG onClick:", "radioCont.name:"+radioCont.name);
		Log.w("-328 RBG onClick:", "radioCont.text:"+radioCont.text);
		Log.w("-329 RBG onClick:", "radioCont.group:"+radioCont.group);
		Log.w("-330 RBG onClick:", "radioCont.coef:"+radioCont.coef);
		Log.w("-331 RBG onClick:", "radioCont.isGroupValidated:"+radioCont.isGroupValidated);
		Log.w("-332 RBG onClick:", "-?- radio.getId:"+radio.getId());
		//----------------------------XXX-------------------------------------------
	
		// validate(String albertRadioTest,String albertRadioName, Activity context );
		//=======================================================
		RadioButtonGroup.statBoxName = radioCont.name ; // Kutusov ?
		RadioButtonGroup.statBoxText = radioCont.text ; // Kutusov ?
		RadioButtonGroup.statBoxG = radioCont.group ;   // Kutusov ?
		RadioButtonGroup.statBoxCoef = radioCont.coef ; // Kutusov ?

		Log.w("__333__ RBG onClick:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
		Log.w("--=======--", "--===================--");

	}








}
