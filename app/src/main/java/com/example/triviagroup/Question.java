package com.example.triviagroup;

public class Question {

    private String question;
    private String[] answer;

    public Question(String[] questionA){
        question = questionA[0];
        answer = new String[questionA.length-1];
        for(int i = 0; i < answer.length; i++){
            answer[i] = questionA[i+1];
        }
    }
    public String getQuestion(){
        return question;
    }
    public String[] getAnswer(){
        return answer;
    }

}
