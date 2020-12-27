#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <set>
#include <bitset>
#include <unordered_map>
#include <unordered_set>
#include <cstring>
#include <cmath>
#include <cstdio>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(0); cout.tie(0);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF 987654321
#define endl '\n'
#define vec vector<int>
#define vec2 vector<vector<int>>
#define FOR(i, n) for(int i=0; i<n; ++i)

using namespace std;
int n, m;
//vector<vector<pii>> adj;
vector<bool> visited;
vector<pair<int, int>> adj[5001];
// ÃÊ±âÈ­
void init()
{
	FASTIO
		cin >> n >> m;
	//adj = vector<vector<pii>>(n + 1, vector<pii>(n + 1));
	for (int i = 0; i < n - 1; ++i)
	{
		int u, v, c;
		cin >> u >> v >> c;
		adj[u].push_back({ v, c });
		adj[v].push_back({ u, c });
	}
}
int getMiniVideo(int k, int v)
{
	int ans = 0;
	vector<bool> visited(5001, false);
	queue<int> q;
	q.push(v);
	visited[v] = 1;
	while (!q.empty())
	{
		int cur = q.front();
		q.pop();
		for (int i = 0; i < adj[cur].size(); ++i)
		{
			int next = adj[cur][i].first;
			int relev = adj[cur][i].second;
			if (!visited[next] && k <= relev) {
				visited[next] = 1;
				q.push(next);
				ans++;
			}
		}
	}
	return ans;
}
void calc()
{
	int v, k;
	while (m-- > 0)
	{
		cin >> k >> v;
		cout << getMiniVideo(k, v) << '\n';
	}
}
int main()
{
	init();
	calc();
}