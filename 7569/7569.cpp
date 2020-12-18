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

int n, m, h, fresh;
int dx[6] = { -1, 0, 1, 0 };
int dy[6] = { 0, 1, 0, -1, 0, 0 };
vec2 board;
queue<pii> q;
// 초기화
void init()
{
	FASTIO
		cin >> m >> n >> h;
	dx[4] = n; dx[5] = -n;
	board = vec2(n * h, vec(m, 0));
	fresh = 0;
	FOR(i, n * h)
		FOR(j, m)
	{
		cin >> board[i][j];
		if (board[i][j] == 1)
			q.push(pii(i, j));
		else if (board[i][j] == 0) fresh += 1;
	}
}
// 보드를 벗어나는지 확인하는 기능
bool isBorder(int x, int y, int level, int floors)
{
	if (level >= 4) return (x >= 0 && x < n*h && y >= 0 && y < m);
	return (x >= n * floors && x < n * floors + n && y >= 0 && y < m);
}

void calc()
{
	int days = 0;
	while (!q.empty() && fresh > 0)
	{
		int size = q.size();
		for (int i = 0; i < size; ++i)
		{
			pii here = q.front(); q.pop();
			for (int p = 0; p < 6; ++p)
			{
				int nx = here.first + dx[p], ny = here.second + dy[p];
				if (isBorder(nx, ny, p, here.first / n) && board[nx][ny] == 0)
				{
					board[nx][ny] = 1;
					fresh -= 1;
					q.push(pii(nx, ny));
				}
			}
		}
		days += 1;
	}
	if (fresh > 0) cout << -1 << endl;
	else cout << days << endl;
}
int main()
{
	init();
	calc();
}
/*
3 3 2
0 1 0
-1 -1 -1
-1 0 0
0 -1 0
-1 -1 -1
1 0 -1


*/