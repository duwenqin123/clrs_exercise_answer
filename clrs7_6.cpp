#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
using namespace std;
//示例程序：6,5,7,4,6,8,6
/*如果我们仅仅重叠就视为相等，考虑下面情况，<1,4>,<3,5>,<1,2>，按重叠就相等，
	则三者视为相等，无所谓最后的先后顺序。但是显然没法从三个区间取出满足
	c1<=c2<=c3的値。此时，需要每次有新的相等区间加进来时，合并新的区间。*/
pair<int, int> fuzzy_partion(vector<pair<int, int>>& intervals, int begin, int end) {
	pair<int, int> pivot = intervals[end];
	//把right设为end+1可以把pivot交换到中间去
	int left = begin - 1, right = end + 1;
	for (int i = begin; i <= end && i < right;) {
		if (intervals[i].second<pivot.first) {
			swap(intervals[++left], intervals[i]);
			i++;
		}
		//注意这个分支没有i++
		else if (pivot.first < intervals[i].first) {
			swap(intervals[--right], intervals[i]);
		}
		else {
			//采用上面的偏序关系则没必要合并区间。因为pivot的start始终为公共点
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