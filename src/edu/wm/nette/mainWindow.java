package edu.wm.nette;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class mainWindow extends Activity {
	
	private final int mode = Activity.MODE_PRIVATE;
	private final String APP_PREFS = "App_Prefs";
	
	int selectedChecks = 0;
	CheckBox treble;
	CheckBox bass;
	CheckBox alto;
	CheckBox tenor;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Reset values to 0
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	    SharedPreferences.Editor editor = prefs.edit();
	    //editor.clear();
	    
	    editor.putInt("correct", 0);
	    editor.putInt("total", 0);
	    editor.putString("image", "none");
	    editor.putString("corrAns", "");
	    editor.putString("Abox", "");
	    editor.putString("Bbox", "");
	    editor.putString("Cbox", "");
	    editor.putString("Dbox", "");
	    editor.putString("Ebox", "");
	    editor.putString("Fbox", "");
	    editor.putString("Gbox", "");
	    
	    // Commit to storage
	    editor.commit();
        
        treble = (CheckBox) findViewById(R.id.treble);
        bass = (CheckBox) findViewById(R.id.bass);
        alto = (CheckBox) findViewById(R.id.alto);
        tenor = (CheckBox) findViewById(R.id.tenor);      
        
    }
    
    protected void onResume(){
    	super.onResume();
    	
    	// Reset values to 0
    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	    SharedPreferences.Editor editor = prefs.edit();
	    //editor.clear();
	    
	    editor.putInt("correct", 0);
	    editor.putInt("total", 0);
	    editor.putString("image", "none");
	    editor.putString("corrAns", "");
	    editor.putString("Abox", "");
	    editor.putString("Bbox", "");
	    editor.putString("Cbox", "");
	    editor.putString("Dbox", "");
	    editor.putString("Ebox", "");
	    editor.putString("Fbox", "");
	    editor.putString("Gbox", "");
	    
	    // Commit to storage
	    editor.commit();
    }
    
    public void btnStart(View v){
    	
    	if(treble.isChecked() == true || bass.isChecked() == true || tenor.isChecked() == true || alto.isChecked() == true){
			Intent myIntent = new Intent(mainWindow.this, noteWindow.class);
		
	    	myIntent.putExtra("treble", treble.isChecked());
	    	myIntent.putExtra("bass", bass.isChecked());
	    	myIntent.putExtra("alto", alto.isChecked());
	    	myIntent.putExtra("tenor", tenor.isChecked());
	    
			mainWindow.this.startActivity(myIntent);
    	}
    	
    }
    
    public void btnStatistics(View v){
    	Intent myIntent = new Intent(mainWindow.this, statWindow.class);
    	mainWindow.this.startActivity(myIntent);
    	
    }
    
}
