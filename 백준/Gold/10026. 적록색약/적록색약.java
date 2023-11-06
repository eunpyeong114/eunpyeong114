
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N;
	public static String[][] image;
	public static boolean[][] visitedSameRG;
	public static boolean[][] visitedDiffRG;
	public static int sameRGcnt = 0;
	public static int diffRGcnt = 0;
	public static int[] dr = { 0, 1, 0, -1 };
	public static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		image = new String[N][N];

		for (int i = 0; i < N; i++) {
			image[i] = sc.next().split("");
		}

		visitedSameRG = new boolean[N][N];
		visitedDiffRG = new boolean[N][N];

		// 적록색약 아닌 사람
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedDiffRG[i][j])
					continue;
				diffRG(i, j);
			}
		}
		// 적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedSameRG[i][j])
						continue;
				sameRG(i, j);
			}
		}

		System.out.println(diffRGcnt + " " + sameRGcnt);
	}

	// 적록색약 아닌 사람
	public static void diffRG(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { i, j });
		visitedDiffRG[i][j] = true;
		diffRGcnt++;
		// queue가 빌 때까지 반복
		String color = image[i][j];
		while (!queue.isEmpty()) {
			// queue에서 제거하고
			int[] poll = queue.poll();
			int r = poll[0];
			int c = poll[1];
			// 4방향 탐색 후 제거한 값과 인접한 값들 확인 후 넣어주기
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				// 제거한 값과 동일한 경우에만 삽입
				if (!visitedDiffRG[nr][nc] && color.equals(image[nr][nc])) {
					// 인접한 것도 파랑일 때만 queue 삽입
					queue.add(new int[] { nr, nc });
					// 방문처리 해주기
					visitedDiffRG[nr][nc] = true;
				}
			}
		}
	}

	// 적록색약인 사람
	public static void sameRG(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { i, j });
		visitedSameRG[i][j] = true;
		sameRGcnt++;
		String color = image[i][j];
		// queue가 빌 때까지 반복
		while (!queue.isEmpty()) {
			// queue에서 제거하고
			int[] poll = queue.poll();
			int r = poll[0];
			int c = poll[1];
			// 4방향 탐색 후 제거한 값과 인접한 값들 확인 후 넣어주기
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				// 제거한 값이 파랑인 경우
				if (color.equals("B")) {
					// 인접한 것도 파랑일 때만 queue 삽입
					if (image[nr][nc].equals("B") && !visitedSameRG[nr][nc]) {
						queue.add(new int[] { nr, nc });
						// 방문처리 해주기
						visitedSameRG[nr][nc] = true;
					}
					// 그 외(제거한 값이 초록이나 빨강인 경우)
				} else if (color.equals("R") || color.equals("G")) {
					// 인접한 것도 초록이나 빨강인 경우에만 queue 삽입
					if ((image[nr][nc].equals("G") || image[nr][nc].equals("R")) && !visitedSameRG[nr][nc]) {
						queue.add(new int[] { nr, nc });
						// 방문처리 해주기
						visitedSameRG[nr][nc] = true;
					}
				}
			}
		}
	}
}