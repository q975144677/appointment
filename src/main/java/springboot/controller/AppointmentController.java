package springboot.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springboot.pojo.Appointment;
import springboot.pojo.Item;
import springboot.service.AppointmentService;
import springboot.service.ItemService;

@RestController
public class AppointmentController {
@Autowired
AppointmentService appointmentService ; 
@Autowired 
ItemService ItemService;

@RequestMapping("appointment")
	public ModelAndView appointment(int iid) {
	ModelAndView modelAndView  = new ModelAndView("appointment");
	System.out.println(iid);
	List<Appointment> list = appointmentService.list(iid);
	Item i = ItemService.get(iid);
	int cid = i.getCid();
	modelAndView.addObject("iid",iid);
	modelAndView.addObject("cid",cid);
	modelAndView.addObject("list",list);
	for(Appointment a : list) {
		System.out.println(a.getReason());
		
	}
	return modelAndView;
}


@RequestMapping("appointment_update")
public ModelAndView appointment_update(Appointment appointment,int i) {
	int iid = appointment.getIid();
	System.out.println(appointment.getStatus());
	
	ModelAndView modelAndView = new ModelAndView("redirect:/appointment?"+"iid="+iid);
	
	if(i==1) {
	 modelAndView = new ModelAndView("redirect:/appointment_all");
	}
	appointmentService.update(appointment);
return modelAndView;
}
@RequestMapping("appointment_delete")
public ModelAndView delete(int iid) {
	List<Appointment> list = appointmentService.list(iid);
	for(Appointment a : list){
		appointmentService.delete(a.getId());
	}
	ModelAndView mav = new ModelAndView("redirect:/appointment?iid="+iid);
	return mav;
}
@RequestMapping("appointment_all")
public ModelAndView app() {
	List<Appointment> list  = appointmentService.list();
	Collections.sort(list,new Comparator<Appointment>() {
	@Override
	public int compare(Appointment o1, Appointment o2) {
		// TODO Auto-generated method stub
		if(!o1.getStatus().equals("wait")) {
			return 1;
		}
		else {
			return (int)(o2.getDatetime().getDate().getTime()-o2.getDatetime().getDate().getTime());
		}
	}
	});
	ModelAndView mav = new ModelAndView("appointment_all");
	
	mav.addObject("appointments",list);
	return mav;
}
}
