/*
==============================================================
RandomSimilarity - java version 1.0.001 
=============================================================
*/

import java.io.*;
//import java.util.*;

public class RandomSimilarity{
	public static void main(String args[]){
	try
	{		

		//Process args
			
		String path=args[0];
		int TotalSeq=Integer.parseInt(args[1]);
		
		boolean ToAlign= false;
		String	Aligned= "";
		if (args[2].toLowerCase().indexOf("y")>=0)
		{
			ToAlign= true;
			Aligned= "Aligned";
		}
		else
		{
			ToAlign= false;
			Aligned= "NotAligned";
		}
		  
		if(ToAlign)
		{
			System.out.println("\nCompute the pairwise similarities of the translations of random CDSs by aligning them. \n"); 
		}
		else
		{
			System.out.println("\nCompute the pairwise similarities of the translations of random CDSs directly, without aligning the translations.\n"); 
		}

	  	String SimilarityFile=path+"/Random-"+TotalSeq+".3-CDSs.Pro."+Aligned+".Random.Similarities.txt";
		
		//Prepare directories for the sequence and alignment files	
	
		String CDSPath=path+"/CDS";
		String ProPath=path+"/Pro";	
		String AlnPath=path+"/Aln";	
		
		File CDSDir=new File(CDSPath);
		File ProDir=new File(ProPath);
		File AlnDir=new File(AlnPath);
		
		if(!CDSDir.exists()){ CDSDir.mkdir();}
		if(!ProDir.exists()){ ProDir.mkdir();}
		if(!AlnDir.exists()){ AlnDir.mkdir();}
		  
		for  (int sNo=1;sNo<=TotalSeq;sNo++)
		{
			String profile=ProPath+"/"+String.valueOf(sNo)+".Pro.fas";
			File ProFile = new File(profile);
			
			if(!ProFile.exists())
			{
				System.out.println("Desired protein sequence file is not found! \n"+ profile +"  \n");
				System.out.println("Before using this program, you must produce "+ TotalSeq +" x 3 random CDSs using RandomCDSs.java firstly, which produce random CDSs and translate them into protein sequences. \n\n");
				System.exit(0);
			}
		}
		
		System.out.println("Protein sequence file found:"+ TotalSeq +"  \n");
		
		String[] sName= new String[100000];
		String[] sData= new String[100000]; 
		String[] sData1= new String[100000]; 
		String[] sData2= new String[100000]; 
		
		int i=0,j=0;
		FileReader fr;
		BufferedReader br;
		String record;
		
		if (ToAlign)
		{
			// Run ClustalW to align the three translations
			
			System.out.println("\nCalling ClustalW to align unified sequences: \n");  
		
			for  (int sNo=1;sNo<=TotalSeq;sNo++)
			{
				
				String input=ProPath+"/"+String.valueOf(sNo)+".Pro.fas";
				String output=AlnPath+"/"+String.valueOf(sNo)+".Pro.ClustalW.fas";
				
				String exe= "clustalw2 -INFILE=" + input + " -TYPE=PROTEIN" + " -OUTFILE=" + output + " -OUTPUT=PIR";
				System.out.println(exe);

				Process p = Runtime.getRuntime().exec(exe);
				
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				String str;
				
				while(( str = br.readLine()) != null)
				{
					System.out.println(str);
				}

				int exitCode = p.waitFor();
				
				while(( str = br.readLine()) != null)
				{
					System.out.println(str);
				}

				if (exitCode == 0) 
				{
					System.out.println("\nClustal Alignment SUCCESS!\n");
				} 
				else 
				{
					System.err.println("\nClustal Alignment ERROR! (Error Number:" + exitCode+")\n");
					System.exit(0);
				}
			}
		}
		
	//Compute Similarity
	
		//Preparing the scoring matrix Gon250
	   	String allAAs="ACDEFGHIKLMNPQRSTVWY*";
			String[] S=new String [22];
			S[1]="A,24,5,-3,0,-23,5,-8,-8,-4,-12,-7,-3,3,-2,-6,11,6,1,-36,-22,0,";
			S[2]="C,5,115,-32,-30,-8,-20,-13,-11,-28,-15,-9,-18,-31,-24,-22,1,-5,0,-10,-5,0,";
			S[3]="D,-3,-32,47,27,-45,1,4,-38,5,-40,-30,22,-7,9,-3,5,0,-29,-52,-28,0,";
			S[4]="E,0,-30,27,36,-39,-8,4,-27,12,-28,-20,9,-5,17,4,2,-1,-19,-43,-27,0,";
			S[5]="F,-23,-8,-45,-39,70,-52,-1,10,-33,20,16,-31,-38,-26,-32,-28,-22,1,36,51,0,";
			S[6]="G,5,-20,1,-8,-52,66,-14,-45,-11,-44,-35,4,-16,-10,-10,4,-11,-33,-40,-40,0,";
			S[7]="H,-8,-13,4,4,-1,-14,60,-22,6,-19,-13,12,-11,12,6,-2,-3,-20,-8,22,0,";
			S[8]="I,-8,-11,-38,-27,10,-45,-22,40,-21,28,25,-28,-26,-19,-24,-18,-6,31,-18,-7,0,";
			S[9]="K,-4,-28,5,12,-33,-11,6,-21,32,-21,-14,8,-6,15,27,1,1,-17,-35,-21,0,";
			S[10]="L,-12,-15,-40,-28,20,-44,-19,28,-21,40,28,-30,-23,-16,-22,-21,-13,18,-7,0,0,";
			S[11]="M,-7,-9,-30,-20,16,-35,-13,25,-14,28,43,-22,-24,-10,-17,-14,-6,16,-10,-2,0,";
			S[12]="N,-3,-18,22,9,-31,4,12,-28,8,-30,-22,38,-9,7,3,9,5,-22,-36,-14,0,";
			S[13]="P,3,-31,-7,-5,-38,-16,-11,-26,-6,-23,-24,-9,76,-2,-9,4,1,-18,-50,-31,0,";
			S[14]="Q,-2,-24,9,17,-26,-10,12,-19,15,-16,-10,7,-2,27,15,2,0,-15,-27,-17,0,";
			S[15]="R,-6,-22,-3,4,-32,-10,6,-24,27,-22,-17,3,-9,15,47,-2,-2,-20,-16,-18,0,";
			S[16]="S,11,1,5,2,-28,4,-2,-18,1,-21,-14,9,4,2,-2,22,15,-10,-33,-19,0,";
			S[17]="T,6,-5,0,-1,-22,-11,-3,-6,1,-13,-6,5,1,0,-2,15,25,0,-35,-19,0,";
			S[18]="V,1,0,-29,-19,1,-33,-20,31,-17,18,16,-22,-18,-15,-20,-10,0,34,-26,-11,0,";
			S[19]="W,-36,-10,-52,-43,36,-40,-8,-18,-35,-7,-10,-36,-50,-27,-16,-33,-35,-26,142,41,0,";
			S[20]="Y,-22,-5,-28,-27,51,-40,22,-7,-21,0,-2,-14,-31,-17,-18,-19,-19,-11,41,78,0,";
			S[21]="*,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";

			int [][] ScoreMatrix = new int [22][22];
			for ( i=1;i<=21;i++)
			{
				int pos=S[i].indexOf(",")+1;
				S[i]=S[i].substring(pos, S[i].length());
				for ( j=1;j<=21;j++)
				{
					pos=S[i].indexOf(",")+1;
					String ScoreStr=S[i].substring(0, pos-1);
					ScoreMatrix[i][j]=Integer.valueOf(ScoreStr);
					S[i]=S[i].substring(pos, S[i].length());
					//System.out.print(ScoreMatrix[i][j]+"\t");
				}
				//System.out.print("\n");
			}

	   // Preparing Output file
	   
 		BufferedWriter out=new BufferedWriter(new FileWriter(SimilarityFile));

		String profile="";
 		String[] ProSeq= new String[4]; 
 		
        System.out.println("No\tLength\tsAA-1-2\tsAA-1-3\tsAA-2-3\tS-1-2\tS-1-3\tS-2-3\tAverage\tGaps\r\n"); 

		out.write(SimilarityFile+"\r\n");
		out.write("Total Number of Sequences: "+String.valueOf(TotalSeq)+"\r\n");
		out.write("No\tLength\tsAA-1-2\tsAA-1-3\tsAA-2-3\tS-1-2\tS-1-3\tS-2-3\tAverage\tGaps\r\n");

		for  (int sNo=1;sNo<=TotalSeq;sNo++)
		{
			if (ToAlign)
			{profile=AlnPath+"/"+String.valueOf(sNo)+".Pro.ClustalW.fas";}
			else 
			{profile=ProPath+"/"+String.valueOf(sNo)+".Pro.fas";}
				
			//Read the Protein sequences for each ORF 		
			
			fr=new FileReader(profile);
			br=new BufferedReader(fr);
			record=new String();
			
			i=0;j=0;
			
			ProSeq[1]="";
			ProSeq[2]="";
			ProSeq[3]="";	
			
			while((record=br.readLine()) != null){
				
				String rec = record.trim();
				
				if(rec.length() > 0)
				{
	
					char rec_char[]=rec.toCharArray();

					//Name of the Sequence
					if(rec_char[0]=='>')
					{
						i++;
						//sName[i]=rec.substring(1);
						sData[i]="";
						if (i>1) 
						{
							//System.out.print(ProSeq[i-1].length()+" Sites\n");
						}
						//System.out.print("Sequence "+i+": "+sName[i]+", ");
					}
					else
					{
						ProSeq[i]=ProSeq[i]+rec;
					}
				}
			}
		//if (i>1) {System.out.print(ProSeq[i].length()+" Sites\n");}
		
		fr.close();
		
		// Compute Similarity
 		
        String iSeq="";
        String jSeq = ""; 
        String iAA="";
        String jAA = ""; 
   		int [] Gaps=new int [4];
		double TotalGaps=0.0;	           
		double FSS=0.0;	
  		int [][]SimilarAAs=new int [4][4];
		double [][]Similarity=new double [4][4];	            
		double MeanSimilarity=0.0;	
		
		int AlnLen=ProSeq[1].length();
     
        for (i = 1; i <= 3; i++)
        {
			iSeq = ProSeq[i];
			
			Gaps[i]= countOf(iSeq, "-");
 		   
			for (j = i+1; j <=3; j++)
            {
	            jSeq = ProSeq[j];
				SimilarAAs[i][j]=0;
				Similarity[i][j]=0.0;
				
		        for (int Pos = 0; Pos < AlnLen; Pos++)
		        {
	                iAA = iSeq.substring(Pos,Pos+1);
	                jAA = jSeq.substring(Pos,Pos+1);
	                               	                
	        		int AA1No=allAAs.indexOf(iAA)+1;
	        		int AA2No=allAAs.indexOf(jAA)+1;
					
	        		if (AA1No>0&&AA2No>0){FSS=ScoreMatrix[AA1No][AA2No];} else { FSS = -1;}	
					
	        		if (FSS>=0) {SimilarAAs[i][j]++;  }
					
	        		//System.out.print(Pos+": AA1: "+iAA+": "+String.valueOf(AA1No));
	        		//System.out.print(" --> AA2: "+jAA+": "+String.valueOf(AA2No));
	        		//System.out.print(", FSS: "+String.valueOf(FSS));
	        		//System.out.print(", SimilarAAs: "+String.valueOf(SimilarAAs[i][j])+"\n");
				}
				
				Similarity[i][j] = (double) SimilarAAs[i][j]/AlnLen;
			}
 		}
         
			MeanSimilarity=(Similarity[1][2]+Similarity[1][3])/2.0;
			TotalGaps = Gaps[1]+Gaps[2]+Gaps[3];
			
			//output results to file
			
			out.write(sNo+"\t"); 
			//out.write(sName[sNo]+"\t"); 
			out.write(String.valueOf(AlnLen)+"\t"); 
			
			for (i = 1; i <= 3; i++)
			{
				for (j = i+1; j <=3; j++)
				{
					out.write(String.valueOf(SimilarAAs[i][j])+"\t");
				}
			}
				
			for (i = 1; i <= 3; i++)
			{
				for (j = i+1; j <=3; j++)
				{
					out.write(String.valueOf(Similarity[i][j])+"\t"); 
				}
			}
			out.write(String.valueOf(MeanSimilarity)+"\t");	
	        out.write(String.valueOf(TotalGaps)+"\r\n "); 
			
			//output results to screen
			
			System.out.print(sNo+"\t"); 
			//System.out.print(sName[sNo]+"\t"); 
			System.out.print(String.valueOf(AlnLen)+"\t"); 
			
			 for (i = 1; i <= 3; i++)
			{
				for (j = i+1; j <=3; j++)
				{
					System.out.print(String.valueOf(SimilarAAs[i][j])+"\t");
				}
			}
	
			for (i = 1; i <= 3; i++)
			{
				for (j = i+1; j <=3; j++)
				{
					System.out.print(String.valueOf(Similarity[i][j])+"\t"); 
				}
			}
			System.out.print(String.valueOf(MeanSimilarity)+"\t");	
	        System.out.print(String.valueOf(TotalGaps)+"\r\n "); 
		}
		
		out.close();
		System.out.println("\nComputing completed successfully! \n"); 
		System.out.println("\tResults saved in file:\t"+SimilarityFile+"\n"); 
 	 
	}
	catch(Exception e)
	{
		System.out.println("\nUsage: RandomSimilarity <Path> <Number of CDSs> <[ToAlign=]Yes/No>\n"); 
		System.out.println("\nSomething is wrong: \n"); 
		System.out.println(e);
		e.printStackTrace();
	}
}
	
public static int countOf (String s, String c) 
{
    return s.length() - s.replace(c, "").length();
}

}
/*
=============================================================
This software is released under GNU/GPL license
Copyright (c) 2015, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
website: http://www.DNAplusPro.com
================================================================

*/