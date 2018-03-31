#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
#include<chrono>
using namespace std;
using namespace std::chrono;
pair<int,int> my_partition_for_same(vector<int>& nums, int first, int last) {
	int l = first - 1, r = last + 1;
	int pivot = nums[last];
	for (int i = first; i <= last && i < r;) {
		if (nums[i] < pivot) {
			swap(nums[++l], nums[i]);
			i++;
		}
		else if (nums[i] > pivot) {
			swap(nums[--r], nums[i]);
		}
		else
		{
			i++;
		}
	}
	return make_pair(l, r);
}
void my_quicksort_for_same(vector<int>& nums, int l, int r) {
	if (l < r) {
		auto p = my_partition_for_same(nums, l, r);
		my_quicksort_for_same(nums, l, p.first);
		my_quicksort_for_same(nums, p.second, r);
	}
}
void my_quicksort_for_same(vector<int>& nums) {
	my_quicksort_for_same(nums, 0, nums.size() - 1);
}
int my_partition(vector<int>& nums, int l, int r) {
	int pivot = nums[l];
	int split_point = l;
	for (int i = l + 1; i <= r; i++) {
		if (nums[i] <= pivot) {
			swap(nums[++split_point], nums[i]);
		}
	}
	swap(nums[l], nums[split_point]);
	return split_point;
}
void my_quicksort(vector<int>& nums, int l, int r) {
	if (l < r) {
		int split = my_partition(nums, l, r);
		my_quicksort(nums, l, split - 1);
		my_quicksort(nums, split + 1, r);
	}
}
void my_quicksort(vector<int>& nums) {
	my_quicksort(nums, 0, nums.size() - 1);
}
void my_quicksort_with_insertionsort(vector<int>& nums, int l, int r,int k) {
	if (r-l>k) {
		int split = my_partition(nums, l, r);
		my_quicksort_with_insertionsort(nums, l, split - 1,k);
		my_quicksort_with_insertionsort(nums, split + 1, r,k);
	}
}
void insertion_sort(vector<int>& nums) {
	for (int i = 1; i < nums.size(); i++) {
		int temp = nums[i];
		int j = i - 1;
		while (j >= 0 && nums[j] > temp) {
			nums[j + 1] = nums[j];
			j--;
		}
		nums[j + 1] = temp;
	}
}
void my_quicksort_with_insertionsort(vector<int>& nums,int k) {
	my_quicksort_with_insertionsort(nums, 0, nums.size() - 1,k);
	insertion_sort(nums);
}
int Hoare_partion(vector<int>& nums, int l, int r) {
	int i = l - 1, j = r + 1;
	int pivot = nums[l];
	while (true) {
		while (nums[--j] > pivot)
			;
		while (nums[++i] < pivot)
			;
		if (i >= j)
			break;
		else
			swap(nums[i], nums[j]);
	}
	return j;

}
void Hoare_quicksort(vector<int>& nums, int l, int r) {
	if (l < r) {
		int i = Hoare_partion(nums, l, r);
		Hoare_quicksort(nums, l, i);
		Hoare_quicksort(nums, i + 1, r);
	}
}
void Hoare_quicksort(vector<int>& nums) {
	Hoare_quicksort(nums, 0, nums.size() - 1);
}
void heap_sort(vector<int>& nums) {
	make_heap(nums.begin(), nums.end());
	for (int i = 0; i < nums.size(); i++) {
		pop_heap(nums.begin(), nums.end() - i);
	}
}
void stl_heap_sort(vector<int>& nums) {
	make_heap(nums.begin(), nums.end());
	sort_heap(nums.begin(),nums.end());
}
int main() {
	const int N = 30000;
	const int R = 32768;
	vector<int> nums;
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin0 = high_resolution_clock::now();
	sort(nums.begin(), nums.end());
	auto end0 = high_resolution_clock::now();
	cout <<"STL_quicksort:"<< duration_cast<milliseconds>(end0 - begin0).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin1 = high_resolution_clock::now();
	my_quicksort(nums);
	auto end1= high_resolution_clock::now();
	cout <<"my_quicksort:"<< duration_cast<milliseconds>(end1 - begin1).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin2 = high_resolution_clock::now();
	my_quicksort_for_same(nums);
	auto end2 = high_resolution_clock::now();
	cout <<"my_quicksort_for_same:"<< duration_cast<milliseconds>(end2 - begin2).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin3 = high_resolution_clock::now();
	Hoare_quicksort(nums);
	auto end3= high_resolution_clock::now();
	cout <<"Hoare_quicksort:"<< duration_cast<milliseconds>(end3- begin3).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin4 = high_resolution_clock::now();
	my_quicksort_with_insertionsort(nums,20);
	auto end4= high_resolution_clock::now();
	cout <<"my_quicksort_with_insertionsort"<< duration_cast<milliseconds>(end4 - begin4).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin5 = high_resolution_clock::now();
	//heap_sort(nums);
	auto end5 = high_resolution_clock::now();
	cout << "my_heapsort:" << duration_cast<milliseconds>(end5 - begin5).count() << endl;
	nums.clear();
	for (int i = 0; i < N; i++) {
		nums.push_back(rand() % R);
	}
	auto begin6 = high_resolution_clock::now();
	stl_heap_sort(nums);
	auto end6 = high_resolution_clock::now();
	cout << "stl_heapsort:" << duration_cast<milliseconds>(end6 - begin6).count() << endl;
}