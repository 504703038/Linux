

import java.util.Random;


public class Digital {
	private char digi[] = new char[4];
	private int number[] = new int[10];
	private boolean make_it;
	private String user_name;
	private static int try_times;//尝试次数
//	初始化
	public Digital(String name){
		for(int i = 0;i<10;i++) {
			number[i]=0;
		}
		try_times=0;
		user_name = name;
		generate();
	}
//	随机数生成
	private void generate() {
		make_it=false;
		Random random = new Random();
		for (int i=0;i<4;i++) {
			int x=random.nextInt(10);
			while (number[x]!=0 || (x==0 && i==0)) {
				x=random.nextInt(10);
			}
			number[x]=i+1;
			digi[i]=(char)(48+x);
		}
	}
//	验证输入的数
	public String check(int x) {
		if (!make_it) try_times++;
		int n,m;
		n=m=0;
		int y=x;
		for(int i=3;i>=0;i--) {
			int z=y%10;
			y/=10;
			if (digi[i]==(char)(48+z)) {
				n++;
			}
			else if (number[z]!=0) {
				m++;
			}
		}
		if (n==4) make_it=true;
		return n+"A"+m+"B";
	} 
//	获取尝试次数
	public int get_try_times() {
		return try_times;
	}
//	生成分数
	public Score get_score() {
		Score score = new Score(user_name, 100-try_times);
		return score;
	}
	public boolean is_finish() {
		return make_it;
	}
	public String toString() {
		return ""+digi[0]+digi[1]+digi[2]+digi[3];
	}
}
