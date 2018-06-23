import java.io.*;

public class AlternativeCodesPAM{

	public static void main(String[] args) 
	{
	try{
 		//String[] C={"A","C","D","E","F","G","H","I","K","L","M","N","P","Q","R","S","U","V","W","Y"};
		int ScoringMatrix[][]={
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


		int CodeScores[]=new int[100000]; 
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
									aa[i]=StandardTranslate(code[i]);
									//get the  Standard Translations 
									//Also will be used for alternative translations
									System.out.println(String.valueOf(i)+": "+code[i]+": "+aa[i]+" ");
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
									//System.out.print(codon1+" "+Translate(codon1,code,aa)+"|");							
									//System.out.println(codon2+" "+Translate(codon2,code,aa));
									int Score;
									Score=ScoringMatrix[MatrixIndex(Translate(codon1,code,aa))][MatrixIndex(Translate(codon2,code,aa))];
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
									//System.out.print(codon1+" "+Translate(codon1,code,aa)+"|");							
									//System.out.println(codon2+" "+Translate(codon2,code,aa));
									int Score1;
									Score1=ScoringMatrix[MatrixIndex(Translate(codon1,code,aa))][MatrixIndex(Translate(codon2,code,aa))];
									//System.out.println(Score1);
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
		int ScoreNaturalCode=CodeScores[0];
		
	//print the scores  
	//	System.out.println("All FSS Scores: ");
		
	//	for(int g=0;g<NumCodes;g++){
	//		System.out.print(g+1);
	//		System.out.print(": ");
	//		System.out.println(CodeScores[g]);
	//	}
		
		System.out.println(" ");
		System.out.println("Sorted FSS Scores: ");
		
		for(int e=0;e<NumCodes;e++)
		{
			for(int f=e+1;f<NumCodes;f++)
			{
				int temp;
				if(CodeScores[e]<CodeScores[f])
				{
					temp=CodeScores[e];
					CodeScores[e]=CodeScores[f];
					CodeScores[f]=temp;
				}
			}
		}
		//print the rank 
		int Rank=0;		
		for(int g=0;g<NumCodes;g++){
			if(CodeScores[g]==ScoreNaturalCode)
			{   
				Rank=g+1;
				System.out.print("Score of the natural genetic code: ");
				System.out.println(ScoreNaturalCode);
				System.out.print("Rank of the natural genetic code: ");
				System.out.print(Rank);
				System.out.print("/");
				System.out.println(NumCodes);
				break;
			}
		}
		//print the sorted scores  

		for(int g=0;g<NumCodes;g++){
			System.out.print(g+1);
			System.out.print(": ");
			System.out.println(CodeScores[g]);
		}
		//print the rank once again
		System.out.print("Score of the natural genetic code: ");
		System.out.println(ScoreNaturalCode);
		System.out.print("Rank of the natural genetic code: ");
		System.out.print(Rank);
		System.out.print("/");
		System.out.println(NumCodes);
	}catch(Exception e){
		System.out.println("\nWrong running: AlternativeCodesGonnet"); 
		System.out.println(e);
		e.printStackTrace();
	}
		

}
	
		public static String Translate(String codon, String code[],String aa[])
	 {
		String amino="0";
		if(codon.length() ==3){
			
			String codon1=codon.toUpperCase();
			
			for(int i=0;i<64;i++){
				if(codon1.equals(code[i])){
					amino=aa[i];break;
				}
			}
		}
		

	return amino;
	 }
	
	public static int MatrixIndex(String a)
	{   int c;
		switch(a)
		{
		case "C":c=0;break;
		case "S":c=1;break;
		case "U":c=2;break;
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
		return c; 	
	}
	
	public static String StandardTranslate(String codon)
	{
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
					amino="U";
				}
				else if((codon1.equals("ACC")) || (codon1.equals("ACA"))|| (codon1.equals("ACG"))){
					amino="U";
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


