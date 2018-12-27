package springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.CategoryMapper;
import springboot.pojo.Category;
import springboot.pojo.CategoryExample;
import springboot.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
CategoryMapper categoryMapper ;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
categoryMapper.insert(category );
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		CategoryExample example =new CategoryExample();
		example.setOrderByClause("id desc");
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		Category c = categoryMapper.selectByPrimaryKey(id);
		return c;
	}

}
