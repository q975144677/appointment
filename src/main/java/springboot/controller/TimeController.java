package springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javassist.expr.NewArray;
import springboot.pojo.Appointment;
import springboot.pojo.Category;
import springboot.pojo.Datetime;
import springboot.pojo.Item;
import springboot.pojo.Time;
import springboot.service.AppointmentService;
import springboot.service.CategoryService;
import springboot.service.DatetimeService;
import springboot.service.ItemService;
import springboot.service.TimeService;
import springboot.util.DateUtil;

@RestController
public class TimeController {
	@Autowired
	DatetimeService datetimeService;
	@Autowired
	TimeService timeService;
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	CategoryService categoryService;
@Autowired
ItemService itemService;
	@RequestMapping("date")
	public ModelAndView date() {
		ModelAndView mav = new ModelAndView("date");
		List<Datetime> list = datetimeService.list();
		mav.addObject("dates", list);
		return mav;
	}

	@RequestMapping("date_insert")
	public ModelAndView date_insert(String start, String end) {
		ModelAndView mav = new ModelAndView("redirect:/date");
		List<Date> list = DateUtil.creatDateTime(start, end);
		List<Datetime> list2 = new ArrayList<>();
		Datetime dt = new Datetime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Date date : list) {
			sdf.format(date);
			dt.setDate(date);
			list2.add(dt);
			dt = new Datetime();
		}
		datetimeService.insert(list2);
		return mav;
	}

	@RequestMapping("time")
	public ModelAndView time() {
		ModelAndView mav = new ModelAndView("time");
		List<Time> list = timeService.list();
		mav.addObject("times", list);
		return mav;
	}

	@RequestMapping("time_insert")
	public ModelAndView time_insert(Time time) {
		ModelAndView mav = new ModelAndView("redirect:/time");
		timeService.insert(time);
		return mav;
	}

	@RequestMapping("appoint")
	public ModelAndView homePage(Datetime datetime,Integer iid) {
		ModelAndView mav = new ModelAndView("appoint");
		 Datetime d  = null;
		 System.out.println(datetime);
		 
		if ( datetime == null  || datetime.getNewDate() == null || datetime.getNewDate().isEmpty()) {
			Calendar calendar = Calendar.getInstance();

			calendar.set(Calendar.HOUR_OF_DAY, 0);

			calendar.set(Calendar.MINUTE, 0);

			calendar.set(Calendar.SECOND, 0);

			calendar.set(Calendar.MILLISECOND, 0);
			Date date = calendar.getTime();
		
			 d = datetimeService.getByDate(date);
			
		
		}
		else {
			 d = datetime;
			 try {
				Date date = (new SimpleDateFormat("yyyy-MM-dd").parse(d.getNewDate()));
				d = datetimeService.getByDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			 String string =  d.getNewDate();
//			 System.out.println(string+"ssssssss");
//			 try {
//				Date date  = new SimpleDateFormat("yyyy-MM-dd").parse(string);
//				
//				//下一周的时间
//				Date newDate = new Date(date.getTime()+86400000*7);
//				String next  =  new SimpleDateFormat("yyyy-MM-dd").format(newDate);
//				System.out.println(next);
//				//上一周的时间
//				newDate  = new Date(date.getTime()-86400000*7);
//				String previous =  new SimpleDateFormat("yyyy-MM-dd").format(newDate);
//				System.out.println(previous);
//				 d = datetimeService.getByDate(date);
//				 if(d == null) {
//						Datetime datetime2  =new Datetime();
//						datetime2.setDate(date);
//						datetimeService.insert(datetime2);
//						d = datetimeService.getByDate(date);
//					}
//				mav.addObject("next",next);
//				mav.addObject("previous",previous);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			 
		}	
		
		
		//get 完之后  数据库 不存在 这天
		
		
		Calendar calendar = Calendar.getInstance();
		 if(d == null) {
			 Date date;
			try {
				if (datetime !=null && datetime.getNewDate()!=null &&!datetime.getNewDate().isEmpty()) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime.getNewDate());
				}
				else {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}
				Datetime datetime2  =new Datetime();
				datetime2.setDate(date);
				datetimeService.insert(datetime2);
				d = datetimeService.getByDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			} 
		//判断 date 与今天相差是否在 一个月以内
		System.out.println((d.getDate().getTime()-new Date().getTime())/86400000+"相差相差时间");
		
		if(((d.getDate().getTime()-new Date().getTime())/86400000)>30||((d.getDate().getTime()-new Date().getTime())/86400000)<-30) {
			d = new Datetime();
			try {
				d = datetimeService.getByDate(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
				 if(d == null) {
					 Date date;
					try {
						
						date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
						Datetime datetime2  =new Datetime();
						datetime2.setDate(date);
						datetimeService.insert(datetime2);
						d = datetimeService.getByDate(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					} 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		
		
		 try {	 
			 	String string =  d.getNewDate();
				Date date2  = new SimpleDateFormat("yyyy-MM-dd").parse(string);
				
				//下一周的时间
				Date newDate = new Date(date2.getTime()+86400000*7);
				String next  =  new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				System.out.println(next);
				//上一周的时间
				newDate  = new Date(date2.getTime()-86400000*7);
				String previous =  new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				System.out.println(previous);
				mav.addObject("next",next);
				mav.addObject("previous",previous);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		List<Datetime> list = datetimeService.listByWeek(d);
		
		String now = d.getNewDate();
		mav.addObject("now",now);
		mav.addObject("list",list);
		
		List<Appointment> list2 = appointmentService.list();
		mav.addObject("service",appointmentService);
		mav.addObject("list2" , list2);
		List<Time> list3 = timeService.list();
		mav.addObject("list3",list3);
		Item i = itemService.get(iid);
		mav.addObject("item",i);
		int cid=  i.getCid();
		Category c = categoryService.get(cid);
		mav.addObject("c",c);
		mav.addObject("date",datetime.getNewDate());
		if((d.getDate().getTime()-new Date().getTime())/86400000>23)	{
			mav.addObject("flag",1);
		}
		System.out.println(d.getDate().getTime()-new Date().getTime()/86400000);
		if((d.getDate().getTime()-new Date().getTime())/86400000<-23)	{
			mav.addObject("flag",0);
		}
		
		
		
		
		//拼接 近 30 年 的 年月日 
		//未完成
		
		System.out.println(Calendar.YEAR);
		
		List<Date> list4 = new DateUtil().creatDateTime(calendar.get(Calendar.YEAR)-15+"-01-01", calendar.get(Calendar.YEAR)+15+"-12-31");
		
		return mav;

	}
	@RequestMapping("insert")
	public ModelAndView insert(Appointment appointment,String date) {
		appointmentService.insert(appointment);
		ModelAndView mav = new ModelAndView("redirect:/appoint?iid="+appointment.getIid()+"&newDate="+date);
		return mav;
		
	}

}
