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
string line;
// 초기화
void init()
{
	FASTIO
		cin >> n;
	cin >> line;
}

void calc()
{
	int ans = 0;
	int left = line[0], left_idx = 0;
	// 시작점과 끝점이 다르다면 끝 부분을 칠해줌.
	if (left != line.back()) ans = 1;
	for (int i = line.size() - 1; i >= 0 && i >= left_idx; --i)
	{
		if (line[i] == left)
		{
			ans += 1;
			left = (left == 'B' ? 'R' : 'B');
			left_idx = line.find(left, left_idx);
		}
	}
	cout << ans << endl;
}
int main()
{
	init();
	calc();
}
/*
 6
 BRRRRB
 2


 BBBRRBBBRBBBRBBBRR
6
BRBBRR
3
 */