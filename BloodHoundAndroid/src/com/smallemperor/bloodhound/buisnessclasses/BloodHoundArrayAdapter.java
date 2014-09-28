package com.smallemperor.bloodhound.buisnessclasses;

import com.smallemperor.bloodhound.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BloodHoundArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public BloodHoundArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_mobile, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		
		//ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView,
		//		false);
		//ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer, listView,
			//	false);
		
		//addHeaderView(header, null, false);
		//addFooterView(footer, null, false);

		View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		ImageView imageView1 = (ImageView) rowView.findViewById(R.id.uncheck);
		
		String[] array=values[position].split(",");
		// Change icon based on name
		String s = array[1]+" "+array[2]; 
		System.out.println(s);
		textView.setText(s);
		//imageView.setImageResource(R.drawable.sample);
		imageView.setImageBitmap(decodeFile(array[0]));			
		return rowView;
	}
	public Bitmap decodeFile(String path) {
        try {
            // Decode image size
        	if(path!=null && !path.equalsIgnoreCase("") ){
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
        	}
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }
}