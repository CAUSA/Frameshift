import java.util.ArrayList;

public class AlternativeCode {// 封装密码表的属性，通过传入的密码子三个位置的信息生成密码表
	private ArrayList<String> allCodon = new ArrayList<String>();// 存放每一个密码表的所有密码子，方便查找
	private ArrayList<Codon> allCodon1 = new ArrayList<Codon>();
	private int frameSScoreBlossum62 = 0;
	private int frameSScorePAM250 = 0;
	private int frameSScoreGon250 = 0;

	private String AA;
	private String frameSAA;
	final private String[] aminoAcidList = { "F", "F", "L", "L", "S", "S", "S", "S", "Y", "Y", "ST", "ST", "C", "C",
			"ST", "W", "L", "L", "L", "L", "P", "P", "P", "P", "H", "H", "Q", "Q", "R", "R", "R", "R", "I", "I", "I",
			"M", "T", "T", "T", "T", "N", "N", "K", "K", "S", "S", "R", "R", "V", "V", "V", "V", "A", "A", "A", "A",
			"D", "D", "E", "E", "G", "G", "G", "G" };// 按顺序存放氨基酸
	final private String allAAsBlossum62 = "CSTPAGNDEQHRKMILVFYW";
	final private String allAAsPAM250 = "CSTPAGNDEQHRKMILVFYW";
	final private String allAAsGon250 = "ACDEFGHIKLMNPQRSTVWY";
	final private int[][] BLOSUM62 = { 
			{ 9, -1, -1, -3, 0, -3, -3, -3, -4, -3, -3, -3, -3, -1, -1, -1, -1, -2, -2, -2 },
			{ -1, 4, 1, -1, 1, 0, 1, 0, 0, 0, -1, -1, 0, -1, -2, -2, -2, -2, -2, -3 },
			{ -1, 1, 5, -1, 0, -2, 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, 0, -2, -2, -2 },
			{ -3, -1, -1, 7, -1, -2, -2, -1, -1, -1, -2, -2, -1, -2, -3, -3, -2, -4, -3, -4 },
			{ 0, 1, 0, -1, 4, 0, -2, -2, -1, -1, -2, -1, -1, -1, -1, -1, 0, -2, -2, -3 },
			{ -3, 0, -2, -2, 0, 6, 0, -1, -2, -2, -2, -2, -2, -3, -4, -4, -3, -3, -3, -2 },
			{ -3, 1, 0, -2, -2, 0, 6, 1, 0, 0, 1, 0, 0, -2, -3, -3, -3, -3, -2, -4 },
			{ -3, 0, -1, -1, -2, -1, 1, 6, 2, 0, -1, -2, -1, -3, -3, -4, -3, -3, -3, -4 },
			{ -4, 0, -1, -1, -1, -2, 0, 2, 5, 2, 0, 0, 1, -2, -3, -3, -2, -3, -2, -3 },
			{ -3, 0, -1, -1, -1, -2, 0, 0, 2, 5, 0, 1, 1, 0, -3, -2, -2, -3, -1, -2 },
			{ -3, -1, -2, -2, -2, -2, 1, -1, 0, 0, 8, 0, -1, -2, -3, -3, -3, -1, 2, -2 },
			{ -3, -1, -1, -2, -1, -2, 0, -2, 0, 1, 0, 5, 2, -1, -3, -2, -3, -3, -2, -3 },
			{ -3, 0, -1, -1, -1, -2, 0, -1, 1, 1, -1, 2, 5, -1, -3, -2, -2, -3, -2, -3 },
			{ -1, -1, -1, -2, -1, -3, -2, -3, -2, 0, -2, -1, -1, 5, 1, 2, 1, 0, -1, -1 },
			{ -1, -2, -1, -3, -1, -4, -3, -3, -3, -3, -3, -3, -3, 1, 4, 2, 3, 0, -1, -3 },
			{ -1, -2, -1, -3, -1, -4, -3, -4, -3, -2, -3, -2, -2, 2, 2, 4, 1, 0, -1, -2 },
			{ -1, -2, 0, -2, 0, -3, -3, -3, -2, -2, -3, -3, -2, 1, 3, 1, 4, -1, -1, -3 },
			{ -2, -2, -2, -4, -2, -3, -3, -3, -3, -3, -1, -3, -3, 0, 0, 0, -1, 6, 3, 1 },
			{ -2, -2, -2, -3, -2, -3, -2, -3, -2, -1, 2, -2, -2, -1, -1, -1, -1, 3, 7, 2 },
			{ -2, -3, -2, -4, -3, -2, -4, -4, -3, -2, -2, -3, -3, -1, -3, -2, -3, 1, 2, 11 } };

	final private int[][] PAM250 = { { 12, 0, -2, -3, -2, -3, -4, -5, -5, -5, -3, -4, -5, -5, -2, -6, -2, -4, 0, -8 },
			{ 0, 2, 1, 1, 1, 1, 1, 0, 0, -1, -1, 0, 0, -2, -1, -3, -1, -3, -3, -2 },
			{ -2, 1, 3, 0, 1, 0, 0, 0, 0, -1, -1, -1, 0, -1, 0, -2, 0, -3, -3, -5 },
			{ -3, 1, 0, 6, 1, -1, -1, -1, -1, 0, 0, 0, -1, -2, -2, -3, -1, -5, -5, -6 },
			{ -2, 1, 1, 1, 2, 1, 0, 0, 0, 0, -1, -2, -1, -1, -1, -2, 0, -5, -3, -6 },
			{ -3, 1, 0, -1, 1, 5, 0, 1, 0, -1, -2, -3, -2, -3, -3, -4, -1, -5, -5, -7 },
			{ -4, 1, 0, -1, 0, 0, 2, 2, 1, 1, 2, 0, 1, -2, -2, -3, -2, -4, -2, -4 },
			{ -5, 0, 0, -1, 0, 1, 2, 4, 3, 2, 1, -1, 0, -3, -2, -4, -2, -6, -4, -7 },
			{ -5, 0, 0, -1, 0, 0, 1, 3, 4, 2, 1, -1, 0, -2, -2, -3, -2, -5, -4, -7 },
			{ -5, -1, -1, 0, 0, -1, 1, 2, 2, 4, 3, 1, 1, -1, -2, -2, -2, -5, -4, -5 },
			{ -3, -1, -1, 0, -1, -2, 2, 1, 1, 3, 6, 2, 0, -2, -2, -2, -2, -2, 0, -3 },
			{ -4, 0, -1, 0, -2, -3, 0, -1, -1, 1, 2, 6, 3, 0, -2, -3, -2, -4, -4, 2 },
			{ -5, 0, 0, -1, -1, -2, 1, 0, 0, 1, 0, 3, 5, 0, -2, -3, -2, -5, -4, -3 },
			{ -5, -2, -1, -2, -1, -3, -2, -3, -2, -1, -2, 0, 0, 6, 2, 4, 2, 0, -2, -4 },
			{ -2, -1, 0, -2, -1, -3, -2, -2, -2, -2, -2, -2, -2, 2, 5, 2, 4, 1, -1, -5 },
			{ -6, -3, -2, -3, -2, -4, -3, -4, -3, -2, -2, -3, -3, 4, 2, 6, 2, 2, -1, -2 },
			{ -2, -1, 0, -1, 0, -1, -2, -2, -2, -2, -2, -2, -2, 2, 4, 2, 4, -1, -2, -6 },
			{ -4, -3, -3, -5, -5, -5, -4, -6, -5, -5, -2, -4, -5, 0, 1, 2, -1, 9, 7, 0 },
			{ 0, -3, -3, -5, -3, -5, -2, -4, -4, -4, 0, -4, -4, -2, -1, -1, -2, 7, 10, 0 },
			{ -8, -2, -5, -6, -6, -7, -4, -7, -7, -5, -3, 2, -3, -4, -5, -2, -6, 0, 0, 17 } };
	final private int[][] Gon250 = { { 24, 5, -3, 0, -23, 5, -8, -8, -4, -12, -7, -3, 3, -2, -6, 11, 6, 1, -36, -22 },
			{ 5, 115, -32, -30, -8, -20, -13, -11, -28, -15, -9, -18, -31, -24, -22, 1, -5, 0, -10, -5 },
			{ -3, -32, 47, 27, -45, 1, 4, -38, 5, -40, -30, 22, -7, 9, -3, 5, 0, -29, -52, -28 },
			{ 0, -30, 27, 36, -39, -8, 4, -27, 12, -28, -20, 9, -5, 17, 4, 2, -1, -19, -43, -27 },
			{ -23, -8, -45, -39, 70, -52, -1, 10, -33, 20, 16, -31, -38, -26, -32, -28, -22, 1, 36, 51 },
			{ 5, -20, 1, -8, -52, 66, -14, -45, -11, -44, -35, 4, -16, -10, -10, 4, -11, -33, -40, -40 },
			{ -8, -13, 4, 4, -1, -14, 60, -22, 6, -19, -13, 12, -11, 12, 6, -2, -3, -20, -8, 22 },
			{ -8, -11, -38, -27, 10, -45, -22, 40, -21, 28, 25, -28, -26, -19, -24, -18, -6, 31, -18, -7 },
			{ -4, -28, 5, 12, -33, -11, 6, -21, 32, -21, -14, 8, -6, 15, 27, 1, 1, -17, -35, -21 },
			{ -12, -15, -40, -28, 20, -44, -19, 28, -21, 40, 28, -30, -23, -16, -22, -21, -13, 18, -7, 0 },
			{ -7, -9, -30, -20, 16, -35, -13, 25, -14, 28, 43, -22, -24, -10, -17, -14, -6, 16, -10, -2 },
			{ -3, -18, 22, 9, -31, 4, 12, -28, 8, -30, -22, 38, -9, 7, 3, 9, 5, -22, -36, -14 },
			{ 3, -31, -7, -5, -38, -16, -11, -26, -6, -23, -24, -9, 76, -2, -9, 4, 1, -18, -50, -31 },
			{ -2, -24, 9, 17, -26, -10, 12, -19, 15, -16, -10, 7, -2, 27, 15, 2, 0, -15, -27, -17 },
			{ -6, -22, -3, 4, -32, -10, 6, -24, 27, -22, -17, 3, -9, 15, 47, -2, -2, -20, -16, -18 },
			{ 11, 1, 5, 2, -28, 4, -2, -18, 1, -21, -14, 9, 4, 2, -2, 22, 15, -10, -33, -19 },
			{ 6, -5, 0, -1, -22, -11, -3, -6, 1, -13, -6, 5, 1, 0, -2, 15, 25, 0, -35, -19 },
			{ 1, 0, -29, -19, 1, -33, -20, 31, -17, 18, 16, -22, -18, -15, -20, -10, 0, 34, -26, -11 },
			{ -36, -10, -52, -43, 36, -40, -8, -18, -35, -7, -10, -36, -50, -27, -16, -33, -35, -26, 142, 41 },
			{ -22, -5, -28, -27, 51, -40, 22, -7, -21, 0, -2, -14, -31, -17, -18, -19, -19, -11, 41, 78 } };

	public AlternativeCode(String location1, String location2, String location3) {// 构造函数，传入密码子三个位置的，UCAG的各自不同顺序
		getAlternativeCodeAndScore(location1, location2, location3);
		getCodeFScore();

	}

	public void getAlternativeCodeAndScore(String location1, String location2, String location3) {// 以三维数组的形式生成密码表

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					Codon codon = new Codon(String.valueOf(location1.charAt(i)), String.valueOf(location2.charAt(j)),
							String.valueOf(location3.charAt(k)));
					//System.out.print(codon.toString() + " ");
					allCodon.add(codon.toString());// 将所有的密码子加入allCodon，方便以后查找
					allCodon1.add(codon);

				}
				//System.out.print("|");
			}
			//System.out.println();
		}

	}

	public String matchCodonToAA(String c) {//密码子匹配出对应的氨基酸
		if (allCodon.contains(c))
			return aminoAcidList[allCodon.indexOf(c)];
		return "Error,Not Found!";
	}

	public int getCodonBLOSUM62score(String AA, String frameShiftedAA) {//得出移码前和移码后氨基酸对应在表中的分数
		if (AA.equals("ST") || frameShiftedAA.equals("ST")) {
			return 0;
		} else {
			int i, j;
			i = allAAsBlossum62.indexOf(AA);
			j = allAAsBlossum62.indexOf(frameShiftedAA);
			return BLOSUM62[i][j];
		}

	}

	public int getCodonPAM250score(String AA, String frameShiftedAA) {//得出移码前和移码后氨基酸对应在表中的分数
		if (AA.equals("ST") || frameShiftedAA.equals("ST")) {
			return 0;
		} else {
			int i, j;
			i = allAAsPAM250.indexOf(AA);
			j = allAAsPAM250.indexOf(frameShiftedAA);
			return PAM250[i][j];
		}

	}

	public int getCodonGon250score(String AA, String frameShiftedAA) {//得出移码前和移码后氨基酸对应在表中的分数
		if (AA.equals("ST") || frameShiftedAA.equals("ST")) {
			return 0;
		} else {
			int i, j;
			i = allAAsGon250.indexOf(AA);
			j = allAAsGon250.indexOf(frameShiftedAA);
			return Gon250[i][j];
		}

	}

	public void getCodeFScore() {
		for (int i = 0; i < 64; i++) {//计算一个密码表64个密码子对应分数的总和
			AA = matchCodonToAA(allCodon.get(i));

			for (int j = 0; j < 8; j++) {//计算一个密码子移码后的8个密码子分别对应分数的总和
				frameSAA = matchCodonToAA(allCodon1.get(i).getFrameSCodon()[j]);
				frameSScoreBlossum62 += getCodonBLOSUM62score(AA, frameSAA);

				frameSAA = matchCodonToAA(allCodon1.get(i).getFrameSCodon()[j]);
				frameSScorePAM250 += getCodonPAM250score(AA, frameSAA);

				frameSAA = matchCodonToAA(allCodon1.get(i).getFrameSCodon()[j]);
				frameSScoreGon250 += getCodonGon250score(AA, frameSAA);
			}
		}

	}

	public String toString() {
		String result = "Forward Framshift Blossum62:" + frameSScoreBlossum62 + "\nFramshift PAM250："
				+ frameSScorePAM250 + "\nFramshift Gon250：" + frameSScoreGon250;

		return result;
	}

	public Integer getFrameSScoreBlossum62() {
		return frameSScoreBlossum62;
	}

	public Integer getFrameSScorePAM250() {
		return frameSScorePAM250;
	}

	public Integer getFrameSScoreGon250() {
		return frameSScoreGon250;
	}
	
	public ArrayList<Codon> getAllCodon1(){
		return allCodon1;
	}



}
