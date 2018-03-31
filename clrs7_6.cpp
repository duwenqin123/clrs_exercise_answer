#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
using namespace std;
//ʾ������6,5,7,4,6,8,6
/*������ǽ����ص�����Ϊ��ȣ��������������<1,4>,<3,5>,<1,2>�����ص�����ȣ�
	��������Ϊ��ȣ�����ν�����Ⱥ�˳�򡣵�����Ȼû������������ȡ������
	c1<=c2<=c3�Ă�����ʱ����Ҫÿ�����µ��������ӽ���ʱ���ϲ��µ����䡣*/
pair<int, int> fuzzy_partion(vector<pair<int, int>>& intervals, int begin, int end) {
	pair<int, int> pivot = intervals[end];
	//��right��Ϊend+1���԰�pivot�������м�ȥ
	int left = begin - 1, right = end + 1;
	for (int i = begin; i <= end && i < right;) {
		if (intervals[i].second<pivot.first) {
			swap(intervals[++left], intervals[i]);
			i++;
		}
		//ע�������֧û��i++
		else if (pivot.first < intervals[i].first) {
			swap(intervals[--right], intervals[i]);
		}
		else {
			//���������ƫ���ϵ��û��Ҫ�ϲ����䡣��Ϊpivot��startʼ��Ϊ������
			//pivot.first = max(pivot.first, intervals[i].first);
			//pivot.second = min(pivot.second, intervals[i].second);
			i++;
		}
	}
	return make_pair(left,right);
}
void fuzzy_sort(vector<pair<int, int>>& intervals, int begin, int end) {
	if (begin < end) {
		pair<int, int> p = fuzzy_partion(intervals, begin, end);
		fuzzy_sort(intervals, begin,p.first);
		fuzzy_sort(intervals, p.second, end);
	}
}
void fuzzy_sort(vector<pair<int, int>>& intervals) {
	fuzzy_sort(intervals, 0, intervals.size() - 1);
}
int main() {
	vector<pair<int, int>> intervals = { make_pair(1,4),make_pair(2,5),make_pair(2,3),
		make_pair(4,6), make_pair(5,6), make_pair(1,3), make_pair(2,7) };
	fuzzy_sort(intervals);
	for (auto p : intervals) {
		cout << "<" << p.first << "," << p.second << "> ";
	}
	cout << endl;
}