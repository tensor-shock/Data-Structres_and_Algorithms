import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;


class Pair implements Comparable<Pair>{  
Integer first,second;  

Pair(Integer first,Integer second){  
this.first=first;
this.second=second;  
}  
public int compareTo(Pair p){  
if(second==p.second)  
{
	if(first<p.first)
	{
		return -1;
	}
	else 
	{
		return 0;
	}
}  
else 
{
	if(second<p.second)
		return -1;
	else
		return 1;
}  
}  
}
class A4_2019MT10718{

	static int vertexcount;
	static ArrayList<ArrayList<Pair> > edgelist;
	static HashMap<String, Integer> vertextoindex = new HashMap<>();
	static HashMap<Integer, String> indextovertex = new HashMap<>();
	static Vector<String> vertex= new Vector<String>(); 
	static int[] neighboursize;
	static int[] vis;

	public static void mergesort(int arr[],int l,int r)
	{
		if(l>=r)
			return;
		int mid=(l+r)/2;
		mergesort(arr,l,mid);
		mergesort(arr,mid+1,r);
		merge(arr,l,mid,r);
	}

	public static void merge(int arr[], int l, int mid, int r)
	{
		if(l==r)
			return;
		int i,j,k,n;
		i=l;
		j=mid+1;
		k=0;
		n=r-l+1;
		int abs[]=new int[n];
		while(i<=mid && j<=r)
		{
			if(neighboursize[arr[i]]<neighboursize[arr[j]])
			{
				abs[k]=arr[i];	
				i++;				
			}
			else if(neighboursize[arr[i]]==neighboursize[arr[j]])
			{
				String a,b;
            	a=indextovertex.get(arr[i]);
            	b=indextovertex.get(arr[j]);
            	if(a.compareTo(b)<0)
            	{
            		abs[k]=arr[i];
            		i++;               		
            	}
            	else
            	{
            		abs[k]=arr[j];
					j++;		
            	}	
			}
			else
			{
				abs[k]=arr[j];
				j++;
			}
			k++;
		}
		while(k<n && i<=mid)
		{
			abs[k]=arr[i];
			k++;
			i++;
		}
		while(k<n && j<=r)
		{
			abs[k]=arr[j];
			k++;
			j++;
		}
		
		for(i=l;i<=r;i++)
		{
			arr[i]=abs[i-l];
		}
	}

	public static void mergesort2(Vector<Integer> arr,int l,int r)
	{
		if(l>=r)
			return;
		int mid=(l+r)/2;
		mergesort2(arr,l,mid);
		mergesort2(arr,mid+1,r);
		merge2(arr,l,mid,r);
	}

	public static void merge2(Vector<Integer> arr, int l, int mid, int r)
	{
		if(l==r)
			return;
		int i,j,k,n;
		i=l;
		j=mid+1;
		k=0;
		n=r-l+1;
		Vector<Integer> abs=new Vector<Integer>();
		while(i<=mid && j<=r)
		{
			String a,b;
            a=indextovertex.get(arr.get(i));
            b=indextovertex.get(arr.get(j));
            if(a.compareTo(b)>0)
           	{
            	abs.add(arr.get(i));
            	i++;               		
            }
            else
            {
            	abs.add(arr.get(j));
				j++;		
            }
			k++;
		}
		while(k<n && i<=mid)
		{
			abs.add(arr.get(i));
			k++;
			i++;
		}
		while(k<n && j<=r)
		{
			abs.add(arr.get(j));
			k++;
			j++;
		}
		
		for(i=l;i<=r;i++)
		{
			arr.set(i,abs.get(i-l));
		}
	}

	public static void mergesort3(Vector<Vector<Integer>> arr,int l,int r)
	{
		if(l>=r)
			return;
		int mid=(l+r)/2;
		mergesort3(arr,l,mid);
		mergesort3(arr,mid+1,r);
		merge3(arr,l,mid,r);
	}

	public static void merge3(Vector<Vector<Integer>> arr, int l, int mid, int r)
	{
		if(l==r)
			return;
		int i,j,k,n;
		i=l;
		j=mid+1;
		k=0;
		n=r-l+1;
		
		Vector<Vector<Integer>> abs=new Vector<Vector<Integer>>();
		while(i<=mid && j<=r)
		{
			if(arr.get(i).size()>arr.get(j).size())
			{
					
				abs.add(arr.get(i));
				i++;				
			}
			else if(arr.get(i).size()==arr.get(j).size())
			{
				String a,b;
            	a=indextovertex.get(arr.get(i).get(0));
            	b=indextovertex.get(arr.get(j).get(0));
            	if(a.compareTo(b)>0)
            	{
            		abs.add(arr.get(i));
            		i++;               		
            	}
            	else
            	{
            		abs.add(arr.get(j));
					j++;		
            	}	
			}
			else
			{
				abs.add(arr.get(j));
				j++;
			}
			k++;
		}
		while(k<n && i<=mid)
		{
			abs.add(arr.get(i));
			k++;
			i++;
		}
		while(k<n && j<=r)
		{
			abs.add(arr.get(j));
			k++;
			j++;
		}
		
		for(i=l;i<=r;i++)
		{
			arr.set(i,abs.get(i-l));
		}
	}


    public static void average()
    {
        int i,j,k;
        int sum=0;
        float average;
        for(i=0;i<vertexcount;i++)
        {
        	sum+=edgelist.get(i).size();
        }
        average=Math.round((sum*1.0f/vertexcount)*100)*1.0f/100;
        System.out.printf("%.2f\n",average);
    }

    public static void rank()
    {
    	int i,j,k;
  		neighboursize=new int[vertexcount];
  		int[] tosort=new int[vertexcount];
  		for(i=0;i<vertexcount;i++)
        {
        	int weightsum=0;
        	int ns=edgelist.get(i).size();
        	for(j=0;j<ns;j++)
        	{
        		weightsum+=edgelist.get(i).get(j).second;
        	}
        	neighboursize[i]=weightsum;
        	tosort[i]=i;
        }
  		mergesort(tosort,0,vertexcount-1);
  		
  		for(i=vertexcount-1;i>=0;i--)
  		{
  			int y=tosort[i];
  			if(i!=0)
   			System.out.printf("%s,",indextovertex.get(y));
  			else
  			System.out.printf("%s",indextovertex.get(y));
  		}
  		System.out.printf("\n");
    }

    public static void independent_storylines_dfs()
    {
    	vis=new int[vertexcount];
    	int i,j,k;
    	for(i=0;i<vertexcount;i++)
    	{
    		vis[i]=0;
    	}
    	Vector<Vector<Integer>> ans=new Vector<Vector<Integer>>();
    	neighboursize=new int[vertexcount];
  		
  		for(i=0;i<vertexcount;i++)
        {
        	int weightsum=0;
        	int ns=edgelist.get(i).size();
        	for(j=0;j<ns;j++)
        	{
        		weightsum+=edgelist.get(i).get(j).second;
        	}
        	neighboursize[i]=weightsum;
        	
        }
    	for(i=0;i<vertexcount;i++)
    	{
    		if(vis[i]==0)
    		{
    			Vector<Integer> story=new Vector<Integer>();
    			dfs(i,story);
    			mergesort2(story,0,story.size()-1);
    			ans.add(story);
    		}
    	}
    	mergesort3(ans,0,ans.size()-1); 
    	
    	for(i=0;i<ans.size();i++)
    	{
    		
    		j=ans.get(i).size();
    		for(k=0;k<j;k++)
    		{
    			if(k!=j-1)
    			System.out.printf("%s,",indextovertex.get(ans.get(i).get(k)));
    			
    			else
    			System.out.printf("%s\n",indextovertex.get(ans.get(i).get(k)));
    		}
    	}
    	
    }

    static void dfs(int i,Vector<Integer>sto)
    {
    	vis[i]=1;
    	sto.add(i);
    	for(int j=0;j<edgelist.get(i).size();j++)
    	{
    		if(vis[edgelist.get(i).get(j).first]==0)
    		{
    			dfs(edgelist.get(i).get(j).first,sto);
    		}
    	}
    }

	public static void main(String args[]) throws FileNotFoundException,IOException
	{
		long startTime=System.nanoTime();
		Scanner sc=new Scanner(System.in);
		
		String vertexfile,edgefile,func;
		
		vertexfile=args[0];
		edgefile=args[1];
		func=args[2];
		int linecount=0;
	    File file = new File(vertexfile);
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    String line = "";
	    String[] tempArr=new String[3];
	    while((line = br.readLine()) != null) {
	      
	    	if(linecount==0)
		    {
		      	linecount++;
		       	continue;
		    }
		    linecount++;

			int startpos=0;
			int linesz=line.length();
			for(int poo=0;poo<2;poo++)
			{
				String text="";
				while( startpos!=linesz && line.charAt(startpos)==',' )
					startpos++;
				if(line.charAt(startpos)=='\"')
				{
					
					startpos++;
				
					while(startpos!=linesz && line.charAt(startpos)!='\"' )
					{
						text+=line.charAt(startpos);
						startpos++;
					}

					startpos++;
				
				}
				else
				{
					while(startpos!=linesz && line.charAt(startpos)!=',' )
					{
						text+=line.charAt(startpos);
						startpos++;
					}	
				}
				tempArr[poo]=text;
				
			}
	        vertex.add(tempArr[1]);
	    }    
	    br.close();
	    
        
        vertexcount=vertex.size();
        
        for(int i=0;i<vertex.size();++i)
        {
        	
        	vertextoindex.put(vertex.get(i), i);
        	indextovertex.put(i,vertex.get(i));
        }
 		
 		file = new File(edgefile);
	    fr = new FileReader(file);
	    br = new BufferedReader(fr);
	    line = "";
	    
	    edgelist = new ArrayList<ArrayList<Pair> >(vertexcount+5);

	    for (int i = 0; i < vertexcount+5; i++) 
        edgelist.add(new ArrayList<Pair>());
	    
	    linecount=0;
	    while((line = br.readLine()) != null) {
	          
		    if(linecount==0)
		    {
		      	linecount++;
		       	continue;
		    }
		    linecount++;
		    
		    int startpos=0;
			int linesz=line.length();
			for(int poo=0;poo<3;poo++)
			{
				String text="";
				while( startpos!=linesz && line.charAt(startpos)==',' )
					startpos++;
				if(line.charAt(startpos)=='\"')
				{
					startpos++;
					while(startpos!=linesz && line.charAt(startpos)!='\"' )
					{
						text+=line.charAt(startpos);
						startpos++;
					}
					startpos++;
				}
				else
				{
					while(startpos!=linesz && line.charAt(startpos)!=',' )
					{
						text+=line.charAt(startpos);
						startpos++;
					}	
				}
				tempArr[poo]=text;
				
			}
		    String a=tempArr[0];
		    String b=tempArr[1];
		    Integer c=Integer.parseInt(tempArr[2]);
			Integer hasha,hashb;
		
		    hasha=vertextoindex.get(a);
		    hashb=vertextoindex.get(b);
		    edgelist.get(hasha).add(new Pair(hashb,c));
		    edgelist.get(hashb).add(new Pair(hasha,c));
		          
	    }
	    br.close(); 
        
        switch(func){
        	case "average":
        		average();
        		break;
        	case "rank":
        		rank();
        		break;
        	case "independent_storylines_dfs":
        		independent_storylines_dfs();
        		break;
        }
        long stopTime=System.nanoTime();
    	System.out.println((stopTime-startTime)/1000000000.0);
	}
}