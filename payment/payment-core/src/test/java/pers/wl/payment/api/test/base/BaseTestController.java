/**
 * 
 */
package pers.wl.payment.api.test.base;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author wuliang
 * @Date 2019 May 12, 2019 5:38:23 PM
 * @version 1.0.0
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
// @AutoConfigureMockMvc //(用这种注解方式会被登录过滤器拦截掉)
public class BaseTestController {

	@Autowired
	private WebApplicationContext context;

	MockMvc mockMvc;

	@Before
	public void before() {
		// 可以对所有的controller来进行测试
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * get请求
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public String get(String uri) throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		return content;
	}

	/**
	 * post请求（json参数）
	 * 
	 * @param uri
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String postJson(String uri, String jsonParams) throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(jsonParams).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		return content;
	}
}
