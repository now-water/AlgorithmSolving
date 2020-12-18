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

int n, m, g, r;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1};
vector<pii> ground;
vec2 board; // 0: 호수, 1: 배양액 x, 2: 배양액 o, 3: 초록색 배양액, 4: 빨간색 배양액
queue<pii> q;
int plantG[51][51], plantR[51][51];
// 초기화
void init()
{
	FASTIO
		cin >> n >> m >> g >> r;
	board = vec2(n, vec(m, 0));
	memset(plantG, 0, sizeof(plantG));
	memset(plantR, 0, sizeof(plantR));
	FOR(i, n)
		FOR(j, m)
	{
		cin >> board[i][j];
		if (board[i][j] == 2) ground.push_back(pii(i, j));
	}
}
// 보드를 벗어나는지 확인하는 기능
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < m);
}
int spread(queue<pii> greenQ, queue<pii> redQ)
{
	int bloom = 0, sec = 1;
	while (greenQ.size() > 0 && redQ.size() > 0)
	{
		// time overlaps
		sec += 1;
		// green seed spread
		int greenSize = greenQ.size();
		FOR(j, greenSize)
		{
			pii green = greenQ.front(); greenQ.pop();
			if (plantR[green.first][green.second]) continue;// 이미 꽃을 피운 자리
			FOR(i, 4)
			{
				int nx = green.first + dx[i], ny = green.second + dy[i];
				// 두 가지 배양액이 안뿌려졌고, 뿌릴 수 있는 땅일 경우
				if (isBorder(nx, ny) && board[nx][ny] && !plantG[nx][ny] && !plantR[nx][ny])
				{
					greenQ.push(pii(nx, ny));
					plantG[nx][ny] = sec;
				}
			}
		}
		// red seed spread
		int redSize = redQ.size();
		FOR(j, redSize)
		{
			pii red = redQ.front(); redQ.pop();
			FOR(i, 4)
			{
				int nx = red.first + dx[i], ny = red.second + dy[i];
				if (isBorder(nx, ny) && board[nx][ny] && !plantR[nx][ny])
				{
					if (!plantG[nx][ny])
					{
						redQ.push(pii(nx, ny));
						plantR[nx][ny] = sec;
					}
					else if (plantG[nx][ny] == sec)
					{
						bloom += 1;
						plantR[nx][ny] = sec;
					}
				}
			}
		}
	}
	return bloom;
}
void calc()
{
	int total_ground = ground.size(), total_seed = g + r, ans = -1;
	// 모든 조합의 수로 돌려본다
	vec seed; // 0: none, 1: green, 2: red
	FOR(i, total_ground - total_seed) seed.push_back(0);
	FOR(i, g) seed.push_back(1);
	FOR(i, r) seed.push_back(2);
	int cnt = 0;
	while (1)
	{
		queue<pii> greenQ, redQ;
		memset(plantG, 0, sizeof(plantG));
		memset(plantR, 0, sizeof(plantR));
		// 배양액 뿌리기
		FOR(i, total_ground)
			if (seed[i] == 1)
			{
				greenQ.push(pii(ground[i].first, ground[i].second));
				plantG[ground[i].first][ground[i].second] = 1;
			}
			else if (seed[i] == 2)
			{
				redQ.push(pii(ground[i].first, ground[i].second));
				plantR[ground[i].first][ground[i].second] = 1;
			}
		ans = max(ans, spread(greenQ, redQ));
		if (!next_permutation(seed.begin(), seed.end()))
			break;
	}
	cout << ans << endl;
}
int main()
{
	init();
	calc();
}
