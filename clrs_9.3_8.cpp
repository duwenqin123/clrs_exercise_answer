#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
using namespace std;
int find_median_of_two_sorted_array(vector<int> nums1,int first1,int last1,
	vector<int> nums2,int first2,int last2) {
	int len = last1-first1+1;
	if (len == 1) {
		return min(nums1[first1], nums2[first2]);
	}
	int ma = nums1[first1+(len -1)/2];
	int mb = nums2[first2+(len -1)/2];
	if (len % 2 == 0) {
		if (ma == mb)
			return ma;
		else if (ma < mb)
			return find_median_of_two_sorted_array(nums1, first1 + len / 2, last1,
				nums2, first2, first2 + len / 2 - 1);
		else
		{
			return find_median_of_two_sorted_array(nums1, first1, first1 + len / 2 - 1,
				nums2, first1 + len / 2, last1);
		}
	}
	else
	{
		if (ma == mb)
			return ma;
		else if (ma < mb) {
			return find_median_of_two_sorted_array(nums1, first1 + len / 2, last1,
				nums2, first2, first2 + len / 2 );
		}
		else {
			return find_median_of_two_sorted_array(nums1, first1, first1 + len / 2,
				nums2, first1 + len / 2, last1);
		}
	}
}
int find_median_of_two_sorted_array(vector<int> nums1, vector<int> nums2) {
	return find_median_of_two_sorted_array(nums1, 0, nums1.size() - 1, nums2, 0,
		nums2.size() - 1);
}
int main() {
	vector<int> nums1{ 1,2,3,4,7 };
	vector<int> nums2{ 2,5,6,6,8 };
	cout << find_median_of_two_sorted_array(nums1, nums2) << endl;
}