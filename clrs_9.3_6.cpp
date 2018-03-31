#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
using namespace std;
void k_divide_points(vector<int> nums, int first, int last, int k, vector<int>& result) {
	int len = last - first + 1;
	if (k == 1)
		return;
	else if (k == 2) {
		result.push_back(nums[first+len / 2 - 1]);
		return;
	}
	if (k % 2 == 0) {
		int divide1 = (len / k)*(k / 2);
		int divide2 = (len / k)*(k / 2 + 1);
		k_divide_points(nums, first, first + divide1 - 1,k/2,result);
		result.push_back(nums[first + divide1 - 1]);
		result.push_back(nums[first + divide2 - 1]);
		k_divide_points(nums, first + divide2, last, k / 2-1, result);
	}
	else {
		int divide = (len / k)*(k / 2 + 1);
		k_divide_points(nums, first, first + divide - 1, k / 2 + 1, result);
		result.push_back(nums[first+divide - 1]);
		k_divide_points(nums, first + divide, last, k / 2, result);
	}
}
vector<int> k_divide_points(vector<int> numbers, int k) {
	vector<int> result;
	k_divide_points(numbers, 0, numbers.size() - 1, k, result);
	return result;
}
int main() {
	vector<int> nums;
	for (int i = 0; i < 3000; i++)
		nums.push_back(i + 1);
	vector<int> result = k_divide_points(nums, 8);
	for (auto d : result)
		cout << d << " ";
	cout << endl;
}