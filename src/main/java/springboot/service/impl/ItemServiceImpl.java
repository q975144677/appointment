package springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.ItemMapper;
import springboot.pojo.Category;
import springboot.pojo.Item;
import springboot.pojo.ItemExample;
import springboot.service.CategoryService;
import springboot.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
@Autowired
CategoryService categoryService;
	
	@Autowired
ItemMapper itemMapper;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		itemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Item item) {
		// TODO Auto-generated method stub
itemMapper.insert(item);
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public List<Item> list() {
		// TODO Auto-generated method stub
		ItemExample example  = new ItemExample();
		example.setOrderByClause("id desc");
		List<Item> list = itemMapper.selectByExample(example);set(list);
		return list;
	}

	@Override
	public Item get(int id) {
		// TODO Auto-generated method stub
		
		return itemMapper.selectByPrimaryKey(id);
	}
	@Override
		public List<Item> list(int cid) {
			// TODO Auto-generated method stub
		ItemExample example  = new ItemExample();
		example.setOrderByClause("id desc");
		example.createCriteria().andCidEqualTo(cid);
		List<Item> list = itemMapper.selectByExample(example);
	set(list);
		return list;
	
		}
	
	public void set(Item item) {
		int cid = item.getCid();
		Category c = categoryService.get(cid);
		item.setCategory(c);
	}
	public void set(List<Item> list) {
		for(Item i : list) {
			set(i);
		}
	}
	
	
}
