/*
==============================================================
AAindexRandomCodesFrameshiftCorrelation123.java version 2.1.001 
Produce random genetic codes and compute the mean squre difference of their AA indices upon frameshifting, and the fRank of the natural genetic code. The AA indices is in file ./Matrix/AAindex1.txt
=============================================================
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class AAindexRandomCodesFrameshiftCorrelation123{

public static void main(String[] args) throws Exception
{
	System.out.println("\n Produce random genetic codes, calculate the mean square difference (MSm and MSf) for each AA indices upon mismatching and frameshifting, the p-value of the MS of the natural genetic code (Pm and Pf), and the correlation of their MSm and MSf. \n The AA indices is in file ./Matrix/AAindex1.txt"); 

	if (args.length<6)
	{
		System.out.println("\n Wrong parameter!"); 
		System.out.println("\n Usage: java -cp ./ AAindexRandomCodesMismatch <nCodes> <Repeats> <Start> <End> <walkSteps> <Output>"); //
		System.out.println("\n <nCodes> is the number of random codes: (1~10,000,000);"); 
		System.out.println("\n <Repeats> is the number of replications: (1~1,000)"); 
		System.out.println("\n <Start> is the start number of AA index: (1~655);"); 
		System.out.println("\n <End> is the ending number of AA index: (1~655);"); 
		System.out.println("\n <walkSteps> is walking steps for each random codes to prevent obtaining very similar codes (1~1000);"); 
		System.out.println("\n Output: whether or not to output the scores for each genetic code:  "); 
		System.out.println("\t\t\tOutput=Y-Output FSS scores; \n\t\t\tOutput=nCodes-not to output FSS scores. "); 
		System.exit(0); 
	}
	
	int nCodes = Integer.parseInt(args[0]);
	int Repeats = Integer.parseInt(args[1]);
	int Start = Integer.parseInt(args[2]);
	int End = Integer.parseInt(args[3]);
	int walkSteps=Integer.parseInt(args[4]);
	String Output=args[5];

	System.out.println("\n Parameters accepted: \n "); 
	System.out.println("\n The number of random codes (1~10,000,000): " + nCodes); 
	System.out.println("\n The number of replications:(1~1,000)" + Repeats); 
	System.out.println("\n The start number of AA index: " + Start); 
	System.out.println("\n The ending number of AA index: " + End); 
	System.out.println("\n Walk steps for each random codes:" + walkSteps ); 
	System.out.println("\n Output the scores for each genetic code: " + Output); 
		
	Translation Trobj=new Translation();
	AAindex AAidxObj=new AAindex();
	
	//Reading the AA indices in file ./Matrix/AAindex1.txt 
	
	String [] iNum = new String [1000];	//Accession number of AA indices
	String [] iName = new String [1000];//Description of AA indices
	String [] iAAs = new String [1000]; //The titles of the AA indices
	double [][] iMat = new double[1000][22];  //The values of the AA indices
	
	int nSM = AAindex.readAAidx(iNum, iName, iAAs, iMat);
	
	System.out.println("number of AA indices "+nSM);
	
	if (nCodes<1||nCodes>10000000||Repeats<1||Repeats>1000||walkSteps<1||walkSteps>1000||Start<1||Start>nSM||End<1||End>nSM||Start>End)
	{
		System.out.println("\n Wrong parameter!"); 
		System.out.println("\n Usage: java -cp ./ AAindexRandomCodesMismatch <nCodes> <Output>"); //<Repeats>
		System.out.println("\n nCodes is the number of random codes: (1~10,000,000);"); 
		System.out.println("\n Repeats is the number of replications: (1~1,000)"); 
		System.out.println("\n Start is the start number of AA index: (1~"+nSM+");"); 
		System.out.println("\n End is the ending number of AA index: ("+Start+"~"+nSM+");"); 
		System.out.println("\n <walkSteps> is walking steps for each random codes to prevent obtaining very similar codes (1~1000);"); 
		System.out.println("\n Output: whether or not to output the scores for each genetic code:  "); 
		System.out.println("\t\t\tOutput=Y-Output FSS scores; \n\t\t\tOutput=nCodes-not to output FSS scores. "); 
		System.exit(0); 
	}
	
	//Preparing the standard genetic code
	System.out.print("Preparing the standard genetic code: \n");
	
	String bases="AUCG";
	String [] codons=new String[65];
	String [] AA=new String[65];
	
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
	
	System.out.println("\n\nTotal number of Code: "+String.valueOf(nCodes)+"\n");
	System.out.print("Repeat\tNo\tR\tR1\tR2\tR3\tfRank\tPf\tMSf_SGC\tMeanMSf\tSdMSf\tMaxMSf\tMinMSf\t");
	System.out.print("mRank\tPm\tMSm_SGC\tMeanMSm\tSdMSm\tMaxMSm\tMinMSm\tAccession\tDescription\n");
	
	
	//System.out.print("\nCode: "+String.valueOf(NumCodes)+": ");

	for (int r=1; r<=Repeats; r++)
	{
	for (int s=Start; s<=End; s++)
	{
		//System.out.println("\n Replication: " + r); 
		
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
		
		for(int g=1;g<=nCodes;g++)
		{			
			//randomly exchange AA assignment to the 64 codons but keep the degenerative codons synonymous
			
			int x=1;
			int y=1;
			
			//Walking steps for each random codes to prevent obtaining similar codes for each step
			
			for(int wSteps=1; wSteps<=walkSteps; wSteps++)
			{			
				while (true)
				{
					x=(int)(Math.random()*20);
					y=(int)(Math.random()*20);
					if (y!=x) break;
				}

				String AAx=iAAs[s].substring(x,x+1);
				String AAy=iAAs[s].substring(y,y+1);
				
				for(int a=1;a<=64;a++)
				{
					if (AA[a].equals(AAx)) 
					{
						//if (g<2) System.out.println(codons[a]+":"+AA[a]+"<==>"+AAy);
						AA[a]=AAy; 
					}
					else if (AA[a].equals(AAy)) 
					{
						//if (g<2) System.out.println(codons[a]+":"+AA[a]+"<==>"+AAx);
						AA[a]=AAx;
					}
				}
			}
			
			// frameshift codons
			
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
						
						
						//Mismatching at the first base position
						for(int i4=0;i4<4;i4++)
						{
							String b1=bases.substring(i4,i4+1);
							codon2= b1+a2+a3;
							
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							if (g==1)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons, AA);
								AA2=AlternativeTranslate(codon2,codons, AA);	
							}
							int idxAA1=iAAs[s].indexOf(AA1);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m1++;
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
							double MSm1=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[1][g]+=MSm1*MSm1;
							//System.out.println(MSm1);
						}
						
						//Mismatching at the second base position
						for(int i5=0;i5<4;i5++)
						{
							String b2 =bases.substring(i5,i5+1);						
							codon2=a1+b2+a3;
							
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							if (g==1)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons, AA);
								AA2=AlternativeTranslate(codon2,codons, AA);	
							}
							int idxAA1=iAAs[s].indexOf(AA1);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m2++;
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
							double MSm2=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[2][g]+=MSm2*MSm2;
							//System.out.println(MSm1);
						}

						//Mismatching at the third base position
						
						for(int i6=0;i6<4;i6++)
						{
							String b3 =bases.substring(i6,i6+1);						
							codon2=a1+a2+b3;
							
							// ignore full matchs							
							if (codon2.equals(codon1)) continue; 
							
							if (g==1)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons, AA);
								AA2=AlternativeTranslate(codon2,codons, AA);	
							}
							int idxAA1=iAAs[s].indexOf(AA1);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore Mismatchs from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							m3++;
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
							double MSm3=iMat[s][idxAA1]-iMat[s][idxAA2];
							MSm[3][g]+=MSm3*MSm3;
							
							//System.out.println(MSm1);
						}

						//forward frameshifting
						for(int i4=0;i4<4;i4++)
						{
							String b0=bases.substring(i4,i4+1);
							
							codon2= b0+a1+a2;
							
							if (g==1)
							{
								AA1=Translation.translate(codon1);
								AA2=Translation.translate(codon2);	
							}
							else
							{
								AA1=AlternativeTranslate(codon1,codons,AA);
								AA2=AlternativeTranslate(codon2,codons,AA);	
							}
							
							int idxAA1=iAAs[s].indexOf(AA1);
							int idxAA2=iAAs[s].indexOf(AA2);
							
							// ignore frameshifts from or to nonsense codons
							if (idxAA1<0||idxAA2<0||idxAA1>=20||idxAA2>=20) continue; 
							
							f++;
							
							//System.out.print(codon1+" "+AA1+"<=>"+codon2+" "+AA2);
							//System.out.print(",Index: "+idxAA1+", "+idxAA2+"\n");
							
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
			//System.out.print(g+1+":");
			//System.out.println(MSf[g]);
		}
								
		m=m1+m2+m3;
		
		for(int g=1;g<=nCodes;g++)
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
		
		for(int g=1;g<=nCodes;g++)
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
		
		for(int g=1;g<=nCodes;g++)
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
		
		for(int g=0;g<=nCodes;g++)
		{
			sdMSf+=(MSf[g]-fMean)*(MSf[g]-fMean);
			sdMSm+=(MSm[0][g]-mMean)*(MSm[0][g]-mMean);
		}
		sdMSf=Math.sqrt((double) sdMSf / (double) nCodes);		
		sdMSm=Math.sqrt((double) sdMSm / (double) nCodes);	
		
		double R = getCorrelation(MSm[0], MSf);
		double R1 = getCorrelation(MSm[0], MSf1);
		double R2 = getCorrelation(MSm[0], MSf2);
		double R3 = getCorrelation(MSm[0], MSf3);
		
		//System.out.println("\n\nTotal number of Code: "+String.valueOf(nCodes)+"\r\n");
		//System.out.print("fRank of the natural genetic code: ");
		
		System.out.print(r);
		System.out.print("\t");
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
		
		if (Output.toUpperCase().indexOf("Y")>=0&&Repeats==1)
		{
			String MSfile="./RGCs-"+String.valueOf(s)+"-"+iNum[s]+"-MSm-MSf.txt";					
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
				
			for(int g=1;g<=nCodes;g++)
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
	
	}
}//End of main

public static String AlternativeTranslate(String codon1, String codons[],String AA[])
{
	String AminoAcid="*";
	if(codon1.length() ==3)
	{
		codon1=codon1.toUpperCase();
		
		//System.out.println(codon1);
		for(int c=1;c<=64;c++)
		{

			//System.out.println(c+": "+ codons[c]+"-->"+AA[c]);
			
			if(codon1.equals(codons[c]))
			{
				AminoAcid=AA[c];
				break;
			}
		}
	}
	return AminoAcid;
} //End of AlternativeTranslate

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

} //End of Class 

/*
==============================================================
This software is released under GNU/GPL license
Copyright (c) 2015, Ocean University of China

Written by:                   
Xiaolong Wang* @ Ocean University of China 

email: xiaolong@ouc.edu.cn
================================================================
*/

