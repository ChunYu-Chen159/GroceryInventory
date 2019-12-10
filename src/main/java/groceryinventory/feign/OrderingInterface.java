package groceryinventory.feign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("ordering")
public interface OrderingInterface {
    @RequestMapping(value = "/newGroceryOrdering", method = RequestMethod.GET)
    String orderingGrocery(@RequestParam("groceryID") String groceryID, @RequestParam("quantity") String quantity);

    @RequestMapping(value = "/getGroceryFromOrderList", method = RequestMethod.GET)
    String getGroceryFromOrderList(@RequestParam("userID") String userID);

}
