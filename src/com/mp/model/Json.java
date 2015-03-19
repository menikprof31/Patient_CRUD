package com.mp.model;

import java.util.ArrayList;
import java.util.List;

public class Json {
	 private int age = 52;
     private String name = "Jim Carrey";
    private JsonTwo j2 = new JsonTwo();
	private List<String> data;//<JsonTwo> data;
	
	public Json(){
		this.data = new ArrayList<String>(){ //<JsonTwo>(){
			{
				//int i = 0;
				//while(i<p.size()){
				//	add(new JsonTwo(p.get(i++)));
				//}
				add("Hey, maybe I will give you..");
                add("Excuse me, I'd like to..");
                add("Brain freeze. Alrighty Then I just..");
			}
		};
	}

}

