#include<iostream>
#include<vector>
#include<tuple>
#include<ctime>
#include<random>
#include<functional>
#include<chrono>
using namespace std;
using namespace std::chrono;
tuple<int, int, int> find_max_crossing_subarray(vector<int> numbers, int first,
	int mid, int last) {
	int sum = 0, max_left_sum = INT_MIN, max_left_index = mid;
	for (int i = mid; i >= first; i--) {
		sum += numbers[i];
		if (sum > max_left_sum) {
			max_left_sum = sum;
			max_left_index = i;
		}
	}
	sum = 0;
	int max_right_sum = INT_MIN, max_right_index = mid + 1;
	for (int i = mid + 1; i <= last; i++) {
		sum += numbers[i];
		if (sum > max_right_sum) {
			max_right_sum = sum;
			max_right_index = i;
		}
	}
	return make_tuple(max_left_index, max_right_index, max_left_sum +
		max_right_sum);
}
tuple<int, int, int> find_max_subarray_brute(vector<int> numbers, int first, int last) {
	tuple<int, int, int> max_subarray;
	int max_sum = INT_MIN;
	for (int i = first; i <= last; i++) {
		int sum = 0;
		for (int j = i; j <= last; j++) {
			sum += numbers[j];
			if (sum > max_sum) {
				max_sum = sum;
				max_subarray = make_tuple(i, j, max_sum);
			}
		}
	}
	return max_subarray;
}
tuple<int, int, int> find_max_subarray(vector<int> numbers, int first, int last) {
	//if (first == last) {
	//	return make_tuple(first, last, numbers[first]);
	//}
	if (last - first <= 50) {
		return find_max_subarray_brute(numbers, first, last);
	}
	else {
		int mid = first + (last - first) / 2;
		auto tuple1 = find_max_subarray(numbers, first, mid);
		auto tuple2 = find_max_subarray(numbers, mid + 1, last);
		auto tuple3 = find_max_crossing_subarray(numbers, first, mid, last);
		if (get<2>(tuple1) >= get<2>(tuple2) && get<2>(tuple1) >= get<2>(tuple3))
			return tuple1;
		else if (get<2>(tuple2) >= get<2>(tuple1) && get<2>(tuple2) >= get<2>(tuple3))
			return tuple2;
		else
			return tuple3;
	}
}
tuple<int, int, int> find_max_subarray_dp(vector<int> numbers, int first, int last) {
	int max_sum = 0, current_sum = 0;
	int max_index_begin = 0, max_index_end = 0;
	int index_begin = 0, index_end = 0;
	for (int i = first; i <= last; i++) {
		index_end = i;
		current_sum += numbers[i];
		if (current_sum < 0) {
			current_sum = 0;
			index_begin = i + 1;
			//index_end = i + 1;
		}
		else if (current_sum > max_sum) {
			max_sum = current_sum;
			max_index_begin = index_begin;
			max_index_end = index_end;
		}
	}
	return make_tuple(max_index_begin, max_index_end, max_sum);
	
}
int main() {¡¢
	cout << log(143640)/log(70) << endl;
	vector<int> numbers{ 13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
	//vector<int> numbers{-2,-3,-5,-1,-4};
	const int SIZE = 10000;
	//vector<int> numbers{ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,4,5 };
	auto result = find_max_subarray_dp(numbers, 0, numbers.size()-1);
	cout << get<0>(result) << " " << get<1>(result) << " " << get<2>(result) << endl;
	//auto die = bind(uniform_int_distribution<>{-65536, 65535},
	//	default_random_engine{});
	//cout << die() << endl;
	//int k_array[] = { 10,20,50,70,100,120,150,200,500,1000,2000,5000,10000 };
	//int k_array[] = { 30,70,80,90,100,120,150,200,220,240,260,280,300,400,500 };
	int k_array[] = { 50000 };
	for (int k : k_array) {
		numbers.clear();
		for (int i = 0; i < k; i++) {
			auto die = bind(uniform_int_distribution<>{-256, 255},
				default_random_engine{});
			numbers.push_back(die());
		}
		double duration1 = 0, duration2 = 0,duration3=0;
		auto start_time3 = high_resolution_clock::now();
		auto tuple3 = find_max_subarray_dp(numbers, 0, numbers.size() - 1);
		auto end_time3 = high_resolution_clock::now();
		duration3 += duration_cast<milliseconds>(end_time3 - start_time3).count();
		cout << duration3 << endl;
		for (int i = 0; i < 1; i++) {
			
			auto start_time1 = high_resolution_clock::now();
			auto tuple1=find_max_subarray_brute(numbers, 0, numbers.size() - 1);
			auto end_time1 = high_resolution_clock::now();
			duration1+=duration_cast<milliseconds>(end_time1-start_time1).count();
			auto start_time2 = high_resolution_clock::now();
			auto tuple2=find_max_subarray(numbers, 0, numbers.size() - 1);
			auto end_time2 = high_resolution_clock::now();
			duration2 += duration_cast<milliseconds>(end_time2-start_time2).count();
			auto start_time3 = high_resolution_clock::now();
			auto tuple3=find_max_subarray_dp(numbers, 0, numbers.size() - 1);
			auto end_time3 = high_resolution_clock::now();
			duration3 += duration_cast<milliseconds>(end_time3-start_time3).count();
			if (tuple1 != tuple3)
				cout << "error: " << "<" << get<0>(tuple1) << "," << get<1>(tuple1) << ","
				<< get<2>(tuple1)<<"> " << "<" << get<0>(tuple1) << "," << get<1>(tuple1) << ","
				<< get<2>(tuple1) <<">"<< endl;
		}
		cout << k << "  " << "brute: " << duration1 << "   " << "divide_and_conquer: " <<
			duration2 <<"   dp: "<<duration3<< endl;

	}

	//auto result = find_max_subarray_brute(numbers, 0, numbers.size()-1);
	//cout << get<0>(result) << " " << get<1>(result) << " " << get<2>(result) << endl;
}