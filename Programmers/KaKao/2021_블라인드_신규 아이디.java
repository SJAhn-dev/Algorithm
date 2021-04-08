class Solution {
    public String solution(String new_id) {
        // 1단계 : 소문자화
        String tolower = new_id.toLowerCase();

        // 2단계 : 허용 문자 이외 제거
        String delSpecial = tolower.replaceAll("[^a-z0-9-_.]", "");

        // 3단계 : 연속되는 마침표 제거
        String delComma = delSpecial.replaceAll("[.]{2,}", ".");

        // 4단계 : 시작, 끝 마침표 제거
        if(delComma.length() > 0 && delComma.charAt(0) == '.') {
            delComma = delComma.substring(1, delComma.length());
        }
        if(delComma.length() > 0 && delComma.charAt(delComma.length() - 1) == '.') {
            delComma = delComma.substring(0, delComma.length() - 1);
        }

        // 5단계 : 빈 문자열이라면 a 대입
        StringBuilder convertId = new StringBuilder(delComma);
        if(convertId.length() == 0) {
            convertId.append('a');
        }

        // 6단계 : 길이가 16이상이면 자르고 마지막 문자가 comma면 제거
        if(convertId.length() > 15) {
            convertId.delete(15, convertId.length());
        }
        if(convertId.length() > 0 && convertId.charAt(convertId.length() - 1) == '.') {
            convertId.delete(convertId.length() - 1, convertId.length());
        }

        // 7단계 : 길이가 3 이하라면 마지막 문자 반복
        if(convertId.length() < 3) {
            char lastChar = convertId.charAt(convertId.length() - 1);
            while(convertId.length() < 3) {
                convertId.append(lastChar);
            }
        }

        return convertId.toString();
    }
}
