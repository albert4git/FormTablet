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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.android.effectivenavigation.R;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.asignature.SignatureView;

public class sgCanvas extends InputElement implements TextWatcher {
	Question question;
	public String defaultText;
	EditText textbox;
	TextBox textBox2; // Add !!

	public sgCanvas(Question question) {
		super(question);
		this.question = question;

	}

	@Override
	public View display(Activity context) {
		// -------------------------------------------------------------------------------------
		SignatureView mSignature;
		mSignature = (SignatureView) context
				.findViewById(R.id.custom_toast_layout); // (ViewGroup)
															// findViewById(R.id.signaturePad));
		mSignature = new SignatureView(context);
		mSignature.setBackgroundColor(Color.WHITE);
		mSignature.setContentDescription("CANVAS:");
		mSignature.setBackgroundResource(R.drawable.borderblue); // SUPER oder
																	// custom_toast_layout
		// -------------------------------------------------------------------------------------

		/*
		 * mContent.addView(mSignature, LayoutParams.FILL_PARENT,
		 * LayoutParams.FILL_PARENT); mClear = (Button)findViewById(R.id.clear);
		 * mGetSign = (Button)findViewById(R.id.getsign);
		 * mGetSign.setEnabled(false); mCancel =
		 * (Button)findViewById(R.id.cancel); mView = mContent;
		 */

		return mSignature;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// val=String.valueOf(progress);
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
		String outBuf = "";
		outBuf += this.val;
		return outBuf;
	}

	@Override
	public String writeDataToPdf() {
		String outBuf = "";
		// outBuf+=this.val;
		String tIMG = "img/lb1.jpg";
		String _path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/4Survey/" + tIMG;
		Image image;
		try {
			image = Image.getInstance(_path);
			PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
			PdfPCell cell2 = new PdfPCell(image, false);
			// datatable.addCell(cell2 );

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// outBuf+=this.defaultText;
		return outBuf;
	}

	// ***************

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
