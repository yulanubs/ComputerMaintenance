package com.computerdmaintenance.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * 圆角头像工具类
 */
public class CircleImageTool {
    public static Bitmap getCircleBitmap(Bitmap bitmap, Context context) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            bitmap = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
        } else {
            bitmap = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
        }
        int strokeWidth = bitmap.getWidth() / (12 * 2) + 1;
        Bitmap outbitmap = Bitmap.createBitmap(strokeWidth * 2 + bitmap.getWidth() + strokeWidth * 2, strokeWidth * 2 + bitmap.getHeight() + strokeWidth * 2, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(outbitmap);
        //画出最里面的圆形头像
        Rect rectdst = new Rect(strokeWidth * 2, strokeWidth * 2, strokeWidth * 2 + bitmap.getWidth(), strokeWidth * 2 + bitmap.getHeight());
        RectF rect = new RectF(rectdst);
        Paint paint = new Paint();
        int color = 0xff424242;
        //铺一层透明底色?
        canvas.drawARGB(0, 0, 0, 0);
        paint.setAntiAlias(true);
        //画圆
        canvas.drawRoundRect(rect, bitmap.getWidth(), bitmap.getWidth(), paint);
        paint.setColor(color);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        //画图 ，因为画笔设置了xfermode为 Mode.SRC_IN，画出来的图片只会显示圆形的部分,这样画出了最里面的圆形头像
        canvas.drawBitmap(bitmap, strokeWidth * 2, strokeWidth * 2, paint);

        Paint paintFirst = new Paint();
        paintFirst.setStyle(Style.STROKE);
        paintFirst.setAntiAlias(true);
        paintFirst.setStrokeWidth(strokeWidth);
        paintFirst.setStrokeJoin(Join.ROUND);
        paintFirst.setStrokeCap(Cap.ROUND);

        //画圆环
        paintFirst.setARGB(0xff, 0xff, 0xff, 0xff);
        canvas.drawCircle(strokeWidth * 2 + bitmap.getWidth() / 2, strokeWidth * 2 + bitmap.getWidth() / 2, strokeWidth / 2 + bitmap.getWidth() / 2, paintFirst);
        paintFirst.setARGB(0x4C, 0xed, 0xed, 0xed);
        canvas.drawCircle(strokeWidth * 2 + bitmap.getWidth() / 2, strokeWidth * 2 + bitmap.getWidth() / 2, strokeWidth / 2 + strokeWidth + bitmap.getWidth() / 2, paintFirst);
        return outbitmap;
    }
}
