// Generated from /Users/sgreben/Explore/rule-checker/src/main/antlr4/io/github/sgreben/rcc/parser/Constraint.g4 by ANTLR 4.6

package io.github.sgreben.rc.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConstraintLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, TRUE=19, FALSE=20, INT=21, FLOAT=22, ID=23, WS=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "TRUE", "FALSE", "INT", "FLOAT", "ID", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'!'", "'if'", "'then'", "'else'", "'^'", "'*'", "'/'", 
		"'+'", "'-'", "'='", "'<='", "'>='", "'>'", "'<'", "'&&'", "'||'", "'true'", 
		"'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "TRUE", "FALSE", "INT", "FLOAT", 
		"ID", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ConstraintLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Constraint.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u0091\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\5\26o"+
		"\n\26\3\26\6\26r\n\26\r\26\16\26s\3\27\5\27w\n\27\3\27\6\27z\n\27\r\27"+
		"\16\27{\3\27\3\27\6\27\u0080\n\27\r\27\16\27\u0081\3\30\3\30\7\30\u0086"+
		"\n\30\f\30\16\30\u0089\13\30\3\31\6\31\u008c\n\31\r\31\16\31\u008d\3\31"+
		"\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\6\3\2\62"+
		";\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\u0097\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63"+
		"\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13<\3\2\2\2\rA\3\2\2\2\17"+
		"F\3\2\2\2\21H\3\2\2\2\23J\3\2\2\2\25L\3\2\2\2\27N\3\2\2\2\31P\3\2\2\2"+
		"\33R\3\2\2\2\35U\3\2\2\2\37X\3\2\2\2!Z\3\2\2\2#\\\3\2\2\2%_\3\2\2\2\'"+
		"b\3\2\2\2)g\3\2\2\2+n\3\2\2\2-v\3\2\2\2/\u0083\3\2\2\2\61\u008b\3\2\2"+
		"\2\63\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6\3\2\2\2\678\7#\2\28\b\3"+
		"\2\2\29:\7k\2\2:;\7h\2\2;\n\3\2\2\2<=\7v\2\2=>\7j\2\2>?\7g\2\2?@\7p\2"+
		"\2@\f\3\2\2\2AB\7g\2\2BC\7n\2\2CD\7u\2\2DE\7g\2\2E\16\3\2\2\2FG\7`\2\2"+
		"G\20\3\2\2\2HI\7,\2\2I\22\3\2\2\2JK\7\61\2\2K\24\3\2\2\2LM\7-\2\2M\26"+
		"\3\2\2\2NO\7/\2\2O\30\3\2\2\2PQ\7?\2\2Q\32\3\2\2\2RS\7>\2\2ST\7?\2\2T"+
		"\34\3\2\2\2UV\7@\2\2VW\7?\2\2W\36\3\2\2\2XY\7@\2\2Y \3\2\2\2Z[\7>\2\2"+
		"[\"\3\2\2\2\\]\7(\2\2]^\7(\2\2^$\3\2\2\2_`\7~\2\2`a\7~\2\2a&\3\2\2\2b"+
		"c\7v\2\2cd\7t\2\2de\7w\2\2ef\7g\2\2f(\3\2\2\2gh\7h\2\2hi\7c\2\2ij\7n\2"+
		"\2jk\7u\2\2kl\7g\2\2l*\3\2\2\2mo\7/\2\2nm\3\2\2\2no\3\2\2\2oq\3\2\2\2"+
		"pr\t\2\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2t,\3\2\2\2uw\7/\2\2"+
		"vu\3\2\2\2vw\3\2\2\2wy\3\2\2\2xz\t\2\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2"+
		"{|\3\2\2\2|}\3\2\2\2}\177\7\60\2\2~\u0080\t\2\2\2\177~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082.\3\2\2\2\u0083\u0087"+
		"\t\3\2\2\u0084\u0086\t\4\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\60\3\2\2\2\u0089\u0087\3\2\2"+
		"\2\u008a\u008c\t\5\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\b\31\2\2"+
		"\u0090\62\3\2\2\2\n\2nsv{\u0081\u0087\u008d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}