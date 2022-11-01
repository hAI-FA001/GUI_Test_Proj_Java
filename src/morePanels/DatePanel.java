package morePanels;

import degreeObjects.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatePanel extends JPanel implements ActionListener {
    
    JFrame dateFrame;
    JPanel datePanelsHolder, dateOptionsHolder;
    JPanel[] datePanels;
    JLabel[] dateLabels, descLabels;
    JTextField dateInfo, descInfo;
    JButton addDateBtn, doneBtn;

    Date[] dates;

    public DatePanel(Date[] dates){
        dateFrame = new JFrame();
        dateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dateFrame.setSize(new Dimension(500, 500));
        dateFrame.setLocationRelativeTo(null);
        dateFrame.setLayout(new BorderLayout());
        dateFrame.setBackground(new Color(0x112233));



        datePanelsHolder = new JPanel();
        datePanelsHolder.setSize(new Dimension(3*dateFrame.getWidth()/4, dateFrame.getHeight()/2));
        datePanelsHolder.setLayout(new GridLayout());
        datePanelsHolder.setBackground(new Color(0x332244));
        datePanelsHolder.setVisible(true);

        dateFrame.add(datePanelsHolder, BorderLayout.CENTER);

        addDatesToPanel(dates);

        dateFrame.setVisible(true);
    }
    
    public DatePanel(JFrame parentFrame){
        dateFrame = new JFrame();
        dateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dateFrame.setSize(new Dimension(500, 500));
        dateFrame.setLocationRelativeTo(null);
        dateFrame.setLocation(dateFrame.getLocation().x + 50, dateFrame.getLocation().y);
        dateFrame.setLayout(new BorderLayout());
        dateFrame.setBackground(new Color(0x112233));



        datePanelsHolder = new JPanel();
        datePanelsHolder.setSize(new Dimension(3*dateFrame.getWidth()/4, dateFrame.getHeight()/2));
        datePanelsHolder.setLayout(new GridLayout());
        datePanelsHolder.setBackground(new Color(0x332244));
        datePanelsHolder.setVisible(true);



        dateOptionsHolder = new JPanel();
        dateOptionsHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        dateOptionsHolder.setSize(new Dimension(3*dateFrame.getWidth()/4, dateFrame.getHeight()/3));
        dateOptionsHolder.setBackground(new Color(0x443344));

        addDateBtn = new JButton("Add Date");
        addDateBtn.setSize(new Dimension(dateOptionsHolder.getWidth()/4, dateOptionsHolder.getHeight()/3));
        addDateBtn.setBackground(new Color(0x664455));
        addDateBtn.setForeground(new Color(0xBB88AA));
        addDateBtn.addActionListener(this);
        addDateBtn.setFocusable(false);

        doneBtn = new JButton("Done");
        doneBtn.setSize(new Dimension(dateOptionsHolder.getWidth()/4, dateOptionsHolder.getHeight()/3));
        doneBtn.setBackground(new Color(0x664455));
        doneBtn.setForeground(new Color(0xBB88AA));
        doneBtn.addActionListener((ActionListener) parentFrame);
        doneBtn.setFocusable(false);

        dateInfo = new JTextField("Date");
        dateInfo.setPreferredSize(new Dimension(3*dateOptionsHolder.getWidth()/8, dateOptionsHolder.getHeight()/6));
        dateInfo.setBackground(new Color(0x554455));
        dateInfo.setForeground(new Color(0xAA77BB));

        descInfo = new JTextField("Description");
        descInfo.setPreferredSize(new Dimension(3*dateOptionsHolder.getWidth()/8, dateOptionsHolder.getHeight()/6));
        descInfo.setBackground(new Color(0x554455));
        descInfo.setForeground(new Color(0xAA77BB));

        dateOptionsHolder.add(addDateBtn);
        dateOptionsHolder.add(doneBtn);
        dateOptionsHolder.add(dateInfo);
        dateOptionsHolder.add(descInfo);
        dateOptionsHolder.setVisible(true);



        dateFrame.add(datePanelsHolder, BorderLayout.CENTER);
        dateFrame.add(dateOptionsHolder, BorderLayout.NORTH);

        dateFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addDateBtn){

            if(!verifyDate()) {
                JOptionPane.showMessageDialog(null, "Error! Check entered date.");
                return;
            }

            if(this.datePanels == null){

                datePanels = new JPanel[1];
                dateLabels = new JLabel[1];
                descLabels = new JLabel[1];
                dates = new Date[1];

                dates[0] = parseDate(dateInfo.getText());
                dates[0].setDescription(descInfo.getText());

                datePanels[0] = new JPanel();
                datePanels[0].setLayout(new FlowLayout(FlowLayout.CENTER));
                datePanels[0].setBackground(new Color(0x554455));

                dateLabels[0] = new JLabel();
//                String spaces = "";
//                for(int i=0; i < (datePanelsHolder.getWidth()-dateInfo.getText().length())/2; i++)
//                    spaces += " ";
//              dateLabels[0].setText(spaces+dateInfo.getText()+spaces);
                dateLabels[0].setText(dateInfo.getText());
                dateLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
                dateLabels[0].setForeground(new Color(0xBB7799));

                descLabels[0] = new JLabel();
//                for(int i=0; i < (datePanelsHolder.getWidth()-descInfo.getText().length())/2; i++)
//                    spaces += " ";
//                descLabels[0].setText(spaces + descInfo.getText() + spaces);
                descLabels[0].setText(descInfo.getText());
                descLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
                descLabels[0].setForeground(new Color(0xBB7799));



                datePanels[0].add(dateLabels[0]);
                datePanels[0].add(descLabels[0]);

                datePanelsHolder.add(datePanels[0]);
                datePanelsHolder.setLayout(new GridLayout());
            }
            else {
                JPanel[] datePanels = new JPanel[this.datePanels.length+1];
                JLabel[] dateLabels = new JLabel[this.dateLabels.length+1];
                JLabel[] descLabels = new JLabel[this.descLabels.length+1];
                Date[] dates = new Date[this.dates.length+1];

                System.arraycopy(this.dateLabels, 0, dateLabels, 0, this.dateLabels.length);
                System.arraycopy(this.descLabels, 0, descLabels, 0, this.descLabels.length);
                System.arraycopy(this.datePanels, 0, datePanels, 0, this.datePanels.length);
                System.arraycopy(this.dates, 0, dates, 0, this.dates.length);

                dates[this.dates.length] = parseDate(dateInfo.getText());
                dates[this.dates.length].setDescription(descInfo.getText());

                datePanels[this.datePanels.length] = new JPanel();
                datePanels[this.datePanels.length].setLayout(new FlowLayout(FlowLayout.CENTER));
                datePanels[this.datePanels.length].setBackground(new Color(0x554455));

                dateLabels[this.dateLabels.length] = new JLabel();
//                String spaces = "";
//                for(int i=0; i < (datePanelsHolder.getWidth()-dateInfo.getText().length())/2; i++)
//                    spaces += " ";
//                dateLabels[this.dateLabels.length].setText(spaces + dateInfo.getText() + spaces);
                dateLabels[this.dateLabels.length].setText(dateInfo.getText());
                dateLabels[this.dateLabels.length].setHorizontalAlignment(SwingConstants.CENTER);
                dateLabels[this.dateLabels.length].setForeground(new Color(0xBB7799));

                descLabels[this.descLabels.length] = new JLabel();
//                for(int i=0; i < (datePanelsHolder.getWidth()-descInfo.getText().length())/2; i++)
//                    spaces += " ";
//                descLabels[this.descLabels.length].setText(spaces + descInfo.getText() + spaces);
                descLabels[this.descLabels.length].setText(descInfo.getText());
                descLabels[this.descLabels.length].setHorizontalAlignment(SwingConstants.CENTER);
                descLabels[this.descLabels.length].setForeground(new Color(0xBB7799));

                datePanels[this.datePanels.length].add(dateLabels[this.dateLabels.length]);
                datePanels[this.datePanels.length].add(descLabels[this.descLabels.length]);

                this.datePanels = datePanels;
                this.dateLabels = dateLabels;
                this.descLabels = descLabels;
                this.dates = dates;

                datePanelsHolder.add(this.datePanels[this.datePanels.length-1]);

                datePanelsHolder.setLayout(new GridLayout(
                        ((this.datePanels.length % 4 > 0)? 1 : 0) + this.datePanels.length/4,
                        4, 5, 5));
            } datePanelsHolder.validate();
        }
    }

    public boolean verifyDate(){

       String date = dateInfo.getText();

       if(!java.util.regex.Pattern.matches("^((0*[1-9])|([1-2][0-9])|(3[0-1]))[^0-9]((0*[1-9])|(1[0-2]))[^0-9]([0-9]+)$", date))
           return false;

       return true;
    }

    public Date parseDate(String d){
        int day = 0, month = 0, year = 0;
        boolean readingDay = true, readingMonth = false, readingYear = false;

        for(int i=0; i < d.length(); i++){
            char c = d.charAt(i);

            if(c < '0' || c > '9'){
                if(readingDay){
                    readingDay = false;
                    readingMonth = true;
                }
                else{
                    readingMonth = false;
                    readingYear = true;
                }
                continue;
            }

            if(readingDay)
                day = day==0? Integer.parseInt(String.valueOf(c)) : day*10 + Integer.parseInt(String.valueOf(c));
            if(readingMonth)
                month = month==0? Integer.parseInt(String.valueOf(c)) : month*10 + Integer.parseInt(String.valueOf(c));
            if(readingYear)
                year = year==0? Integer.parseInt(String.valueOf(c)) : year*10 + Integer.parseInt(String.valueOf(c));
        }
        return new Date(day, month, year);
    }

    public JButton getDoneBtn(){
        return doneBtn;
    }

    public Date[] getDates(){
        return dates;
    }

    public JFrame getDateFrame(){
        return dateFrame;
    }

    public void addDatesToPanel(Date[] dates){
        this.dates = dates;

        if(this.dates == null || this.dates.length == 0)
            return;

        JPanel[] datePanels = new JPanel[this.dates.length];
        JLabel[] dateLabels = new JLabel[this.dates.length];
        JLabel[] descLabels = new JLabel[this.dates.length];

        for(int i=0; i < this.dates.length; i++) {

            datePanels[i] = new JPanel();
            datePanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            datePanels[i].setBackground(new Color(0x554455));

            dateLabels[i] = new JLabel();
            dateLabels[i].setText(dates[i].getDay() + "/" + dates[i].getMonth() + "/" + dates[i].getYear());
            dateLabels[i].setForeground(new Color(0xBB7799));

            descLabels[i] = new JLabel();
            descLabels[i].setText(dates[i].getDescription());
            descLabels[i].setForeground(new Color(0xBB7799));

            datePanels[i].add(dateLabels[i]);
            datePanels[i].add(descLabels[i]);

            datePanelsHolder.add(datePanels[i]);
        }

        this.datePanels = datePanels;
        this.dateLabels = dateLabels;
        this.descLabels = descLabels;

        datePanelsHolder.setLayout(new GridLayout(
                ((this.dates.length % 4 > 0) ? 1 : 0) + this.dates.length / 4,
                4, 5, 5));

        datePanelsHolder.validate();
    }
}
