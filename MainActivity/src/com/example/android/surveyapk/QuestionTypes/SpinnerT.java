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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
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
import com.example.android.surveyapk.directorychooser.DirectoryChooserDialog;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SpinnerT extends InputElement implements TextWatcher {
	Question question;
	public String defaultText;
	EditText textbox;
	TextBox textBox2; // Add !!
	public String defaultState;
	public String coef = null;
	public String name = null;
	public int stepSize = 1;
	public int position = 1;
	public String jamato;

	public String dMax;
	public String text;
	public int id;
	public int preAhshaw;
	public int stopSignal;

	// CheckBox checkBox;
	// public RadioButton radio;
	public String def = null;

	public SpinnerT(Question question) {
		super(question);
		this.question = question;
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY00 ");

	}

	@Override
	public View display(Activity context) {
		preAhshaw = CollectionDemoActivity.albertAhshaw;
		CollectionDemoActivity.albertJetzt = CollectionDemoActivity.albertAhshaw;

		CollectionDemoActivity.albertAhshaw++;

		Log.w("-spin", " spin text: " + text);
		Log.w("-spin", " spin def: " + def);
		Log.w("-spin", " spin coef: " + coef);
		Spinner spinn = new Spinner(context);
		// -- spinn.setBackgroundResource(R.drawable.spinner_border); //SUPER
		// oder
		// TextView spinnerText = (TextView) spinn.getChildAt(0);
		// btn3=(Button) context.findViewById(R.id.bt3);

		String[] arraySpinner = coef.split(",");

		// -smallTop-- ArrayAdapter<String> dataAdapter = new
		// ArrayAdapter<String> (context, android.R.layout.simple_spinner_item,
		// arraySpinner);
		// --OneRadio- ArrayAdapter<String> dataAdapter = new
		// ArrayAdapter<String> (context,
		// android.R.layout.simple_spinner_dropdown_item, arraySpinner);
		// --ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
		// (context, android.R.layout.simple_spinner_item, arraySpinner);
		// notw. ArrayAdapter<CharSequence> Visiting_adaptor =
		// ArrayAdapter.createFromResource(context, R.layout.spinner_item,
		// android.R.layout.simple_gallery_item);
		// noneed TextView spinnerText = (TextView) spinn.getChildAt(0);
		// ok ArrayAdapter<String> dataAdapter = new
		// ArrayAdapter<String>(context, R.layout.spinner_item, arraySpinner);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				R.layout.spinner_top, arraySpinner); // firstElem big
		// -- ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
		// (context, android.R.layout.simple_spinner_item, arraySpinner);
		// needed
		// dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dataAdapter.setDropDownViewResource(R.layout.spinner_item);
		spinn.setAdapter(dataAdapter);

		Log.w("-22RBG onClick:", " spin100- kluch0 Spinn -spin.name: " + name);
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY0 ");
		// G
		Log.w("-22RBG onClick:", " spin100- kluch0 Spinn YYYb -albertNow: "
				+ CollectionDemoActivity.albertNow);
		Log.w("-22RBG onClick:", " spin1 kluch1 Spinn YYYa -albertAhshaw: "
				+ CollectionDemoActivity.albertAhshaw);
		// G

		spinn.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.w("-22RBG onClick:",
						" spin100- kluch1 Spinn YYYd -albertNow: "
								+ CollectionDemoActivity.albertNow);
				Log.w("-22RBG onClick:",
						" spin1- kluch1 Spinn YYYe -albertJetzt: "
								+ CollectionDemoActivity.albertJetzt);
				Log.w("-22RBG onClick:",
						" spin100- kluch1 Spinn YYYe -preAhshaw: " + preAhshaw);
				Log.w("-22RBG onClick:",
						" spin1 kluch1 Spinn YYYc -albertAhshaw: "
								+ CollectionDemoActivity.albertAhshaw);
				Log.w("-22RBG onClick:",
						" spin1 kluch1 Spinn YYYc -stopSignal: "
								+ CollectionDemoActivity.stopSignal);
				Log.w("-22RBG onClick:",
						" spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "
								+ RadioButtonGroup.statBoxBuffer);

				Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY0.1 ");
				Log.w("-spin", " spin coef: " + id);
				Log.w("-spin", " spin coef: " + coef);
				// position = spinn.getSelectedItemPosition();
				Log.w("-22RBG onClick:", " spin100- kluch1 Spinn -spin.name: "
						+ name);
				Log.w("-22RBG onClick:",
						" spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "
								+ RadioButtonGroup.statBoxBuffer);

				// DUPONT
				// =========================================================================================================================
				if (CollectionDemoActivity.albertJetzt == preAhshaw
						|| CollectionDemoActivity.albertNow == CollectionDemoActivity.albertAhshaw) {
					CollectionDemoActivity.stopSignal = 1;
				}
				// xxxxx
				try {
					Log.w("-22RBG onClick:",
							"B2 RBG100- kluch1 333 -RadioButtonGroup.statBoxBuffer: "
									+ RadioButtonGroup.statBoxBuffer);
					if (CollectionDemoActivity.stopSignal == 1) {
						// verstaut equ string here:
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
				coef = parent.getSelectedItem().toString();

				if (!coef.matches("Auswählen")) {
					String[] parts = coef.split(":");
					String part1 = parts[0]; // 004
					String part2 = parts[1]; // 034556
					// val =coef;

					Log.w("-spinnT", " spin JAMATOtt coef:" + coef + "xx");
					Log.w("-spinnT", " spin JAMATOtt part1:" + part1 + "xx");
					Log.w("-spinnT", " spin JAMATOtt part2:" + part2 + "xx");
					Log.w("-spinT", " spin JAMATOtt  kluch44 333 -val:" + val
							+ "xx");
					val = part2;
					coef = part1;
				} else {
					val = "NULL";
					coef = "0";

				}

			} // end onItemSelected

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				// coef = "xx";
			}
		});

		return spinn;

	}

	// --------------------------------------------------------------------------------------------------------------------------------

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// String item =String.ValueOf(parent.getItemAtPosition(pos));
		// DUPONT
		// DUPONT
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY2 ");

		val = String.valueOf(progress);
		Log.w("SeekBar", "SeekBar Robo1: progress: " + progress);
		Log.w("SeekBar", "SeekBar Robo1: val: " + val);
		coef = val;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY3 ");

	}

	@Override
	public void afterTextChanged(Editable s) {
		val = textbox.getText().toString();
		System.out.println(val);
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY4 ");

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY5 ");

	}

	@Override
	public String writeData() {
		Log.w("SeekBar", "SeekBar Robo2WD: progress: this.val " + this.val);
		Log.w("SeekBar", "SeekBar Robo2WD: name " + name);
		Log.w("SeekBar", "SeekBar Robo2WD: coef " + coef);
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY6 ");

		String outBuf = "";
		outBuf += this.val;
		if (name != null) {
			// remooved if //
			question.evaluator.putVariable(name, coef);
			Log.w("Spinner",
					"5555 iSCORE: question.evaluator.putVariable: +name+coef: "
							+ name + coef);

		}
		return outBuf;
	}

	@Override
	public String writeDataToPdf() {
		Log.w("SeekBar", "SeekBar Robo3Pdf: name: " + name);
		Log.w("SeekBar", "SeekBar Robo3Pdf: coef: " + coef);
		Log.w("SeekBar", "SeekBar Robo3Pdf: val: " + this.val);
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY6 ");

		String outBuf = "";
		// if(Boolean.valueOf(val))
		outBuf += val;
		// CHANGED .............
		if (name != null && !coef.equals("")) {
			Log.w("Spinner",
					"444 iSCORE: question.evaluator.putVariable: +name: "
							+ name + " coef: " + coef);
			Log.w("SpinnerM", " iSCORE11: TAPORb3 PATROL name:" + name);
			Log.w("SpinnerM", " iSCORE11: TAPORb3 PATROL coef:" + coef);
			if (coef != null) {
				question.evaluator.putVariable(name, coef); // DAVIS
			}

		}

		MainActivity.alphaDef += ", `" + question.name + "-" + name + "` REAL";
		MainActivity.alphaName += ", " + question.name + "-" + name;
		MainActivity.alphaVal += "," + val + ""; // weg '
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

		return outBuf;
	}

	/*	*/
	@Override
	public int validate() {
		Log.w("-22RBG onClick:", "B2B RBG100- kluch1 YYY7 ");

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
