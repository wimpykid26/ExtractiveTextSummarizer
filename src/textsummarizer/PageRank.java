/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummarizer;

import static textsummarizer.TextSummarizer.sentences;

/**
 *
 * @author Dwaipayan
 */
public class PageRank {
    double similar[][]=new double[100][100];
    int matr[][]=new int[100][100];
    //double rank[]=new double[100];
    String arr[]=new String[100];
    String verbs[]={"a","has","it","for","and","to","the","or","at","in","for","beside","into","about","over","on","why","what","where","do","did","doing","is","but","my","because","are","am","not","had","have","has","will","would","should","we","were","i"};    
    double converttokens(String s,String s2)
{
	
	String arr1[]=new String[100];
	s=s+" ";
	s2=s2+" ";
	int c=0;
     int strno[]=new int[100];
     int flag[]=new int[100];
	//size_t found=s.find(str);
	String w="";
	for(int i=0;i<s.length();i++)

	{
		char ch=s.charAt(i);
		if(ch!=' ')
		{
			w+=ch;

		}
		else
		
		{
		int yemp=0;	
		for(int i1=0;i1<38;i1++)
		{
			if(verbs[i1]==w)
			yemp=1;
		}
		if(yemp==0)
		{
			  strno[c]=1;
			arr[c++]=w;

		
		}
		 	w="";
		}


	}
	int c2=c;
	String w2="";
	for(int i=0;i<s2.length();i++)

	{
		char ch2=s2.charAt(i);
		if(ch2!=' ')
		{
			w2+=ch2;

		}
		else
		{ int yemp1=0;	
		for(int i1=0;i1<38;i1++)
		{
			if(verbs[i1]==w2)
			yemp1=1;
		}
		if(yemp1==0)
		{
			  strno[c2]=2;
			arr[c2++]=w2;

		
		}
			w2="";
		}

	}
	String temp;
	int temp1;

        for(int i=1;i<c2;i++ )
  {

      for(int j=0;j<(c2-i);j++)
      {
          if(arr[j].compareTo(arr[j+1])>0)
           {
                temp=arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;

                temp1=strno[j];
                strno[j]=strno[j+1];
                strno[j+1]=temp1;
            }
      }
  }
  

  double similarity=0.0;
	for(int i=0;i<c2;i++)
        flag[i]=0;
    int k=0;

	while(k<c2)
    {
      //  System.out.println(arr[k]+" "+arr[k+1]+" "+flag[k]+" "+flag[k+1]+" "+strno[k]+" "+strno[k+1]);
        if(arr[k]!=null&&arr[k+1]!=null)
        {
        if((arr[k].compareTo(arr[k+1])==0)&&(strno[k]!=strno[k+1])&&(flag[k]==0)&&(flag[k+1]==0))
        {
        	
            similarity+=arr[k].length();
            flag[k]=1;
            flag[k+1]=1;


        }
      
    }
          k++;
    }
    	similarity=similarity/(Math.log(s.length())+Math.log(s2.length()));
//cout<<"similarity between "<<s<<" "<<s2<<similarity<<endl;
//cout<<similarity<<endl;
return similarity;

}

    void calculate(String sentence[],double rank[])
    {
        int i=0;
        int count=0;
       while(sentence[i]!=null)
       {
           int j=0;
            while(sentence[j]!=null)
            {
                if(i!=j)
                {
                double s=converttokens(sentence[i],sentence[j]);
       //         System.out.println("similarity between"+sentence[i]+"and"+sentence[j]+"is"+s);
                if(s>0)
                {
                    matr[i][j]=1;
                    similar[i][j]=s;
            }
                }
                else
                {
                    matr[i][j]=0;
                    similar[i][j]=0;
                }
                j++;
        }
            i++;
    }
       count=i;
      /* System.out.println("The similarity matrix is");
       for(int i3=0;i3<count;i3++)
       {
           for(int j3=0;j3<count;j3++)
           {
               System.out.print(similar[i3][j3]+" ");
           }
           System.out.println();
       }
       System.out.println("The connection matrix is");
       for(int i3=0;i3<count;i3++)
       {
           for(int j3=0;j3<count;j3++)
           {
               System.out.print(matr[i3][j3]+" ");
           }
           System.out.println();
       }*/
        pagerank(sentence,count,rank);
}
    void pagerank(String sentence[],int c,double rank[])
{
	
	
	for(int i=0;i<c;i++)
		
		rank[i]=1;
		String sentence1="";
		double sum,denSum,Wji,Wjk,PRj;
		double sum1,quant1,count1;
		int i,j,k;
	int v;
    //cout<<c;
	for (v = 0; v < c; ++v)
    {
    		   sum=0.0;
        //struct AdjListNode* pCrawlj = graph->array[v].head;
        int pCrawlj=v;
       
        while (sentence[pCrawlj]!=null)
        {
            if(matr[v][pCrawlj]==1)
            {
        	Wji=similar[v][pCrawlj];
		   	PRj=rank[pCrawlj];
		   	denSum=0.0;
		   	
        	
        
            
            // struct AdjListNode* pCrawlk = graph->array[pCrawlj->dest].head;
             int pCrawlk=pCrawlj;
             while(sentence[pCrawlk]!=null)
             {
             	  		Wjk=similar[pCrawlj][pCrawlk];
		   		denSum+=Wjk;
		 
          //   	cout<<"k is "<<k;
             	pCrawlk++;
			 }
			 	   if(denSum>0)
			   sum=sum+(Wji*PRj)/denSum;
		 
			 pCrawlj++;
        }
            else
            {
                pCrawlj++;
            }
        }
        sentence1=sentence[v];
          quant1=(1-0.85)+0.85*sum;
          rank[v]=quant1;


}
     /*   for(int i7=0;i7<c;i7++)
        {
            System.out.println(sentence[i7]+" "+rank[i7]);
        }*/
}
}
    
