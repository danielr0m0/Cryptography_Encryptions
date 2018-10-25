package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hacking {

    public static void main(String[] args) throws IOException {
        String plainText = "CRYPTOGRAPHY";
        byte[] key = new byte[]{0,1,1,1,0,0,1,1,0,1};
        //help to understand code for caasii
        String f = plainText.charAt(1)+"";
        //to get 8 bit representation of a letter
        print(CASCII.Convert(f));
        //to convert bit[] to a string
        System.out.println("\n"+CASCII.toString(CASCII.Convert(f)));

        //need to split the converted plaintext into 8 bits and pass them in encrypt
        byte[] encrypt = CASCII.Convert(plainText);

        System.out.println(plainText +" =");

        byte[] answer1= encrypt(key,encrypt);
        print(answer1);

        System.out.println("\n length of answer1: "+ answer1.length);
        //to convert byte[] to string
        System.out.println(CASCII.toString(answer1));

        String text2="1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
        System.out.println(text2);
        System.out.println(text2.length());

        for (int i = 0; i < Math.pow(2, 10); i++) {
            byte[] key2 = byte10BitSize(i);
            print(key2);
            System.out.print(" ");
            byte[] encryption = decrypt(key2,stringToByteArr(text2));
            System.out.print(CASCII.toString(encryption));
            System.out.println();
        }
    }

    public static byte[] encrypt(byte[] key, byte[] text){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < text.length; i=i+8) {
            byte [] base8 = new byte[8];
            for (int j = 0; j < 8; j++) {
                base8[j]= text[i+j];
            }
            try {
                outputStream.write(SDES.Encrypt(key,base8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    public static byte[] decrypt(byte[] key, byte[]text){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < text.length; i=i+8) {
            byte [] base8 = new byte[8];
            for (int j = 0; j < 8; j++) {
                base8[j]= text[i+j];
            }
            try {
                outputStream.write(SDES.Decrypt(key,base8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    public static byte[] byte10BitSize(int n){
        byte[] key = new byte[10];
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < key.length; i++) {
            if(s.length()==key.length){
                key[i]=(byte)Integer.parseInt(s.charAt(i)+"");
            }else {
                String padding ="";
                for (int j = 0; j < key.length - s.length(); j++) {
                    padding +="0";
                }
                s= padding+s;
                key[i]=(byte)Integer.parseInt(s.charAt(i)+"");
            }
        }
        return key;
    }

    public static void print(byte[] b){
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
        }
    }

    ///assuming string is in binary
    public static byte[] stringToByteArr(String s){
        byte[] text = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            text[i]= (byte) (Integer.parseInt(s.charAt(i)+""));
        }
        return text;
    }
}
