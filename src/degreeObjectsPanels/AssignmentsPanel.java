package degreeObjectsPanels;

import degreeObjects.Assignment;

import javax.swing.*;
import java.awt.*;

public class AssignmentsPanel extends GeneralDegreeObjectPanel{
    public Assignment[] assignments;
    public QuestionsPanel[] questionsPanels;

    AssignmentsPanel(int parentPanelNo){
        super();

        this.parentPanelNo = parentPanelNo;
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(assignments == null){
            assignments = new Assignment[size];
            questionsPanels = new QuestionsPanel[size];

            for(int i=0; i < assignments.length; i++){
                assignments[i] = new Assignment();
                questionsPanels[i] = new QuestionsPanel(i);
            }
        }
        else{
            Assignment[] assignments = new Assignment[this.assignments.length + size];
            QuestionsPanel[] questionsPanels = new QuestionsPanel[this.questionsPanels.length + size];

            System.arraycopy(this.assignments, 0, assignments, 0, this.assignments.length);
            System.arraycopy(this.questionsPanels, 0, questionsPanels, 0, this.questionsPanels.length);

            for(int i=this.assignments.length; i < assignments.length; i++){
                assignments[i] = new Assignment();
                questionsPanels[i] = new QuestionsPanel(i);
            }

            this.assignments = assignments;
            this.questionsPanels = questionsPanels;
        }
    }

    public Assignment[] getDegreeObjects(){
        return assignments;
    }

    @Override
    public Object[] getInnerPanels() {
        return questionsPanels;
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s<br/> %s</pre></html>",
                        "Due On " + assignments[index].getDue_Date(),
                        (assignments[index].getQuestion() != null)?
                                assignments[index].getQuestion().length + " Questions" : "0 Questions")
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
