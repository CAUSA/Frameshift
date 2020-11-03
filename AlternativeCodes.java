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
		
		int s=Integer.parseInt(args[0]);
	
		if (s>3||s<1)
		{
			System.out.println("\nWrong: Usage: RandomCodes <1/2/3>"); 
			System.out.println("\n The parameter is the scoring matrix: 1=GON250; 2=BLOSSUM62; 3=PAM250"); 
			System.exit(0); 			
		}
	
		//C={"A","C","D","E","F","G","H","I","K","L","M","N","P","Q","R","S","T","V","W","Y"};
		String SM="PAM250";	
		double[][] ScoringMatrix=new double[][]{
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
		if (s==1)
		{
			SM="GON250";	
			//C={"A","C","D","E","F","G","H","I","K","L","M","N","P","Q","R","S","T","V","W","Y"};
			ScoringMatrix=new double[][]{
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
			for(int e=0;e<20;e++)
			{
				for(int f=0;f<20;f++)
				{
					ScoringMatrix[e][f]=ScoringMatrix[e][f]/10.0;
				}
			}
		}else {
			if(s==2){
				SM="Blossum62";	
			//C={"A","C","D","E","F","G","H","I","K","L","M","N","P","Q","R","S","T","V","W","Y"};
				ScoringMatrix=new double[][]{
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

		System.out.println("\nScoring Matrix: "+SM);
		double CodeScores[]=new double[100000]; 
		String [] aa=new String[64];
		
		//All permutations of UCAG

		int NumPermutations = 0;
		String [][] Permutations=new String[25][5];
		String[] b1=new String[4] ; 
		b1[0] = "U";
		b1[1] = "C";
		b1[2] = "A";
		b1[3] = "G";
		for (int i = 0 ;i<= 3;i++)
		{
			String x = b1[i];
			
			for (int j = 0;j<=3;j++)
			{
				String y = b1[j];
				
				if (y != x) 
				{
					for (int l = 0 ;l<=3;l++)
					{
					
						String z = b1[l];
						
						if (z != x && z != y) 
						{						
							for (int k = 0 ;k<=3;k++)
							{									
							   String c=b1[k];
							   
							   if (c != x && c != y && c != z)
							   {									   
									
									Permutations[NumPermutations][1] = x ;
									Permutations[NumPermutations][2] = y ;
									Permutations[NumPermutations][3] = z ;
									Permutations[NumPermutations][4] = c ;
									NumPermutations ++;
									//System.out.print(Permutations[NumPermutations]+" ");
							   }
							}
						}
					}
				}
			}
		}

	//	System.out.println("\nNumber Permutations: "+String.valueOf(NumPermutations));
 
		String[] code=new String[65];

		int NumCodes = 0;
		int i=0;

		for (int k = 0;k< NumPermutations;k++) 
		{
			String [] FirstBase=new String [5];
			FirstBase[1] = Permutations[k][1];
			FirstBase[2] = Permutations[k][2];
			FirstBase[3] = Permutations[k][3];
			FirstBase[4] = Permutations[k][4];
				
			 for (int l = 0;l< NumPermutations;l++)
			 {			
				String [] SecondBase=new String [5];
				SecondBase[1] = Permutations[l][1];
				SecondBase[2] = Permutations[l][2];
				SecondBase[3] = Permutations[l][3];
				SecondBase[4] = Permutations[l][4];
				
				 for (int n = 0;n< NumPermutations;n++)
				 {
					String [] ThirdBase=new String [5]	;
					ThirdBase[1] = Permutations[n][1];
					ThirdBase[2] = Permutations[n][2];
					ThirdBase[3] = Permutations[n][3];
					ThirdBase[4] = Permutations[n][4];
					
				
					NumCodes++;
					i=0;
					//System.out.print("\nCode: "+String.valueOf(NumCodes)+": ");
				
					for(int i1=1;i1<=4;i1++)
					{
						for(int i2=1;i2<=4;i2++)
						{
							for(int i3=1;i3<=4;i3++)
							{
								code[i] = FirstBase[i1]+SecondBase[i2]+ThirdBase[i3];
								if (NumCodes==1)//The first code is the standard code
								{
									aa[i]=Translation.translate(code[i]);
									//get the  Standard Translations 
									//Also will be used for alternative translations
									//System.out.println(String.valueOf(i)+": "+code[i]+": "+aa[i]+" ");
								}
							//	System.out.print(code[i]+" ");
								i++;					  
							}
						  
						}
					
					}
	
					String a[]=new String[3];
					for(int i1=0;i1<4;i1++){
						if(i1==0){a[0]="A";}
						if(i1==1){a[0]="U";}
						if(i1==2){a[0]="C";}
						if(i1==3){a[0]="G";}
						for(int i2=0;i2<4;i2++){
							if(i2==0){a[1]="A";}
							if(i2==1){a[1]="U";}
							if(i2==2){a[1]="C";}
							if(i2==3){a[1]="G";}
							for(int i3=0;i3<4;i3++){
								if(i3==0){a[2]="A";}
								if(i3==1){a[2]="U";}
								if(i3==2){a[2]="C";}
								if(i3==3){a[2]="G";}
								//System.out.println(a);
								String b[]=new String[3];
								b[1]=a[0];
								b[2]=a[1];
								for(int i4=0;i4<4;i4++)
								{
									if(i4==0){b[0]="A";}
									if(i4==1){b[0]="U";}
									if(i4==2){b[0]="C";}
									if(i4==3){b[0]="G";}
									String codon1=a[0]+a[1]+a[2];
									String codon2=b[0]+b[1]+b[2];
									String AA1=AlternativeTranslate(codon1,code,aa);
									String AA2=AlternativeTranslate(codon2,code,aa);					
									//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
									int IndexOfAA1=MatrixIndex(AA1,s);
									int IndexOfAA2=MatrixIndex(AA2,s);
									//System.out.print(",Index: ");
									//System.out.print(IndexOfAA1);
									//System.out.print(", ");
									//System.out.print(IndexOfAA2);
									double Score=ScoringMatrix[IndexOfAA1][IndexOfAA2];
									CodeScores[NumCodes-1]+=Score;
									//System.out.println(Score);
								}
								String c[]=new String[3];
								c[0]=a[1];
								c[1]=a[2];
								for(int i5=0;i5<4;i5++)
								{
									if(i5==0){c[2]="A";}
									if(i5==1){c[2]="U";}
									if(i5==2){c[2]="C";}
									if(i5==3){c[2]="G";}
									String codon1=a[0]+a[1]+a[2];
									String codon2=c[0]+c[1]+c[2];
									String AA1=AlternativeTranslate(codon1,code,aa);
									String AA2=AlternativeTranslate(codon2,code,aa);					
									//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
									int IndexOfAA1=MatrixIndex(AA1,s);
									int IndexOfAA2=MatrixIndex(AA2,s);
									//System.out.print(",Index: ");
									//System.out.print(IndexOfAA1);
									//System.out.print(", ");
									//System.out.print(IndexOfAA2);
									double Score1=ScoringMatrix[IndexOfAA1][IndexOfAA2];
									CodeScores[NumCodes-1]+=Score1;
								}
										
						}
						}
					}
				//	System.out.print(" : FSS score:"+CodeScores[NumCodes-1]);
				}
			}
		}

		System.out.println("\n\nTotal Number of Code: "+String.valueOf(NumCodes)+"\r\n");

		for(int g=0;g<NumCodes;g++){
			System.out.print(g+1);
			System.out.print(": ");
			System.out.println(CodeScores[g]);
		}
		System.out.println(" ");

		double ScoreNaturalCode=CodeScores[0];
		for(int e=0;e<NumCodes;e++)
		{
			for(int f=e+1;f<NumCodes;f++)
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

	for(int g=0;g<NumCodes;g++)
		{
			if(CodeScores[g]==ScoreNaturalCode)
			{   
				System.out.print("Score of the natural genetic code: ");
				System.out.println(ScoreNaturalCode);
				System.out.print("Rank of the natural genetic code: ");
				System.out.print(g+1);
				System.out.print("/");
				System.out.println(NumCodes);
				System.out.print(" compatible alternative genetic codes.");
				break;
			}
		}


		System.out.println("All FSS Scores: ");
		
	/*System.out.println("Sorted FSS Scores: ");
	for(int g=0;g<NumCodes;g++)
	{
		System.out.print(g+1);
		System.out.print(": ");
		System.out.println(CodeScores[g]);
	}
*/	
	
}catch(Exception e)
{
		System.out.println("\nWrong: Usage: AlternativeCodes [1/2/3]"); 
		System.out.println("\n The parameter is the scoring matrix: 1=GON250; 2=BLOSSUM62; 3=PAM250"); 
		System.out.println();
		System.out.println(e);
		e.printStackTrace();
}
		

}
	
	public static String AlternativeTranslate(String codon, String code[],String aa[])
	{
		String amino="0";
		if(codon.length() ==3)
		{
			String codon1=codon.toUpperCase();
			
			for(int i=0;i<64;i++)
			{
				if(codon1.equals(code[i]))
				{
					amino=aa[i];break;
				}
			}
		}
		return amino;
	 }
	
	public static int MatrixIndex(String a,int s)
	{   int c;
	if (s==1){ //GON250
		switch(a)
		{
			case "A":c=0;break;
			case "C":c=1;break;
			case "D":c=2;break;
			case "E":c=3;break;
			case "F":c=4;break;
			case "G":c=5;break;
			case "H":c=6;break;
			case "I":c=7;break;
			case "K":c=8;break;
			case "L":c=9;break;
			case "M":c=10;break;
			case "N":c=11;break;
			case "P":c=12;break;
			case "Q":c=13;break;
			case "R":c=14;break;
			case "S":c=15;break;
			case "T":c=16;break;
			case "V":c=17;break;
			case "W":c=18;break;
			case "Y":c=19;break;
			default:c=20;
		}		
		
	} else {
		switch(a)
			{
				case "C":c=0;break;
				case "S":c=1;break;
				case "T":c=2;break;
				case "P":c=3;break;
				case "A":c=4;break;
				case "G":c=5;break;
				case "N":c=6;break;
				case "D":c=7;break;
				case "E":c=8;break;
				case "Q":c=9;break;
				case "H":c=10;break;
				case "R":c=11;break;
				case "K":c=12;break;
				case "M":c=13;break;
				case "I":c=14;break;
				case "L":c=15;break;
				case "V":c=16;break;
				case "F":c=17;break;
				case "Y":c=18;break;
				case "W":c=19;break;
				default:c=20;
			}
		}
		return c; 	
	}

}


