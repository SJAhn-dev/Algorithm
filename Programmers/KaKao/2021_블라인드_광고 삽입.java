class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] timeArray = new int[360000];

        int pTime = timeToInt(play_time);
        int aTime = timeToInt(adv_time);

        for(String log : logs) {
            String[] logSplit = log.split("-");
            int start = timeToInt(logSplit[0]);
            int end = timeToInt(logSplit[1]);
            for(int idx = start; idx < end; idx++) {
                timeArray[idx]++;
            }
        }

        long maxCount = 0;
        int maxCountTime = 0;
        int adStart = 0;
        int adEnd = aTime;

        for(int idx = adStart; idx < adEnd; idx++) {
            maxCount += timeArray[idx];
        }

        long tempSum = maxCount;
        while(adEnd <= pTime) {
            tempSum = tempSum - timeArray[adStart] + timeArray[adEnd];

            if(maxCount < tempSum) {
                maxCount = tempSum;
                maxCountTime = adStart + 1;
            }
            adStart++;
            adEnd++;
        }


        return timeToString(maxCountTime);
    }

    public int timeToInt(String timeString) {
        String[] split = timeString.split(":");

        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    public String timeToString(long timeInt) {
        StringBuilder sb = new StringBuilder();
        long hour = timeInt / 3600;
        if(hour < 10) { sb.append("0"); }
        sb.append(hour);
        sb.append(":");

        timeInt = timeInt % 3600;
        long minute = timeInt / 60;
        if(minute < 10) { sb.append("0"); }
        sb.append(minute);
        sb.append(":");

        timeInt = timeInt % 60;
        long second = timeInt;
        if(second < 10) { sb.append("0"); }
        sb.append(timeInt);
        
        return sb.toString();
    }
}
