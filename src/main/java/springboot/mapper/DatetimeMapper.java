package springboot.mapper;

import java.util.List;
import springboot.pojo.Datetime;
import springboot.pojo.DatetimeExample;

public interface DatetimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Datetime record);

    int insertSelective(Datetime record);

    List<Datetime> selectByExample(DatetimeExample example);

    Datetime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Datetime record);

    int updateByPrimaryKey(Datetime record);
}