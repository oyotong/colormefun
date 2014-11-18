package shop.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private final static DateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private final static DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	private final static DateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
	public static String time(Date date){
		if(null == date){
			return "";
		}
		return timeformat.format(date);
	}
	public static Date time(String dateStr){
		try {
			return timeformat.parse(dateStr);
		} catch (ParseException e) {
			return now();
		}
	}

    public static String date(Date date, String fmt){
        if(null == date){
            return "";
        }
        return new SimpleDateFormat(fmt).format(date);
    }

    public static String date(Date date){
        if(null == date){
            return "";
        }
        return dateformat.format(date);
    }

    public static Date date(String dateStr){
        try {
            return dateformat.parse(dateStr);
        } catch (ParseException e) {
            return now();
        }
    }

    public static String dateTime(Date date){
        if(null == date){
            return "";
        }
        return dateTimeformat.format(date);
    }

    public static Date dateTime(String dateStr){
        try {
            return dateTimeformat.parse(dateStr);
        } catch (ParseException e) {
            return now();
        }
    }

    public static Date setToLastDateOfMonth(Date date){
        if(null == date){
            date = now();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();

    }

    public static Date setToFirstDateOfMonth(Date date){
        if(null == date){
            date = now();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();

    }

    public static Date setToLastTime(Date date){

        if(null == date){
            date = now();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        //calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();

    }

    public static Date setToFirstTime(Date date){

        if(null == date){
            date = now();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();

    }

    public static Date sysdate(){
        return now();
    }
	public static Date now(){
		return new Date();
	}
}
