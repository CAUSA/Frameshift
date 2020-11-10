/*
==============================================================
AlternativeCodes - java version 1.0.001 
=============================================================
*/

import java.io.*;

public class AlternativeCodes{

	public static void main(String[] args) 
	{
	try
	{
		Translation Trobj=new Translation();
		
		int S=Integer.parseInt(args[0]);
	
		if (S>3||S<1)
		{
			System.out.println("\nWrong: Usage: RandomCodes <1/2/3>"); 
			System.out.println("\n The parameter is the scoring matrix: 1=GON250; 2=BLOSSUM62; 3=PAM250"); 
			System.exit(0); 			
		}
	
		String SM="PAM250";	
		String allAAs="CSTPAGNDEQHRKMILVFYW*";
		double[][] ScoringMatrix=new double[][]
		{
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
			SM="GON250";	
			allAAs="ACDEFGHIKLMNPQRSTVWY*";
			ScoringMatrix=new double[][]
			{
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
		}else if(S==2)
		{
			SM="Blossum62";	
			allAAs="CSTPAGNDEQHRKMILVFYW*";	
			ScoringMatrix=new double[][]
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

		System.out.println("\nUsing scoring Matrix: "+SM);
		double CodeScores[]=new double[20000]; 
		
		//Produce all possible permutations of AUCG

		int p = 0;
		String bases = "AUCG";
		String [][] P = new String[25][5];
		String[] b = new String[4] ; 
		b[0] = bases.substring(0,1);
		b[1] = bases.substring(1,2);
		b[2] = bases.substring(2,3);
		b[3] = bases.substring(3,4);
		
		for (int i = 0; i <= 3; i++)
		{
			String x = b[i];
			
			for (int j = 0;j <= 3; j++)
			{
				String y = b[j];
				
				if (y != x) 
				{
					for (int l = 0 ;l <= 3; l++)
					{
						String z = b[l];
						
						if (z != x && z != y) 
						{						
							for (int k = 0 ;k <= 3; k++)
							{									
								String c=b[k];
							   
							    if (c != x && c != y && c != z)
							    {									   
									
									P[p][1] = x ;
									P[p][2] = y ;
									P[p][3] = z ;
									P[p][4] = c ;
									p++;
									//System.out.print(P[p]+" ");
							    }
							}
						}
					}
				}
			}
		}

	//Produce alternative genetic codes by permutation
	
	//	System.out.println("\nNumber P: "+String.valueOf(p));
 
		String [][] CODONs = new String [20000][65];
		String [] AAs=new String [65];

		int N = 0; //Number of genetic codes
		int i = 0;

		for (int k = 0;k< p;k++) 
		{
			String [] B1=new String [5];
			B1[1] = P[k][1];
			B1[2] = P[k][2];
			B1[3] = P[k][3];
			B1[4] = P[k][4];
				
			 for (int l = 0;l< p;l++)
			 {			
				String [] B2=new String [5];
				B2[1] = P[l][1];
				B2[2] = P[l][2];
				B2[3] = P[l][3];
				B2[4] = P[l][4];
				
				for (int n = 0;n< p;n++)
				{
					String [] B3=new String [5]	;
					B3[1] = P[n][1];
					B3[2] = P[n][2];
					B3[3] = P[n][3];
					B3[4] = P[n][4];
				
					N++;
					i = 0;
					//System.out.println("\nCode: "+String.valueOf(N)+": ");
					
					//Produce alternative genetic codes
				
					for(int i1=1;i1<=4;i1++)
					{
						for(int i2=1;i2<=4;i2++)
						{
							for(int i3=1;i3<=4;i3++)
							{
								i++;					  
								CODONs[N][i] = B1[i1]+B2[i2]+B3[i3];
								//	System.out.print(CODONs[N][i]+" ");
								
								AAs[i]=Translation.translate(CODONs[1][i]);
								//get the  Standard Translation at the first code, after that, change CODONs but keep the AAs unchanged.   									
								//System.out.println(String.valueOf(i)+": "+CODONs[N][i]+": "+AAs[i]+" ");
							}
						}
					}
				}
			}
		}
		
		//Find and mark duplicate genetic codes
		
		String AA1="";
		String AA2="";
		String codon1="";
		String codon2="";
		
		/*
		
		System.out.println("\n Find and remove duplicate genetic codes: ");
		
		System.out.println("\n Total genetic codes: "+String.valueOf(N)+": ");
		
		for(int g=1;g<=N;g++)
		{
			System.out.println("\n Processing genetic codes: "+String.valueOf(g)+": ");
			
			CODONs[g][0]="Unique";
			
			for(int h=g+1;h<=N;h++)
			{
				CODONs[h][0]="Duplicate";
			}
				
			for(int h=g+1;h<=N;h++)
			{
				for(i=1;i<=64;i++)
				{
					AA1 = AlternativeTranslate(CODONs[1][i],CODONs[g],AAs);
					AA2 = AlternativeTranslate(CODONs[1][i],CODONs[h],AAs);
					
					if(!AA1.equals(AA2))
					{
						CODONs[h][0]="Unique";
					}
				}
			}
			
			//Remove duplicate genetic codes
			
			for(int h=g+1;h<=N;h++)
			{
				if(CODONs[h][0].equals("Duplicate"))
				{
					for(i=1;i<=64;i++)
					{
						CODONs[h][i]=CODONs[N][i];
					}
					CODONs[h][0]="Unique";
					N--;
				}
			}
		}
		
		*/
		
		//Frameshifting the codons and calculate FSSs
					
		System.out.println("\n Frameshifting the codons and calculate FSSs: ");
		
		System.out.println("\n Total genetic codes: "+String.valueOf(N)+": ");
		
		for(int g=1;g<=N;g++)
		{
			//System.out.println("\n Processing genetic codes: "+String.valueOf(g)+": ");
			
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
						AA1=AlternativeTranslate(codon1,CODONs[g],AAs);
						//System.out.println(codon1+" "+AA1+":");
						
						int idxAA1=allAAs.indexOf(AA1);

						//forward frameshifting
						for(int i4=0;i4<4;i4++)
						{
							String b0=bases.substring(i4,i4+1);
							codon2= b0+a1+a2;							
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							int idxAA2=allAAs.indexOf(AA2);
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index = ");
							//System.out.print(idxAA1);
							//System.out.print(", ");
							//System.out.print(idxAA2);
							//System.out.print(", score = ");
							
							double Score=ScoringMatrix[idxAA1][idxAA2];
							CodeScores[g]+=Score;
							//System.out.println(Score);
						}
						
						//reverse frameshifting
						
						for(int i5=0;i5<4;i5++)
						{
							String b4 =bases.substring(i5,i5+1);
						
							codon2=a2+a3+b4;
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							int idxAA2=allAAs.indexOf(AA2);
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: ");
							//System.out.print(idxAA1);
							//System.out.print(", ");
							//System.out.print(idxAA2);
							//System.out.print(", score = ");
							
							double Score1=ScoringMatrix[idxAA1][idxAA2];
							CodeScores[g]+=Score1;
							//System.out.println(Score1);
						}
					}
				}
			}
			//System.out.print(": FSS score:"+CodeScores[N-1]);
		}
		
		
		//sort the scores
		
		// for(int e=1;e<=N;e++)
		// {
		// 	for(int f=e+1;f<N;f++)
		// 	{
		// 		double temp;
		// 		if(CodeScores[e]<CodeScores[f])
		// 		{
		// 			temp=CodeScores[e];
		// 			CodeScores[e]=CodeScores[f];
		// 			CodeScores[f]=temp;
		// 		}
		// 	}
		// }

		
		// determine the rank of natural genetic code
		System.out.println("\n\ndetermine the rank of natural genetic code\n");
		
		int Rank=1;
		double ScoreNaturalCode=CodeScores[1];
		for(int g=1;g<=N;g++)
		{
			if(CodeScores[g]>ScoreNaturalCode)
			{   
				Rank++;
			}
		}

		System.out.println("\n\nTotal Number of Code: "+String.valueOf(N)+"\r\n");
		System.out.print("Score of the natural genetic code: ");
		System.out.println(ScoreNaturalCode);
		System.out.print("Rank of the natural genetic code: ");
		System.out.print(Rank);
		System.out.print("/");
		System.out.println(N);

		System.out.println("All alternative genetic codes and their FSS Scores: ");
		
		int c=0;
			
		System.out.print("AA:\t");
		
		for(int i1=0;i1<4;i1++)
		{
			for(int i2=0;i2<4;i2++)
			{
				for(int i3=0;i3<4;i3++)
				{
					c++;
					System.out.print(AAs[c]+" \t");
				}
			}
		}
		System.out.print("\n");
			
		for(int g=1;g<=N;g++)
		{
			System.out.print(g);
			System.out.print("\t");
			System.out.print(CodeScores[g]);
			System.out.print("\t");
			
			c=0;
			
			if (CODONs[g][0]!=null) System.out.print(CODONs[g][0]+"\t");
			
			for(int i1=0;i1<4;i1++)
			{
				for(int i2=0;i2<4;i2++)
				{
					for(int i3=0;i3<4;i3++)
					{
						c++;
						System.out.print(CODONs[g][c]+"\t");
					}
				}
			}
			System.out.print("\n");
		}
		
	}catch(Exception e)
	{
			System.out.println("\nWrong: Usage: AlternativeCodes [1/2/3]"); 
			System.out.println("\n The parameter is the scoring matrix: 1=GON250; 2=BLOSSUM62; 3=PAM250"); 
			System.out.println();
			System.out.println(e);
			e.printStackTrace();
	}
}
	
	public static String AlternativeTranslate(String codon1, String codon[],String aa[])
	{
		String amino="-";
		if(codon1.length() == 3)
		{			
			for(int i=1;i<=64;i++)
			{
				if(codon1.toUpperCase().equals(codon[i]))
				{
					amino=aa[i];break;
				}
			}
		}
		return amino;
	 }
}


