package com.smallemperor.bloodhound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.gimbal.proximity.ProximityError;
import com.gimbal.proximity.ProximityFactory;
import com.gimbal.proximity.ProximityListener;
import com.gimbal.proximity.Visit;
import com.gimbal.proximity.VisitListener;
import com.gimbal.proximity.VisitManager;
import com.smallemperor.bloodhound.buisnessclasses.BloodHoundArrayAdapter;
import com.smallemperor.bloodhound.db.DatabaseHandler;
import com.smallemperor.bloodhound.pojo.Register;

public class ReportActivity extends   ListActivity implements ProximityListener,VisitListener {

	
	ImageView image;
	ImageView image1;
	ImageView uncheck;
	ListView listView;
	

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.reportxml);
    
        
    	//image = (ImageView) findViewById(R.id.imageView1);
    	//image.setImageResource(R.drawable.navbar);
    	
       
    	//image1 = (ImageView) findViewById(R.id.imageView2);
    	//image1.setImageResource(R.drawable.reportalert);
    /*	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	lp.setMargins(left, top, right, bottom);
    	imageView.setLayoutParams(lp); */
        
        // Get ListView object from xml
    	DatabaseHandler db = new DatabaseHandler(this);
    	
       
        String[] listDog = new String[db.getCount()];
        int i=0;   

	   List<Register> registers = db.getAll();       
	   for (Register cn : registers) {
	       String log = ""+cn.getImgurl()+ ","+cn.getfName() +", "+ cn.getlName();
	           // Writing Contacts to log
	       listDog[i]=log;
	       i++;
	       Log.d("Name: ", log);
	    }
	        
  

       
    	
    
  
        listView = (ListView) findViewById(android.R.id.list);
		
		//code to add header and footer to listview
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView,
				false);
		ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer, listView,
				false);
		listView.addHeaderView(header, null, false);
		listView.addFooterView(footer, null, false);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listDog);

		  
		
		//listView.setAdapter(adapter);
		listView.setAdapter(new BloodHoundArrayAdapter(getApplicationContext(), listDog));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						((TextView) v).getText(), Toast.LENGTH_SHORT).show();
			}
		});
        
        
        
        
		// setListAdapter();
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    	
       /* image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("Starting syn data.,.......");
            
            }
        });*/
    	
    
    	
    	
    }


    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
 

   

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }
  



    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will

        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);

    }





@Override

public void serviceStarted() {

// TODO Auto-generated method stub

// this will be invoked if the service has successfully started

        // bluetooth scanning will be started at this point

        Log.d("Proximity","Proximity Service successfully started!");    

        

        VisitManager visitManager = ProximityFactory.getInstance().createVisitManager();

        visitManager.setVisitListener(new VisitListener() {


@Override

public void receivedSighting(Visit arg0, Date arg1, Integer arg2) {

// TODO Auto-generated method stub

Log.d("Proximity","Found Signal");

Log.d("Proximity ID",arg0.getTransmitter().getIdentifier());

}


@Override

public void didDepart(Visit arg0) {

// TODO Auto-generated method stub


}


@Override

public void didArrive(Visit arg0) {

// TODO Auto-generated method stub


}

});

        visitManager.start();


}





@Override

public void startServiceFailed(int arg0, String arg1) {

// TODO Auto-generated method stub

  // this will be called if the service has failed to start

        String logMsg = String.format("Proximity Service failed with error code %d, message: %s!", arg0, arg1);

        Log.d("Proximity", logMsg);

        //check for the error Code for Bluetooth status check

        if (arg0 == ProximityError.PROXIMITY_BLUETOOTH_IS_OFF.getCode()) {

            //turn on the bluetooth and once the bluetooth is ON call startService again.

        }


}





@Override

public void didArrive(Visit arg0) {

// TODO Auto-generated method stub


}





@Override

public void didDepart(Visit arg0) {

// TODO Auto-generated method stub


}





@Override

public void receivedSighting(Visit arg0, Date arg1, Integer arg2) {

// TODO Auto-generated method stub


}

}