package edu.brandeis.cs12b.pa10.interpreter;

import java.util.*;
import edu.brandeis.cs12b.pa10.lexer.LexemeType;
import edu.brandeis.cs12b.pa10.parser.ParseTreeNode;

public class Interpreter {
	Map<String, Double> table;
	List<ParseTreeNode> ptns;
	//takes in a list of parse tree nodes and creates and empty table for the variables and their values
	public Interpreter(List<ParseTreeNode> ptns) {
		this.ptns = ptns;
		this.table = new HashMap<String, Double>();
	}

	/**
	 * Evaluates all ParseTreeNodes in the given list
	 * @return a map matching variable names to their corresponding values
	 */
	public Map<String, Double> evaluate() {
		//puts the variables in a map and finds its given value
		for (ParseTreeNode ptn : ptns) {
			if(ptn.getLexeme().getType() == LexemeType.EQUALS) {
				table.put(ptn.getLeft().getLexeme().getValueAsString(), evalNode(ptn.getRight()));
			}
		}
		return table;
	}
	//finds the output of expressions in the parse tree
	public double evalNode(ParseTreeNode ptn) {
		
		if (ptn.getLexeme().getType() == LexemeType.USER_INPUT) {
			Scanner console = new Scanner(System.in);
			System.out.println("Type in a Number: ");
			double answer = console.nextDouble();
			return answer;
		}
		//accesses the variables value from the map
		if(ptn.getLexeme().getType() == LexemeType.VARIABLE) {
			return table.get(ptn.getLexeme().getValueAsString());
		}
		
		if (ptn.getLexeme().getType() == LexemeType.NUMBER) {
			return ptn.getLexeme().getValueAsNumber();
		}
		
		if(ptn.getLexeme().getType() == LexemeType.OPERATOR) {
			double left = evalNode(ptn.getLeft());
			double right = evalNode(ptn.getRight());
			if (ptn.getLexeme().getValueAsString() == "+") {
				return left + right;
			} else if (ptn.getLexeme().getValueAsString() == "-") {
				return left - right;
			} else if(ptn.getLexeme().getValueAsString() == "*") {
				return left * right;
			} else if (ptn.getLexeme().getValueAsString() == "/") {
				return left / right;
			} else {
				return Math.pow(left, right);
			}
		}
		return -1;
	}
}
