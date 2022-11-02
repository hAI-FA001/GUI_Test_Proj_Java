package guiRevamp;

import degreeObjects.Course;
import degreeObjects.DegreeObjectCommon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class OptionsPanel extends JPanel{

    JPanel viewDegreeObjectInfoPanel, btnsPanelMain, btnsSubContainer, btnsPanelDeg, btnsPanelCourse;
    JLabel[] infoLabels;
    JButton addDegreeBtn, addSemesterBtn, addCourseBtn, addAssignmentBtn, addQuizBtn, addQuestionBtn, addTopicBtn,
            goBackBtn, viewDatesBtn;
    boolean isBtnPanelSetForDegree = true;
    String[] strsForBtnsSubContainer = {"Degree", "Course"};

    OptionsPanel(int frameWidth, int frameHeight, JFrame parentFrame){
        this.setBackground(new Color(0x220033));
        this.setLayout(new BorderLayout(10,5));
        this.setPreferredSize(new Dimension(frameWidth/4, frameHeight));

        setAddBtns(parentFrame);
        setGoBackBtn(parentFrame);

        setBtnsPanel();
        setViewDegreeInfoPanel();
    }

    public void setAddBtns(JFrame parentFrame){

        setAddDegreeBtn(parentFrame);
        setAddSemesterBtn(parentFrame);
        setAddCourseBtn(parentFrame);

        setAddAssignmentBtn(parentFrame);
        setAddQuizBtn(parentFrame);
        setAddQuestionBtn(parentFrame);
        setAddTopicBtn(parentFrame);
    }

    public void setAddDegreeBtn(JFrame parentFrame){
        addDegreeBtn = new JButton("Add Degree");

        addDegreeBtn.addActionListener((ActionListener) parentFrame);
        addDegreeBtn.setFocusable(false);
        addDegreeBtn.setBackground(new Color(0x220022));
        addDegreeBtn.setForeground(new Color(0xAA55AA));
        addDegreeBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addDegreeBtn.setPreferredSize(new Dimension(150, 25));


        //addDegreeBtn.setPreferredSize(new Dimension(getWidth()/4, getHeight()/8));
    }

    public void setAddSemesterBtn(JFrame parentFrame){
        addSemesterBtn = new JButton("Add Semester");

        addSemesterBtn.addActionListener((ActionListener) parentFrame);
        addSemesterBtn.setFocusable(false);
        addSemesterBtn.setBackground(new Color(0x220022));
        addSemesterBtn.setForeground(new Color(0xAA55AA));
        addSemesterBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addSemesterBtn.setPreferredSize(new Dimension(150, 25));
        addSemesterBtn.setEnabled(false);
    }

    public void setAddCourseBtn(JFrame parentFrame){
        addCourseBtn = new JButton("Add Course");

        addCourseBtn.addActionListener((ActionListener) parentFrame);
        addCourseBtn.setFocusable(false);
        addCourseBtn.setBackground(new Color(0x220022));
        addCourseBtn.setForeground(new Color(0xAA55AA));
        addCourseBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addCourseBtn.setPreferredSize(new Dimension(150, 25));
        addCourseBtn.setEnabled(false);
    }
    public void setAddAssignmentBtn(JFrame parentFrame){
        addAssignmentBtn = new JButton("Add Assignment");

        addAssignmentBtn.addActionListener((ActionListener) parentFrame);
        addAssignmentBtn.setFocusable(false);
        addAssignmentBtn.setBackground(new Color(0x220022));
        addAssignmentBtn.setForeground(new Color(0xAA55AA));
        addAssignmentBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addAssignmentBtn.setPreferredSize(new Dimension(150, 25));
        addAssignmentBtn.setEnabled(false);
    }
    public void setAddQuizBtn(JFrame parentFrame){
        addQuizBtn = new JButton("Add Quiz");

        addQuizBtn.addActionListener((ActionListener) parentFrame);
        addQuizBtn.setFocusable(false);
        addQuizBtn.setBackground(new Color(0x220022));
        addQuizBtn.setForeground(new Color(0xAA55AA));
        addQuizBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addQuizBtn.setPreferredSize(new Dimension(150, 25));
        addQuizBtn.setEnabled(false);
    }

    public void setAddQuestionBtn(JFrame parentFrame){
        addQuestionBtn = new JButton("Add Question");

        addQuestionBtn.addActionListener((ActionListener) parentFrame);
        addQuestionBtn.setFocusable(false);
        addQuestionBtn.setBackground(new Color(0x220022));
        addQuestionBtn.setForeground(new Color(0xAA55AA));
        addQuestionBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addQuestionBtn.setPreferredSize(new Dimension(150, 25));
        addQuestionBtn.setEnabled(false);
    }

    public void setAddTopicBtn(JFrame parentFrame){
        addTopicBtn = new JButton("Add Topic");

        addTopicBtn.addActionListener((ActionListener) parentFrame);
        addTopicBtn.setFocusable(false);
        addTopicBtn.setBackground(new Color(0x220022));
        addTopicBtn.setForeground(new Color(0xAA55AA));
        addTopicBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        addTopicBtn.setPreferredSize(new Dimension(150, 25));
        addTopicBtn.setEnabled(false);
    }

    public void setGoBackBtn(JFrame parentFrame){
        goBackBtn = new JButton("Go Back");

        goBackBtn.addActionListener((ActionListener) parentFrame);
        goBackBtn.setFocusable(false);
        goBackBtn.setBackground(new Color(0x220022));
        goBackBtn.setForeground(new Color(0xAA55AA));
        goBackBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        goBackBtn.setPreferredSize(new Dimension(150, 25));
        goBackBtn.setEnabled(false);
    }

    public void setBtnsPanel(){
        setBtnsPanelDeg();
        setBtnsPanelCourse();

        setBtnsSubContainer();
        setBtnsSubContainerForDegree();

        btnsPanelMain = new JPanel();
        btnsPanelMain.setLayout(new GridBagLayout());
        btnsPanelMain.setBackground(new Color(0x330033));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;

        btnsPanelMain.add(btnsSubContainer, c);

        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        btnsPanelMain.add(goBackBtn, c);

        add(btnsPanelMain, BorderLayout.NORTH);
    }

    public void setBtnsPanelDeg(){
        btnsPanelDeg = new JPanel();
        btnsPanelDeg.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnsPanelDeg.setBackground(new Color(0x330033));
        btnsPanelDeg.setPreferredSize(new Dimension(getWidth(), 100));

        btnsPanelDeg.add(addDegreeBtn);
        btnsPanelDeg.add(addSemesterBtn);
        btnsPanelDeg.add(addCourseBtn);
    }

    public void setBtnsPanelCourse(){
        btnsPanelCourse = new JPanel();
        btnsPanelCourse.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnsPanelCourse.setBackground(new Color(0x330033));
        btnsPanelCourse.setPreferredSize(new Dimension(getWidth(), 100));

        btnsPanelCourse.add(addAssignmentBtn);
        btnsPanelCourse.add(addQuizBtn);
        btnsPanelCourse.add(addQuestionBtn);
    }

    public void setBtnsSubContainer(){
        btnsSubContainer = new JPanel();
        btnsSubContainer.setLayout(new CardLayout());
        btnsSubContainer.setPreferredSize(new Dimension(getWidth(), 100));
        btnsSubContainer.add(btnsPanelDeg, strsForBtnsSubContainer[0]);
        btnsSubContainer.add(btnsPanelCourse, strsForBtnsSubContainer[1]);
    }

    public void setBtnsSubContainerForDegree(){
        ((CardLayout) btnsSubContainer.getLayout()).show(btnsSubContainer, strsForBtnsSubContainer[0]);
        isBtnPanelSetForDegree = true;
        btnsSubContainer.validate();
    }

    public void setBtnsSubContainerForCourse(){
        ((CardLayout) btnsSubContainer.getLayout()).show(btnsSubContainer, strsForBtnsSubContainer[1]);
        isBtnPanelSetForDegree = false;
        btnsSubContainer.validate();
    }

    public void setViewDatesBtn(JFrame parentFrame){
        viewDatesBtn = new JButton("View Dates");

        viewDatesBtn.addActionListener((ActionListener) parentFrame);
        viewDatesBtn.setFocusable(false);
        viewDatesBtn.setBackground(new Color(0x7744AA));
        viewDatesBtn.setForeground(new Color(0xBBAAFF));
        viewDatesBtn.setFont(new Font("Consolas", Font.PLAIN, 15));
        viewDatesBtn.setPreferredSize(new Dimension(150, 25));
    }

    public void setViewDegreeInfoPanel(){
        viewDegreeObjectInfoPanel = new JPanel();

        viewDegreeObjectInfoPanel.setBackground(new Color(0x330033));
        viewDegreeObjectInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ((FlowLayout)viewDegreeObjectInfoPanel.getLayout()).setVgap(50);

        add(viewDegreeObjectInfoPanel, BorderLayout.CENTER);

        //viewDegreeInfoPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()/4));
    }

    public void setBtnEnabled(boolean en1, boolean en2, boolean en3, boolean en4){
        if(isBtnPanelSetForDegree){
            addDegreeBtn.setEnabled(en1);
            addSemesterBtn.setEnabled(en2);
            addCourseBtn.setEnabled(en3);

        }
        else {
            addAssignmentBtn.setEnabled(en1);
            addQuizBtn.setEnabled(en2);
            addQuestionBtn.setEnabled(en3);
            addTopicBtn.setEnabled(en3);

        }
        goBackBtn.setEnabled(en4);

        btnsPanelMain.validate();
    }

    public void swapFirstWithSecondBtn(JButton first, JButton second){

        JPanel btnPanel = isBtnPanelSetForDegree? btnsPanelDeg : btnsPanelCourse;

        for(Component c : btnPanel.getComponents())
            if(second == c)
                return;

        btnPanel.remove(first);
        btnPanel.add(second);
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

        for(int i=0; i < fieldsWithoutIgnoredFields.length; i++){
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
            infoLabels[i].setPreferredSize(new Dimension(getWidth(), 50));

            JPanel holdLabel = new JPanel();
            holdLabel.setLayout(new GridBagLayout());
            holdLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            holdLabel.setBackground(new Color(0x3B0033));
            holdLabel.setPreferredSize(new Dimension(4*viewDegreeObjectInfoPanel.getWidth()/5,
                    viewDegreeObjectInfoPanel.getHeight()/(2*infoLabels.length)));
            holdLabel.add(infoLabels[i]);

            viewDegreeObjectInfoPanel.add(holdLabel);
        }

        if(hasDates && obj != null){

            setViewDatesBtn(parentFrame);

            viewDatesBtn.setEnabled(true);
            viewDatesBtn.setVisible(true);

            viewDegreeObjectInfoPanel.add(viewDatesBtn);

        }

        viewDegreeObjectInfoPanel.validate();
    }

    public void removeInfoLabels(){
        viewDegreeObjectInfoPanel.removeAll();
    }

}
