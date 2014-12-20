package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
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
import android.widget.Toast;
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
		for (Radio myradio : group) {
			myradio.radio.setChecked(false);
			myradio.val="false";
		}
		
		radio.setChecked(true);
		radioCont.val="true";
	
		
	}
	
	

}
