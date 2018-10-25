package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Hacking {

    public static void main(String[] args) throws IOException {
        String plainText = "CRYPTOGRAPHY";
        byte[] key = new byte[]{0,1,1,1,0,0,1,1,0,1};
        //help to understand code for cascii
        String f = plainText.charAt(0)+"";
        //to get 8 bit representation of a letter
        print(CASCII.Convert(f));
        //to convert bit[] to a string
        System.out.println("\n"+CASCII.toString(CASCII.Convert(f)));

        //pass the plainText string to CASCII convert class where it converts the plain text to byte [] and add padding to the end to make the length divisible by 8
        print(CASCII.Convert(plainText));

        //need to split the converted plaintext into 8 bits and pass them in encrypt
        byte[] encrypt = CASCII.Convert(plainText);

        System.out.println("\n"+plainText +" encrypted=");

        //encrypt method is in this class where it gets the encrypt byte[] and split it into byte[] size of 8 and passes it to the encryption of SDES and then concat it back to one
        byte[] answer1= encrypt(key,encrypt);
        print(answer1);

        //test if i decrypt the answer i got for encryption will give me back the plain text
        System.out.println("\n decrypt the answer i got from above ^ = ");
        System.out.println(CASCII.toString(decrypt(key,answer1)));

        System.out.println("length of answer1: "+ answer1.length);

        String text2="1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
        print(stringToByteArr(text2));
        System.out.println();
        System.out.println(text2);
        System.out.println(text2.length());
        int count = 0;

        for (int i = 0; i < Math.pow(2, 10); i++) {
            byte[] key1 = byte10BitSize(i);
            byte[] decrypt = decrypt(key1,stringToByteArr(text2));
            String data = CASCII.toString(decrypt);

            //print the stuff
            //if the last two byte is 0 print since padding for a 952 size is 2
            if(decrypt[decrypt.length-1]==(byte)0& decrypt[decrypt.length-2]== (byte) 0){
                print(key1);
                System.out.print(" ");
                System.out.print(data);
                System.out.println("\n");
                count++;
            }

        }

        System.out.println(count);

        String text3 = "00011111100111111110011111101100111000000011001011110010101010110001011101001101000000110011010111111110000000001010111111000001010010111001111001010101100000110111100011111101011100100100010101000011001100101000000101111011000010011010111100010001001000100001111100100000001000000001101101000000001010111010000001000010011100101111001101111011001001010001100010100000";
        System.out.println(text3);
        System.out.println(text3.length());
        //for triple SDES need to write to text file since results is too much. since their is 1,048,576 results
        //or we need some filter to shorten the results we get.
//        for (int i = 0; i < Math.pow(2, 10); i++) {
//            byte[] key1 = byte10BitSize(i);
//            for (int j = 0; j < Math.pow(2, 10); j++) {
//                //i for key 1 j for key 2
//                byte[] key2 = byte10BitSize(j);
//                byte[] decrypt = decrypt(key1,key2,stringToByteArr(text3));
//                //print stuff
//                if(decrypt[decrypt.length-1]==(byte)0 &&  decrypt[decrypt.length-2]== (byte) 0 && decrypt[decrypt.length-3]== (byte) 0 ){
//                    print(key1);
//                    System.out.print(" ");
//                    print(key2);
//                    System.out.print(" ");
//                    System.out.print(CASCII.toString(decrypt));
//                    System.out.println("\n");
//                }
//
//            }
//        }
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

    public static byte[] encrypt(byte[] key1, byte[] key2, byte[]text){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < text.length; i=i+8) {
            byte [] base8 = new byte[8];
            for (int j = 0; j < 8; j++) {
                base8[j]= text[i+j];
            }
            try {
                outputStream.write(TripleSDES.Encrypt(key1,key2,base8));
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

    public static byte[] decrypt (byte[] key1, byte[]key2, byte [] text){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < text.length; i=i+8) {
            byte [] base8 = new byte[8];
            for (int j = 0; j < 8; j++) {
                base8[j]= text[i+j];
            }
            try {
                outputStream.write(TripleSDES.Decrypt(key1,key2,base8));
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
            text[i]= (byte) Integer.parseInt(s.charAt(i)+"");
        }
        return text;
    }
}
