
public class ControlGon {
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
		case 'A':c=0;break;
		case 'C':c=1;break;
		case 'D':c=2;break;
		case 'E':c=3;break;
		case 'F':c=4;break;
		case 'G':c=5;break;
		case 'H':c=6;break;
		case 'I':c=7;break;
		case 'K':c=8;break;
		case 'L':c=9;break;
		case 'M':c=10;break;
		case 'N':c=11;break;
		case 'P':c=12;break;
		case 'Q':c=13;break;
		case 'R':c=14;break;
		case 'S':c=15;break;
		case 'T':c=16;break;
		case 'V':c=17;break;
		case 'W':c=18;break;
		case 'Y':c=19;break;
		default:c=a;
		}
		return c; 	
	}

	public static void main(String[] args) 
	{
		int TotalNum=1000000;
		int ScoreAll[]=new int[TotalNum+1]; 
		for(int i=0;i<TotalNum+1;i++)
		{
		long y=2432901008L;
		int ScoringMatrix[][]={
			{24,5,-3,0,-23,5,-8,-8,-4,-12,-7,-3,3,-2,-6,11,6,1,-36,-22},
			{5,115,-32,-30,-8,-20,-13,-11,-28,-15,-9,-18,-31,-24,-22,1,-5,0,-10,-5},
			{-3,-32,47,27,-45,1,4,-38,5,-40,-30,22,-7,9,-3,5,0,-29,-52,-28},
			{0,-30,27,36,-39,-8,4,-27,12,-28,-20,9,-5,17,4,2,-1,-19,-43,-27},
			{-23,-8,-45,-39,70,-52,-1,10,-33,20,16,-31,-38,-26,-32,-28,-22,1,36,51},
			{5,-20,1,-8,-52,66,-14,-45,-11,-44,-35,4,-16,-10,-10,4,-11,-33,-40,-40},
			{-8,-13,4,4,-1,-14,60,-22,6,-19,-13,12,-11,12,6,-2,-3,-20,-8,22},
			{-8,-11,-38,-27,10,-45,-22,40,-21,28,25,-28,-26,-19,-24,-18,-6,31,-18,-7},
			{-4,-28,5,12,-33,-11,6,-21,32,-21,-14,8,-6,15,27,1,1,-17,-35,-21},
			{-12,-15,-40,-28,20,-44,-19,28,-21,40,28,-30,-23,-16,-22,-21,-13,18,-7,0},
			{-7,-9,-30,-20,16,-35,-13,25,-14,28,43,-22,-24,-10,-17,-14,-6,16,-10,-2},
			{-3,-18,22,9,-31,4,12,-28,8,-30,-22,38,-9,7,3,9,5,-22,-36,-14},
			{3,-31,-7,-5,-38,-16,-11,-26,-6,-23,-24,-9,76,-2,-9,4,1,-18,-50,-31},
			{-2,-24,9,17,-26,-10,12,-19,15,-16,-10,7,-2,27,15,2,0,-15,-27,-17},
			{-6,-22,-3,4,-32,-10,6,-24,27,-22,-17,3,-9,15,47,-2,-2,-20,-16,-18},
			{11,1,5,2,-28,4,-2,-18,1,-21,-14,9,4,2,-2,22,15,-10,-33,-19},
			{6,-5,0,-1,-22,-11,-3,-6,1,-13,-6,5,1,0,-2,15,25,0,-35,-19},
			{1,0,-29,-19,1,-33,-20,31,-17,18,16,-22,-18,-15,-20,-10,0,34,-26,-11},
			{-36,-10,-52,-43,36,-40,-8,-18,-35,-7,-10,-36,-50,-27,-16,-33,-35,-26,142,41},
			{-22,-5,-28,-27,51,-40,22,-7,-21,0,-2,-14,-31,-17,-18,-19,-19,-11,41,78}};
		char[] C={'A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W','Y'};
		
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
				if(i==10000)
				{
				code[0]='A';
				code[1]='C';
				code[2]='D';
				code[3]='E';
				code[4]='F';
				code[5]='G';
				code[6]='H';
				code[7]='I';
				code[8]='K';
				code[9]='L';
				code[10]='M';
				code[11]='N';
				code[12]='P';
				code[13]='Q';
				code[14]='R';
				code[15]='S';
				code[16]='T';
				code[17]='V';
				code[18]='W';
				code[19]='Y';
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
							int Score;
							if(Sc(Tr(test1,code))=='*'||Sc(Tr(test2,code))=='*')
									{
								Score=0;
									}
							else
							{
								Score=ScoringMatrix[Sc(Tr(test1,code))][Sc(Tr(test2,code))];
							}
							ScoreAll[i]=ScoreAll[i]+Score;
							//System.out.println(Score);
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
							int Score1;
							if(Sc(Tr(test1,code))=='*'||Sc(Tr(test2,code))=='*')
									{
								Score1=0;
									}
							else
							{
								Score1=ScoringMatrix[Sc(Tr(test1,code))][Sc(Tr(test2,code))];
							}
							//System.out.println(Score1);
							ScoreAll[i]=ScoreAll[i]+Score1;
						}
								
				}
				}
			}
			System.out.print(i+1+":");
			System.out.println(ScoreAll[i]);
				
				
		
				
		}
		int tag=ScoreAll[TotalNum];
		System.out.println("end");
		for(int e=0;e<TotalNum;e++)
			for(int f=e+1;f<TotalNum+1;f++)
			{
				int temp;
				if(ScoreAll[e]<ScoreAll[f])
				{
					temp=ScoreAll[e];
					ScoreAll[e]=ScoreAll[f];
					ScoreAll[f]=temp;
					
				}
			}
		for(int g=0;g<TotalNum+1;g++)
			if(ScoreAll[g]==tag)
			{   
				System.out.print("本来编码方式的排列位数：");
				System.out.print(g+1);
				System.out.print("/");
				System.out.print(TotalNum);
				break;
			}
	}
}


