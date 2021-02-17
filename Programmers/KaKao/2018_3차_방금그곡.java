class Solution {
    static char cSharp = 'C' + '#';
    static char dSharp = 'D' + '#';
    static char fSharp = 'F' + '#';
    static char gSharp = 'G' + '#';
    static char aSharp = 'A' + '#';
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int playTime = 0;
        
        String input = replaceSharp(m);
        
        
        for(String musicinfo : musicinfos) {
            String[] infos = musicinfo.split(",");
            
            // Start Time
            String[] time = infos[0].split(":");
            int start = Integer.parseInt(time[0]) * 60;
            start += Integer.parseInt(time[1]);
            
            // End time
            time = infos[1].split(":");
            int end = Integer.parseInt(time[0]) * 60;
            end += Integer.parseInt(time[1]);
            
            int playMin = 0;
            
            playMin = end - start;
            
            // Melody
            String musicName = infos[2];
            String melody = infos[3];
            melody = replaceSharp(melody);
            StringBuilder realMelody = new StringBuilder();
            
            for(int idx = 0; idx < playMin; idx++) {
                realMelody.append(melody.charAt(idx % melody.length()));
            }
            melody = realMelody.toString();
            
            if(melody.contains(input) &&  playTime < playMin) {
                playTime = playMin;
                answer = musicName;
            }
        }
        if(answer.length() == 0){
            return "(None)";
        }
        
        return answer;
    }
    
    public String replaceSharp(String input) {
        String newString = input;
        newString = newString.replaceAll("C#", Character.toString(cSharp));
        newString = newString.replaceAll("D#", Character.toString(dSharp));
        newString = newString.replaceAll("F#", Character.toString(fSharp));
        newString = newString.replaceAll("G#", Character.toString(gSharp));
        newString = newString.replaceAll("A#", Character.toString(aSharp));
        
        return newString;
    }
}
