
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] score = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			score[i] = sc.nextInt();
		}

		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (i <= 2) {
				dp[i] = score[i] + score[i - 1];
			} else if (i == 3) {
				dp[i] = Math.max(score[1], score[2]) + score[i];
			} else {
				int max1 = dp[i - 2] + score[i];
				int max2 = dp[i - 3] + score[i - 1] + score[i];
				dp[i] = Math.max(max1, max2);
			}
		}
		System.out.println(dp[N]);
		sc.close();
	}
}