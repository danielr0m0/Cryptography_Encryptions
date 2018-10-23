package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SDES {

    public static void main(String[] args) {
    byte key1[] = {1,0,1,0,0,0,0,0,1,0};

        for (int i = 0; i < key1.length; i++) {
            System.out.print(key1[i] + " ");
        }

        Encrypt(key1, key1);

    }

    public static byte[] Encrypt(byte[] rawkey, byte[] plaintext){
        byte[] ciphertext;
        byte[] ki=circularLeftShift1(p10(rawkey));
        byte[]k1= p8(ki);
        byte[]k2= p8(circularLeftShift2(ki));
        print(k1);
        print(k2);

        print(IPinverse(IP(new byte[]{1,2,3,4,5,6,7,8})));
        return plaintext;
    }
    public static  byte[] Decrypt(byte[]rawkey, byte[]ciphertext){
        byte[] plaintext;


        return ciphertext;
    }

    public static byte[] p10(byte[] rawKey){
        byte[] p10= {3,5,2,7,4,10,1,9,8,6};
        byte[]key=new byte[10];
        for (int i = 0; i < p10.length; i++) {
            key[i] = rawKey[p10[i] - 1];
        }
        return key;
    }

    public static byte[] circularLeftShift1(byte[] key){
        byte[] lh = new byte[key.length/2];
        byte[] rh = new byte[key.length/2];

        //fill the data for the right hand and left hand
        for (int i = 0; i < key.length; i++) {
            if(i<(key.length/2))
                lh[i]=key[i];
            else
                rh[i%(key.length/2)]=key[i];
        }
        byte lhTemp= lh[0];
        byte rhTemp= rh[0];
        for (int i = 0; i < lh.length; i++) {
            if (i == (key.length / 2) - 1) {
                lh[i] = lhTemp;
                rh[i] = rhTemp;
            } else{
                lh[i] = lh[i + 1];
                rh[i]= rh[i+1];
            }
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(lh);
            output.write(rh);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toByteArray();
    }

    public static byte[] circularLeftShift2(byte[] key){
        byte[] lh = new byte[key.length/2];
        byte[] rh = new byte[key.length/2];

        //fill the data for the right hand and left hand
        for (int i = 0; i < key.length; i++) {
            if(i<(key.length/2))
                lh[i]=key[i];
            else
                rh[i%(key.length/2)]=key[i];
        }
        byte lhTemp1= lh[0];
        byte lhTemp2= lh[1];

        byte rhTemp1= rh[0];
        byte rhTemp2= rh[1];

        for (int i = 0; i < lh.length; i++) {
            if(i==(key.length/2)-1){
                lh[i]=lhTemp2;
                rh[i]=rhTemp1;
            }
            else if(i == (key.length/2)-2){
                lh[i]=lhTemp1;
                rh[i]=rhTemp2;
            }
            else{
                lh[i]= lh[i+2];
                rh[i]= rh[i+2];
            }

        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(lh);
            output.write(rh);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toByteArray();
    }

    public static byte[] p8(byte[] rawKey){
        byte[] p8 = {6,3,7,4,8,5,10,9};
        byte[] key = new byte[p8.length];
        for (int i = 0; i < p8.length; i++) {
            key[i] = rawKey[p8[i] - 1];
        }
        return key;
    }

    public static byte[] IP(byte[] text){
        byte[] IP={2,6,3,1,4,8,5,7};
        byte[] IPtext= new byte[IP.length];
        for (int i = 0; i < text.length; i++) {
            IPtext[i]= text[IP[i]-1];
        }
        return IPtext;
    }

    public static byte[] IPinverse(byte[] text){
        byte[] IPi={4,1,3,5,7,2,8,6};
        byte[] IPtext= new byte[IPi.length];
        for (int i = 0; i < text.length; i++) {
            IPtext[i]= text[IPi[i]-1];
        }
        return IPtext;
    }

    public static byte[] fK(byte[] L, byte[] R){


        return R;
    }

    //functions to have
    //bite to decimal
    //sbox

    public static void print(byte[] b){
        System.out.println("\n");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]+" ");
        }
    }

}
