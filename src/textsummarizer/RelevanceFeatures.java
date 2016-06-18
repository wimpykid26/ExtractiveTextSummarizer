/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummarizer;
import java.util.HashMap;
import java.util.Iterator;
import textsummarizer.TextSummarizer;
import static textsummarizer.TextSummarizer.m;
/**
 *
 * @author Dwaipayan
 */
public class RelevanceFeatures {
     
    public double similarity(HashMap m1,String s1,String s2)
    {
  double similarity=0.0;      
   HashMap h=new HashMap(); 
    
    HashMap h1=new HashMap();
        int count1=0;
        int count2=0;
        String w="";
        for(int i=0;i<s1.length();i++)
            
        {
            char ch=s1.charAt(i);
            
            if(ch==' '||ch=='.'||ch==',')
            {
                
                count1++;
                try
                {
                int val=(int)h.get(w);
                     h.put(w, val+1);
                    
                }
                catch(NullPointerException e)
                {
                
                    h.put(w,1);
                
               
                }
                w="";
            }
            else
            {
             if(ch=='"')   
            similarity+=1;
                w+=ch;
            }
        }
                    
            String w2="";
             for(int i=0;i<s2.length();i++)
            
        {
            count2++;
            char ch=s2.charAt(i);
            if(ch==' ')
            {
                try
                {
                int val=(int)h1.get(w2);
                     h1.put(w2, val+1);
                }
                catch(NullPointerException e)
                {
                
                    h1.put(w2,1);
                
               
                }
                w2="";
            }
            else
                w2+=ch;
            
        }
   
  int arr1[]=new int[1000]; 
  int arr2[]=new int[1000];
  int y3=0;
  String words[]=new String[2000];
  Iterator iterator1 = h.keySet().iterator();
    int y=0;
    int y1=0;
  while(iterator1.hasNext()){
  Object key1   = iterator1.next();
  words[y3++]=key1.toString();
  int value1 =(int)h.get(key1);
  arr1[y++]=value1;
  try
  {
   int value2 =(int)h1.get(key1);
   arr2[y1++]=value2;
  }
  catch(NullPointerException e)
  {
      
      arr2[y1++]=0;
  }
  
}
   Iterator iterator2 = h1.keySet().iterator();
   while(iterator2.hasNext()){
  Object key1   = iterator2.next();
  
  int value1 =(int)h1.get(key1);
  
  try
  {
   int value2 =(int)h.get(key1);
   
  }
  catch(NullPointerException e)
  {
      words[y3++]=key1.toString();
  arr2[y1++]=value1;    
      arr1[y++]=0;
  }
  
}
   double den1=0.0;
   double den2=0.0;
   double num1=0.0;
  for(int i=0;i<y3;i++)
  {
      num1=num1+(arr1[i]*arr2[i]);
      den1=den1+Math.pow(arr1[i],2);
      den2=den2+Math.pow(arr2[i],2);
  }
  similarity=num1/(Math.sqrt(den1)+Math.sqrt(den2));
  
        return similarity;
    }
    public void calculate(double matr2[][],String sentences[],double first_rel[],double first_para_rel[],HashMap m,int count)
    {
        String sen1=sentences[0];
        String para1=sentences[0];
        for(int i=1;i<count;i++)
        {
            
            first_rel[i]=similarity(m,sen1,sentences[i]);
            if(matr2[i][0]==1)
            {
               
                para1=sentences[i];
                continue;
            }
            first_para_rel[i]=similarity(m,para1,sentences[i]);
            
        }
    }
    
}
