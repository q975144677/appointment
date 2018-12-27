package springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springboot.pojo.Item;

public interface ItemService {
	 void delete(int id);
	 void insert(Item item );
	 void update(Item item );
	 List<Item> list();
	 Item get(int id);
	 List<Item> list(int cid);
}
