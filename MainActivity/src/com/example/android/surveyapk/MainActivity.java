package com.example.android.surveyapk;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Text;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.QuestionTypes.Mydata;
import com.example.android.surveyapk.asignature.SignatureView;
import com.example.android.surveyapk.directorychooser.DirectoryChooserDialog;
import com.example.android.surveyapk.directorychooser.pdf;
import android.R.layout;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int REQUEST_CODE = 6384; // onActivityResult request
													// code
	// ------------------------------------------
	public Spinner spinGender;
	public Spinner spinStudyIs;
	public Spinner spinSubjectIs;
	public Spinner spinTreatmentIs;
	public Spinner spinAgainIs;
	public Spinner spinAgeIs;
	public Spinner spinGroupIs;
	public Spinner spinPharmaIs;
	public Spinner spinDoseIs;
	public Spinner spinDiagnoseIs;

	// ------------------------------------------
	public static String alphaDef = "";
	public static String alphaIn = "";
	public static String alphaName = "";
	public static String alphaVal = "";

	public static String scoreDef = "";
	public static String scoreIn = "";
	public static String scoreName = "";
	public static String scoreVal = "";

	public static String betaDef = "";
	public static String betaIn = "";
	public static String betaName = "";
	public static String betaVal = "";

	public static String topEquation = "null";
	public static String topEquation2 = "null";
	public static String topEquation3 = "null";
	public static String topEquation4 = "null";
	public static String topEquation5 = "null";
	public static String topEquation6 = "null";
	public static String topEquation7 = "null";
	public static String topEquation8 = "null";
	public static String topEquation9 = "null";

	public static Long theID;
	public static String sname;
	public static String surveyName;

	public static String strGenderIs;
	public static String strGroupIs;
	public static String strStudyIs;
	public static String strSubjectIs;
	public static String strTreatmentIs;
	public static String strAgainIs;
	public static String strAgeIs;
	public static int intAgeIs;
	public static String strDiagnoseIs;
	// ------------------------------------------
	private EditText subjectName; // flushes
	private EditText subjectDign; // flushes
	public EditText edCommentIs;
	// ------------------------------------------
	public static String strName;
	public static String strDign;
	public static String strCommentIs;
	public static String strPharmaIs;
	public static String strDoseIs;
	// ------------------------------------------
	// ------------------------------------------
	private String textMann2frau;
	// -- braucht man suX ?? ---
	// private static String suNamen;
	// private static String suDign;
	private String m_chosenDir = "";

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// Creation theory !!!
		// setContentView(R.layout.activity_collection_demo);
		setContentView(R.layout.fragment_section_launchpad);
		LinearLayout l = (LinearLayout) findViewById(R.id.mainlayout);
		l.setOrientation(LinearLayout.VERTICAL);

		l.setBackgroundColor(Color.BLUE); // DKGRAY
		l.setBackgroundResource(R.drawable.border2); // SUPER oder
		// -----------------------------------------------------------------
		TextView label = new TextView(this);
		Evaluator evaluator = new Evaluator();
		String result = null;
		evaluator.putVariable("a", "2");
		try {

			result = evaluator.evaluate("#{a}*5");
			Log.w("MainAct", "--- iSCORE-A5 !evaluator.evaluate! result: "
					+ result);

		} catch (EvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setText(result);
		// l.addView(label);
		// -----check---------------------------------------------------------
		subjectName = (EditText) findViewById(R.id.name); // flushes hier ?
		// subjectDign = (EditText) findViewById(R.id.dign); // Nr1
		edCommentIs = (EditText) findViewById(R.id.commentIs); // flusheshier ?

		Button btn = (Button) findViewById(R.id.demo_collection_button);
		btn.setOnClickListener(new View.OnClickListener() {
			private boolean m_newFolderEnabled = true;

			@Override
			public void onClick(View v) { // RED button on click ??
				Log.w("CVP setOnSwipeOutListener",
						"CVP HoerZu9 onSwipeOut 1001 CustomViewPager DDD");
				// v.setBackgroundColor(Color.GRAY); // GRAY GREEN

				Calendar now = Calendar.getInstance();
				int iYear = now.get(Calendar.YEAR);
				// ---== if-Test-VAL ==---
				if (subjectName.getText().toString().contentEquals("")) {
					// Toast.makeText(MainActivity.this,
					// "Bitte geben Sie einen vollständigen Namen !",
					// Toast.LENGTH_LONG).show();
					Toast toast = Toast.makeText(
							v.getContext(),
							"Bitte geben Sie einen vollständigen Namen (anonymisiert) ! ",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.TOP, 0, 0);
					toast.getView().setBackgroundColor(Color.RED);
					toast.getView().setPadding(10, 10, 10, 10);
					TextView text = (TextView) toast.getView().findViewById(
							android.R.id.message);
					text.setTextColor(Color.WHITE);
					text.setTextSize(24);
					toast.show();
					return;
				}
				// Create DirectoryChooserDialog and register a callback
				DirectoryChooserDialog directoryChooserDialog = new DirectoryChooserDialog(
						MainActivity.this,
						new DirectoryChooserDialog.ChosenDirectoryListener() {
							@Override
							public void onChosenDir(String chosenDir) {
								m_chosenDir = chosenDir;
								// Toast.makeText(MainActivity.this,
								// "This is chosen directory: " + chosenDir,
								// Toast.LENGTH_LONG).show();
								Intent intent = new Intent(MainActivity.this,
										CollectionDemoActivity.class);
								Mydata data1 = new Mydata();
								data1.file = chosenDir;
								data1.name = subjectName.getText().toString();
								// BORIS' dada1 ### START ### START ### START
								// ### START
								spinGender = (Spinner) findViewById(R.id.genderIs); // genderIs;
								spinStudyIs = (Spinner) findViewById(R.id.studyIs); //
								spinGroupIs = (Spinner) findViewById(R.id.groupIs); //
								spinSubjectIs = (Spinner) findViewById(R.id.subjectIs); // subjectIs;
								spinTreatmentIs = (Spinner) findViewById(R.id.treatmentIs);// treatmentIs
								spinAgainIs = (Spinner) findViewById(R.id.againIs); // againIs;
								spinAgeIs = (Spinner) findViewById(R.id.ageIs); // againIs;
								spinPharmaIs = (Spinner) findViewById(R.id.pharmaIs); // againIs;
								spinDoseIs = (Spinner) findViewById(R.id.doseIs); // againIs;
								spinDiagnoseIs = (Spinner) findViewById(R.id.diagnoseIs); //
								// data1.dign=subjectDign.getText().toString();
								// //## Nr1
								// *************************************************************
								// spin = (Spinner)
								// findViewById(R.id.commentIs); //commentIs;
								// ----- --- --- --- --- --- --- -- -- -- -- --
								// -- --
								strCommentIs = edCommentIs.getText().toString();
								strName = subjectName.getText().toString();
								// strDign = subjectDign.getText().toString();
								// // Nr1
								strDign = spinDiagnoseIs.getSelectedItem()
										.toString(); // ## Nr2
								data1.dign = spinDiagnoseIs.getSelectedItem()
										.toString(); // ## Nr2

								// ####
								strDoseIs = spinDoseIs.getSelectedItem()
										.toString();
								strGenderIs = spinGender.getSelectedItem()
										.toString();
								strStudyIs = spinStudyIs.getSelectedItem()
										.toString();
								strGroupIs = spinGroupIs.getSelectedItem()
										.toString();
								strPharmaIs = spinPharmaIs.getSelectedItem()
										.toString();
								// #
								// ##
								// #####
								strDiagnoseIs = spinDiagnoseIs
										.getSelectedItem().toString();
								// #####
								// ##
								// #
								strSubjectIs = spinSubjectIs.getSelectedItem()
										.toString();
								strTreatmentIs = spinTreatmentIs
										.getSelectedItem().toString();
								strAgainIs = spinAgainIs.getSelectedItem()
										.toString();
								strAgainIs = spinAgainIs.getSelectedItem()
										.toString();
								strAgeIs = spinAgeIs.getSelectedItem()
										.toString();
								intAgeIs = Integer.parseInt(strAgeIs);

								Log.w("Seide", "Dil gen:strGenderI "
										+ strGenderIs);
								Log.w("Seide", "Dil gen:strStudyIs "
										+ strStudyIs);
								Log.w("Seide", "Dil gen:strSubject "
										+ strSubjectIs);
								Log.w("Seide", "Dil gen:strTreatme "
										+ strTreatmentIs);
								Log.w("Seide", "Dil gen:strAgainIs "
										+ strAgainIs);
								Log.w("Seide", "Dil gen:strComment "
										+ strCommentIs);
								Log.w("Seide", "Dil gen:strAgeIs:" + strAgeIs
										+ "XX");
								Log.w("Seide", "Dil gen:intAgeIs:" + intAgeIs
										+ "XX");

								Log.w("Seide", "DilY gen:strName:" + strName
										+ " YY");
								Log.w("Seide", "DilY gen:strDign:" + strDign
										+ " YY");
								Log.w("Seide", "DilY gen:strPharmaIs:"
										+ strPharmaIs + " YY");
								Log.w("Seide", "DilY gen:strDoseIs:"
										+ strDoseIs + " YY");

								Log.w("ANGARA",
										"  ANGARA0 MainActivity.alphaName: "
												+ MainActivity.alphaName);
								Log.w("ANGARA",
										"  ANGARA0 MainActivity.alphaVal: "
												+ MainActivity.alphaVal);
								// ----- --- --- --- --- --- --- -- -- -- -- --
								// -- --
								intent.putExtra("Mydata", data1);
								startActivity(intent);
							}
						});
				// Toggle new folder button enabling
				directoryChooserDialog.setNewFolderEnabled(m_newFolderEnabled);
				// Load directory chooser dialog for initial 'm_chosenDir'
				// directory.
				// The registered callback will be called upon final directory
				// selection.
				directoryChooserDialog.chooseDirectory(m_chosenDir);
				m_newFolderEnabled = !m_newFolderEnabled;
			}
		});
		Button btn1 = (Button) findViewById(R.id.reset_button);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nameIs = (EditText) findViewById(R.id.name);
				nameIs.setText("");

				Spinner sp1 = (Spinner) findViewById(R.id.diagnoseIs);
				sp1.setSelection(0);
				Spinner sp2 = (Spinner) findViewById(R.id.genderIs);
				sp2.setSelection(0);// genderIs
				Spinner sp3 = (Spinner) findViewById(R.id.ageIs);
				sp3.setSelection(0);// ageIs
				Spinner sp4 = (Spinner) findViewById(R.id.studyIs);
				sp4.setSelection(0);// studyIs
				Spinner sp5 = (Spinner) findViewById(R.id.groupIs);
				sp5.setSelection(0);// groupIs
				Spinner sp6 = (Spinner) findViewById(R.id.subjectIs);
				sp6.setSelection(0);// subjectIs
				Spinner sp7 = (Spinner) findViewById(R.id.againIs);
				sp7.setSelection(0);// againIs
				Spinner sp8 = (Spinner) findViewById(R.id.treatmentIs);
				sp8.setSelection(0);// treatmentIs
				Spinner sp9 = (Spinner) findViewById(R.id.pharmaIs);
				sp9.setSelection(0);// pharmaIs
				Spinner sp10 = (Spinner) findViewById(R.id.doseIs);
				sp10.setSelection(0);// doseIs

				EditText cIs = (EditText) findViewById(R.id.commentIs);
				cIs.setText("");
				m_chosenDir = "";
			}
		}); // reset listener !

		Button btn2 = (Button) findViewById(R.id.reset_button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				m_chosenDir = "";
				// SignatureView mSignature;
				// mSignature = (SignatureView) findViewById(R.id.signaturePad);

			}
		}); // reset listener !

		// --------------------------------------------
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		String mTimeString = year + "-" + month + "-" + day;

		// -----------
		TextView labelm = new TextView(getBaseContext());
		labelm.setText(mTimeString);
		labelm.setTextSize(35);
		labelm.setPadding(5, 4, 3, 3);
		labelm.setGravity(Gravity.CENTER);
		labelm.setTextColor(Color.WHITE);
		labelm.setBackgroundColor(Color.BLACK);

		l.addView(labelm);

		// ----------------------------------------------------------------------------------
		// DigitalClock digital = (DigitalClock)
		// findViewById(R.id.digital_clock);
		final DigitalClock clk = new DigitalClock(MainActivity.this);
		// --------------
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				(int) LayoutParams.WRAP_CONTENT,
				(int) LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		clk.setTextSize(30);
		clk.setBackgroundColor(Color.DKGRAY);
		// - clk.setTextColor(Color.GREEN);
		clk.setGravity(Gravity.CENTER_HORIZONTAL);
		l.addView(clk);
		clk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), clk.getText().toString(),
						Toast.LENGTH_SHORT).show();
			}
		});

	}
	// l.addView(btn);

}
