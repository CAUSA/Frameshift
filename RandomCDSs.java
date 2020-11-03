/*
==============================================================
RandomCDSs - java version 1.0.001 

Produce and translate CDS files; 
Each CDS file contain three independent, random CDSs; 
Each CDS containing 500 sense codons.

Usage: 
 
java RandomCDSs <\PATH\TO\CDS\FILE> <Number of CDSs> 		
==============================================================

*/

import java.io.*;
//import java.util.*;

public class RandomCDSs{
	public static void main(String args[]){

	try
	{
        //accept args  
			
		RandomCDSs FAobj=new RandomCDSs();
		Translation Trobj=new Translation();
			
		String path=args[0];
		int TotalSeq=Integer.parseInt(args[1]);
		
		//Prepare directories for the sequence and alignment files	
	
		String CDSPath=path+"/CDS";
		String ProPath=path+"/Pro";	
		
		File CDSDir=new File(CDSPath);
		File ProDir=new File(ProPath);
		
		if(!CDSDir.exists()){ CDSDir.mkdir();}
		if(!ProDir.exists()){ ProDir.mkdir();}
	
		if (TotalSeq<10) 
		{
			System.out.println("The number of sequences should be greater than 10.");
			System.exit(0);
		}
		if (TotalSeq>100000) 
		{
			System.out.println("Too many sequences.The number of sequences should be smaller than or equals to 100000.");
			System.exit(0);
		}

		String[] sName= new String[100001];
		String[] sData= new String[100001]; 
		
		FileReader fr;
		BufferedReader br;
		String record;
				
		//Produce three random CDSs and translate them into protein sequences
		
		System.out.println("Produce and translate random CDSs into protein sequences:");
			
		String AllCDSfile=path+"/Random-"+String.valueOf(TotalSeq)+".CDS.fas";
		BufferedWriter outAllCDS = new BufferedWriter(new FileWriter(AllCDSfile));
		
		for  (int sNo=1;sNo<=TotalSeq;sNo++){
					
			System.out.println("Producing and translating CDS "+sNo+": ");			
			
			String CDSfile=CDSPath+"/"+String.valueOf(sNo)+".CDS.fas";
			String profile=ProDir+"/"+String.valueOf(sNo)+".Pro.fas";
					
			BufferedWriter outCDS = new BufferedWriter(new FileWriter(CDSfile));
			BufferedWriter outPro = new BufferedWriter(new FileWriter(profile));
			
			outAllCDS.write("\r\n"+">RandomCDS-"+sNo+"\r\n");
			
			for (int n=1; n<=3; n++)
			{
				outCDS.write("\r\n"+">RandomCDS-"+sNo+"-"+n+"\r\n");			 
				outPro.write("\r\n"+">RandomCDS-"+sNo+"-"+n+"\r\n");			 
				
				int length=0;
				
				for(int i=0; i<1000; i++)
				{
					int b1=(int) (Math.random()*3+1);
					int b2=(int) (Math.random()*3+1);
					int b3=(int) (Math.random()*3+1);
					
					String B1="ATGC".substring(b1, b1+1);
					String B2="ATGC".substring(b2, b2+1);
					String B3="ATGC".substring(b3, b3+1);
		
					String codon ="";	
					String amino ="";
					
					codon=B1+B2+B3;
					amino=Translation.translate(codon);
					
					if (!amino.equals("*")) 
					{
						if (n==1) outAllCDS.write(codon);
						outCDS.write(codon);
						outPro.write(amino);
						length++;
						if (length>=500) break;
					}					
				}			
			}
			outCDS.close();
			outPro.close();
		}
		outAllCDS.close();	
		System.out.println("Produce and translate CDS files; \nEach CDS file contain three independent, random CDSs;\n Each CDS containing 500 sense codons.\n");

		System.out.println(TotalSeq +" random CDSs are produced and translated into protein sequences. \n The CDS file containing all CDSs are saved as : "+ AllCDSfile + "; \n The CDS files containg each CDS are saved in: "+ CDSDir +"; \n The translated protein sequences files are saved in : "+ProPath +"\n\n");
		
	}
	catch(Exception e)
		{
			System.out.println("Produce and translate CDS files; \nEach CDS file contain three independent, random CDSs;\n Each CDS containing 500 sense codons.\n");
			System.out.println("\nUsage: RandomCDSs <Path/to/CDS/Files> <Number of CDS sets> <[readthrough=]Y[es]/N[o]> \n"); 
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
/*
==============================================================
This software is released under GNU/GPL license
Copyright (c) 2015-2020, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
website: http://www.DNAplusPro.com
================================================================

*/
