package springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.AppointmentMapper;
import springboot.pojo.Appointment;
import springboot.pojo.AppointmentExample;
import springboot.pojo.Datetime;
import springboot.pojo.Item;
import springboot.pojo.Time;
import springboot.pojo.User;
import springboot.service.AppointmentService;
import springboot.service.DatetimeService;
import springboot.service.ItemService;
import springboot.service.TimeService;
import springboot.service.UserService;
@Service
public class AppointmentServiceImpl implements AppointmentService {
@Autowired
AppointmentMapper appointmentMapper;
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	@Autowired
	TimeService timeService;
	@Autowired
	DatetimeService datetimeService;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
appointmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Appointment appointment) {
		// TODO Auto-generated method stub
appointmentMapper.insert(appointment);
	}

	@Override
	public List<Appointment> list() {
		// TODO Auto-generated method stub
		AppointmentExample example  =new AppointmentExample();
		example.setOrderByClause("id desc");
		List<Appointment> list = appointmentMapper.selectByExample(example);
		setUser(list);
		setItemName(list);setDate(list);
		return list;
	}

	@Override
	public Appointment get(int id) {
		// TODO Auto-generated method stub
		return appointmentMapper.selectByPrimaryKey(id);
	}
	@Override
		public Appointment get(int tid, int did,int iid) {
			// TODO Auto-generated method stub
		AppointmentExample example = new AppointmentExample();
		example.createCriteria().andDidEqualTo(did).andTidEqualTo(tid).andIidEqualTo(iid).andStatusEqualTo("success");
		List<Appointment> list = appointmentMapper.selectByExample(example);	setUser(list);
		setItemName(list);
		setDate(list);
		if(list.isEmpty()) {
			return null;
			
		}
		else {
			return list.get(0);
		}
		}
	

	@Override
	public List<Appointment> list(int iid) {
		// TODO Auto-generated method stub
		AppointmentExample example  = new AppointmentExample() ; 
		example.setOrderByClause("id desc");
		example.createCriteria().andIidEqualTo(iid);
		List<Appointment> list=  appointmentMapper.selectByExample(example);
		setItemName(list);
		setUser(list);	
		setDate(list);
		return list;
	}
	
	@Override
		public List<Appointment> listByUid(int uid) {
			// TODO Auto-generated method stub
		AppointmentExample example  = new AppointmentExample() ; 
		example.setOrderByClause("id desc");
		example.createCriteria().andUidEqualTo(uid);
		List<Appointment> list=  appointmentMapper.selectByExample(example);
		setUser(list);
		setItemName(list);
		setDate(list);
		return list;
		}
	public void setItemName(Appointment appointment) {
		if(appointment!=null) {
		int iid = appointment.getIid();
		Item i = itemService.get(iid);
		if(i!=null) {
			appointment.setItemName(i.getName());
			
		}
		}
	}
	public void setItemName(List<Appointment> list) {
		for(Appointment a : list) {
			setItemName(a);
			
		}
	}
	public void setUser(Appointment appointment) {
		if(appointment!=null) {
			int tid = appointment.getTid();
			int did = appointment.getDid();
			Datetime dt =  datetimeService.get(did);
			Time t = timeService.get(tid);
			String string = dt.getNewDate()+"："+t.getTime();
			appointment.setTime(string);
			int uid = appointment.getUid();
			
			User user =userService.get(uid);
			
			if(user!=null) {
				appointment.setUser(user);
				
			}
			}
	}
	
	public void setUser(List<Appointment> list) {
		for(Appointment a : list) {
			setUser(a);
			
		}
	}
	@Override
	public void update(Appointment appointment) {
		// TODO Auto-generated method stub
appointmentMapper.updateByPrimaryKeySelective(appointment);
	}
	public void setDate(Appointment appointment) {
		Datetime dt = datetimeService.get(appointment.getDid());
		System.out.println("非空"+dt.getDate().getTime());
		appointment.setDatetime(dt);
	}
	public void setDate(List<Appointment> list) {
		for(Appointment appointment : list) {
			setDate(appointment);
			
		}
		
	}

}
