package org.techtown.letseat.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class PhotoSave {
    /** 사진 크기 조정 */
    public Bitmap resize(Bitmap bm, Resources res){
        Configuration config = res.getConfiguration();
        if(config.smallestScreenWidthDp>=800)
            bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
        else if(config.smallestScreenWidthDp>=600)
            bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
        else if(config.smallestScreenWidthDp>=400)
            bm = Bitmap.createScaledBitmap(bm, 200,  120, true);
        else if(config.smallestScreenWidthDp>=360)
            bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
        else
            bm = Bitmap.createScaledBitmap(bm, 160, 96, true);
        return bm;
    }
    /**비트맵을 바이너리 바이트배열로 바꾸어주는 메서드 */
    public String bitmapToByteArray(Bitmap bitmap){
        String image = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        image = "&image="+byteArrayToBinaryString(byteArray);
        return image;
    }
    /**바이너리 바이트 배열을 스트링으로 바꾸어주는 메서드*/
    public String byteArrayToBinaryString(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++){
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }
    /**바이너리 바이트를 스트링으로 바꾸어주는 메서드*/
    public String byteToBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for(int bit = 0;bit < 8; bit++){
            if(((n>>bit)&1)>0){
                sb.setCharAt(7-bit,'1');
            }
        }
        return sb.toString();
    }
}
