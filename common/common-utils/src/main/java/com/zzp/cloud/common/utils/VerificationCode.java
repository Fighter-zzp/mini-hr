package com.zzp.cloud.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
/**
 * 验证码生成
 * <p>
 *  //TODO
 *  VerificationCode.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/28 13:50
 * @see  VerificationCode
 **/
public class VerificationCode {
    /**
     * 生成验证码图片的宽度
     */
    private int width = 100;
    /**
     * 生成验证码图片的高度
     */
    private int height = 30;
    private String[] fontNames = { "宋体", "楷体", "隶书", "微软雅黑" };
    /**
     * 定义验证码图片的背景颜色为白色
     */
    private Color bgColor = new Color(255, 255, 255);
    private Random random = new Random();
    private String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 记录随机字符串
     */
    private String text;

    /**
     * 获取一个随意颜色
     *
     * @return
     */
    private Color randomColor() {
        var red = random.nextInt(150);
        var green = random.nextInt(150);
        var blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 获取一个随机字体
     *
     * @return
     */
    private Font randomFont() {
        String name = fontNames[random.nextInt(fontNames.length)];
        var style = random.nextInt(4);
        var size = random.nextInt(5) + 24;
        return new Font(name, style, size);
    }

    /**
     * 获取一个随机字符
     *
     * @return
     */
    private char randomChar() {
        return codes.charAt(random.nextInt(codes.length()));
    }

    /**
     * 创建一个空白的BufferedImage对象
     *
     * @return
     */
    private BufferedImage createImage() {
        var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        var g2 = (Graphics2D) image.getGraphics();
        // 设置验证码图片的背景颜色
        g2.setColor(bgColor);
        g2.fillRect(0, 0, width, height);
        return image;
    }

    public BufferedImage getImage() {
        var image = createImage();
        var g2 = (Graphics2D) image.getGraphics();
        var sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            g2.setColor(randomColor());
            g2.setFont(randomFont());
            float x = i * width * 1.0f / 4;
            g2.drawString(s, x, height - 8);
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    /**
     * 绘制干扰线
     *
     * @param image
     */
    private void drawLine(BufferedImage image) {
        var g2 = (Graphics2D) image.getGraphics();
        var num = 5;
        for (var i = 0; i < num; i++) {
            var x1 = random.nextInt(width);
            var y1 = random.nextInt(height);
            var x2 = random.nextInt(width);
            var y2 = random.nextInt(height);
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public String getText() {
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
}
