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

	// ��ȿ �Ⱦ���� ��� �տ������� ���ذ�
	calc(idx + 2, value);

	if (idx + 2 < n)
	{
		value = apply(line[idx] - '0', line[idx + 2] - '0', line[idx + 1]); // ���粨�� �������� ��ȣ �����
		value = apply(sum, value, op); // ���������� �հ� ������
		calc(idx + 4, value); // �������� ���ڸ� ã��.
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
* string���� ¥��
* idx�� + 4 �� ������Ű�鼭
* ���� idx�� ���� (������) �� ���� ���� ���� ���� ��, �ش� ���ڿ��� �� �ڸ��� �ٲ�ġ����.
* ��ȣ �Ⱦ���� �״�� �ٽ� �Ѱ��ְ� idx + 1
*
* ��ȣ ����� idx + 4
* �Ⱦ���� idx + 1
s*/