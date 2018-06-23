/*
==============================================================
FrameshiftAlign - java version 2.0.001 

Frameshift Alignment

Usage: 
 
java FrameshiftAlign <\PATH\TO\FILE> <FileName (CDS files in fasta format)> 
=============================================================
This software is released under GNU/GPL license
Copyright (c) 2015, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
website: http://www.DNAplusPro.com
================================================================

*/

import java.io.*;
//import java.util.*;

public class FrameshiftAlign{
	public static void main(String args[]){

		try{
            //accept only one args - the input file name for coding DNA sequences 
			
			FrameshiftAlign FAobj=new FrameshiftAlign();

			//Reading the coding DNA sequences in fasta file
			
			System.out.println("Reading FASTA sequence file");
			
		  String path=args[0];
		  File file=new File(path);
		  File[] FileList = file.listFiles();
		  String[] FileNames=new String [FileList.length+1];
		  int NumberOfFiles=0;
		  System.out.println("Number of files:"+FileList.length);
		  
		  for (int i = 0; i < FileList.length; i++) {
		   if (FileList[i].isFile()) {
		    FileNames[NumberOfFiles]=FileList[i].toString();
		    if(FileNames[NumberOfFiles].indexOf(args[1])>0) {
		    	NumberOfFiles++;		    
		    	System.out.println("File:"+String.valueOf(i)+": " +FileList[i]);
}
		   }
		   if (FileList[i].isDirectory()) {
		    System.out.println("sub directory ignored:"+FileList[i]);
		   }
		  }
	String[] SeqName= new String[100000];
	String[] SeqData= new String[100000]; 
	String[] SeqData1= new String[100000]; 
	String[] SeqData2= new String[100000]; 
	String Isoform0="";
	String Isoform1="";
	String Isoform2="";
	
	int i=0,j=0;
	FileReader fr;
	BufferedReader br;
	String record;
	
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
			for ( i=1;i<=21;i++){
				int pos=S[i].indexOf(",")+1;
				S[i]=S[i].substring(pos, S[i].length());
				for ( j=1;j<=21;j++){
					pos=S[i].indexOf(",")+1;
					String ScoreStr=S[i].substring(0, pos-1);
					ScoreMatrix[i][j]=Integer.valueOf(ScoreStr);
					S[i]=S[i].substring(pos, S[i].length());
					//System.out.print(ScoreMatrix[i][j]+", ");
				}
				//System.out.print("\n");
			}

				
	
	//Reading the coding DNA sequences in fasta file
    System.out.println("Number of CDS files:"+String.valueOf(NumberOfFiles));
	System.out.println("Reading CDS Files ");
	
	int TotalSeq=0;
	
	for (int FileNo=0;FileNo<NumberOfFiles;FileNo++) {
						
			System.out.println("Reading File "+FileNo+": "+FileNames[FileNo]+": ");
			 fr=new FileReader(FileNames[FileNo]);
			 br=new BufferedReader(fr);
			 record=new String();
			 i=0;		
				while((record=br.readLine()) != null){
				
				String rec = record.trim();
				
				if(rec.length() > 0){

					char rec_char[]=rec.toCharArray();

					//Name of the Sequence
					if(rec_char[0]=='>'){
						i++;
						SeqName[i]=rec.substring(1);
						int GeneNameStart=SeqName[i].indexOf("[gene=");
						if (GeneNameStart>0){
							SeqName[i]=SeqName[i].substring(GeneNameStart+6);
							int GeneNameEnd=SeqName[i].indexOf("]");							
							if (GeneNameEnd>0) {SeqName[i]=ClearSeqName(SeqName[i].substring(0,GeneNameEnd));}
						}
						SeqData[i]="";
					}
					else{
						SeqData[i]=SeqData[i]+rec;
					}
				}
			}
		fr.close();
		System.out.print("Total "+i+" CDSs read.\n");
		TotalSeq+=i;	
	}
	System.out.println("Total Number of Sequences: "+String.valueOf(TotalSeq)+"\r\n");

	int Pos=0;
	String codon="", codon1="", amino="";			
	String rec1="";
	char rec_char1[];
		
	//Clear the sequences, remove non-base characters
	String input=args[0]+args[1]+".Clear.fas";					
	BufferedWriter out=new BufferedWriter(new FileWriter(input));		
	out.write("\r\n\r\n"+">"+SeqName[seqNo]+"-0"+"\r\n");
	out.write(Isoform0);

	for  (int seqNo=1;seqNo<=TotalSeq;seqNo++){
		
		System.out.println("Clearing Sequence "+seqNo+": "+SeqName[seqNo]+": ");			
		//System.out.println(SeqData[seqNo]);
		rec1=SeqData[seqNo];
		rec_char1=rec1.toCharArray();
		SeqData[seqNo]="";
		 for(i=0; i<rec1.length(); i++){
			 if (("agctu".indexOf(rec_char1[i]) != -1) || ("AGCTU".indexOf(rec_char1[i]) != -1)){					 
				 SeqData[seqNo]+=rec_char1[i];				 }
		 }
	}
		
			
 	String SimilarityFile=args[0]+"Similarities.txt";
	BufferedWriter out1=new BufferedWriter(new FileWriter(SimilarityFile));
	System.out.println("SeqName, Length, ORF-1-2, ORF-1-3, ORF-2-3, Average, Gaps\r\n"); 
	out1.write("Total Number of Sequences: "+String.valueOf(TotalSeq)+"\r\n");
	out1.write("SeqName, Length, ORF-0-1, ORF-0*1,  ORF-0-2, ORF-0*2, Average, Gaps-1-2, Gaps*1*2\r\n");
	
	for  (int seqNo=1;seqNo<=TotalSeq;seqNo++){
			
		//Translate the original and frameshifts into protein sequences
		System.out.println("Translating Sequence "+seqNo+": "+SeqName[seqNo]+": ");			
			
		// Original 0
		 
		rec1=SeqData[seqNo];
		rec_char1=rec1.toCharArray();
		codon="";
		 for(i=0; i<rec1.length(); i++){
			codon=codon+rec_char1[i];
			if(codon.length() == 3){
				codon1=codon.toUpperCase();
				Isoform0+=FrameshiftAlign.translate(codon1);								
				codon="";
			}
		}
			
		for (int Frame=1;Frame<=2;Frame++){
			
			// Create Frameshifts				
			rec1=rec1.substring(0,3)+rec1.substring(4,rec1.length());
			
			rec_char1=rec1.toCharArray();
			codon=""; Isoform1="";Isoform2="";
			 for(i=0; i<rec1.length(); i++){
				codon=codon+rec_char1[i];
				if(codon.length() ==3){
					codon1=codon.toUpperCase();
					Isoform1+=FrameshiftAlign.translate(codon1);								
					Isoform2+=FrameshiftAlign.translatereadthrough(codon1);
					codon="";
				}
			}
			
			String input=args[0]+"Frame-"+Frame+".Pro.fas";					
			BufferedWriter out=new BufferedWriter(new FileWriter(input));		
			out.write("\r\n\r\n"+">"+SeqName[seqNo]+"-0"+"\r\n");
			out.write(Isoform0);
			out.write("\r\n\r\n"+">"+SeqName[seqNo]+"-"+Frame+"\r\n");
			out.write(Isoform1);
			out.write("\r\n\r\n"+">"+SeqName[seqNo]+"*"+Frame+"\r\n");
			out.write(Isoform2);
			out.close();

	
			// Run ClustalW to align frameshift isoforms
			
			String output=args[0]+"Frame-"+Frame+".Pro.Clustalw2.fas";
			java.io.File ff = new java.io.File(input);
			if (ff.exists()) {
			
			System.out.println("\nCalling ClustalW to align unified sequences: \n");  
		
				
			String exe= "clustalw2 -INFILE=" + input + " -TYPE=PROTEIN" + " -OUTFILE=" + output + " -OUTPUT=PIR -OUTORDER=INPUT";
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

			if (exitCode == 0) {
				System.out.println("\nClustal Alignment SUCCESS!\n");
			  //  FAobj.ResolveCAUSA(output, args[0]+".CAUSA.Combined Alignment.fasta");
		   } else {
				System.err.println("\nClustal Alignment ERROR! (Error Number:" + exitCode+")\n");
			}

		}
			
			//Compute Similarity
			
			String ThisSeq="";
			String ThatSeq = ""; 
			String ThisSymbol="";
			String ThatSymbol = ""; 
			int Gaps=0;
			float SimilarResidues=0;
			float Similarity=0;	            
			float FrameshiftAlign_substitution_score=0;			
			
			ff = new java.io.File(output);
			if (ff.exists()) {			
			
		   // Output the frameshift coding sequences
		
			String[] Alignment= new String[4]; 
			System.out.println("Reading alignment in FASTA file:\r\n ");
			System.out.println("Compute Similarity: \r\n");   			
				
			//Read the Clustalw alignment in fasta file
			
			
			fr=new FileReader(output);
			br=new BufferedReader(fr);
			record=new String();
			
			i=0;j=0;
			
			Alignment[1]="";
			Alignment[2]="";
			Alignment[3]="";	
			
			
			while((record=br.readLine()) != null){
				
				String rec = record.trim();
				
				if(rec.length() > 0){

					char rec_char[]=rec.toCharArray();

					//Name of the Sequence
					if(rec_char[0]=='>'){
						i++;
						//SeqName[i]=rec.substring(1);
						SeqData[i]="";
						if (i>1) {
							//System.out.print(Alignment[i-1].length()+" Sites\n");
							}
						//System.out.print("Sequence "+i+": "+SeqName[i]+", ");

					}
					else{
						Alignment[i]=Alignment[i]+rec;
					}
				}

			}
			//if (i>1) {System.out.print(Alignment[i].length()+" Sites\n");}
	//		TotalSeq=i;
			fr.close();
			
			// Compute Similarity
			
		
			System.out.print(SeqName[seqNo]+", "); 
			out1.write(SeqName[seqNo]+", ");
			System.out.print(String.valueOf(Alignment[1].length())+", "); 
			out1.write(String.valueOf(Alignment[1].length())+", ");
		 
			for (i = 1; i <= 3; i++){
					ThisSeq = Alignment[i];
			   
					for (j = i+1; j <=3; j++){
						ThatSeq = Alignment[j];
						SimilarResidues=0;
						Similarity=0;	            
						for (Pos = 0; Pos < Alignment[i].length(); Pos++)
						{
							ThisSymbol = ThisSeq.substring(Pos,Pos+1);
							ThatSymbol = ThatSeq.substring(Pos,Pos+1);
										   
							if (ThisSymbol.equals("-"))
							{
							   Gaps++;
							}
							
							int AA1No=allAAs.indexOf(ThisSymbol)+1;
							int AA2No=allAAs.indexOf(ThatSymbol)+1;
							//System.out.print("AA1: "+AA1+":"+String.valueOf(AA1No)+"-->AA2: "+AA2+":"+String.valueOf(AA1No));
							if (AA1No>0&&AA2No>0){
								
								FrameshiftAlign_substitution_score=ScoreMatrix[AA1No][AA2No];			
									
							}else
							{ FrameshiftAlign_substitution_score= 0;}	                
							if (FrameshiftAlign_substitution_score>=0)
							{
								SimilarResidues++;
							}

					}
					Similarity= SimilarResidues/Alignment[i].length();
					System.out.print(String.valueOf(Similarity)+", "); 
					out1.write(String.valueOf(Similarity)+", ");
				}
				
				System.out.print(String.valueOf(Gaps/3)+",\r\n "); 
				out1.write(String.valueOf(Gaps/3)+", \r\n");  		   
			}
			}
		}	
	}
	out1.close();		
		}catch(Exception e){
			System.out.println("\nUsage: FrameshiftAlign <Path> <Input File Name (CDS files in fasta format)>"); 
			System.out.println(e);
			e.printStackTrace();
			}
	}
	public static String translatereadthrough(String codon){
	String amino="";
			if(codon.length() ==3){
	
				String codon1=codon.toUpperCase();
	
				if((codon1.equals("GCT")) || (codon1.equals("GCU"))){
					amino="A";
					codon1="GCU";
				}
				else if ((codon1.equals("GCC"))||(codon1.equals("GCA"))||(codon1.equals("GCG"))){
					amino="A";
				}
				else if((codon1.equals("CGU")) || (codon1.equals("CGT"))){
					codon1="CGU";
					amino="R";
				}
				else if ((codon1.equals("CGC"))||(codon1.equals("CGA"))||(codon1.equals("CGG"))||(codon1.equals("AGA"))||(codon1.equals("AGG"))){
					amino="R";
				}
				else if((codon1.equals("AAU")) || (codon1.equals("AAT"))){
					codon1="AAU";
					amino="N";
				}
				else if(codon1.equals("AAC")){
					amino="N";
				}
				else if((codon1.equals("GAU")) || (codon1.equals("GAT"))){
					codon1="GAU";
					amino="D";
				}
				else if((codon1.equals("GAC"))){
					amino="D";
				}
				else if((codon1.equals("UGC"))||(codon1.equals("TGC"))){
					codon1="UGC";
					amino="C";
				}
				else if((codon1.equals("UGU")) || (codon1.equals("TGT"))){
					codon1="UGU";
					amino="C";
				}
				else if((codon1.equals("CAA")) || (codon1.equals("CAG"))){
					amino="Q";
				}
				else if((codon1.equals("GAA")) || (codon1.equals("GAG"))){
					amino="E";
				}
				else if((codon1.equals("GGU")) || (codon1.equals("GGT"))){
					codon1="GGU";
					amino="G";
				}
				else if((codon1.equals("GGC"))|| (codon1.equals("GGA"))|| (codon1.equals("GGG"))){
					amino="G";
				}
				else if((codon1.equals("CAU")) || (codon1.equals("CAT"))){
					codon1="CAU";
					amino="H";
				}
				else if((codon1.equals("CAC"))){
					amino="H";
				}
				else if((codon1.equals("AUU")) || (codon1.equals("ATT"))){
					codon1="AUU";
					amino="I";
				}
				else if((codon1.equals("AUC"))|| (codon1.equals("ATC"))){
					codon1="AUC";
					amino="I";
				}
				else if((codon1.equals("AUA"))|| (codon1.equals("ATA"))){
					codon1="AUA";
					amino="I";
				}
				else if((codon1.equals("AUG")) || (codon1.equals("ATG"))){
					codon1="AUG";
					amino="M";
				}
				else if((codon1.equals("UUG")) || (codon1.equals("TTG"))){
					codon1="UUG";
					amino="L";
				}
				else if((codon1.equals("UUA"))|| (codon1.equals("TTA"))){
					codon1="UUA";
					amino="L";
				}
				else if((codon1.equals("CUU"))|| (codon1.equals("CTT"))){
					codon1="CUU";
					amino="L";
				}
				else if((codon1.equals("CUC"))|| (codon1.equals("CTC"))){
					codon1="CUC";
					amino="L";
				}
				else if((codon1.equals("CUA"))|| (codon1.equals("CTA"))){
					codon1="CUA";
					amino="L";
				}
				else if((codon1.equals("CUG"))|| (codon1.equals("CTG"))){
					codon1="CUG";
					amino="L";
				}
				else if((codon1.equals("UUU")) || (codon1.equals("TTT"))){
					codon1="UUU";
					amino="F";
				}
				else if((codon1.equals("UUC")) || (codon1.equals("TTC"))){
					codon1="UUC";
					amino="F";
				}
				else if((codon1.equals("CCU")) || (codon1.equals("CCT"))){
					codon1="CCU";
					amino="P";
				}
				else if((codon1.equals("CCC")) || (codon1.equals("CCA"))|| (codon1.equals("CCG"))){
					amino="P";
				}
				else if((codon1.equals("UCU")) || (codon1.equals("TCT"))){
					codon1="UCU";
					amino="S";
				}
				else if((codon1.equals("UCC")) || (codon1.equals("TCC"))){
					codon1="UCC";
					amino="S";
				}
				else if((codon1.equals("UCA"))|| (codon1.equals("TCA"))){
					codon1="UCA";
					amino="S";
				}
				else if((codon1.equals("UCG"))|| (codon1.equals("TCG"))){
					codon1="UCG";
					amino="S";
				}
				else if((codon1.equals("AGU"))|| (codon1.equals("AGT"))){
					codon1="AGU";
					amino="S";
				}
				else if((codon1.equals("AGC"))){
					amino="S";
				}
				else if((codon1.equals("ACU")) || (codon1.equals("ACT"))){
					codon1="ACU";
					amino="T";
				}
				else if((codon1.equals("ACC")) || (codon1.equals("ACA"))|| (codon1.equals("ACG"))){
					amino="T";
				}
	
				else if((codon1.equals("UGG")) || (codon1.equals("TGG"))){
					codon1="UGG";
					amino="W";
				}
				else if((codon1.equals("UAU")) || (codon1.equals("TAT")) ){
					codon1="UAU";
					amino="Y";
				}
				else if((codon1.equals("UAC")) ||(codon1.equals("TAC"))){
					codon1="UAC";
					amino="Y";
				}
				else if((codon1.equals("GUU")) || (codon1.equals("GTT"))){
					codon1="GUU";
					amino="V";
				}
				else if((codon1.equals("GUC")) ||(codon1.equals("GTC"))){
					codon1="GUC";
					amino="V";
				}
				else if((codon1.equals("GUA"))||(codon1.equals("GTA"))){
					codon1="GUA";
					amino="V";
				}
				else if((codon1.equals("GUG"))||(codon1.equals("GTG"))){
					codon1="GUG";
					amino="V";
				}
				else if((codon1.equals("UAA")) || (codon1.equals("TAA"))){
					codon1="UAA"; //Readthrough by replacing the stop codon UAA with Lys (K)
					amino="K";
				}
				else if((codon1.equals("UGA")) ||(codon1.equals("TGA"))){
					codon1="UGA";
					amino="W"; //Readthrough by replacing the stop codon UGA with Trp (W)
				}
				else if((codon1.equals("UAG"))||(codon1.equals("TAG"))){
					codon1="UAG";
					amino="S"; //Readthrough by replacing the stop codon UAG with Ser (S) or Gln(Q) or Tyr (Y)
				}
				else if((codon1.equals("AAA"))||(codon1.equals("AAG"))){
					amino="K";
				}
			}
	
		return amino;
		
 }
	public static String translate(String codon){
	String amino="";
			if(codon.length() ==3){
	
				String codon1=codon.toUpperCase();
	
				if((codon1.equals("GCT")) || (codon1.equals("GCU"))){
					amino="A";
					codon1="GCU";
				}
				else if ((codon1.equals("GCC"))||(codon1.equals("GCA"))||(codon1.equals("GCG"))){
					amino="A";
				}
				else if((codon1.equals("CGU")) || (codon1.equals("CGT"))){
					codon1="CGU";
					amino="R";
				}
				else if ((codon1.equals("CGC"))||(codon1.equals("CGA"))||(codon1.equals("CGG"))||(codon1.equals("AGA"))||(codon1.equals("AGG"))){
					amino="R";
				}
				else if((codon1.equals("AAU")) || (codon1.equals("AAT"))){
					codon1="AAU";
					amino="N";
				}
				else if(codon1.equals("AAC")){
					amino="N";
				}
				else if((codon1.equals("GAU")) || (codon1.equals("GAT"))){
					codon1="GAU";
					amino="D";
				}
				else if((codon1.equals("GAC"))){
					amino="D";
				}
				else if((codon1.equals("UGC"))||(codon1.equals("TGC"))){
					codon1="UGC";
					amino="C";
				}
				else if((codon1.equals("UGU")) || (codon1.equals("TGT"))){
					codon1="UGU";
					amino="C";
				}
				else if((codon1.equals("CAA")) || (codon1.equals("CAG"))){
					amino="Q";
				}
				else if((codon1.equals("GAA")) || (codon1.equals("GAG"))){
					amino="E";
				}
				else if((codon1.equals("GGU")) || (codon1.equals("GGT"))){
					codon1="GGU";
					amino="G";
				}
				else if((codon1.equals("GGC"))|| (codon1.equals("GGA"))|| (codon1.equals("GGG"))){
					amino="G";
				}
				else if((codon1.equals("CAU")) || (codon1.equals("CAT"))){
					codon1="CAU";
					amino="H";
				}
				else if((codon1.equals("CAC"))){
					amino="H";
				}
				else if((codon1.equals("AUU")) || (codon1.equals("ATT"))){
					codon1="AUU";
					amino="I";
				}
				else if((codon1.equals("AUC"))|| (codon1.equals("ATC"))){
					codon1="AUC";
					amino="I";
				}
				else if((codon1.equals("AUA"))|| (codon1.equals("ATA"))){
					codon1="AUA";
					amino="I";
				}
				else if((codon1.equals("AUG")) || (codon1.equals("ATG"))){
					codon1="AUG";
					amino="M";
				}
				else if((codon1.equals("UUG")) || (codon1.equals("TTG"))){
					codon1="UUG";
					amino="L";
				}
				else if((codon1.equals("UUA"))|| (codon1.equals("TTA"))){
					codon1="UUA";
					amino="L";
				}
				else if((codon1.equals("CUU"))|| (codon1.equals("CTT"))){
					codon1="CUU";
					amino="L";
				}
				else if((codon1.equals("CUC"))|| (codon1.equals("CTC"))){
					codon1="CUC";
					amino="L";
				}
				else if((codon1.equals("CUA"))|| (codon1.equals("CTA"))){
					codon1="CUA";
					amino="L";
				}
				else if((codon1.equals("CUG"))|| (codon1.equals("CTG"))){
					codon1="CUG";
					amino="L";
				}
				else if((codon1.equals("UUU")) || (codon1.equals("TTT"))){
					codon1="UUU";
					amino="F";
				}
				else if((codon1.equals("UUC")) || (codon1.equals("TTC"))){
					codon1="UUC";
					amino="F";
				}
				else if((codon1.equals("CCU")) || (codon1.equals("CCT"))){
					codon1="CCU";
					amino="P";
				}
				else if((codon1.equals("CCC")) || (codon1.equals("CCA"))|| (codon1.equals("CCG"))){
					amino="P";
				}
				else if((codon1.equals("UCU")) || (codon1.equals("TCT"))){
					codon1="UCU";
					amino="S";
				}
				else if((codon1.equals("UCC")) || (codon1.equals("TCC"))){
					codon1="UCC";
					amino="S";
				}
				else if((codon1.equals("UCA"))|| (codon1.equals("TCA"))){
					codon1="UCA";
					amino="S";
				}
				else if((codon1.equals("UCG"))|| (codon1.equals("TCG"))){
					codon1="UCG";
					amino="S";
				}
				else if((codon1.equals("AGU"))|| (codon1.equals("AGT"))){
					codon1="AGU";
					amino="S";
				}
				else if((codon1.equals("AGC"))){
					amino="S";
				}
				else if((codon1.equals("ACU")) || (codon1.equals("ACT"))){
					codon1="ACU";
					amino="T";
				}
				else if((codon1.equals("ACC")) || (codon1.equals("ACA"))|| (codon1.equals("ACG"))){
					amino="T";
				}
	
				else if((codon1.equals("UGG")) || (codon1.equals("TGG"))){
					codon1="UGG";
					amino="W";
				}
				else if((codon1.equals("UAU")) || (codon1.equals("TAT")) ){
					codon1="UAU";
					amino="Y";
				}
				else if((codon1.equals("UAC")) ||(codon1.equals("TAC"))){
					codon1="UAC";
					amino="Y";
				}
				else if((codon1.equals("GUU")) || (codon1.equals("GTT"))){
					codon1="GUU";
					amino="V";
				}
				else if((codon1.equals("GUC")) ||(codon1.equals("GTC"))){
					codon1="GUC";
					amino="V";
				}
				else if((codon1.equals("GUA"))||(codon1.equals("GTA"))){
					codon1="GUA";
					amino="V";
				}
				else if((codon1.equals("GUG"))||(codon1.equals("GTG"))){
					codon1="GUG";
					amino="V";
				}
				else if((codon1.equals("UAA")) || (codon1.equals("TAA"))){
					codon1="UAA";
					amino="*";
				}
				else if((codon1.equals("UGA")) ||(codon1.equals("TGA"))){
					codon1="UGA";
					amino="*";
				}
				else if((codon1.equals("UAG"))||(codon1.equals("TAG"))){
					codon1="UAG";
					amino="*";
				}
				else if((codon1.equals("AAA"))||(codon1.equals("AAG"))){
					amino="K";
				}
			}
	
		return amino;
		
 }   
	public static String ClearSeqName(String SeqName){
	SeqName=SeqName.replace(' ', '-');
	SeqName=SeqName.replace('(', '-');
	SeqName=SeqName.replace(')', '-');
	SeqName=SeqName.replace('[', '-');
	SeqName=SeqName.replace(']', '-');
	SeqName=SeqName.replace('{', '-');
	SeqName=SeqName.replace('}', '-');
	SeqName=SeqName.replace(';', '-');
	SeqName=SeqName.replace(':', '-');
	SeqName=SeqName.replace('"', '-');
	SeqName=SeqName.replace('\'', '-');
	SeqName=SeqName.replace('-', '-');
	SeqName=SeqName.replace('>', '-');
	SeqName=SeqName.replace('~', '-');
	SeqName=SeqName.replace('#', '-');
	SeqName=SeqName.replace('*', '-');
	SeqName=SeqName.replace('&', '-');
	SeqName=SeqName.replace('%', '-');
	SeqName=SeqName.replace('=', '-');
	SeqName=SeqName.replace('+', '-');
	SeqName=SeqName.replace('?', '-');
	SeqName=SeqName.replace('<', '-');
	SeqName=SeqName.replace(',', '-');
	SeqName=SeqName.replace('.', '-');
	SeqName=SeqName.replace('$', '-');
	SeqName=SeqName.replace('@', '-');
	SeqName=SeqName.replace('!', '-');
	SeqName=SeqName.replace('|', '-');
	SeqName=SeqName.replace('\\', '-');
	SeqName=SeqName.replace('/', '-');
	SeqName=SeqName.replaceAll("-", "");
	int j=SeqName.indexOf("\\");
	if(j>0){SeqName=SeqName.substring(0,j-1)+SeqName.substring(j+1);}
	j=SeqName.indexOf("/");
	if(j>0){SeqName=SeqName.substring(0,j-1)+SeqName.substring(j+1);}
	return SeqName;
}

}
