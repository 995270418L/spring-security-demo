package com.imooc.utils.validate;

import com.imooc.utils.validate.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * 图形验证码
 */
public class ImageValidateCode extends ValidateCode{

    /**
     * 背景图
     */
    private BufferedImage bufferedImage;


    public ImageValidateCode(BufferedImage bufferedImage, String code, Integer expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}
