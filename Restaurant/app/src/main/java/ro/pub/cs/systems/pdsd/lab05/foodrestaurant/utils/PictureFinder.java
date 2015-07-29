package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.utils;

import android.util.Log;

import java.lang.reflect.Field;

import ro.pub.cs.systems.pdsd.lab05.foodrestaurant.R;

public class PictureFinder {
	
	public static int findPictureByName(String pictureName) {
		try {
			Class<?> RDrawableClass = (new R.drawable()).getClass();
			Field[] fields = RDrawableClass.getFields();
			for (Field field : fields) {
				if (field.getName().equals(pictureName))
					return field.getInt(R.drawable.class);
			}
		} catch (Exception exception) {
			Log.println(Log.ERROR, "error", exception.getMessage());
		}
		return -1;
	}

}
