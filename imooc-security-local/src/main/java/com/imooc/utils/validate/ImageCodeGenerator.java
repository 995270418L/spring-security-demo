package com.imooc.utils.validate;

import com.imooc.utils.validate.ImageValidateCode;
import com.imooc.utils.validate.ValidateCodeGenerator;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 图片验证码生成器的实现
 */
@Service
public class ImageCodeGenerator implements ValidateCodeGenerator<ImageValidateCode>{

    private static final int width = 100;    //图片宽度
    private static final int height = 30;    //图片高度

    private static final int expireTime = 20;  // 验证码过期时间s

    public static final char[] chars="1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();

    //  设置随机数
    public static Random random = new Random();

    //  获取4位随机数
    public static String getRandomString(){
        StringBuffer buffer = new StringBuffer();
        int index;   //获取随机chars下标
        for(int i=0;i<4;i++){
            index = random.nextInt(chars.length);  //获取随机chars下标
            buffer.append(chars[index]);
        }
        return buffer.toString();
    }

    //  获取随机颜色
    public static Color getRandomColor(){
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    //  返回某颜色的反色
    public static Color getReverseColor(Color c){
//      防止反色和原色相近
        if(c.getRed()<130&&c.getRed()>125 && c.getBlue()<130&& c.getBlue()>125 &&
                c.getGreen()<130&&c.getGreen()>125){
            return new Color(255,255,255);
        }else{
            return new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue());
        }
    }

    @Override
    public ImageValidateCode generatorValidateCode() {
//      1.创建图片缓存区     传参为宽高和图片类型
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Color color =   getRandomColor();  //随机色，用于背景色
        String code = getRandomString();  //获取随机验证码
        Color reverse = getReverseColor(color);  //反色，用于前景色

        // 2.获取画笔并绘画
        Graphics g = bi.getGraphics();
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));//设置字体
        g.setColor(color);//设置画笔颜色
        g.fillRect(0, 0, width, height);   //绘制背景
        g.setColor(reverse);   //设置画笔颜色
        g.drawString(code, 18, 20); //绘制字符
        // 设置最多100个噪音点
        for(int i=0,n = random.nextInt(100);i<n;i++){
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
        return new ImageValidateCode(bi,code,expireTime);
    }
}
