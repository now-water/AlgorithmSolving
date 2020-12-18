#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <map>
#include <set>
#include <bitset>
#include <unordered_map>
#include <unordered_set>
#include <cstring>
#include <cmath>
#include <cstdio>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF 987654321
#define endl '\n'
#define vec vector<int>
#define vec2 vector<vector<int>>
#define FOR(i, n) for(int i=0; i<n; ++i)
using namespace std;

int n, m, r, c, dir;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
vec2 board;

// 초기화
void init()
{
	FASTIO
		cin >> n >> m >> r >> c >> dir;
	board = vec2(n, vec(m, 0));
	FOR(i, n)
		FOR(j, m)
		cin >> board[i][j];
}
// 보드를 벗어나는지 확인하는 기능
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n&& y >= 0 && y < m);
}

void calc()
{
	queue<pii> q;
	q.push(pii(r, c));
	board[r][c] = 2;
	int cnt = 1;
	while (!q.empty())
	{
		pii here = q.front(); q.pop();
		int x = here.first, y = here.second;
		bool contFlag = false;
		int tempDir = dir;
		FOR(i, 4)
		{
			tempDir = (tempDir - 1  < 0 ? 3 : tempDir - 1);
			int nx = x + dx[tempDir], ny = y + dy[tempDir];
			if (isBorder(nx, ny) && board[nx][ny] == 0) // 벽은 1, 청소하지 않은 칸은 0
			{
				q.push(pii(nx, ny));
				board[nx][ny] = 2; // 청소를 완료한 칸을 2로 표시
				dir = tempDir;
				cnt += 1;
				contFlag = true;
				break;
			}
		}
		if (contFlag) continue;
		if (board[x - dx[dir]][y - dy[dir]] == 1)
			break;
		q.push(pii(x - dx[dir], y - dy[dir]));
	}
	cout << cnt << endl;
}
int main()
{
	init();
	calc();
}
