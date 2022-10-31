package edu.brandeis.cs12b.pa10.lexer;

import java.util.*;

public class Lexer implements Iterator<Lexeme> {
	//lexer statement is the string passed in
	String lexerStatement;
	//this is my lexercounter to keep track of which lexer I am on
	int lexCounter = 0;
	/**
	 * Initializes a lexer
	 * @param toLex the PitoScript program that will be turned into a series of Lexemes
	 */
	public Lexer(String toLex) {
		lexerStatement = toLex;

	}

	/**
	 * @return true if the String toLex has more characters to be turned into Lexemes, otherwise, false
	 */
	@Override
	//Goes to lexer counter and returns true or false if there is an available lexeme
	public boolean hasNext() {
		for (int i = lexCounter; i < lexerStatement.length(); i++) {
			if (!Character.isWhitespace(lexerStatement.charAt(i))) {
				return true;
			}
		}
		return false;
	}


	/**
	 * @return the Lexeme generated from the next non-whitespace characters in toLex
	 */
	@Override
	//creates lexemes from the lexerstatement. I update the lexercounter before I return the lexeme
	public Lexeme next() {	
		for (int i = lexCounter; i < lexerStatement.length(); i++) {
			char c = lexerStatement.charAt(i);
			if (!Character.isWhitespace(c)) {
				if(Character.isDigit(c)) {
					updater(i);
					return new Lexeme(LexemeType.NUMBER, addString(i));
				} else if (Character.isLetter(c)){
					updater(i);
					return new Lexeme(LexemeType.VARIABLE, addString(i));
				} else if(c == '(') {
					updater(i);
					return new Lexeme(LexemeType.LEFT_PAREN, null);
				} else if (c == ')'){
					updater(i);
					return new Lexeme(LexemeType.RIGHT_PAREN, null);
				} else if(c == '+' || c == '-' || c == '*' || c == '/' ||c == '^') {
					updater(i);
					return new Lexeme(LexemeType.OPERATOR, Character.toString(c));
				} else if (c == '=') {
					updater(i);
					return new Lexeme(LexemeType.EQUALS, Character.toString(c));
				} else if (c == '?') {
					updater(i);
					return new Lexeme(LexemeType.USER_INPUT, null);
				} else {
					updater(i);
					return new Lexeme(LexemeType.SEMICOLON, null);
				}	
			}
		}
		return null;
	}

	public String addString(int i) {
		String newStr = new String();
		char c = lexerStatement.charAt(i);
		while(Character.isDigit(c)||Character.isLetter(c)||c == '.') {
			newStr += c;
			i++;
			c = lexerStatement.charAt(i);
		}
		lexCounter = i;
		return newStr;
	}
	public void updater(int i) {
		lexCounter = i + 1;
	}
}
