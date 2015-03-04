// DirectoryChooserDialog.java

package com.example.android.effectivenavigation.directorychooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.android.effectivenavigation.MainActivity;
import com.example.android.effectivenavigation.R;
import com.example.android.effectivenavigation.QuestionTypes.RadioButtonGroup;
import com.lowagie.text.pdf.codec.Base64.InputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
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

public class DirectoryChooserDialog 
{
    private boolean m_isNewFolderEnabled = true;
    private String m_sdcardDirectory = "";
    private Context m_context;
    private TextView m_titleView;
  
    private String m_dir = "";
    private List<String> m_subdirs = null;
    private ChosenDirectoryListener m_chosenDirectoryListener = null;
    private ArrayAdapter<String> m_listAdapter = null;
    public static long longLastTime;
    public static long longNowTime;
    public static int timeLastFlag=0;

    //////////////////////////////////////////////////////
    // Callback interface for selected directory
    //////////////////////////////////////////////////////
    public interface ChosenDirectoryListener 
    {
   
        public void onChosenDir(String chosenDir);
    }

    public DirectoryChooserDialog(Context context, ChosenDirectoryListener chosenDirectoryListener)
    {
        //**************************************************************************
        RadioButtonGroup.statBoxFlag = 0;
        RadioButtonGroup.statBoxSFlag = "BBB";
        //**************************************************************************
     	///
       	Calendar now = Calendar.getInstance();
    	int iYear=now.get(Calendar.YEAR);	
    	//public String getDeviceName() 
    	String buildModel =android.os.Build.MODEL;
    	String buildDev =android.os.Build.DEVICE;
    	String buildCPU =android.os.Build.CPU_ABI; 
    	String buildDisplay =android.os.Build.DISPLAY; 
    	String buildFinger =android.os.Build.FINGERPRINT;        
    	String buildID =android.os.Build.ID;   
    	String buildSER =android.os.Build.SERIAL;    
	    Log.w("555:", " iser 1234 buildSER: "+buildSER);

    	// String iFinger = "Lenovo/LenovoS6000L-F/S6000L:4.2.2/JDQ39/S6000L_A422_001_029_130923_WW_:user/release-keys";
    	// String iDisplay = "S6000L_A422_001_029_130923_WW_WiFi"; // Wolfgang
    	String iModel = "S6000L";// Lenovo
    	String iID = "JDQ39"; // Valeri1
    	String iSER = ""; //

    	//  iSER = "PNLZEAKRJVJVCYZT"; // Valeri1
    	//  iSER = "KN65QOWW9TSC5DQG"; // Valeri2    
    	//  iSER = "KN65QOWW9TSC5DQG"; // Valeri2    
    	//  iSER = "8ed02d3207ca75df"; // samsung weiss
    	//  iSER = "YT55LFDMTKKJMRZ9"; // samsung weiss
        iSER ="YT55LFDMTKKJMRZ9"; //wolfg
     	iSER = "8ed02d3207ca75df"; // samsung weiss

    	
    	// iSER= hd_nbr;
    //+++++++++++++TicTac2Start+++++++++++++++++++++++++++
		File rootL = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
		String pathL = rootL+"/SurveyResults/";
		String path2L = pathL;
		File xxlogFileL = new File(pathL+"log.csv");   
    	long longFirstTime=1424925425721L;
    	long longFinishTime = 1454367600000L;
		Log.w("line ...", "TicTac lineXXX read !!!pathL: "+pathL); // sentence.replace("and", " ");

    //------------------------------------

			//##########################################		
	        //File file2 = new File(context.getExternalFilesDir(null), "DayTwentyTwoFileTwo");	
					// get the path to sdcard
					File sdcard = Environment.getExternalStorageDirectory();
					// to this path add a new directory path
					//File dir = new File(sdcard.getAbsolutePath() + "/1Survey/"); mnt/extSdCard/
					
					File dir = new File("/mnt/extSdCard" + "/1Survey/"); //mnt/extSdCard/

					// create this directory if not already created
					dir.mkdir();
					String data = "This is the content of my file";
					// create the file in which we will write the contents
					File file2 = new File(dir, "log.csv");
					FileOutputStream os;
					try {
						os = new FileOutputStream(file2);
						try {
							os.write(data.getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							os.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	

	        
   //******READ****TicTac2-READ-LAST-Time********READ*************************************************
			File file = new File(pathL+"log.csv");
			//Read text from file
			StringBuilder text = new StringBuilder();

			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line;	    
	            int fi=0;
			    while ((line = br.readLine()) != null) {
			        //text.append(line);
			        //text.append('\n');
			        line = line.replace("\"", "");		        
					   Log.w("line ...", "TicTac line read line: "+line);
					longLastTime = Long.parseLong(line);
					   Log.w("line ...", "TicTac line read longLastTime: "+longLastTime);
					   

	            fi ++;
			    }
			    br.close();
			}
			catch (IOException e) {
			    //You'll need to add proper error handling here
			}
			
   //+++++++++++++++TicTac2+Check-NOW++++++++++++++++++++++++++++++++++++++++++++++++++
    	   long time= System.currentTimeMillis() ;
		   Date cDate = new Date(time);
		   String nowTimeMs =""; 
		   nowTimeMs+=Long.toString(time); // the LastTimeMs in Ms
		   Log.w("line ...", " theline TicTac line LastTimeMs: "+nowTimeMs);

			if ((longLastTime > longNowTime) && ( longNowTime < longFinishTime) ){
				timeLastFlag=0;
        		Toast.makeText(context, "Check-YES- longLastTime:"+longLastTime, Toast.LENGTH_LONG).show();
				//Log.w("line ...", "Check-YES- TicTac line read line: longLastTime:"+longLastTime);

				try {
					if(!xxlogFileL.exists()){
						xxlogFileL.createNewFile();
					}
		
					CSVWriter writer15 = new CSVWriter(new FileWriter(xxlogFileL, true));
					String [] record15 = nowTimeMs.split(";");
					writer15.writeNext(record15);
					writer15.close();
					//=====iii====//
					

							
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
        		Toast.makeText(context, "Check-No- longLastTime:"+longLastTime, Toast.LENGTH_LONG).show();
                // licence TEST XXX 
				// timeLastFlag=1;
				timeLastFlag=1;

			}

	//***********WRITE****************************************************************	

	
   
		//***********END-TIME****************************************************************

    	
        m_context = context;
        // File file = new File("res/raw/textfile.txt");
        // InputStream ins = getResources().openRawResource(R.raw.my_db_file);
        //========================================================================================
     	//if (iYear < 2016  && buildSER.equals(iSER) && buildFinger.contains(iID) && buildFinger.contains(iModel) )    {   
        // if (iYear < 2016  )    {   

     	if (iYear < 2016  && buildSER.equals(iSER) )    {   
 	
        	// Toast.makeText(context,"Your Model is:" + buildModel +" Dev:"+ buildDev , Toast.LENGTH_LONG).show();
            m_sdcardDirectory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/All_Surveys";
        } else  {
            // m_sdcardDirectory = Environment.getRootDirectory().getAbsolutePath()+"/lost+found";
            m_sdcardDirectory = Environment.getRootDirectory().getAbsolutePath()+"/media/Please-update-your-software-license.msg";
    		//Toast.makeText(context, "Please update your software license! ", Toast.LENGTH_LONG).show();
        	// Toast.makeText(context,"Your Model is: ID" + buildID +" SER:"+ buildSER , Toast.LENGTH_LONG).show();

    	 }//end else //
        
    	//========================================================================================
        //m_sdcardDirectory = "res/raw";
        m_chosenDirectoryListener = chosenDirectoryListener;
        try
        {
            m_sdcardDirectory = new File(m_sdcardDirectory).getCanonicalPath();
        }
        catch (IOException ioe)
        {// msg
        }

    }

    ///////////////////////////////////////////////////////////////////////
    // setNewFolderEnabled() - enable/disable new folder button
    ///////////////////////////////////////////////////////////////////////

    public void setNewFolderEnabled(boolean isNewFolderEnabled)
    {
        m_isNewFolderEnabled = isNewFolderEnabled;
    }

    public boolean getNewFolderEnabled()
    {
        return m_isNewFolderEnabled;
    }

    ///////////////////////////////////////////////////////////////////////
    // chooseDirectory() - load directory chooser dialog for initial
    // default sdcard directory
    ///////////////////////////////////////////////////////////////////////

    public void chooseDirectory()
    {
        // Initial directory is sdcard directory
        chooseDirectory(m_sdcardDirectory);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // chooseDirectory(String dir) - load directory chooser dialog for initial 
    // input 'dir' directory
    ////////////////////////////////////////////////////////////////////////////////

    public void chooseDirectory(String dir)
    {
        File dirFile = new File(dir);
        if (! dirFile.exists() || ! dirFile.isDirectory())
        {
            dir = m_sdcardDirectory;
        }

        try
        {
            dir = new File(dir).getCanonicalPath();
        }
        catch (IOException ioe)
        {
            return;
        }

        m_dir = dir;
        m_subdirs = getDirectories(dir);

        class DirectoryOnClickListener implements DialogInterface.OnClickListener
        {
            public void onClick(DialogInterface dialog, int item) 
            {
                // Navigate into the sub-directory
                m_dir += "/" + ((AlertDialog) dialog).getListView().getAdapter().getItem(item);
                updateDirectory();
            }
        }

    AlertDialog.Builder dialogBuilder = 
    createDirectoryChooserDialog(dir, m_subdirs, new DirectoryOnClickListener());

    dialogBuilder.setPositiveButton("OK", new OnClickListener() 
    {
        @Override
        public void onClick(DialogInterface dialog, int which) 
        {
            // Current directory chosen
            if (m_chosenDirectoryListener != null)
            {
                // Call registered listener supplied with the chosen directory
                m_chosenDirectoryListener.onChosenDir(m_dir);
            }
        }
    }).setNegativeButton("Cancel", null);

    final AlertDialog dirsDialog = dialogBuilder.create();

    dirsDialog.setOnKeyListener(new OnKeyListener() 
    {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) 
        {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
            {
                // Back button pressed
                if ( m_dir.equals(m_sdcardDirectory) )
                {
                    // The very top level directory, do nothing
                    return false;
                }
                else
                {
                    // Navigate back to an upper directory
                    m_dir = new File(m_dir).getParent();
                    updateDirectory();
                }
    
                return true;
            }
            else
            {
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

private boolean createSubDir(String newDir)
{

	
    File newDirFile = new File(newDir);
    if (! newDirFile.exists() )
    {
        return newDirFile.mkdir();
    }

    return false;
}

private List<String> getDirectories(String dir)
{
    List<String> dirs = new ArrayList<String>();

    try
    {
        File dirFile = new File(dir);
        if (! dirFile.exists() || ! dirFile.isDirectory())
        {
            return dirs;
        }
 
        for (File file : dirFile.listFiles()) 
        {
            if ( file.isDirectory() )
            {
                dirs.add( file.getName() );
            }
        }
    }
    catch (Exception e)
    {
    }

    Collections.sort(dirs, new Comparator<String>()
    {
        public int compare(String o1, String o2) 
        {
            return o1.compareTo(o2);
        }
    });

    return dirs;
}

private AlertDialog.Builder createDirectoryChooserDialog(String title, List<String> listItems,
        DialogInterface.OnClickListener onClickListener)
{
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(m_context);

    // Create custom view for AlertDialog title containing 
    // current directory TextView and possible 'New folder' button.
    // Current directory TextView allows long directory path to be wrapped to multiple lines.
    LinearLayout titleLayout = new LinearLayout(m_context);
    titleLayout.setOrientation(LinearLayout.VERTICAL);
    // titleLayout.setBackgroundColor(Color.BLACK);
       titleLayout.setBackgroundColor(Color.DKGRAY); // Nur select window head 
       titleLayout.setBackgroundResource(R.drawable.border2); //SUPER oder




    m_titleView = new TextView(m_context);
    m_titleView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    m_titleView.setTextAppearance(m_context, android.R.style.TextAppearance_Large);
    m_titleView.setTextColor( m_context.getResources().getColor(android.R.color.white) );
    m_titleView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    m_titleView.setText(title);

    Button newDirButton = new Button(m_context);
    
    newDirButton.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    newDirButton.setText("New folder");
    newDirButton.setOnClickListener(new View.OnClickListener() 
    {
        @Override
        public void onClick(View v) 
        {
            final EditText input = new EditText(m_context);
            Log.w("CVP setOnSwipeOutListener", "CVP HoerZu3 onSwipeOut 1001 CustomViewPager DDD");

            // Show new folder name input dialog
            new AlertDialog.Builder(m_context).
            setTitle("New folder name").
            setView(input).setPositiveButton("OK", new DialogInterface.OnClickListener() 
            {
            	
                public void onClick(DialogInterface dialog, int whichButton) 
                {   
                	Log.w("CVP setOnSwipeOutListener", "CVP HoerZu1 onSwipeOut 1001 CustomViewPager DDD");

                    Editable newDir = input.getText();
                    String newDirName = newDir.toString();
                    // Create new directory
                    if ( createSubDir(m_dir + "/" + newDirName) )
                    {
                        // Navigate into the new directory
                        m_dir += "/" + newDirName;
                        updateDirectory();
                    }
                    else
                    {
                    	// Chosen Dir ??
                        // Toast.makeText(m_context, "Failed to create '" + newDirName +    "' folder", Toast.LENGTH_SHORT).show();
                    }
                }
            }).setNegativeButton("Cancel", null).show(); 
        }
    });

    
    
    if (! m_isNewFolderEnabled)
    {
        newDirButton.setVisibility(View.GONE);
    }


    titleLayout.addView(m_titleView);
    titleLayout.addView(newDirButton);

    dialogBuilder.setCustomTitle(titleLayout);

    m_listAdapter = createListAdapter(listItems);
    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu4 onSwipeOut 1001 CustomViewPager DDD");

    dialogBuilder.setSingleChoiceItems(m_listAdapter, -1, onClickListener);
    dialogBuilder.setCancelable(false);

    return dialogBuilder;
}

private void updateDirectory()
{
    m_subdirs.clear();
    m_subdirs.addAll( getDirectories(m_dir) );
    m_titleView.setText(m_dir);

    m_listAdapter.notifyDataSetChanged();
}

private ArrayAdapter<String> createListAdapter(List<String> items)
{
    return new ArrayAdapter<String>(m_context, 
      android.R.layout.select_dialog_item, android.R.id.text1, items)
    {
        @Override
        public View getView(int position, View convertView,
        ViewGroup parent) 
        {
            View v = super.getView(position, convertView, parent);

            if (v instanceof TextView)
            {
                // Enable list item (directory) text wrapping
                TextView tv = (TextView) v;
                tv.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
                tv.setEllipsize(null);
            }
            return v;
        }
    };
}
} 