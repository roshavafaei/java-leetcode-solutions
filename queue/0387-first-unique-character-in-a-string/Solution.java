class Solution {
    public int firstUniqChar(String s) {
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();

        var chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            queue.add(i);
        }

        for (var ch : chars) {

          if (map.containsKey(ch))
              map.put(ch, map.get(ch) +1);

          else
              map.put(ch, 1);
        }

        while (!queue.isEmpty() && map.get(chars[queue.peek()]) > 1)
            queue.remove();
        
        if (queue.isEmpty())
            return -1;

        return queue.peek();
    
    }
}
