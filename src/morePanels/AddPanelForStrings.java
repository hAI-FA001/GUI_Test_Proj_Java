package morePanels;

import degreeObjects.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanelForStrings extends JPanel implements ActionListener {
    
    JFrame adderFrame;
    JPanel adderPanelsHolder, adderOptionsHolder;
    JPanel[] adderPanels;
    JLabel[] noLabels, descLabels;
    JTextField descInfo;
    JButton addBtn, doneBtn;

    String[] storedStrings;
    String textToAttach;

    public AddPanelForStrings(String[] storedStrings){
        adderFrame = new JFrame();
        adderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adderFrame.setSize(new Dimension(500, 500));
        adderFrame.setLocationRelativeTo(null);
        adderFrame.setLayout(new BorderLayout());
        adderFrame.setBackground(new Color(0x112233));



        adderPanelsHolder = new JPanel();
        adderPanelsHolder.setSize(new Dimension(3* adderFrame.getWidth()/4, adderFrame.getHeight()/2));
        adderPanelsHolder.setLayout(new GridLayout());
        adderPanelsHolder.setBackground(new Color(0x332244));
        adderPanelsHolder.setVisible(true);

        adderFrame.add(adderPanelsHolder, BorderLayout.CENTER);

        addStoredStringsToPanel(storedStrings);

        adderFrame.setVisible(true);
    }
    
    public AddPanelForStrings(JFrame parentFrame, String textToAttach){

        this.textToAttach = textToAttach;

        adderFrame = new JFrame();
        adderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adderFrame.setSize(new Dimension(500, 500));
        adderFrame.setLocationRelativeTo(null);
        adderFrame.setLocation(adderFrame.getLocation().x + 50, adderFrame.getLocation().y);
        adderFrame.setLayout(new BorderLayout());
        adderFrame.setBackground(new Color(0x112233));



        adderPanelsHolder = new JPanel();
        adderPanelsHolder.setSize(new Dimension(3* adderFrame.getWidth()/4, adderFrame.getHeight()/2));
        adderPanelsHolder.setLayout(new GridLayout());
        adderPanelsHolder.setBackground(new Color(0x332244));
        adderPanelsHolder.setVisible(true);



        adderOptionsHolder = new JPanel();
        adderOptionsHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        adderOptionsHolder.setSize(new Dimension(3* adderFrame.getWidth()/4, adderFrame.getHeight()/3));
        adderOptionsHolder.setBackground(new Color(0x443344));

        addBtn = new JButton("Add "+this.textToAttach);
        addBtn.setSize(new Dimension(adderOptionsHolder.getWidth()/4, adderOptionsHolder.getHeight()/3));
        addBtn.setBackground(new Color(0x664455));
        addBtn.setForeground(new Color(0xBB88AA));
        addBtn.addActionListener(this);
        addBtn.setFocusable(false);

        doneBtn = new JButton("Done");
        doneBtn.setSize(new Dimension(adderOptionsHolder.getWidth()/4, adderOptionsHolder.getHeight()/3));
        doneBtn.setBackground(new Color(0x664455));
        doneBtn.setForeground(new Color(0xBB88AA));
        doneBtn.addActionListener((ActionListener) parentFrame);
        doneBtn.setFocusable(false);

        descInfo = new JTextField(this.textToAttach+" Description");
        descInfo.setPreferredSize(new Dimension(adderOptionsHolder.getWidth()/2, adderOptionsHolder.getHeight()/6));
        descInfo.setBackground(new Color(0x554455));
        descInfo.setForeground(new Color(0xAA77BB));

        adderOptionsHolder.add(addBtn);
        adderOptionsHolder.add(doneBtn);
        adderOptionsHolder.add(descInfo);
        adderOptionsHolder.setVisible(true);



        adderFrame.add(adderPanelsHolder, BorderLayout.CENTER);
        adderFrame.add(adderOptionsHolder, BorderLayout.NORTH);

        adderFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){

            if(this.adderPanels == null){

                adderPanels = new JPanel[1];
                noLabels = new JLabel[1];
                descLabels = new JLabel[1];
                storedStrings = new String[1];

                storedStrings[0] = new String(descInfo.getText());

                adderPanels[0] = new JPanel();
                adderPanels[0].setLayout(new FlowLayout(FlowLayout.CENTER));
                adderPanels[0].setBackground(new Color(0x554455));

                noLabels[0] = new JLabel();

//                String spaces = "";
//                for(int i = 0; i < ((adderPanelsHolder.getWidth()-4)/2); i++)
//                    spaces += " ";
//
//                noLabels[0].setText(spaces+textToAttach+"-"+storedStrings.length+spaces);
                noLabels[0].setText(textToAttach+"-"+storedStrings.length);
                noLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
                noLabels[0].setForeground(new Color(0xBB7799));

                descLabels[0] = new JLabel();

//                spaces = "";
//                for(int i = 0; i < (adderPanelsHolder.getWidth()-descInfo.getText().length())/2; i++)
//                    spaces += " ";
//
//                descLabels[0].setText(spaces + descInfo.getText() + spaces);
                descLabels[0].setText(descInfo.getText());
                descLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
                descLabels[0].setForeground(new Color(0xBB7799));



                adderPanels[0].add(noLabels[0]);
                adderPanels[0].add(descLabels[0]);

                adderPanelsHolder.add(adderPanels[0]);
                adderPanelsHolder.setLayout(new GridLayout());
            }
            else {
                JPanel[] questionPanels = new JPanel[this.adderPanels.length+1];
                JLabel[] noLabels = new JLabel[this.noLabels.length+1];
                JLabel[] descLabels = new JLabel[this.descLabels.length+1];
                String[] storedStrings = new String[this.storedStrings.length+1];

                System.arraycopy(this.noLabels, 0, noLabels, 0, this.noLabels.length);
                System.arraycopy(this.descLabels, 0, descLabels, 0, this.descLabels.length);
                System.arraycopy(this.adderPanels, 0, questionPanels, 0, this.adderPanels.length);
                System.arraycopy(this.storedStrings, 0, storedStrings, 0, this.storedStrings.length);

                storedStrings[this.storedStrings.length] = new String(descInfo.getText());

                questionPanels[this.adderPanels.length] = new JPanel();
                questionPanels[this.adderPanels.length].setLayout(new FlowLayout(FlowLayout.CENTER));
                questionPanels[this.adderPanels.length].setBackground(new Color(0x554455));

                noLabels[this.noLabels.length] = new JLabel();

//                String spaces = "";
//                for(int i = 0; i < (adderPanelsHolder.getWidth()-4)/2; i++)
//                    spaces += " ";
//
//                noLabels[this.noLabels.length].setText(spaces + textToAttach + "-" + storedStrings.length + spaces);
                noLabels[this.noLabels.length].setText(textToAttach+"-"+storedStrings.length);
                noLabels[this.noLabels.length].setHorizontalAlignment(SwingConstants.CENTER);
                noLabels[this.noLabels.length].setForeground(new Color(0xBB7799));

                descLabels[this.descLabels.length] = new JLabel();

//                spaces = "";
//                for(int i = 0; i < (adderPanelsHolder.getWidth()-descInfo.getText().length())/2; i++)
//                    spaces += " ";
//
//                descLabels[this.descLabels.length].setText(spaces + descInfo.getText() + spaces);
                descLabels[this.descLabels.length].setText(descInfo.getText());
                descLabels[this.descLabels.length].setHorizontalAlignment(SwingConstants.CENTER);
                descLabels[this.descLabels.length].setForeground(new Color(0xBB7799));

                questionPanels[this.adderPanels.length].add(noLabels[this.noLabels.length]);
                questionPanels[this.adderPanels.length].add(descLabels[this.descLabels.length]);

                this.adderPanels = questionPanels;
                this.noLabels = noLabels;
                this.descLabels = descLabels;
                this.storedStrings = storedStrings;

                adderPanelsHolder.add(this.adderPanels[this.adderPanels.length-1]);

                adderPanelsHolder.setLayout(new GridLayout(
                        ((this.adderPanels.length % 4 > 0)? 1 : 0) + this.adderPanels.length/4,
                        4, 5, 5));
            } adderPanelsHolder.validate();
        }
    }

    public JButton getDoneBtn(){
        return doneBtn;
    }

    public String[] getStoredStrings(){
        return storedStrings;
    }

    public JFrame getAdderFrame(){
        return adderFrame;
    }

    public void addStoredStringsToPanel(String[] storedStrings){
        this.storedStrings = storedStrings;

        if(this.storedStrings == null || this.storedStrings.length == 0)
            return;

        JPanel[] questionPanels = new JPanel[this.storedStrings.length];
        JLabel[] noLabels = new JLabel[this.storedStrings.length];
        JLabel[] descLabels = new JLabel[this.storedStrings.length];

        for(int i=0; i < this.storedStrings.length; i++) {

            questionPanels[i] = new JPanel();
            questionPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            questionPanels[i].setBackground(new Color(0x554455));

            noLabels[i] = new JLabel();
            noLabels[i].setText("Q." + (i+1));
            noLabels[i].setForeground(new Color(0xBB7799));

            descLabels[i] = new JLabel();
            descLabels[i].setText(storedStrings[i]);
            descLabels[i].setForeground(new Color(0xBB7799));

            questionPanels[i].add(noLabels[i]);
            questionPanels[i].add(descLabels[i]);

            adderPanelsHolder.add(questionPanels[i]);
        }

        this.adderPanels = questionPanels;
        this.noLabels = noLabels;
        this.descLabels = descLabels;

        adderPanelsHolder.setLayout(new GridLayout(
                ((this.storedStrings.length % 4 > 0) ? 1 : 0) + this.storedStrings.length / 4,
                4, 5, 5));

        adderPanelsHolder.validate();
    }
}
