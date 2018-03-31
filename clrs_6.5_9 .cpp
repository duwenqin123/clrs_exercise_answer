#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;
struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};
struct Cmp {
	bool operator()(const ListNode* p, const ListNode* q) {
		return p->val > q->val;
	}
};
ListNode* mergeKLists(vector<ListNode*>& lists) {
	priority_queue<ListNode*, vector<ListNode*>, Cmp> heap;
	ListNode dummy{ -1 };
	ListNode* pre = &dummy;
	for (ListNode* p : lists) {
		if (p)
			heap.push(p);
	}
	while (!heap.empty()) {
		ListNode* cur = heap.top();
		heap.pop();
		if (cur->next)
			heap.push(cur->next);
		pre->next = cur;
		pre = cur;
	}
	return dummy.next;
}
int main() {
	
}