package springboot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springboot.pojo.Appointment;
import springboot.pojo.Category;
import springboot.pojo.Item;
import springboot.pojo.User;
import springboot.service.AppointmentService;
import springboot.service.CategoryService;
import springboot.service.ItemService;
import springboot.service.UserService;

@RestController
public class ForeController {

	@Autowired
	ItemService itemService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	UserService userService;
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");
		}
		return new ModelAndView("redirect:/homePage");
	}
	@RequestMapping("regist")
	public ModelAndView regist() {
		ModelAndView modelAndView  = new ModelAndView("regist");
		
		return modelAndView;
		
	}
	@RequestMapping("regist_check")
	public ModelAndView regist_check(User user,HttpSession session) {
		ModelAndView modelAndView  = new ModelAndView("redirect:/homePage");
		boolean flag = userService.isExist(user);
		if(!flag) {
			userService.insert(user);
			session.setAttribute("user", user);
		}
		
		return modelAndView;
		
	}
	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView modelAndView  = new ModelAndView("login");
		
		return modelAndView;
		
	}
	@RequestMapping("login_check")
	public ModelAndView login_check(User user,HttpSession session) {
		ModelAndView modelAndView  = new ModelAndView("redirect:/homePage");
		boolean flag = userService.login(user);
		User user2 = userService.get(user.getUsername(), user.getPassword());
		if(flag) {
			session.setAttribute("user", user2);
		}
		return modelAndView;
		
	}
	@RequestMapping("homePage")
	public ModelAndView homePage() {
		ModelAndView modelAndView  = new ModelAndView("homePage");
		List<Category> list = categoryService.list();
		modelAndView.addObject("categories",list);
		List<Item> items = itemService.list();
		modelAndView.addObject("items",items);
		return modelAndView;
		
	}
	
	@RequestMapping("foreItem")
	public ModelAndView items(int cid) {
		ModelAndView modelAndView  = new ModelAndView("foreItem");
		List<Item> items = itemService.list(cid);
		modelAndView.addObject("items",items);
		return modelAndView;
		
	}
	@RequestMapping("foreAppointment")
	public ModelAndView appointment(int iid,HttpSession session) {
		ModelAndView mav = new ModelAndView("foreAppointment");
		mav.addObject("iid",iid);
		
		User user = (User)session.getAttribute("user");
		if(user!=null) {
		mav.addObject("uid",user.getId());
		System.out.println(user.getId());
		}
		
		else {
			mav.setViewName("redirect:/homePage");
		}
		return mav;
	}
	@RequestMapping("foreAppointment_check")
	public ModelAndView foreAppointment_check(Appointment appointment) {
		ModelAndView mav = new ModelAndView("redirect:/homePage");
		appointmentService.insert(appointment);
		return mav;
	}
	@RequestMapping("foreMyAppointment")
	public ModelAndView foreMyAppointment(HttpSession session) {
		ModelAndView mav = new ModelAndView("foreMyAppointment");
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			int id = user.getId();
			List<Appointment> list = appointmentService.listByUid(id);
			mav.addObject("list",list);
		}
		else {
			mav.setViewName("redirect:/homePage");
		}
		return mav;
	}
	@RequestMapping("login_inspect")
	public String login_inspect(User user) {
		User user2 = userService.get(user.getUsername(), user.getPassword());
		if(user2 != null) {
			return "success";
		}
		return String.valueOf(false);
		
	}
	@RequestMapping("regist_username_check")
	public String regist_username_check(String username) {
		User user = new User();
		user.setUsername(username);;;
		if(userService.isExist(user)) {
			return "false";
			
		}
		return "success";
	}
	
}
