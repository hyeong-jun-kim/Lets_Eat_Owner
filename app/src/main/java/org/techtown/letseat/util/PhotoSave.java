package org.techtown.letseat.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class PhotoSave {
    /**
     * 사진 크기 조정
     */
    public static Bitmap resizeMenu(Bitmap bm, Resources res) {
        Configuration config = res.getConfiguration();
        bm = Bitmap.createScaledBitmap(bm, 200, 200, true);
        return bm;
    }

    public static Bitmap resize(Bitmap bm, Resources res) {
        Configuration config = res.getConfiguration();
        bm = Bitmap.createScaledBitmap(bm, 500, 500, true);
        return bm;
    }

    /*
     * String형을 BitMap으로 변환시켜주는 함수
     * */
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /*
     * Bitmap을 String형으로 변환
     * */
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    /*
     * Bitmap을 byte배열로 변환
     * */
    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        return baos.toByteArray();
    }

/*    // 비트맵을 바이너리 바이트배열로 바꾸어주는 메서드
    public String bitmapToByteArray(Bitmap bitmap){
        String image = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        image = "&image="+byteArrayToBinaryString(byteArray);
        return image;
    }
    // 바이너리 바이트 배열을 스트링으로 바꾸어주는 메서드
    public String byteArrayToBinaryString(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++){
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }
    // 바이너리 바이트를 스트링으로 바꾸어주는 메서드
    public String byteToBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for(int bit = 0;bit < 8; bit++){
            if(((n>>bit)&1)>0){
                sb.setCharAt(7-bit,'1');
            }
        }
        return sb.toString();
    }*/
}
