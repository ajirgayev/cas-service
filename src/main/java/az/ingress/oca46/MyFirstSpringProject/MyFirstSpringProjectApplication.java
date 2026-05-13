package az.ingress.oca46.MyFirstSpringProject;

import az.ingress.oca46.MyFirstSpringProject.dto.request.CustomerRequestDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFirstSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstSpringProjectApplication.class, args);
//		CustomerRequestDto c = new CustomerRequestDto();
//		c.setFirstName("");
//		c.setCustomerId(123);
//		CustomerRequestDto c2 = new CustomerRequestDto(123,"Ad");
//		CustomerRequestDto cBuilder = CustomerRequestDto
//				.builder()
//				.customerId(123)
//				.firstName("Name")
//				.build();
	}
}
