import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class AlternativeCodesAPP {
	static ArrayList<String> permutationLocation1 = new ArrayList<String>();// 密码子位置1的4！全排列所有的数
	static ArrayList<String> permutationLocation2 = new ArrayList<String>();// 密码子位置2的4！全排列所有的数
	static ArrayList<String> permutationLocation3 = new ArrayList<String>();// 密码子位置3的2！全排列所有的数
	static ArrayList<Integer>frameSScoreBlossum62List=new ArrayList<Integer>();
	static ArrayList<Integer>frameSScorePAM250List=new ArrayList<Integer>();
	static ArrayList<Integer>frameSScoreGon250List=new ArrayList<Integer>();
	static int countAlternativeCodes = 0;//计算生成了多少密码表
	static int countPermutation = 0;//计算0123的全排列生成的数组中有多少元素
	static ArrayList<AlternativeCode> allAlternativeCodes = new ArrayList<AlternativeCode>();//存放所有的密码表
	

	public static void main(String[] args) {
		permutationLocation3.add("UCAG");//密码子第三个位置的2！全排列直接添加
		permutationLocation3.add("UCGA");//密码子第三个位置的2！全排列直接添加



		permutation("", "UCAG");// 生成0123全排列的数，0代表U, 1代表C, 2代表A, 3代表G
		System.out.println(permutationLocation1);// 输出4！全排列所有的数
		System.out.println("0123的全排列4!="+countPermutation);// 测试生成全排列个数是24
		
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 24; j++) {
				for (int k = 0; k < 2; k++) {
					AlternativeCode ac = new AlternativeCode(permutationLocation1.get(i), permutationLocation2.get(j),
							permutationLocation3.get(k));
					allAlternativeCodes.add(ac);//将密码表添加到allAlternativeCodes
					countAlternativeCodes++;//计算生成了多少张密码表
					//System.out.println(ac);
					frameSScoreBlossum62List.add(ac.getFrameSScoreBlossum62());//存放Blossum62的分数
					frameSScorePAM250List.add(ac.getFrameSScorePAM250());//存放PAM250的分数
					frameSScoreGon250List.add(ac.getFrameSScoreGon250());//存放Gon250的分数

				}
			}
		}//生成1152张遗传密码表
		System.out.println("一共" + countAlternativeCodes + "张密码表。");
		
		Collections.sort(frameSScoreBlossum62List);//排序
		Collections.reverse(frameSScoreBlossum62List);//逆序
		Collections.sort(frameSScorePAM250List);
		Collections.reverse(frameSScorePAM250List);
		Collections.sort(frameSScoreGon250List);
		Collections.reverse(frameSScoreGon250List);
		
		System.out.println("frameSScoreBlossum62List");
		for(int i=0;i<1152;i++){
			System.out.println((i+1)+":"+frameSScoreBlossum62List.get(i));
		}
		System.out.println("自然表排在第"+(frameSScoreBlossum62List.indexOf(-344)+1)+"位。百分之"+((float)(frameSScoreBlossum62List.indexOf(-344)+1)/1152*100));
		System.out.println("frameSScorePAM250List");
		System.out.println("自然表排在第"+(frameSScorePAM250List.indexOf(-344)+1)+"位。百分之"+((float)(frameSScorePAM250List.indexOf(-344)+1)/1152*100));
		System.out.println("frameSScoreGon250List");
		System.out.println("自然表排在第"+(frameSScoreGon250List.indexOf(-912)+1)+"位。百分之"+((float)(frameSScoreGon250List.indexOf(-912)+1)/1152*100));
	
	}
	

	public static void permutation(String current, String rest) {// 0123的4！全排列
		if (rest.equals("")) {//当rest中的数全部被调出
			permutationLocation1.add(current);//将全排列生成的元素放入permutationLocation1中
			permutationLocation2.add(current);//将全排列生成的元素放入permutationLocation2中
			countPermutation++;//计算生成的全排列数
		} else {//rest中还存在数
			for (int i = 0; i < rest.length(); i++) {
				String nextNum = current + rest.charAt(i);//将rest里面的数逐个放入current的后面，赋值给nextNum
				String remain = rest.substring(0, i) + rest.substring(i + 1);//将rest中剩下的数连接在一起，赋值给remain
				permutation(nextNum, remain);//将nextNum和remain传入permutation（），实现递归
			}
		}
	}

	

	

}
