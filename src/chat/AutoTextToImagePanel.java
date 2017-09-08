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

                                // Smile
                                int i=text.indexOf(":)");
	                            while(i>=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
                                       doc.getCharacterElement(start+i).getAttributes());
                                    if (StyleConstants.getIcon(attrs)==null) {
                                        StyleConstants.setIcon(attrs, createSmile());
                                        doc.remove(start+i, 2);
                                        doc.insertString(start+i,":)", attrs);
                                    }
                                    i=text.indexOf(":)", i+2);  // after 2letters(':',')')
                                }
                                // End Smile
	                            
                                // Sad
	                            int index2 = text.indexOf(":(");
	                            while (index2 >=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                    doc.getCharacterElement(start+index2).getAttributes());
	                                if (StyleConstants.getIcon(attrs)==null) {
	                                    StyleConstants.setIcon(attrs, createSad());
	                                    doc.remove(start+index2, 2);
	                                    doc.insertString(start+index2,":(", attrs);
	                                }
	                                index2=text.indexOf(":(", index2+2);  // after 2letters(':',')')
	                            } 
                                // End Sad
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

    static ImageIcon createSmile() {
        BufferedImage res=new BufferedImage(17, 17, BufferedImage.TYPE_INT_ARGB);
        Graphics g=res.getGraphics();
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.yellow);
        g.fillOval(0,0,16,16);

        g.setColor(Color.black);
        g.drawOval(0,0,16,16);

        g.drawLine(4,5, 6,5);
        g.drawLine(4,6, 6,6);

        g.drawLine(11,5, 9,5);
        g.drawLine(11,6, 9,6);

        g.drawLine(4,10, 8,12);
        g.drawLine(8,12, 12,10);
        g.dispose();

        return new ImageIcon(res);
    }
    
	protected ImageIcon createSad() {
		BufferedImage bi = new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.red);
		g.drawOval(0, 0, 14, 14);
		g.drawLine(4, 9, 9, 9);
		g.drawOval(4, 4, 1, 1);
		g.drawOval(10, 4, 1, 1);
		return new ImageIcon(bi);
	}
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