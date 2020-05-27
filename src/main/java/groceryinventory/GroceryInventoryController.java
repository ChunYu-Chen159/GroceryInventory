package groceryinventory;

import com.soselab.vmamvserviceclient.annotation.FeignRequest;
import groceryinventory.GroceryInventory;
import groceryinventory.feign.NotificationInterface;
import groceryinventory.feign.OrderingInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "GroceryInventoryController", tags = "與周邊商品相關的所有一切都在這裡")
@RestController
public class GroceryInventoryController {
	@Autowired
	NotificationInterface notificationInterface;
	@Autowired
	OrderingInterface orderingInterface;
	
/*
	@ApiOperation(value = "測試此伺服器是否成功連線", notes = "成功連線就回傳success")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() 
    {
		return "success";
    }
*/

/*	// 模擬404
	@ApiOperation(value = "模擬404", notes = "回傳404")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/index2", method = RequestMethod.GET)
	public ResponseEntity<GroceryInventory> index2()
	{

		return ResponseEntity.notFound().build();
	}

	// 模擬回應延遲
	@ApiOperation(value = "模擬回應延遲", notes = "回應延遲")
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/index3", method = RequestMethod.GET)
	public String index3()
	{

		String result = "wait success";


		long num = (long)(Math.random() * 30);
		try {
			Thread.sleep(num);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/


	@ApiOperation(value = "取得周邊商品", notes = "列出所有周邊商品")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getGrocery", method = RequestMethod.GET)
    public String getGrocery()
    {
    	return GroceryInventory.getGrocery();
    }
	
	@ApiOperation(value = "利用ID取得某個周邊商品", notes = "列出此周邊商品")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getGroceryByID", method = RequestMethod.GET)
    public String getGroceryByID(@ApiParam(required = true, name = "ID", value = "商品編號") @RequestParam("ID") String ID)
    {
		GroceryInventory groceryInventory = new GroceryInventory();
    	return groceryInventory.getGroceryByID(ID);
    }

	@FeignRequest(client = NotificationInterface.class, method = "getNotification", parameterTypes = String.class)
	@ApiOperation(value = "取得通知", notes = "列出所有通知")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getNotification", method = RequestMethod.GET)
    public String getNotification(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String ID)
    {
		String result = "";
		
		try {
			
			result = notificationInterface.getNotification(ID);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
    }

	@FeignRequest(client = OrderingInterface.class, method = "orderingGrocery", parameterTypes = {String.class, String.class})
	@ApiOperation(value = "購買周邊商品", notes = "若購買成功則回傳購買成功")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "orderingGrocery", method = RequestMethod.GET)
    public String orderingGrocery(@ApiParam(required = true, name = "ID", value = "購買的商品編號") @RequestParam("groceryID") String ID, @ApiParam(required = true, name = "quantity", value = "購買的商品數量") @RequestParam("quantity") String quantity)
    {
		String result = "";
		
		try {
			
			result = orderingInterface.orderingGrocery(ID, quantity);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
    }

	@FeignRequest(client = OrderingInterface.class, method = "getGroceryFromOrderList", parameterTypes = String.class)
	@ApiOperation(value = "取得已購買的周邊商品", notes = "列出所有已購買的周邊商品")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getGroceryFromOrderList", method = RequestMethod.GET)
    public String getGroceryFromOrderList(@ApiParam(required = true, name = "userID", value = "使用者編號") @RequestParam("userID") String userID)
    {
    	
    	String data = "";
		try {
			
			data = orderingInterface.getGroceryFromOrderList(userID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		GroceryInventory groceryInventory = new GroceryInventory();
		return groceryInventory.getGroceryFromOrderList(userID, data);
    }
	
}



