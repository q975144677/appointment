package springboot.service.impl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.DatetimeMapper;
import springboot.pojo.Datetime;
import springboot.pojo.DatetimeExample;
import springboot.pojo.Time;
import springboot.service.DatetimeService;
import springboot.service.TimeService;
@Service
public class DatetimeServiceImpl implements DatetimeService {
@Autowired
DatetimeMapper datetimeMapper;
@Autowired
TimeService timeService;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
datetimeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Datetime datetime) {
		// TODO Auto-generated method stub
datetimeMapper.insert(datetime);
	}

	@Override
	public void insert(List<Datetime> list) {
		// TODO Auto-generated method stub
for(Datetime d : list) {
	insert(d);
}
	}

	@Override
	public List<Datetime> list() {
		// TODO Auto-generated method stub
		DatetimeExample example = new DatetimeExample();
		example.setOrderByClause("id desc");
		List<Datetime> list =  datetimeMapper.selectByExample(example);
		formatDate(list);
		return list;
	}

	@Override
	public Datetime get(int id) {
		// TODO Auto-generated method stub
		Datetime datetime = datetimeMapper.selectByPrimaryKey(id);
		formatDate(datetime);
		return datetime;
	}
		
	public void formatDate(Datetime datetime ) {
		Date d  = datetime.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String string = sdf.format(d);
		datetime.setNewDate(string);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int i =  calendar.get(Calendar.DAY_OF_WEEK);
	//	int i = Calendar.DAY_OF_WEEK;
		i--;
		if(i==0) {
			i=7;
		}
		System.out.println(i);
		datetime.setDayOfWeek(String.valueOf(i));
		List<Time> list = timeService.list();
		datetime.setTimes(list);
		
	}
	public void formatDate(List<Datetime> list) {
		for(Datetime datetime : list) {
			formatDate(datetime);
		}
	}
	@Override
		public Datetime getByDate(Date date) {
			// TODO Auto-generated method stub
		DatetimeExample example = new DatetimeExample();
		example.createCriteria().andDateEqualTo(date);
		List<Datetime> list = datetimeMapper.selectByExample(example);
		formatDate(list);
		if(list!=null && !list.isEmpty()) {
			return list.get(0);	
		}
		return null;
		}
	
	//获取 本周 7天的 datetime
	@Override
		public List<Datetime> listByWeek(Datetime datetime) {
		formatDate(datetime);
		// TODO Auto-generated method stub
		int i = Integer.parseInt(datetime.getDayOfWeek());
		int before = i-1;
		int after = 7-i;
		List<Datetime> list = new ArrayList<>();
		Calendar calendar  = Calendar.getInstance();
		calendar.setTime(datetime.getDate());
		List<Datetime> list2 = new ArrayList<>();
		for(int x = 0 ; x<before ; x++) {
			calendar.add(Calendar.DATE, -1);
		
			Date date = calendar.getTime();
			Datetime dt = getByDate(date);
			if(dt == null) {
				Datetime datetime2  =new Datetime();
				datetime2.setDate(date);
				insert(datetime2);
				dt = getByDate(date);
			}
			list2.add(dt);
		}
		Collections.reverse(list2);
		list = list2 ;
		list.add(datetime);
		calendar.setTime(datetime.getDate());
		for(int x = 0 ; x < after ; x++) {
			calendar.add(Calendar.DATE, 1);
			Date date = calendar.getTime();
			Datetime dt = getByDate(date);
			if(dt == null) {
				Datetime datetime2  =new Datetime();
				datetime2.setDate(date);
				insert(datetime2);
				dt = getByDate(date);
			}
			list.add(dt);
			
		}
			formatDate(list);
			return list;
		}

}
