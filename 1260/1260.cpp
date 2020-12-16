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
#define vec vector<int>
#define vec2 vector<vector<int>>
#define fori(n) for(int i=0; i<n; ++i)
#define forj(n) for(int j=0; j<n; ++j)
using namespace std;

int n, m, s, TC;
bool dvisited[1002], bvisited[1002];
vec path[1002];
void init()
{
	FASTIO
		cin >> n >> m >> s;

	fori(m)
	{
		int u, v;
		cin >> u >> v;
		path[u].push_back(v);
		path[v].push_back(u);
	}
	fori(n) sort(path[i].begin(), path[i].end());
	memset(dvisited, 0, sizeof(dvisited));
	memset(bvisited, 0, sizeof(bvisited));
}
void dfs(int here)
{
	dvisited[here] = true;
	cout << here << ' ';
	for (auto& elem : path[here])
		if (!dvisited[elem])
			dfs(elem);
}
void bfs(int here)
{
	queue<int> q;
	q.push(here);
	bvisited[here] = true;
	while (!q.empty())
	{
		int here = q.front(); q.pop();
		cout << here << ' ';
		for (auto& there : path[here])
			if (!bvisited[there])
			{
				bvisited[there] = true;
				q.push(there);
			}
	}
	cout << endl;
}
int main()
{
	init();
	dfs(s);
	cout << endl;
	bfs(s);
}