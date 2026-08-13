Map<Character, Integer> map = new HashMap<>();
        if (t.length() != s.length())
            return false;

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch))
                map.put(ch, map.get(ch) +1);
            
            else 
                map.put(ch, 1);
        }
        
        for (char ch : t.toCharArray()) {
           if (!map.containsKey(ch) || map.get(ch) == 0)
               return false;
           
           map.put(ch, map.get(ch) - 1);
        }
        return true;
