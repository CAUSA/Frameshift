public class ControlPAM {
	public static char Tr(String codon,char b[])
	 {
		char amino='0';
		if(codon.length() ==3){
			
			String codon1=codon.toUpperCase();
			

			if((codon1.equals("GCT")) || (codon1.equals("GCU"))){
				amino=b[0];
				codon1="GCU";
			}
			else if ((codon1.equals("GCC"))||(codon1.equals("GCA"))||(codon1.equals("GCG"))){
				amino=b[0];
			}
			else if((codon1.equals("CGU")) || (codon1.equals("CGT"))){
				codon1="CGU";
				amino=b[1];
			}
			else if ((codon1.equals("CGC"))||(codon1.equals("CGA"))||(codon1.equals("CGG"))||(codon1.equals("AGA"))||(codon1.equals("AGG"))){
				amino=b[1];
			}
			else if((codon1.equals("AAU")) || (codon1.equals("AAT"))){
				codon1="AAU";
				amino=b[2];
			}
			else if(codon1.equals("AAC")){
				amino=b[2];
			}
			else if((codon1.equals("GAU")) || (codon1.equals("GAT"))){
				codon1="GAU";
				amino=b[3];
			}
			else if((codon1.equals("GAC"))){
				amino=b[3];
			}
			else if((codon1.equals("UGC"))||(codon1.equals("TGC"))){
				codon1="UGC";
				amino=b[4];
			}
			else if((codon1.equals("UGU")) || (codon1.equals("TGT"))){
				codon1="UGU";
				amino=b[4];
			}
			else if((codon1.equals("CAA")) || (codon1.equals("CAG"))){
				amino=b[5];
			}
			else if((codon1.equals("GAA")) || (codon1.equals("GAG"))){
				amino=b[6];
			}
			else if((codon1.equals("GGU")) || (codon1.equals("GGT"))){
				codon1="GGU";
				amino=b[7];
			}
			else if((codon1.equals("GGC"))|| (codon1.equals("GGA"))|| (codon1.equals("GGG"))){
				amino=b[7];
			}
			else if((codon1.equals("CAU")) || (codon1.equals("CAT"))){
				codon1="CAU";
				amino=b[8];
			}
			else if((codon1.equals("CAC"))){
				amino=b[8];
			}
			else if((codon1.equals("AUU")) || (codon1.equals("ATT"))){
				codon1="AUU";
				amino=b[9];
			}
			else if((codon1.equals("AUC"))|| (codon1.equals("ATC"))){
				codon1="AUC";
				amino=b[9];
			}
			else if((codon1.equals("AUA"))|| (codon1.equals("ATA"))){
				codon1="AUA";
				amino=b[9];
			}
			else if((codon1.equals("AUG")) || (codon1.equals("ATG"))){
				codon1="AUG";
				amino=b[10];
			}
			else if((codon1.equals("UUG")) || (codon1.equals("TTG"))){
				codon1="UUG";
				amino=b[11];
			}
			else if((codon1.equals("UUA"))|| (codon1.equals("TTA"))){
				codon1="UUA";
				amino=b[11];
			}
			else if((codon1.equals("CUU"))|| (codon1.equals("CTT"))){
				codon1="CUU";
				amino=b[11];
			}
			else if((codon1.equals("CUC"))|| (codon1.equals("CTC"))){
				codon1="CUC";
				amino=b[11];
			}
			else if((codon1.equals("CUA"))|| (codon1.equals("CTA"))){
				codon1="CUA";
				amino=b[11];
			}
			else if((codon1.equals("CUG"))|| (codon1.equals("CTG"))){
				codon1="CUG";
				amino=b[11];
			}
			else if((codon1.equals("UUU")) || (codon1.equals("TTT"))){
				codon1="UUU";
				amino=b[12];
			}
			else if((codon1.equals("UUC")) || (codon1.equals("TTC"))){
				codon1="UUC";
				amino=b[12];
			}
			else if((codon1.equals("CCU")) || (codon1.equals("CCT"))){
				codon1="CCU";
				amino=b[13];
			}
			else if((codon1.equals("CCC")) || (codon1.equals("CCA"))|| (codon1.equals("CCG"))){
				amino=b[13];
			}
			else if((codon1.equals("UCU")) || (codon1.equals("TCT"))){
				codon1="UCU";
				amino=b[14];
			}
			else if((codon1.equals("UCC")) || (codon1.equals("TCC"))){
				codon1="UCC";
				amino=b[14];
			}
			else if((codon1.equals("UCA"))|| (codon1.equals("TCA"))){
				codon1="UCA";
				amino=b[14];
			}
			else if((codon1.equals("UCG"))|| (codon1.equals("TCG"))){
				codon1="UCG";
				amino=b[14];
			}
			else if((codon1.equals("AGU"))|| (codon1.equals("AGT"))){
				codon1="AGU";
				amino=b[14];
			}
			else if((codon1.equals("AGC"))){
				amino=b[14];
			}
			else if((codon1.equals("ACU")) || (codon1.equals("ACT"))){
				codon1="ACU";
				amino=b[15];
			}
			else if((codon1.equals("ACC")) || (codon1.equals("ACA"))|| (codon1.equals("ACG"))){
				amino=b[15];
			}

			else if((codon1.equals("UGG")) || (codon1.equals("TGG"))){
				codon1="UGG";
				amino=b[16];
			}
			else if((codon1.equals("UAU")) || (codon1.equals("TAT")) ){
				codon1="UAU";
				amino=b[17];
			}
			else if((codon1.equals("UAC")) ||(codon1.equals("TAC"))){
				codon1="UAC";
				amino=b[17];
			}
			else if((codon1.equals("GUU")) || (codon1.equals("GTT"))){
				codon1="GUU";
				amino=b[18];
			}
			else if((codon1.equals("GUC")) ||(codon1.equals("GTC"))){
				codon1="GUC";
				amino=b[18];
			}
			else if((codon1.equals("GUA"))||(codon1.equals("GTA"))){
				codon1="GUA";
				amino=b[18];
			}
			else if((codon1.equals("GUG"))||(codon1.equals("GTG"))){
				codon1="GUG";
				amino=b[18];
			}
			else if((codon1.equals("UAA")) || (codon1.equals("TAA"))){
				codon1="UAA";
				amino='*';
			}
			else if((codon1.equals("UGA")) ||(codon1.equals("TGA"))){
				codon1="UGA";
				amino='*'; 
			}
			else if((codon1.equals("UAG"))||(codon1.equals("TAG"))){
				codon1="UAG";
				amino='*';
			}
			else if((codon1.equals("AAA"))||(codon1.equals("AAG"))){
				amino=b[19];
			}
		}
		

	return amino;
	 }
	
	public static int Sc(char a)
	{   int c;
		switch(a)
		{
		case 'C':c=0;break;
		case 'S':c=1;break;
		case 'T':c=2;break;
		case 'P':c=3;break;
		case 'A':c=4;break;
		case 'G':c=5;break;
		case 'N':c=6;break;
		case 'D':c=7;break;
		case 'E':c=8;break;
		case 'Q':c=9;break;
		case 'H':c=10;break;
		case 'R':c=11;break;
		case 'K':c=12;break;
		case 'M':c=13;break;
		case 'I':c=14;break;
		case 'L':c=15;break;
		case 'V':c=16;break;
		case 'F':c=17;break;
		case 'Y':c=18;break;
		case 'W':c=19;break;
		default:c=a;
		}
		return c; 	
	}

	public static void main(String[] args) 
	{
		int TotalNum=1000000;
		int socall[]=new int[TotalNum+1]; 
		for(int i=0;i<TotalNum+1;i++)
		{
		long y=2432901008L;
		int BLOSUM62[][]={
							{12,0,-2,-3,-2,-3,-4,-5,-5,-5,-3,-4,-5,-5,-2,-6,-2,-4,0,-8},
							{0,2,1,1,1,1,1,0,0,-1,-1,0,0,-2,-1,-3,-1,-3,-3,-2},
							{-2,1,3,0,1,0,0,0,0,-1,-1,-1,0,-1,0,-2,0,-3,-3,-5},
							{-3,1,0,6,1,-1,-1,-1,-1,0,0,0,-1,-2,-2,-3,-1,-5,-5,-6},
							{-2,1,1,1,2,1,0,0,0,0,-1,-2,-1,-1,-1,-2,0,-5,-3,-6},
							{-3,1,0,-1,1,5,0,1,0,-1,-2,-3,-2,-3,-3,-4,-1,-5,-5,-7},
							{-4,1,0,-1,0,0,2,2,1,1,2,0,1,-2,-2,-3,-2,-4,-2,-4},
							{-5,0,0,-1,0,1,2,4,3,2,1,-1,0,-3,-2,-4,-2,-6,-4,-7},
							{-5,0,0,-1,0,0,1,3,4,2,1,-1,0,-2,-2,-3,-2,-5,-4,-7},
							{-5,-1,-1,0,0,-1,1,2,2,4,3,1,1,-1,-2,-2,-2,-5,-4,-5},
							{-3,-1,-1,0,-1,-2,2,1,1,3,6,2,0,-2,-2,-2,-2,-2,0,-3},
							{-4,0,-1,0,-2,-3,0,-1,-1,1,2,6,3,0,-2,-3,-2,-4,-4,2},
							{-5,0,0,-1,-1,-2,1,0,0,1,0,3,5,0,-2,-3,-2,-5,-4,-3},
							{-5,-2,-1,-2,-1,-3,-2,-3,-2,-1,-2,0,0,6,2,4,2,0,-2,-4},
							{-2,-1,0,-2,-1,-3,-2,-2,-2,-2,-2,-2,-2,2,5,2,4,1,-1,-5},
							{-6,-3,-2,-3,-2,-4,-3,-4,-3,-2,-2,-3,-3,4,2,6,2,2,-1,-2},
							{-2,-1,0,-1,0,-1,-2,-2,-2,-2,-2,-2,-2,2,4,2,4,-1,-2,-6},
							{-4,-3,-3,-5,-5,-5,-4,-6,-5,-5,-2,-4,-5,0,1,2,-1,9,7,0},
							{0,-3,-3,-5,-3,-5,-2,-4,-4,-4,0,-4,-4,-2,-1,-1,-2,7,10,0},
							{-8,-2,-5,-6,-6,-7,-4,-7,-7,-5,-3,2,-3,-4,-5,-2,-6,0,0,17}
						};
		char[] C={'C','S','T','P','A','G','N','D','E','Q','H','R','K','M','I','L','V','F','Y','W'};
		
			long x=(long)(Math.random()*y);
			x=x+y*i;
				//System.out.println(x);
				char[] code;code=new char[20];
				for(int a=19;a>-1;a--)
				{int b=a;
				 long js=1;
					for(;b>0;b--)
					{
						js=js*b;
					}
					int num=(int) (x/js);
					code[a]=C[num];
					 int p =num;
				        for(int d=p+1;d<20;d++) {
				            C[d-1]=C[d];
				        }
				 
				 x=x%js;
				 //System.out.println(code[a]);
				}
				if(i==TotalNum)
				{
				code[0]='A';
				code[1]='R';
				code[2]='N';
				code[3]='D';
				code[4]='C';
				code[5]='Q';
				code[6]='E';
				code[7]='G';
				code[8]='H';
				code[9]='I';
				code[10]='M';
				code[11]='L';
				code[12]='F';
				code[13]='P';
				code[14]='S';
				code[15]='T';
				code[16]='W';
				code[17]='Y';
				code[18]='V';
				code[19]='K';
				}
				char a[]=new char[3];
			for(int i1=0;i1<4;i1++){
				if(i1==0){a[0]='A';}
				if(i1==1){a[0]='T';}
				if(i1==2){a[0]='C';}
				if(i1==3){a[0]='G';}
				for(int i2=0;i2<4;i2++){
					if(i2==0){a[1]='A';}
					if(i2==1){a[1]='T';}
					if(i2==2){a[1]='C';}
					if(i2==3){a[1]='G';}
					for(int i3=0;i3<4;i3++){
						if(i3==0){a[2]='A';}
						if(i3==1){a[2]='T';}
						if(i3==2){a[2]='C';}
						if(i3==3){a[2]='G';}
						//System.out.println(a);
						char b[]=new char[3];
						b[1]=a[0];
						b[2]=a[1];
						for(int i4=0;i4<4;i4++)
						{
							if(i4==0){b[0]='A';}
							if(i4==1){b[0]='T';}
							if(i4==2){b[0]='C';}
							if(i4==3){b[0]='G';}
							String test1=String.valueOf(a);
							String test2=String.valueOf(b);
							//System.out.print(test1+' '+Tr(test1,code)+'|');							
							//System.out.println(test2+' '+Tr(test2,code));
							int soc;
							if(Sc(Tr(test1,code))=='*'||Sc(Tr(test2,code))=='*')
									{
								soc=0;
									}
							else
							{
								soc=BLOSUM62[Sc(Tr(test1,code))][Sc(Tr(test2,code))];
							}
							socall[i]=socall[i]+soc;
							//System.out.println(soc);
						}
						char c[]=new char[3];
						c[0]=a[1];
						c[1]=a[2];
						for(int i5=0;i5<4;i5++)
						{
							if(i5==0){c[2]='A';}
							if(i5==1){c[2]='T';}
							if(i5==2){c[2]='C';}
							if(i5==3){c[2]='G';}
							String test1=String.valueOf(a);
							String test2=String.valueOf(c);
							//System.out.print(test1+' '+Tr(test1,code)+'|');							
							//System.out.println(test2+' '+Tr(test2,code));
							int soc1;
							if(Sc(Tr(test1,code))=='*'||Sc(Tr(test2,code))=='*')
									{
								soc1=0;
									}
							else
							{
								soc1=BLOSUM62[Sc(Tr(test1,code))][Sc(Tr(test2,code))];
							}
							//System.out.println(soc1);
							socall[i]=socall[i]+soc1;
						}
								
				}
				}
			}
			System.out.print(i+1+":");
			System.out.println(socall[i]);
				
				
		
				
		}
		int tag=socall[TotalNum];
		System.out.println("end");
		for(int e=0;e<TotalNum;e++)
			for(int f=e+1;f<TotalNum+1;f++)
			{
				int temp;
				if(socall[e]<socall[f])
				{
					temp=socall[e];
					socall[e]=socall[f];
					socall[f]=temp;
					
				}
			}
		for(int g=0;g<TotalNum+1;g++)
			if(socall[g]==tag)
			{   
				System.out.print("本来编码方式的排列位数：");
				System.out.print(g+1);
				System.out.print("/");
				System.out.print(TotalNum);
				break;
			}
	}
}


