package groceryinventory.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("notification")
public interface NotificationInterface {

    @RequestMapping(value = "/getNotification", method = RequestMethod.GET)
    String getNotification(@RequestParam("userID") String userID);

}
