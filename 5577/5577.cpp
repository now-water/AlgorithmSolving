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
vector<pii> arr;
map<vec, int> visited;
// 초기화
void init()
{
	FASTIO
		cin >> n;
	int color;
	FOR(i, n)
	{
		cin >> color;
		// 서로 인접한 같은 색의 공들을 하나의 집합으로 본다.
		if (arr.size() == 0 || arr.back().first != color)
			arr.push_back(pii(color, 1));
		else arr.back().second += 1;
	}
}
// 공이 터지는 경우에만 계속해서 반복되는 함수.
int boom(const vector<pii>& balls, int left, int right)
{
	if (!balls[left].second) left -= 1;
	if (!balls[right].second) right += 1;
	int ret = 0;
	while (left >= 0 && right < balls.size())
	{
		// 인접한 두 그룹의 공들의 색이 같다면, 4개 이상일 경우 터진다.
		if (balls[left].first == balls[right].first)
		{
			int sum = balls[left].second + balls[right].second;
			if (sum < 4) break;
			ret += sum;
			left -= 1;
			right += 1;
		}
		else if (balls[left].second >= 4) // 왼쪽 그룹의 공이 4개 이상일 경우 터진다.
		{
			ret += balls[left].second;
			left -= 1;
		}
		else if (balls[right].second >= 4) // 오른쪽 그룹의 공이 4개 이상일 경우 터진다.
		{
			ret += balls[right].second;
			right += 1;
		}
		else break;
	}
	while (left >= 0 && balls[left].second >= 4)
		ret += balls[left--].second;

	while (right < balls.size() && balls[right].second >= 4)
		ret += balls[right++].second;

	return ret;
}
void calc()
{
	int pang = INF;
	for (int i = 1; i < arr.size(); i++)
	{
		// 현재 공을 이전 그룹의 색깔로 바꿈
		arr[i - 1].second += 1;
		arr[i].second -= 1;
		pang = min(pang, n - boom(arr, i-1, i));
		arr[i - 1].second -= 1; // 원상태로 돌린다.
		arr[i].second += 1;
		if (i < arr.size() - 1)
		{
			// 현재 공을 다음 그룹의 색깔로 바꿈
			arr[i].second -= 1;
			arr[i + 1].second += 1;
			pang = min(pang, n - boom(arr, i, i + 1));
			arr[i].second += 1; // 원상태로 돌린다.
			arr[i + 1].second -= 1;
		}
	}
	cout << pang<< endl;
}
int main()
{
	init();
	calc();
}