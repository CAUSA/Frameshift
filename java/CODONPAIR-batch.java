/*
==============================================================
CODONPAIR: Codon and Codon Pair Usage- java version 2.0.001 

Usage: 
 
Java CODONPAIR <PATH> <File Name of protein coding DNA sequences (in fasta format)>
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

public class CODONPAIR{

	public static void main(String args[]){

		try{
            //accept only one args - the input file name for coding DNA sequences 
			
			CODONPAIR FAobj=new CODONPAIR();
			
//		Prepare dicodon table
			System.out.println(" Preparing codon table");
			String[] dicodons= new String[5000];
			int[] occurrence=new int[5000];
			String dicodon="", codon1="", codon2="";
			String AA1="";			
			String rec1="";
			char rec_char1[];
			String base1="";
			String base2="";
			String base3="";
			int NoCodon=0;
			int i=0,j=0,k=0,s=0,l=0,m=0,n=0;
					 
		for ( i=1;i<=4;i++){
			if (i==1){ base1="A";}
			else if (i==2){ base1="G";}
			else if (i==3){ base1="C";}
			else if (i==4){ base1="T";}
					
			for ( j=1;j<=4;j++){
				if (j==1){ base2="A";}
				else if (j==2){ base2="G";}
				else if (j==3){ base2="C";}
				else if (j==4){ base2="T";}
				for ( k=1;k<=4;k++){
					if (k==1){ base3="A";}
					else if (k==2){ base3="G";}
					else if (k==3){ base3="C";}
					else if (k==4){ base3="T";}
					codon1 = base1 + base2 + base3;
				for (l=1;l<=4;l++){
					if (l==1){ base1="A";}
					else if (l==2){ base1="G";}
					else if (l==3){ base1="C";}
					else if (l==4){ base1="T";}
							
					for ( m=1;m<=4;m++){
						if (m==1){ base2="A";}
						else if (m==2){ base2="G";}
						else if (m==3){ base2="C";}
						else if (m==4){ base2="T";}
						for (n=1;n<=4;n++){
							if (n==1){ base3="A";}
							else if (n==2){ base3="G";}
							else if (n==3){ base3="C";}
							else if (n==4){ base3="T";}
								codon2 = base1 + base2 + base3;
								dicodon = codon1 + codon2;
								AA1=CODONPAIR.translate(codon1) + CODONPAIR.translate(codon2);
								NoCodon++;
								 dicodons[NoCodon]=dicodon;
								 System.out.println("Codon"+String.valueOf(NoCodon)+": "+ dicodon+":"+AA1);
							}
						}
					}
				}
			}
		}
			
		//Read the coding DNA sequences in fasta file
			
		System.out.println("Reading FASTA sequence file");
		  String path=args[0];
		  File file=new File(path);
		  File[] FileList = file.listFiles();
		  String[] FileNames=new String [FileList.length+1];
		  int NumberOfFiles=0;
		  System.out.println("Number of files: "+FileList.length);
		  
		  for (i = 0; i < FileList.length; i++) {
		   if (FileList[i].isFile()) {
		    FileNames[NumberOfFiles]=FileList[i].toString();
		    if(FileNames[NumberOfFiles].indexOf(args[1])>=0) {
		    	NumberOfFiles++;		    
		    	System.out.println("File: "+String.valueOf(i)+": " +FileList[i]);
}
		   }
		   if (FileList[i].isDirectory()) {
		    System.out.println("sub directory ignored: "+FileList[i]);
		   }
		  }
	String[] SeqName= new String[100000];
	String[] SeqData= new String[100000]; 

	FileReader fr;
	BufferedReader br;
	String record;
	int TotalSeq=0;
	
    System.out.println("Number of CDS files: "+String.valueOf(NumberOfFiles));
	
	for (int FileNo=1;FileNo<NumberOfFiles;FileNo++) {
	
			i=0;		
			System.out.println("Reading File "+FileNo+": "+FileNames[FileNo]+": ");
			 fr=new FileReader(FileNames[FileNo]);
			 br=new BufferedReader(fr);
			 record=new String();
			
			while((record=br.readLine()) != null){
				
				String rec = record.trim();
				
				if(rec.length() > 0){

						char rec_char[]=rec.toCharArray();

						//Name of the Sequence
						if(rec_char[0]=='>'){
							i++;
							SeqName[i]=ClearSeqName(rec.substring(1,rec.length()>20?20:rec.length()));
							SeqData[i]="";
						}
						else{
							SeqData[i]=SeqData[i]+rec;
						}
				}

			}
			System.out.print(i+" Sequences\n");
			fr.close();
	
			int Pos=0;
  		
			TotalSeq+=i;
	}
		System.out.print(TotalSeq+" Total Sequences\n");

		//Count number of dicodons
		NoCodon=0;
		for  (int seqNo=1;seqNo<=TotalSeq;seqNo++){
			System.out.println("Working on sequence No. "+seqNo);
			rec1=SeqData[seqNo];
			rec_char1=rec1.toCharArray();
			codon1="";codon2="";
			 for(i=0; i<rec1.length()-3; i++){
				codon1=codon1+rec_char1[i];
				codon2=codon2+rec_char1[i+3];
				if(codon1.length() == 3){
					NoCodon++;
					dicodon=codon1.toUpperCase()+codon2.toUpperCase();
					//System.out.print(dicodon+", ");
					for (int c=1;c<=4096;c++){
						if (dicodon.equals(dicodons[c])){occurrence[c]++;}
					}
					dicodon="";
					codon1="";
					codon2="";
				}
			}
		}
	
		
		String output=args[0]+args[1]+".CodonPair.txt";
		BufferedWriter out=new BufferedWriter(new FileWriter(output));
	    System.out.println("Codon pair usage: Total number of codon pairs:"+NoCodon); 
	    out.write("Codon pair usage:\r\n Total number of codon pairs:"+NoCodon+"\r\n"); 
		for (int c=1;c<=4096;c++){
			if (occurrence[c]>0){
				System.out.println(dicodons[c]+": "+String.valueOf(occurrence[c]));
				out.write(dicodons[c]+" "+String.valueOf(occurrence[c])+"\r\n");
			}
		}
		out.close();
		 
		}catch(Exception e){
			System.out.println("\nUsage: CODONPAIR <InputFile (CDSs in fasta format)>\n"); 
			System.out.println(e);
			e.printStackTrace();
			}
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
