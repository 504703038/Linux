package Background;

import java.util.Random;

public class Digital {
	private char digi[] = new char[4];
	private int number[] = new int[10];
	private static int try_times;//尝试次数
//	初始化
	Digital(){
		for(int i = 0;i<10;i++) {
			number[i]=0;
		}
		try_times=0;
	}
//	随机数生成
	public void generate() {
		Random random = new Random();
		for (int i=0;i<4;i++) {
			int x=random.nextInt(10);
			while (number[x]!=0 || (x==0 && i==0)) {
				x=random.nextInt(10);
			}
			number[x]=i;
			digi[i]=(char)(48+x);
		}
	}
//	验证输入的数
	public String check(int x) {
		try_times++;
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
		return n+"A"+m+"B";
	}
//	获取尝试次数
	public int get_try_times() {
		return try_times;
	}
//	生成分数
	public int get_score() {
		
		return 0;
	}
}
