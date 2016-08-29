package com.tech.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ibm.icu.impl.ICUService.Key;

public class RandomUtil
{
  public static String random(int size)
  {
    List<String> ranList = new ArrayList<String>();
    int r = 10000000;
    Random random = new Random();
    for (int j = 0; j < size; j++)
    {
      int a = random.nextInt(1);
      int b = random.nextInt(1);
      int c = random.nextInt(9);
      int d = random.nextInt(9);
      int e = random.nextInt(9);
      int f = random.nextInt(9);
      int g = random.nextInt(9);
      ranList.add(String.valueOf(r + (a + b + c + d + e + f + g)));
    }
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  public static String newRandomV1(int size, int price, List<String> oldRandomList)
  {
	  long start = System.currentTimeMillis();
    List<String> randomList = new ArrayList<String>();
    int r = 10000000;
    for (int i = 1; i <= price; i++) {
      if (!oldRandomList.contains(String.valueOf(r + i))) {
        randomList.add(String.valueOf(r + i));
      }
    }
    long mmmm = System.currentTimeMillis();
    long secs = (mmmm-start)/1000;
    System.out.println(secs);
    List<String> ranList = new ArrayList<String>();
    for (int j = 0; j < size; j++) {
      if (randomList.size() > 0)
      {
        int ran = new Random().nextInt(randomList.size());
        ranList.add((String)randomList.get(ran));
        randomList.remove(ran);
      }
    }
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  /**
   * 采用连续号段生成夺宝码，解决大额购买的生成效率问题
   * @param size
   * @param price
   * @param oldRandomList
   * @return
   */
	public static String newRandomV2(int size, int price, List<String> oldRandomList) {
		Map<String, Integer> randomMap = new HashMap<String, Integer>();
		int r = 10000000;
		for (int i = 1; i <= price; i++) {
			randomMap.put(String.valueOf(r + i), new Integer(0));
		}
		for (int i = 0; i < oldRandomList.size(); i++) {
			randomMap.put(oldRandomList.get(i), new Integer(1));
		}
		List<String> ranList = new ArrayList<String>();
		String[] keys = new String[randomMap.size()];
		randomMap.keySet().toArray(keys);
		Integer flag =0;
		String key = null;
		for (int i = 0, k=0; i < keys.length; i++) {
			key = keys[i];
			flag = randomMap.get(key);
			if (flag == 0) {
				ranList.add(key);
				randomMap.put(key, new Integer(1));
				k++;
			}
			if(k==size){
				break;
			}
		}
		return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
	}

  public static String newRandom(int size, int price, List<String> oldRandomList)
  {
	  //long start = System.currentTimeMillis();
	  if(size>500){
		  return newRandomV2(size, price, oldRandomList);
	  }
    Map<String,Integer> randomMap = new HashMap<String,Integer>();
    int r = 10000000;
    for (int i = 1; i <= price; i++) {
    	randomMap.put(String.valueOf(r + i), new Integer(0));
    }
    for (int i = 0; i < oldRandomList.size(); i++) {
    	randomMap.put(oldRandomList.get(i), new Integer(1));
    }
    //System.out.println("size="+randomMap.size());
    //long mmmm = System.currentTimeMillis();
    //long secs = (mmmm-start)/1000;
    //System.out.println(secs);
    List<String> ranList = new ArrayList<String>();
    for (int j = 0; j < size; j++) {
      if (randomMap.size() > 0)
      {
    	  int range = randomMap.size()-oldRandomList.size()-j;
    	  if(range<1){
    		  range=1;
    	  }
        int ran = new Random().nextInt(range);
        int max = Math.min(ran+oldRandomList.size()+size, randomMap.size());
        //System.out.println("ran="+ran+",max="+max);
        String[] keys = new String[randomMap.size()];
        randomMap.keySet().toArray(keys);
        for(int k=0,x=0;k<max;k++){
        	String key= keys[k];
        	Integer flag = randomMap.get(key);
        	if(flag==0){
        		if(x==ran){
        			//System.out.println("x="+x+",ran="+ran);
        			ranList.add(key);
        			randomMap.put(key, new Integer(1));
        			break;
        		}
        		x++;
        	}
        }
      }
    }
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  public static void main(String[] args)
  {
    List<String> oldRandomList = new ArrayList<String>();
    int r = 10000000;
    for (int i = 1; i <= 390000; i++) {
    	oldRandomList.add(String.valueOf(r + i));
    }
    /*for (int i = 390000; i <= 400000; i++) {
    	oldRandomList.add(String.valueOf(r + i));
    }*/
    String strList = newRandom(10000, 400000, oldRandomList);
    System.err.println(strList);
	  
  }
}
