package yll.com.test1;

import java.util.Scanner;

/****************************
*@类名  Test1.java
*@作者  yanglulu
*@日期  2019年3月18日 下午3:28:44
*@版本  v1.0
*@描述  
*
 **/
public class Test1  {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("输入文件路径：\n");			
		Scanner in=new Scanner(System.in);
		String line=in.nextLine();
		String fileName= line.trim();
		WordCount wc = new WordCount();
		//wc.displayWordCount(fileName);
		//wc.displayFrequencyWord(fileName);
	}

}
