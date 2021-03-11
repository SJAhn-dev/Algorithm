import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;
        double maxValue = 0;
        word = word.toLowerCase();
        HashMap<String, Webpage> webMap = new HashMap<>();
        ArrayList<Webpage> pagelist = new ArrayList<>();
        
        for(String page : pages) {
            Webpage webpage = parsePage(page);
            for(String cword : webpage.body.words) {
                String lowcword = cword.toLowerCase();
                if(lowcword.equals(word)) {
                    webpage.baseScore++;
                }
            }
            webMap.put(webpage.url, webpage);
            pagelist.add(webpage);
        }
        
        for(int idx = 0; idx < pagelist.size(); idx++) {
            Webpage page = pagelist.get(idx);
            int pageCnt = page.body.link.length;
            for(String url : page.body.link) {
                if(webMap.containsKey(url)) {
                    webMap.get(url).linkScore += (page.baseScore / ((double) pageCnt));
                }
            }
        }
        
        for(int idx = 0; idx < pagelist.size(); idx++) {
            Webpage page = pagelist.get(idx);
            if(page.baseScore + page.linkScore > maxValue) {
                answer = idx;
                maxValue = page.baseScore + page.linkScore;
            }
        }
        
        return answer;
    }
    
    public Webpage parsePage(String page) {
        Webpage webpage = new Webpage();
        
        // url
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(https://[\\S]*)\"/>");
        Matcher matcher = pattern.matcher(page);
        matcher.find();
        webpage.url = matcher.group(1);
        
        // body
        int bodyStart = page.indexOf("<body>") + 6;
        int bodyEnd = page.indexOf("</body>");
        webpage.body = parseBody(page.substring(bodyStart, bodyEnd));
        
        return webpage;
    }
    
    public Body parseBody(String body) {
        ArrayList<String> links = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("<a href=\"(https://[\\S]*)\">");
        Matcher matcher = pattern.matcher(body);
        while(matcher.find()) {
            links.add(matcher.group(1));
        }

        String[] words = body.toString().split("[^a-zA-Z]");

        return new Body(words, links.toArray(new String[links.size()]));
    }
    
    class Webpage {
        String url;
        Body body;
        double baseScore;
        double linkScore;
        
        public Webpage() {
            this.url = null;
            this.body = null;
            this.baseScore = 0;
            this.linkScore = 0;
        }
    }
    class Body {
        String[] words;
        String[] link;
        
        public Body(String[] words, String[] link) {
            this.words = words;
            this.link = link;
        }
    }
}
