package com.example.android.surveyapk.QuestionTypes;

import android.R.*;

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
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
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
import android.view.LayoutInflater;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import java.io.BufferedInputStream;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.CollectionDemoActivity;
import com.example.android.surveyapk.CustomViewPager;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class RadioButtonGroup implements OnClickListener {
	List<ArrayList<Radio>> groups;
	List<InputElement> childs;
	private static RadioButtonGroup ref;
	public static int statBoxG = 37; // TEST
	public static String statBoxName = "aaa"; // TEST
	public static String statBoxText = "aaa"; // TEST
	public static String statBoxCoef = "aaa"; // TEST
	public static boolean statBoxIGV = false; // TEST
	public static int statBoxFlag = 0; // TEST
	public static String statBoxSFlag = "aaa"; // TEST
	public static boolean statBoxStartWith = false; // TEST
	public static boolean statBoxStartHasA = false; // TEST
	public static boolean statBoxTFlag = false; // TEST
	public static String statBoxBuffer = "QQ"; // TEST
	public static String q_IMG = "QP"; // TEST
	public static int q_IMG_Flag = 1; // TEST

	public RadioButtonGroup() {
		Log.w("@BBB BBB BBB BBB", "BBB BBB BBB BBB");

		Log.w("__399__ RBG constructor:", "RadioButtonGroup.statBoxName:"
				+ RadioButtonGroup.statBoxName);
		groups = new ArrayList<ArrayList<Radio>>();
	}

	public void addToGroup(Radio radio) {
		int g = radio.group;
		Log.w("__377__ RBG addToGroup:", "RadioButtonGroup.statBoxName:"
				+ RadioButtonGroup.statBoxName);
		Log.w("@AAA1 AAA AAA  AAA", "AAA AAA AAA  AAA");

		if (groups.size() < g) {
			Log.w("@AAA2 AAA AAA  AAA", "AAA AAA AAA  AAA");

			for (int i = 0; i < g; i++) {
				groups.add(new ArrayList<Radio>());

			}

		}
		groups.get(g - 1).add(radio);

	}

	@Override
	public void onClick(View v) {
		Radio radioCont;
		RadioButton radio;
		radio = (RadioButton) v;
		radioCont = (Radio) v.getTag();
		List<Radio> group = groups.get(radioCont.group - 1);
		Log.w("-PPP PPP  PPP RAMKA", "FIN PPP PPP PPP PPP");
		Log.w("-01 RBG-onClick:", "1. radioCont.val:" + radioCont.val);
		Log.w("def", "DEF2: " + radioCont.def);

		// /************************************************
		/*
		 * TextView tV = new TextView(v.getContext());
		 * tV.setBackgroundColor(70); Toast toast =
		 * Toast.makeText(v.getContext(),"default is:"+radioCont.def ,
		 * Toast.LENGTH_LONG); toast.setGravity(Gravity.CENTER , 0, 0);
		 * //toast.setDuration(300); toast.setDuration(Toast.LENGTH_LONG);
		 * //toast.show(); ///************************************************
		 * final Dialog dialog = new Dialog(v.getContext());
		 * //dialog.setContentView(R.layout.custom);
		 * dialog.setTitle("Title..."); Button dialogButton = new
		 * Button(v.getContext()); // if button is clicked, close the custom
		 * dialog dialogButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { dialog.dismiss(); } });
		 * dialog.show();
		 */
		// --- ---- ---- ---- ---- ---- --- --- --- --- --
		/*
		 * Toast.makeText(v.getContext(),
		 * " Sie können ihre Eingaben überprüfen korrigieren :"+radioCont.def,
		 * Toast.LENGTH_LONG).show(); Toast.makeText(context, "Survey saved",
		 * Toast.LENGTH_LONG).show(); Toast.makeText(this,
		 * " mViewPager.setCurrentItem: dofID "+radioCont.def,
		 * Toast.LENGTH_LONG).show(); Toast.makeText(MainActivity.this,
		 * "This is chosen directory: ", Toast.LENGTH_LONG).show();
		 * Toast.makeText(getBaseContext(),
		 * " Sie können ihre Eingaben überprüfen korrigieren :",
		 * Toast.LENGTH_LONG).show();
		 */
		// --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
		int j = 0; // WoZu das ganze ???
		for (Radio myradio : group) {
			Log.w("-308 RBG-onClick:", "2A. myradio.radio.getId:"
					+ myradio.radio.getId() + " j: " + j);
			myradio.isGroupValidated = true; // WoZu kann ich das nuzen ?
			myradio.radio.setChecked(false); // Wichtig for Sigle Radio Select
												// !!
			myradio.val = "false"; // WoZu
			Log.w("-309 RBG-onClick", "2B. myradio.isGroupValidated:"
					+ myradio.isGroupValidated + " j: " + j);
			j++;
		}// END_for

		Log.w("02- RBG onClick:", "4. radioCont.val:" + radioCont.val);
		// ----------------------------XXX------------------------------------------
		radio.setChecked(true); // WoZu
		radioCont.val = "true"; // WoZu

		// CollectionDemoActivity.albertEquationNow ;
		// CollectionDemoActivity.albertNameNow ;
		// CollectionDemoActivity.albertContentNow ;
		// CollectionDemoActivity.albertEquationNext ;
		// CollectionDemoActivity.albertNameNext ;
		// CollectionDemoActivity.albertContentNext ;

		Log.w(" 03RBG TRIO #########", "TRIO RBG100 ################### ");
		Log.w(" 04RBG Pre #########", "Pre RBG100 ################### ");
		Log.w(" 05RBG onClick:", "*RBG100-Pre albertNamePre: "
				+ CollectionDemoActivity.albertNamePre);
		Log.w(" 06RBG onClick:", "*RBG100-Pre albertEquationPre: "
				+ CollectionDemoActivity.albertEquationPre);
		Log.w(" 07RBG onClick:", "*RBG100-Pre albertContentPre: "
				+ CollectionDemoActivity.albertContentPre);

		Log.w(" 08RBG NOW #########", "NOW RBG100 ################### ");
		Log.w(" 09RBG onClick:", "*RBG100-Now albertNameNow: "
				+ CollectionDemoActivity.albertNameNow);
		Log.w(" 10RBG onClick:", "*RBG100-Now albertEquationNow: "
				+ CollectionDemoActivity.albertEquationNow);
		Log.w(" 11RBG onClick:", "*RBG100-Now albertContentNow: "
				+ CollectionDemoActivity.albertContentNow);
		Log.w(" 12RBG onClick:", "*RBG100-Now albertIdNow: "
				+ CollectionDemoActivity.albertIdNow);

		Log.w(" 13RBG Nxt #########", "Nxt RBG100 ################### ");
		Log.w(" 14RBG onClick:", "*RBG100-Nxt albertNameNext: "
				+ CollectionDemoActivity.albertNameNext);
		Log.w(" 15RBG onClick:", "*RBG100-Nxt albertEquationNext: "
				+ CollectionDemoActivity.albertEquationNext);
		Log.w(" 16RBG onClick:", "*RBG100-Nxt albertContentNext: "
				+ CollectionDemoActivity.albertContentNext);
		Log.w(" 17RBG onClick:", "*RBG100-Nxt albertIdNext: "
				+ CollectionDemoActivity.albertIdNext);

		Log.w("-18RBG onClick:", "radioCont.isGroupValidated: "
				+ radioCont.isGroupValidated);
		Log.w("-19RBG onClick:", "statBoxName: " + RadioButtonGroup.statBoxName);
		Log.w("-20RBG onClick:", "radioCont.val: " + radioCont.val);
		Log.w("-21RBG onClick:", "radioCont.id: " + radioCont.id);
		Log.w("-22RBG onClick:", "radioCont.name: " + radioCont.name);
		Log.w("-23RBG onClick:", "radioCont.text: " + radioCont.text);
		Log.w("-24RBG onClick:", "radioCont.group: " + radioCont.group);
		Log.w("-25RBG onClick:", "radioCont.coef: " + radioCont.coef);
		// Log.w("-316 RBG onClick:", "radioCont.row: "+radioCont.row);
		// Log.w("-317 RBG onClick:",
		// "radioCont.question: "+radioCont.question);
		// Log.w("-319 RBG onClick:", "-?- radio.getId: "+radio.getId());
		Log.w(" RBG  #########", " RBG100 ################### ");

		// ----------------------------XXX-------------------------------------------
		RadioButtonGroup.statBoxName = radioCont.name; // Cut ?
		RadioButtonGroup.statBoxText = radioCont.text; // Cut ?
		RadioButtonGroup.statBoxG = radioCont.group; // Cut ?
		RadioButtonGroup.statBoxCoef = radioCont.coef; // Cut ?
		RadioButtonGroup.statBoxIGV = radioCont.isGroupValidated; // Cut ?
		// --------------------------------------------------------------------------------
		Log.w(" RBG  #########", "B RBG100 ################### ");
		// ---POINT---
		Log.w("-22RBG onClick:", "YY kluch1 !!!-radioCont.name: "
				+ radioCont.name);
		if (radioCont.name.length() == 4 && radioCont.name.contains("yy")) {
			// radioCont.name = radioCont.name.substring(0,3); // yy10
			Log.w("-22RBG onClick:", "YY kluch1-444 !!!-radioCont.name: "
					+ radioCont.name);
		}

		// DUPONT //
		try {

			Log.w("-22RBG onClick:",
					"B2 RBG100- kluch1 333 -RadioButtonGroup.statBoxBuffer: "
							+ RadioButtonGroup.statBoxBuffer);
			RadioButtonGroup.statBoxBuffer = RadioButtonGroup.statBoxBuffer
					.replaceAll(radioCont.name, "KK");
			Log.w("-22RBG onClick:",
					"B2 RBG100- kluch12 333 -RadioButtonGroup.statBoxBuffer: "
							+ RadioButtonGroup.statBoxBuffer);
		} catch (Exception e) {
			Log.w("#MATCH", " RBG100- CATCH radioCont.name" + radioCont.name);
			// Log.wtf("DO THIS", " WHEN SAVE() FAILS");
		}
		// DUPONT //

		Log.w("-22RBG onClick:",
				"B2 RBG100- kluch1 DUPONT -RadioButtonGroup.statBoxBuffer: "
						+ RadioButtonGroup.statBoxBuffer);

		try {
			Log.w("CVP ", "CVP $$$$$HH12   1001  DDD");

			if (CollectionDemoActivity.albertNameNow.matches("mq.*")
					&& !RadioButtonGroup.statBoxBuffer.matches(".*yy.*")) {
				CustomViewPager.enabled = true; // $$$$$
				Log.w("CVP ", "CVP $$$$$HH11   1001  TRUE");
				Log.w("27POINT- RBG-onClick ", "*RBG100 @333 albertNameNow: "
						+ CollectionDemoActivity.albertNameNow);
				Log.w("28POINT- RBG-onClick ",
						"*RBG100 @333 KLUCH statBoxBuffer: "
								+ RadioButtonGroup.statBoxBuffer);
				Log.w("29POINT- RBG-onClick", "RBG100 @333 enabled ?: "
						+ CustomViewPager.enabled);
			}// end_if || ODER
			if (CollectionDemoActivity.albertNameNow.matches("q.*")) {
				CustomViewPager.enabled = true; // $$$$$
				Log.w("CVP ", "CVP $$$$$HH10 qqq  1001  TRUE");
				Log.w("30POINT- RBG-onClick",
						"*RBG100 @333 FALSE CustomViewPager.enabled: "
								+ CustomViewPager.enabled + "###");
			}// end_if

			if (CollectionDemoActivity.albertNameNow.matches("t.*")) {
				CustomViewPager.enabled = true; // $$$$$
				Log.w("CVP ", "CVP $$$$$HH9   1001  TRUE");
				Log.w("31POINT- RBG-onClick",
						"*RBG100 @333 TRUE CustomViewPager.enabled: "
								+ CustomViewPager.enabled + "###");
			}// end_if
		} catch (Exception e) {
			Log.w("#MATCH", " MATCHE FAILS");
			// Log.wtf("DO THIS", " WHEN SAVE() FAILS");

		}
		Log.w("CVP ", "CVP $$$$$HH91   1001  DDD");
		Log.w("32POINT- RBG-onClick", "*RBG100 @333 enabled ?: "
				+ CustomViewPager.enabled);
		Log.w("33RRR RRR  RRR RAMKA", "*FIN RRR RRR RRR RRR");

	}

}
