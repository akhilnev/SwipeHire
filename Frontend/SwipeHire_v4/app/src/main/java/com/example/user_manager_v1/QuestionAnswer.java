package com.example.user_manager_v1;

public class QuestionAnswer {

    public static String question[] ={
            "What is the running time of Binary Search",
            "Which one of the following does not have a primitive type?",
            "Which data structure is FIFO",
            "Which data structure is LIFO",
            "Which data structure has instant access to any element",
            "What is the runtime of the maxheapify algorithm",
            "Which company maintains Java"
    };

    public static String choices[][] = {
            {"O(n)","O(nlogn)","O(logn)","O(1)"},
            {"Integer","Double","Character","String"},
            {"Stacks","Lists","Queues","Array"},
            {"Stacks","Lists","Queues","Array"},
            {"Stacks","Lists","Queues","Array"},
            {"O(n)","O(nlogn)","O(logn)","O(1)"},
            {"Google","Microsoft","Eclipse","Oracle"}
    };

    public static String correctAnswers[] = {
            "O(logn)",
            "String",
            "Queues",
            "Stacks",
            "Array",
            "O(logn)",
            "Oracle"
    };
}
