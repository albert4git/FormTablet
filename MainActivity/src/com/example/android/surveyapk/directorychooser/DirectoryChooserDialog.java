// DirectoryChooserDialog.java

package com.example.android.surveyapk.directorychooser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.MainActivity;
import com.example.android.surveyapk.QuestionTypes.RadioButtonGroup;
import com.example.android.surveyapk.csv.openreadcsv;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Environment;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

public class DirectoryChooserDialog {
	private boolean m_isNewFolderEnabled = true;
	public static String m_sdcardDirectory = ""; // see ?
	private Context m_context;
	private TextView m_titleView;

	private String m_dir = "";
	private List<String> m_subdirs = null;
	private ChosenDirectoryListener m_chosenDirectoryListener = null;
	private ArrayAdapter<String> m_listAdapter = null;
	public long longPosledniePolzovanie;
	public static int loBeZmanAhad = 0;
	public static int delta = 0;
	public static long gamma = 0;
	public static String iSER = ""; // see
	public static int subSER = 0;
	public static String strRedSER;
	public static String Shapka = "";
	public static String iTimeString = "";

	// ////////////////////////////////////////////////////
	// Callback interface for selected directory
	// ////////////////////////////////////////////////////
	public interface ChosenDirectoryListener {

		public void onChosenDir(String chosenDir);
	}

	public DirectoryChooserDialog(Context context,
			ChosenDirectoryListener chosenDirectoryListener) {
		// **************************************************************************
		RadioButtonGroup.statBoxFlag = 0;
		RadioButtonGroup.statBoxSFlag = "BBB";
		// **************************************************************************
		Calendar now = Calendar.getInstance();
		String buildModel = android.os.Build.MODEL;
		String buildDev = android.os.Build.DEVICE;
		String buildCPU = android.os.Build.CPU_ABI;
		String buildDisplay = android.os.Build.DISPLAY;
		String buildFinger = android.os.Build.FINGERPRINT;
		String buildID = android.os.Build.ID;
		String buildSER = android.os.Build.SERIAL;

		Log.w("555:", " iser 1234 buildSER: " + buildSER);
		// String iFinger =
		// "Lenovo/LenovoS6000L-F/S6000L:4.2.2/JDQ39/S6000L_A422_001_029_130923_WW_:user/release-keys";
		String iModel = "S6000L";// Lenovo
		String iID = "JDQ39"; // Valeri1

		iSER = "Q4E9KOHHHJ"; // xoro

		iSER = "KN65QOWW9TSC5DQG"; // Valeri2
		iSER = "PNLZEAKRJVJVCYZT"; // Valeri1
		iSER = "93d6a68807c4f1c8"; // GCBS tDCS samsung
		iSER = "8ed02d3207ca75df"; // samsung weiss HB1 // origin
		iSER = "93d6a68807c4f1c8"; // GCBS tDCS samsung
		iSER = "YT55LFDMTKKJMRZ9"; // wolf 93d6a68807c4f1c8
		iSER = "8ed02d3207ca75df"; // samsung weiss HB1 // origin
		iSER = "93d6a68807c4f1c8"; // GCBS tDCS samsung
		iSER = "KN65QOWW9TSC5DQG"; // Valeri2
		iSER = "93d6a68807c4f1c8"; // GCBS tDCS samsung
		iSER = "8ed02d3207ca75df"; // samsung weiss green HB1 // origin
		iSER = "a5e816e0077bb150"; // NeuroImmuno 2 8ed02d3207ca75df
		iSER = "8ed02d3207ca75df"; // samsung weiss HB1 // origin
		iSER = "PNLZEAKRJVJVCYZT"; // Valeri1
		iSER = "8ed02d3207ca75df"; // samsung weiss green HB1 // origin

		iSER = "a5e816e0077bb150"; // NeuroImmuno 2 8ed02d3207ca75df DeepPurpel
		iSER = "YT55LFDMTKKJMRZ9"; // wolf 93d6a68807c4f1c8
		iSER = "8ed02d3207ca75df"; // samsung weiss green HB1 // origin

		int count = 0;
		int vowels = 0;
		int aeiou = 0;
		int BCDFG = 0;
		int HJKLMN = 0;
		int PQRST = 0;
		int VWXYZ = 0;
		int numb = 0;

		String lower = iSER.toLowerCase();
		for (int i = 0; i < lower.length(); i++) {
			char ch = lower.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				aeiou++;
			}
			if (ch == 'b' || ch == 'c' || ch == 'd' || ch == 'f' || ch == 'g') {
				BCDFG++;
			}
			if (ch == 'h' || ch == 'j' || ch == 'k' || ch == 'l' || ch == 'm'
					|| ch == 'n') {
				HJKLMN++;
			}
			if (ch == 'p' || ch == 'q' || ch == 'r' || ch == 's' || ch == 't') {
				PQRST++;
			}
			if (ch == 'v' || ch == 'w' || ch == 'x' || ch == 'y' || ch == 'z') {
				VWXYZ++;
			}
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4'
					|| ch == '5' || ch == '6' || ch == '7' || ch == '8'
					|| ch == '9') {
				numb++;
			}
		} // end for
		String strSER = iSER.replaceAll("[a-zA-Z]", "");
		Long timeB = Long.valueOf(strSER);

		String bSER = "";
		Log.w("xxx", "spez strSER: " + strSER);
		bSER += aeiou;
		Log.w("xxx", "spez strSER: " + aeiou);
		bSER += BCDFG;
		Log.w("xxx", "spez strSER: " + BCDFG);
		bSER += HJKLMN;
		Log.w("xxx", "spez strSER: " + HJKLMN);
		bSER += PQRST;
		Log.w("xxx", "spez strSER: " + PQRST);
		bSER += VWXYZ;
		Log.w("xxx", "spez strSER: " + VWXYZ);
		bSER += numb;
		strRedSER = bSER;
		Log.w("xxx", "spez strSER: " + bSER);
		Log.w("xxx", "spez strRedSER: " + strRedSER);

		int iYear = now.get(Calendar.YEAR);
		int iMonth = now.get(Calendar.MONTH);
		// VAJNO
		m_context = context; // VAJNO
		// ========================================================================================
		// mnsk2
		// ======BLAU===================================BLAU===================================
		try {
			String text;
			java.io.InputStream is = context.getAssets().open("test.txt");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			text = new String(buffer);
			Log.w("blau", " blau line: " + text); // RABOTAET Blau //
			// ******
			File rootA = Environment.getExternalStorageDirectory(); // getExternalStorageDirectory();
			String srcPath = rootA + "/4Survey/";
			String tgtPath = rootA + "/documents/";
			File oldFile = new File(srcPath);
			File newFile = new File(tgtPath);
			FileOutputStream fos = new FileOutputStream(rootA
					+ "/documents/abb.txt");
			fos.write(buffer); // BLAU Rabotaet //
			fos.close();
			is.close();
			// ****************************************************************
			// m_sdcardDirectory = new
			// File(m_sdcardDirectory).getCanonicalPath();
			AssetManager ASM = context.getAssets();
			String toPath = "/data/data/" + context.getPackageName() + "/base";
			Log.w("blau1", " blau1 toPath: " + toPath); // RABOTAET Blau //

			// copyAssetFolder( context, ASM, "base" , rootA+"/documents/bas/");

			String path = rootA + "/4Survey";
			boolean exists = (new File(path).exists());
			if (!exists) {
				new File(path).mkdirs();
				copyAssetFolder(context, ASM, "4Survey", rootA + "/4Survey");
				Log.w("red", " red No!! Path: " + path); // RABOTAET Blau //
			} else {
				Log.w("red", " red Yes!! Path: " + path); // RABOTAET Blau //
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// ======EndBLAU===============================EndBLAU===================================

		// ******* start BORIS SHAPKA
		// ******************************************************
		// ** readcsv.longKonezRibalki
		// ** long timeNow= System.currentTimeMillis() ;
		m_sdcardDirectory = "4Survey";
		long wii;
		openreadcsv readCSV = null;
		wii = readCSV.readcsv(iSER, m_sdcardDirectory);
		delta = readCSV.delta;
		gamma = readCSV.sVersion;
		String tID = "";
		String ltID = "";
		tID = openreadcsv.strTimeS + strRedSER;
		ltID = openreadcsv.strTimeS + strRedSER + "L";
		Log.w("Seide", "3 spic YY tID:" + tID);
		Log.w("Seide", "4 spic YY ltID:" + ltID);
		MainActivity.theID = Long.valueOf(tID); // ORANGE

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		iTimeString = year + "-" + month + "-" + day;
		iTimeString = year + "-" + month + "-" + day + " " + hours + ":"
				+ minutes + ":" + seconds;

		Shapka = "";
		Shapka += "'" + MainActivity.theID + "'";
		Shapka += ",'" + DirectoryChooserDialog.iSER + "'";
		Shapka += ",'" + openreadcsv.timeMS + "'";
		Shapka += ",'" + DirectoryChooserDialog.iTimeString + "'";
		// Shapka+=",'Gde"+MainActivity.strName+"Imia'";
		Log.w("shapka", " angara1, shapka: " + Shapka); // RABOTAET Blau //

		Log.w("blau1", " blau1 SuperID MainActivity.theID: "
				+ MainActivity.theID); // RABOTAET Blau //

		// **
		// ***********SOF end log BORIS
		// *****************************************************
		// bemet
		// if (iYear < 2016 && buildSER.equals(iSER) )

		if (iYear < 2016) {
			// Toast.makeText(context,"Your Model is:" + buildModel +" Dev:"+
			// buildDev , Toast.LENGTH_LONG).show();
			m_sdcardDirectory = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/4Survey/inbox";

		} else {
			m_sdcardDirectory = Environment.getRootDirectory()
					.getAbsolutePath()
					+ "/err/-Please-update-your-software-license.msg* ";
		}// end else //

		m_chosenDirectoryListener = chosenDirectoryListener;
		try {
			m_sdcardDirectory = new File(m_sdcardDirectory).getCanonicalPath();
		} catch (IOException ioe) {// msg
		}

		File rootA = Environment.getExternalStorageDirectory(); // getExternalStorageDirectory();
		String tgtPath = rootA + "/documents/aaa/";
		File newFile = new File(tgtPath);
		// ?? transferFileToPrivateFS(newFile, "myimage.jpg",
		// context.getAssets());
		// ---END of ASSETS---
	}// End DirectoryChooserDialog

	// ****************************BORIS2 **********************************

	// /////////////////////////////////////////////////////////////////////
	// setNewFolderEnabled() - enable/disable new folder button
	// /////////////////////////////////////////////////////////////////////

	public void setNewFolderEnabled(boolean isNewFolderEnabled) {
		m_isNewFolderEnabled = isNewFolderEnabled;
	}

	public boolean getNewFolderEnabled() {
		return m_isNewFolderEnabled;
	}

	// /////////////////////////////////////////////////////////////////////
	// chooseDirectory() - load directory chooser dialog for initial
	// default sdcard directory
	// /////////////////////////////////////////////////////////////////////

	public void chooseDirectory() {
		// Initial directory is sdcard directory
		chooseDirectory(m_sdcardDirectory);
	}

	// //////////////////////////////////////////////////////////////////////////////
	// chooseDirectory(String dir) - load directory chooser dialog for initial
	// input 'dir' directory
	// //////////////////////////////////////////////////////////////////////////////

	public void chooseDirectory(String dir) {
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			dir = m_sdcardDirectory;
		}

		try {
			dir = new File(dir).getCanonicalPath();
		} catch (IOException ioe) {
			return;
		}

		m_dir = dir;
		m_subdirs = getDirectories(dir);

		class DirectoryOnClickListener implements
				DialogInterface.OnClickListener {
			public void onClick(DialogInterface dialog, int item) {
				// Navigate into the sub-directory
				m_dir += "/"
						+ ((AlertDialog) dialog).getListView().getAdapter()
								.getItem(item);
				updateDirectory();
			}
		}

		AlertDialog.Builder dialogBuilder = createDirectoryChooserDialog(dir,
				m_subdirs, new DirectoryOnClickListener());

		dialogBuilder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Current directory chosen
				if (m_chosenDirectoryListener != null) {
					// Call registered listener supplied with the chosen
					// directory
					m_chosenDirectoryListener.onChosenDir(m_dir);
				}
			}
		}).setNegativeButton("Cancel", null);

		final AlertDialog dirsDialog = dialogBuilder.create();

		dirsDialog.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK
						&& event.getAction() == KeyEvent.ACTION_DOWN) {
					// Back button pressed
					if (m_dir.equals(m_sdcardDirectory)) {
						// The very top level directory, do nothing
						return false;
					} else {
						// Navigate back to an upper directory
						m_dir = new File(m_dir).getParent();
						updateDirectory();
					}

					return true;
				} else {
					return false;
				}
			}
		});

		// Show directory chooser dialog
		try {
			dirsDialog.show();
		} catch (Exception e) {
			System.out.println("error in popup");
		}

	}

	private boolean createSubDir(String newDir) {

		File newDirFile = new File(newDir);
		if (!newDirFile.exists()) {
			return newDirFile.mkdir();
		}

		return false;
	}

	private List<String> getDirectories(String dir) {
		List<String> dirs = new ArrayList<String>();

		try {
			File dirFile = new File(dir);
			if (!dirFile.exists() || !dirFile.isDirectory()) {
				return dirs;
			}

			for (File file : dirFile.listFiles()) {
				if (file.isDirectory()) {
					dirs.add(file.getName());
				}
			}
		} catch (Exception e) {
		}

		Collections.sort(dirs, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		return dirs;
	}

	private AlertDialog.Builder createDirectoryChooserDialog(String title,
			List<String> listItems,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(m_context);

		// Create custom view for AlertDialog title containing
		// current directory TextView and possible 'New folder' button.
		// Current directory TextView allows long directory path to be wrapped
		// to multiple lines.
		LinearLayout titleLayout = new LinearLayout(m_context);

		titleLayout.setOrientation(LinearLayout.VERTICAL);

		// titleLayout.setBackgroundColor(Color.BLACK);
		titleLayout.setBackgroundColor(Color.DKGRAY); // Nur select window head
		titleLayout.setBackgroundResource(R.drawable.border2); // SUPER oder

		m_titleView = new TextView(m_context);
		m_titleView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		m_titleView.setTextAppearance(m_context,
				android.R.style.TextAppearance_Large);
		m_titleView.setTextColor(m_context.getResources().getColor(
				android.R.color.white));
		m_titleView.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);
		m_titleView.setText(title);

		Button newDirButton = new Button(m_context);

		newDirButton.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		newDirButton.setText("New folder");
		newDirButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText input = new EditText(m_context);
				Log.w("CVP setOnSwipeOutListener",
						"CVP HoerZu3 onSwipeOut 1001 CustomViewPager DDD");

				// Show new folder name input dialog
				new AlertDialog.Builder(m_context)
						.setTitle("New folder name")
						.setView(input)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int whichButton) {
										Log.w("CVP setOnSwipeOutListener",
												"CVP HoerZu1 onSwipeOut 1001 CustomViewPager DDD");

										Editable newDir = input.getText();
										String newDirName = newDir.toString();
										// Create new directory
										if (createSubDir(m_dir + "/"
												+ newDirName)) {
											// Navigate into the new directory
											m_dir += "/" + newDirName;
											updateDirectory();
										} else {
											// Chosen Dir ??
											// Toast.makeText(m_context,
											// "Failed to create '" + newDirName
											// + "' folder",
											// Toast.LENGTH_SHORT).show();
										}
									}
								}).setNegativeButton("Cancel", null).show();
			}
		});

		if (!m_isNewFolderEnabled) {
			newDirButton.setVisibility(View.GONE);
		}

		titleLayout.addView(m_titleView);
		titleLayout.addView(newDirButton);

		dialogBuilder.setCustomTitle(titleLayout);

		m_listAdapter = createListAdapter(listItems);
		Log.w("CVP setOnSwipeOutListener",
				"CVP HoerZu4 onSwipeOut 1001 CustomViewPager DDD");

		dialogBuilder.setSingleChoiceItems(m_listAdapter, -1, onClickListener);
		dialogBuilder.setCancelable(false);

		return dialogBuilder;
	}

	private void updateDirectory() {
		m_subdirs.clear();
		m_subdirs.addAll(getDirectories(m_dir));
		m_titleView.setText(m_dir);

		m_listAdapter.notifyDataSetChanged();
	}

	private ArrayAdapter<String> createListAdapter(List<String> items) {
		return new ArrayAdapter<String>(m_context,
				android.R.layout.select_dialog_item, android.R.id.text1, items) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView, parent);

				if (v instanceof TextView) {
					// Enable list item (directory) text wrapping
					TextView tv = (TextView) v;
					tv.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
					tv.setEllipsize(null);
				}
				return v;
			}
		};
	}

	// =============================4BLAU===========================================================================
	// //
	private static boolean copyAssetFolder(Context context,
			AssetManager assetManager, String fromAssetPath, String toPath) {
		try {
			Log.w("blau", " blau2 toPath: " + toPath); // RABOTAET Blau //
			Log.w("blau", " blau2 fromAssetPath: " + fromAssetPath); // RABOTAET
																		// Blau
																		// //

			String[] files = assetManager.list(fromAssetPath);
			new File(toPath).mkdirs();
			boolean res = true;
			for (String file : files)
				if (file.contains("."))
					res &= copyAsset(context, assetManager, fromAssetPath + "/"
							+ file, toPath + "/" + file);
				else
					res &= copyAssetFolder(context, assetManager, fromAssetPath
							+ "/" + file, toPath + "/" + file);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}// end

	private static boolean copyAsset(Context context,
			AssetManager assetManager, String fromAssetPath, String toPath) {
		java.io.InputStream in = null;
		FileOutputStream out = null;
		Log.w("blau", " blau3 toPath: " + toPath); // RABOTAET Blau //
		Log.w("blau", " blau3 fromAssetPath: " + fromAssetPath); // RABOTAET
																	// Blau //
		try {
			in = context.getAssets().open(fromAssetPath);
			// in = context.assetManager.open(fromAssetPath);
			new File(toPath).createNewFile();
			out = new FileOutputStream(toPath);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void copyFile(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		Log.w("blau", " blau4 toPath: "); // RABOTAET Blau //
		Log.w("blau", " blau4 fromAssetPath: "); // RABOTAET Blau //
		while ((read = in.read(buffer)) != -1) {
			Log.w("blau", " blau5 toPath: "); // RABOTAET Blau //
			Log.w("blau", " blau5 fromAssetPath: "); // RABOTAET Blau //
			out.write(buffer, 0, read);
		}
	}

	// =============================4BLAU===========================================================================
	private void copyAssets() {
	}

	private static void copyFile(java.io.InputStream inputStream,
			FileOutputStream out) throws IOException {
		Log.w("blau", " blau6 toPath: "); // RABOTAET Blau //
		Log.w("blau", " blau6 fromAssetPath: "); // RABOTAET Blau //
		byte[] buffer = new byte[1024];
		int read;
		while ((read = inputStream.read(buffer)) != -1) {
			Log.w("blau", " blau7 toPath: "); // RABOTAET Blau //
			Log.w("blau", " blau7 fromAssetPath: "); // RABOTAET Blau //
			out.write(buffer, 0, read);
		}
	}

	/**
	 * 
	 * @param base
	 * @param assetFileName
	 *            filename of the file in the assets folder
	 * @param res
	 * @throws IOException
	 */
	public static void transferFileToPrivateFS(File base, String assetFileName,
			Resources res) throws IOException {
		AssetManager am = res.getAssets();
		if (!base.exists()) {
			base.mkdir();
		}
		if (base.exists()) {
			File file = new File(base, assetFileName);
			if (!file.exists()) {
				copyFile(am.open(assetFileName), new FileOutputStream(file));
			}
		}
	}

	static void copy2(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[0xFFFF];
		for (int len; (len = in.read(buffer)) != -1;)
			out.write(buffer, 0, len);
	}

	// =========================END4BLAU===============================================================================

}