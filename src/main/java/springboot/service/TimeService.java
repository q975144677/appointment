package springboot.service;


import java.util.List;

import springboot.pojo.Time;

public interface TimeService {
void delete(int id);
List<Time> list();
void insert(Time time);
Time get(int id);
}
