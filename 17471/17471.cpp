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
int n;
bool visited[11];
vec2 adj;
vec population;
// 초기화
void init()
{
	FASTIO
		cin >> n;
	population = vec(n + 1, 0);
	adj = vec2(n + 1);
	for (int i = 1; i <= n; i++)
		cin >> population[i];
	for (int i = 1; i <= n; i++)
	{
		int cnt, district; cin >> cnt;
		FOR(j, cnt)
		{
			cin >> district;
			adj[i].push_back(district);
			adj[district].push_back(i);
		}
	}
}
bool isConn(const vec& a, const vec& b)
{
	unordered_set<int> ua, ub;
	for (auto& elem : a) ua.insert(elem);
	for (auto& elem : b) ub.insert(elem);

	queue<int> aq, bq;
	aq.push(a[0]); bq.push(b[0]);
	memset(visited, 0, sizeof(visited));
	visited[a[0]] = true;
	while (!aq.empty())
	{
		int here = aq.front(); aq.pop();
		for (auto& elem : adj[here])
		{
			if (!visited[elem] && ub.find(elem) == ub.end()) // find(a.begin(), a.end(), elem) != a.end() 으로도 가능!!!
			{
				visited[elem] = true;
				aq.push(elem);
			}
		}
	}
	for(auto& elem: a) 
		if (!visited[elem]) 
			return false;

	memset(visited, 0, sizeof(visited));
	visited[b[0]] = true;
	while (!bq.empty())
	{
		int here = bq.front(); bq.pop();
		for (auto& elem : adj[here])
		{
			if (!visited[elem] && ua.find(elem) == ua.end())
			{
				visited[elem] = true;
				bq.push(elem);
			}
		}
	}
	for (auto& elem : b)
		if (!visited[elem])
			return false;
	return true;
}
int seperate(vec a, vec b, int idx)
{
	if (idx > n)
	{
		if (a.size() == 0 || b.size() == 0 || !isConn(a, b)) 
			return INF;
		int asum = 0, bsum = 0;
		for (auto& elem : a) asum += population[elem];
		for (auto& elem : b) bsum += population[elem];
		return abs(asum - bsum);
	}
	a.push_back(idx);
	int minValue = seperate(a, b, idx + 1);
	a.pop_back();
	b.push_back(idx);
	return min(minValue, seperate(a, b, idx + 1));
}
void calc()
{
	vec a, b;
	int ans = seperate(a, b, 1);
	cout << (ans == INF ? -1 : ans ) << endl;
}
int main()
{
	init();
	calc();
}
/*
* find(vec.begin(), vec.end(), 값)
* 
비트셋으로 조합구하는 방법
for(int m = 0; m < (1 << n); ++m){
		vector<int> vec1, vec2;
		int k = m;
		for(int i = 1; i <= n; ++i){
			if(k % 2 == 1){
				vec1.push_back(i);
			}else{
				vec2.push_back(i);
			}
			k /= 2;
		}
		int sum1 = 0, sum2 = 0;
		bool conn1 = connected(amt, adj, vec1, n, sum1);
		bool conn2 = connected(amt, adj, vec2, n, sum2);
		if(conn1 && conn2 && ret > abs(sum1 - sum2)){
			ret = abs(sum1 - sum2);
		}
	}
*/
/*
(입력)
3
1 2 1
1 2
2 1 3
1 2
(출력)
2
-------------------
(입력)
5
5 2 3 4 1
1 2
4 1 3 5 4
1 2
1 2
1 2
(출력)
5
*/