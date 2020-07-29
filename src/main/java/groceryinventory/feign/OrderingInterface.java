package groceryinventory.feign;
import com.soselab.vmamvserviceclient.annotation.TargetVersion;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("ordering")
@TargetVersion("0.0.1-SNAPSHOT")
public interface OrderingInterface {
    @RequestMapping(value = "/newGroceryOrdering", method = RequestMethod.GET)
    String orderingGrocery(@RequestParam("userID") String userID, @RequestParam("groceryID") String groceryID, @RequestParam("quantity") String quantity);

    @RequestMapping(value = "/getGroceryFromOrderList", method = RequestMethod.GET)
    String getGroceryFromOrderList(@RequestParam("userID") String userID);

    @RequestMapping(value = "/getGroceryInformation", method = RequestMethod.GET)
    String getGroceryInformation(@RequestParam("userID") String userID);

}
