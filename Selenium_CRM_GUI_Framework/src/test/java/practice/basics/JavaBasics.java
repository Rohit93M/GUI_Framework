package practice.basics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {

	public static void main(String[] args) {
		
Date dateObj = new Date();
System.out.println(dateObj.toString());

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String actualDate = sdf.format(dateObj);
System.out.println(actualDate);

Calendar cal = sdf.getCalendar();
cal.add(Calendar.DAY_OF_MONTH,30);
String dateRequired = sdf.format(cal.getTime());
System.out.println(dateRequired);

	}
}
