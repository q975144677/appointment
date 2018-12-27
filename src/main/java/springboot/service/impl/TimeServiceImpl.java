package springboot.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.TimeMapper;
import springboot.pojo.Time;
import springboot.pojo.TimeExample;
import springboot.service.TimeService;
@Service
public class TimeServiceImpl implements TimeService {
@Autowired
TimeMapper timeMapper;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
timeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Time> list() {
		// TODO Auto-generated method stub
		TimeExample example = new TimeExample();
		example.setOrderByClause("id desc");
		
		return timeMapper.selectByExample(example);
	}

	@Override
	public void insert(Time time) {
		// TODO Auto-generated method stub
		timeMapper.insert(time);
	}

	@Override
	public Time get(int id) {
		// TODO Auto-generated method stub
		return timeMapper.selectByPrimaryKey(id);
	}

}
