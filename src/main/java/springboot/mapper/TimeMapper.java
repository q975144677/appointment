package springboot.mapper;

import java.util.List;
import springboot.pojo.Time;
import springboot.pojo.TimeExample;

public interface TimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Time record);

    int insertSelective(Time record);

    List<Time> selectByExample(TimeExample example);

    Time selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKey(Time record);
}