package com.mp.model;

import java.util.ArrayList;
import java.util.List;

public class JsonTwo {
	
	private int age = 76;
    private String name = "Morgan Freeman";
	private List<String> datalist;
	
	public JsonTwo() {
		this.datalist = new ArrayList<String>() {
            
			/**
			 * 
			 */
			//private static final long serialVersionUID = 1L;

			{
                      add("I once heard a wise man say..");
                      add("Well, what is it today? More..");
                      add("Bruce... I'm God. Circumstances have..");
                      }
        };
  
	}
	
	/*JsonTwo(final Patient p){
		this.datalist = new ArrayList<String>(){
			{
				add(p.getPid()+"");
				add(p.getFname());
				add(p.getLname());
				add(p.getAddress());
				add(p.getCountry());
				add(p.getCity());
				
			}
		};
	}*/
	
}
