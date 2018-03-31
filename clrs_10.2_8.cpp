#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
using namespace std;
struct ListNode {
	int val;
	int np;
	ListNode(int x) : val(x) {}
};
/*实现有些繁琐，主要是指针和int的类型转换*/
class DoubleLinkList {
private:
	ListNode* dummy, *tail;
	const int nil = reinterpret_cast<int>(nullptr);
public:
	DoubleLinkList() {
		dummy = new ListNode{ -1 };
		dummy->np = nil^nil;
		tail = dummy;
	}
	void insert(int k) {
		ListNode* newNode = new ListNode{ k };
		newNode->np = reinterpret_cast<int>(tail) ^ nil;
		tail->np = (tail->np^nil) ^ reinterpret_cast<int>(newNode);
		tail = newNode;
	}
	bool search(int k) {
		ListNode* pre = dummy;
		ListNode* cur = reinterpret_cast<ListNode*>(dummy->np^nil);
		while (cur && cur->val!=k) {
			ListNode* next = reinterpret_cast<ListNode*>(cur->np^reinterpret_cast<int>(pre));
			pre = cur;
			cur = next;
		}
		return cur == nullptr;
	}
	bool remove(int k) {
		ListNode* pre = dummy;
		ListNode* cur = reinterpret_cast<ListNode*>(dummy->np^nil);
		while (cur && cur->val != k) {
			ListNode* next = reinterpret_cast<ListNode*>(cur->np^reinterpret_cast<int>(pre));
			pre = cur;
			cur = next;
		}
		if (cur) {
			ListNode* next = reinterpret_cast<ListNode*>(cur->np^reinterpret_cast<int>(pre));
			pre->np = (pre->np^reinterpret_cast<int>(cur)) ^ reinterpret_cast<int>(next);
			if(next)
				next->np = (next->np^reinterpret_cast<int>(cur)) ^ reinterpret_cast<int>(pre);
			return true;
		}
		return false;
	}
	void reverse() {
		ListNode* head = reinterpret_cast<ListNode*>(dummy->np^reinterpret_cast<int>(nullptr));
		if (head && head != tail) {
			dummy->np = nil^reinterpret_cast<int>(tail);
			tail->np = (tail->np^nil) ^ reinterpret_cast<int>(dummy);
			head->np = (head->np^reinterpret_cast<int>(dummy)) ^ nil;
		}
	}
	void print() {
		ListNode* pre = dummy;
		ListNode* cur = reinterpret_cast<ListNode*>(dummy->np^nil);
		while (cur) {
			cout << cur->val << " ";
			ListNode* next = reinterpret_cast<ListNode*>(cur->np^reinterpret_cast<int>(pre));
			pre = cur;
			cur = next;
		}
	}
};
int main() {
	DoubleLinkList list{};
	for (int i = 0; i < 10; i++)
		list.insert(i);
	list.remove(3);
	list.reverse();
	list.print();
}