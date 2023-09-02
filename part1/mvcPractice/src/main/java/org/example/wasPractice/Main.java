package org.example.wasPractice;

import java.io.IOException;

// GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8080).start();
    }
}
