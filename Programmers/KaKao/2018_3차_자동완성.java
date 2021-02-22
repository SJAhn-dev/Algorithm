class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Route root = new Route();
        
        for(String word : words) {
            int wordLength = word.length();
            Route temp = root;
            for(int idx = 0; idx < wordLength; idx++) {
                char tempChar = word.charAt(idx);
                
                switch(tempChar) {
                    case 'a': 
                        if(temp.a == null) { temp.a = new Route(); }
                        temp = temp.a;
                        break;
                    case 'b': 
                        if(temp.b == null) { temp.b = new Route(); }
                        temp = temp.b;
                        break;
                    case 'c': 
                        if(temp.c == null) { temp.c = new Route(); }
                        temp = temp.c;
                        break;
                    case 'd': 
                        if(temp.d == null) { temp.d = new Route(); }
                        temp = temp.d;
                        break;
                    case 'e': 
                        if(temp.e == null) { temp.e = new Route(); }
                        temp = temp.e;
                        break;
                    case 'f': 
                        if(temp.f == null) { temp.f = new Route(); }
                        temp = temp.f;
                        break;
                    case 'g': 
                        if(temp.g == null) { temp.g = new Route(); }
                        temp = temp.g;
                        break;
                    case 'h': 
                        if(temp.h == null) { temp.h = new Route(); }
                        temp = temp.h;
                        break;
                    case 'i': 
                        if(temp.i == null) { temp.i = new Route(); }
                        temp = temp.i;
                        break;
                    case 'j': 
                        if(temp.j == null) { temp.j = new Route(); }
                        temp = temp.j;
                        break;
                    case 'k': 
                        if(temp.k == null) { temp.k = new Route(); }
                        temp = temp.k;
                        break;
                    case 'l': 
                        if(temp.l == null) { temp.l = new Route(); }
                        temp = temp.l;
                        break;
                    case 'm': 
                        if(temp.m == null) { temp.m = new Route(); }
                        temp = temp.m;
                        break;
                    case 'n': 
                        if(temp.n == null) { temp.n = new Route(); }
                        temp = temp.n;
                        break;
                    case 'o': 
                        if(temp.o == null) { temp.o = new Route(); }
                        temp = temp.o;
                        break;
                    case 'p': 
                        if(temp.p == null) { temp.p = new Route(); }
                        temp = temp.p;
                        break;
                    case 'q': 
                        if(temp.q == null) { temp.q = new Route(); }
                        temp = temp.q;
                        break;
                    case 'r': 
                        if(temp.r == null) { temp.r = new Route(); }
                        temp = temp.r;
                        break;
                    case 's': 
                        if(temp.s == null) { temp.s = new Route(); }
                        temp = temp.s;
                        break;
                    case 't': 
                        if(temp.t == null) { temp.t = new Route(); }
                        temp = temp.t;
                        break;
                    case 'u': 
                        if(temp.u == null) { temp.u = new Route(); }
                        temp = temp.u;
                        break;
                    case 'v': 
                        if(temp.v == null) { temp.v = new Route(); }
                        temp = temp.v;
                        break;
                    case 'w': 
                        if(temp.w == null) { temp.w = new Route(); }
                        temp = temp.w;
                        break;
                    case 'x': 
                        if(temp.x == null) { temp.x = new Route(); }
                        temp = temp.x;
                        break;
                    case 'y': 
                        if(temp.y == null) { temp.y = new Route(); }
                        temp = temp.y;
                        break;
                    case 'z': 
                        if(temp.z == null) { temp.z = new Route(); }
                        temp = temp.z;
                        break;
                }
                temp.value = temp.value + 1;
            }
        }
        
        for(String word : words) {
            int wordLength = word.length();
            Route temp = root;
            for(int idx = 0; idx < wordLength; idx++) {
                char tempChar = word.charAt(idx);
                
                switch(tempChar) {
                    case 'a': temp = temp.a; break;
                    case 'b': temp = temp.b; break;
                    case 'c': temp = temp.c; break;
                    case 'd': temp = temp.d; break;
                    case 'e': temp = temp.e; break;
                    case 'f': temp = temp.f; break;
                    case 'g': temp = temp.g; break;
                    case 'h': temp = temp.h; break;
                    case 'i': temp = temp.i; break;
                    case 'j': temp = temp.j; break;
                    case 'k': temp = temp.k; break;
                    case 'l': temp = temp.l; break;
                    case 'm': temp = temp.m; break;
                    case 'n': temp = temp.n; break;
                    case 'o': temp = temp.o; break;
                    case 'p': temp = temp.p; break;
                    case 'q': temp = temp.q; break;
                    case 'r': temp = temp.r; break;
                    case 's': temp = temp.s; break;
                    case 't': temp = temp.t; break;
                    case 'u': temp = temp.u; break;
                    case 'v': temp = temp.v; break;
                    case 'w': temp = temp.w; break;
                    case 'x': temp = temp.x; break;
                    case 'y': temp = temp.y; break;
                    case 'z': temp = temp.z; break;
                }
                
                if(temp.value == 1) {
                    answer += (idx + 1);
                    break;
                }
                if(idx == wordLength - 1) {
                    answer += wordLength;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public class Route {
        Route a, b, c, d, e, f, g, h, i, j, k, l, m,
                n, o, p, q, r, s, t, u, v, w, x, y, z;
        int value;
        
        public Route() {
            this.a = null; this.b = null; this.c = null; this.d = null;
            this.e = null; this.f = null; this.g = null; this.h = null;
            this.i = null; this.j = null; this.k = null; this.l = null;
            this.m = null; this.n = null; this.o = null; this.p = null;
            this.q = null; this.r = null; this.s = null; this.t = null;
            this.u = null; this.v = null; this.w = null; this.x = null;
            this.y = null; this.z = null;
            this.value = 0;
        }
    }
}
