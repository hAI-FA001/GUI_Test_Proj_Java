package morePanels;

import degreeObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class GetInfoPanel extends JPanel implements FocusListener {

    JTextField[] textFields;
    JLabel[] labels;
    JButton submitBtn, datesBtn, setBtn;
    JFrame frameForInfoPanel;
    JPanel infoPanel;

    String[] valuesFromTextFields;

    Object toSetInfoOf;

    int locX, locY, compWidth, compHeight;

    public GetInfoPanel(){

    }

    public GetInfoPanel(Object o, JFrame parentFrame) {

        toSetInfoOf = o;

        frameForInfoPanel = new JFrame();

        frameForInfoPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameForInfoPanel.setSize(new Dimension(250, 250));
        frameForInfoPanel.setLocationRelativeTo(null);
        frameForInfoPanel.setLayout(null);
        frameForInfoPanel.setResizable(false);

        infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(20, 0,
                        new Color(0x330022), getWidth()-50, getHeight()/5, new Color(0x331125));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        infoPanel.setSize(new Dimension(250, 250));
        infoPanel.setLayout(null);
        //infoPanel.setBackground(new Color(0x333344));


        Field[] fields = o.getClass().getDeclaredFields(),
                fieldsWithoutIgnoredFields = null;

        boolean containsDate = false, skipTextAndLabelFields = false;
        String dateBtnText = "";

        setBtn = null;
        String setBtnText = "";




        for (int i = 0; i < fields.length; i++) {

            Annotation[] annotations = fields[i].getAnnotations();
            String firstAnnotationStr = "";

            if(annotations != null && annotations.length > 0)
                firstAnnotationStr += annotations[0].toString().toLowerCase();

            if (!(firstAnnotationStr.contains("ignore") && firstAnnotationStr.contains("infopanel"))) {

                if(fields[i].getName().toLowerCase().contains("date")){
                    containsDate = true;

                    dateBtnText = fields[i].getName().toUpperCase();
                    dateBtnText = "SET " +  dateBtnText.substring(0, dateBtnText.toLowerCase().lastIndexOf("date"))
                            + " " + dateBtnText.substring(dateBtnText.toLowerCase().lastIndexOf("date"));
                    continue;
                }
                else if(firstAnnotationStr.contains("make set button")){
                    setBtn = new JButton();

                    setBtnText += "SET " + fields[i].getName().toUpperCase();
                    continue;
                }

                if(fieldsWithoutIgnoredFields == null)
                    fieldsWithoutIgnoredFields = new Field[] {fields[i]};
                else {
                    Field[] holdFields = new Field[fieldsWithoutIgnoredFields.length+1];
                    System.arraycopy(fieldsWithoutIgnoredFields, 0, holdFields, 0, fieldsWithoutIgnoredFields.length);
                    holdFields[fieldsWithoutIgnoredFields.length] = fields[i];
                    fieldsWithoutIgnoredFields = holdFields;
                }
            }
        }

        int noOfComponents = 1, noOfComponentsBeforeThisPoint = 0;

        if(fieldsWithoutIgnoredFields == null)
            skipTextAndLabelFields = true;

        if(!skipTextAndLabelFields) {
            textFields = new JTextField[fieldsWithoutIgnoredFields.length];
            labels = new JLabel[textFields.length];
            noOfComponents += textFields.length;
        }

        if(containsDate)
            noOfComponents += 1;

        if(setBtn != null)
            noOfComponents += 1;

        locX = 5;
        locY = 5;
        compWidth = infoPanel.getWidth() / 3 - locX;
        compHeight = infoPanel.getHeight() / (noOfComponents + 1);

        if(!skipTextAndLabelFields) {
            for (int i = 0; i < fieldsWithoutIgnoredFields.length; i++) {

                labels[i] = new JLabel();

                String labelText = fieldsWithoutIgnoredFields[i].getName().toUpperCase();
    //            if(labelText.toLowerCase().contains("no"))
    //                labelText = "No of " + labelText.substring(0, labelText.lastIndexOf("NO"));

                labels[i].setText(labelText);
                labels[i].setBounds(locX, locY + i * compHeight, compWidth, compHeight - locY);
                labels[i].setForeground(new Color(0x773366));
                labels[i].setHorizontalAlignment(SwingConstants.CENTER);

                textFields[i] = new JTextField(fieldsWithoutIgnoredFields[i].getName(), SwingConstants.CENTER);
                textFields[i].setBounds(locX + compWidth, locY + i * compHeight,
                        infoPanel.getWidth() - (locX + (5 * compWidth / 4)), compHeight - locY);
                textFields[i].setHorizontalAlignment(SwingConstants.CENTER);
                textFields[i].addFocusListener(this);
                textFields[i].setBackground(new Color(0x440011));
                textFields[i].setForeground(new Color(0xCC66AA));

                infoPanel.add(textFields[i]);
                infoPanel.add(labels[i]);
            }

            noOfComponentsBeforeThisPoint += textFields.length;
        }

        if(setBtn != null){

            setBtn.setText(setBtnText);
            setBtn.setBounds(infoPanel.getWidth()/8,
                    locY + (noOfComponentsBeforeThisPoint) * compHeight,
                    6*infoPanel.getWidth()/8, compHeight - locY);
            setBtn.setBackground(new Color(0x661544));
            setBtn.setForeground(new Color(0x996688));
            setBtn.setFocusable(false);
            setBtn.addActionListener((ActionListener) parentFrame);

            infoPanel.add(setBtn);

            noOfComponentsBeforeThisPoint++;
        }


        if(containsDate){
            datesBtn = new JButton();

            datesBtn.setText(dateBtnText);
            datesBtn.setBounds(infoPanel.getWidth()/8,
                    locY + (noOfComponentsBeforeThisPoint * compHeight),
                    6*infoPanel.getWidth()/8, compHeight - locY);
            datesBtn.setBackground(new Color(0x661544));
            datesBtn.setForeground(new Color(0x996688));
            datesBtn.setFocusable(false);
            datesBtn.addActionListener((ActionListener) parentFrame);

            infoPanel.add(datesBtn);

            noOfComponentsBeforeThisPoint++;
        }

        submitBtn = new JButton("SUBMIT");
        submitBtn.setBounds(infoPanel.getWidth() / 4,
                noOfComponentsBeforeThisPoint*compHeight + 3*locY,
                infoPanel.getWidth() / 2, compHeight - 3*locY);

        submitBtn.setBackground(new Color(0x661544));
        submitBtn.setForeground(new Color(0x996688));
        submitBtn.setFocusable(false);
        submitBtn.addActionListener((ActionListener) parentFrame);

        infoPanel.add(submitBtn);
        infoPanel.setVisible(true);

        frameForInfoPanel.add(infoPanel);
        frameForInfoPanel.setVisible(true);
    }

    public Object getObjectInfo() {

        StringBuilder valuesString = new StringBuilder();

        valuesFromTextFields = new String[] {""};

        if(textFields != null) {
            for (JTextField textField : textFields) {
                valuesString.append(textField.getText()).append(",");
            }
            valuesFromTextFields = valuesString.substring(0, valuesString.toString().lastIndexOf(',')).split(",");
        }

        if(textFields != null)
            if(valuesFromTextFields.length < textFields.length) {
                JOptionPane.showMessageDialog(null, "Error! Values cannot be empty.");
                return null;
            }

        int checkValidation = validateValues(valuesFromTextFields);
        if(!(checkValidation == 0)) {
            switch (checkValidation){
                case 1:
                    JOptionPane.showMessageDialog(null, "Error! Values cannot be empty.");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Error! Found incorrect integer value.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error! Check entered values.");
            }
            return null;
        }

        switch (toSetInfoOf.getClass().getSimpleName()) {
            case "DegreeProgram": {
                toSetInfoOf = new DegreeProgram(null, Integer.parseInt(valuesFromTextFields[0]),
                        valuesFromTextFields[1], new ImageIcon());
            }
            break;
            case "Semester": {
                toSetInfoOf = new Semester(null, 0,
                        valuesFromTextFields[0], null);
            }
            break;
            case "Course": {
                toSetInfoOf = new Course(valuesFromTextFields[0],valuesFromTextFields[1],
                        valuesFromTextFields[2], Integer.parseInt(valuesFromTextFields[3]),null,null);
            }
            break;
            case "Assignment": {
                toSetInfoOf = new Assignment();
            }
            break;
            case "Quiz": {
                toSetInfoOf = new Quiz();
            }
            break;
            case "Question": {
                toSetInfoOf = new Question(valuesFromTextFields[0]);
            }
            break;
            case "Topic": {
                toSetInfoOf = new Topic(valuesFromTextFields[0]);
            }
            break;
            default:
                System.out.println("ERROR! Unknown Object");
        }

        this.frameForInfoPanel.dispose();

        return toSetInfoOf;
    }

    private int validateValues(String[] values) {
        Field[] fields = toSetInfoOf.getClass().getDeclaredFields(),
                fieldsWithoutIgnoredFields = null;

        for (int i = 0, j = 0; i < fields.length; i++) {
            if (!(fields[i].getAnnotations() != null && fields[i].getAnnotations().length > 0
                    && fields[i].getAnnotations()[0].toString().toLowerCase().contains("ignore")
                    && fields[i].getAnnotations()[0].toString().toLowerCase().contains("infopanel"))) {
                if(fieldsWithoutIgnoredFields == null)
                    fieldsWithoutIgnoredFields = new Field[] {fields[i]};
                else {
                    Field[] holdFields = new Field[fieldsWithoutIgnoredFields.length+1];
                    System.arraycopy(fieldsWithoutIgnoredFields, 0, holdFields, 0, fieldsWithoutIgnoredFields.length);
                    holdFields[fieldsWithoutIgnoredFields.length] = fields[i];
                    fieldsWithoutIgnoredFields = holdFields;
                }
            }
        }

        for(int i=0; i < values.length; i++){
            if((values[i].equals("") || values[i].equals(" ")) && setBtn == null)
                return 1;
            if(fieldsWithoutIgnoredFields != null && fieldsWithoutIgnoredFields[i].toString().contains("int"))
                for(char c : values[i].toCharArray()){
                    if(c < '0' || c > '9')
                        return 2;
                }
        }

        return 0;
    }

    public JButton getSubmitBtn(){
        return submitBtn;
    }

    public JButton getDatesBtn(){
        return datesBtn;
    }

    public JButton getSetBtn(){
        return  setBtn;
    }

    @Override
    public void focusGained(FocusEvent e) {
        for(int i=0; i < textFields.length; i++)
            if(e.getSource() == textFields[i])
                if(textFields[i].getText().equalsIgnoreCase(labels[i].getText()))
                    textFields[i].setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {

        for(int i=0; i < textFields.length; i++)
            if(e.getSource() == textFields[i])
                if (textFields[i].getText().equals(""))
                    textFields[i].setText(labels[i].getText().toLowerCase());
    }
}
