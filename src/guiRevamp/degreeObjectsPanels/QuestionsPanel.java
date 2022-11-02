package guiRevamp.degreeObjectsPanels;

import degreeObjects.Question;

import javax.swing.*;
import java.awt.*;


public class QuestionsPanel extends GeneralDegreeObjectPanel {
    public Question[] questions;

    QuestionsPanel(int parentPanelNo){
        super();

        this.parentPanelNo = parentPanelNo;
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(questions == null){
            questions = new Question[size];

            for(int i=0; i < questions.length; i++){
                questions[i] = new Question();
            }
        }
        else {
            Question[] questions = new Question[this.questions.length + size];

            System.arraycopy(this.questions, 0, questions, 0, this.questions.length);

            for(int i=this.questions.length; i < questions.length; i++){
                questions[i] = new Question();
            }

            this.questions = questions;
        }
    }

    @Override
    public Question[] getDegreeObjects(){
        return questions;
    }

    @Override
    void setDegreeObjects(Object[] o) {
        if(this.questions.length == o.length)
            this.questions = (Question[]) o;
        else {
            for(int i=this.questions.length-o.length; i < this.questions.length; i++)
                this.questions[i] = (Question) o[i];
        }
    }

    @Override
    public void incrementSetAndAdd(Object[] o, JFrame parentFrame, String labelText){
        this.incrementSizeBy(o.length);
        this.setDegreeObjects(o);
        this.addDegreeObject(parentFrame, labelText, o.length);
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s</pre></html>",
                        questions[index].getDesc())
        );
        infoLabelOnHover.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        infoLabelOnHover.setForeground(new Color(0x774455));
        infoLabelOnHover.setHorizontalAlignment(SwingConstants.LEADING);
        infoLabelOnHover.setVerticalAlignment(SwingConstants.CENTER);
        infoLabelOnHover.setPreferredSize(new Dimension(250, 40));

        degreeObjectPanels[index].add(infoLabelOnHover, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void hideInfo(int index){
        super.hideInfo(index);
        degreeObjectPanels[index].remove(infoLabelOnHover);
        validate();
        repaint();
    }
}
