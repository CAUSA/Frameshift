/*
==============================================================
FrameshiftNonStop - java version 2.0.001 

Create non-stop frameshift DNA sequences

Usage: 
 
java FrameshiftNonStop <\PATH\TO\FILE> <FileName (CDS files in fasta format)> 
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

public class FrameshiftNonStop{
	public static void main(String args[]){

		try{
            //accept only one args - the input file name for coding DNA sequences 
			
			FrameshiftNonStop FAobj=new FrameshiftNonStop();

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
	String Isoform="";
	
	int i=0,j=0;
	FileReader fr;
	BufferedReader br;
	String record;
	
			
	
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

	for  (int seqNo=1;seqNo<TotalSeq;seqNo++){
		
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
		
			

	String input=args[0]+args[1]+".Non-Stop CDSs.fas";					
	BufferedWriter out=new BufferedWriter(new FileWriter(input));		
	
	for  (int seqNo=1;seqNo<TotalSeq;seqNo++){
			
		//Convert the frameshifts into non-stop sequences
		System.out.println("Converting Sequence "+seqNo+": "+SeqName[seqNo]+": ");			
			
		// Original 0
		 
		rec1=SeqData[seqNo];
		rec_char1=rec1.toCharArray();
		codon="";Isoform="";
		 for(i=0; i<rec1.length(); i++){
			codon=codon+rec_char1[i];
			if(codon.length() == 3){
				codon1=codon.toUpperCase();
				Isoform+=FrameshiftNonStop.KeepGoing(codon1);;								
				codon="";
			}
		}
		out.write("\r\n\r\n"+">"+SeqName[seqNo]+"*\r\n");
		out.write(Isoform);
			
		}	
		out.close();
		}catch(Exception e){
			System.out.println("\nUsage: FrameshiftNonStop <Path> <Input File Name (CDS files in fasta format)>"); 
			System.out.println(e);
			e.printStackTrace();
			}
	}
	public static String KeepGoing(String codon){
			String amino="";
			String codon1=codon.toUpperCase();
			if(codon.length() ==3){
	
	
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
					codon1="AAA";
					amino="*";
				}
				else if((codon1.equals("UGA")) ||(codon1.equals("TGA"))){
					codon1="TGG";
					amino="*";
				}
				else if((codon1.equals("UAG"))||(codon1.equals("TAG"))){
					codon1="UCG";
					amino="*";
				}
				else if((codon1.equals("AAA"))||(codon1.equals("AAG"))){
					amino="K";
				}
			}
	
		return codon1;
		
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
