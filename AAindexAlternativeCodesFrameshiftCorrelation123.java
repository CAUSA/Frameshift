/*
==============================================================
AAindexAlternativeCodesFrameshiftCorrelation123.java version 3.0.001 
Produce alternative genetic codes, calculate the mean square difference of their AA indices, and the rank of the natural genetic code.
=============================================================
*/

import java.io.*;

public class AAindexAlternativeCodesFrameshiftCorrelation123{

public static void main(String[] args) throws Exception 
{
	
	System.out.println("\n Produce alternative genetic codes, calculate the mean square difference of their AA indices upon frameshifting, and the rank of the natural genetic code. The AA indices is in file ./Matrix/AAindex1.txt"); 
	System.out.println("\n Usage: java -cp ./ AAindexAlternativeCodesFrameshiftCorrelation123"); 

	Translation Trobj=new Translation();
	AAindex AAidxObj=new AAindex();
		
	//Reading the AA indices in file ./Matrix/AAindex1.txt 
	
	String []  iNum  = new String [1000];	//Accession Number of AA indices
	String []  iName = new String [1000];//Description of AA indices
	String []  iAAs  = new String [1000]; //The titles of the AA indices
	double [][]iMat  = new double [1000][22];  //The values of the AA indices
	
	int nSM = AAindex.readAAidx(iNum, iName, iAAs, iMat);
			
	//Produce all possible permutations of AUCG

	int p = 0;
	String bases = "AUCG";
	String [][] P = new String[25][5];
	String []   b = new String[4] ; 
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
	
	//System.out.println("\n Number P: "+String.valueOf(p));

	String [][] CODONs = new String [20000][65];
	String [] AAs=new String [65];

	int nCodes = 0; //Number of genetic codes
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
			
				nCodes++;
				i = 0;
				//System.out.println("\nCode: "+String.valueOf(nCodes)+": ");
				
				//Produce alternative genetic codes
			
				for(int i1=1;i1<=4;i1++)
				{
					for(int i2=1;i2<=4;i2++)
					{
						for(int i3=1;i3<=4;i3++)
						{
							i++;					  
							CODONs[nCodes][i] = B1[i1]+B2[i2]+B3[i3];
							//	System.out.print(CODONs[nCodes][i]+" ");
							
							AAs[i]=Translation.translate(CODONs[1][i]);
							//get the  Standard Translation at the first code, after that, change CODONs but keep the AAs unchanged.   									
							//System.out.println(String.valueOf(i)+": "+CODONs[nCodes][i]+": "+AAs[i]+" ");
						}
					}
				}
			}
		}
	}
	
	System.out.println("\n Calculate the mean squre difference of their AA indices upon frameshifting, and the rank of the natural genetic code."); 
	System.out.println("\n\nTotal Number of Code: "+String.valueOf(nCodes)+"\r\n");
	System.out.print("No\tR\tR1\tR2\tR3\tfRank\tPf\tMSf_SGC\tMeanMSf\tSdMSf\tMaxMSf\tMinMSf\t");
	System.out.print("mRank\tPm\tMSm_SGC\tMeanMSm\tSdMSm\tMaxMSm\tMinMSm\tAccession\tDescription\n");
		
	for (int s=1; s<=nSM; s++)
	{
		//Frameshifting the codons and calculate AA index
				
		//System.out.println("Frameshifting the codons and calculate AA index: ");
		
		//System.out.println("Total genetic codes: "+String.valueOf(nCodes)+": ");
		
		int f=0;  // Number of all shifted codons
		int	f1=0; // Number of 0-1 mismatches shifted codons
		int	f2=0; // Number of 2 mismatches shifted codons
		int	f3=0; // Number of 3 mismatches shifted codons
		int m=0;  // Number of mismatchemismatched codons
		int m1=0; // Number of mismatched at the 1st base position
		int m2=0; // Number of mismatched at the 2nd base position
		int m3=0; // Number of mismatched at the 3rd base position
		double MSm[][]=new double[4][nCodes+1]; // MS for mismatched codons
		double MSf[] = new double[nCodes+1]; 	// MS for all shifted codons
		double MSf1[]= new double[nCodes+1]; 	// MS for 0-1 mismatches shifted codons
		double MSf2[]= new double[nCodes+1]; 	// MS for 2 mismatches shifted codons
		double MSf3[]= new double[nCodes+1]; 	// MS for 3 mismatches shifted codons
		
		for(int g = 1; g <= nCodes; g++)
		{
			//System.out.print(" Processing genetic codes: "+String.valueOf(g)+": ");
			
			f=0;
			f1=0;
			f2=0;
			f3=0;
			m =0;
			m1=0; 
			m2=0; 
			m3=0; 
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
						AA1=AlternativeTranslate(codon1,CODONs[g],AAs);						
						int idxAA1=iAAs[s].indexOf(AA1);
						
						//System.out.println(codon1+" "+AA1+":");
						
						//Mismatching at the first base position
						for(int i4=0;i4<4;i4++)
						{
							String b1=bases.substring(i4,i4+1);
							codon2= b1+a2+a3;
							
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							
							int idxAA2=iAAs[s].indexOf(AA2);
														
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m1++;
						
							double MS1=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[1][g]+=MS1*MS1;
							
							//System.out.println(MSm);
						}
						
						//Mismatching at the second base position
						
						for(int i5=0;i5<4;i5++)
						{
							String b2 =bases.substring(i5,i5+1);						
							codon2=a1+b2+a3;
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m2++;
							
							double MS2=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[2][g]+=MS2*MS2;
							
							//System.out.println(MS1);
						}
						
						//Mismatching at the third base position
						
						for(int i6=0;i6<4;i6++)
						{
							String b3 =bases.substring(i6,i6+1);						
							codon2=a1+a2+b3;
							
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m3++;
							
							double MS3=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[3][g]+=MS3*MS3;
							
							//System.out.println(MS1);
						}
						
						//forward frameshifting
						
						//codon1 = a1+a2+a3;
						//AA1=AlternativeTranslate(codon1,CODONs[g],AAs);						
						//int idxAA1=iAAs[s].indexOf(AA1);
						
						for(int i4=0;i4<4;i4++)
						{
							String b0=bases.substring(i4,i4+1);
							codon2= b0+a1+a2;							
							AA2=AlternativeTranslate(codon2,CODONs[g],AAs);
							
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore frameshifts from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							f++;
														
							double MS1=iMat[s][idxAA1]-iMat[s][idxAA2];
							double MS2=MS1*MS1;
							
							MSf[g]+=MS2;
							
							int DifferentBases=0;
							
							if(!a1.equals(b0))DifferentBases++;
							if(!a2.equals(a1))DifferentBases++;
							if(!a3.equals(a2))DifferentBases++;
							
							if (DifferentBases<=1) 
							{
								f1++;
								MSf1[g]+=MS2;
							}
							else if (DifferentBases==2) 
							{
								f2++;
								MSf2[g]+=MS2;
							}
							else 
							{
								f3++;
								MSf3[g]+=MS2;
							}
							//if (g==1&&s==1) System.out.println(codon1+"\t->\t"+codon2+"\t" + DifferentBases);
							//System.out.println(MSf[g]);
						}
					}
				}
			}
			
			//System.out.print(": total AA index score:"+MSf[g]+“\n");
		}
		
		m=m1+m2+m3;
		
		for(int g = 1; g <= nCodes; g++)
		{
			MSm[0][g] = (double) (MSm[1][g]+MSm[2][g]+MSm[3][g])/(double) m;
			
			MSm[1][g] = (double) MSm[1][g] / (double) m1;
			MSm[2][g] = (double) MSm[2][g] / (double) m2;
			MSm[3][g] = (double) MSm[3][g] / (double) m3;
			
			MSf[g] = (double) MSf[g] / (double) f;
			MSf1[g] = (double) MSf1[g] / (double) f1;
			MSf2[g] = (double) MSf2[g] / (double) f2;
			MSf3[g] = (double) MSf3[g] / (double) f3;
		}

		// determine the fRank and mRank of natural genetic code (The first code)
		
		int fRank=1;
		int mRank=1;
		double MSmNGC=MSm[0][1]; 
		double MSfNGC=MSf[1];    
		
		for(int g = 1; g <= nCodes; g++)
		{
			if(MSfNGC - MSf[g]>0.000001)
			{   
				fRank++;
			}
			if(MSmNGC - MSm[0][g]>0.000001)
			{   
				mRank++;
			}
		}
		
		double  Pf = (double) fRank / (double) nCodes;
		double  Pm = (double) mRank / (double) nCodes;
		
		// Calculate fSum, fMean, fMax, fMin, and the standard deviation of MSf
		
		double fMean=0.0;
		double fSum=0.0;
		double fMax=0.0;
		double fMin=MSf[1];
		double mMean=0.0;
		double mSum=0.0;
		double mMax=0.0;
		double mMin=MSm[0][1];
		
		for(int g = 1; g <= nCodes; g++)
		{
			if(MSm[0][g]>mMax) mMax=MSm[0][g];
			if(MSm[0][g]<mMin) mMin=MSm[0][g];
			
			if(MSf[g]>fMax) fMax=MSf[g];
			if(MSf[g]<fMin) fMin=MSf[g];
			
			mSum+=MSm[0][g];
			fSum+=MSf[g];
		}
		
		fMean= (double) fSum / (double) nCodes;		
		mMean= (double) mSum / (double) nCodes;		
		
		double sdMSf=0.0;
		double sdMSm=0.0;
		
		for(int g = 1; g <= nCodes; g++)
		{
			sdMSf+=(MSf[g]-fMean)*(MSf[g]-fMean);
			sdMSm+=(MSm[0][g]-mMean)*(MSm[0][g]-mMean);
		}
		sdMSf=Math.sqrt((double) sdMSf / (double) nCodes);		
		sdMSm=Math.sqrt((double) sdMSm / (double) nCodes);	
		
		double R  = getCorrelation(MSm[0], MSf);
		double R1 = getCorrelation(MSm[0], MSf1);
		double R2 = getCorrelation(MSm[0], MSf2);
		double R3 = getCorrelation(MSm[0], MSf3);
		
		//System.out.println("\n\nTotal number of Code: "+String.valueOf(nCodes)+"\r\n");
		//System.out.print("fRank of the natural genetic code: ");
		
		System.out.print(s);
		System.out.print("\t");
		System.out.print(String.valueOf(R));
		System.out.print("\t");
		System.out.print(String.valueOf(R1));
		System.out.print("\t");
		System.out.print(String.valueOf(R2));
		System.out.print("\t");
		System.out.print(String.valueOf(R3));
		System.out.print("\t");
		System.out.print(fRank);
		System.out.print("\t");
		System.out.print(String.valueOf(Pf));
		System.out.print("\t");
		System.out.print(MSfNGC);
		System.out.print("\t");
		System.out.print(fMean);
		System.out.print("\t");
		System.out.print(sdMSf);
		System.out.print("\t");
		System.out.print(fMax);
		System.out.print("\t");
		System.out.print(fMin);
		System.out.print("\t");
		System.out.print(mRank);
		System.out.print("\t");
		System.out.print(String.valueOf(Pm));
		System.out.print("\t");
		System.out.print(MSmNGC);
		System.out.print("\t");
		System.out.print(mMean);
		System.out.print("\t");
		System.out.print(sdMSm);
		System.out.print("\t");
		System.out.print(mMax);
		System.out.print("\t");
		System.out.print(mMin);
		System.out.print("\t");
		System.out.print(iNum[s]);
		System.out.print("\t");
		System.out.print(iName[s]);
		System.out.print("\n");	
		
		//Output the MSfs for each AA index for each code
		//If repeats>1, DO NOT output the MSfs for each AA index for each code!			
		//Because too many large-sized files,  the disk space may not be enough!	
	
		String MSfile="./AGCs-"+String.valueOf(s)+"-"+iNum[s]+"-MSm-MSf.txt";					
		BufferedWriter bwMSfile = new BufferedWriter(new FileWriter(MSfile));
		
		bwMSfile.write(String.valueOf(s)+"."+iNum[s]+": "+iName[s]+" MSm MSf\n");
		bwMSfile.write("Number of all mismatches shifted codons: f = "+f+"\n");
		bwMSfile.write("Number of 0-1 mismatches shifted codons: f1 = "+f1+"\n");
		bwMSfile.write("Number of   2 mismatches shifted codons: f2 = "+f2+"\n");
		bwMSfile.write("Number of   3 mismatches shifted codons: f3 = "+f3+"\n");
		bwMSfile.write("Number of mismatched sense codons at 1st base:  m1 = "+m1+"\n");
		bwMSfile.write("Number of mismatched sense codons at 2nd base:  m2 = "+m2+"\n");
		bwMSfile.write("Number of mismatched sense codons at 3rd base:  m3 = "+m3+"\n");
		bwMSfile.write("Number of mismatched sense codons at all bases: m  = "+m +"\n");
		bwMSfile.write("Code\tMSf\tMSf1\tMSf2\tMSf3\tMSm\tMS1\tMS2\tMS3\n");
			
		for(int g = 1; g <= nCodes; g++)
		{						
			bwMSfile.write(String.valueOf(g));
			bwMSfile.write("\t");
			bwMSfile.write(String.valueOf(MSf[g]));
			bwMSfile.write("\t");
			bwMSfile.write(String.valueOf(MSf1[g]));
			bwMSfile.write("\t");
			bwMSfile.write(String.valueOf(MSf2[g]));
			bwMSfile.write("\t");
			bwMSfile.write(String.valueOf(MSf3[g]));
			bwMSfile.write("\t");
			bwMSfile.write(String.valueOf(MSm[0][g]));
			bwMSfile.write("\t");            
			bwMSfile.write(String.valueOf(MSm[1][g]));
			bwMSfile.write("\t");            
			bwMSfile.write(String.valueOf(MSm[2][g]));
			bwMSfile.write("\t");            
			bwMSfile.write(String.valueOf(MSm[3][g]));
			bwMSfile.write("\n");
		}
		bwMSfile.close();	
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
	 
public static double getCorrelation(double[] x, double[] y) 
{
	double AvX;
	double AvY;
	double numerator;
	double denofMinator;
	double R;
	double fSumX = 0.0;
	for (int i = 0; i < x.length; i++) 
	{
		fSumX += x[i];
	}
	AvX = fSumX / x.length;
	
	double fSumY = 0.0;
	
	for (int i = 0; i < y.length; i++) 
	{
		fSumY += y[i];
	}
	AvY = fSumY / y.length;
	
	double fSumXY = 0.0;
	for (int i = 0; i < x.length; i++) 
	{
		fSumXY += (x[i] - AvX) * (y[i] - AvY);
	}
	numerator = fSumXY;
	
	double fSumXX = 0.0;
	for (int i = 0; i < x.length; i++) 
	{
		fSumXX += (x[i] - AvX) * (x[i] - AvX);
	}

	double fSumYY = 0.0;
	for (int i = 0; i < y.length; i++) 
	{
		fSumYY += (y[i] - AvY) * (y[i] - AvY);
	}
	denofMinator =  Math.sqrt(fSumXX) * Math.sqrt(fSumYY);
	
	R = numerator / denofMinator;
	
	return R;
} //End of getCorrelation

}

/*
==============================================================
This software is released under GNU/GPL license
Copyright (c) 2015-2022, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
================================================================
*/

