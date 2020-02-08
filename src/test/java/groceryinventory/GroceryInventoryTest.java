package groceryinventory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;




@RunWith(SpringRunner.class)
@SpringBootTest
public class GroceryInventoryTest 
{

	private MockMvc mockMvc;

	@Autowired
	private GroceryInventoryController groceryInventoryController;

	@MockBean
	private GroceryInventory groceryInventory;


	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.groceryInventoryController).build();// Standalone context

	}

	@Test
	public void testGetAPI() throws Exception {
		//Mocking
		GroceryInventory groceryInventoryMock = mock(GroceryInventory.class);
		when(groceryInventoryMock.getGroceryByID("ID")).thenReturn(new String("123456"));

		// verify(groceryInventoryMock).getGroceryByID("ID");

/*		ResultActions resultActions = mockMvc.perform(get("/getGroceryByID").contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
			//.andExpect(jsonPath("ID", is("123456")));*/
	}

	
	//controller's test
	
	@Test
	public void testIndex()
	{
		System.out.println("測試連線是否成功");
		assertEquals("success", groceryInventoryController.index());
	}
/*
	@Test
	public void testGetGrocery()
	{
		System.out.println("測試是否拿到Grocery資料");
		assertTrue(isJSONValid(groceryInventory.getGrocery()));
	}

	@Test
	public void testGetGroceryByID()
	{
		System.out.println("測試是否可用ID拿到資料");
		String ID = "5c49e70e212d8d18c0fccd55";
		assertTrue(isJSONValid(groceryInventory.getGroceryByID(ID)));
	}
	
	@Test
	public void testGetNotification()
	{
		System.out.println("測試是否拿的到通知");
		assertTrue(isJSONValid(groceryInventory.getNotification("1")));
	}
	
	@Test
	public void testGetGroceryFromOrderList()
	{
		System.out.println("測試是否有拿到已購買清單");
		System.out.println(groceryInventory.getGroceryFromOrderList("1"));
		assertTrue(isJSONValid(groceryInventory.getGroceryFromOrderList("1")));
	}
	
	
	public boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}*/
}
