/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.effectivenavigation;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;



import com.example.android.effectivenavigation.QuestionTypes.Mydata;
import com.example.android.effectivenavigation.directorychooser.DirectoryChooserDialog;
import com.example.android.effectivenavigation.directorychooser.pdf;




import android.R.layout;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity{
	private static final int REQUEST_CODE = 6384; // onActivityResult request code
	private EditText subjectName;
	private EditText subjectDign;



    public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        //Creation theory !!!
        //setContentView(R.layout.activity_collection_demo);

          setContentView(R.layout.fragment_section_launchpad);
		
      // setContentView(R.layout.my_table);
        // final TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        // setContentView(R.layout.setBackgroundColor(Color.RED));
        // set the background to green     
        // final ActionBar actionBar = getActionBar();
        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        // actionBar.setHomeButtonEnabled(false);
        // Demonstration of a collection-browsing activity.
        
       
        LinearLayout l=(LinearLayout) findViewById(R.id.mainlayout);
        l.setBackgroundColor(Color.DKGRAY);
        TextView label= new TextView(this);

       
        Evaluator evaluator = new Evaluator();
        String result=null;
        evaluator.putVariable("a", "2");
        try {
			result=evaluator.evaluate("#{a}*5");
		} catch (EvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        label.setText(result);
        l.addView(label);
        //-----check-------

    	
        subjectName = (EditText) findViewById(R.id.name);
        subjectDign = (EditText) findViewById(R.id.dign);
   
        Button btn=(Button) findViewById(R.id.demo_collection_button);
                 
               btn.setOnClickListener(new View.OnClickListener() {
                  
                            private String m_chosenDir = "";
                            private boolean m_newFolderEnabled = true;
                           
                            @Override
                            public void onClick(View v) 
                            {   // RED button on click ??
                            	
                          	
                            	///
                            	v.setBackgroundColor(Color.GRAY);
                            	System.out.println(subjectName.getText().toString());
                            	Calendar now = Calendar.getInstance();
                            	int iYear=now.get(Calendar.YEAR);	
                            	// ---==i==---                                   	
                            	
                            	if(subjectName.getText().toString().contentEquals(""))
                            	{
                            		Toast.makeText(MainActivity.this, "Bitte geben Sie einen vollständigen Namen !", Toast.LENGTH_LONG).show();
                            		return;
                            	}	
                                // Create DirectoryChooserDialog and register a callback 
                                DirectoryChooserDialog directoryChooserDialog = 
                                new DirectoryChooserDialog(MainActivity.this, 
                                    new DirectoryChooserDialog.ChosenDirectoryListener() 
                                {
                                    @Override
                                    public void onChosenDir(String chosenDir) 
                                    {
                                        m_chosenDir = chosenDir;
                                        Toast.makeText(
                                        		MainActivity.this, "This is chosen directory: " + 
                                          chosenDir, Toast.LENGTH_LONG).show();
                                        
                                     	Intent intent = new Intent(MainActivity.this, CollectionDemoActivity.class);
                                     Mydata data1=new Mydata();
                                     data1.file=chosenDir;
                                     data1.name=subjectName.getText().toString();
                                     data1.dign=subjectDign.getText().toString();
                                       intent.putExtra("Mydata", data1);
                                     startActivity(intent);
                                    }
                                }); 
                                // Toggle new folder button enabling
                                directoryChooserDialog.setNewFolderEnabled(m_newFolderEnabled);
                                // Load directory chooser dialog for initial 'm_chosenDir' directory.
                                // The registered callback will be called upon final directory selection.
                                directoryChooserDialog.chooseDirectory(m_chosenDir);
                                m_newFolderEnabled = ! m_newFolderEnabled;
                            }
                        });
                    	
                    }
                    
                
               
        //  l.addView(btn);
               
        
}
