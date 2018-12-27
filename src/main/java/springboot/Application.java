package springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("springboot.mapper")
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		SpringApplication.run(Application.class, args);

	}

}
