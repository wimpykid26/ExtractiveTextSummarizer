/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummarizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.Arrays;
import textsummarizer.Stemmer;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author Dwaipayan
 */

public class TextSummarizer {
public static String sentences[]=new String[100];
public static HashMap m=new HashMap();
public static String update="";    
int graph[][]=new int[100][100];
    public static String summary="";
    static double matr2[][]=new double[100][8];
    static int count2=0;
public static double rank[]=new double[50];
public static double first_rel[]=new double[50];
public static double first_para_rel[]=new double[50];

public static String originals[]=new String[100];
    void tokenconvert(String str1)

{
    int i=0;
    String sent="";
    matr2[0][0]=matr2[0][1]=1;
    
    for(int k=0;k<str1.length();k++)
    {
        char ch=str1.charAt(k);
        if(ch=='.')
        {
            if(sent.charAt(0)=='0')
                matr2[i][0]=1;
            sentences[i++]=sent;
            sent="";
                    
        }
        else
            sent+=ch;
    }
    count2=i;
    
    
}
    public static void calculate()
    {
        HashMap ult=new HashMap();
        double ultimate[]=new double[count2];
        int ultimate2[]=new int[count2];
        PageRank obj3=new PageRank();
       obj3.calculate(sentences,rank);
       for(int k=0;k<count2;k++)
       {
           matr2[k][2]=rank[k];
       }
      RelevanceFeatures obj4=new RelevanceFeatures();
      obj4.calculate(matr2,sentences,first_rel, first_para_rel, m, count2);
      for(int k=0;k<count2;k++)
       {
           matr2[k][3]=first_rel[k];
           matr2[k][4]=first_para_rel[k];
       }
     
        System.out.println("The rank matrix is");
       for(int k=0;k<count2;k++)
       {
           double sum=matr2[k][0]+matr2[k][1]+matr2[k][2]+matr2[k][3]+matr2[k][4]+matr2[k][5]+matr2[k][6]+matr2[k][7];
          for(int j=0;j<8;j++)
          {
              ultimate[k]+=matr2[k][j];
             
              System.out.print(matr2[k][j]+" ");
          }
           
          
          
          ult.put(ultimate[k],k);
              System.out.println();
       }
      Arrays.sort(ultimate);
      int u=0;
      int ultimate3[]=new int[6];
      for(int i=count2-1;i>=0;i--)
          ultimate2[u++]=(int)ult.get(ultimate[i]);
      
    for(int i=0;i<count2;i++)
    {
        if(i<6)
            ultimate3[i]=ultimate2[i];
        System.out.println(ultimate2[i]);
    }
    Arrays.sort(ultimate3);
    for(int i=0;i<6;i++)
    {
        summary+=originals[ultimate3[i]]+'\n';
        System.out.println(ultimate3[i]+" "+originals[ultimate3[i]]);
    }
    int countword=0;
    int l=0;
     update="";
    while(countword<75&&l<summary.length())
    {
        if(summary.charAt(l)=='.'||summary.charAt(l)=='"'||summary.charAt(l)==','||summary.charAt(l)==' ')
            countword++;
        update+=summary.charAt(l);
        l++;
    }
    System.out.println("countword is"+countword);
    }
  public static void main(String[] args) {
String original="";        
String str="";
  char[] w = new char[501];
      Stemmer s = new Stemmer();
    TextSummarizer obj2=new TextSummarizer();
     try 
    {
    Scanner in = new Scanner(new FileReader("test/input.txt"));
    String sentence="";
    int m=0;
    while(in.hasNext())
    {
        
        //System.out.println(in.toString());
        String word=in.next();
        
       if(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==']')
       {
           original+=word+" "+'\n';
           originals[m++]=sentence+" "+word;
           sentence="";
       }
       else
       {
           original+=" "+word;
              sentence+=" "+word;
       }
    }
} 
catch (FileNotFoundException ex) 
{
    System.out.println("File no found");
}
    try
      {
                  
         FileInputStream in = new FileInputStream("test/input.txt");

         try
         { while(true)

           {  int ch = in.read();
          
              if (Character.isLetter((char) ch)||(char)ch=='.'||(char)ch=='\n')
              {
                 int j = 0;
                 while(true)
                 {  ch = Character.toLowerCase((char) ch);
                    w[j] = (char) ch;
                    if (j < 500) j++;
                    ch = in.read();
                    if (!(Character.isLetter((char) ch)||((char)ch=='.')||(char)ch=='\n'))
                    {
                     
                       for (int c = 0; c < j; c++) s.add(w[c]);

                 
                     
                     int i1=0;
                       s.stem();
                       {  String u;

                     
                          u = s.toString();
                          if(u.charAt(u.length()-1)=='.')
                              str+=u;
                          else if(u.charAt(u.length()-1)=='\n')
                          {
                              
                              str+=i1;
                              i1++;
                          }
                          else
                          str+=u+" ";
                          
                             
                          //System.out.print(u+"f");
                       }
                       break;
                    }
                 }
              }
              if (ch < 0) break;
              
              System.out.print((char)ch);
           }
         }
         catch (IOException e)
         {  System.out.println("error reading " );
            
         }
      }
      catch (FileNotFoundException e)
      {  System.out.println("file "  + " not found");
         
      }
     // System.out.print(original);
      try
      {
      FileInputStream fstream = new FileInputStream("test/f1.txt");
      try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream)))
      {
         String strLine;
          int i3=0;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            
            	if(strLine.compareTo("end")!=0)
	{
            
		String w4="";
		for(int k=0;k<strLine.length();k++)
		{
			char ch=strLine.charAt(k);
			if(ch!=' ')
			w4+=ch;
			else
			{
				m.put(w4,i3);
				w4="";
			}
		}
	}
	else
                {

	i3++;
                }
            }

            //Close the input stream
            br.close(); 
      }
      catch(IOException e)
      {
           System.out.println("error reading " );
      }
      }
      catch (FileNotFoundException e)
      {  System.out.println("file "  + " not found");
         
      }
     Iterator iterator = m.keySet().iterator();
    

      
      obj2.tokenconvert(str);
      obj2.calculate();
      update+=".....";
      try{
          File file=new File( "test/output.txt" );
            
    FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(update);
			fileWriter.flush();
			fileWriter.close();
      }
       catch (IOException e )
      {  System.out.println("file "  + " not found");
         
      }
          }
}

