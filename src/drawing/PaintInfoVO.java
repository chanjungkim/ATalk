package drawing;

import java.awt.Color;
import java.io.Serializable;

public class PaintInfoVO implements Serializable {
	private Color drawColor;
	private double drawX;
	private double drawY;
	private int widthLine=5;
	
	public PaintInfoVO() {}
	public PaintInfoVO(Color drawColor, double x, double y, int widthLine) {
		this.drawColor = drawColor;
		this.drawX = x;
		this.drawY = y;
		this.widthLine = widthLine;
	}
	public PaintInfoVO(Color drawColor, double x, double y) {
		this.drawColor = drawColor;
		this.drawX = x;
		this.drawY = y;
	}
	
	public Color getDrawColor() {
		return drawColor;
	}
	public void setDrawColor(Color drawColor) {
		this.drawColor = drawColor;
	}
	public double getDrawX() {
		return drawX;
	}
	public void setDrawX(double drawX) {
		this.drawX = drawX;
	}
	public double getDrawY() {
		return drawY;
	}
	public void setDrawY(double drawY) {
		this.drawY = drawY;
	}	
	public void setWidthLine(int widthLine) {
		this.widthLine = widthLine;
	}
	public int getWidthLine() {
		return widthLine;
	}
}