package com.example.android.surveyapk.QuestionTypes;

import android.R.*;
import android.net.Uri;
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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.CollectionDemoActivity;
import com.example.android.surveyapk.CustomViewPager;
import com.example.android.surveyapk.MainActivity;
import com.example.android.surveyapk.csv.openreadcsv;
import com.example.android.surveyapk.directorychooser.DirectoryChooserDialog;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;
import au.com.bytecode.opencsv.CSVWriter;

public class SliderM extends InputElement implements TextWatcher {
	Question question;
	public String defaultText;
	EditText textbox;
	TextBox textBox2; // Add !!
	CheckBox checkBox;
	public String defaultState;
	public String coef = null;
	public String name = null;
	public int stepSize = 1;
	public String dMax;
	public int preAhshaw;
	public int stopSignal;
	public String alpha = "";

	public SliderM(Question question) {
		super(question);
		this.question = question;

	}

	@Override
	public View display(Activity context) {
		// Toast.makeText(context, " textbox :", Toast.LENGTH_LONG).show();
		// return textbox;
		// ---------------------------------------------------------------------------------------

		// return checkBox;
		/*
		 * TextView texta = (TextView)
		 * context.findViewById(R.layout.list_content); TextView
		 * questionTextt=(TextView) rootView.findViewById(R.id.text1); Button
		 * btn=(Button) findViewById(R.id.demo_collection_button);
		 */

		SeekBar slider = new SeekBar(context);
		int foo = Integer.parseInt(coef);
		Log.w("SeekBar", "SeekBar Robo2: dMax: " + dMax);
		Log.w("SeekBar", "SeekBar Robo2: foo: " + foo);
		// ??? slider.setText(text);
		slider.setMax(foo);
		slider.setTag(this);

		Log.w("-22RBG onClick:", " spin100- kluch00sl Spinn YYYd -albertNow: "
				+ CollectionDemoActivity.albertNow);
		Log.w("-22RBG onClick:", " spin1- kluch00sl Spinn YYYe -albertJetzt: "
				+ CollectionDemoActivity.albertJetzt);
		Log.w("-22RBG onClick:", " spin100- kluch00sl Spinn YYYe -preAhshaw: "
				+ preAhshaw);
		Log.w("-22RBG onClick:", " spin1 kluch00sl Spinn YYYc -albertAhshaw: "
				+ CollectionDemoActivity.albertAhshaw);
		Log.w("-22RBG onClick:", " spin1 kluch00sl Spinn YYYc -stopSignal: "
				+ CollectionDemoActivity.stopSignal);
		Log.w("-22RBG onClick:",
				" spin100- kluch00sl Spinn -RadioButtonGroup.statBoxBuffer: "
						+ RadioButtonGroup.statBoxBuffer);

		slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.w("-22RBG onClick:",
						" spin100- kluch1sl Spinn YYYd -albertNow: "
								+ CollectionDemoActivity.albertNow);
				Log.w("-22RBG onClick:",
						" spin1- kluch1sl Spinn YYYe -albertJetzt: "
								+ CollectionDemoActivity.albertJetzt);
				Log.w("-22RBG onClick:",
						" spin100- kluch1sl Spinn YYYe -preAhshaw: "
								+ preAhshaw);
				Log.w("-22RBG onClick:",
						" spin1 kluch1sl Spinn YYYc -albertAhshaw: "
								+ CollectionDemoActivity.albertAhshaw);
				Log.w("-22RBG onClick:",
						" spin1 kluch1sl Spinn YYYc -stopSignal: "
								+ CollectionDemoActivity.stopSignal);
				Log.w("-22RBG onClick:",
						" spin100- kluch1sl Spinn -RadioButtonGroup.statBoxBuffer: "
								+ RadioButtonGroup.statBoxBuffer);

				progress = ((int) Math.round(progress / stepSize)) * stepSize;
				seekBar.setProgress(progress);
				val = String.valueOf(progress);
				Log.w("SeekBar", "SeekBar Robo: progress: " + progress);
				coef = val; //
				coef = val; //
				// DUPONT
				// =========================================================================================================================
				try {
					Log.w("-22RBG onClick:",
							"B2 RBG100- kluch1 333 -RadioButtonGroup.statBoxBuffer: "
									+ RadioButtonGroup.statBoxBuffer);
					if (progress > 0) {
						// versaut equ string here:
						RadioButtonGroup.statBoxBuffer = RadioButtonGroup.statBoxBuffer
								.replaceAll(name, "KK");
						Log.w("SHLUSSEL",
								"*** RBG100- kluch2 333 -RadioButtonGroup.statBoxBuffer: "
										+ RadioButtonGroup.statBoxBuffer);
					}
				} catch (Exception e) {
					Log.w("#MATCH", " RBG100- CATCH spinn.name" + name);
				}
				// DUPONT ====================================================
				// +++++++
				if (CollectionDemoActivity.albertNameNow.matches("mq.*")
						&& !RadioButtonGroup.statBoxBuffer.matches(".*yy.*")) {
					CustomViewPager.enabled = true; // $$$$$
					Log.w("CVP ", "CVP $$$$$HH11   1001  TRUE");
					Log.w("27POINT- RBG-onClick ",
							"*RBG100 @333 albertNameNow: "
									+ CollectionDemoActivity.albertNameNow);
					Log.w("28POINT- RBG-onClick ",
							"*RBG100 @333 KLUCH statBoxBuffer: "
									+ RadioButtonGroup.statBoxBuffer);
					Log.w("29POINT- RBG-onClick", "RBG100 @333 enabled ?: "
							+ CustomViewPager.enabled);
				}// end_if || ODER
				// ++++++++
				if (CollectionDemoActivity.albertNameNow.matches("q.*")
						&& CollectionDemoActivity.dofRelease == 0) {
					CustomViewPager.enabled = false; // $$$$$
					Log.w("CVP ", "CVP $$$$$HH13   1001  FALSE ");
					Log.w("04Radio.D inputElement: ",
							"Radio.D1000 333 kluch FALSE CustomViewPager.enabled: "
									+ CustomViewPager.enabled + "###");
				}// end_if
					// ++++++++
				if (CollectionDemoActivity.albertNameNow.matches("t.*")
						&& CollectionDemoActivity.dofRelease == 0) {
					CustomViewPager.enabled = true; // $$$$$
					Log.w("CVP ", "CVP $$$$$HH14   1001  TRUE");
					Log.w("05Radio.D inputElement: ",
							"Radio.D1000 333 kluch TRUE CustomViewPager.enabled: "
									+ CustomViewPager.enabled + "###");
				}// end_if
					// DUPONT
					// ===========================================================================================================================

			}
		});
		if (val != null) {
			slider.setProgress(Integer.valueOf(val));
			// ((Boolean.valueOf(val)));
		}

		if (foo == 4) {
			slider.setBackgroundResource(R.drawable.sk4);
		}
		if (foo == 5) {
			slider.setBackgroundResource(R.drawable.sk5);
		}
		if (foo == 6) {
			slider.setBackgroundResource(R.drawable.sk6);
		}
		if (foo == 7) {
			slider.setBackgroundResource(R.drawable.sk7);
		}
		if (foo == 8) {
			slider.setBackgroundResource(R.drawable.sk8);
		}
		if (foo == 9) {
			slider.setBackgroundResource(R.drawable.sk9);
		}
		if (foo == 10) {
			slider.setBackgroundResource(R.drawable.sk10);
		}
		if (foo == 12) {
			slider.setBackgroundResource(R.drawable.sk12);
		}
		if (foo == 20) {
			slider.setBackgroundResource(R.drawable.sk20);
		}

		slider.setPadding(3, 5, 3, 20);
		return slider;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		val = String.valueOf(progress);
		Log.w("SeekBar", "SeekBar Robo1: progress: " + progress);
		Log.w("SeekBar", "SeekBar Robo1: val: " + val);
		coef = val;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		val = textbox.getText().toString();
		System.out.println(val);

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public String writeData() {
		Log.w("SeekBar", "SeekBar Robo2WD: progress: this.val " + this.val);
		Log.w("SeekBar", "SeekBar Robo2WD: name " + name);
		Log.w("SeekBar", "SeekBar Robo2WD: coef " + coef);

		Log.w("SeekBar", "SeekBar Robo3Pdf: gras4 name: " + name);
		Log.w("SeekBar", "SeekBar Robo3Pdf: gras4 coef: " + coef);
		Log.w("SeekBar", "SeekBar Robo3Pdf: gras4 val: " + val);

		String outBuf = "";
		outBuf += this.val;
		if (name != null) {
			// remooved if //
			question.evaluator.putVariable(name, coef);
		}
		Log.w("SeekBar", "SeekBar Robo3Pdf: gras44 outBuf: " + outBuf);

		return outBuf;
	}

	@Override
	public String writeDataToPdf() {

		// CSVWriter writer53 = new CSVWriter(new FileWriter(sv_historyFile,
		// true));
		// String [] record53 = stdNames.split(";");
		// writer53.writeNext(record53);
		// writer53.close();

		MainActivity.alphaDef += ", `" + question.name + "-" + name + "` REAL";
		MainActivity.alphaName += ", " + question.name + "-" + name;
		MainActivity.alphaVal += "," + val + ""; // weg'
		Log.w("SeekBar", " Robo3Pdf: gras34 MainActivity.alphaName: "
				+ MainActivity.alphaName);
		Log.w("SeekBar", " Robo3Pdf: gras35 MainActivity.alphaVal: "
				+ MainActivity.alphaVal);
		Log.w("SeekBar", " Robo3Pdf: gras36 MainActivity.alphaDef: "
				+ MainActivity.alphaDef);
		Log.w("ANGARA", "  ANGARA2 MainActivity.alphaName: "
				+ MainActivity.alphaName);
		Log.w("ANGARA", "  ANGARA2 MainActivity.alphaVal: "
				+ MainActivity.alphaVal);

		String outBuf = "";
		// if(Boolean.valueOf(val))
		outBuf += val;
		// CHANGED .............
		if (name != null) {
			question.evaluator.putVariable(name, coef);
			Log.w("SliderM", " iSCORE11: TAPORb4 PATROL name:" + name);
			Log.w("SliderM", " iSCORE11: TAPORb4 PATROL coef:" + coef);
		}

		return outBuf;
	}

	/*	*/
	@Override
	public int validate() {
		// Log.w("Radio boolean validate()77:",
		// "this.isValidCount:"+this.isValidCount);
		// return this.isValidCount;
		int tst = 1;
		return tst;

	}

	@Override
	public int validate(String albertRadioTest) { // ToDo
		int isValidNr = 1;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: " + albertRadioTest);
		// return this.isValidCount;
		// return isValidCount;
		return isValidNr;

	}

	@Override
	public String validate(String albertRadioTest, String albertRadioName,
			Activity context) {
		// this.isGroupValidated ???
		// ------------------------------------------------------------------------------------------------------------------------------
		Toast myToast = Toast.makeText(context, "007 albertRadioTest:"
				+ albertRadioTest, Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.RIGHT, 0, 0);
		myToast.show();
		// =-----------------------------------------
		String stringBox = albertRadioTest;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: " + albertRadioTest);
		return stringBox;

	}
}
