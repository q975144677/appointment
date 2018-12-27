package springboot.service;

import java.util.List;

import springboot.pojo.Category;

public interface CategoryService {
 void delete(int id);
 void insert(Category category );
 void update(Category category );
 List<Category> list();
 Category get(int id);
}
