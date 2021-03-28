import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Mosiac extends JFrame{
	
	BufferedImage img =null;
	int imgWidth=0,imgHeight=0;
	Mosiac(){
		try {
			img = ImageIO.read(new File("F:\\Projects\\Mosaic\\src\\JELAL.jpg"));
			imgHeight=img.getHeight();
			imgWidth=img.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setTitle("Mosaic");
		super.setSize(imgWidth*2,imgHeight);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawImage(img,0,0,this);
		int p=0;
		int offset=1;
		for(int i=0;i<imgWidth-offset;i+=offset) {
			for(int j=0;j<imgHeight-offset;j+=offset) {
				p=img.getRGB(i, j);
				int r=(p>>16)&0xff;
				int gr=(p>>8)&0xff;
				int b= p & 0xff;
				int avg = (r+gr+b)/3;
				if(avg<220) {
					g.drawImage(img, i+imgWidth, j, i+imgWidth+offset, j+offset, 0, 0, imgWidth, imgHeight, this);
				}
				//System.out.println(r+"-"+gr+"-"+b);
			}
		}
	}

	public static void main(String[] args) {
		new Mosiac();
	}

}
