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

int n, m, s, TC;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int chunk;
bool visited[301][301];
vec2 board;
queue<pii> q;
// 초기화
void init()
{
	FASTIO
		cin >> n >> m;
	board = vec2(n, vec(m, 0));
	chunk = 0;
	FOR(i, n)
		FOR(j, m)
	{
		cin >> board[i][j];
		if (board[i][j]) chunk += 1;
	}

}
// 보드를 벗어나는지 확인하는 기능
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n&& y >= 0 && y < m);
}
// 두 개의 빙산으로 나뉘어지는지 확인하는 코드
bool isSeperate()
{
	int tempChunk = 0, flag = 0;
	memset(visited, 0, sizeof(visited));
	// 시작점 발견
	FOR(i, n)
	{
		FOR(j, m)
			if (board[i][j])
			{
				tempChunk += 1;
				flag = 1;
				visited[i][j] = true;
				q.push(pii(i, j));
				break;
			}
		if (flag) break;
	}
	// BFS 
	while (!q.empty())
	{
		pii here = q.front(); q.pop();
		FOR(i, 4)
		{
			int nx = here.first + dx[i], ny = here.second + dy[i];
			if (isBorder(nx, ny) && board[nx][ny] > 0 && !visited[nx][ny])
			{
				q.push(pii(nx, ny));
				visited[nx][ny] = true;
				tempChunk += 1;
			}
		}
	}
	// 하나의 빙산인 경우
	if (tempChunk == chunk)
		return false;
	return true;
}
// 빙산을 녹이는 코드
void melt()
{
	vector<pii> victim;
	FOR(x, n)
	{
		FOR(y, m)
			if (board[x][y] > 0)
			{
				int minus = 0;
				FOR(k, 4) // 얼음이 녹는 기능
				{
					int nx = x + dx[k], ny = y + dy[k];
					if (isBorder(nx, ny) && board[nx][ny] == 0)
						minus -= 1;
				}
				if (board[x][y] + minus <= 0)
					victim.push_back(pii(x, y));
				else board[x][y] += minus;
			}
	}
	for (auto& elem : victim)
	{
		chunk -= 1;
		board[elem.first][elem.second] = 0;
	}
}
void calc()
{
	int years = 0;
	while (chunk > 1 && !isSeperate())
	{
		years += 1;
		melt();
		//FOR(i, n)
		//{
		//	FOR(j, m) cout << board[i][j];
		//	cout << endl;
		//}
		//cout << endl;
	}

	if (chunk > 1) cout << years << endl;
	else cout << 0 << endl;
}
int main()
{
	init();
	calc();
}

/*
3 4
0 0 0 0
5 5 5 5
0 0 0 2
*/