package springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static List<Date> creatDateTime(String s ,String s2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		Date d2 = null;
		List<Date> list = new ArrayList<>();
		try {
			 d = sdf.parse(s);
			 d2 = sdf.parse(s2);
			//1天=24*60*60*1000=86400000毫秒
					long dt1 = d.getTime();
					long dt2 = d2.getTime();
					for(;dt2>=dt1;dt1+=86400000) {
						
						Date result = new Date(dt1);
						list.add(result);
					}
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	} 
}
