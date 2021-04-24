package kr.or.ddit.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

/**
 * 이미지 핸들링 유틸클래스
 */
public class ImageUtils {
	public static final int SAME = -1;
	public static final int RATIO = 0;
	
	/**
	 * 이미지 사이즈 측정
	 * @param src 이미지 파일
	 * @return width 와 height 를 갖는 Dimension 객체.
	 * @throws IOException
	 */
	public static Dimension imageSize(File src) throws IOException {
		String mime = src.toURI().toURL().openConnection().getContentType();
		if(!mime.startsWith("image/")) throw new IllegalArgumentException("이미지가 아님");
		Image srcImage = ImageIO.read(src);
		int srcWidth = srcImage.getWidth(null);
		int srcHeight = srcImage.getHeight(null);
		Dimension size = new Dimension(srcWidth, srcHeight);
		return size;
	}
	/**
	 * @param src : 소스 파일
	 * @param dest : 타겟 파일
	 * @param width : 타겟 넓이(SAME 이면 유지), (RATIO 면 높이에 따른 상대 비율)
	 * @param height : 타겟 높이(SAME 면 유지), (RATIO 면 넓이에 따른 상대 비율)
	 * @throws IOException
	 */
	public static void resize(File src, File dest, int width, int height) throws IOException{
		String mime = src.toURI().toURL().openConnection().getContentType();
		if(!mime.startsWith("image/")) return;
		
		String imageName = src.getName();
		String suffix = "";
		int lastIdx = imageName.lastIndexOf('.');
		if(lastIdx!=-1) {
			suffix = imageName.substring(lastIdx + 1).toLowerCase();
		}
		
		Image srcImage = ImageIO.read(src);
		int srcWidth = srcImage.getWidth(null);
		int srcHeight = srcImage.getHeight(null);
		
		int destWidth = -1, destHeight = -1;
		
		if(width == SAME){
			destWidth = srcWidth;
		} else if(width > 0){
			destWidth = width;
		}
		
		if(height==SAME){
			destHeight = srcHeight;
		}else if(height > 0){
			destHeight = height;
		}
		
		if(width == RATIO && height == RATIO){
			destWidth = srcWidth;
			destHeight = srcHeight;
		} else if(width == RATIO){
			double ratio = (double)destHeight /srcHeight;
			destWidth =(int)(srcWidth * ratio);
		}else if(height == RATIO){
			double ratio = (double)destWidth/ srcWidth;
			destHeight = (int)(srcHeight*ratio);
		}
		Image targetImage = srcImage.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
		int[] pixels = new int[destWidth * destHeight];
		PixelGrabber pg = new PixelGrabber(targetImage, 0, 0, destWidth, destHeight, pixels, 0, destWidth);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new IOException(e);
		}
		BufferedImage destImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
		destImage.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
		
		ImageIO.write(destImage, suffix, dest);
	}
}
