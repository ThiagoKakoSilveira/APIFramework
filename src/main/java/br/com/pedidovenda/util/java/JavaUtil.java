package br.com.pedidovenda.util.java;

public class JavaUtil {
    public static String entregaString(){
        java.util.Random r = new java.util.Random();
        char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h','i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x','w',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K','L',
                'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1',
                '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(goodChar[r.nextInt(goodChar.length)]);
        }
        return sb.toString();
    }
}
