#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
#include<chrono>
using namespace std;
using namespace std::chrono;
int my_partition(vector<int>& nums, int begin, int end) {
	int pivot = nums[begin];
	int last_index = begin;
	for (int i = begin + 1; i <= end; i++) {
		if (nums[i] <= pivot) {
			swap(nums[i], nums[++last_index]);
		}
	}
	swap(nums[begin], nums[last_index]);
	return last_index;
}
int randomize_partion(vector<int>& nums, int begin, int end) {
	int index = rand() % (end - begin + 1)+begin;
	swap(nums[index], nums[begin]);
	return my_partition(nums, begin, end);
}
int randomize_select_iterate(vector<int>& nums, int begin, int end, int k) {
	while (begin < end) {
		int pivot_index = randomize_partion(nums, begin, end);
		int index = pivot_index - begin + 1;
		if (index == k)
			return nums[pivot_index];
		else if (index < k) {
			begin = pivot_index + 1;
			k -= index;
		}
		else
		{
			end = pivot_index - 1;
		}
	}
	return nums[begin];
}
int randomize_select(vector<int>& nums, int begin, int end, int k) {
	if (begin == end)
		return nums[begin];
	int pivot_index = randomize_partion(nums, begin, end);
	int index = pivot_index - begin + 1;
	if (index == k)
		return nums[pivot_index];
	else if (index < k)
		return randomize_select(nums, pivot_index + 1, end, k - index);
	else
	{
		return randomize_select(nums, begin, pivot_index - 1, k);
	}
}
int find_median(vector<int>& nums, int begin, int end) {
	//рв╢М
	sort(nums.begin()+begin, nums.begin()+end+1);
	return nums[begin+(end - begin) /2];
}
int modified_partition(vector<int>& nums, int begin, int end, int pivot) {
	int i = begin;
	while (nums[i] != pivot)
		i++;
	swap(nums[i], nums[begin]);
	return my_partition(nums, begin, end);
}
int select(vector<int>& nums, int begin, int end, int k) {
	int length = end - begin + 1;
	if (length <= 5) {
		//рв╢М
		sort(nums.begin()+begin, nums.begin()+end+1);
		return nums[begin+k - 1];
	}	
	vector<int> temp;
	for (int i = begin; i <= end; i += 5) {
		int median = (i + 4 <= end) ? find_median(nums, i, i + 4) : find_median(nums, i, end);
		temp.push_back(median);
	}
	int	median = select(temp, 0, temp.size() - 1, ceil(temp.size()/2));
	int pivot_index = modified_partition(nums, begin, end, median);
	int index = pivot_index - begin + 1;
	if (index == k)
		return nums[pivot_index];
	else if (index < k) {
		return select(nums, pivot_index + 1, end, k - index);
	}
	else
	{
		return select(nums, begin, pivot_index - 1, k);
	}
}
vector<int> k_near_medians(vector<int>& nums, int k) {
	int median = randomize_select(nums, 0, nums.size() - 1, (nums.size() + 1) / 2);
	vector<int> distances;
	for (auto d : nums) {
		distances.push_back(abs(d - median));
	}
	int kth_distance = randomize_select(distances, 0, distances.size() - 1, k);
	vector<int> result;
	for (auto d : nums) {
		if (abs(d - median) < kth_distance)
			result.push_back(d);
	}
	for (auto d : nums) {
		if (abs(d - median) == kth_distance)
			result.push_back(d);
		if (result.size() == k)
			break;
	}
	return result;
}
int main() {
	vector<int> numbers{ 6,5,7,4,3,8,2,9,11,7};
	vector<int> result = k_near_medians(numbers, 3);
	for (auto d : result)
		cout << d << " ";
	cout << endl;
	//cout << randomize_select_iterate(nums, 0, nums.size() - 1,3) << endl;;
	const int N = 100000;
	vector<int> nums;
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % 100001);
	}
	auto start1 = high_resolution_clock::now();
	randomize_select_iterate(nums, 0, nums.size()-1, N / 2);
	auto end1 = high_resolution_clock::now();
	auto duration1 = duration_cast<milliseconds>(end1 - start1).count();
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % 100001);
	}
	auto start2 = high_resolution_clock::now();
	select(nums, 0, nums.size()-1, N / 2);
	auto end2 = high_resolution_clock::now();
	auto duration2 = duration_cast<milliseconds>(end2- start2).count();
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % 100001);
	}
	auto start3 = high_resolution_clock::now();
	randomize_select(nums, 0, nums.size()-1, N / 2);
	auto end3 = high_resolution_clock::now();
	auto duration3 = duration_cast<milliseconds>(end3- start3).count();
	cout << "radomize_select_iterate: " << duration1 << endl;
	cout << "select: " << duration2 << endl;
	cout << "radomize_select: " << duration3 << endl;
}