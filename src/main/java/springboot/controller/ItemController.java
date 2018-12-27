package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springboot.pojo.Item;
import springboot.service.ItemService;

@RestController
public class ItemController {
@Autowired
ItemService itemService;
@RequestMapping("item")
public ModelAndView item(int cid) {
	ModelAndView mav = new ModelAndView("item");
	List<Item> list = itemService.list(cid);
	mav.addObject("list",list);
	mav.addObject("cid",cid);
	
	return mav;
}
@RequestMapping("item_insert")
public ModelAndView insert(Item item) {
	ModelAndView mav = new ModelAndView("redirect:/item?cid="+item.getCid());
	itemService.insert(item);
	return mav;
}

@RequestMapping("item_delete")
public ModelAndView delete(int id) {
	int cid = itemService.get(id).getCid();
	itemService.delete(id);
	
	return new ModelAndView("redirect:/item?cid="+cid);
}
@RequestMapping("item_edit")
public ModelAndView edit(int id) {
	int cid = itemService.get(id).getCid();
	ModelAndView mav = new ModelAndView("item_edit") ;
	mav.addObject("cid",cid);
	mav.addObject("id",id);
	return mav;
}
@RequestMapping("item_update")
public ModelAndView update(Item item) {
Item item2 = itemService.get(item.getId());
	int  cid = item2.getCid();
	ModelAndView mav = new ModelAndView("redirect:/item?cid="+cid);
	itemService.update(item);
	return mav;
}
}
