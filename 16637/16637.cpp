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
#include <climits>
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

int n, ans = INT_MIN;
string line;
void init()
{
	cin >> n >> line;
}
int apply(int a, int b, char oper)
{
	switch (oper)
	{
	case '+': return a + b;
	case '-': return a - b;
	case '*': return a * b;
	}
}

void calc(int idx, int sum)
{
	if (idx >= n)
	{
		ans = max(ans, sum);
		return;
	}
	char op = (idx >= 1 ? line[idx - 1] : '+');
	int value = apply(sum, line[idx] - '0', op);

	// 괄효 안씌우고 계속 앞에서부터 더해감
	calc(idx + 2, value);

	if (idx + 2 < n)
	{
		value = apply(line[idx] - '0', line[idx + 2] - '0', line[idx + 1]); // 현재꺼와 다음꺼에 괄호 씌우기
		value = apply(sum, value, op); // 이전까지의 합과 더해줌
		calc(idx + 4, value); // 다음다음 숫자를 찾음.
	}

}
int main()
{
	init();
	if (n == 1)
	{
		cout << line << endl;
		return 0;
	}
	calc(0, 0);
	cout << ans << endl;
}
/*
* string으로 짜서
* idx를 + 4 씩 증가시키면서
* 현재 idx의 숫자 (연산자) 그 다음 숫자 값을 구한 후, 해당 문자열의 그 자리에 바꿔치기함.
* 괄호 안씌우면 그대로 다시 넘겨주고 idx + 1
*
* 괄호 씌우면 idx + 4
* 안씌우면 idx + 1
s*/