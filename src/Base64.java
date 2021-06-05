public class Base64 {

    Base64(){}
    public static String asciiToBase64(int binary){
        System.out.println(binary);
        System.out.println(Integer.toBinaryString(binary));
        if (binary <= 25) {
            return Character.toString(binary+65);
        }
        else if (binary <= 51){
            return Character.toString(binary+71);
        }
        else if (binary <= 61){
            return Character.toString(binary-4);
        }
        else if (binary == 63){
            return Character.toString(binary-20);
        }
        else{
            return Character.toString(binary-16);
        }
    }
    public static String base64Toascii(int binary){
        System.out.println(binary);
        System.out.println(Integer.toBinaryString(binary));
        if (binary <= 25) {
            return Character.toString(binary+65);
        }
        else if (binary <= 51){
            return Character.toString(binary+71);
        }
        else if (binary <= 61){
            return Character.toString(binary-4);
        }
        else if (binary == 63){
            return Character.toString(binary-20);
        }
        else{
            return Character.toString(binary-16);
        }
    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();

        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();
    }

    public static String encoder(String binary) {

        StringBuilder result =new StringBuilder();
        int index = 0;
        while (index < binary.length()) {
            result.append(asciiToBase64(Integer.parseInt(binary.substring(index, Math.min(index + 6, binary.length())),2)));
            index += 6;
        }
        return result.toString();
    }

    public static String decoder(String code){
        String binary = convertStringToBinary(code);
        StringBuilder result =new StringBuilder();
        int index = 0;

        while (index < binary.length()) {
            result.append(Character.toString(Integer.parseInt(binary.substring(index, Math.min(index + 8, binary.length())),2)));
            index += 8;
        }
        return result.toString();
    }

}
