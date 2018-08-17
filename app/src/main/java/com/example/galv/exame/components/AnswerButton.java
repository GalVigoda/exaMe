package com.example.galv.exame.components;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class AnswerButton extends AppCompatButton{

    private int defaultResource;
    private int selectedResource;
    private int approvedCorrectResource;
    private int approvedWrongResource;

    private boolean selected;

    public AnswerButton(Context context) {
        super(context);

    }

    public void setResources(int defaultResource, int selectedResource, int approvedCorrectResource, int approvedWrongResource){
        setDefaultResource          (defaultResource);
        setSelectedResource         (selectedResource);
        setApprovedCorrectResource  (approvedCorrectResource);
        setApprovedWrongResource    (approvedWrongResource);
        reset();
    }

    public void reset(){
        setBackgroundResource(defaultResource);
        selected = false;
        setEnabled(true);
    }
    public void changeState() {
        setBackgroundResource(selected ? defaultResource : selectedResource);
        selected = !selected;
    }

    public void setApproved(boolean rightAnswer){
        setBackgroundResource(rightAnswer ? approvedCorrectResource : approvedWrongResource);
        setEnabled(false);
    }


    public void setDefaultResource(int defaultResource) {
        this.defaultResource = defaultResource;
    }

    public void setSelectedResource(int selectedResource) {
        this.selectedResource = selectedResource;
    }

    public void setApprovedCorrectResource(int approvedCorrectResource) {
        this.approvedCorrectResource = approvedCorrectResource;
    }

    public void setApprovedWrongResource(int approvedWrongResource) {
        this.approvedWrongResource = approvedWrongResource;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
}
