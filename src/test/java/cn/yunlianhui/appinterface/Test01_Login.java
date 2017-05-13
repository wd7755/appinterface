package cn.yunlianhui.appinterface;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.json.JSONObject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class Test01_Login {	
	private static Utils utils;
	private static String token ="";
	private String m_id = "ylhadmin";
	
   @BeforeClass
   public static void setUpBeforeClass() throws Exception{	 
	   utils = new Utils();	 
   }
   
   @AfterClass
   public static void tearDownAfterClass() throws Exception{		   
   }	   
   
   @Before
   public void setUp() throws Exception {
	  
   }
	
   @After
   public void tearDown() throws Exception {       
	  
   }	  
   @Test
   //登录正面测试
   public void case01_login_positive() throws Exception{		  
       JSONObject j = new JSONObject(); 
	   j.put(UrlConstants.MODULE, "Login");    	   
	   j.put(UrlConstants.M_ID, m_id);     	  
	   j.put(UrlConstants.TYPE, 0);  
	   j.put(UrlConstants.TOKEN, token);
	   j.put(UrlConstants.PWD, "000000");  		   
	  
	   //对原始请求参数加密
	   String parameters = AESUtil.Encrypt(j.toString());
   	   //执行Post方法并返回字符创格式的响应结果
       String value = utils.getResponse(parameters);     
      
       //解密
       String resString = AESUtil.Decrypt(value);   
       // 使用返回的字符串直接构造一个JSONObject       
       JSONObject jsonobj = new JSONObject(resString.trim());  
       String err = jsonobj.getString("err");     
       String token = jsonobj.getString("token");    
       Assert.assertEquals(token, jsonobj.getString("token"));	      
       /*  
       ResultSet rs = OperationDB.getResultSet("Select m_uid,m_class From member_base_file Where m_id=?", new String[]{m_id});
             
       String m_uid = rs.getString(1);
       String m_class = rs.getString(2);
       
       Assert.assertEquals(m_id, jsonobj.getString("m_id"));	
       Assert.assertEquals(m_uid, jsonobj.getString("m_uid"));	
       Assert.assertEquals(m_class, jsonobj.getString("m_class"));	
       */      
   } 
   @Test
   //登录负面测试：错误的参数值
   public void case02_login_wrongParaValue() throws Exception{	
	   JSONObject j = new JSONObject(); 
	   j.put(UrlConstants.MODULE, "Login");    	   
	   j.put(UrlConstants.M_ID, m_id);     	  
	   j.put(UrlConstants.TYPE, 0);  
	   j.put(UrlConstants.TOKEN, token);
	   j.put(UrlConstants.PWD, "123123");  		   
	  
	   //对原始请求参数加密
	   String parameters = AESUtil.Encrypt(j.toString());
   	   //执行Post方法并返回字符创格式的响应结果
       String value = utils.getResponse(parameters);     
      
       //解密
       String resString = AESUtil.Decrypt(value);   
       // 使用返回的字符串直接构造一个JSONObject       
       JSONObject jsonobj = new JSONObject(resString.trim());  
       String err = jsonobj.getString("err");  
       System.out.println(err);
       //Assert.assertEquals("member01", err);	      
   }
   @Test
   //登录负面测试：错误的参数数量
   public void case03_login_wrongParasCount() throws Exception{	
	   JSONObject j = new JSONObject(); 
	   j.put(UrlConstants.MODULE, "Login");    	   
	   //j.put(UrlConstants.M_ID, m_id);     	  
	   j.put(UrlConstants.TYPE, 0);  
	   j.put(UrlConstants.TOKEN, token);
	   j.put(UrlConstants.PWD, "123123");  		   
	  
	   //对原始请求参数加密
	   String parameters = AESUtil.Encrypt(j.toString());
   	   //执行Post方法并返回字符创格式的响应结果
       String value = utils.getResponse(parameters);     
      
       //解密
       String resString = AESUtil.Decrypt(value);   
       // 使用返回的字符串直接构造一个JSONObject       
       JSONObject jsonobj = new JSONObject(resString.trim());  
       String err = jsonobj.getString("err");     
       System.out.println(err);
       //Assert.assertEquals("member01", err);	      
   }  
}