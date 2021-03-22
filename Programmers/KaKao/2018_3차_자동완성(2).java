import java.util.HashMap;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        
        Trie root = new Trie();
        root.count = Integer.MAX_VALUE;
        
        for(String word : words) {
            Trie tempTrie = root;
            int wordLength = word.length();
            for(int idx = 0; idx < wordLength; idx++) {
                char tempChar = word.charAt(idx);
                if(tempTrie.map.containsKey(tempChar)) {
                    tempTrie = tempTrie.map.get(tempChar);
                    tempTrie.count++;
                }
                else {
                    Trie trie = new Trie();
                    tempTrie.map.put(tempChar, trie);
                    tempTrie = trie;
                    tempTrie.count++;
                }
            }
        }
        
        for(String word : words) {
            Trie tempTrie = root;
            int wordLength = word.length();
            for(int idx = 0; idx < wordLength; idx++) {
                if(tempTrie.count == 1) {
                    answer += idx;
                    break;
                }
                
                char tempChar = word.charAt(idx);
                tempTrie = tempTrie.map.get(tempChar);
                if(idx == wordLength - 1) {
                    answer += idx + 1;
                }
            }
        }
        
        return answer;
    }
    
    class Trie {
        HashMap<Character, Trie> map;
        int count;
        Trie() {
            this.map = new HashMap<>();
            this.count = 0;
        }
    }
}
