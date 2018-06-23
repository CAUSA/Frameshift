import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class AlternativeCodesAPP {
	static ArrayList<String> permutationLocation1 = new ArrayList<String>();// ������λ��1��4��ȫ�������е���
	static ArrayList<String> permutationLocation2 = new ArrayList<String>();// ������λ��2��4��ȫ�������е���
	static ArrayList<String> permutationLocation3 = new ArrayList<String>();// ������λ��3��2��ȫ�������е���
	static ArrayList<Integer>frameSScoreBlossum62List=new ArrayList<Integer>();
	static ArrayList<Integer>frameSScorePAM250List=new ArrayList<Integer>();
	static ArrayList<Integer>frameSScoreGon250List=new ArrayList<Integer>();
	static int countAlternativeCodes = 0;//���������˶��������
	static int countPermutation = 0;//����0123��ȫ�������ɵ��������ж���Ԫ��
	static ArrayList<AlternativeCode> allAlternativeCodes = new ArrayList<AlternativeCode>();//������е������
	

	public static void main(String[] args) {
		permutationLocation3.add("UCAG");//�����ӵ�����λ�õ�2��ȫ����ֱ�����
		permutationLocation3.add("UCGA");//�����ӵ�����λ�õ�2��ȫ����ֱ�����



		permutation("", "UCAG");// ����0123ȫ���е�����0����U, 1����C, 2����A, 3����G
		System.out.println(permutationLocation1);// ���4��ȫ�������е���
		System.out.println("0123��ȫ����4!="+countPermutation);// ��������ȫ���и�����24
		
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 24; j++) {
				for (int k = 0; k < 2; k++) {
					AlternativeCode ac = new AlternativeCode(permutationLocation1.get(i), permutationLocation2.get(j),
							permutationLocation3.get(k));
					allAlternativeCodes.add(ac);//���������ӵ�allAlternativeCodes
					countAlternativeCodes++;//���������˶����������
					//System.out.println(ac);
					frameSScoreBlossum62List.add(ac.getFrameSScoreBlossum62());//���Blossum62�ķ���
					frameSScorePAM250List.add(ac.getFrameSScorePAM250());//���PAM250�ķ���
					frameSScoreGon250List.add(ac.getFrameSScoreGon250());//���Gon250�ķ���

				}
			}
		}//����1152���Ŵ������
		System.out.println("һ��" + countAlternativeCodes + "�������");
		
		Collections.sort(frameSScoreBlossum62List);//����
		Collections.reverse(frameSScoreBlossum62List);//����
		Collections.sort(frameSScorePAM250List);
		Collections.reverse(frameSScorePAM250List);
		Collections.sort(frameSScoreGon250List);
		Collections.reverse(frameSScoreGon250List);
		
		System.out.println("frameSScoreBlossum62List");
		for(int i=0;i<1152;i++){
			System.out.println((i+1)+":"+frameSScoreBlossum62List.get(i));
		}
		System.out.println("��Ȼ�����ڵ�"+(frameSScoreBlossum62List.indexOf(-344)+1)+"λ���ٷ�֮"+((float)(frameSScoreBlossum62List.indexOf(-344)+1)/1152*100));
		System.out.println("frameSScorePAM250List");
		System.out.println("��Ȼ�����ڵ�"+(frameSScorePAM250List.indexOf(-344)+1)+"λ���ٷ�֮"+((float)(frameSScorePAM250List.indexOf(-344)+1)/1152*100));
		System.out.println("frameSScoreGon250List");
		System.out.println("��Ȼ�����ڵ�"+(frameSScoreGon250List.indexOf(-912)+1)+"λ���ٷ�֮"+((float)(frameSScoreGon250List.indexOf(-912)+1)/1152*100));
	
	}
	

	public static void permutation(String current, String rest) {// 0123��4��ȫ����
		if (rest.equals("")) {//��rest�е���ȫ��������
			permutationLocation1.add(current);//��ȫ�������ɵ�Ԫ�ط���permutationLocation1��
			permutationLocation2.add(current);//��ȫ�������ɵ�Ԫ�ط���permutationLocation2��
			countPermutation++;//�������ɵ�ȫ������
		} else {//rest�л�������
			for (int i = 0; i < rest.length(); i++) {
				String nextNum = current + rest.charAt(i);//��rest��������������current�ĺ��棬��ֵ��nextNum
				String remain = rest.substring(0, i) + rest.substring(i + 1);//��rest��ʣ�µ���������һ�𣬸�ֵ��remain
				permutation(nextNum, remain);//��nextNum��remain����permutation������ʵ�ֵݹ�
			}
		}
	}

	

	

}
