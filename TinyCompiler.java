package com.tinycompiler;

import com.tinycompiler.model.Token;

import java.util.ArrayList;

/**
 * Created by lakshmi on 5/22/17.
 *
 *  (add(2, sub(4, 5)))
 */
public class TinyCompiler {

    ArrayList<Token> tokenizer(String input){
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
        }
        return tokens;
    }

    public static void main(String args[]){
        TinyCompiler tn = new TinyCompiler();
        ArrayList<Token> tokens = tn.tokenizer("(add(3 sub(4 5)))");
    }
}
