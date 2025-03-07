package in.DAA.PhotoPlaza;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "in.DAA.PhotoPlaza")
public class PhotoPlazaApplication {

//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}

	public static void main(String[] args) {
		SpringApplication.run(PhotoPlazaApplication.class, args);
	}

}
