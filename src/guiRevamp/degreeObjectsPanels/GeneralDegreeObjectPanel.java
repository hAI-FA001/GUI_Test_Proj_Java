package guiRevamp.degreeObjectsPanels;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseListener;

import static guiRevamp.constants.*;

public class GeneralDegreeObjectPanel extends JPanel {
    public int activePanelNo;
    int nextSeqNum = 1, parentPanelNo;
    final int numComponentsToDivideBy = 2, panelWidth = FRAME_WIDTH, panelHeight = 63*FRAME_HEIGHT/128;
    public JPanel[] degreeObjectPanels;
    JLabel[] degreeObjectInfoLabels;
    JLabel infoLabelOnHover;
    public JScrollPane scrollPane;

    GeneralDegreeObjectPanel(){

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
//        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));;
//        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
//        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());

        scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setBackground(new Color(0xAA3366));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(0xCC6688);
            }
        });
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPane.getHorizontalScrollBar().setBackground(new Color(0xAA3366));
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(0xCC6688);
            }
        });
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        scrollPane.setViewportBorder(BorderFactory.createLineBorder(new Color(0x774433), 5));
        scrollPane.setViewportView(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0,
                new Color(0xAA6666), 3*w, h/50, new Color(0x660033));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    public void addDegreeObject(JFrame parentFrame, String labelText, int numAdded){

        int insetVal = 5;

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(insetVal,insetVal,insetVal,insetVal);

        for (int i = degreeObjectPanels.length - numAdded; i < degreeObjectPanels.length; i++)
        {

            degreeObjectPanels[i].setLayout(new BorderLayout());
            degreeObjectPanels[i].setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
            degreeObjectPanels[i].setPreferredSize(new Dimension(
                    panelWidth/(numComponentsToDivideBy+1),
                    panelHeight/numComponentsToDivideBy
            ));
            degreeObjectPanels[i].setMinimumSize(new Dimension(
                    panelWidth/(numComponentsToDivideBy+1),
                    panelHeight/numComponentsToDivideBy
            ));
            degreeObjectPanels[i].addMouseListener((MouseListener) parentFrame);

            degreeObjectInfoLabels[i].setText(labelText + ((labelText.contains("-"))? (i+1) : ""));
            degreeObjectInfoLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            degreeObjectInfoLabels[i].setForeground(new Color(0xFFCCAA));
            degreeObjectInfoLabels[i].setPreferredSize(new Dimension(
                    panelWidth/(numComponentsToDivideBy+1),
                    panelHeight/(numComponentsToDivideBy*5)
            ));
            degreeObjectInfoLabels[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

            int check = i % 3;

            c.anchor = (check == 2)? GridBagConstraints.LINE_END :
                        (check == 1)? GridBagConstraints.CENTER : GridBagConstraints.LINE_START;

            if(nextSeqNum == degreeObjectPanels.length && nextSeqNum >= 10) {
                this.setPreferredSize(new Dimension(
                        panelWidth,
                        (((nextSeqNum - 7) / 3)
                                * (panelHeight/numComponentsToDivideBy))
                                + panelHeight));
                nextSeqNum += 3;
            }

            c.gridy = (nextSeqNum < 10)? (((nextSeqNum++ - 1) / 3)
                    * (panelHeight / numComponentsToDivideBy))
                    :
                    (((nextSeqNum - 4) / 3)
                    * (panelHeight / numComponentsToDivideBy));

            degreeObjectPanels[i].add(degreeObjectInfoLabels[i], BorderLayout.NORTH);
            this.add(degreeObjectPanels[i], c);
        }

        this.validate();
        parentFrame.validate();

        this.scrollRectToVisible(degreeObjectPanels[degreeObjectPanels.length-1].getBounds());
    }

    public void incrementSizeBy(int size) {
        if(degreeObjectPanels == null) {
            degreeObjectPanels = new JPanel[size];
            degreeObjectInfoLabels = new JLabel[size];

            for(int i=0; i < degreeObjectPanels.length; i++) {
                degreeObjectPanels[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0xFF7777), getWidth(), getHeight()/2, new Color(0xAA3366));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                degreeObjectPanels[i].setName(String.valueOf(i));
                degreeObjectInfoLabels[i] = new JLabel();
            }

        }
        else {
            JPanel[] degreeObjectPanels = new JPanel[this.degreeObjectPanels.length + size];
            JLabel[] degreeObjectInfoLabels = new JLabel[this.degreeObjectInfoLabels.length + size];

            System.arraycopy(this.degreeObjectPanels, 0, degreeObjectPanels, 0, this.degreeObjectPanels.length);
            System.arraycopy(this.degreeObjectInfoLabels, 0, degreeObjectInfoLabels, 0, this.degreeObjectInfoLabels.length);

            for(int i=this.degreeObjectPanels.length; i < degreeObjectPanels.length; i++) {
                degreeObjectPanels[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0xFF7777), getWidth(), getHeight()/2, new Color(0xAA3366));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                degreeObjectPanels[i].setName(String.valueOf(i));
                degreeObjectInfoLabels[i] = new JLabel();
            }

            this.degreeObjectPanels = degreeObjectPanels;
            this.degreeObjectInfoLabels= degreeObjectInfoLabels;
        }
    }

    public Object[] getDegreeObjects(){
        return null;
    }
    void setDegreeObjects(Object[] o) {
    }

    public void incrementSetAndAdd(Object[] o, JFrame parentFrame, String labelText){
        this.incrementSizeBy(o.length);
        this.setDegreeObjects(o);
        this.addDegreeObject(parentFrame, labelText, o.length);
    }

    public Object[] getInnerPanels(){
        return null;
    }

    public void showInfo(int index) {}

    public void hideInfo(int index) {}
}
