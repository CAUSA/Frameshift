/*
=============================================================
This software is released under GNU/GPL license
Copyright (c) 2011, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 
Pedamallu Chandra Sekhar  @ The Broad Institute and DFCI

*To whom correspondences should be addressed
email: xiaolong@ouc.edu.cn
website: http://www.CAUSA.com
================================================================

*/

import java.io.*;
//import java.util.*;

public class CountGenes{
   
	public static void main(String args[]){

		try{
            //accept only one args - the input file name for coding DNA sequences 
			
			CountGenes FAobj=new CountGenes();

			//Read the coding DNA sequences in fasta file
			
			System.out.println("Reading FASTA sequence file");
			
			FileReader fr=new FileReader(args[0]);
			BufferedReader br=new BufferedReader(fr);
			String record=new String();
			int TotalSeq;
			String[] SeqName= new String[1000000];
			//String[] SeqData= new String[1000000]; 
			
			int i=0,j=0;
			while((record=br.readLine()) != null){
				
				String rec = record.trim();
				
				if(rec.length() > 0){

						char rec_char[]=rec.toCharArray();

						//Name of the Sequence
						if(rec_char[0]=='>'){
							i++;
							System.out.println("Sequence "+i+": "+SeqName[i]+", ");

						}
						else{
							//SeqData[i]=SeqData[i]+rec;
						}
				}

			}
			//if (i>1) {System.out.println(SeqData[i].length()+" Sites\n");}
			TotalSeq=i;
			fr.close();
		
	   // Output the number of sequences
     
	  	String SimilarityFile=args[0]+".NumberOfGenes.txt";
		
		BufferedWriter out=new BufferedWriter(new FileWriter(SimilarityFile));

		String[] Alignment= new String[4]; 
         System.out.println("Total Number of Sequences: "+String.valueOf(TotalSeq)+"\r\n");
		out.write("Total Number of Sequences: "+String.valueOf(TotalSeq)+"\r\n");
		
	out.close();
 
		}catch(Exception e){
			System.out.println("\nUsage: FrameshiftAlign <InputFile (CDSs in fasta format)>\n"); 
			System.out.println(e);
			e.printStackTrace();
			}
	}


}
