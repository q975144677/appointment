package springboot.service;

import java.util.List;

import springboot.pojo.Appointment;

public interface AppointmentService {
void delete(int id);
void insert(Appointment appointment );
List<Appointment> list();
Appointment get(int id);
Appointment get(int tid,int did,int iid);
void update(Appointment appointment );
List<Appointment> list(int iid);
List<Appointment> listByUid(int uid);
}
