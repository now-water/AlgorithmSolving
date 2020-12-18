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

int n, m, TC;
vector<pii> spot;
vec2 adj;
bool visited[101 * 101];
bool connected(pii a, pii b)
{
	return abs(a.first - b.first) + abs(a.second - b.second) <= 1000;
}
void init()
{
	cin >> n;
	int x, y;
	spot.clear();
	memset(visited, 0, sizeof(visited));
	adj = vec2(n + 2);
	for (int i = 0; i < n + 2; ++i)
	{
		cin >> x >> y;
		spot.push_back(pii(x, y));
	}
	FOR(i, n+2)
	{
		for (int j = i + 1; j < n + 2; j++)
		{
			if (i == j) continue;
			if (connected(spot[i], spot[j]))
			{
				adj[i].push_back(j);
				adj[j].push_back(i);
			}
		}
	}
}
void calc()
{
	queue<int> q;
	q.push(0);
	while (!q.empty())
	{
		int here = q.front(); q.pop();
		for (auto& elem : adj[here])
		{
			if (elem == n + 1)
			{
				cout << "happy" << endl;
				return;
			}
			if (!visited[elem])
			{
				q.push(elem);
				visited[elem] = true;
			}
		}
	}
	cout << "sad" << endl;
}
int main()
{
	cin >> TC;
	FASTIO
	while (TC-- > 0)
	{
		init();
		calc();
	}
}