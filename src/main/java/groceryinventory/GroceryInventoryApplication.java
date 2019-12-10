package groceryinventory;

import com.soselab.vmamvserviceclient.annotation.EnableVmamvClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableVmamvClient
public class GroceryInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryInventoryApplication.class, args);
	}

}

