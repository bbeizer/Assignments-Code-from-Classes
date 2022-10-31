package edu.brandeis.cs12b.pa10.parser;

import java.util.*;

import edu.brandeis.cs12b.pa10.lexer.Lexeme;
import edu.brandeis.cs12b.pa10.lexer.LexemeType;

public class Parser {
	Iterator<Lexeme> lexemes;

	//the parser accepts lexemes
	public Parser(Iterator<Lexeme> lexemes) {
		this.lexemes = lexemes;
	}

	/**
	 * Turns the iterator of Lexemes into a list of ParseTreeNodes, with each element
	 * being the root of a different parse tree
	 * @return the List of ParseTreeNodes
	 */
	//creates a parse tree from the lexeme
	public List<ParseTreeNode> parse() {
		ArrayList<ParseTreeNode> tree_nodes = new ArrayList<ParseTreeNode>();
		//while statement that makes sure there's more lexemes
		while(lexemes.hasNext()) {
			ParseTreeNode variable = new ParseTreeNode(lexemes.next());
			Lexeme equals = lexemes.next();
			//expression represents each statement
			ArrayList<Lexeme> expression = new ArrayList<Lexeme>();
			do {
				expression.add(lexemes.next());
			} while(expression.get(expression.size()-1).getType() != LexemeType.SEMICOLON);
			expression.remove(expression.size()-1);
			ParseTreeNode toAdd = new ParseTreeNode(equals);
			toAdd.setLeft(variable);
			toAdd.setRight(eval_expression(expression));
			tree_nodes.add(toAdd);
		}
		return tree_nodes;
	}
	//a recursive method that evaluates an expression
	//widdles down large expressions and calls its self until the expression has
	//no parenthesis at the first index and stores the operator in one node, and left and right
	//as the expression before and after the operator
	public ParseTreeNode eval_expression(ArrayList<Lexeme> expression) {
		if(expression.size() == 1) {
			return new ParseTreeNode(expression.get(0));
		}
		ParseTreeNode left = null;
		ParseTreeNode right = null;
		Lexeme operator = null;
		
		if (expression.get(0).getType() == LexemeType.LEFT_PAREN) {
			int endIdx = getMatchingParenIndex(expression);
			ArrayList<Lexeme> insideParen = new ArrayList<Lexeme>();
			for(int i = 1; i < endIdx; i++) {
				insideParen.add(expression.get(i));
			}
			left = eval_expression(insideParen);
			if(expression.size() == endIdx + 1) {
				return left;
			}
			ArrayList<Lexeme> afterParen = new ArrayList<Lexeme>(); 
			for(int i = endIdx+2; i < expression.size(); i++) {
				afterParen.add(expression.get(i));
			}
			right = eval_expression(afterParen);
			operator = expression.get(endIdx+1);
		} else {
			ArrayList<Lexeme> firstElement = new ArrayList<Lexeme>();
			firstElement.add(expression.get(0));
			left = eval_expression(firstElement);
			operator = expression.get(1);
			ArrayList<Lexeme> restOfElements = new ArrayList<Lexeme>();
			for (int i = 2; i<expression.size(); i++) {
				restOfElements.add(expression.get(i));
			}
			right = eval_expression(restOfElements);
		}
		ParseTreeNode operatorNode = new ParseTreeNode(operator);
		operatorNode.setLeft(left);
		operatorNode.setRight(right);
		return operatorNode;
	}
	//adds left parens to a stack and remove them once it sees its right counter part
	//when the stack is empty, you've found the index of the matching paren
	public int getMatchingParenIndex(ArrayList<Lexeme> expression) {
		Stack<Lexeme> stack = new Stack<Lexeme>();
		int lastIndex = -1;
		int currentIndex = 0;
		do {
			if (expression.get(currentIndex).getType() == LexemeType.LEFT_PAREN) {
				stack.push(expression.get(currentIndex));
			}
			if (expression.get(currentIndex).getType() == LexemeType.RIGHT_PAREN){
				stack.pop();
				lastIndex = currentIndex;
			}
			currentIndex++;
		} while(!stack.isEmpty());
		return lastIndex;
	}
}

