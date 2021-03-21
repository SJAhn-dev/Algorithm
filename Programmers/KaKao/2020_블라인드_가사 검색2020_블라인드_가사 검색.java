import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static final char wildcard = '?';
    
    public int[] solution(String[] words, String[] queries) {
        int querySize = queries.length;
        int[] answer = new int[querySize];
        HashMap<String, Integer> queryCache = new HashMap<>();
        TrieRoot root = new TrieRoot();
        TrieRoot rootRev = new TrieRoot();
        
        for(String word : words) {
            int wordLength = word.length();
            Trie tempTrie = null;
            Trie tempTrieRev = null;
            
            if(root.rootMap.containsKey(wordLength)) {
                tempTrie = root.rootMap.get(wordLength);
            }
            else {
                Trie newroot = new Trie();
                root.rootMap.put(wordLength, newroot);
                tempTrie = newroot;
            }
            
            if(rootRev.rootMap.containsKey(wordLength)) {
                tempTrieRev = rootRev.rootMap.get(wordLength);
            }
            else {
                Trie newrootRev = new Trie();
                rootRev.rootMap.put(wordLength, newrootRev);
                tempTrieRev = newrootRev;
            }
            
            for(int idx = 0; idx < wordLength; idx++) {
                char tempChar = word.charAt(idx);
                char tempCharRev = word.charAt(wordLength - idx - 1);
                
                if(tempTrie.childMap.containsKey(tempChar)) {
                    tempTrie = tempTrie.childMap.get(tempChar);
                }
                else {
                    Trie trie = new Trie();
                    tempTrie.childMap.put(tempChar, trie);
                    tempTrie = trie;
                }
                
                if(tempTrieRev.childMap.containsKey(tempCharRev)) {
                    tempTrieRev = tempTrieRev.childMap.get(tempCharRev);
                }
                else {
                    Trie trieRev = new Trie();
                    tempTrieRev.childMap.put(tempCharRev, trieRev);
                    tempTrieRev = trieRev;
                }
                
                if(idx == wordLength - 1) { tempTrieRev.count++; tempTrie.count++; }
            }
        }
        
        for(int qidx = 0; qidx < querySize; qidx++) {
            String query = queries[qidx];
            int searchScore = 0;
            
            if(queryCache.containsKey(query)) {
                answer[qidx] = queryCache.get(query);
                continue;
            }
            
            if(!root.rootMap.containsKey(query.length())) {
                answer[qidx] = 0;
                continue;
            }
            
            if(query.charAt(0) == wildcard) {
                StringBuilder sb = new StringBuilder(query);
                searchScore = dfs(sb.reverse().toString(), 0, rootRev.rootMap.get(query.length()));
            }
            else {
                searchScore = dfs(query, 0, root.rootMap.get(query.length()));
            }
            
            answer[qidx] = searchScore;
            queryCache.put(query, searchScore);
        }
        
        return answer;
    }
    
    public int dfs(String query, int idx, Trie tempTrie) {
        if(idx == query.length()) {
            return tempTrie.count;
        }
        char tempChar = query.charAt(idx);
        if(tempChar == wildcard) {
            int sum = 0;
            for(Character key : tempTrie.childMap.keySet()) {
                sum += dfs(query, idx + 1, tempTrie.childMap.get(key));
            }
            return sum;
        }
        else {
            if(tempTrie.childMap.containsKey(tempChar)) {
                return dfs(query, idx + 1, tempTrie.childMap.get(tempChar));
            }
        }
        
        return 0;
    }
    
    class Trie {
        HashMap<Character, Trie> childMap;
        int count;
        
        Trie() {
            this.childMap = new HashMap<>();
            this.count = 0;
        }
    }
    
    class TrieRoot {
        HashMap<Integer, Trie> rootMap;
        TrieRoot() {
            this.rootMap = new HashMap<>();
        }
    }
}
