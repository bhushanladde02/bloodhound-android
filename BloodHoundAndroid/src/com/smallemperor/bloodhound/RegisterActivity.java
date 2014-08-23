package com.smallemperor.bloodhound;


import java.util.List;


import com.smallemperor.bloodhound.db.DatabaseHandler;
import com.smallemperor.bloodhound.pojo.Register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	ImageView image;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	ImageView image5;
	  private EditText  username=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        
	

        setContentView(R.layout.registerxml);
        
        //Image
        image = (ImageView) findViewById(R.id.imageView1);
    	image.setImageResource(R.drawable.navbar);
    	       
        image1 = (ImageView) findViewById(R.id.imageView2);
    	image1.setImageResource(R.drawable.anonicon100);
    	
    	image2 = (ImageView) findViewById(R.id.imageView3);
    	image2.setImageResource(R.drawable.selectphoto);
    	
    	image3 = (ImageView) findViewById(R.id.imageView4);
    	image3.setImageResource(R.drawable.camera);
    	
    	image4 = (ImageView) findViewById(R.id.imageView5);
    	image4.setImageResource(R.drawable.check);
    	
    	
    	image5 = (ImageView) findViewById(R.id.imageView6);
    	image5.setImageResource(R.drawable.submit);
    	
    	//EditText
    	final Register register=new Register();
    	
    	 
    	 
    	
        
        final DatabaseHandler db = new DatabaseHandler(this);
        
    	image5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
            	 EditText beaconeId =null;
	           	 EditText fName  =null;
	           	 EditText lName =null;
	           	 EditText imgurl =null; //not there
	           	 EditText street =null;
	           	 EditText city =null;
	           	 EditText state =null;
	           	 EditText zip =null;
	           	 EditText age =null;
	           	 EditText height=null;
	           	 EditText weight=null;
	           	 EditText hcolor =null;
	           	 EditText ecolor =null;
	           	 EditText feature =null;
	           	 EditText special =null;
	           	 EditText action =null;
	           	 EditText report =null;//not there
            	
            	System.out.println("I  am inside the inserting data listner ");
            	String message = "Bhushan Arun Ladde";
            	
            	beaconeId = (EditText)findViewById(R.id.editTextId);
            	System.out.println("IIIIIIIIIIIIIIIIIDDDDDDDDDDDDDDDDDDD"+beaconeId.getText().toString());
            	register.setBeaconeId(beaconeId.getText().toString());
            	
            	fName= (EditText)findViewById(R.id.editTextFname);
            	register.setfName(fName.getText().toString());
            	
            	lName=(EditText)findViewById(R.id.editTextlName);
            	register.setlName(lName.getText().toString());
            	
            	street= (EditText)findViewById(R.id.editTextStreet);
            	register.setStreet(street.getText().toString());
            	
            	city= (EditText)findViewById(R.id.editTextCity);
            	register.setCity(city.getText().toString());
            	
            	state= (EditText)findViewById(R.id.editTextAZ);
            	register.setState(state.getText().toString());
            	
            	zip= (EditText)findViewById(R.id.editTextZip);
            	register.setZip(zip.getText().toString());
            	
            	age= (EditText)findViewById(R.id.editTextAge);
            	register.setAge(age.getText().toString());
            	
            	height= (EditText)findViewById(R.id.editTextheight);
            	register.setHeight(height.getText().toString());
            	
            	weight= (EditText)findViewById(R.id.editTextWeight);
            	register.setWeight(weight.getText().toString());
            	
            	hcolor= (EditText)findViewById(R.id.editTextHColor);
            	register.setHcolor(hcolor.getText().toString());
            	
            	ecolor=(EditText)findViewById(R.id.editTextEye);
            	register.setEcolor(ecolor.getText().toString());
            	
            	feature=(EditText)findViewById(R.id.editTextFeatures);
            	register.setFeature(feature.getText().toString());
            	
            	special=(EditText)findViewById(R.id.editTextNotes);
            	register.setSpecial(special.getText().toString());
            	
            	action=(EditText)findViewById(R.id.editTextifFind);
                register.setAction(action.getText().toString()); 
            	
                
                //Not There so Temp Data fill
                register.setReport("Report 1");
                register.setImgurl("Image  1");
            
            	
                
                /**
                 * CRUD Operations
                 * */
                // Inserting Contacts
                Log.d("Insert: ", "Inserting .."); 
                long l=db.add(register);        
                System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllll"+l); 
                 
                if(l!=-1){
				    Context context = getApplicationContext();
					CharSequence text = "Register in Bloodhound!";
					int duration = Toast.LENGTH_SHORT;					
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();					
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);   
	            	startActivity(intent);
				}
			  else{
				    Context context = getApplicationContext();
					CharSequence text = "Not Register in Bloodhound. Please enter correct data.";
					int duration = Toast.LENGTH_SHORT;					
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
                }
                 
                
                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts.."); 
                List<Register> registers = db.getAll();       
                 
                for (Register cn : registers) {
                    String log = "Id: "+cn.getBeaconeId()+" ,Name: " + cn.getfName() + " ,Phone: " + cn.getlName();
                        // Writing Contacts to log
                Log.d("Name: ", log);
            }
                //v.getId() will give you the image id

            }
        });
    	
    	
    	
    //	   image1 = (ImageView) findViewById(R.id.imageView2);
     //  	image1.setImageResource(R.drawable.regis_img1);
    //   	image1.setScaleType(ImageView.ScaleType.FIT_XY  );
    	
       	
	    // Get the message from the intent
	    /*Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);*/
	}
  
}