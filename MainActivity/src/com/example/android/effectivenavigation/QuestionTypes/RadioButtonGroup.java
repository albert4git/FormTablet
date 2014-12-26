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
	public RadioButtonGroup() {
	groups=new ArrayList<ArrayList<Radio>>();
	}
	

	public void addToGroup(Radio radio)
	{
		int g=radio.group;
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
	
        Log.w("RBG onClick:", "1. radioCont.val:"+radioCont.val);
        int j=0;
		for (Radio myradio : group) {
	        Log.w("RBG onClick:", "2. myradio.isGroupValidated:"+myradio.isGroupValidated+j);
	        Log.w("RBG onClick:", "3. myradio.radio.getId:"+myradio.radio.getId()+j);

			myradio.isGroupValidated=true;
			myradio.radio.setChecked(false);
			myradio.val="false";
			j++;
		}
        Log.w("RBG onClick:", "4. radioCont.val:"+radioCont.val);

		
		radio.setChecked(true);
		radioCont.val="true";
		//----------------------------XXX-------------------------------------------
		Log.w("RBG onClick:", "5. radio.getId:"+radio.getId());
		Log.w("RBG onClick:", "-------XXX--------");
		Log.w("RBG onClick:", "6. radioCont.val:"+radioCont.val);
		Log.w("RBG onClick:", "7. radioCont.id:"+radioCont.id);
		Log.w("RBG onClick:", "8. radioCont.name:"+radioCont.name);
		Log.w("RBG onClick:", "9. radioCont.text:"+radioCont.text);
		Log.w("RBG onClick:", "10. radioCont.group:"+radioCont.group);
		Log.w("RBG onClick:", "11. radioCont.coef:"+radioCont.coef);
		Log.w("RBG onClick:", "12. radioCont.isGroupValidated:"+radioCont.isGroupValidated);


		
	}








}
