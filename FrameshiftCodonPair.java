/*
==============================================================
Frameshift - java version 1.0.001 

Usage: Frameshift <CDSs.fas> <n1> <n2> <n3>
            CDSs.fas -- CDSs simulated by Recodon.fasta; 
            n1 -- Number of repeats; 
            n2 -- Number of indels;
            n3 -- Maximum indel length.
=============================================================
This software is released under GNU/GPL license
Copyright (c) 2011, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 
*To whom correspondences should be addressed
email: xiaolong@ouc.edu.cn
website: http://www.dnapluspro.com
================================================================

*/

import java.io.*;
import java.util.*;
public class FrameshiftCodonPair{
	public static String substitution_score(String ScoreMatrix, String AA1, String AA2){
		String allAAs="CSTPAGNDEQHRKMILVFYW*";
		int AA1No=allAAs.indexOf(AA1);
		int AA2No=allAAs.indexOf(AA2);
		String AllScores=ScoreMatrix.substring(ScoreMatrix.indexOf(AA1));
		String Score="";
		for (int i=0;i<=AA2No;i++){
		   int ScoreStart=AllScores.indexOf(",");
		   AllScores=AllScores.substring(ScoreStart+1,AllScores.length());
		}
		int ScoreEnd=AllScores.indexOf(",");
		Score=AllScores.substring(0,ScoreEnd);
		return Score;
	}
	
	public static void main(String[] args){

	try{

			
for (int M=1;M<=2;M++){
	
	String S="";
		
	if (M==1){
		System.out.println("\n================================");
		System.out.println("Scoring Matrix: Blossum62");
		System.out.println("================================");
		
		 S+="C,9,-1,-1,-3,0,-3,-3,-3,-4,-3,-3,-3,-3,-1,-1,-1,-1,-2,-2,-2,0,";
		 S+="S,-1,4,1,-1,1,0,1,0,0,0,-1,-1,0,-1,-2,-2,-2,-2,-2,-3,0,";
		 S+="T,-1,1,4,1,-1,1,0,1,0,0,0,-1,0,-1,-2,-2,-2,-2,-2,-3,0,";
		 S+="P,-3,-1,1,7,-1,-2,-1,-1,-1,-1,-2,-2,-1,-2,-3,-3,-2,-4,-3,-4,0,";
		 S+="A,0,1,-1,-1,4,0,-1,-2,-1,-1,-2,-1,-1,-1,-1,-1,-2,-2,-2,-3,0,";
		 S+="G,-3,0,1,-2,0,6,-2,-1,-2,-2,-2,-2,-2,-3,-4,-4,0,-3,-3,-2,0,";
		 S+="N,-3,1,0,-2,-2,0,6,1,0,0,-1,0,0,-2,-3,-3,-3,-3,-2,-4,0,";
		 S+="D,-3,0,1,-1,-2,-1,1,6,2,0,-1,-2,-1,-3,-3,-4,-3,-3,-3,-4,0,";
		 S+="E,-4,0,0,-1,-1,-2,0,2,5,2,0,0,1,-2,-3,-3,-3,-3,-2,-3,0,";
		 S+="Q,-3,0,0,-1,-1,-2,0,0,2,5,0,1,1,0,-3,-2,-2,-3,-1,-2,0,";
		 S+="H,-3,-1,0,-2,-2,-2,1,1,0,0,8,0,-1,-2,-3,-3,-2,-1,2,-2,0,";
		 S+="R,-3,-1,-1,-2,-1,-2,0,-2,0,1,0,5,2,-1,-3,-2,-3,-3,-2,-3,0,";
		 S+="K,-3,0,0,-1,-1,-2,0,-1,1,1,-1,2,5,-1,-3,-2,-3,-3,-2,-3,0,";
		 S+="M,-1,-1,-1,-2,-1,-3,-2,-3,-2,0,-2,-1,-1,5,1,2,-2,0,-1,-1,0,";
		 S+="I,-1,-2,-2,-3,-1,-4,-3,-3,-3,-3,-3,-3,-3,1,4,2,1,0,-1,-3,0,";
		 S+="L,-1,-2,-2,-3,-1,-4,-3,-4,-3,-2,-3,-2,-2,2,2,4,3,0,-1,-2,0,";
		 S+="V,-1,-2,-2,-2,0,-3,-3,-3,-2,-2,-3,-3,-2,1,3,1,4,-1,-1,-3,0,";
		 S+="F,-2,-2,-2,-4,-2,-3,-3,-3,-3,-3,-1,-3,-3,0,0,0,-1,6,3,1,0,";
		 S+="Y,-2,-2,-2,-3,-2,-3,-2,-3,-2,-1,2,-2,-2,-1,-1,-1,-1,3,7,2,0,";
		 S+="W,-2,-3,-3,-4,-3,-2,-4,-4,-3,-2,-2,-3,-3,-1,-3,-2,-3,1,2,11,0,";
		 S+="*,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";
	 }
	 else if (M==2){
			System.out.println("\n================================");
			System.out.println("Scoring Matrix: PAM250");
			System.out.println("================================");
		
		S+="C,12,0,-2,-3,-2,-3,-4,-5,-5,-5,-3,-4,-5,-5,-2,-6,-2,-4,0,-8,0,";
		S+="S,0,2,1,1,1,1,1,0,0,-1,-1,0,0,-2,-1,-3,-1,-3,-3,-2,0,";
		S+="T,-2,1,3,0,1,0,0,0,0,-1,-1,-1,0,-1,0,-2,0,-3,-3,-5,0,";
		S+="P,-3,1,0,6,1,-1,-1,-1,-1,0,0,0,-1,-2,-2,-3,-1,-5,-5,-6,0,";
		S+="A,-2,1,1,1,2,1,0,0,0,0,-1,-2,-1,-1,-1,-2,0,-5,-3,-6,0,";
		S+="G,-3,1,0,-1,1,5,0,1,0,-1,-2,-3,-2,-3,-3,-4,-1,-5,-5,-7,0,";
		S+="N,-4,1,0,-1,0,0,2,2,1,1,2,0,1,-2,-2,-3,-2,-4,-2,-4,0,";
		S+="D,-5,0,0,-1,0,1,2,4,3,2,1,-1,0,-3,-2,-4,-2,-6,-4,-7,0,";
		S+="E,-5,0,0,-1,0,0,1,3,4,2,1,-1,0,-2,-2,-3,-2,-5,-4,-7,0,";
		S+="Q,-5,-1,-1,0,0,-1,1,2,2,4,3,1,1,-1,-2,-2,-2,-5,-4,-5,0,";
		S+="H,-3,-1,-1,0,-1,-2,2,1,1,3,6,2,0,-2,-2,-2,-2,-2,0,-3,0,";
		S+="R,-4,0,-1,0,-2,-3,0,-1,-1,1,2,6,3,0,-2,-3,-2,-4,-4,2,0,";
		S+="K,-5,0,0,-1,-1,-2,1,0,0,1,0,3,5,0,-2,-3,-2,-5,-4,-3,0,";
		S+="M,-5,-2,-1,-2,-1,-3,-2,-3,-2,-1,-2,0,0,6,2,4,2,0,-2,-4,0,";
		S+="I,-2,-1,0,-2,-1,-3,-2,-2,-2,-2,-2,-2,-2,2,5,2,4,1,-1,-5,0,";
		S+="L,-6,-3,-2,-3,-2,-4,-3,-4,-3,-2,-2,-3,-3,4,2,6,2,2,-1,-2,0,";
		S+="V,-2,-1,0,-1,0,-1,-2,-2,-2,-2,-2,-2,-2,2,4,2,4,-1,-2,-6,0,";
		S+="F,-4,-3,-3,-5,-5,-5,-4,-6,-5,-5,-2,-4,-5,0,1,2,-1,9,7,0,0,";
		S+="Y,0,-3,-3,-5,-3,-5,-2,-4,-4,-4,0,-4,-4,-2,-1,-1,-2,7,10,0,0,";
		S+="W,-8,-2,-5,-6,-6,-7,-4,-7,-7,-5,-3,2,-3,-4,-5,-2,-6,0,0,17,0,";
		S+="*,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";
	 
	 }
	
//	Shifting left
	System.out.println(" Shifting left ");
	String base1="";
	String base2="";
	String base3="";
	String codon="";
	String codon2="";
	int NoCodon=0, NumFS=0; 
	double SumScore=0;
	String outstr="";
		 
	for (int i=1;i<=4;i++){
		if (i==1){ base1="A";}
		else if (i==2){ base1="G";}
		else if (i==3){ base1="C";}
		else if (i==4){ base1="T";}
				
		for (int j=1;j<=4;j++){
			if (j==1){ base2="A";}
			else if (j==2){ base2="G";}
			else if (j==3){ base2="C";}
			else if (j==4){ base2="T";}
			for (int k=1;k<=4;k++){
				if (k==1){ base3="A";}
				else if (k==2){ base3="G";}
				else if (k==3){ base3="C";}
				else if (k==4){ base3="T";}
				
				codon=base1+base2+base3;
				
				String AA=FrameshiftCodonPair.translate(codon);
				NoCodon++;
			//	System.out.println("Codon"+String.valueOf(NoCodon)+": "+ codon+": "+AA +"-->");
				for (int s=1;s<=4;s++){
				   codon2=codon.substring(1,codon.length());
					if (s==1){ codon2+="A";}
					else if (s==2){ codon2+="G";}
					else if (s==3){ codon2+="C";}
					else if (s==4){ codon2+="T";}
					
					String AA2=FrameshiftCodonPair.translate(codon2);
					if (!AA.equals(AA2)){
						outstr="     "+codon2+": "+AA2;
						String SScore=FrameshiftCodonPair.substitution_score(S, AA, AA2);
					//	System.out.println(outstr+": "+String.valueOf(SScore));
						NumFS++;
						SumScore+= Double.parseDouble(SScore);
					}
				}
			}
		}
	}
	
	System.out.println("    Number of Substitutions="+String.valueOf(NumFS));
	System.out.println("    Sum Score="+String.valueOf(SumScore));
	System.out.println("    Average Score="+String.valueOf(SumScore/NumFS));
	double SumScore1=SumScore;
	int NumFS1=NumFS;
			
//	Shifting right
	System.out.println(" Shifting right");
	base1="";
	base2="";
	base3="";
	codon="";
	codon2="";
	NoCodon=0;
	NumFS=0; 
	SumScore=0;
	outstr="";
		 
	for (int i=1;i<=4;i++){
		if (i==1){ base1="A";}
		else if (i==2){ base1="G";}
		else if (i==3){ base1="C";}
		else if (i==4){ base1="T";}
				
		for (int j=1;j<=4;j++){
			if (j==1){ base2="A";}
			else if (j==2){ base2="G";}
			else if (j==3){ base2="C";}
			else if (j==4){ base2="T";}
			for (int k=1;k<=4;k++){
				if (k==1){ base3="A";}
				else if (k==2){ base3="G";}
				else if (k==3){ base3="C";}
				else if (k==4){ base3="T";}
				
				codon=base1+base2+base3;
				
				String AA=FrameshiftCodonPair.translate(codon);
				NoCodon++;
			//	System.out.println("Codon"+String.valueOf(NoCodon)+": "+ codon+": "+AA +"-->");
				for (int s=1;s<=4;s++){
				   codon2=codon.substring(0,codon.length()-1);
					if (s==1){ codon2="A"+codon2;}
					else if (s==2){ codon2="G"+codon2;}
					else if (s==3){ codon2="C"+codon2;}
					else if (s==4){ codon2="T"+codon2;}
					
					String AA2=FrameshiftCodonPair.translate(codon2);
					if (!AA.equals(AA2)){
						outstr="     "+codon2+": "+AA2;
						String SScore=FrameshiftCodonPair.substitution_score(S, AA, AA2);
					//	System.out.println(outstr+": "+String.valueOf(SScore));
						NumFS++;
						SumScore+= Double.parseDouble(SScore);
					}
				}
			}
		}
	}
	
	System.out.println("    Number of Substitutions="+String.valueOf(NumFS));
	System.out.println("    Sum Score="+String.valueOf(SumScore));
	System.out.println("    Average Score="+String.valueOf(SumScore/NumFS));
	double SumScore2=SumScore;
	int NumFS2=NumFS;
			
//	Wobbling
	System.out.println(" Wobbling");
	base1="";
	base2="";
	base3="";
	codon="";
	codon2="";
	NoCodon=0;
	NumFS=0; 
	SumScore=0;
	outstr="";
		 
	for (int i=1;i<=4;i++){
		if (i==1){ base1="A";}
		else if (i==2){ base1="G";}
		else if (i==3){ base1="C";}
		else if (i==4){ base1="T";}
				
		for (int j=1;j<=4;j++){
			if (j==1){ base2="A";}
			else if (j==2){ base2="G";}
			else if (j==3){ base2="C";}
			else if (j==4){ base2="T";}
			for (int k=1;k<=4;k++){
				if (k==1){ base3="A";}
				else if (k==2){ base3="G";}
				else if (k==3){ base3="C";}
				else if (k==4){ base3="T";}
				
				codon=base1+base2+base3;
				
				String AA=FrameshiftCodonPair.translate(codon);
				NoCodon++;
			//	System.out.println("Codon"+String.valueOf(NoCodon)+": "+ codon+": "+AA +"-->");
				for (int s=1;s<=4;s++){
				   codon2=codon.substring(0,codon.length()-1);
					if (s==1){ codon2+="A";}
					else if (s==2){ codon2+="G";}
					else if (s==3){ codon2+="C";}
					else if (s==4){ codon2+="T";}
					
					String AA2=FrameshiftCodonPair.translate(codon2);
					if (!AA.equals(AA2)){
						outstr="     "+codon2+": "+AA2;
						String SScore=FrameshiftCodonPair.substitution_score(S, AA, AA2);
					//	System.out.println(outstr+": "+String.valueOf(SScore));
						NumFS++;
						SumScore+= Double.parseDouble(SScore);
					}
				}
			}
		}
	}
	
	System.out.println("    Number of Substitutions="+String.valueOf(NumFS));
	System.out.println("    Sum Score="+String.valueOf(SumScore));
	System.out.println("    Average Score="+String.valueOf(SumScore/NumFS));
	double SumScore3=SumScore;
	int NumFS3=NumFS;
	
//	Jumping
	System.out.println(" Jumping");
	base1="";
	base2="";
	base3="";
	codon="";
	codon2="";
	NoCodon=0;
	NumFS=0; 
	SumScore=0;
	outstr="";
		 
	for (int i=1;i<=4;i++){
		if (i==1){ base1="A";}
		else if (i==2){ base1="G";}
		else if (i==3){ base1="C";}
		else if (i==4){ base1="T";}
				
		for (int j=1;j<=4;j++){
			if (j==1){ base2="A";}
			else if (j==2){ base2="G";}
			else if (j==3){ base2="C";}
			else if (j==4){ base2="T";}
			for (int k=1;k<=4;k++){
				if (k==1){ base3="A";}
				else if (k==2){ base3="G";}
				else if (k==3){ base3="C";}
				else if (k==4){ base3="T";}
				
				codon=base1+base2+base3;
				
				String AA=FrameshiftCodonPair.translate(codon);
				NoCodon++;
			//	System.out.println("Codon"+String.valueOf(NoCodon)+": "+ codon+": "+AA +"-->");
				for (int s=1;s<=4;s++){
				   codon2=codon.substring(1,codon.length());
					if (s==1){ codon2="A"+codon2;}
					else if (s==2){ codon2="G"+codon2;}
					else if (s==3){ codon2="C"+codon2;}
					else if (s==4){ codon2="T"+codon2;}
					
						String AA2=FrameshiftCodonPair.translate(codon2);
						outstr="     "+codon2+": "+AA2;
					if (!AA.equals(AA2)){
						String SScore=FrameshiftCodonPair.substitution_score(S, AA, AA2);
					//	System.out.println(outstr+": "+String.valueOf(SScore));
						NumFS++;
						SumScore+= Double.parseDouble(SScore);
					}
				}
			}
		}
	}
	
	System.out.println("    Number of Substitutions="+String.valueOf(NumFS));
	System.out.println("    Sum Score="+String.valueOf(SumScore));
	System.out.println("    Average Score="+String.valueOf(SumScore/NumFS));
	double SumScore4=SumScore;
	int NumFS4=NumFS;
	
//	All Substitutions
	System.out.println("\n All Substitutions:");
	NoCodon=0;
	int NumSub=0;
	double TotalSumScore=0;
	for (int i=1;i<=4;i++){
		if (i==1){ base1="A";}
		else if (i==2){ base1="G";}
		else if (i==3){ base1="C";}
		else if (i==4){ base1="T";}
				
		for (int j=1;j<=4;j++){
			if (j==1){ base2="A";}
			else if (j==2){ base2="G";}
			else if (j==3){ base2="C";}
			else if (j==4){ base2="T";}
			for (int k=1;k<=4;k++){
				if (k==1){ base3="A";}
				else if (k==2){ base3="G";}
				else if (k==3){ base3="C";}
				else if (k==4){ base3="T";}
				
				codon=base1+base2+base3;
				
				String AA=FrameshiftCodonPair.translate(codon);
				NoCodon++;
		//		System.out.println("Codon"+String.valueOf(NoCodon)+": "+ codon+": "+AA +"-->");
					
				for (int l=1;l<=4;l++){
					if (l==1){ base1="A";}
					else if (l==2){ base1="G";}
					else if (l==3){ base1="C";}
					else if (l==4){ base1="T";}
							
					for (int m=1;m<=4;m++){
						if (m==1){ base2="A";}
						else if (m==2){ base2="G";}
						else if (m==3){ base2="C";}
						else if (m==4){ base2="T";}
						for (int n=1;n<=4;n++){
							if (n==1){ base3="A";}
							else if (n==2){ base3="G";}
							else if (n==3){ base3="C";}
							else if (n==4){ base3="T";}
							
							codon2=base1+base2+base3;
							String AA1=FrameshiftCodonPair.translate(codon2);
							if (!AA.equals(AA1)){
								outstr="     "+codon2+": "+AA1;
								String SScore=FrameshiftCodonPair.substitution_score(S, AA, AA1);
							//	System.out.println(outstr+": "+String.valueOf(SScore));
								NumSub++;
								TotalSumScore+= Double.parseDouble(SScore);
							}
						}
					}
				}
			}
		}
	}
	
	System.out.println("    Number of Substitutions="+String.valueOf(NumSub));
	System.out.println("    Sum Score="+String.valueOf(TotalSumScore));
	System.out.println("    Average Score="+String.valueOf(TotalSumScore/NumSub));

	System.out.println("\n All except Frameshift:");
	System.out.println("    Number of Substitutions="+String.valueOf(NumSub-NumFS1-NumFS2));
	System.out.println("    Sum Score="+String.valueOf(TotalSumScore-SumScore1-SumScore2));
	System.out.println("    Average Score="+String.valueOf((TotalSumScore-SumScore1-SumScore2)/(NumSub-NumFS1-NumFS2)));
	
	System.out.println("\n All Other:");
	System.out.println("    Number of Substitutions="+String.valueOf(NumSub-NumFS1-NumFS2-NumFS3-NumFS4));
	System.out.println("    Sum Score="+String.valueOf(TotalSumScore-SumScore1-SumScore2-SumScore3-SumScore4));
	System.out.println("    Average Score="+String.valueOf((TotalSumScore-SumScore1-SumScore2-SumScore3-SumScore4)/(NumSub-NumFS1-NumFS2-NumFS3-NumFS4)));
	
}
	System.out.println("\n Calculated SUCCESS!\n");

}
catch(Exception e){
            System.out.println("\n Usage:java Frameshift");
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

}
