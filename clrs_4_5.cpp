#include<iostream>
#include<vector>
#include<cstdlib>
#include<ctime>
#include<algorithm>
using namespace std;
//srand((unsigned)time(nullptr));
bool detect_report(bool chip1, bool chip2) {
	if (chip1)
		return chip2;
	else
	{
		return (bool)(rand() % 2);
	}
}
int one_good_chip_dectect(vector<pair<int, bool>> chips) {
	/*若有2块芯片，由于好的芯片超过一半，故都是好的芯片*/
	if (chips.size() <= 2) {
		return chips[0].first;
	}
	int half = chips.size() / 2;
	vector<pair<int, bool>> next_chips;
	for (int i = 0; i < half; i++) {
		pair<bool, bool> detect;
		detect.first = detect_report(chips[2 * i + 1].second, chips[2 * i].second);
		detect.second= detect_report(chips[2 * i].second, chips[2 * i+1].second);
		if (detect.first && detect.second)
			next_chips.push_back(chips[2 * i]);
	}
	
	if (chips.size() % 2 != 0 && next_chips.size() % 2 == 0)
		next_chips.push_back(chips.back());
	return one_good_chip_dectect(next_chips);
}
int main() {
	cout << log(2) <<" "<< log(365 / 364) << endl;
	vector<bool> chips{ false,true,true,true,false,true,false,true,false,false,true };
	for (int j = 0; j < 1000; j++) {
		random_shuffle(chips.begin(), chips.end());
		vector<pair<int, bool>> arr;
		for (int i = 0; i < chips.size(); i++) {
			arr.push_back(make_pair(i, chips[i]));
		}
		int index = one_good_chip_dectect(arr);
		if (!chips[index])
			cout << "error!" << endl;
	}
}