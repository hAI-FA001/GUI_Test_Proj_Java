package guiRevamp;

import degreeObjects.Course;
import degreeObjects.DegreeObjectCommon;
import static guiRevamp.constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ViewInfoPanel extends JPanel {

    final int panelWidth = FRAME_WIDTH, panelHeight = FRAME_HEIGHT/4;
    JButton viewDatesBtn;
    JLabel[] infoLabels;
    
    ViewInfoPanel(JFrame parentFrame){
        setBackground(new Color(0x330033));
//        setLayout(new FlowLayout(FlowLayout.CENTER));
//        ((FlowLayout)getLayout()).setVgap(50);
        setLayout(new GridBagLayout());
        
        setViewDatesBtn(parentFrame);
    }

    public void setViewDatesBtn(JFrame parentFrame){
        viewDatesBtn = new JButton("View Dates");

        viewDatesBtn.addActionListener((ActionListener) parentFrame);
        viewDatesBtn.setFocusable(false);
        viewDatesBtn.setBackground(new Color(0x7744AA));
        viewDatesBtn.setForeground(new Color(0xBBAAFF));
        viewDatesBtn.setFont(new Font("Consolas", Font.PLAIN, 15));
        viewDatesBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/7));
    }

    public void setViewInfoLabels(Object o, JFrame parentFrame){

        removeInfoLabels();

        Field[] fields = o.getClass().getDeclaredFields(),
                fieldsWithoutIgnoredFields = null;

        boolean hasDates = false;
        Object obj = null;

        for (int i = 0; i < fields.length; i++) {
            if (!(fields[i].getAnnotations() != null && fields[i].getAnnotations().length > 0
                    && fields[i].getAnnotations()[0].toString().toLowerCase().contains("ignore")
                    && fields[i].getAnnotations()[0].toString().toLowerCase().contains("optionspanel"))) {
                if(fields[i].getName().toLowerCase().contains("date")){

                    fields[i].setAccessible(true);
                    try {
                        obj = fields[i].get(o);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    hasDates = true;
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

        infoLabels = new JLabel[fieldsWithoutIgnoredFields.length];

        JPanel holdLabel = new JPanel();
        holdLabel.setLayout(new GridBagLayout());
        holdLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        holdLabel.setBackground(new Color(0x3B0033));
        holdLabel.setPreferredSize(new Dimension(panelWidth,
                panelHeight/(infoLabels.length)));

        final int INSET_VAL = 5, WEIGHT_X = 1, WEIGHT_Y = 1;
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = WEIGHT_X;
        c.weighty = WEIGHT_Y;
        c.ipadx = panelWidth/3;
        c.insets = new Insets(INSET_VAL, INSET_VAL, INSET_VAL, INSET_VAL);
        c.gridheight = 1;
        c.gridwidth = 1;

        for(int i=0, j=0; i < fieldsWithoutIgnoredFields.length; i++){
            infoLabels[i] = new JLabel();

            StringBuilder infoText = new StringBuilder();

            infoText.append(fieldsWithoutIgnoredFields[i].getName().toUpperCase().replace("_", " "))
                    .append(": ");

            Annotation[] annotations = fieldsWithoutIgnoredFields[i].getAnnotations();
            String[] secondAnnotation = new String[] {""};
            if(annotations != null && annotations.length > 0)
                secondAnnotation = annotations[0].toString().split(",");

            if(secondAnnotation.length > 1 && secondAnnotation[1].toLowerCase().contains("deg")) {
                Object[] degObj = ((DegreeObjectCommon) o).getInnerDegreeObject();

                if (o instanceof Course)
                    degObj = (Object[]) (fieldsWithoutIgnoredFields[i].getName().toLowerCase().contains("quiz")?
                            ((Course) o).getInnerDegreeObject()[1] : ((Course) o).getInnerDegreeObject()[0]);

                if (degObj == null)
                    infoText.append("0");
                else {
                    infoText.append(degObj.length);
                }
            }
            else {
                fieldsWithoutIgnoredFields[i].setAccessible(true);
                try {
                    infoText.append(fieldsWithoutIgnoredFields[i].get(o));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            infoLabels[i].setText(String.valueOf(infoText));
            infoLabels[i].setHorizontalAlignment(SwingConstants.CENTER);

            infoLabels[i].setForeground(new Color(0x774455));
            infoLabels[i].setPreferredSize(new Dimension(panelWidth/3, panelHeight/6));

            int check = (j+1) % 3;
            c.gridx = j % 3;
            c.gridy = j / 3;
            c.anchor = (check == 0)? GridBagConstraints.LINE_START :
                    (check == 1)? GridBagConstraints.CENTER : GridBagConstraints.LINE_END;

            if(annotations != null && annotations.length > 0 && annotations[0].toString().toLowerCase().contains("main")) {
                infoLabels[i].setPreferredSize(new Dimension(panelWidth, panelHeight/3));

                c.gridwidth = 2;
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.CENTER;
                this.add(infoLabels[i], c);

                c.gridwidth = 1;
                continue;
            }
            else
                holdLabel.add(infoLabels[i], c);
            ++j;
        }
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(INSET_VAL, panelWidth/16, INSET_VAL, panelWidth/16);
        this.add(holdLabel, c);

        if(hasDates && obj != null){

            setViewDatesBtn(parentFrame);

            viewDatesBtn.setEnabled(true);
            viewDatesBtn.setVisible(true);

            this.add(viewDatesBtn);

        }

        this.validate();
    }

    public void removeInfoLabels(){
        this.removeAll();
        this.repaint();
    }

}
