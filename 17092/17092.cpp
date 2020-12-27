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

int h, w, n;
map<pii, int> black, cnt;
void init()
{
	FASTIO
		cin >> h >> w >> n;
	FOR(i, n)
	{
		int x, y;
		cin >> x >> y;
		black[pii(x, y)] = 1;
	}
}

void calc()
{
	for (auto& elem : black)
	{
		int x = elem.first.first - 2, y = elem.first.second - 2; // ÁÂÇ¥¾ÐÃà
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				int nx = x + i, ny = y + j;
				if (nx < 1 || ny < 1 || nx + 2 > h || ny + 2 > w) continue;
				if (cnt.find(pii(nx, ny)) != cnt.end())
					cnt[pii(nx, ny)] += 1;
				else cnt[pii(nx, ny)] = 1;
			}
		}
	}
	vector<ll> black_cnt(10, 0);
	ll sum_cnt = 0;
	for (auto& elem : cnt)
	{
		black_cnt[elem.second] += 1;
		sum_cnt += 1;
	}
	black_cnt[0] = ((ll)h - 2) * ((ll)w - 2) - sum_cnt;
	for (auto& elem : black_cnt)
		cout << elem << endl;
}
int main()
{
	init();
	calc();
}