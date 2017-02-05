// Generated from /Users/sgreben/Explore/rule-checker/src/main/antlr4/io/github/sgreben/rcc/parser/Constraint.g4 by ANTLR 4.6

package io.github.sgreben.rc.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ConstraintParser}.
 */
public interface ConstraintListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(ConstraintParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(ConstraintParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gtExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGtExpr(ConstraintParser.GtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gtExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGtExpr(ConstraintParser.GtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(ConstraintParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(ConstraintParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(ConstraintParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(ConstraintParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(ConstraintParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(ConstraintParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(ConstraintParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(ConstraintParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(ConstraintParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(ConstraintParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(ConstraintParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(ConstraintParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ltExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLtExpr(ConstraintParser.LtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ltExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLtExpr(ConstraintParser.LtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(ConstraintParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(ConstraintParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(ConstraintParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(ConstraintParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(ConstraintParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(ConstraintParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code leqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLeqExpr(ConstraintParser.LeqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code leqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLeqExpr(ConstraintParser.LeqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDivExpr(ConstraintParser.DivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDivExpr(ConstraintParser.DivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(ConstraintParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(ConstraintParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(ConstraintParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(ConstraintParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code geqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGeqExpr(ConstraintParser.GeqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code geqExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGeqExpr(ConstraintParser.GeqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(ConstraintParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(ConstraintParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code iteExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIteExpr(ConstraintParser.IteExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code iteExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIteExpr(ConstraintParser.IteExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(ConstraintParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link ConstraintParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(ConstraintParser.AndExprContext ctx);
}