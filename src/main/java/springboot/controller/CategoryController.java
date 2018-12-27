package springboot.controller;

import java.util.List;

import org.omg.CORBA.NVList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springboot.pojo.Category;
import springboot.service.CategoryService;
@RestController
public class CategoryController {
@Autowired
CategoryService categoryService ;


@RequestMapping("category")
public ModelAndView list() {
	ModelAndView mav = new ModelAndView("category");
	List<Category> list = categoryService.list();
	mav.addObject("list",list);
	return mav;
}
@RequestMapping("category_insert")
public ModelAndView insert(Category category ) {
	categoryService.insert(category);
	return new ModelAndView("redirect:/category");
}
@RequestMapping("category_delete")
public ModelAndView delete(int id) {
	categoryService.delete(id);
	return new ModelAndView("redirect:/category");
}
@RequestMapping("category_update")
public ModelAndView update(Category category) {
	categoryService.update(category);
	return  new ModelAndView("redirect:/category");
}
@RequestMapping("category_edit")
public ModelAndView edit(int id) {
	ModelAndView mav = new  ModelAndView("category_edit");
	mav.addObject("id" , id);
	return mav;
	
}
}
