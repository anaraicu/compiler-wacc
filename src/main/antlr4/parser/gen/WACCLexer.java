// Generated from /Users/ana_raicu/Desktop/UNI/y2/50007/Compilers/WACC_08/src/main/antlr4/parser/WACCLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP2=4, READ=5, FREE=6, RETURN=7, EXIT=8, PRINT=9, 
		PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, DO=16, DONE=17, 
		CALL=18, FOR=19, BREAK=20, CONTINUE=21, CLASS=22, PRIVATE=23, PUBLIC=24, 
		NEW=25, SEMICOLON=26, COMMA=27, HASH=28, OPEN_PARENTHESES=29, CLOSE_PARENTHESES=30, 
		OPEN_SQUARE=31, CLOSE_SQUARE=32, DOT=33, ASSIGN=34, INT=35, BOOL=36, CHAR=37, 
		STRING=38, NOT=39, LEN=40, ORD=41, CHR=42, MULT=43, DIV=44, MOD=45, PLUS=46, 
		MINUS=47, GT=48, GTE=49, LT=50, LTE=51, EQUAL=52, NOT_EQUAL=53, AND=54, 
		OR=55, BITWISE_AND=56, BITWISE_OR=57, BITWISE_NOT=58, BITWISE_XOR=59, 
		NULL=60, PAIR=61, NEW_PAIR=62, FST=63, SND=64, INT_LITER=65, BOOL_LITER=66, 
		CHAR_LITER=67, STRING_LITER=68, IDENT=69, WHITESPACE=70, EOL=71, NON_EOL=72, 
		COMMENT=73;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BEGIN", "END", "IS", "SKIP2", "READ", "FREE", "RETURN", "EXIT", "PRINT", 
			"PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", "CALL", 
			"FOR", "BREAK", "CONTINUE", "CLASS", "PRIVATE", "PUBLIC", "NEW", "SEMICOLON", 
			"COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE", 
			"CLOSE_SQUARE", "DOT", "ASSIGN", "INT", "BOOL", "CHAR", "STRING", "NOT", 
			"LEN", "ORD", "CHR", "MULT", "DIV", "MOD", "PLUS", "MINUS", "GT", "GTE", 
			"LT", "LTE", "EQUAL", "NOT_EQUAL", "AND", "OR", "BITWISE_AND", "BITWISE_OR", 
			"BITWISE_NOT", "BITWISE_XOR", "NULL", "PAIR", "NEW_PAIR", "FST", "SND", 
			"UNDERSCORE", "LOWER_LETTER", "UPPER_LETTER", "DIGIT", "ESCAPED_CHAR", 
			"CHARACTER", "INT_SIGN", "TRUE", "FALSE", "INT_LITER", "BOOL_LITER", 
			"CHAR_LITER", "STRING_LITER", "IDENT", "WHITESPACE", "EOL", "NON_EOL", 
			"COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'do'", "'done'", "'call'", "'for'", "'break'", "'continue'", 
			"'class'", "'private'", "'public'", "'new'", "';'", "','", "'#'", "'('", 
			"')'", "'['", "']'", "'.'", "'='", "'int'", "'bool'", "'char'", "'string'", 
			"'!'", "'len'", "'ord'", "'chr'", "'*'", "'/'", "'%'", "'+'", "'-'", 
			"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'&'", 
			"'|'", "'~'", "'^'", "'null'", "'pair'", "'newpair'", "'fst'", "'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP2", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"CALL", "FOR", "BREAK", "CONTINUE", "CLASS", "PRIVATE", "PUBLIC", "NEW", 
			"SEMICOLON", "COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE", "CLOSE_SQUARE", "DOT", "ASSIGN", "INT", "BOOL", "CHAR", 
			"STRING", "NOT", "LEN", "ORD", "CHR", "MULT", "DIV", "MOD", "PLUS", "MINUS", 
			"GT", "GTE", "LT", "LTE", "EQUAL", "NOT_EQUAL", "AND", "OR", "BITWISE_AND", 
			"BITWISE_OR", "BITWISE_NOT", "BITWISE_XOR", "NULL", "PAIR", "NEW_PAIR", 
			"FST", "SND", "INT_LITER", "BOOL_LITER", "CHAR_LITER", "STRING_LITER", 
			"IDENT", "WHITESPACE", "EOL", "NON_EOL", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public WACCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WACCLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2K\u01fb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3$\3%\3%"+
		"\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3)\3"+
		")\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\38\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3=\3=\3=\3>\3>"+
		"\3>\3>\3>\3?\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3A\3A\3A\3A\3B\3B\3C\3C"+
		"\3D\3D\3E\3E\3F\3F\3G\3G\3G\5G\u01b1\nG\3H\3H\5H\u01b5\nH\3I\3I\3I\3I"+
		"\3I\3J\3J\3J\3J\3J\3J\3K\6K\u01c3\nK\rK\16K\u01c4\3L\3L\5L\u01c9\nL\3"+
		"M\3M\3M\3M\3N\3N\7N\u01d1\nN\fN\16N\u01d4\13N\3N\3N\3O\3O\3O\5O\u01db"+
		"\nO\3O\3O\3O\3O\7O\u01e1\nO\fO\16O\u01e4\13O\3P\6P\u01e7\nP\rP\16P\u01e8"+
		"\3P\3P\3Q\3Q\3R\3R\3S\3S\7S\u01f3\nS\fS\16S\u01f6\13S\3S\3S\3S\3S\2\2"+
		"T\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o"+
		"9q:s;u<w=y>{?}@\177A\u0081B\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d"+
		"\2\u008f\2\u0091\2\u0093\2\u0095C\u0097D\u0099E\u009bF\u009dG\u009fH\u00a1"+
		"I\u00a3J\u00a5K\3\2\6\13\2$$))\62\62^^ddhhppttvv\5\2$$))^^\5\2\13\f\17"+
		"\17\"\"\4\2\f\f\17\17\2\u01fe\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081"+
		"\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2"+
		"\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5"+
		"\3\2\2\2\3\u00a7\3\2\2\2\5\u00ad\3\2\2\2\7\u00b1\3\2\2\2\t\u00b4\3\2\2"+
		"\2\13\u00b9\3\2\2\2\r\u00be\3\2\2\2\17\u00c3\3\2\2\2\21\u00ca\3\2\2\2"+
		"\23\u00cf\3\2\2\2\25\u00d5\3\2\2\2\27\u00dd\3\2\2\2\31\u00e0\3\2\2\2\33"+
		"\u00e5\3\2\2\2\35\u00ea\3\2\2\2\37\u00ed\3\2\2\2!\u00f3\3\2\2\2#\u00f6"+
		"\3\2\2\2%\u00fb\3\2\2\2\'\u0100\3\2\2\2)\u0104\3\2\2\2+\u010a\3\2\2\2"+
		"-\u0113\3\2\2\2/\u0119\3\2\2\2\61\u0121\3\2\2\2\63\u0128\3\2\2\2\65\u012c"+
		"\3\2\2\2\67\u012e\3\2\2\29\u0130\3\2\2\2;\u0132\3\2\2\2=\u0134\3\2\2\2"+
		"?\u0136\3\2\2\2A\u0138\3\2\2\2C\u013a\3\2\2\2E\u013c\3\2\2\2G\u013e\3"+
		"\2\2\2I\u0142\3\2\2\2K\u0147\3\2\2\2M\u014c\3\2\2\2O\u0153\3\2\2\2Q\u0155"+
		"\3\2\2\2S\u0159\3\2\2\2U\u015d\3\2\2\2W\u0161\3\2\2\2Y\u0163\3\2\2\2["+
		"\u0165\3\2\2\2]\u0167\3\2\2\2_\u0169\3\2\2\2a\u016b\3\2\2\2c\u016d\3\2"+
		"\2\2e\u0170\3\2\2\2g\u0172\3\2\2\2i\u0175\3\2\2\2k\u0178\3\2\2\2m\u017b"+
		"\3\2\2\2o\u017e\3\2\2\2q\u0181\3\2\2\2s\u0183\3\2\2\2u\u0185\3\2\2\2w"+
		"\u0187\3\2\2\2y\u0189\3\2\2\2{\u018e\3\2\2\2}\u0193\3\2\2\2\177\u019b"+
		"\3\2\2\2\u0081\u019f\3\2\2\2\u0083\u01a3\3\2\2\2\u0085\u01a5\3\2\2\2\u0087"+
		"\u01a7\3\2\2\2\u0089\u01a9\3\2\2\2\u008b\u01ab\3\2\2\2\u008d\u01b0\3\2"+
		"\2\2\u008f\u01b4\3\2\2\2\u0091\u01b6\3\2\2\2\u0093\u01bb\3\2\2\2\u0095"+
		"\u01c2\3\2\2\2\u0097\u01c8\3\2\2\2\u0099\u01ca\3\2\2\2\u009b\u01ce\3\2"+
		"\2\2\u009d\u01da\3\2\2\2\u009f\u01e6\3\2\2\2\u00a1\u01ec\3\2\2\2\u00a3"+
		"\u01ee\3\2\2\2\u00a5\u01f0\3\2\2\2\u00a7\u00a8\7d\2\2\u00a8\u00a9\7g\2"+
		"\2\u00a9\u00aa\7i\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac\4\3"+
		"\2\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7f\2\2\u00b0"+
		"\6\3\2\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7u\2\2\u00b3\b\3\2\2\2\u00b4"+
		"\u00b5\7u\2\2\u00b5\u00b6\7m\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7r\2\2"+
		"\u00b8\n\3\2\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7c"+
		"\2\2\u00bc\u00bd\7f\2\2\u00bd\f\3\2\2\2\u00be\u00bf\7h\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7g\2\2\u00c2\16\3\2\2\2\u00c3\u00c4"+
		"\7t\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7w\2\2\u00c7"+
		"\u00c8\7t\2\2\u00c8\u00c9\7p\2\2\u00c9\20\3\2\2\2\u00ca\u00cb\7g\2\2\u00cb"+
		"\u00cc\7z\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7v\2\2\u00ce\22\3\2\2\2\u00cf"+
		"\u00d0\7r\2\2\u00d0\u00d1\7t\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7p\2\2"+
		"\u00d3\u00d4\7v\2\2\u00d4\24\3\2\2\2\u00d5\u00d6\7r\2\2\u00d6\u00d7\7"+
		"t\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7v\2\2\u00da\u00db"+
		"\7n\2\2\u00db\u00dc\7p\2\2\u00dc\26\3\2\2\2\u00dd\u00de\7k\2\2\u00de\u00df"+
		"\7h\2\2\u00df\30\3\2\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3"+
		"\7g\2\2\u00e3\u00e4\7p\2\2\u00e4\32\3\2\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7"+
		"\7n\2\2\u00e7\u00e8\7u\2\2\u00e8\u00e9\7g\2\2\u00e9\34\3\2\2\2\u00ea\u00eb"+
		"\7h\2\2\u00eb\u00ec\7k\2\2\u00ec\36\3\2\2\2\u00ed\u00ee\7y\2\2\u00ee\u00ef"+
		"\7j\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7g\2\2\u00f2"+
		" \3\2\2\2\u00f3\u00f4\7f\2\2\u00f4\u00f5\7q\2\2\u00f5\"\3\2\2\2\u00f6"+
		"\u00f7\7f\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7p\2\2\u00f9\u00fa\7g\2\2"+
		"\u00fa$\3\2\2\2\u00fb\u00fc\7e\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7n\2"+
		"\2\u00fe\u00ff\7n\2\2\u00ff&\3\2\2\2\u0100\u0101\7h\2\2\u0101\u0102\7"+
		"q\2\2\u0102\u0103\7t\2\2\u0103(\3\2\2\2\u0104\u0105\7d\2\2\u0105\u0106"+
		"\7t\2\2\u0106\u0107\7g\2\2\u0107\u0108\7c\2\2\u0108\u0109\7m\2\2\u0109"+
		"*\3\2\2\2\u010a\u010b\7e\2\2\u010b\u010c\7q\2\2\u010c\u010d\7p\2\2\u010d"+
		"\u010e\7v\2\2\u010e\u010f\7k\2\2\u010f\u0110\7p\2\2\u0110\u0111\7w\2\2"+
		"\u0111\u0112\7g\2\2\u0112,\3\2\2\2\u0113\u0114\7e\2\2\u0114\u0115\7n\2"+
		"\2\u0115\u0116\7c\2\2\u0116\u0117\7u\2\2\u0117\u0118\7u\2\2\u0118.\3\2"+
		"\2\2\u0119\u011a\7r\2\2\u011a\u011b\7t\2\2\u011b\u011c\7k\2\2\u011c\u011d"+
		"\7x\2\2\u011d\u011e\7c\2\2\u011e\u011f\7v\2\2\u011f\u0120\7g\2\2\u0120"+
		"\60\3\2\2\2\u0121\u0122\7r\2\2\u0122\u0123\7w\2\2\u0123\u0124\7d\2\2\u0124"+
		"\u0125\7n\2\2\u0125\u0126\7k\2\2\u0126\u0127\7e\2\2\u0127\62\3\2\2\2\u0128"+
		"\u0129\7p\2\2\u0129\u012a\7g\2\2\u012a\u012b\7y\2\2\u012b\64\3\2\2\2\u012c"+
		"\u012d\7=\2\2\u012d\66\3\2\2\2\u012e\u012f\7.\2\2\u012f8\3\2\2\2\u0130"+
		"\u0131\7%\2\2\u0131:\3\2\2\2\u0132\u0133\7*\2\2\u0133<\3\2\2\2\u0134\u0135"+
		"\7+\2\2\u0135>\3\2\2\2\u0136\u0137\7]\2\2\u0137@\3\2\2\2\u0138\u0139\7"+
		"_\2\2\u0139B\3\2\2\2\u013a\u013b\7\60\2\2\u013bD\3\2\2\2\u013c\u013d\7"+
		"?\2\2\u013dF\3\2\2\2\u013e\u013f\7k\2\2\u013f\u0140\7p\2\2\u0140\u0141"+
		"\7v\2\2\u0141H\3\2\2\2\u0142\u0143\7d\2\2\u0143\u0144\7q\2\2\u0144\u0145"+
		"\7q\2\2\u0145\u0146\7n\2\2\u0146J\3\2\2\2\u0147\u0148\7e\2\2\u0148\u0149"+
		"\7j\2\2\u0149\u014a\7c\2\2\u014a\u014b\7t\2\2\u014bL\3\2\2\2\u014c\u014d"+
		"\7u\2\2\u014d\u014e\7v\2\2\u014e\u014f\7t\2\2\u014f\u0150\7k\2\2\u0150"+
		"\u0151\7p\2\2\u0151\u0152\7i\2\2\u0152N\3\2\2\2\u0153\u0154\7#\2\2\u0154"+
		"P\3\2\2\2\u0155\u0156\7n\2\2\u0156\u0157\7g\2\2\u0157\u0158\7p\2\2\u0158"+
		"R\3\2\2\2\u0159\u015a\7q\2\2\u015a\u015b\7t\2\2\u015b\u015c\7f\2\2\u015c"+
		"T\3\2\2\2\u015d\u015e\7e\2\2\u015e\u015f\7j\2\2\u015f\u0160\7t\2\2\u0160"+
		"V\3\2\2\2\u0161\u0162\7,\2\2\u0162X\3\2\2\2\u0163\u0164\7\61\2\2\u0164"+
		"Z\3\2\2\2\u0165\u0166\7\'\2\2\u0166\\\3\2\2\2\u0167\u0168\7-\2\2\u0168"+
		"^\3\2\2\2\u0169\u016a\7/\2\2\u016a`\3\2\2\2\u016b\u016c\7@\2\2\u016cb"+
		"\3\2\2\2\u016d\u016e\7@\2\2\u016e\u016f\7?\2\2\u016fd\3\2\2\2\u0170\u0171"+
		"\7>\2\2\u0171f\3\2\2\2\u0172\u0173\7>\2\2\u0173\u0174\7?\2\2\u0174h\3"+
		"\2\2\2\u0175\u0176\7?\2\2\u0176\u0177\7?\2\2\u0177j\3\2\2\2\u0178\u0179"+
		"\7#\2\2\u0179\u017a\7?\2\2\u017al\3\2\2\2\u017b\u017c\7(\2\2\u017c\u017d"+
		"\7(\2\2\u017dn\3\2\2\2\u017e\u017f\7~\2\2\u017f\u0180\7~\2\2\u0180p\3"+
		"\2\2\2\u0181\u0182\7(\2\2\u0182r\3\2\2\2\u0183\u0184\7~\2\2\u0184t\3\2"+
		"\2\2\u0185\u0186\7\u0080\2\2\u0186v\3\2\2\2\u0187\u0188\7`\2\2\u0188x"+
		"\3\2\2\2\u0189\u018a\7p\2\2\u018a\u018b\7w\2\2\u018b\u018c\7n\2\2\u018c"+
		"\u018d\7n\2\2\u018dz\3\2\2\2\u018e\u018f\7r\2\2\u018f\u0190\7c\2\2\u0190"+
		"\u0191\7k\2\2\u0191\u0192\7t\2\2\u0192|\3\2\2\2\u0193\u0194\7p\2\2\u0194"+
		"\u0195\7g\2\2\u0195\u0196\7y\2\2\u0196\u0197\7r\2\2\u0197\u0198\7c\2\2"+
		"\u0198\u0199\7k\2\2\u0199\u019a\7t\2\2\u019a~\3\2\2\2\u019b\u019c\7h\2"+
		"\2\u019c\u019d\7u\2\2\u019d\u019e\7v\2\2\u019e\u0080\3\2\2\2\u019f\u01a0"+
		"\7u\2\2\u01a0\u01a1\7p\2\2\u01a1\u01a2\7f\2\2\u01a2\u0082\3\2\2\2\u01a3"+
		"\u01a4\7a\2\2\u01a4\u0084\3\2\2\2\u01a5\u01a6\4c|\2\u01a6\u0086\3\2\2"+
		"\2\u01a7\u01a8\4C\\\2\u01a8\u0088\3\2\2\2\u01a9\u01aa\4\62;\2\u01aa\u008a"+
		"\3\2\2\2\u01ab\u01ac\t\2\2\2\u01ac\u008c\3\2\2\2\u01ad\u01b1\n\3\2\2\u01ae"+
		"\u01af\7^\2\2\u01af\u01b1\5\u008bF\2\u01b0\u01ad\3\2\2\2\u01b0\u01ae\3"+
		"\2\2\2\u01b1\u008e\3\2\2\2\u01b2\u01b5\5]/\2\u01b3\u01b5\5_\60\2\u01b4"+
		"\u01b2\3\2\2\2\u01b4\u01b3\3\2\2\2\u01b5\u0090\3\2\2\2\u01b6\u01b7\7v"+
		"\2\2\u01b7\u01b8\7t\2\2\u01b8\u01b9\7w\2\2\u01b9\u01ba\7g\2\2\u01ba\u0092"+
		"\3\2\2\2\u01bb\u01bc\7h\2\2\u01bc\u01bd\7c\2\2\u01bd\u01be\7n\2\2\u01be"+
		"\u01bf\7u\2\2\u01bf\u01c0\7g\2\2\u01c0\u0094\3\2\2\2\u01c1\u01c3\5\u0089"+
		"E\2\u01c2\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c4"+
		"\u01c5\3\2\2\2\u01c5\u0096\3\2\2\2\u01c6\u01c9\5\u0091I\2\u01c7\u01c9"+
		"\5\u0093J\2\u01c8\u01c6\3\2\2\2\u01c8\u01c7\3\2\2\2\u01c9\u0098\3\2\2"+
		"\2\u01ca\u01cb\7)\2\2\u01cb\u01cc\5\u008dG\2\u01cc\u01cd\7)\2\2\u01cd"+
		"\u009a\3\2\2\2\u01ce\u01d2\7$\2\2\u01cf\u01d1\5\u008dG\2\u01d0\u01cf\3"+
		"\2\2\2\u01d1\u01d4\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3"+
		"\u01d5\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d5\u01d6\7$\2\2\u01d6\u009c\3\2"+
		"\2\2\u01d7\u01db\5\u0083B\2\u01d8\u01db\5\u0085C\2\u01d9\u01db\5\u0087"+
		"D\2\u01da\u01d7\3\2\2\2\u01da\u01d8\3\2\2\2\u01da\u01d9\3\2\2\2\u01db"+
		"\u01e2\3\2\2\2\u01dc\u01e1\5\u0083B\2\u01dd\u01e1\5\u0085C\2\u01de\u01e1"+
		"\5\u0087D\2\u01df\u01e1\5\u0089E\2\u01e0\u01dc\3\2\2\2\u01e0\u01dd\3\2"+
		"\2\2\u01e0\u01de\3\2\2\2\u01e0\u01df\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2"+
		"\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u009e\3\2\2\2\u01e4\u01e2\3\2"+
		"\2\2\u01e5\u01e7\t\4\2\2\u01e6\u01e5\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8"+
		"\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01eb\bP"+
		"\2\2\u01eb\u00a0\3\2\2\2\u01ec\u01ed\t\5\2\2\u01ed\u00a2\3\2\2\2\u01ee"+
		"\u01ef\n\5\2\2\u01ef\u00a4\3\2\2\2\u01f0\u01f4\59\35\2\u01f1\u01f3\5\u00a3"+
		"R\2\u01f2\u01f1\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f7\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7\u01f8\5\u00a1"+
		"Q\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\bS\2\2\u01fa\u00a6\3\2\2\2\r\2\u01b0"+
		"\u01b4\u01c4\u01c8\u01d2\u01da\u01e0\u01e2\u01e8\u01f4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}