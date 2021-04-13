import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static int cardSize;
    static Card[][] cardBoard;
    static boolean[][] exist;
    static boolean[] backTrack;
    static ArrayList<ArrayList<Card>> permutation;
    static ArrayList<CardSet> cardSetList;
    static int cardSetSize;

    public int solution(int[][] board, int r, int c) {
        cardBoard = new Card[4][4];
        exist = new boolean[4][4];
        cardSetList = new ArrayList<>();
        permutation = new ArrayList<>();

        HashMap<Integer, Card> findSetMap = new HashMap<>();

        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                if(board[y][x] != 0) {
                    Card card = new Card(x, y, 0, 0, board[y][x]);
                    cardSize++;
                    if(findSetMap.containsKey(board[y][x])) {
                        Card set = findSetMap.get(board[y][x]);
                        set.setx = x;
                        set.sety = y;
                        card.setx = set.x;
                        card.sety = set.y;
                        CardSet cset = new CardSet(card, set);
                        cardSetList.add(cset);
                        findSetMap.remove(set);
                    }
                    else {
                        findSetMap.put(board[y][x], card);
                    }
                    cardBoard[y][x] = card;
                    exist[y][x] = true;
                }
            }
        }

        cardSetSize = cardSetList.size();
        backTrack = new boolean[cardSetSize];
        setPermutation(new ArrayList<>());

        int minControl = Integer.MAX_VALUE;
        for (ArrayList<Card> list : permutation) {
            int controlSum = 0;
            int x = c;
            int y = r;
            for (int idx = 0; idx < cardSize; idx++) {
                exist[list.get(idx).y][list.get(idx).x] = true;
            }

            for (int idx = 0; idx < cardSize; idx++) {
                Card card = list.get(idx);
                Queue<Loc> queue = new LinkedList<>();
                queue.add(new Loc(x, y));

                int phase = 0;
                outside : while (!queue.isEmpty()) {
                    int phase_count = 0;
                    int phase_size = queue.size();
                    while(phase_count < phase_size) {
                        Loc loc = queue.poll();
                        if(loc.x == card.x && loc.y == card.y) { break outside; }

                        // left
                        if(loc.x - 1 >= 0)
                            queue.add(new Loc(loc.x - 1, loc.y));
                        // ctrl + left
                        if(loc.x - 3 >= 0 && !exist[loc.y][loc.x - 2] && !exist[loc.y][loc.x - 1])
                            queue.add(new Loc(loc.x - 3, loc.y));
                        else if(loc.x - 2 >= 0 && !exist[loc.y][loc.x - 1])
                            queue.add(new Loc(loc.x - 2, loc.y));

                        // right
                        if(loc.x + 1 < 4)
                            queue.add(new Loc(loc.x + 1, loc.y));
                        // ctrl + right
                        if(loc.x + 3 < 4 && !exist[loc.y][loc.x + 2] && !exist[loc.y][loc.x + 1])
                            queue.add(new Loc(loc.x + 3, loc.y));
                        else if(loc.x + 2 < 4 && !exist[loc.y][loc.x + 1])
                            queue.add(new Loc(loc.x + 2, loc.y));

                        // top
                        if(loc.y - 1 >= 0)
                            queue.add(new Loc(loc.x, loc.y - 1));
                        // ctrl + top
                        if(loc.y - 3 >= 0 && !exist[loc.y - 2][loc.x] && !exist[loc.y - 1][loc.x])
                            queue.add(new Loc(loc.x, loc.y - 3));
                        else if(loc.y - 2 >= 0 && !exist[loc.y - 1][loc.x])
                            queue.add(new Loc(loc.x, loc.y - 2));

                        // down
                        if(loc.y + 1 < 4)
                            queue.add(new Loc(loc.x, loc.y + 1));
                        // ctrl + down
                        if(loc.y + 3 < 4 && !exist[loc.y + 2][loc.x] && !exist[loc.y + 1][loc.x])
                            queue.add(new Loc(loc.x, loc.y + 3));
                        else if(loc.y + 2 < 4 && !exist[loc.y + 1][loc.x])
                            queue.add(new Loc(loc.x, loc.y + 2));

                        phase_count++;
                    }
                    phase++;
                }
                controlSum += phase;
                x = card.x;
                y = card.y;
                exist[y][x] = false;
            }
            minControl = Math.min(controlSum, minControl);
        }

        return minControl + cardSize;
    }

    public void setPermutation(ArrayList<Card> list) {
        if(list.size() == cardSize) {
            permutation.add(list);
        }

        for(int idx = 0; idx < cardSetSize; idx++) {
            if(!backTrack[idx]) {
                backTrack[idx] = true;
                ArrayList<Card> list1 = new ArrayList<>(list);
                ArrayList<Card> list2 = new ArrayList<>(list);
                CardSet cset = cardSetList.get(idx);
                list1.add(cset.c1);
                list1.add(cset.c2);
                setPermutation(list1);
                list2.add(cset.c2);
                list2.add(cset.c1);
                setPermutation(list2);
                backTrack[idx] = false;
            }
        }
    }

    class Card {
        int x, y, setx, sety, cardnum;

        Card(int x, int y, int setx, int sety, int cardnum) {
            this.x = x;
            this.y = y;
            this.setx = setx;
            this.sety = sety;
            this.cardnum = cardnum;
        }
    }

    class CardSet {
        Card c1, c2;

        CardSet(Card c1, Card c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }

    class Loc {
        int x, y;
        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
