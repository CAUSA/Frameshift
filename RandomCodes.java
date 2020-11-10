/*
==============================================================
RandomCodes.java version 2.0.001 
=============================================================
*/

import java.util.ArrayList;
import java.util.List;
public class RandomCodes{

public static void main(String[] args) 
{
	Translation Trobj=new Translation();
	
	System.out.println(" Produce random genetic codes \n "); 
	System.out.println("\n Usage: RandomCodes <N> <S> <R>"); 
	System.out.println("\n N is the number of random codes: n=1~10,000,000;"); 
	System.out.println("\n S is the scoring matrix: 1=GON250; 2=BLOSSUM62; 3=PAM250"); 
	System.out.println("\n R is the number of replications: r=1~10,000. "); 
	System.out.println();

	int N=Integer.parseInt(args[0]); 
	int S=Integer.parseInt(args[1]);
	int R=Integer.parseInt(args[2]);
	
	String M="PAM250";	
	
	if (N<1||N>10000000||S>3||S<1||R<1||R>10000)
	{
		System.out.println("\nWrong parameter!"); 
		System.exit(0); 
	}
	
	System.out.println(" Parameter accepted: \n "); 
	System.out.println("\n N = " + N); 
	System.out.println("\n S = " + S); 
	System.out.println("\n R = " + R); 

	
	//Preparing the scoring matrix
	String[] C = new String[22];
	
	String allAAs="";
		
	M="PAM250";
	allAAs="CSTPAGNDEQHRKMILVFYW*";
	int[][] ScoringMatrix=new int[][]{
		{12,0,-2,-3,-2,-3,-4,-5,-5,-5,-3,-4,-5,-5,-2,-6,-2,-4,0,-8,0},
		{0,2,1,1,1,1,1,0,0,-1,-1,0,0,-2,-1,-3,-1,-3,-3,-2,0},
		{-2,1,3,0,1,0,0,0,0,-1,-1,-1,0,-1,0,-2,0,-3,-3,-5,0},
		{-3,1,0,6,1,-1,-1,-1,-1,0,0,0,-1,-2,-2,-3,-1,-5,-5,-6,0},
		{-2,1,1,1,2,1,0,0,0,0,-1,-2,-1,-1,-1,-2,0,-5,-3,-6,0},
		{-3,1,0,-1,1,5,0,1,0,-1,-2,-3,-2,-3,-3,-4,-1,-5,-5,-7,0},
		{-4,1,0,-1,0,0,2,2,1,1,2,0,1,-2,-2,-3,-2,-4,-2,-4,0},
		{-5,0,0,-1,0,1,2,4,3,2,1,-1,0,-3,-2,-4,-2,-6,-4,-7,0},
		{-5,0,0,-1,0,0,1,3,4,2,1,-1,0,-2,-2,-3,-2,-5,-4,-7,0},
		{-5,-1,-1,0,0,-1,1,2,2,4,3,1,1,-1,-2,-2,-2,-5,-4,-5,0},
		{-3,-1,-1,0,-1,-2,2,1,1,3,6,2,0,-2,-2,-2,-2,-2,0,-3,0},
		{-4,0,-1,0,-2,-3,0,-1,-1,1,2,6,3,0,-2,-3,-2,-4,-4,2,0},
		{-5,0,0,-1,-1,-2,1,0,0,1,0,3,5,0,-2,-3,-2,-5,-4,-3,0},
		{-5,-2,-1,-2,-1,-3,-2,-3,-2,-1,-2,0,0,6,2,4,2,0,-2,-4,0},
		{-2,-1,0,-2,-1,-3,-2,-2,-2,-2,-2,-2,-2,2,5,2,4,1,-1,-5,0},
		{-6,-3,-2,-3,-2,-4,-3,-4,-3,-2,-2,-3,-3,4,2,6,2,2,-1,-2,0},
		{-2,-1,0,-1,0,-1,-2,-2,-2,-2,-2,-2,-2,2,4,2,4,-1,-2,-6,0},
		{-4,-3,-3,-5,-5,-5,-4,-6,-5,-5,-2,-4,-5,0,1,2,-1,9,7,0,0},
		{0,-3,-3,-5,-3,-5,-2,-4,-4,-4,0,-4,-4,-2,-1,-1,-2,7,10,0,0},
		{-8,-2,-5,-6,-6,-7,-4,-7,-7,-5,-3,2,-3,-4,-5,-2,-6,0,0,17,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
	};
	if (S==1)
	{
		M="Gon250";
		allAAs="ACDEFGHIKLMNPQRSTVWY*";
		ScoringMatrix=new int[][]{
		{24,5,-3,0,-23,5,-8,-8,-4,-12,-7,-3,3,-2,-6,11,6,1,-36,-22,0},
		{5,115,-32,-30,-8,-20,-13,-11,-28,-15,-9,-18,-31,-24,-22,1,-5,0,-10,-5,0},
		{-3,-32,47,27,-45,1,4,-38,5,-40,-30,22,-7,9,-3,5,0,-29,-52,-28,0},
		{0,-30,27,36,-39,-8,4,-27,12,-28,-20,9,-5,17,4,2,-1,-19,-43,-27,0},
		{-23,-8,-45,-39,70,-52,-1,10,-33,20,16,-31,-38,-26,-32,-28,-22,1,36,51,0},
		{5,-20,1,-8,-52,66,-14,-45,-11,-44,-35,4,-16,-10,-10,4,-11,-33,-40,-40,0},
		{-8,-13,4,4,-1,-14,60,-22,6,-19,-13,12,-11,12,6,-2,-3,-20,-8,22,0},
		{-8,-11,-38,-27,10,-45,-22,40,-21,28,25,-28,-26,-19,-24,-18,-6,31,-18,-7,0},
		{-4,-28,5,12,-33,-11,6,-21,32,-21,-14,8,-6,15,27,1,1,-17,-35,-21,0},
		{-12,-15,-40,-28,20,-44,-19,28,-21,40,28,-30,-23,-16,-22,-21,-13,18,-7,0,0},
		{-7,-9,-30,-20,16,-35,-13,25,-14,28,43,-22,-24,-10,-17,-14,-6,16,-10,-2,0},
		{-3,-18,22,9,-31,4,12,-28,8,-30,-22,38,-9,7,3,9,5,-22,-36,-14,0},
		{3,-31,-7,-5,-38,-16,-11,-26,-6,-23,-24,-9,76,-2,-9,4,1,-18,-50,-31,0},
		{-2,-24,9,17,-26,-10,12,-19,15,-16,-10,7,-2,27,15,2,0,-15,-27,-17,0},
		{-6,-22,-3,4,-32,-10,6,-24,27,-22,-17,3,-9,15,47,-2,-2,-20,-16,-18,0},
		{11,1,5,2,-28,4,-2,-18,1,-21,-14,9,4,2,-2,22,15,-10,-33,-19,0},
		{6,-5,0,-1,-22,-11,-3,-6,1,-13,-6,5,1,0,-2,15,25,0,-35,-19,0},
		{1,0,-29,-19,1,-33,-20,31,-17,18,16,-22,-18,-15,-20,-10,0,34,-26,-11,0},
		{-36,-10,-52,-43,36,-40,-8,-18,-35,-7,-10,-36,-50,-27,-16,-33,-35,-26,142,41,0},
		{-22,-5,-28,-27,51,-40,22,-7,-21,0,-2,-14,-31,-17,-18,-19,-19,-11,41,78,0},	
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
		};
	}
	else 
	{
		if(S==2){
			M="Blossum62";
			allAAs="CSTPAGNDEQHRKMILVFYW*";	
			ScoringMatrix=new int[][]
			{
				{9,-1,-1,-3,0,-3,-3,-3,-4,-3,-3,-3,-3,-1,-1,-1,-1,-2,-2,-2,0},
				{-1,4,1,-1,1,0,1,0,0,0,-1,-1,0,-1,-2,-2,-2,-2,-2,-3,0},
				{-1,1,4,1,-1,1,0,1,0,0,0,-1,0,-1,-2,-2,-2,-2,-2,-3,0},
				{-3,-1,1,7,-1,-2,-1,-1,-1,-1,-2,-2,-1,-2,-3,-3,-2,-4,-3,-4,0},
				{0,1,-1,-1,4,0,-1,-2,-1,-1,-2,-1,-1,-1,-1,-1,-2,-2,-2,-3,0},
				{-3,0,1,-2,0,6,-2,-1,-2,-2,-2,-2,-2,-3,-4,-4,0,-3,-3,-2,0},
				{-3,1,0,-2,-2,0,6,1,0,0,-1,0,0,-2,-3,-3,-3,-3,-2,-4,0},
				{-3,0,1,-1,-2,-1,1,6,2,0,-1,-2,-1,-3,-3,-4,-3,-3,-3,-4,0},
				{-4,0,0,-1,-1,-2,0,2,5,2,0,0,1,-2,-3,-3,-3,-3,-2,-3,0},
				{-3,0,0,-1,-1,-2,0,0,2,5,0,1,1,0,-3,-2,-2,-3,-1,-2,0},
				{-3,-1,0,-2,-2,-2,1,1,0,0,8,0,-1,-2,-3,-3,-2,-1,2,-2,0},
				{-3,-1,-1,-2,-1,-2,0,-2,0,1,0,5,2,-1,-3,-2,-3,-3,-2,-3,0},
				{-3,0,0,-1,-1,-2,0,-1,1,1,-1,2,5,-1,-3,-2,-3,-3,-2,-3,0},
				{-1,-1,-1,-2,-1,-3,-2,-3,-2,0,-2,-1,-1,5,1,2,-2,0,-1,-1,0},
				{-1,-2,-2,-3,-1,-4,-3,-3,-3,-3,-3,-3,-3,1,4,2,1,0,-1,-3,0},
				{-1,-2,-2,-3,-1,-4,-3,-4,-3,-2,-3,-2,-2,2,2,4,3,0,-1,-2,0},
				{-1,-2,-2,-2,0,-3,-3,-3,-2,-2,-3,-3,-2,1,3,1,4,-1,-1,-3,0},
				{-2,-2,-2,-4,-2,-3,-3,-3,-3,-3,-1,-3,-3,0,0,0,-1,6,3,1,0},
				{-2,-2,-2,-3,-2,-3,-2,-3,-2,-1,2,-2,-2,-1,-1,-1,-1,3,7,2,0},
				{-2,-3,-3,-4,-3,-2,-4,-4,-3,-2,-2,-3,-3,-1,-3,-2,-3,1,2,11,0},	
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
			};
		}
	}
	
	System.out.println("Using scoring matrix: "+M);
	
	System.out.print("  ");
	for (int l=0;l<21;l++)
	{
		C[l]=allAAs.substring(l,l+1);
		System.out.print(C[l]+" ");
	}
	
	System.out.print("\n");
	
	for (int l=0;l<21;l++)
	{
		System.out.print(C[l]+" ");
		
		for (int j=0;j<21;j++)
		{
			System.out.print(ScoringMatrix[l][j]+" ");
		}
		System.out.print("\n");
	}

	//Preparing the standard genetic codons
	
	String bases="AUCG";
	String [] codons=new String[65];
	String [] AA=new String[65];
	
	System.out.print("Translation using the standard genetic code: \n");
	
	int c=0;
	
	for(int i1=0;i1<4;i1++)
	{
		String a1=bases.substring(i1,i1+1);
		
		for(int i2=0;i2<4;i2++)
		{
			String a2=bases.substring(i2,i2+1);
			
			for(int i3=0;i3<4;i3++)
			{
				String a3=bases.substring(i3,i3+1);
				
				c++;
				codons[c] = a1+a2+a3;							
				AA[c]  =  Translation.translate(codons[c]);
				System.out.print(c+": "+codons[c]+"("+AA[c]+") ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}		

	
	System.out.println("\nNo\taverage\tMax\tMin\tRank");
	
	double ScoreNaturalCode=0.0;
	double CodeScores[]= new double[N+1]; 
	
	//System.out.print("\nCode: "+String.valueOf(NumCodes)+": ");

	
	for(int r=1;r<=R;r++)
	{
		//System.out.println("\n Replication: " + r); 
		
		for(int i=0;i<=N;i++)
		{
			CodeScores[i]=0.0;
			
			//randomly exchange AA assignment to the 64 codons but keep the degenerative codons synonymous
			
			int x=1;
			int y=1;
			
			while (true)
			{
				x=(int)(Math.random()*20);
				y=(int)(Math.random()*20);
				if (y!=x) break;
			}

			String AAx=allAAs.substring(x,x+1);
			String AAy=allAAs.substring(y,y+1);
			
			for(int a=1;a<=64;a++)
			{
				if (AA[a].equals(AAx)) 
				{
					//if (i<2) System.out.println(codons[a]+":"+AA[a]+"<==>"+AAy);
					AA[a]=AAy; 
				}
				else if (AA[a].equals(AAy)) 
				{
					//if (i<2) System.out.println(codons[a]+":"+AA[a]+"<==>"+AAx);
					AA[a]=AAx;
				}
			}

			
			// frameshift codons
			
			String AA1="";
			String AA2="";
			String codon1="";
			String codon2="";
			
			for(int i1=0;i1<4;i1++)
			{
				String a1=bases.substring(i1,i1+1);
				for(int i2=0;i2<4;i2++)
				{
					String a2=bases.substring(i2,i2+1);
					for(int i3=0;i3<4;i3++)
					{
						String a3=bases.substring(i3,i3+1);
							
						codon1 = a1+a2+a3;
						
						//forward frameshifting
						for(int i4=0;i4<4;i4++)
						{
							String b0=bases.substring(i4,i4+1);
							
							codon2= b0+a1+a2;
							
							if (i==0)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons,AA);
								AA2=AlternativeTranslate(codon2,codons,AA);	
							}
							int idxAA1=allAAs.indexOf(AA1);
							int idxAA2=allAAs.indexOf(AA2);
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
							double Score=ScoringMatrix[idxAA1][idxAA2];
							CodeScores[i]+=Score;
							//System.out.println(Score);
						}
						
						//reverse frameshifting
						
						for(int i5=0;i5<4;i5++)
						{
							String b4 =bases.substring(i5,i5+1);
						
							codon2=a2+a3+b4;
							
							if (i==0)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons,AA);
								AA2=AlternativeTranslate(codon2,codons,AA);	
							}
							
							int idxAA1=allAAs.indexOf(AA1);
							int idxAA2=allAAs.indexOf(AA2);
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
							double Score1=ScoringMatrix[idxAA1][idxAA2];
							CodeScores[i]+=Score1;
						}
					}
				}
			}			
			//System.out.print(i+1+":");
			//System.out.println(CodeScores[i]);
		}
		
		
		
		/*
		//sort and output the scores if only one replication is specified (R=1)
		
		if (R==1)
		{
			
			System.out.println("\n\nTotal Number of Code: "+String.valueOf(N)+"\r\n");

			for(int g=0;g<N;g++)
			{
				System.out.print(g+1);
				System.out.print(": ");
				System.out.println(CodeScores[g]);
			}
			System.out.println(" ");
			
			for(int e=1;e<N;e++)
			{
				for(int f=e+1;f<=N;f++)
				{
					double temp;
					if(CodeScores[e]<CodeScores[f])
					{
						temp=CodeScores[e];
						CodeScores[e]=CodeScores[f];
						CodeScores[f]=temp;
					}
				}
			}
					
			System.out.println("Sorted FSS Scores: ");
			for(int g=0;g<N;g++)
			{
				System.out.print(g+1);
				System.out.print(": ");
				System.out.println(CodeScores[g]);
			}
		}
		*/
		
		// determine the rank of natural genetic code
		
		int Rank=1;
		ScoreNaturalCode=CodeScores[0];
		for(int g=1;g<N;g++)
		{
			if(CodeScores[g]>ScoreNaturalCode)
			{   
				Rank++;
			}
		}
		
		// Calculate average, max, and min
		
		double average=0.0;
		double max=CodeScores[0];
		double min=CodeScores[0];
		for(int g=1;g<N;g++)
		{
			if(CodeScores[g]>max) max=CodeScores[g];
			if(CodeScores[g]<min) min=CodeScores[g];
			average+=CodeScores[g];
		}
		
		average= (double) average/N;
		
		System.out.println(r+"\t"+String.valueOf(average)+"\t"+max+"\t"+min+"\t"+Rank);
	}
	
	System.out.println("\n");
	System.out.println("Scoring Matrix:"+M);
	System.out.println("Number of random genetic codes."+N);
	System.out.println("Score of the natural genetic codons: "+ScoreNaturalCode);
	
	System.out.println("\nAfter "+N+" x "+R+" random changing the amino acid assignment,the final random genetic codons is:\n");
	
	c=0;
	
	for(int i1=0;i1<4;i1++)
	{
		for(int i2=0;i2<4;i2++)
		{
			for(int i3=0;i3<4;i3++)
			{
				c++;
				System.out.print(c+": "+codons[c]+"("+AA[c]+") ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}

	public static String AlternativeTranslate(String codon1, String codons[],String AA[])
	{
		String amino="*";
		if(codon1.length() ==3)
		{
			codon1=codon1.toUpperCase();
			
			//System.out.println(codon1);
			for(int i=1;i<=64;i++)
			{

				//System.out.println(i+": "+ codons[i]+"-->"+AA[i]);
				
				if(codon1.equals(codons[i]))
				{
					amino=AA[i];
					break;
				}
			}
		}
		return amino;
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

