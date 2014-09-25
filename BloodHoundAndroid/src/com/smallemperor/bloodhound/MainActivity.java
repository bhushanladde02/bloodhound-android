package com.smallemperor.bloodhound;

import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import com.gimbal.proximity.Proximity;
import com.gimbal.proximity.ProximityError;
import com.gimbal.proximity.ProximityFactory;
import com.gimbal.proximity.ProximityListener;
import com.gimbal.proximity.Visit;
import com.gimbal.proximity.VisitListener;
import com.gimbal.proximity.VisitManager;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements ProximityListener,VisitListener {

	
	ImageView image;
	ImageView imagebg;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	
	  public final static String EXTRA_MESSAGE = "Starting Event 1";
    


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        

        Proximity.initialize(this,

                "94e47e725835e2f5761d0174df5f5db704689069e38a341abc6326a49d19902d",

                "9b66489c9e38c0475e11786c3244601ac62dde16ac9c483a27f3134210a63901");

        

        //start the service

        Proximity.startService(this);
        
      //  addListenerOnButton();
        
    	image = (ImageView) findViewById(R.id.imageView1);
    	image.setImageResource(R.drawable.navbar);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN); 
    	
        //requestWindowFeature(Window.FEATURE_NO_TITLE);  
    	//imagebg = (ImageView) findViewById(R.id.imageViewBg);
    	//imagebg.setImageResource(R.drawable.background);
    	
    	LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout_id);
    	ll.setBackgroundResource(R.drawable.background);
    
    	//imagebg.setScaleType(ImageView.ScaleType.FIT_XY  );
    	
    	image1 = (ImageView) findViewById(R.id.imageView2);
    	image1.setImageResource(R.drawable.register);
    	
    	image2 = (ImageView) findViewById(R.id.imageView3);
    	image2.setImageResource(R.drawable.report);
    	
    	image3 = (ImageView) findViewById(R.id.imageView4);
    	image3.setImageResource(R.drawable.activesearch);
    	
    	image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
            	System.out.println("I  am inside the image1 one listner ");
            	Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            	String message = "Bhushan Arun Ladde";
            	intent.putExtra(EXTRA_MESSAGE, message);
            	startActivity(intent);
                //v.getId() will give you the image id

            }
        });
    	
    	image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("I  am inside the image2 one listner ");
            	Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            	startActivity(intent);
                //v.getId() will give you the image id

            }
        });
    	
    	image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("I  am inside the image3 one listner ");

                //v.getId() will give you the image id

            }
        });
    	
    	
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


//need to call fetch listner method. send this bcone to other servlet

//bcone id put in hashmap


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