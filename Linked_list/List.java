import java.util.HashSet;
import java.util.Stack;

public class List {
    private static class Node {
        int key;
        Node next;
    }
    private Node head;
    private static Node i;
    List() {}
    List(int[] items) {
        this.head = new Node();
        head.key = items[0];
        Node node = head;
        for (int i = 1; i < items.length; i++) {
            node.next = new Node();
            node = node.next;
            node.key = items[i];
        }

    }
    public static List sum(List a, List b) {
        Node i = a.head;
        Node j = b.head;
        int carry = 0;
        Node head = new Node(); // fake head
        head.key = -1;
        Node tail = head;
        int digit_sum = 0;
        while (i != null && j != null) {
           digit_sum = i.key + j.key + carry;
           if (digit_sum > 9) {
               carry = 1;
               digit_sum -= 10;
           }
           else {
               carry = 0;
           }
           Node cur = new Node();
           cur.key = digit_sum;
           tail.next = cur;
           tail = tail.next;

           i = i.next;
           j = j.next;
        }
        if (i != null) {
            digit_sum = i.key + carry;
        }
        else if (j != null) {
            digit_sum = j.key + carry;
        }
        else {
            digit_sum = carry;
        }
        if (digit_sum > 9) {
            digit_sum -= 10;
            Node se = new Node();
            se.key = digit_sum;
            Node fi = new Node();
            fi.key = 1;
            tail.next = se;
            se.next = fi;

        }
        else {
            if (digit_sum != 0) {
                Node fi = new Node();
                fi.key = digit_sum;
                tail.next = fi;
            }
        }
        /*
        if (carry == 1) {
            Node cur = new Node();
            cur.key = 1;
            tail.next = cur;
        }
        */
        List ret = new List();
        ret.head = head.next;
        return ret;
    }

    // recursive
    // return sum of a+b, one more digit (0/1)
    // base case: a = null, b = null;
    private static Node sum (Node a, Node b) {
        if (a == null && b == null) {
            Node head = new Node();
            head.key = 0;
            return head;
        }
        Node sum = sum (a.next, b.next);
        int sum_digit = a.key + b.key + sum.key;
        if (sum_digit > 9) {
            Node head = new Node();
            head.key = 1;
            sum.key = sum_digit - 10;
            head.next = sum;
            return head;
        }
        else {
            Node head = new Node();
            head.key = 0;
            sum.key = sum_digit;
            head.next = sum;
            return head;
        }
    }
    // a and b is not necessarily the same length
    private static List re_sum(List a, List b) {
        Node i = a.head;
        Node j = b.head;
        Node li;
        List sr;
        while (i != null && j != null) {
            i = i.next;
            j = j.next;
        }
        li = (i == null) ? j : i;
        sr = (i == null) ? a : b;

        // li can be null
        while (li != null) {
            Node oldHead = sr.head;
            sr.head = new Node();
            sr.head.key = 0;
            sr.head.next = oldHead;
            li = li.next;
        }
        List ret = new List();
        Node re = sum(a.head, b.head);
        if (re.key == 0)
            ret.head = re.next;
        else
            ret.head = re;
        return ret;
    }


    public String toString() {
        Node e;
        StringBuilder bu = new StringBuilder();
        for (e = head; e != null; e = e.next) {
            bu.append(String.valueOf(e.key) + " ");
        }
        return bu.toString();
    }

    // k th element to the end of list
    public int find(int k) {
        Node e = head;
        Node kth = null;
        int counter = 0;
        boolean find = false;
        while (e.next != null) {
            if (!find) {
                if (counter == k) {
                    kth = head;
                    find = true;
                }
                else {
                    e = e.next;
                    counter++;
                }
            }
            if (find) {
                e = e.next;
                kth = kth.next;
            }
        }
        if (!find)
            throw new IllegalArgumentException("k too large");
        return kth.key;
    }

    public int len() {
        Node e;
        int cnt = 0;
        for (e = head; e != null; e = e.next) {
            cnt++;
        }
        return cnt;
    }
    private Node index(int k) {
        Node e;
        int cnt = -1;
        for (e = head; e != null; e = e.next) {
            cnt++;
            if (cnt == k)
                return e;
        }
        return null;
    }

    // invariant: i sym j-1 on true return to be ready for j.key == i.key
    private boolean check(Node j) {
        if (j.next == null) {
            i = head.next;
            return head.key == j.key;
        }
        if (!check(j.next))
            return false;
        else {
            if (j.key == i.key) {
                i = i.next;
                return true;
            }
            else
                return false;
        }
    }
    public boolean isPalin() {
        int len = len();
        int j;
        if (len % 2 == 0)
            j = len / 2;
        else
            j = (len-1)/2 + 1;
        return check(index(j));
    }

    private static class Result {
        boolean match;
        Node end;
        Result(boolean match, Node end) {
            this.end = end;
            this.match = match;
        }
    }
    private Result check_palin(Node start, int width) {
        // base case
        if (width == 2) {
            if (start.key == start.next.key)
                return new Result(true, start.next);
            else
                return new Result(false, null);
        }
        if (width == 1) {
            return new Result(true, start);
        }

        Result re = check_palin(start.next, width-2);
        if (re.match == true && start.key == re.end.next.key)
            return new Result(true, re.end.next);
        else
            return new Result(false, null);
    }
    public boolean isPalin_re() {
        Result re = check_palin(head, len());
        return re.match;
    }
    public boolean isPalin_stack() {
        int len = len();
        Node j;
        Node e = head;
        Stack<Integer> s = new Stack<Integer>();
        if (len % 2 == 0) {
            for (int i = 0; i < len/2; i++) {
                s.add(e.key);
                e = e.next;
            }
            j = e;
        }
        else {
            for (int i = 0; i < (len-1)/2; i++) {
                s.add(e.key);
                e = e.next;
            }
            j = e.next;
        }
        while (j != null) {
            if (s.pop() != j.key)
                return false;
            j = j.next;
        }
        return true;
    }

    
    private void delete (Node x) {
        /*
        while (true) {
            x.key = x.next.key;
            if (x.next.next == null) {
                x.next = null;
                break;
            }
            x = x.next;    
        }
        */
        x.key = x.next.key;
        x.next = x.next.next;
    }
    private Node get (int i) {
        Node e;
        int cnt = 0;
        for (e = head; e != null; e = e.next) {
            if (cnt == i)
                return e;
            cnt++;
        }
        return null;
    }

    public void partition (int v) {
        // partition in place
        Node lt = null; // head->~->lt : <
        Node e = head; // e->... unexamined
        Node prev = null;
        while (e != null) {
            if (e.key < v) {
                Node tmp = e.next;
                // move e out
                if (prev == null) {
                    // e is the first item
                    lt = e;
                    prev = e;
                    e = e.next;
                    continue;
                }
                prev.next = e.next;

                // insert e
                if (lt == null) {    
                    // move e into front, update head and lt
                    Node oldhead = head;
                    head = e;
                    lt = e;
                    head.next = oldhead;  
                }
                else {
                    // insert e
                    e.next = lt.next;
                    lt.next = e;
                    lt = e;
                }
                // update e and prev
                e = tmp;
            }
            else {
                prev = e;
                e = e.next;
            }
        }
    }



    public void remove_dup () {
        HashSet<Integer> table = new HashSet<Integer>();
        Node e;
        Node prev = null;
        for (e = this.head; e != null; e = e.next) {
            if (!table.add(e.key)) {
                if (prev != null)
                    prev.next = e.next;
            }
            else
                prev = e;
        }
    }

    // compare a and b
    // return the last Node that is the same
    // null if diff on the first
    private static Node compare(Stack<Node> a, Stack<Node> b) {
        // a and b is not empty
        Node prev = a.pop();
        if (prev != b.pop())
            return null;
        while ((!a.isEmpty()) && (!b.isEmpty())) {
            Node tmp = a.pop();
            if (tmp != b.pop()) {
                return prev;
            }
            else
                prev = tmp;
        }
        return prev;
    }
    public static Node intersect(List a, List b) {
        // push a on statck
        Stack<Node> sa = new Stack<Node>();
        Stack<Node> sb = new Stack<Node>();
        for (Node i = a.head; i != null; i = i.next)
            sa.add(i);
        for (Node j = b.head; j != null; j = j.next)
            sb.add(j);
        if (sa.isEmpty() || sb.isEmpty())
            return null;
        return compare(sa, sb);
        
    }
    /*
    public static Node circular(List a) {
        Node e;
        HashSet<Node> s = new HashSet<Node>();
        for (e = a.head; e != null; e = e.next) {
            if (!s.add(e))
                return e;
        }
        return null;
    }
    */
    public static Node circular(List a) {
        Node slow = a.head.next;
        Node fast = a.head.next.next;
        boolean meet = false;
        while (slow != null && fast != null) {
            if (slow != fast) {
                slow = slow.next;
                fast = fast.next.next;
            }
            else {
                meet = true;
                break; // first meet
            }
        }
        if (!meet)
            return null;
        else {
            // begin point
            Node be = a.head;
            while (be != slow) {
                be = be.next;
                slow = slow.next;
            }
            return be;
        }
    }

    public static void main(String[] args) {
        /*
        int[] a = {1, 2, 3, 4, 5, 4, 5, 4, 3, 0};
        var ls = new List(a);
        ls.remove_dup();
        System.out.println(ls);
        */
        /*
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        var ls = new List(a);
        assert (ls.find(0) == 10);
        assert (ls.find(2) == 8);
        assert (ls.find(9) == 1);
        ls.delete(ls.get(8));
        System.out.println(ls);
        ls.find(20);
        */
        /*
        int[] a = {3, 5, 8, 5, 10, 2, 1, 5, 6, 0, 4, 9};
        var ls = new List(a);
        ls.partition(7);
        System.out.println(ls);
        */
        /*
        int[] a = {6, 6, 9, 6};
        int[] b = {5, 9, 2};
        System.out.println(List.re_sum(new List(a), new List(b)));
        */
        /*
        int[] a = {0, 1, 2, 3, 2, 1, 2};
        List ls = new List(a);
        if (ls.isPalin_re())
            System.out.println("YES");
        else
            System.out.println("NO");
        */
        /*
        int[] a = {0, 1, 2};
        List ls = new List(a);
        var node1 = new Node();
        node1.key = -1;
        node1.next = ls.head;
        var node2 = new Node();
        node2.key = -1;
        node2.next = ls.head;
        List la = new List();
        la.head = node1;
        List lb = new List();
        lb.head = node2;
        assert (intersect(la, lb).key == 0);
        */
        
        
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        List ls = new List(a);
        Node tail = ls.get(ls.len()-1);
        tail.next = ls.get(3);
        assert (circular(ls).key == 3);
        
        

    


    }

}
