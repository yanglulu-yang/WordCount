package yll.com.test1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/****************************
*@类名  WordEntrty.java
*@作者  yanglulu
*@日期  2019年3月18日 下午3:39:03
*@版本  v1.0
*@描述  
*
 **/
public class WordEntrty  {
	
	
			//查找单词之后进行与词群里面查找单词的个数
			public String[]  bijiao(TreeMap<String, Integer> tm,String s) {//在这个方法中，需要传入的是之前存入单词的集合，用户输入的字符串
				TreeMap<String,Integer> map1 = new TreeMap<String, Integer>();  
				String[] word= s.split(";");//通过分号来截取用户传入的字符串
		        int i;
		        for(i=0; i<word.length; i++) {
		        	for(Entry<String,Integer> entry : tm.entrySet()) { 
		        		if(word[i].equals(entry.getKey()))//与集合中的单词比较
		        		{
		        			map1.put(entry.getKey(), entry.getValue());
		        			System.out.println(entry.getKey() + "的个数是" + entry.getValue()); 
		        			break;
		        		}
		            } 
		        }
				return word;
		        
		    }
			//单词的存放
			public void cunfang(TreeMap<String, Integer> tm){

				//统计该文本所有单词数量及词频数，并能将单词及词频数按字典顺序输出到文件result.txt
				BufferedWriter bw = null;
	    		try {
	    			File file1 = new File("src/yll/com/test1/result.txt");
	    			if (!file1.exists()) {
	    				file1.createNewFile();
	    			}
	    			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
	    			bw = new BufferedWriter(fw);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
				
	            Iterator<String> it1 = tm.keySet().iterator();
	            while(it1.hasNext())
	            {
	            	String key = (String) it1.next();
		        	Integer value = tm.get(key);
		        	
		        	try {
						bw.write(key+"="+value+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
			}
			
			
			
			 //高频词的统计 整数k
			 public void gaopin(TreeMap<String,Integer> tm,int k){
				
				 ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(tm.entrySet());
				 Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
				  public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
				         return o2.getValue() - o1.getValue(); 
				    }  
				   }); 
				     //输出前k个数
				    for(int i = 0; i<k; i++){  
				     System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
				        }     
				    }
}
