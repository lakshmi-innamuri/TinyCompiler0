package com.tinycompiler;

import com.tinycompiler.exceptions.TypeError;
import com.tinycompiler.model.Node;
import com.tinycompiler.model.Token;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lakshmi on 5/22/17.
 *
 * Reference
 * https://www.youtube.com/watch?v=Tar4WgAfMr4&index=1&list=PLHEcW6YwumbpmDB_BWoy5wSVQzrVBLWPI
 *  (add(2, sub(4, 5)))
 */
public class TinyCompiler {

    ArrayList<Token> tokenizer(String input) throws TypeError {
        int current = 0;
        ArrayList<Token> tokens = new ArrayList<>();

        while (current < input.length()){
            char ch = input.charAt(current);

            if(ch == '('){
                Token token = new Token();
                token.type = "paren";
                token.value = "(";
                tokens.add(token);
                current ++;
                continue;
            }

            if(ch == ')'){
                Token token = new Token();
                token.type = "paren";
                token.value = ")";
                tokens.add(token);
                current ++;
                continue;
            }

            if(ch == ' '){
                current ++;
                continue;
            }

            if(ch >= 48 && ch <= 57){
                String number = "";
                while (ch >= 48 && ch <= 57){
                    number += ch;
                    current++ ;
                    ch = input.charAt(current);
                }
                Token token = new Token();
                token.type = "number";
                token.value = number;
                tokens.add(token);
                continue;
            }

            if((ch >= 65 && ch <= 90 )|| (ch >= 97 && ch <= 122)){
                String name = "";
                while ((ch >= 65 && ch <= 90 )|| (ch >= 97 && ch <= 122)){
                    name += ch;
                    current++ ;
                    ch = input.charAt(current);
                }
                Token token = new Token();
                token.type = "name";
                token.value = name;
                tokens.add(token);
                continue;
            }

            throw new TypeError("can't recognize this"+ch);
        }
        return tokens;
    }

    void parser(ArrayList<Token> tokens){
        int current = 0;

    }

    Node walk(ArrayList<Token> tokens, int current){

        Token token = tokens.get(current);
        if(token.type.equals("number")){
            current ++;
            return new Node(token.type, token.value);
        }

        if (token.type.equals("paren") && token.value.equals("(")){
             //TODO https://www.youtube.com/watch?v=Tar4WgAfMr4&index=1&list=PLHEcW6YwumbpmDB_BWoy5wSVQzrVBLWPI&t=1312s
            //skip open paren
            token = tokens.get(++current);
            Node node = new Node(token.type, token.value);
            token = tokens.get(++current);
            while (!token.type.equals("paren") || !token.value.equals(")")){
                //push params of this call expression
            }

        }
        return  null;
    }

    public static void main(String args[]){
        TinyCompiler tn = new TinyCompiler();
        try {
            ArrayList<Token> tokens = tn.tokenizer("(add(3 sub(4 5)))");
        } catch (TypeError typeError) {
            typeError.printStackTrace();
        }
    }
}
