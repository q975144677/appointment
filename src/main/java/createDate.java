
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class createDate {
	
public static void main(String[] args) {
	String string = "2015-02-03";
	String string2 = "2016-03-03";
	List<Date> list = creatDateTime(string,string2);
	for(Date date  : list) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
		
	}
}
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
