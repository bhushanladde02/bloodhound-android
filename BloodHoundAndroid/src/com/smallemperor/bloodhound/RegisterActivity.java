package com.smallemperor.bloodhound;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import com.smallemperor.bloodhound.db.DatabaseHandler;
import com.smallemperor.bloodhound.pojo.Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	ImageView image;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	ImageView image5;
	int flag =0;
	//private static Bitmap bitmap;
	//private static int RESULT_LOAD_IMAGE = 1;
	//private EditText  username=null;
	private static String selectedImagePath = "image12357.png";
	final private int PICK_IMAGE = 1;
	final private int CAPTURE_IMAGE = 2;
	private String imgPath="";
	
	

	//save the context recievied via constructor in a local variable

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
    	image4.setImageResource(R.drawable.uncheck);
    	
    	
    	image5 = (ImageView) findViewById(R.id.imageView6);
    	image5.setImageResource(R.drawable.submit);
    	
    	//EditText
    	final Register register=new Register();
        final DatabaseHandler db = new DatabaseHandler(this);
        
        
        //Accept
        
        image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	image4.setImageResource(R.drawable.check);
            	flag=1;
            	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                boolean isEnabled = bluetoothAdapter.isEnabled();
                if (!isEnabled) {
                     bluetoothAdapter.enable(); 
                }
                
                //v.getId() will give you the image id

            }
        });
    	
        
        
        //gallary
        image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        
            	/* Intent i = new Intent(
                         Intent.ACTION_PICK,
                         android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                  
                 startActivityForResult(i, RESULT_LOAD_IMAGE);
                 System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIII MMMMMMMMM in this line");*/
            	 Intent intent = new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent, ""), PICK_IMAGE);

            	
            }
        });	
            
        //camera
        image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        
            	// Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               //  startActivity(intent);
            	///open();
            	final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            	System.out.println("#################setImageUri()############################################"+setImageUri());
            	System.out.println("#################getImagePath()############################################"+getImagePath());
            	
            	
                intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(intent, CAPTURE_IMAGE);
            }
        });	
       
        
        image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        
            	// Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               //  startActivity(intent);
            	///open();
            	final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            	System.out.println("#################setImageUri()############################################"+setImageUri());
            	System.out.println("#################getImagePath()############################################"+getImagePath());
            	
            	
                intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(intent, CAPTURE_IMAGE);
            }
        });
        
       // if(flag==1){
    	image5.setOnClickListener(new View.OnClickListener() {
    		
            public void onClick(View v) {
            	
            	if(flag==1){   //flag for checking accept button click
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
                register.setImgurl(selectedImagePath);
            
            	
                
                /**
                 * CRUD Operations
                 * */
                // Inserting Contacts
                Log.d("Insert: ", "Inserting .."); 
                long l=db.add(register);        
                System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllll"+l); 
                if(beaconeId.equals(null) || beaconeId==null ||   beaconeId.equals("") || beaconeId.toString().equalsIgnoreCase(""))
                {	
                	validation("Beacone Id is mandatory!",v);
                	v.focusSearch(RESULT_OK);
                }
                else{
		                if(l!=-1){
						    Context context = getApplicationContext();
							CharSequence text = "Register in Bloodhound!";
							int duration = Toast.LENGTH_SHORT;					
							Toast toast = Toast.makeText(context, text, duration);
							toast.show();					
							Intent intent = new Intent(getApplicationContext(), MainActivity.class);   
			            	startActivity(intent);
			            	//String resultServer= POST("http://smallemperor.com:8080/BloodHoundBackend/UploadServlet",register);
			            	new HttpAsyncTask(RegisterActivity.this).execute("http://smallemperor.com:8080/BloodHoundBackend/UploadServlet");//(pradeep server)
			            	//new HttpAsyncTask().execute("http://192.168.42.229:8080/BloodHoundBackend/UploadServlet");//(bhushan local system- check ip of system everytime)
			            	
			            	
			            	//System.out.println("#$##############SERVER RESULT###$####$$$#####"+resultServer);
			            	
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
                
                
            	}//end of if flag
            	else{
            		validation("Please accept the permission!",v);
            	}
            }
            
    
        });
       // }else{
        	//do nothing if term and condition are not accepted
       // }
    	
    	
    	
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
	
	/*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
        	//Bundle bundle = data.getExtras();
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
             
            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            System.out.println("#########################################"+picturePath);
           // System.out.println("#########################IiiiiiiiiiiiiiiiiMMMMMMMMS#########################################"+image1);
            
            image1.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //System.out.println("#########################################"+picturePath);
            
            image1.setMaxHeight(300);
  	      	image1.setMinimumHeight(300);
  	      	image1.setMaxWidth(300);
  	      	image1.setMinimumWidth(300);
         
        }
     
         // super.onActivityResult(requestCode, resultCode, data);
	      Bitmap bp = (Bitmap) data.getExtras().get("data");
	      image1.setImageBitmap(bp);
	      image1.setMaxHeight(300);
	      image1.setMinimumHeight(300);
	      image1.setMaxWidth(300);
	      image1.setMinimumWidth(300);
	      
	      
    }*/
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE) {
                selectedImagePath = getAbsolutePath(data.getData());
                System.out.println("Selected image to upload::::::::::::::::::::::::::::"+selectedImagePath);
                image1.setImageBitmap(decodeFile(selectedImagePath));
                image1.setMaxHeight(300);
      	      	image1.setMinimumHeight(300);
      	      	image1.setMaxWidth(300);
      	      	image1.setMinimumWidth(300);
            } else if (requestCode == CAPTURE_IMAGE) {
                selectedImagePath = getImagePath();
                image1.setImageBitmap(decodeFile(selectedImagePath));
                image1.setMaxHeight(300);
      	      	image1.setMinimumHeight(300);
      	      	image1.setMaxWidth(300);
      	      	image1.setMinimumWidth(300);
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
	
	public void open(){
	      Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	      startActivityForResult(intent, 0);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.menu.main, menu);
	      return true;
	}
	public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }

public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

public String getAbsolutePath(Uri uri) {
        String[] projection = { MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

// Server Sending data

private static String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
        result += line;

    inputStream.close();
    return result;

}  

	
public static String POST(String url, Register register){
    InputStream inputStream = null;
    String result = "";
    try {

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        
        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);
        
        String json = "";
        
        String name = register.getImgurl();
        

        // 3. build jsonObject
        //{"beaconId":"\"BEBH-HYEY71\"","address":"\"2461 Boxwood Street\"","col0":"\"7\"","col1":"\"32%22\"","col2":"\"70\"","col3":"\"Some white on muzzle.\"","col4":"\"93458\"","col5":"\"Very friendly.\"","col6":"\"Call owner - 619.206.3422, Debby Lanthier\"","col7":"false","col8":"\"ShouldBeUnique\"","col9":"\"imageBEBH-HYEY7.png\"","eyecolor":"\"Brown\"","firstname":"\"Bella\"","haircolor":"\"Black\"","lastname":"\"Scafani\""}
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("deviceID", register.getBeaconeId());
        jsonObject.accumulate("firstname", register.getfName());
        
        jsonObject.accumulate("lastname", register.getlName());
        String imageName = name.substring(name.lastIndexOf("/")+1);
        jsonObject.accumulate("imgURL",imageName);// register.getImgurl());//.replace("/", "\\"));
        jsonObject.accumulate("age", register.getAge());
        
        jsonObject.accumulate("height", register.getHeight());
        jsonObject.accumulate("weight", register.getWeight());
        jsonObject.accumulate("hcolor", register.getHcolor());
        
        jsonObject.accumulate("ecolor", register.getEcolor());
        jsonObject.accumulate("feature", register.getFeature());
        jsonObject.accumulate("street", register.getStreet());
        
        jsonObject.accumulate("zip", register.getZip());
        jsonObject.accumulate("action", register.getAction());
        jsonObject.accumulate("uniqueID", register.getBeaconeId());//sending as unique id
        jsonObject.accumulate("special", register.getSpecial());
        
        

        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();//(make a string as following format)
        //json="{beaconId:\"BEBH-HYEY7\",address:\"2461 Boxwood Street\",col0:\"7\",col1:\"32%22\",col2:\"70\",col3:\"Some white on muzzle.\",col4:\"93458\",col5:\"Very friendly.\",col6:\"Call owner - 619.206.3422, Debby Lanthier\",col7:\"false\",col8:\"ShouldBeUnique\",col9:\"imageBEBH-HYEY7.png\",eyecolor:\"Brown\",firstname:\"Bella\",haircolor:\"Black\",lastname:\"Scafani\"}";
        System.out.println("##############Json###############"+json);
        // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person); 
        // 7. Set some headers to inform server about the type of the content   
              
        String boundaryConstant  = "----------V2ymHFg03ehbqgZCaKO6jy";
  
        
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "multipart/form-data; boundary="+boundaryConstant);
      //  httpPost.setHeader("jsonString",json);
        httpPost.addHeader(new BasicHeader("jsonString",json));
        
        
        StringBuilder data = new StringBuilder();
       
        Bitmap fullImage = BitmapFactory.decodeFile(register.getImgurl());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        fullImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b,Base64.DEFAULT);
        String FileParamConstant = "file";
        
        data.append("--"+boundaryConstant+"\r\n");
        data.append("Content-Disposition: form-data; name=\""+FileParamConstant+"\";filename=\""+name+"\"\r\n");
        data.append("Content-Type: image/jpeg\r\n\r\n");
        data.append(encodedImage);
        data.append("\r\n");

        data.append("--"+boundaryConstant+"--\r\n");
        
        
        HttpEntity entity = new ByteArrayEntity(data.toString().getBytes("UTF-8"));
        httpPost.setEntity(entity);
        
        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);
        
        //String response = EntityUtils.toString( httpResponse.getEntity(), "UTF-8" );
      	
	     //httpclient.getConnectionManager().shutdown();
        
        //bitmap.recycle();
        
        System.out.println("################inputstream###############################"+httpResponse+"################inputstream###############################" );
        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();
        System.out.println("################inputstream###############################"+inputStream);
        
        // 10. convert inputstream to string
        
        if(inputStream != null){
            result = convertInputStreamToString(inputStream);
            //result1=ImageUpload();
    		
            }
        else{
            result = "Did not work!";
            }

    } catch (Exception e) {
    	System.out.println("I am in exception :::::::::::"+ e);
    	System.out.println("I am in exception :::::::::::"+ e.getLocalizedMessage());
        Log.d("InputStream", e.getLocalizedMessage());
    }

    // 11. return result
    return result ;//+","+result1;
}

private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	
	private Context context;
	private ProgressDialog pdia;
	
	public HttpAsyncTask(Context context){
		this.context = context;
	}
	

	
	
    @Override
    protected String doInBackground(String... urls) {
       Register register=new Register();
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
    	
    	beaconeId = (EditText)findViewById(R.id.editTextId);
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
        register.setImgurl(selectedImagePath);
        
       // ImageUpload();//uploading images to server
        String result = null;
        try{
        	result = POST(urls[0],register);
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	pdia.dismiss();
        }
        
        return result;
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
    	try {
			Utils.showDialog("Registration Successful ", context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent in = new Intent(context, MainActivity.class);
		in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); 
		context.startActivity(in);
   }
   
    @Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pdia = new ProgressDialog(context);
	    pdia.setMessage("Registering...");
	    pdia.show();  	
	}
   
}
  
public void validation(String msg,View v)
{
	AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	builder.setMessage(msg)
	       .setCancelable(false)
	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                //do things
	           }
	       });
	AlertDialog alert = builder.create();
	alert.show();
}

}