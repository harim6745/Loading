package org.techtown.loading;

public class PersonalData {

    private String user_id;
    private String content;
    private String grade;
    private String date;

    public String getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public String getGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }


    public void setUser_id (String user_id){
        this.user_id = user_id;
    }

    public void setContent (String content){
        this.content = content;
    }

    public void setGrade (String grade){
        this.grade = grade;
    }

    public void setDate (String date){
        this.date = date;
    }

}
