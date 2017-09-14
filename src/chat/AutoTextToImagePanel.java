package chat;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.Utilities;

public class AutoTextToImagePanel extends JEditorPane {

    public AutoTextToImagePanel() {
        super();
         JFrame frame = new JFrame("Autoreplace :) with Smiles images example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setEditorKit(new StyledEditorKit());
        initListener();
        JScrollPane scroll = new JScrollPane(this);
        frame.getContentPane().add(scroll);
    
//      Smiley test11 = new Smiley();
//      test11.show();
    }

    private void initListener() {
        getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent event) {
                final DocumentEvent e=event;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getDocument() instanceof StyledDocument) {
                            try {
                                StyledDocument doc=(StyledDocument)e.getDocument();
                                int start= Utilities.getRowStart(AutoTextToImagePanel.this,Math.max(0,e.getOffset()-1));
                                int end=Utilities.getWordStart(AutoTextToImagePanel.this,e.getOffset()+e.getLength());
                                String text=doc.getText(start, end-start);

                                // Angry
                                int i=text.indexOf("#-");
	                            while(i>=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
                                       doc.getCharacterElement(start+i).getAttributes());
                                    if (StyleConstants.getIcon(attrs)==null) {
                                        StyleConstants.setIcon(attrs, new ImageIcon("angry.gif"));
                                        doc.remove(start+i, 2);
                                        doc.insertString(start+i,"#-", attrs);
                                    }
                                    i=text.indexOf("#-", i+2);  // after 2letters(':',')')
                                }
                                // End Angry
	                            
                                // Dance
	                            int index2 = text.indexOf("@@");
	                            while (index2 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index2).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("dance.gif"));
	                                    doc.remove(start+index2, 2);
	                                    doc.insertString(start+index2,"@@", attrs);
	                                }
	                                index2=text.indexOf("@@", index2+2);  // after 2letters(':',')')
	                            } 
                                // End Dance
	                            
                                // Cry
	                            int index3 = text.indexOf(";(");
	                            while (index3 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index3).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("cry.jpg"));
	                                    doc.remove(start+index3, 2);
	                                    doc.insertString(start+index3,";(", attrs);
	                                }
	                                index3=text.indexOf(";(", index3+2);  // after 2letters(':',')')
	                            } 
                                // End Cry

                                // Dance
	                            int index4 = text.indexOf(":D");
	                            while (index4 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index4).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("funny.jpg"));
	                                    doc.remove(start+index4, 2);
	                                    doc.insertString(start+index4,":D", attrs);
	                                }
	                                index4=text.indexOf(":D", index4+2);  // after 2letters(':',')')
	                            } 
                                // End Dance
	                            
                                // Mad
	                            int index5 = text.indexOf("--");
	                            while (index5 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index5).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("mad.jpg"));
	                                    doc.remove(start+index5, 2);
	                                    doc.insertString(start+index5,"--", attrs);
	                                }
	                                index5=text.indexOf("--", index5+2);  // after 2letters(':',')')
	                            } 
                                // End Mad
	                            
                                // Wink
	                            int index6 = text.indexOf(";)");
	                            while (index6 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index6).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("wink.jpg"));
	                                    doc.remove(start+index6, 2);
	                                    doc.insertString(start+index6,";)", attrs);
	                                }
	                                index6=text.indexOf(";)", index6+2);  // after 2letters(':',')')
	                            } 
                                // End Wink
	                            
                                // Heart
	                            int index7 = text.indexOf("<3");
	                            while (index7 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index7).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("heart.gif"));
	                                    doc.remove(start+index7, 2);
	                                    doc.insertString(start+index7,";)", attrs);
	                                }
	                                index7=text.indexOf(";)", index7+2);  // after 2letters(':',')')
	                            } 
                                // End Heart
	                            
                                // Heart
	                            int index8 = text.indexOf("^^");
	                            while (index8 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index8).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, new ImageIcon("baby.gif"));
	                                    doc.remove(start+index8, 2);
	                                    doc.insertString(start+index8,"^^", attrs);
	                                }
	                                index8=text.indexOf("^^", index8+2);  // after 2letters(':',')')
	                            } 
                                // End Heart
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
            }
            public void removeUpdate(DocumentEvent e) {
            }
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

//    static ImageIcon createSmile() {
//        BufferedImage res=new BufferedImage(17, 17, BufferedImage.TYPE_INT_ARGB);
//        Graphics g=res.getGraphics();
//        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setColor(Color.yellow);
//        g.fillOval(0,0,16,16);
//
//        g.setColor(Color.black);
//        g.drawOval(0,0,16,16);
//
//        g.drawLine(4,5, 6,5);
//        g.drawLine(4,6, 6,6);
//
//        g.drawLine(11,5, 9,5);
//        g.drawLine(11,6, 9,6);
//
//        g.drawLine(4,10, 8,12);
//        g.drawLine(8,12, 12,10);
//        g.dispose();
//
//        return new ImageIcon(res);
//    }
//    
//	protected ImageIcon createSad() {
//		BufferedImage bi = new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB);
//		Graphics g = bi.getGraphics();
//		g.setColor(Color.red);
//		g.drawOval(0, 0, 14, 14);
//		g.drawLine(4, 9, 9, 9);
//		g.drawOval(4, 4, 1, 1);
//		g.drawOval(10, 4, 1, 1);
//		return new ImageIcon(bi);
//	}
}

//class Smiley extends JFrame {
//    //autoreplacing :) with picture
//    JTextPane p = new JTextPane();
//    public Smiley() throws Exception {
//        p.setEditorKit(new StyledEditorKit());
//        getContentPane().add(p, BorderLayout.CENTER);
//        SimpleAttributeSet attrs = new SimpleAttributeSet();
//        StyleConstants.setIcon(attrs, getImage());
//        p.addCaretListener(new CaretListener() {
//            public void caretUpdate(CaretEvent e) {
//                SwingUtilities.invokeLater(new Runnable() {
//                    public void run() {
//                        try {
//
//                        }
//                        catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(400, 400);
//    }

//}