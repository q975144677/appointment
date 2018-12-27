package springboot.service;

import java.util.Date;
import java.util.List;

import springboot.pojo.Datetime;

public interface DatetimeService {
void delete(int id);
void insert(Datetime datetime);
void insert(List<Datetime> list);
List<Datetime> list();
Datetime get(int id);
List<Datetime> listByWeek(Datetime datetime );
Datetime getByDate(Date date);
}
