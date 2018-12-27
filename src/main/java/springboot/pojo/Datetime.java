package springboot.pojo;

import java.util.Date;
import java.util.List;

public class Datetime {
    private Integer id;

    private Date date;
    //自己新增的字段
    private String dayOfWeek;
    
    private String newDate;
    
private List<Time> times;



    public List<Time> getTimes() {
	return times;
}

public void setTimes(List<Time> times) {
	this.times = times;
}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}