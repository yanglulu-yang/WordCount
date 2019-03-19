package yll.com.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFrame;


/****************************
*@类名  WordCount.java
*@作者  yanglulu
*@日期  2019年3月18日 下午3:33:25
*@版本  v1.0
*@描述  
*
 **/
public class WordCount extends JFrame {
	
	public static String [] ss;
	public static TreeMap<String,Integer> tm;
	
	public void displayWordCount(){
		//字符统计
    	try {
    		
    		String line = "src/yll/com/test1/word.txt";
			File file = new File(line);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			
			//将文本中的英文词语放在集合里面
	         tm = new TreeMap<String,Integer>();
	        
	        while((line=reader.readLine())!=null){
	        	line = line.toLowerCase();//忽略大小写
	        	
	        	String[] str = line.split("[^a-zA-Z]");//过滤出只含有字母的  
	        	  
	        	for(int i = 0; i<str.length; i++){
	        		String word = str[i].trim();
	        		if(tm.containsKey(word) &&  word.length() != 0){
	        			tm.put(word, tm.get(word)+1);
	        		}else{
	        			tm.put(word, 1);
	        		}
	        	}
	        }
	        System.out.println("-------词屏统计--------");
			System.out.println("请输入您想查询的功能");
			System.out.println("1.指定单词的统计,并显示查找单词的柱状图");
			System.out.println("2.高频单词的统计");
			System.out.println("3.统计该文本所有单词数量及词频数，并能将单词及词频数按字典顺序输出到文件result.txt");
			
			Scanner input = new Scanner(System.in);
			int i = input.nextInt();
			if(i==1){
				System.out.println("请输入你想查询的单词，请用分号隔开");
				String str = input.next();
				WordEntrty we = new WordEntrty();
				 ss = we.bijiao(tm, str);
				 WordCount demo = new WordCount();
					demo.setVisible(true);
				
			}else if(i==3){
				WordEntrty we = new WordEntrty();
				we.cunfang(tm);
	            System.out.println("已存入到result.txt中，请查看result.txt文件");
			}else if(i==2){
				System.out.println("请输入要查找多少个高频词");
				int num = input.nextInt();
				WordEntrty we = new WordEntrty();
				we.gaopin(tm, num);
				
			}
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	//柱状图的显示
	public WordCount(){
		super();
		setTitle("绘制柱形图");
		setBounds(3, 200, 450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g){
		int Width = getWidth();
		int Height = getHeight();
		int leftMargin = 50;//柱形图左边界
		int topMargin = 50;//柱形图上边界
		Graphics2D g2 = (Graphics2D) g;
		int ruler = Height-topMargin;
		int rulerStep = ruler/20;//将当前的高度评分为20个单位
		g2.setColor(Color.WHITE);//绘制白色背景
		g2.fillRect(0, 0, Width, Height);//绘制矩形图
		g2.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<rulerStep;i++){
			g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
		}
		g2.setColor(Color.RED);
		int m=0;
		for(int i = 0;i<ss.length;i++){
			int value = tm.get(ss[i]);
			int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
			g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
			g2.drawString(ss[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
			m++;
		}
	}
	
}
