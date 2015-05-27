package com.example.android.surveyapk;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import repack.org.bouncycastle.crypto.generators.RSAKeyPairGenerator;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.QuestionTypes.*;
import com.example.android.surveyapk.csv.DBHelper;
import com.example.android.surveyapk.csv.ExternalStorage;
import com.example.android.surveyapk.csv.openreadcsv;
import com.example.android.surveyapk.directorychooser.DirectoryChooserDialog;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Section;
import com.lowagie.text.TextElementArray;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.OutputStream;


import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Environment;
import android.renderscript.Font;
//import android.util.Base64; //for apache.common
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;
//=====================================================
import java.lang.Math;//in the top of my file
import java.math.BigInteger;
import java.net.MalformedURLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//?? import org.apache.commons.codec;
import org.apache.commons.*;
//import android.util.Base64; /// apache
import org.apache.commons.codec.binary.Base64; //new
// DATE CHANGER FILE ! ToDo here
//Bouncy castle imports
/*
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
*/
//import sun.misc.BASE64Encoder;
//import sun.misc.BASE64Decoder;
//import org.apache.commons.codec.binary.Base64

public class QuestionParser {
	
    // We don't use namespaces
    private static final String ns = null;
	private String type,root;
	private int raw;
	private Context context;
	private  File[] files;
	private List <survey> surveys;
	 List <Question> questions;
	 List <Question> entries;
	public String logErr = "";		
	//MainActivity.theID;
	//*******************************************************
	 public QuestionParser(String root) {
			Log.w("-QP-", "B2B RBG100- kluch1 YYY0.95 ");

	}
	 
	 private class survey
	 {
		 
		public Evaluator evaluator; 
		public survey(File file2) {
			file=file2.toString();
			evaluator= new Evaluator();
		}
		
		String file;
		int length;
		String equation=null;
		
	 }
	 
	 public List<Question> parseAll(String dir)
	 {
		
		 File f = new File(dir);
		 files = f.listFiles();
		 Arrays.sort(files);
		 questions=new ArrayList();
		 surveys=new ArrayList();
		 if(files.length==0) { Toast.makeText(context, "No surveys found in the selected folder", Toast.LENGTH_SHORT); }
		 
		 for (File file : files) {
			 try {
				FileInputStream stream = new FileInputStream(file);
				survey cSurvey=new survey(file);
				surveys.add(cSurvey);
				List queTemp = this.parse(stream);
				
				cSurvey.length=queTemp.size();
				
				questions.addAll(queTemp);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("parsing error in "+file);				
				e.printStackTrace();
				
			} catch (IOException e) {
				System.out.println("file opening problem in "+file);		
				e.printStackTrace();
			}
		}
		 return questions;
	}
	 
	 	 
	 
    public List parse(InputStream in) throws XmlPullParserException, IOException {
    	Log.w("-QP-", "B2B RBG100- kluch1 YYY0.94 ");

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readQuestions(parser);
        } finally {
            in.close();
        }
    }




// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
private Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
	//Toast.makeText(context, "ElementType :"+type, Toast.LENGTH_LONG).show();
	Log.w("-QP-", "B2B RBG100- kluch1 YYY0.93 ");

    parser.require(XmlPullParser.START_TAG, ns, "question");
    int id = 0;
    String content = null;
    Question question=new Question();
    Question questionB=new Question();

    question.inputs= new ArrayList();
    String type;
    InputElement currentElement=null;
   
    try {
    	 question.equation= parser.getAttributeValue(null, "equation");
    	 question.eq2= parser.getAttributeValue(null, "equation2");     //WIRD GEBRAUCHT WICHTIG!!!
    	 question.eq3= parser.getAttributeValue(null, "equation3");     //WIRD GEBRAUCHT WICHTIG 200!!!    
    	 question.eq4= parser.getAttributeValue(null, "equation4");     //WIRD GEBRAUCHT !!!     
    	 question.eq5= parser.getAttributeValue(null, "equation5");     //WIRD GEBRAUCHT !!!        
    	 question.eq6= parser.getAttributeValue(null, "equation6");     //WIRD GEBRAUCHT !!!        
    	 question.eq7= parser.getAttributeValue(null, "equation7");     //WIRD GEBRAUCHT !!!   
    	 
    	 question.id = Integer.parseInt(parser.getAttributeValue(null, "id"));
    	 question.name = parser.getAttributeValue(null, "name");

    	 
	} catch (Exception e) {
		// TODO: handle exception
	}
   
	//Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
	// The TOSTER
	//Toast.makeText(getApplicationContext(), x1Box+" msg msg "+ x2Box, Toast.LENGTH_LONG).show();
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        if (name.equals("content")) {
        	 question.content = readContent(parser);
        	 
        	 currentElement=readContent(parser, question); // Pic
        	 question.inputs.add(currentElement); 
        	//------------------------------------------
        	// Toast.makeText(getApplicationContext(), "1234.A: question.content"+question.content, Toast.LENGTH_SHORT).show();
        	//------------------------------------------

        } else if (name.equals("input")) {
        	currentElement=readInput(parser, question); //qPic
        	if(currentElement!=null)
        	question.inputs.add(currentElement);
        	String qPic= question.toString();
            // Log.w("question", " qqqq question: "+question);
	            Log.w("question", " qqqq aPic: "+qPic);
	            questionB = question;

      
        } else if (name.equals("image")) {
        	 // SET UP 1 // readImage RAUS-hauen ??
        	 String MidleName = readImage(parser);
        	 RadioButtonGroup.q_IMG = MidleName;
    		 RadioButtonGroup.q_IMG_Flag = 1;
        	 question.content += " #cc# "+MidleName;
        	 question.content2 = MidleName;

    	     Log.w("img01:", "img01 RadioButtonGroup.q_IMG: "+RadioButtonGroup.q_IMG);
    	     Log.w("img01:", "img01 RadioButtonGroup.q_IMG_Flag: "+RadioButtonGroup.q_IMG_Flag);	
      
        } 
        else {
            skip(parser);
        }
    }
    

    return question;
} // End_readQuestion



// Processes summary tags in the feed.
private InputElement readInput(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	parser.require(XmlPullParser.START_TAG, ns, "input");
	String type;
	type=parser.getAttributeValue(null, "type"); 
	// ULI //
	// RadioButtonGroup.statBoxBuffer="-"+CollectionDemoActivity.albertEquationNow ;
	Log.w("-QP-", " spin100- kluch0 ULI QP RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer );
	
	if(type.equals("switch"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");

		return readSwitchM(parser,question);
	}
	
	if(type.equals("buttonTG"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");

		return readButtonTG(parser,question);
	}
	
	if(type.equals("button"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");
		return readButtonM(parser,question);
	}
	if(type.equals("buttonSgS"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");
		return readButtonSgS(parser,question);
	}
	if(type.equals("spinnt"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");

		return readSpinnerT(parser,question);
	}
	if(type.equals("spinner"))
	{
		Log.w("-QP-", "B2B RBG100- kluch YYY0.3 ");

		return readSpinnerM(parser,question);
	}
	if(type.equals("slider"))
	{
		return readSlider(parser,question);
	}
	if(type.equals("mpg"))
	{
		return readMPG(parser,question);
	}	
	if(type.equals("img"))
	{
		return readImg(parser,question);
	}
	if(type.equals("sgcanvas"))
	{
		return sgCanvas(parser,question);
	}
	if(type.equals("editbox"))
	{
		return readMultiBox(parser,question);
	}
	if(type.equals("edittextN"))
	{
		return readEditTextN(parser,question);
	}
	if(type.equals("edittext"))
	{
		return readEditText(parser,question);
	}
	else if(type.equals("checkbox"))
	{
		return readCheckBox(parser,question);
	}
	else if(type.equals("textview"))
	{		
		return readTextView(parser,question);
	}
	else if(type.equals("textviewS"))
	{		
		return readTextViewS(parser,question);
	}
	else if(type.equals("textviewG"))
	{		
		return readTextViewG(parser,question);
	}
	else if(type.equals("textSgS"))
	{		
		return readTextViewV(parser,question);
	}
	else if(type.equals("radio"))
	{
		Log.w("-QP-", "B2B RBG100- kluch Radio YYY0.4 ");

 		// Toast.makeText(getApplicationContext(), question+" RADIO ", Toast.LENGTH_SHORT).show();
		// Toast.makeText(context, "ElementType :"+type, Toast.LENGTH_LONG).show();
		return readRadio(parser,question);
	}
	
	else
	{
		Toast.makeText(context, "Uknown input element type :"+type, Toast.LENGTH_LONG).show();
		
	}

    parser.require(XmlPullParser.END_TAG, ns, "input");
	Log.w("-QP-", "B2B RBG100- kluch YYY0.6 ");

  
    return null;
}

// For the tags title and summary, extracts their text values.
private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	Log.w("-QP-", "B2B RBG100- kluch1 YYY0.92 ");

    String result = "";
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.getText();
        parser.nextTag();
    }
    Log.w("readText", "1111 readText, PPP ");

    return result;
}
 


  private void skip (XmlPullParser parser) throws XmlPullParserException, IOException {
		Log.w("-QP-", "B2B RBG100- kluch1 YYY0.91 ");

	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
//***************************************  ********************************************************
//***************************************  *****************************************************  
  
  private List readQuestions(XmlPullParser parser) throws XmlPullParserException, IOException {
	     entries = new ArrayList();
			Log.w("-QP-", "B2B RBG100- kluch1 YYY0.9 ");

	    
	     	survey cSurvey;
	    	parser.require(XmlPullParser.START_TAG, ns, "survey");
	        // Starts by looking for the entry tag
	    	cSurvey=surveys.get(surveys.size() - 1);
	    	try {
		    	cSurvey.equation=parser.getAttributeValue(null, "equation");
		    	
		    	MainActivity.topEquation2 =parser.getAttributeValue(null, "equation2");
		    	MainActivity.topEquation3 =parser.getAttributeValue(null, "equation3");		    	
		    	MainActivity.topEquation4 =parser.getAttributeValue(null, "equation4");
		    	MainActivity.topEquation5 =parser.getAttributeValue(null, "equation5");
		    	MainActivity.topEquation6 =parser.getAttributeValue(null, "equation6");
		    	MainActivity.topEquation7 =parser.getAttributeValue(null, "equation7");
		    	MainActivity.topEquation8 =parser.getAttributeValue(null, "equation8");
		    	MainActivity.topEquation9 =parser.getAttributeValue(null, "equation9");

		    	//? MainActivity.topEquation4 =parser.getAttributeValue(namespace, name)
		    	
				Log.w("-QP-", "0 MULL2  cSurvey.file: "+cSurvey.file );
				Log.w("-QP-", "1 MULL2  cSurvey.equation: "+cSurvey.equation );
				Log.w("-QP-", "2 MULL2  MainActivity.topEquation2: "+MainActivity.topEquation2 );
				Log.w("-QP-", "3 MULL2  MainActivity.topEquation3: "+MainActivity.topEquation3 );
				Log.w("-QP-", "4 MULL2  MainActivity.topEquation4: "+MainActivity.topEquation4 );
				Log.w("-QP-", "4 MULL2 ===================-----===================: AAA");

			} catch (Exception e) {
				// TODO: handle exception
			}

	        while (parser.next() != XmlPullParser.END_TAG) {
	        	if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
	        	 String name = parser.getName();
	        if (name.equals("question")) {
	            entries.add(readQuestion(parser));
	            
	        }
	        else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
  
  // One One One One One One One One One One One 
  private String readContent(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "content");
	    // XXX TTT 
		RadioButtonGroup.q_IMG_Flag = 1;
		RadioButtonGroup.q_IMG = parser.getText();
		//String tutle = "yy# "+readText(parser);	     
		Log.w("img0:", "img0 q_IMG: "+RadioButtonGroup.q_IMG);
	    Log.w("img0:", "img0 q_IMG_Flag: "+RadioButtonGroup.q_IMG_Flag);
        // 
	    //String title = "bb#"+readText(parser);
	    String title = ""+readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "content");
	    
    	// question.img ??
		
	    
	    return title;
	}
 
//Two Two Two Two  Two Two Two  Two Two Two  Two Two Two  Two Two Two 
 private InputElement readContent(XmlPullParser parser, Question question) throws IOException, XmlPullParserException {
	    question.qFlag =1;
		Log.w("-QP-", "B2B RBG100- kluch YYY0.7 "+question.qFlag );
	   // return readTextView(parser,question);
		return readTextViewContent(parser,question);

}// two
//******* 
private InputElement readMPG(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	    readMPG	tb=new readMPG(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    tb.defaultText = parser.getAttributeValue(null, "default"); 	
	    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");

	    return (InputElement) tb;
	}
//******* 
 private InputElement readImg(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	    readImg	tb=new readImg(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    tb.defaultText = parser.getAttributeValue(null, "default"); 	
	    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) tb;
	}
//******* XXXX **********
private InputElement sgCanvas(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	    sgCanvas tb=new sgCanvas(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    tb.defaultText = parser.getAttributeValue(null, "default"); 	
	    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) tb;
	}
  //**
  private String readImage(XmlPullParser parser) throws IOException,XmlPullParserException {
		//MyLog.d(TAG, "readImage");
		parser.require(XmlPullParser.START_TAG, ns, "image");
		String image = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "image");				
		return image;
  }
  //**
  private String readName(XmlPullParser parser) throws IOException,XmlPullParserException {
	//MyLog.d(TAG, "readName");
	parser.require(XmlPullParser.START_TAG, ns, "name");
	String name = readText(parser);
	parser.require(XmlPullParser.END_TAG, ns, "name");
	Log.w("-QP-", "B2B RBG100- kluch YYY0.8 " );

	return name;
	}
  //**
	private String readUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
	//MyLog.d(TAG, "readUrl");
	parser.require(XmlPullParser.START_TAG, ns, "url");
	String url = readText(parser);
	parser.require(XmlPullParser.END_TAG, ns, "url");
	return url;
	}
  //**
  //*******
	
//####################################################################################################################
	  private InputElement readMultiBox(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		    MultiBox	tb=new MultiBox(question);
		   	parser.require(XmlPullParser.START_TAG, ns, "input");
			tb.name=parser.getAttributeValue(null, "name");
		    tb.defaultText = parser.getAttributeValue(null, "default"); 	
		    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		    return (InputElement) tb;
		}
 //-----------------------------ButtonM----------------------------------------
	  private InputElement readSwitchM(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	SwitchM cb=new SwitchM(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    //cb.def=parser.getAttributeValue(null, "default");
	   	    // cb.text =""+question.content; //-Nemez-
	
		    try {
				cb.name=parser.getAttributeValue(null, "name");
				// cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
			Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");
	
		    return (InputElement) cb;
		}

	//####################################################################################################################
	  private InputElement readEditTextN(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		   	TextBoxN	tb=new TextBoxN(question);
		   	parser.require(XmlPullParser.START_TAG, ns, "input");
			tb.name=parser.getAttributeValue(null, "name");
		    tb.defaultText = parser.getAttributeValue(null, "default"); 	
		    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		    return (InputElement) tb;
		}
	 

//####################################################################################################################
  private InputElement readEditText(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	TextBox	tb=new TextBox(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
		tb.name=parser.getAttributeValue(null, "name");
	    tb.defaultText = parser.getAttributeValue(null, "default"); 	
	    tb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) tb;
	}
 
 //####################################################################################################################
  private InputElement readCheckBox(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	  	Check cb=new Check(question);
	    parser.require(XmlPullParser.START_TAG, ns, "input");
	    cb.defaultState=parser.getAttributeValue(null, "default");
	    try {
			cb.name=parser.getAttributeValue(null, "name");
			cb.coef=parser.getAttributeValue(null, "coefficient");	 
			} catch (Exception e) {
				// TODO: handle exception
			}
	    
	    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) cb;
	}
 //####################################################################################################################
  private InputElement readTextView(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	Label	lb=new Label(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    lb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    lb.text =""+readText(parser);	
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) lb;
	}
  //####################################################################################################################
  private InputElement readTextViewS(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	LabelSpace	lb=new LabelSpace(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    lb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    lb.text =""+readText(parser);	
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) lb;
	}
  //####################################################################################################################
  private InputElement readTextViewV(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	LabelV	lb=new LabelV(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    lb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    lb.text =""+readText(parser);	
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) lb;
	}
  //####################################################################################################################
  private InputElement readTextViewG(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	LabelClock	lb=new LabelClock(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    lb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    lb.text =""+readText(parser);	
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) lb;
	}
 //########################################################################################################################
 // Pic2
  private InputElement readTextViewContent(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	LabelTop	lb=new LabelTop(question); // Pic2	  
	   	lb.row=1;	   	 
   	    lb.text =""+question.content; //-Nemez-// lb2text HEAD
	    return (InputElement) lb;
	    
	}
//#########################- RRR -#########################################################################################
  private InputElement readRadio(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	  	Radio	r=new Radio(question);
	  	if(question.RadioGroup==null)
	  	question.RadioGroup=new RadioButtonGroup();
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	   	r.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
	    try {
			r.name=parser.getAttributeValue(null, "name");
			r.coef=parser.getAttributeValue(null, "coefficient");
			r.def=parser.getAttributeValue(null, "default");			
		    //r.text =""+readText(parser);	// what to do ??double

			} catch (Exception e) {
				// TODO: handle exception
			}
	 	r.group=Integer.parseInt(parser.getAttributeValue(null, "group"));
	   	r.text=""+readText(parser);
	   	question.RadioGroup.addToGroup(r);
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) r;
	}
//########################################################################################################################
	  private InputElement readSlider(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	SliderM cb=new SliderM(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    cb.dMax=parser.getAttributeValue(null, "default");
		    try {
				cb.name=parser.getAttributeValue(null, "name");
				cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		    return (InputElement) cb;
		}
	//-----------------------------ButtonM----------------------------------------
	  private InputElement readButtonM(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	buttonM cb=new buttonM(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    //cb.def=parser.getAttributeValue(null, "default");
	   	    // cb.text =""+question.content; //-Nemez-

		    try {
				cb.name=parser.getAttributeValue(null, "name");
				// cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
    		Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");

		    return (InputElement) cb;
		}
		//-----------------------------ButtonM----------------------------------------
	  private InputElement readButtonSgS(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	buttonSgS cb=new buttonSgS(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    //cb.def=parser.getAttributeValue(null, "default");
	   	    // cb.text =""+question.content; //-Nemez-

		    try {
				cb.name=parser.getAttributeValue(null, "name");
				// cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
    		Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");

		    return (InputElement) cb;
		}
	//-----------------------------ButtonM----------------------------------------
	  private InputElement readButtonTG(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	buttonTG cb=new buttonTG(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    //cb.def=parser.getAttributeValue(null, "default");
	   	    // cb.text =""+question.content; //-Nemez-

		    try {
				cb.name=parser.getAttributeValue(null, "name");
				// cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
    		Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");

		    return (InputElement) cb;
		}	  
//-----------------------------ANGRIFF----------------------------------------
	  private InputElement readSpinnerM(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	SpinnerM cb=new SpinnerM(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    cb.def=parser.getAttributeValue(null, "default");
	   	    cb.text =""+question.content; //-Nemez-

		    try {
				cb.name=parser.getAttributeValue(null, "name");
				cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
    		Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");

		    return (InputElement) cb;
		}
//-----------------------------ANGRIFF----------------------------------------
	  private InputElement readSpinnerT(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
		  	SpinnerT cb=new SpinnerT(question);
		    parser.require(XmlPullParser.START_TAG, ns, "input");
		    cb.def=parser.getAttributeValue(null, "default");
	   	    cb.text =""+question.content; //-Nemez-

		    try {
				cb.name=parser.getAttributeValue(null, "name");
				cb.coef=parser.getAttributeValue(null, "coefficient");	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		    
		    cb.row=1+Integer.parseInt(parser.getAttributeValue(null, "row"));
		    parser.next();
		    parser.require(XmlPullParser.END_TAG, ns, "input");
		   	// i wish: question.RadioGroup.addToGroup(cb);
    		Log.w("-QP-", " spin100- kluch2 Spinn -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			Log.w("-QP-", "B2B RBG100- kluch YYY0.2 ");

		    return (InputElement) cb;
		}

//-----------------------------/IRA/------------------------------------------------  
//------------------------------//--------------------------------------------------  
  
public void saveToFile(String filename,Context context){
	// String iTimeString = null;

	String exStorageState = Environment.getExternalStorageState();
	String outBuf = null;
	Question entry=null;
	int lastInd=0;
	int current = 0;
	if (Environment.MEDIA_MOUNTED.equals(exStorageState)){
		for (current = 0; current < surveys.size(); current++) {
			
	    survey cSurvey=(survey) surveys.get(current);
				
			try {
				File root = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date(System.currentTimeMillis()));
				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);
				int seconds = calendar.get(Calendar.SECOND);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int month = calendar.get(Calendar.MONTH)+1;
				int year = calendar.get(Calendar.YEAR);
                // --------------------IRA---------------------------
                // iTimeString = year+"_"+month+"_"+day+"-"+hours+minutes; 

				// Test if the path exists
				String surveyName[] =cSurvey.file.split("/");
				String name;
                // NAME is the SurveyName !!!
				name=surveyName[surveyName.length-1];
				// String path = root+"/4Survey/outbox/"+minutes+"-"+day+"-"+month+"-"+year+"/"+name+"/";
            File rootA = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
            String pathA = rootA+"/4Survey/outbox/";
            String pathL = rootA+"/4Survey/log/";
            String pathP = rootA+"/4Survey/outbox/pdfbox/";
            String pathS = rootA+"/4Survey/outbox/sqlbox/";
            String pathC = rootA+"/4Survey/outbox/csvbox/";
            String pathX = rootA+"/4Survey/outbox/xlsbox/";
			String path = root+"/4Survey/outbox/";

				boolean exists = (new File(pathP).exists());
				if (!exists) {new File(pathP).mkdirs();}
				 exists = (new File(pathS).exists());
				if (!exists) {new File(pathS).mkdirs();}
				 exists = (new File(pathC).exists());
				if (!exists) {new File(pathC).mkdirs();}
				 exists = (new File(pathX).exists());
				if (!exists) {new File(pathX).mkdirs();}
				// If not, create dirs // IRA // whats name ?
				 exists = (new File(path).exists());
				if (!exists) {new File(path).mkdirs();}
				// Open the file and a writer
				//+"-"+hours+"-"+minutes+"-"+seconds+".txt"
				// apply PATH
				File logFile = new File(pathX+year+"-"+month+"-"+day+"-"+minutes+"-IRA-"+name+filename+".xls");
				logFile.createNewFile();
				FileWriter logWriter = new FileWriter(logFile);
				BufferedWriter outer = new BufferedWriter(logWriter);
				// Write log entries to file
			
				outBuf="";
				int i=0; // ccc
	   		    Log.w("iiiA:", "iiiA: "+i);


				for ( i= lastInd ; i < lastInd +cSurvey.length; i++) {
		   		    Log.w("iiiA:", "iiiA: "+i);
		   		    Log.w("lastInd iiiE:", "iiiE lastInd: "+lastInd);

					entry = questions.get(i);									
					outBuf+=entry.writeData(context);
					if(entry.equation!=null)
					{	
					//entry.equation					
					System.out.println(entry.equation);
					}
					outBuf+="\r\n";
					
				}
	   		    Log.w("lastInd iiiF:", "iiiF: "+i);
				lastInd=i;				
	   		    Log.w("lastInd iiiE:", "iiiE lastInd: "+lastInd);
                // IRA ?
				outer.write(outBuf);
				outer.close();
				//Toast.makeText(context, "Survey saved"+name+filename, Toast.LENGTH_LONG).show();
				Toast toast = Toast.makeText(context,"Survey saved:"+name+filename , Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
								
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(context, "Couldn't save"+filename , Toast.LENGTH_LONG).show();
				
			}
		
		}
	
	}
	else{
		//FAIL
		System.out.println("File not accessible");
		Toast.makeText(context, "R.string.pp_omni_file_not_accessible", Toast.LENGTH_SHORT).show();
	}
	 current = 0;
}

//-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-/-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-/-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-
//-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-/-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-/-NAT-/-NAT-/-NAT-/-NAT-/-NAT/-

public void saveToPdf (String filename,String PatDign, Context context) {
	String overall = null;
	//String tss2 = "";
    String surveyFileName="";
    //----------------------------------------------------------------
    Log.w("Seide", "spez openreadcsv.timeMS: "+openreadcsv.timeMS);
	Log.w("line ...", " spez openreadcsv.strTimeS: "+openreadcsv.strTimeS);
    
	String sqIn ="";
    sqIn+="\n";

    Log.w("Seide", "1 spic DirectoryChooserDialog.strRedSER: "+DirectoryChooserDialog.strRedSER);
    Log.w("Seide", "2 spic openreadcsv.strTimeS: "+openreadcsv.strTimeS);

    Long theID1=Long.valueOf(DirectoryChooserDialog.strRedSER);
    Log.w("Seide", "5 spic theID1: "+theID1);
    Long theID2=Long.valueOf(openreadcsv.strTimeS);
    Log.w("Seide", " spic theID2: "+theID2);
    Log.w("Seide", "6 spic theID: "+MainActivity.theID); // Pinokio
    //----------------------------------------------------------------	
	String exStorageState = Environment.getExternalStorageState();
	Document document;
	
	String outBuf = null;
	String csvValCont = null;
	
	Question entry=null;
	int lastInd=0;
	int y=0;
	int sumScore=0;
	String strSumScore="";
	
	String USER_PASS = "vk2015";
	String OWNER_PASS ="vk2015";
	 USER_PASS = "";
	 OWNER_PASS ="";
	//------------------------------
	String tBuf ="";
	String csvHeadCont ="";
	String x1Box ="";
	String x2Box ="";
	String x3Box ="";
	String subScore ="";
	String historyIn = "" ;
    String stSQL="";
	

	File rootA = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
	String pathA = rootA+"/4Survey/outbox/";
	String pathL = rootA+"/4Survey/log/";
	String pathP = rootA+"/4Survey/outbox/pdfbox/";
	String pathS = rootA+"/4Survey/outbox/sqlbox/";
	String pathC = rootA+"/4Survey/outbox/csvbox/";
	String pathX = rootA+"/4Survey/outbox/xlsbox/";
	String pathJ = rootA+"/4Survey/outbox/JSONbox/";
	String pathH = rootA+"/4Survey/outbox/hist/";
	//
	//keyIn.close();
	//RSAKeyPairGenerator r = new RSAKeyPairGenerator();
	String pathK = rootA+"/4Survey/key/";
	String pathKAB = rootA+"/4Survey/key/ABKEY.asc";


	// InputStream keyIn = new ByteArrayInputStream(pathKAB2);
    //-- FileInputStream key = new FileInputStream("res/keys/public.bpg");
    //-- PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(key);
	
	
	
    //__________________.BOX.______________________ //
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date(System.currentTimeMillis()));
	int hours = calendar.get(Calendar.HOUR_OF_DAY);
	int minutes = calendar.get(Calendar.MINUTE);
	int seconds = calendar.get(Calendar.SECOND);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	int month = calendar.get(Calendar.MONTH)+1;
	int year = calendar.get(Calendar.YEAR);
    logErr+= DirectoryChooserDialog.iTimeString+"-"+seconds+", ";    	
    String iDayString = year+"-"+month+"-"+day; 
    //----------------------------------------------//
	String srcPath =rootA+"/4Survey/";
	String tgtPath =rootA+"/documents/"+iDayString+"/";
			
	boolean exists = (new File(pathA).exists());
	if (!exists) {new File(pathA).mkdirs();}		
     exists = (new File(pathP).exists());
	if (!exists) {new File(pathP).mkdirs();}	
     exists = (new File(pathL).exists());
	if (!exists) {new File(pathL).mkdirs();}	
	 exists = (new File(pathS).exists());
	if (!exists) {new File(pathS).mkdirs();}	
	 exists = (new File(pathC).exists());
	if (!exists) {new File(pathC).mkdirs();}	
	 exists = (new File(pathX).exists());
	if (!exists) {new File(pathX).mkdirs();}	
	 exists = (new File(pathJ).exists());
	if (!exists) {new File(pathJ).mkdirs();}	
	 exists = (new File(pathH).exists());
	if (!exists) {new File(pathH).mkdirs();}
     exists = (new File(tgtPath).exists());
	if (!exists) {new File(tgtPath).mkdirs();}
		
	logErr+=pathA+", ";	
	File historyFile = new File(pathA+"/hist/history"+"_"+DirectoryChooserDialog.iSER+".csv");
	File sv_historyFile = new File(pathA+"/hist/sv_history"+"_"+DirectoryChooserDialog.iSER+".csv");
	File sv_historyFileQ = new File(pathS+"/sv_history"+"_"+DirectoryChooserDialog.iSER+".sql");
	File sv_qIn = new File(pathS+"/qIn"+"_"+DirectoryChooserDialog.iSER+".sql");
	File sv_set = new File(pathS+"/sv_set"+"_"+DirectoryChooserDialog.iSER+".sql");
	File sv_json = new File(pathJ+"/sv_history"+"_"+DirectoryChooserDialog.iSER+".txt");
	File printLogFile = new File(pathL+"print_log"+"_"+DirectoryChooserDialog.iSER+".csv");

    MainActivity.sname = filename; // ??? SUBJECTNAME

	Log.w("-QP", " triple BVB MainActivity.sname = filename;:"+MainActivity.sname+"xx");

	//*************************************************************************************************	
	//*************************************************************************************************
	int current = 0;
	if (Environment.MEDIA_MOUNTED.equals(exStorageState)){
	for ( current = 0; current < surveys.size(); current++) {
	survey cSurvey=(survey) surveys.get(current);
	document = new Document(PageSize.A4, 10, 10, 10, 10);		
		try {
			
			File root = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
			String surveyName[] =cSurvey.file.split("/");
			surveyFileName=surveyName[surveyName.length-1];
			logErr+=surveyFileName+", ";
			logErr+=MainActivity.sname+", ";
			 MainActivity.surveyName = surveyFileName; 
			 
            Log.w("Seide", "MULL 1logic surveyFileName: "+surveyFileName );
            Log.w("Seide", "MULL 1logic filename: "+filename );
            surveyFileName = surveyFileName.replace(".xml", "");
            Log.w("Seide", " 1logic surveyFileName2: "+surveyFileName );
            //--------------------------------------------------------------------------------
			File logFile = new File(pathX+year+"-"+month+"-"+day+"-"+MainActivity.sname+"-"+surveyFileName+"_"+DirectoryChooserDialog.iSER+".xls");
			File csvLogFile = new File(pathC+surveyFileName+"_"+DirectoryChooserDialog.iSER+".csv");
			File extLogFile = new File(pathC+surveyFileName+"_ExtInput_"+DirectoryChooserDialog.iSER+".csv");
			File scorelogFile = new File(pathC+surveyFileName+"_Score_"+DirectoryChooserDialog.iSER+".csv");
            //--
			File stQlogFile = new File(pathS+surveyFileName+"_"+DirectoryChooserDialog.iSER+".sql");
			File extQLogFile = new File(pathS+surveyFileName+"_ExtInput_"+DirectoryChooserDialog.iSER+".sql");
			File scoreSQlogFile = new File(pathS+surveyFileName+"_Score_"+DirectoryChooserDialog.iSER+".sql");
            //--------------------------------------------------------------------------------
			logFile.createNewFile();
			FileWriter logWriter = new FileWriter(logFile);
			BufferedWriter outer = new BufferedWriter(logWriter);
			outBuf="";
            //-----------------------
			tBuf="";
            tBuf+=DirectoryChooserDialog.iTimeString+";";
            tBuf+=MainActivity.sname+";";
            tBuf+=PatDign+";";
            tBuf+=surveyFileName+";";
            //--------
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(new Date(year, month, month));
            Log.w("Seide", "Sun DATE strDate: "+strDate );
            //***************************
			stSQL="CREATE TABLE IF NOT EXISTS `";
			stSQL+=surveyFileName+"` ( ";
			stSQL+="`_ID` INT ,";
			stSQL+="`SER`	TEXT,";
			stSQL+="`TimeMS`	REAL,";
			stSQL+="`Date`	TEXT,";
			stSQL+="`Survay`	TEXT,";
			stSQL+="`SName`	TEXT,";
			stSQL+="`Diagnose`	TEXT,";
            //--------VAL---------------
    	    // timeMS=Long.toString(timeNow); // the LastTimeMs in Ms
            Random rn = new Random();
            Random rn1 = new Random();
            long localTimeMS;
            int A = rn1.nextInt(100000000);
            int B = rn.nextInt(10000000);
            int C = rn1.nextInt(1000000);
            int D = rn.nextInt(10000);
            //------------
    	    localTimeMS= System.currentTimeMillis() - 1000000000 + A + B + C + D + rn.nextInt(1000) + rn1.nextInt(100)+ rn.nextInt(10);
            Log.w("Seide", " time2 A: "+A );
            Log.w("Seide", " time2 B: "+B );
            Log.w("Seide", " time2 C: "+C );
            Log.w("Seide", " time2 D: "+D );
            Log.w("Seide", " time2 localTimeMS: "+localTimeMS );
            //--------HEAD---------------
            csvHeadCont="";
            csvHeadCont+="_ID;";
            csvHeadCont+="SER;";
            csvHeadCont+="TimeMS;";
            csvHeadCont+="Date;";
            csvHeadCont+="Survay;";
            csvHeadCont+="SName;";
            csvHeadCont+="Diagnose;";          
            //-------BODY-----------------
			csvValCont="";
            csvValCont+=MainActivity.theID+",";
            csvValCont+=DirectoryChooserDialog.iSER+",";
            csvValCont+=openreadcsv.timeMS+",";
            csvValCont+=DirectoryChooserDialog.iTimeString+",";
            csvValCont+=surveyFileName+",";
            csvValCont+=MainActivity.sname+",";// SNAME
            csvValCont+=PatDign+",";
            //-----------------------
            //-----------------------
            //-----------------------

			try {
                //--------------------------- // ---------------------------
                //    Document document = new Document();
                //    PdfWriter writer = PdfWriter.getInstance(document, file);
                //    writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                //--------------------------- // ---------------------------
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathP+year+"-"+month+"-"+day+"-"+MainActivity.sname+"-"+surveyFileName+"-"+DirectoryChooserDialog.iSER+".pdf"));
                writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
	   			logErr+="Problem PdfWriter #710, ";
				e.printStackTrace();
			}
			// Write log entries to file
			//====================================================================================
			document.open();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // pdf-logo Logo LOGO 		
			// Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.kirsh1);
			//Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.nilogo);
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.gcbs);

			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */, 100 /* Ratio */, stream);
			Image jpg = Image.getInstance(stream.toByteArray());		
			jpg.scalePercent(99f, 99f);
			document.add(jpg);
			//-------------------------------------------------------------		        
		      Paragraph p = new Paragraph(" Subject:"+MainActivity.sname+" | Date: "+DirectoryChooserDialog.iTimeString+" | Test: "+surveyFileName+"  "+"| Tablet: "+DirectoryChooserDialog.iSER+" ");
		      p.setSpacingAfter(20);
		      p.setAlignment(1); // Center
		      document.add(p);
			//-----------------------------------------------------------
			document.addHeader("My Header Title", "My Header Details");
			//-----------------------------------------------------------
            String foot =" Subject:"+MainActivity.sname+" | Date: "+DirectoryChooserDialog.iTimeString+" | Test: "+surveyFileName+"  "+"| Tablet: "+DirectoryChooserDialog.iSER+" ";
			HeaderFooter footer = new HeaderFooter(new Phrase(foot , FontFactory.getFont(FontFactory.HELVETICA, 14)), exists);
            footer.setBorder(Rectangle.TOP);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.setFooter(footer);
                
			String score = null;
			String score2 = null;
			String score3 = null;
			String score4 = null;
			String score5 = null;
			String score6 = null;
			String score7 = null;
			String score8 = null;
			String score9 = null;
			String score10 = null;
			String scoreV = "";
			
			int i=0;
   		    Log.w("iiiG:", "iiiG: "+i); // LLL
            int qp = 0;
			// for ( i= 0 ; i < lastInd +cSurvey.length; i++) {
		    for ( i= lastInd ; i < lastInd +cSurvey.length; i++) {
               qp ++ ;
				
	   		    Log.w("iiiD:", "iiiD: "+i);
				entry = questions.get(i);
				// pic5 
								
				try {
					Element element = entry.writeDataToPdf(context);						
					if(element!=null)
					{
						if(entry.equation!=null)
						{	
                             //--------------------------------------------
							 x1Box=entry.name+";";
							 x2Box=entry.content+";";
							 //-------------------------
							 csvHeadCont+=x1Box;// Ribka
							 //---- ANKER ----
							 stSQL+="`"+entry.name+"` REAL,";
                             //--------------------------------------------
							 score=entry.evaluator.evaluate(entry.equation);
							 outBuf+=score+"\r\n";
							 csvValCont+=score+","; //minus;
                             strSumScore= score;
                             subScore = score.substring(0,1);
                             //-------AAA-----------------------------------
							 try {
                                 //****** Unnotig ?? *******************
                                 y = Integer.parseInt(subScore);
                                 String needle ="Haendigkeit";							 
                                 if(containsIgnoreCase( surveyFileName, needle))		
                                 { y=y*10;}
                                 sumScore+=y;
							 } catch (NumberFormatException e) {
                              //do something! anything to handle the exception.
							 }
                             //---------------------------------------------------- 
 							 Log.w("line ...", " gras pod5: nach_else ");				   
 					         Log.w("1", "1 iSCORE11: TAPORb NAME: "+entry.name);
 					         Log.w("1", "1 iSCORE11: TAPORb  entry.equation:"+entry.equation);
 					         Log.w("2", "2 iSCORE11: TAPORb2 entry.eq2:"+entry.eq2);
 					         Log.w("3", "3 iSCORE11: TAPORb3 entry.eq3:"+entry.eq3);
 					         Log.w("vvvv", " iSCORE11: TAPORc score:"+score);
 					         Log.w("vvvv", " iSCORE11: TAPORc2 score:"+score2);
 					         Log.w("vvvv", " iSCORE11: TAPORc3 score:"+score3);
 					         Log.w("bSc", "START: ============================== eradio 1 START");                  
                             String bSc; //DYN1
 							if(entry.equation!=null){   
 							    bSc = entry.name+""; //mach_bar								
 								score=entry.evaluator.evaluate(entry.equation);
 								scoreV += ""+entry.name+" score: "+score; 	
 								cSurvey.evaluator.putVariable(bSc, score); // WOZU ?	DAZU							

 								MainActivity.alphaDef+=" ,`"+entry.name+"_score`"+" REAL";
 								MainActivity.alphaName+=" ,"+entry.name+"_score";
 								MainActivity.alphaVal+=" ,"+score+"";
 								//********************************************************
 								MainActivity.scoreDef+=" ,`"+entry.name+"_score`"+" REAL";
 								MainActivity.scoreName+=" ,"+entry.name+"_score";
 								MainActivity.scoreVal+=" ,"+score+"";
 								//MainActivity.betaIn="";
 						        Log.w("", "1 eradio ================================== 2");
 						        Log.w("", "1 eradio NAME: "+entry.name);
 						        Log.w("", "1 eradio entry.equation: "+entry.equation );
 						        Log.w("", "1 eradio bSc: "+bSc);	
 							}
 							try {
								if(entry.eq2!=null){  
								    bSc = entry.name+"eq2"; //mach_bar
									score2=entry.evaluator.evaluate(entry.eq2);// weg damit
									scoreV += ", "+bSc+" score2: "+score2; 		
								    cSurvey.evaluator.putVariable(bSc, score2); 

									MainActivity.alphaDef+=" ,`"+entry.name+"_score2`"+" REAL"; // add bSc ? 
									MainActivity.alphaName+=" ,"+entry.name+"_score2";
									MainActivity.alphaVal+=" ,"+score2+"";
									//*******
									MainActivity.scoreDef+=" ,`"+entry.name+"_score2`"+" REAL"; // add bSc ? 
									MainActivity.scoreName+=" ,"+entry.name+"_score2";
									MainActivity.scoreVal+=" ,"+score2+"";	
								    //2222222222222222222222222222222222222222222222222222222222222222
								    Log.w("2", "2 eradio ================================== 2");
								    Log.w("2", "2 eradio NAME: "+entry.name);
								    Log.w("2", "2 eradio entry.eq2: "+entry.eq2);
								    Log.w("2", "2 eradio bSc: "+bSc);							    
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq2: "+entry.eq2+" | entryName: "+entry.name+"eq2" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq2: "+entry.eq2+" | entryName: "+entry.name+"eq2" , Toast.LENGTH_LONG).show();
					   			}
							}
 							try {
								if(entry.eq3!=null){  
								    bSc = entry.name+"eq3"; //mach_bar
									score3=entry.evaluator.evaluate(entry.eq3); // weg damit
									scoreV += ", "+bSc+" score3: "+score3; 
								    cSurvey.evaluator.putVariable(bSc, score3); 

									MainActivity.alphaDef+=" ,`"+entry.name+"_score3`"+" REAL";
									MainActivity.alphaName+=" ,"+entry.name+"_score3";
									MainActivity.alphaVal+=" ,"+score3+"";	
									//***
									MainActivity.scoreDef+=" ,`"+entry.name+"_score3`"+" REAL";
									MainActivity.scoreName+=" ,"+entry.name+"_score3";
									MainActivity.scoreVal+=" ,"+score3+"";	
								    //33333333333333333333333333333333333333333333333333333333333333333		
								    Log.w("3", "3 eradio =================================== 3");
								    Log.w("3", "3 eradio NAME: "+entry.name);
								    Log.w("3", "3 eradio entry.eq3: "+entry.eq3);
								    Log.w("3", "3 eradio bSc: "+bSc);
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq3: "+entry.eq3+" | entryName: "+entry.name+"eq3" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq3: "+entry.eq3+" | entryName: "+entry.name+"eq3" , Toast.LENGTH_LONG).show();
					   			}
							}
 					         Log.w("bSc", "STOP: ============================== eradio STOP");							
 							try {
								if(entry.eq4!=null){ 
								    bSc = entry.name+"eq4"; //mach_bar
									score4=entry.evaluator.evaluate(entry.eq4);// weg damit
									scoreV += ", "+entry.name+" score4: "+score4; 
								    cSurvey.evaluator.putVariable(bSc, score4); 							    

									MainActivity.alphaDef+=" ,`"+entry.name+"_score4`"+" REAL";
									MainActivity.alphaName+=" ,"+entry.name+"_score4";
									MainActivity.alphaVal+=" ,"+score4+"";
									//***
									MainActivity.scoreDef+=" ,`"+entry.name+"_score4`"+" REAL";
									MainActivity.scoreName+=" ,"+entry.name+"_score4";
									MainActivity.scoreVal+=" ,"+score4+"";
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq4: "+entry.eq4+" | entryName: "+entry.name+"eq4" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq4: "+entry.eq4+" | entryName: "+entry.name+"eq4" , Toast.LENGTH_LONG).show();
					   			}
							}
 							try {
								if(entry.eq5!=null){  
								    bSc = entry.name+"eq5"; //mach_bar
									score5=entry.evaluator.evaluate(entry.eq5);// weg damit
									scoreV += ", "+entry.name+" score5: "+score5; 
								    cSurvey.evaluator.putVariable(bSc, score5); 

									MainActivity.alphaDef+=" ,`"+entry.name+"_score5`"+" REAL";
									MainActivity.alphaName+=" ,"+entry.name+"_score5";
									MainActivity.alphaVal+=" ,"+score5+"";	
									//***
									MainActivity.scoreDef+=" ,`"+entry.name+"_score5`"+" REAL";
									MainActivity.scoreName+=" ,"+entry.name+"_score5";
									MainActivity.scoreVal+=" ,"+score5+"";
									
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq5: "+entry.eq5+" | entryName: "+entry.name+"eq5" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq5: "+entry.eq5+" | entryName: "+entry.name+"eq5" , Toast.LENGTH_LONG).show();
					   			}
							}
 							try {
								if(entry.eq6!=null){  
								    bSc = entry.name+"eq6"; //mach_bar
									score6=entry.evaluator.evaluate(entry.eq6);// weg damit
									scoreV += ", "+entry.name+" score6: "+score6; 
								    cSurvey.evaluator.putVariable(bSc, score6); 
								  
									MainActivity.alphaDef+=" ,`"+entry.name+"_score6`"+" REAL";
									MainActivity.alphaName+=" ,"+entry.name+"_score6";
									MainActivity.alphaVal+=" ,"+score6+"";
									//***
									MainActivity.scoreDef+=" ,`"+entry.name+"_score6`"+" REAL";
									MainActivity.scoreName+=" ,"+entry.name+"_score6";
									MainActivity.scoreVal+=" ,"+score6+"";
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq6: "+entry.eq6+" | entryName: "+entry.name+"eq6" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq6: "+entry.eq6+" | entryName: "+entry.name+"eq6" , Toast.LENGTH_LONG).show();
					   			}
							}
 							try {
								if(entry.eq7!=null){  
								    bSc = entry.name+"eq7"; //mach_bar
									score7=entry.evaluator.evaluate(entry.eq7);// weg damit
									scoreV += ", "+entry.name+" score7: "+score7; 
								    cSurvey.evaluator.putVariable(bSc, score7); 

									MainActivity.alphaDef+=" ,`"+entry.name+"_score7`"+" REAL";
									MainActivity.alphaName+=" ,"+entry.name+"_score7";
									MainActivity.alphaVal+=" ,"+score7+"";
									//***
									MainActivity.scoreDef+=" ,`"+entry.name+"_score7`"+" REAL";
									MainActivity.scoreName+=" ,"+entry.name+"_score7";
									MainActivity.scoreVal+=" ,"+score7+"";								
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					   			logErr+="Problem with equation in survey #1252 | "+cSurvey.file+"eq6: "+entry.eq6+" | entryName: "+entry.name+"eq6" ;
					   			for (int ik=0; ik < 3; ik++)
					   			{
									Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"eq6: "+entry.eq6+" | entryName: "+entry.name+"eq6" , Toast.LENGTH_LONG).show();
					   			}
							}
                             //---------------------------------------------------
							 
 							 if(entry.name!=null) {
 						        Log.w("SeekBar", " DAVIS iscore12 cSurvey.evaluator.putVariable(entry.name: "+entry.name);
 					        	Log.w("SeekBar", " DAVIS iscore12 cSurvey.evaluator.putVariable score: "+score);
 							    Log.w("iii:", "iii: "+i);
 							    document.add(new Paragraph(" \nQuestion "+(qp)+") "+scoreV+"\n\n")); // scoreV		
 							 }
 						}	else {
 					      outBuf+="null"+"\r\n";
 					    }
 						    //csvValCont+="null"+"\r\n";	
 						    Log.w("QP", "--- QPkluch1 TAPORqq5 equation: ");
 			        		Log.w("QP", "Kakao: ((( iscore gras5 entry.name: "+entry.name);
 			        		Log.w("QP", "Kakao: ((( iscore gras5 entry.content: "+entry.content);
 			        		Log.w("QP", "Kakao: ((( iscore gras5 entry.coef: "+entry.coef );
 					      document.add(element); //WOZU ??		           
					}
				} catch (DocumentException e) {
		   			logErr+="Problem  #806, ";
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EvaluationException e) {
		   			logErr+="Problem with equation in survey #1367, "+cSurvey.file+"equ: "+entry.equation+"name: "+entry.name;

		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: "+cSurvey.file+"equ: "+entry.equation+"name: "+entry.name , Toast.LENGTH_LONG).show();
		   			}
					e.printStackTrace();
				}
				scoreV = "";
			}//===== end PDF schleife =======
			lastInd=i;
            //***************
			Log.w("QP!", " alphaX MA.alphaName!!: "+MainActivity.alphaName);
			Log.w("QP!", " alphaX MA.alphaVal!!: "+MainActivity.alphaVal);
			Log.w("QP!", " alphaX MA.alphaDef!!: "+MainActivity.alphaDef);			
			//String overall = null;
			String tss2 = "";
			String tss3 = "";
			String tss4 = "";
			String tss5 = "";
			String tss6 = "";
			String tss7 = "";
			String tss8 = "";
			String tss9 = "";

			// parser.getAttributeValue(null, "equation2");     //WIRD GEBRAUCHT WICHTIG!!!
	    	// question.eq2= parser.getAttributeValue(null, "equation2");     //WIRD GEBRAUCHT WICHTIG!!!
			Log.w("QP!", " fradio MainActivity.topEquation2: "+MainActivity.topEquation2);
			Log.w("QP!", " fradio MainActivity.topEquation3: "+MainActivity.topEquation3);
			Log.w("QP!", " fradio ======================= === === ===");
			//#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-
			//#-#
					
				try {
					if(MainActivity.topEquation2!=null){  
						tss2 += cSurvey.evaluator.evaluate(MainActivity.topEquation2); 
						cSurvey.evaluator.putVariable("eq2", tss2);

						Log.w("QP", " beta gama tss2: "+tss2);			
						document.add(new Paragraph(" tss2: "+tss2+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore2`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore2";
						MainActivity.alphaVal+=" ,"+tss2+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore2`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore2";
						MainActivity.scoreVal+=" ,"+tss2+"";
						
						
						Log.w("QP!", " fradio MainActivity.topEquation2: "+MainActivity.topEquation2);
						Log.w("QP!", " fradio tss2: "+tss2);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq2: "+MainActivity.topEquation2+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq2: "+MainActivity.topEquation2+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
				
				try {
					if(MainActivity.topEquation3!=null){  
						tss3 += cSurvey.evaluator.evaluate(MainActivity.topEquation3); 
						cSurvey.evaluator.putVariable("eq3", tss3);

						Log.w("QP", " beta gama tss3: "+tss3);
						document.add(new Paragraph(" tss3: "+tss3+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore3`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore3";
						MainActivity.alphaVal+=" ,"+tss3+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore3`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore3";
						MainActivity.scoreVal+=" ,"+tss3+"";
						
						
						Log.w("QP!", " fradio MainActivity.topEquation3: "+MainActivity.topEquation3);
						Log.w("QP!", " fradio tss2: "+tss3);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq3: "+MainActivity.topEquation3+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq3: "+MainActivity.topEquation3+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
				try {
					if(MainActivity.topEquation4!=null){  
						//NEW
						tss4 += cSurvey.evaluator.evaluate(MainActivity.topEquation4); 
						cSurvey.evaluator.putVariable("eq4", tss4);					

						Log.w("QP", " beta gama tss4: "+tss4);
						document.add(new Paragraph(" tss4: "+tss4+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore4`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore4";
						MainActivity.alphaVal+=" ,"+tss4+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore4`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore4";
						MainActivity.scoreVal+=" ,"+tss4+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation4: "+MainActivity.topEquation4);
						Log.w("QP!", " fradio tss4: "+tss4);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq4: "+MainActivity.topEquation4+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq4: "+MainActivity.topEquation4+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
				try {
					if(MainActivity.topEquation5!=null){  
						//NEW
						tss5 += cSurvey.evaluator.evaluate(MainActivity.topEquation5); 
						cSurvey.evaluator.putVariable("eq5", tss5);				

						Log.w("QP", " beta gama tss5: "+tss5);
						document.add(new Paragraph(" tss5: "+tss5+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore5`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore5";
						MainActivity.alphaVal+=" ,"+tss5+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore5`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore5";
						MainActivity.scoreVal+=" ,"+tss5+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation5: "+MainActivity.topEquation5);
						Log.w("QP!", " fradio tss5: "+tss5);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq5: "+MainActivity.topEquation5+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq5: "+MainActivity.topEquation5+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}

			//#-#
				try {
					if(MainActivity.topEquation6!=null){  
						//NEW
						tss6 += cSurvey.evaluator.evaluate(MainActivity.topEquation6); 
						cSurvey.evaluator.putVariable("eq6", tss6);				

						Log.w("QP", " beta gama tss6: "+tss6);
						document.add(new Paragraph(" tss6: "+tss6+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore6`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore6";
						MainActivity.alphaVal+=" ,"+tss6+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore6`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore6";
						MainActivity.scoreVal+=" ,"+tss6+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation6: "+MainActivity.topEquation6);
						Log.w("QP!", " fradio tss6: "+tss6);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq6: "+MainActivity.topEquation6+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq6: "+MainActivity.topEquation6+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
			//#-#
				try {
					if(MainActivity.topEquation7!=null){  
						//NEW
						tss7 += cSurvey.evaluator.evaluate(MainActivity.topEquation7); 
						cSurvey.evaluator.putVariable("eq7", tss7);				

						Log.w("QP", " beta gama tss7: "+tss7);
						document.add(new Paragraph(" tss7: "+tss7+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore7`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore7";
						MainActivity.alphaVal+=" ,"+tss7+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore7`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore7";
						MainActivity.scoreVal+=" ,"+tss7+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation7: "+MainActivity.topEquation7);
						Log.w("QP!", " fradio tss7: "+tss7);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq7: "+MainActivity.topEquation7+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq7: "+MainActivity.topEquation7+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
			//#-#
				try {
					if(MainActivity.topEquation8!=null){  
						//NEW
						tss8 += cSurvey.evaluator.evaluate(MainActivity.topEquation8); 
						cSurvey.evaluator.putVariable("eq8", tss8);				

						Log.w("QP", " beta gama tss8: "+tss8);
						document.add(new Paragraph(" tss8: "+tss8+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore8`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore8";
						MainActivity.alphaVal+=" ,"+tss8+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore8`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore8";
						MainActivity.scoreVal+=" ,"+tss8+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation8: "+MainActivity.topEquation8);
						Log.w("QP!", " fradio tss8: "+tss8);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq8: "+MainActivity.topEquation8+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq8: "+MainActivity.topEquation8+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
			//#-#
				try {
					if(MainActivity.topEquation9!=null){  
						//NEW
						tss9 += cSurvey.evaluator.evaluate(MainActivity.topEquation9); 
						cSurvey.evaluator.putVariable("eq9", tss9);				

						Log.w("QP", " beta gama tss9: "+tss9);
						document.add(new Paragraph(" tss9: "+tss9+""));
						//*******
						MainActivity.alphaDef+=" ,`"+"topSubScore9`"+" REAL";
						MainActivity.alphaName+=" ,"+"topSubScore9";
						MainActivity.alphaVal+=" ,"+tss9+"";
						//*******
						MainActivity.scoreDef+=" ,`"+"topSubScore9`"+" REAL";
						MainActivity.scoreName+=" ,"+"topSubScore9";
						MainActivity.scoreVal+=" ,"+tss9+"";
						
						Log.w("QP!", " fradio MainActivity.topEquation9: "+MainActivity.topEquation9);
						Log.w("QP!", " fradio tss9: "+tss9);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq9: "+MainActivity.topEquation9+" | "+" | "  ;
		   			for (int ik=0; ik < 3; ik++)
		   			{
						Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq9: "+MainActivity.topEquation9+" | "+" | " , Toast.LENGTH_LONG).show();
		   			}
				}
			//#-#	
			//#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-
			
			
			try {
				if(cSurvey.equation!=null)
				{
				    overall=cSurvey.evaluator.evaluate(cSurvey.equation);
					Log.w("QP!", " MULL2-OS  surveyFileName: "+surveyFileName);
					Log.w("QP!", " MULL2-OS OVERALL EQ. cSurvey.equation: "+cSurvey.equation);
					Log.w("QP!", " MULL2-OS OVERALL EQ. overall: "+overall);

					//*******
					MainActivity.alphaDef+=" ,`"+"overall_score`"+" REAL";
					MainActivity.alphaName+=" ,"+"overall_score";
					MainActivity.alphaVal+=" ,"+overall+"";
					//*******
					MainActivity.scoreDef+=" ,`"+"overall_score`"+" REAL";
					MainActivity.scoreName+=" ,"+"overall_score";
					MainActivity.scoreVal+=" ,"+overall+"";
					//*******
				    document.add(new Paragraph("\nOverall Score: "+overall+"\n\n"));	
				    //******************************************************************************

			 	        if (surveyFileName.contains("Sg")) { 
							//document.add(new Paragraph("---------Untershrieft---------- "+"\n\n"));
							Matrix matrix = new Matrix();
							matrix.postRotate(90);
							// pdf-logo Logo LOGO 		
							String sgPath = Environment
									.getExternalStorageDirectory()
									.getAbsolutePath()
									+ "/4Survey/outbox/pdfbox/sg.jpg";
							ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
							Bitmap bitmap2 = BitmapFactory.decodeFile(sgPath);
							bitmap2 = Bitmap.createBitmap(bitmap2, 100, 188, 685, 1083, matrix, true);
							//bitmap2 = Bitmap.createBitmap(bitmap2, x, y, width, height)						
							bitmap2.compress(
									Bitmap.CompressFormat.JPEG /* FileType */,
									100 /* Ratio */, stream2);
							Image jpg2 = Image.getInstance(stream2
									.toByteArray());
							jpg2.scalePercent(30f, 30f);
							document.add(jpg2);
							//document.add(new Paragraph("---------Untershrieft---------- "+"\n\n"));
							String sg = "sg.jpg";
		                    File yFile = new File(pathP, sg);
							if(yFile.delete()){
		                		Log.w("del", "  delete: "+pathP );
		            		}else{
		                		Log.w("del", "  not delete: "+pathP );
		            		}
						
							
						}
			 	        
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	   			logErr+="Problem with equation in survey #1252 | Top: "+cSurvey.file+"TOP eq: "+cSurvey.equation+" | "+" | "  ;
	   			for (int ik=0; ik < 3; ik++)
	   			{
					Toast.makeText(context, "Problem with equation in survey: TOP: "+cSurvey.file+"TOP eq: "+cSurvey.equation+" | "+" | " , Toast.LENGTH_LONG).show();
	   			}
			}
		    Log.w("iiiB:", "iii: "+i);
			outer.write(outBuf); // ???!!!log-O-was
			outer.close();
			document.close();
			logWriter.close();
			
						
            //***************KRASNODON here soll SQL **********************************************
			/*
			MainActivity.topEquation2="null";
			MainActivity.topEquation3="null";
			MainActivity.topEquation4="null";
			MainActivity.topEquation5="null";
			MainActivity.topEquation6="null";
			MainActivity.topEquation7="null";
			MainActivity.topEquation8="null";
            */
            //*****NotRadioVal******ANGARA ************ ANGARA *********ANGARA *************************N+1
            MainActivity.alphaName = "_ID,SER,TimeMS,Date,SName,Survay" + MainActivity.alphaName; // survay added //
            MainActivity.scoreName = "_ID,SER,TimeMS,Date,SName,Survay" + MainActivity.scoreName; // survay added //
            MainActivity.alphaVal=DirectoryChooserDialog.Shapka+",'"+surveyFileName+"','"+MainActivity.strName+"'"+MainActivity.alphaVal; // surveyFileName added //      
            MainActivity.scoreVal=DirectoryChooserDialog.Shapka+",'"+surveyFileName+"','"+MainActivity.strName+"'"+MainActivity.scoreVal; // surveyFileName added //                       
            //+MainActivity.strName+"','"
            //+MainActivity.strName+"','"
    		Log.w("ANGARA", "  ANGARA3 MainActivity.alphaName: "+MainActivity.alphaName );
    		Log.w("ANGARA", "  ANGARA3 MainActivity.alphaVal: "+MainActivity.alphaVal );
    		Log.w("ANGARA", "  ANGARA3 LENA2 csvLogFile: "+csvLogFile );
    		Log.w("ANGARA", "  ANGARA3 LENA22 DirectoryChooserDialog.delta: "+DirectoryChooserDialog.delta );
            // ----------------------- CSV -----------------------
    		if(!scorelogFile.exists()){
    			scorelogFile.createNewFile();
                copyHead(scorelogFile , MainActivity.scoreName ); //*****scoreName******	 
    		}//
    		if(!extLogFile.exists()){
    			extLogFile.createNewFile();
                copyHead(extLogFile , MainActivity.alphaName ); //*****alphaName******	                	                
    		}//
    		if(!csvLogFile.exists()){
    			csvLogFile.createNewFile();
                csvHeadCont+= "Overall_Score";	                
                copyHead(csvLogFile , csvHeadCont ); //*****csvLogFile******	                
                csvHeadCont="";	                	                	                
                Log.w("krasno ", " krasno csvHeadCont1: "+csvHeadCont);
    		}//
	        //***NR4************// Body-CSV //*******************************************N+4
            if( DirectoryChooserDialog.delta <5 ){
                csvValCont += overall;
                copyBody(csvLogFile , csvValCont ); //*****csvLogFile******	                
                copyBody(extLogFile , MainActivity.alphaVal ); //*****extLogFile******	                
                copyBody(scorelogFile , MainActivity.scoreVal ); //*****scorelogFile****
            } 
            
            //****************** score SQL-HEAD *********************************

            //??????????????????????????????????????????????????????????????????????
        	String pathRsaPub = rootA+"/4Survey/key/rsa/id_rsa.pub";
        	String pathRsaPubPem = rootA+"/4Survey/key/rsa/id_rsa.pub.pem";
        	String pathRsaPubMac = rootA+"/4Survey/key/rsa/id_rsa.ab.pub";        	
        	String pathPubPGP= rootA+"/4Survey/key/ABKEY.asc";
    		//readPublicKeyFromFile(pathRsaPubMac);	//BigTest	doesnt work
              		
           	
            //??        
            //??????????????????????????????????????????????????????????????????????
          	try {	} catch (Exception e2) {
        		// TODO Auto-generated catch block
        		e2.printStackTrace();
        	}
        	
            //****************** score SQL-HEAD *********************************
            if(!sv_set.exists()){
                String createSET= "CREATE TABLE IF NOT EXISTS `sv_set` ( `_ID` INT,`SER` TEXT,`TimeMS` REAL,`Date` datetime,`SName` TEXT );";
                sv_set.createNewFile();
                copySql(sv_set , createSET ); //*****Leon
                    sqIn+=createSET+"\n";
                    createSET="";
            }// 
            //*****NR9*********sv_history CREATE**********************************
            if(!sv_historyFileQ.exists()){
                String createHIST= "CREATE TABLE IF NOT EXISTS `sv_history` ( `_ID` INT, `SER` TEXT, `TimeMS` REAL, `Age` INTEGER, `Gender` TEXT, `RGroup` TEXT, `Study` TEXT, `Subject` TEXT, `Treatment` TEXT, `Repetition` TEXT, `Pharmacology` TEXT, `Dose` TEXT, `Comment` TEXT, `Date` datetime, `SName` TEXT, `Diagnose` TEXT, `Survay` TEXT, `Score` INTEGER);";	
                copySql(sv_historyFileQ , createHIST ); //*****Leon
                    sqIn+=createHIST+"\n";
                    createHIST="";
            }//
            //************************EEE******************************************
    		if(!scoreSQlogFile.exists()){
    			scoreSQlogFile.createNewFile();
                MainActivity.scoreDef = "CREATE TABLE IF NOT EXISTS `"+surveyFileName+"-ScoreInput` ("+"`_ID` INT,`SER` TEXT,`TimeMS` REAL,`Date` datetime,`SName` TEXT,`Survay` TEXT" + MainActivity.scoreDef+" );";
                copySql(scoreSQlogFile , MainActivity.scoreDef); // Write-Head	 
                    sqIn+=MainActivity.scoreDef+"\n";
                    MainActivity.scoreDef="";
    		}//
            //************************EEE******************************************
    		if(!extQLogFile.exists()){
    			extQLogFile.createNewFile();
                MainActivity.alphaDef = "CREATE TABLE IF NOT EXISTS `"+surveyFileName+"-ExtInput` ("+"`_ID` INT,`SER` TEXT,`TimeMS` REAL,`Date` datetime,`SName` TEXT,`Survay` TEXT" + MainActivity.alphaDef+" );";
                copySql(extQLogFile , MainActivity.alphaDef); // Write-Head	 
                    sqIn+=MainActivity.alphaDef+"\n";
                    MainActivity.alphaDef="";
    		}//
            //************************EEE******************************************
    		if(!stQlogFile.exists()){
    			stQlogFile.createNewFile();
                stSQL+="`Score` REAL);";
                copySql(stQlogFile, stSQL ); // Write-Head	 
                    sqIn+=stSQL+"\n";
                    stSQL ="";
    		}//
            if( DirectoryChooserDialog.delta <5 ){
                //******************// VALUES-SQL //*******************************************
                String firstSQL ="";
                csvValCont = csvValCont.replace(",", "','");
                firstSQL = "INSERT INTO `"+surveyFileName+"` VALUES ('"+csvValCont+"');";
                copyBody(stQlogFile , firstSQL ); //*****csvLogFile******	                
                    sqIn+=firstSQL+"\n";
                    firstSQL="";
                //******************// VALUES-SQL //*******************************************
                MainActivity.alphaIn = " INSERT INTO `"+surveyFileName+"-ExtInput` VALUES ("+MainActivity.alphaVal+" );";
                copySql(extQLogFile , MainActivity.alphaIn ); //*****
                    sqIn+=MainActivity.alphaIn+"\n";
                    MainActivity.alphaIn="";
                //******************// VALUES-SQL //*******************************************
                MainActivity.scoreIn = "  INSERT INTO `"+surveyFileName+"-ScoreInput` VALUES ("+MainActivity.scoreVal+" );";
                copySql(scoreSQlogFile , MainActivity.scoreIn ); //*****
                    sqIn+=MainActivity.scoreIn+"\n";
                    MainActivity.scoreIn="";
                //******************// VALUES-SQL //*******************************************
                MainActivity.alphaDef="";
                MainActivity.alphaName="";
                MainActivity.alphaVal="";
                //*********
                MainActivity.scoreDef="";
                MainActivity.scoreName="";
                MainActivity.scoreVal="";
                //***********************
                csvValCont ="";
                //MainActivity.strName="";
            } //End LoBeZman	    		
            i=0;

   		    Log.w("iiiC:", "iiiC: "+i); 		    
            //pritLogFile   		    
   			logErr+="Survey saved #1350, ";
			Toast toast = Toast.makeText(context,"Survey saved: "+surveyFileName+" | "+MainActivity.sname , Toast.LENGTH_LONG);
		    TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
			toast.setGravity(Gravity.TOP, 0, 0);
		    toast.getView().setBackgroundColor(Color.GREEN);
		    toast.getView().setPadding(10, 10, 10, 10);
		    text.setTextColor(Color.WHITE);
		    text.setTextSize(24); 
			toast.show();
            surveyFileName="";
		} catch (IOException e) {
			e.printStackTrace();
   			logErr+="Couldn't save Survey #1360, ";
			// Toast.makeText(context, "Couldn't save Survey", Toast.LENGTH_LONG).show();
			Toast toast = Toast.makeText(context,"Couldn't save Survey !!! : "+surveyFileName+" | "+MainActivity.sname , Toast.LENGTH_LONG);
		    TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
			toast.setGravity(Gravity.TOP, 0, 0);
		    toast.getView().setBackgroundColor(Color.RED);
		    toast.getView().setPadding(10, 10, 10, 10);
		    text.setTextColor(Color.WHITE);
		    text.setTextSize(24); 
			toast.show();
            surveyFileName="";
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
   			logErr+="Couldn't save Survey #1366, ";
			e.printStackTrace();
			Toast toast = Toast.makeText(context,"Couldn't save Survey #2!! : "+surveyFileName+" | " , Toast.LENGTH_LONG);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
   			logErr+="Couldn't save Survey #1371, ";
			e.printStackTrace();
			Toast toast = Toast.makeText(context,"Couldn't save Survey #3!! : "+surveyFileName+" | " , Toast.LENGTH_LONG);

		} 
		Log.w("historyIn ", "ssumS core1: "+historyIn);
		//***************************************//***************************************//
		//***************************************//***************************************//
		if( DirectoryChooserDialog.delta <5 ){
            //shlus stein ohne 
			historyIn= tBuf+overall;
			Log.w("historyIn ", "sun ssumS core2: "+historyIn);

			try {
				if(!historyFile.exists()){
					historyFile.createNewFile();
				}
                //****NR old gut history*************************************************** 
                copyBody(historyFile ,historyIn ); //*****
				sumScore=0;
				//=========================sv_historyFile================================//                           
                //**********************************************************************
	                String svh_Head="";
	                svh_Head+="_ID"; 
	                svh_Head+=";SER"; 
	                svh_Head+=";TimeMS"; 
	                svh_Head+=";Age";
	                svh_Head+=";Gender";
	                svh_Head+=";RGroup";
	                svh_Head+=";Study";
	                svh_Head+=";Subject";                
	                svh_Head+=";Treatment";
	                svh_Head+=";Repetition";
	                svh_Head+=";Comment";
	                svh_Head+=";Date";
	                svh_Head+=";Pharmacology";                
	                svh_Head+=";Dose";
	                svh_Head+=";SName";
	                svh_Head+=";Diagnose";
	                svh_Head+=";Survay";
	                svh_Head+=";Score";
	                Log.w("Seide", "7 spic svh_Head: "+svh_Head);
			    //************** Vals ********************************************
                    String svh_Vals="";
	                svh_Vals+="'"+MainActivity.theID+"'"+"," ; 
	                svh_Vals+="'"+DirectoryChooserDialog.iSER+"'"+","; 
	                svh_Vals+="'"+openreadcsv.timeMS+"'"+"," ;   
	                svh_Vals+="'"+MainActivity.intAgeIs+"'"+"," ;            
	                svh_Vals+="'"+MainActivity.strGenderIs+"'"+","    ;                
	                svh_Vals+="'"+MainActivity.strGroupIs+"'"+","     ;                
	                svh_Vals+="'"+MainActivity.strStudyIs+"'"+","     ;
	                svh_Vals+="'"+MainActivity.strSubjectIs+"'"+","   ;
	                svh_Vals+="'"+MainActivity.strTreatmentIs+"'"+"," ;
	                svh_Vals+="'"+MainActivity.strAgainIs+"'"+","     ;
	                svh_Vals+="'"+MainActivity.strPharmaIs+"'"+","    ;
	                svh_Vals+="'"+MainActivity.strDoseIs+"'"+","      ;	                
	                svh_Vals+="'"+MainActivity.strCommentIs+"'"+","   ;                
	                svh_Vals+="'"+DirectoryChooserDialog.iTimeString+"'"+",";
	                svh_Vals+="'"+filename+"'"+",";
	                svh_Vals+="'"+PatDign+"'"+",";
	                svh_Vals+="'"+surveyFileName+MainActivity.surveyName+"'"+",";
	                svh_Vals+="'"+overall+"'";
	                Log.w("Seide", "8 spic XX svh_Vals: "+svh_Vals);  
                //***NR5*********** HEAD sv_hist *********************************  
	            //***NR5*********** HEAD sv_hist *********************************            
    
				if(!sv_historyFile.exists()){
					sv_historyFile.createNewFile();
                    copyHead(sv_historyFile ,svh_Head ); //*****
	                Log.w("Seide", "10 spic napisano svh_Head: "+svh_Head);
				}//
                    copyBody(sv_historyFile , svh_Vals ); //*****
                    Log.w("Seide", "10B spic n2Info: ");             
	                String n2Info ="";
	                n2Info = "INSERT INTO `sv_history` VALUES ("+svh_Vals+");"; 
                    copySql(sv_historyFileQ , n2Info ); //*****Leon
					sqIn+=n2Info+"\n";
                    n2Info ="";
    			//****NR11****SQlight PAUSE ********************************************
    			//-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-                
                    Log.w("Seide", "11 spic vorher n2Info: "+n2Info);
				    String sts="";
				    DBHelper db = new DBHelper(context); //LeonLeon
                    // sts= db.qIn(n2Info, createHIST); // Problema Reshena <-------
          	        Log.w("Seide", "17 spic nacher END sts: "+sts );
				//-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB-DB
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logErr+="history write out err #912, ";
				e.printStackTrace();
			}
			
		}//end LoBeZman
		Log.w("logErr ", "logErr1: "+logErr);
 	    Log.w("Seide", "30 spic " ); 	
	   
			
	        try { 
			    if(!sv_qIn.exists()) { sv_qIn.createNewFile(); } //+++
	            copySql(sv_qIn , sqIn ); //+++Leon
	            sqIn=""; //ZERO
			} catch (IOException e) {
			  	logErr+="sqIn write out err #1848, ";
				e.printStackTrace();
			}//	
	        //***************************
			MainActivity.alphaDef = "";
			MainActivity.alphaName = "";
			MainActivity.alphaVal = "";
			//***
			MainActivity.scoreDef = "";
			MainActivity.scoreName = "";
			MainActivity.scoreVal = "";  
			//***
			MainActivity.surveyName = "";
	}// 111000 111000 111000 111000 111000 111000 111000 111000 111000 111000 
    // 111000 111000 111000 111000 111000 111000 111000 111000 111000 111000 
    //****NR10***********************************************************     
	    		        
        try {
        	Log.w("logErr ", "logErr2: "+logErr);
            String setSQL = "INSERT INTO `sv_set` VALUES ("+DirectoryChooserDialog.Shapka+",'"+MainActivity.sname+"');";	// YOUROPE	
			copySql( sv_set, setSQL  );
			sqIn+=setSQL+"\n";
	        setSQL="";
            sqIn="\n";

	        Log.w("mdr", "mdr YOUROPE SHAPKAa setSQL:  "+setSQL );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //*****Leon
        
	//**** NR12 ********* sqIN  ********************************************
        try { 
		    if(!sv_qIn.exists()) { sv_qIn.createNewFile(); } //+++
            sqIn+="\n";
            copySql(sv_qIn , sqIn ); //+++Leon
		} catch (IOException e) {
		  	logErr+="sqIn write out err #1563, ";
			e.printStackTrace();
		}//
		//**** NR13 ********* sqIN  ********************************************

	    try {
			deleteFiles(pathX); //dddd
		} catch (Exception e) {
			// TODO: handle exception
		}
		File oldFile = new File (srcPath);
		 File newFile = new File (tgtPath);		
		 try{
			  FileUtils.copyDirectoryToDirectory(oldFile, newFile);
			  //copyDirectory(oldFile, newFile);
		 } catch (IOException e) {
		     // TODO Auto-generated catch block
			 logErr+="copyDirectoryToDirectory err #1563, ";
		     e.printStackTrace();
		 }//END COPY
}
else{
	//Toast.makeText(context, R.string.pp_omni_file_not_accessible, Toast.LENGTH_SHORT).show();
	logErr+="File not accessible #1585, ";
	System.out.println("File not accessible");
}
	//**** NR14 ********* sqIN  ********************************************
		try {
			if(!printLogFile.exists()){ printLogFile.createNewFile(); }	//+++
            copyHead( printLogFile , logErr ); //*****Leon
		} catch (IOException e) {
			logErr+=" printLogFile err #1600, ";
			e.printStackTrace();
		}
		
current = 0;
}

//------------------------------/Y/-------------------------------------------------
//-----------------------------/YYY/------------------------------------------------
//--------------------------/FUNKTIONS/---------------------------------------------  
//-----------------------------/YYY/------------------------------------------------ 
/** 
 * read Public Key From File 
 * @param fileName 
 * @return PublicKey 
 * @throws IOException 
 */  
public PublicKey readPublicKeyFromFile(String fileName) throws IOException{  
 FileInputStream fis = null;  
 ObjectInputStream ois = null;  
	Log.w("PGP", "--- RSA 0001 ---------------- fileName "+fileName );

 try {  
    Log.w("PGP", "--- RSA 0002 ---------------- fileName "+fileName );

  fis = new FileInputStream(new File(fileName));  
  Log.w("PGP", "--- RSA 00021 ---------------- fileName "+fileName );

  ois = new ObjectInputStream(fis);  
  Log.w("PGP", "--- RSA 00022 ---------------- fileName "+fileName );
  
  BigInteger modulus = (BigInteger) ois.readObject();  
     BigInteger exponent = (BigInteger) ois.readObject();  
  	Log.w("PGP", "--- RSA 0003 ---------------- fileName "+fileName );

     //Get Public Key  
     RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent); 
  	Log.w("PGP", "--- RSA 0004 ---------------- fileName "+fileName );

     KeyFactory fact = KeyFactory.getInstance("RSA");  
  	Log.w("PGP", "--- RSA 0005 ---------------- fileName "+fileName );

     PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);  
     
 	Log.w("PGP", "--- RSA 0006 ---------------- fileName "+fileName );
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey); 
    String data ="frust botomup";
	Log.w("PGP", "--- RSA 0007 ---------------- data "+data );
	String encoData = Base64.encodeBase64String(cipher.doFinal(data.getBytes("UTF-8")));
	Log.w("PGP", "--- RSA 0008 ---------------- encoData "+encoData );

     return publicKey;  
       
 } catch (Exception e) {  
  e.printStackTrace();  
 }  
 finally{  
  if(ois != null){  
   ois.close();  
   if(fis != null){  
    fis.close();  
   }  
  }  
 }  
	Log.w("PGP", "--- RSA 0006 ---------------- fileName "+fileName );

 return null;  
}  
//** RSA **
public static PublicKey getRsaPub(String filename)
	    throws Exception {
	    File f = new File(filename);
	    FileInputStream fis = new FileInputStream(f);
	    DataInputStream dis = new DataInputStream(fis);
	    byte[] keyBytes = new byte[(int)f.length()];
	    dis.readFully(keyBytes);
	    dis.close();

	    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
		Log.w("PGP", "--- RSA kf1:  "+kf );
		Log.w("PGP", "--- RSA kf2:  "+kf.generatePublic(spec) );

	    return kf.generatePublic(spec);
	  }
//------------------------------/Y/-------------------------------------------------
public void copyBody(File  csvLogFile , String txtBuf ) throws IOException
{
	txtBuf = txtBuf.replace(";", ",");
	FileWriter fw = new FileWriter(csvLogFile,true);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(txtBuf);
	bw.newLine();
	bw.close();	                
	// Log.w("krasno ", " krasno csvValCont2: "+csvValCont);
}

public void copyHead(File  csvLogFile , String txtBuf ) throws IOException
{
	txtBuf = txtBuf.replace(",", "\",\"");
	txtBuf = txtBuf.replace(";", "\",\"");
    txtBuf = "\""+txtBuf+"\"";
	FileWriter fw = new FileWriter(csvLogFile,true);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(txtBuf);
	bw.newLine();
	bw.close();	                
	// Log.w("krasno ", " krasno csvValCont2: "+csvValCont);
}

public void copySql(File LogFile , String txtBuf ) throws IOException
{
	FileWriter fw = new FileWriter(LogFile,true);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(txtBuf);
	bw.newLine();
	bw.close();	                
	// Log.w("krasno ", " krasno csvValCont2: "+csvValCont);
}

//------------------------------ZZZ---------------------------------------
public boolean containsIgnoreCase( String haystack, String needle ) {
	  if(needle.equals(""))
	    return true;
	  if(haystack == null || needle == null || haystack .equals(""))
	    return false; 

	  Pattern p = Pattern.compile(needle,Pattern.CASE_INSENSITIVE+Pattern.LITERAL);
	  Matcher m = p.matcher(haystack);
	  return m.find();
	}

private Context getApplicationContext() {
	// TODO Auto-generated method stub
	return null;
}

//---copy-
public static void deleteFiles(String path) {

    File file = new File(path);

    if (file.exists()) {
        String deleteCmd = "rm -r " + path;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(deleteCmd);
        } catch (IOException e) { }
    }
}
//If targetLocation does not exist, it will be created.
public void copyDirectory(File sourceLocation , File targetLocation) throws IOException {

	 if (sourceLocation.isDirectory()) {
	     if (!targetLocation.exists() && !targetLocation.mkdirs()) {
	         throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
	     }
	
	     String[] children = sourceLocation.list();
	     for (int i=0; i<children.length; i++) {
	         copyDirectory(new File(sourceLocation, children[i]),
	                 new File(targetLocation, children[i]));
	     }
	 } else {	
	     // make sure the directory we plan to store the recording in exists
	     File directory = targetLocation.getParentFile();
	     if (directory != null && !directory.exists() && !directory.mkdirs()) {
	         throw new IOException("Cannot create dir " + directory.getAbsolutePath());
	     }
	
	     InputStream in = new FileInputStream(sourceLocation);
	     FileOutputStream out = new FileOutputStream(targetLocation);
	
	     // Copy the bits from instream to outstream
	     byte[] buf = new byte[1024];
	     int len;
	     while ((len = in.read(buf)) > 0) {
	         out.write(buf, 0, len);
	     }
	     in.close();
	     out.close();
	 }
	}
//***************

} // public class QuestionParser END


  
 
