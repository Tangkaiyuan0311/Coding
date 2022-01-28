public class Tri_stack<Item> {
    private Item[] stack;
    private int[] tops;
    Tri_stack () {
        stack = (Item []) new Object[100];
        tops = new int[3];
        tops[0] = 0;
        tops[1] = 1;
        tops[2] = 2;
    }

    // push item i into stack s
    public void add(Item i, int s) {
        if (tops[s] > 99)
            throw new IllegalArgumentException("stack is full");
        stack[tops[s]] = i;
        tops[s] += 3;
    }

    public Item pop(int s) {
        if (isEmpty(s))
            throw new IllegalArgumentException("empty stack");
        tops[s] -= 3;
        Item i = stack[tops[s]];
        stack[tops[s]] = null;
        return i;
    }

    public boolean isEmpty(int s) {
        return tops[s] == s;
    }

    public static boolean valid(String s, int j) {
        Tri_stack<Character> stack = new Tri_stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.add('(', j);
            else if (s.charAt(i) == ')')
                stack.pop(j);
        }
        return stack.isEmpty(j);
    }

    public static void main(String[] args) {
        String s = "(apple(fun)";
        assert (!valid(s, 0));
        assert (!valid(s, 1));
        assert (!valid(s, 2));
    }
}