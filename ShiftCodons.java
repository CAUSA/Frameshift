/*
==============================================================
ShiftCodons.java version 2.1.001 
=============================================================
*/

import java.io.*;
import java.util.*;
public class ShiftCodons
{
	public static void main(String[] args)
	{
	try
	{
        System.out.println("\n Usage: computes the frameshift substitution scores (FSS) for each kind of codon substitution");
       System.out.println("\n Usage: java ShiftCodons <-M=1/2/3> \n use -M=1/2/3 to select scoring matrix: \n \t\t\t1=GON250; \n \t\t\t2=BLOSSUM62; \t\t\t 3=PAM250\n");

		Translation Trobj=new Translation();

		String allAAs="";	
		String[] S=new String [22];
		
		String M=args[0].toUpperCase();
			
		if (M.equals("-M=1")||M.equals("1"))
		{
			M="Gon250";
			System.out.println("Gon250");
			allAAs="ACDEFGHIKLMNPQRSTVWY*";
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
		 }
		else if (M.equals("-M=2")||M.equals("2"))
		{
			M="Blossum62";
			allAAs="CSTPAGNDEQHRKMILVFYW*";	
			S[1]="C,9,-1,-1,-3,0,-3,-3,-3,-4,-3,-3,-3,-3,-1,-1,-1,-1,-2,-2,-2,0,";
			S[2]="S,-1,4,1,-1,1,0,1,0,0,0,-1,-1,0,-1,-2,-2,-2,-2,-2,-3,0,";
			S[3]="T,-1,1,4,1,-1,1,0,1,0,0,0,-1,0,-1,-2,-2,-2,-2,-2,-3,0,";
			S[4]="P,-3,-1,1,7,-1,-2,-1,-1,-1,-1,-2,-2,-1,-2,-3,-3,-2,-4,-3,-4,0,";
			S[5]="A,0,1,-1,-1,4,0,-1,-2,-1,-1,-2,-1,-1,-1,-1,-1,-2,-2,-2,-3,0,";
			S[6]="G,-3,0,1,-2,0,6,-2,-1,-2,-2,-2,-2,-2,-3,-4,-4,0,-3,-3,-2,0,";
			S[7]="N,-3,1,0,-2,-2,0,6,1,0,0,-1,0,0,-2,-3,-3,-3,-3,-2,-4,0,";
			S[8]="D,-3,0,1,-1,-2,-1,1,6,2,0,-1,-2,-1,-3,-3,-4,-3,-3,-3,-4,0,";
			S[9]="E,-4,0,0,-1,-1,-2,0,2,5,2,0,0,1,-2,-3,-3,-3,-3,-2,-3,0,";
			S[10]="Q,-3,0,0,-1,-1,-2,0,0,2,5,0,1,1,0,-3,-2,-2,-3,-1,-2,0,";
			S[11]="H,-3,-1,0,-2,-2,-2,1,1,0,0,8,0,-1,-2,-3,-3,-2,-1,2,-2,0,";
			S[12]="R,-3,-1,-1,-2,-1,-2,0,-2,0,1,0,5,2,-1,-3,-2,-3,-3,-2,-3,0,";
			S[13]="K,-3,0,0,-1,-1,-2,0,-1,1,1,-1,2,5,-1,-3,-2,-3,-3,-2,-3,0,";
			S[14]="M,-1,-1,-1,-2,-1,-3,-2,-3,-2,0,-2,-1,-1,5,1,2,-2,0,-1,-1,0,";
			S[15]="I,-1,-2,-2,-3,-1,-4,-3,-3,-3,-3,-3,-3,-3,1,4,2,1,0,-1,-3,0,";
			S[16]="L,-1,-2,-2,-3,-1,-4,-3,-4,-3,-2,-3,-2,-2,2,2,4,3,0,-1,-2,0,";
			S[17]="V,-1,-2,-2,-2,0,-3,-3,-3,-2,-2,-3,-3,-2,1,3,1,4,-1,-1,-3,0,";
			S[18]="F,-2,-2,-2,-4,-2,-3,-3,-3,-3,-3,-1,-3,-3,0,0,0,-1,6,3,1,0,";
			S[19]="Y,-2,-2,-2,-3,-2,-3,-2,-3,-2,-1,2,-2,-2,-1,-1,-1,-1,3,7,2,0,";
			S[20]="W,-2,-3,-3,-4,-3,-2,-4,-4,-3,-2,-2,-3,-3,-1,-3,-2,-3,1,2,11,0,";
			S[21]="*,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";
		}
		else if (M.equals("-M=3")||M.equals("3"))
		{
			M="PAM250";
			allAAs="CSTPAGNDEQHRKMILVFYW*";
			S[1]="C,12,0,-2,-3,-2,-3,-4,-5,-5,-5,-3,-4,-5,-5,-2,-6,-2,-4,0,-8,0,";
			S[2]="S,0,2,1,1,1,1,1,0,0,-1,-1,0,0,-2,-1,-3,-1,-3,-3,-2,0,";
			S[3]="T,-2,1,3,0,1,0,0,0,0,-1,-1,-1,0,-1,0,-2,0,-3,-3,-5,0,";
			S[4]="P,-3,1,0,6,1,-1,-1,-1,-1,0,0,0,-1,-2,-2,-3,-1,-5,-5,-6,0,";
			S[5]="A,-2,1,1,1,2,1,0,0,0,0,-1,-2,-1,-1,-1,-2,0,-5,-3,-6,0,";
			S[6]="G,-3,1,0,-1,1,5,0,1,0,-1,-2,-3,-2,-3,-3,-4,-1,-5,-5,-7,0,";
			S[7]="N,-4,1,0,-1,0,0,2,2,1,1,2,0,1,-2,-2,-3,-2,-4,-2,-4,0,";
			S[8]="D,-5,0,0,-1,0,1,2,4,3,2,1,-1,0,-3,-2,-4,-2,-6,-4,-7,0,";
			S[9]="E,-5,0,0,-1,0,0,1,3,4,2,1,-1,0,-2,-2,-3,-2,-5,-4,-7,0,";
			S[10]="Q,-5,-1,-1,0,0,-1,1,2,2,4,3,1,1,-1,-2,-2,-2,-5,-4,-5,0,";
			S[11]="H,-3,-1,-1,0,-1,-2,2,1,1,3,6,2,0,-2,-2,-2,-2,-2,0,-3,0,";
			S[12]="R,-4,0,-1,0,-2,-3,0,-1,-1,1,2,6,3,0,-2,-3,-2,-4,-4,2,0,";
			S[13]="K,-5,0,0,-1,-1,-2,1,0,0,1,0,3,5,0,-2,-3,-2,-5,-4,-3,0,";
			S[14]="M,-5,-2,-1,-2,-1,-3,-2,-3,-2,-1,-2,0,0,6,2,4,2,0,-2,-4,0,";
			S[15]="I,-2,-1,0,-2,-1,-3,-2,-2,-2,-2,-2,-2,-2,2,5,2,4,1,-1,-5,0,";
			S[16]="L,-6,-3,-2,-3,-2,-4,-3,-4,-3,-2,-2,-3,-3,4,2,6,2,2,-1,-2,0,";
			S[17]="V,-2,-1,0,-1,0,-1,-2,-2,-2,-2,-2,-2,-2,2,4,2,4,-1,-2,-6,0,";
			S[18]="F,-4,-3,-3,-5,-5,-5,-4,-6,-5,-5,-2,-4,-5,0,1,2,-1,9,7,0,0,";
			S[19]="Y,0,-3,-3,-5,-3,-5,-2,-4,-4,-4,0,-4,-4,-2,-1,-1,-2,7,10,0,0,";
			S[20]="W,-8,-2,-5,-6,-6,-7,-4,-7,-7,-5,-3,2,-3,-4,-5,-2,-6,0,0,17,0,";
			S[21]="*,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";
		 }
		 else 
		 {
			System.out.println("Input is illegal: "+args[0]);
			System.exit(0); 
		 }
			
		//Preparing the scoring matrix
	 
		System.out.println("Using scoring matrix: "+M);
		
		int [][] ScoreMatrix = new int [22][22];
		int i,j,k,s,l,m,n;
		for ( i=1;i<=21;i++)
		{
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
		 
		System.out.println("All");
		System.out.println("Same Codons");
		System.out.println("NSS-Positive");
		System.out.println("NSS-Negative");
		System.out.println("SS");
		System.out.println("Sum");
		System.out.println("Average");
		
	//	Forward Shifting
		System.out.println(" Forward Shifting ");
		String base1="";
		String base2="";
		String base3="";
		String codon1="";
		String codon2="";
		int NoCodon=0, NumFS=0; 
		double SumScore=0;
		String outstr="";
		String AA1="";
		String AA2="";
		int AA1No=0;
		int AA2No=0;
		int SSS=0;
		int NSS1=0;
		int NSS2=0;
		int NumberOfSameCodons=0;
		
		for ( i=1;i<=4;i++)
		{
			if (i==1){ base1="A";}
			else if (i==2){ base1="G";}
			else if (i==3){ base1="C";}
			else if (i==4){ base1="T";}
					
			for ( j=1;j<=4;j++)
			{
				if (j==1){ base2="A";}
				else if (j==2){ base2="G";}
				else if (j==3){ base2="C";}
				else if (j==4){ base2="T";}
				for (k=1;k<=4;k++)
				{
					if (k==1){ base3="A";}
					else if (k==2){ base3="G";}
					else if (k==3){ base3="C";}
					else if (k==4){ base3="T";}
					
					codon1=base1+base2+base3;
					AA1=Translation.translate(codon1);
					NoCodon++;
					System.out.println("Codon"+String.valueOf(NoCodon)+":"+ codon1+":"+ AA1 +"-->");
					for (s=1;s<=4;s++)
					{
					   codon2=codon1.substring(1,codon1.length());
						if (s==1){ codon2+="A";}
						else if (s==2){ codon2+="G";}
						else if (s==3){ codon2+="C";}
						else if (s==4){ codon2+="T";}
							if (codon1.equals(codon2)){NumberOfSameCodons++;}
							AA2=Translation.translate(codon2);
							outstr="     "+codon2+": "+AA2;
							AA1No=allAAs.indexOf(AA1)+1;
							AA2No=allAAs.indexOf(AA2)+1;
							float SScore=ScoreMatrix[AA1No][AA2No];
							NumFS++;
							SumScore+= SScore;
						System.out.println(outstr+" "+String.valueOf(SScore));
						if (AA1.equals(AA2))
						{
							SSS++;
							//System.out.println(outstr+": "+String.valueOf(SScore));
						}
						else 
						{
							if (SScore>=0) {NSS1++;} else {NSS2++;}
						}
					}
				}
			}
		}
		
		System.out.println(String.valueOf(NumFS));
		System.out.println(String.valueOf(NumberOfSameCodons));
		System.out.println(String.valueOf(NSS1));
		System.out.println(String.valueOf(NSS2));
		System.out.println(String.valueOf(SSS));
		System.out.println(String.valueOf(SumScore));
		System.out.println(String.valueOf(SumScore/NumFS));
		double SumScore1=SumScore;
		int NumFS1=NumFS;
		base1="";
		base2="";
		base3="";
		codon1="";
		codon2="";
		NoCodon=0;
		NumFS=0; 
		SumScore=0;
		outstr="";
		NSS1=0;
		NSS2=0;
		SSS=0;
		NumberOfSameCodons=0;
				
	//	Backward Shifting
		System.out.println(" Backward Shifting");
			 
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
					
					codon1=base1+base2+base3;
					
					AA1=Translation.translate(codon1);
					NoCodon++;
					System.out.println("Codon"+String.valueOf(NoCodon)+":"+ codon1+":"+AA1 +"-->");
					for ( s=1;s<=4;s++){
					   codon2=codon1.substring(0,codon1.length()-1);
						if (s==1){ codon2="A"+codon2;}
						else if (s==2){ codon2="G"+codon2;}
						else if (s==3){ codon2="C"+codon2;}
						else if (s==4){ codon2="T"+codon2;}
							if (codon1.equals(codon2)){NumberOfSameCodons++;}
							AA2=Translation.translate(codon2);
							outstr="     "+codon2+" "+AA2;
							AA1No=allAAs.indexOf(AA1)+1;
							AA2No=allAAs.indexOf(AA2)+1;
							float SScore=ScoreMatrix[AA1No][AA2No];
							NumFS++;
							SumScore+= SScore;
							System.out.println(outstr+"  "+String.valueOf(SScore));
							if (AA1.equals(AA2)){
								SSS++;
							//	System.out.println(outstr+": "+String.valueOf(SScore));
							}
							else {
								if (SScore>=0) {NSS1++;} else {NSS2++;}
							}
					}
				}
			}
		}
		
		System.out.println(String.valueOf(NumFS));
		System.out.println(String.valueOf(NumberOfSameCodons));
		System.out.println(String.valueOf(NSS1));
		System.out.println(String.valueOf(NSS2));
		System.out.println(String.valueOf(SSS));
		System.out.println(String.valueOf(SumScore));
		System.out.println(String.valueOf(SumScore/NumFS));
		base1="";
		base2="";
		base3="";
		codon1="";
		codon2="";
		NoCodon=0;
		NumFS=0; 
		SumScore=0;
		outstr="";
		NSS1=0;
		NSS2=0;
		SSS=0;
		NumberOfSameCodons=0;
		double SumScore2=SumScore;
		int NumFS2=NumFS;
				
	//	Wobbling
		System.out.println(" Wobbling");
			 
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
					
					codon1=base1+base2+base3;
					
					AA1=Translation.translate(codon1);
					NoCodon++;
					System.out.println("Codon"+String.valueOf(NoCodon)+":"+ codon1+":"+AA1 +"-->");
					for (s=1;s<=4;s++){
					   codon2=codon1.substring(0,codon1.length()-1);
						if (s==1){ codon2+="A";}
						else if (s==2){ codon2+="G";}
						else if (s==3){ codon2+="C";}
						else if (s==4){ codon2+="T";}
							if (codon1.equals(codon2)){NumberOfSameCodons++;}
							AA2=Translation.translate(codon2);
							outstr="     "+codon2+" "+AA2;
							AA1No=allAAs.indexOf(AA1)+1;
							AA2No=allAAs.indexOf(AA2)+1;
							float SScore=ScoreMatrix[AA1No][AA2No];
							NumFS++;
							SumScore+= SScore;
							System.out.println(outstr+" "+String.valueOf(SScore));
							if (AA1.equals(AA2)){
								SSS++;
								//System.out.println(outstr+": "+String.valueOf(SScore));
							}
							else {
								if (SScore>=0) {NSS1++;} else {NSS2++;}
							}
						}
					}
				}
			}
			
			System.out.println(String.valueOf(NumFS));
			System.out.println(String.valueOf(NumberOfSameCodons));
			System.out.println(String.valueOf(NSS1));
			System.out.println(String.valueOf(NSS2));
			System.out.println(String.valueOf(SSS));
			System.out.println(String.valueOf(SumScore));
			System.out.println(String.valueOf(SumScore/NumFS));
			base1="";
			base2="";
			base3="";
			codon1="";
			codon2="";
			NoCodon=0;
			NumFS=0; 
			SumScore=0;
			outstr="";
			NSS1=0;
			NSS2=0;
			SSS=0;
			NumberOfSameCodons=0;
		double SumScore3=SumScore;
		int NumFS3=NumFS;
		
	//	Jumping
		System.out.println(" Jumping");
			 
		for (i=1;i<=4;i++){
			if (i==1){ base1="A";}
			else if (i==2){ base1="G";}
			else if (i==3){ base1="C";}
			else if (i==4){ base1="T";}
					
			for (j=1;j<=4;j++){
				if (j==1){ base2="A";}
				else if (j==2){ base2="G";}
				else if (j==3){ base2="C";}
				else if (j==4){ base2="T";}
				for (k=1;k<=4;k++){
					if (k==1){ base3="A";}
					else if (k==2){ base3="G";}
					else if (k==3){ base3="C";}
					else if (k==4){ base3="T";}
					
					codon1=base1+base2+base3;
					
					AA1=Translation.translate(codon1);
					NoCodon++;
					System.out.println("Codon"+String.valueOf(NoCodon)+":"+ codon1+":"+AA1 +"-->");
					for (s=1;s<=4;s++){
					   codon2=codon1.substring(1,codon1.length());
						if (s==1){ codon2="A"+codon2;}
						else if (s==2){ codon2="G"+codon2;}
						else if (s==3){ codon2="C"+codon2;}
						else if (s==4){ codon2="T"+codon2;}
							if (codon1.equals(codon2)){NumberOfSameCodons++;}
							AA2=Translation.translate(codon2);
							outstr="     "+codon2+" "+AA2;
							AA1No=allAAs.indexOf(AA1)+1;
							AA2No=allAAs.indexOf(AA2)+1;
							float SScore=ScoreMatrix[AA1No][AA2No];
							NumFS++;
							SumScore+= SScore;
							System.out.println(outstr+" "+String.valueOf(SScore));
							if (AA1.equals(AA2)){
								SSS++;
								//System.out.println(outstr+": "+String.valueOf(SScore));
							}
							else {
								if (SScore>=0) {NSS1++;} else {NSS2++;}
							}
						}
					}
				}
			}
			
			System.out.println(String.valueOf(NumFS));
			System.out.println(String.valueOf(NumberOfSameCodons));
			System.out.println(String.valueOf(NSS1));
			System.out.println(String.valueOf(NSS2));
			System.out.println(String.valueOf(SSS));
			System.out.println(String.valueOf(SumScore));
			System.out.println(String.valueOf(SumScore/NumFS));
			base1="";
			base2="";
			base3="";
			codon1="";
			codon2="";
			NoCodon=0;
			NumFS=0; 
			SumScore=0;
			outstr="";
			NSS1=0;
			NSS2=0;
			SSS=0;
			NumberOfSameCodons=0;
		double SumScore4=SumScore;
		int NumFS4=NumFS;
		

	//	All Random Substitutions
		System.out.println("\n All Random Substitutions:");
		NoCodon=0;
		int NumSub=0;
		double TotalSumScore=0;
		for (i=1;i<=4;i++){
			if (i==1){ base1="A";}
			else if (i==2){ base1="G";}
			else if (i==3){ base1="C";}
			else if (i==4){ base1="T";}
					
			for ( j=1;j<=4;j++){
				if (j==1){ base2="A";}
				else if (j==2){ base2="G";}
				else if (j==3){ base2="C";}
				else if (j==4){ base2="T";}
				for (k=1;k<=4;k++){
					if (k==1){ base3="A";}
					else if (k==2){ base3="G";}
					else if (k==3){ base3="C";}
					else if (k==4){ base3="T";}
					
					codon1=base1+base2+base3;
					
					AA1=Translation.translate(codon1);
					NoCodon++;
					System.out.println("Codon"+String.valueOf(NoCodon)+":"+ codon1+":"+AA1 +"-->");
						
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
								
								codon2=base1+base2+base3;
								
								if (codon1.equals(codon2)){NumberOfSameCodons++;}
								
									AA2=Translation.translate(codon2);
									outstr="     "+codon2+" "+AA2;
									AA1No=allAAs.indexOf(AA1)+1;
									AA2No=allAAs.indexOf(AA2)+1;
									float SScore=ScoreMatrix[AA1No][AA2No];
									NumSub++;
									TotalSumScore+= SScore;
									System.out.println(outstr+" "+String.valueOf(SScore));
									if (AA1.equals(AA2)){
										SSS++;
										//System.out.println(outstr+": "+String.valueOf(SScore));
									}
									else {
										if (SScore>=0) {NSS1++;} else {NSS2++;}
									}
							}
						}
					}
				}
			}
		}
		
		System.out.println(String.valueOf(NumSub));
		System.out.println(String.valueOf(NumberOfSameCodons));
		System.out.println(String.valueOf(NSS1));
		System.out.println(String.valueOf(NSS2));
		System.out.println(String.valueOf(SSS));
		System.out.println(String.valueOf(TotalSumScore));
		System.out.println(String.valueOf(TotalSumScore/NumSub));
		System.out.println("\n All Other:");
		System.out.println(String.valueOf(NumSub-NumFS1-NumFS2-NumFS3-NumFS4));
		System.out.println(String.valueOf(TotalSumScore-SumScore1-SumScore2-SumScore3-SumScore4));
		System.out.println(String.valueOf((TotalSumScore-SumScore1-SumScore2-SumScore3-SumScore4)/(NumSub-NumFS1-NumFS2-NumFS3-NumFS4)));
		
		System.out.println("\n\n Calculated SUCCESS!\n");

	}
	catch(Exception e)
	{
		System.out.println(e);
		e.printStackTrace();
		}
	}

}
/*
==============================================================
This software is released under GNU/GPL license
Copyright (c) 2015, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
website: http://www.dnapluspro.com
================================================================
*/
