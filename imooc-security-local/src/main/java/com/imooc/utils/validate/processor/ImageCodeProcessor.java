package com.imooc.utils.validate.processor;

import com.imooc.utils.validate.ImageValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

@Component
public class ImageCodeProcessor extends ValidateCodeAbstractProcessor<ImageValidateCode> {

    @Override
    public void validateProcessor(ServletWebRequest request, ImageValidateCode code) throws IOException {
        ImageIO.write(code.getBufferedImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
