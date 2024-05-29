package com.p1.klu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;



@Controller
public class SpringController {

  
  @RequestMapping(value = "/trigger", method = RequestMethod.GET)
  @ResponseBody
   public String fun1() {
     return "welcome";
   }

  @GetMapping("/sender/{a}")
  @ResponseBody
  public String sender(@PathVariable("a")double a)
  {
	  String result=null;
	try {
		Currency c1=new Currency();
		c1.setA(a);
		
		URL url=new URL("http://localhost:8082/recevier/"+c1.getA());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept","application/json");
		con.setUseCaches(false);
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		result = br.readLine();
		
	} catch (Exception e) {
		e.printStackTrace();
	}  
	return result;
  }
  

  @GetMapping("/sender2/{a}")
  @ResponseBody
  public String sender2(@PathVariable("a") double a) {
    String result = null;
    try {
      Currency c1 = new Currency();
      c1.setA(a);
      URL url = new URL("http://localhost:8082/receiver");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Accept", "application/json");
      con.setRequestProperty("Content-Type","application/json");
      con.setUseCaches(false);
      con.setDoOutput(true);
      DataOutputStream dw = new DataOutputStream(con.getOutputStream());
      dw.writeBytes(new Gson().toJson(c1));
      dw.flush();
      dw.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      result = br.readLine();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
}