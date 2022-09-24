// Generated from parser/WACCLexer.g4 by ANTLR 4.9.3
package parser;
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
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP2=4, READ=5, FREE=6, RETURN=7, EXIT=8, PRINT=9, 
		PRINTLN=10, IF=11, THEN=12, ELSE=13, FI=14, WHILE=15, DO=16, DONE=17, 
		CALL=18, CALL_IN_CLASS=19, FOR=20, BREAK=21, CONTINUE=22, CLASS=23, PRIVATE=24, 
		PUBLIC=25, NEW=26, SEMICOLON=27, COMMA=28, HASH=29, OPEN_PARENTHESES=30, 
		CLOSE_PARENTHESES=31, OPEN_SQUARE=32, CLOSE_SQUARE=33, DOT=34, ASSIGN=35, 
		INT=36, BOOL=37, CHAR=38, STRING=39, NOT=40, LEN=41, ORD=42, CHR=43, MULT=44, 
		DIV=45, MOD=46, PLUS=47, MINUS=48, GT=49, GTE=50, LT=51, LTE=52, EQUAL=53, 
		NOT_EQUAL=54, AND=55, OR=56, BITWISE_AND=57, BITWISE_OR=58, BITWISE_NOT=59, 
		BITWISE_XOR=60, NULL=61, PAIR=62, NEW_PAIR=63, FST=64, SND=65, INT_LITER=66, 
		BOOL_LITER=67, CHAR_LITER=68, STRING_LITER=69, IDENT=70, WHITESPACE=71, 
		EOL=72, NON_EOL=73, COMMENT=74;
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
			"CALL_IN_CLASS", "FOR", "BREAK", "CONTINUE", "CLASS", "PRIVATE", "PUBLIC", 
			"NEW", "SEMICOLON", "COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE", "CLOSE_SQUARE", "DOT", "ASSIGN", "INT", "BOOL", "CHAR", 
			"STRING", "NOT", "LEN", "ORD", "CHR", "MULT", "DIV", "MOD", "PLUS", "MINUS", 
			"GT", "GTE", "LT", "LTE", "EQUAL", "NOT_EQUAL", "AND", "OR", "BITWISE_AND", 
			"BITWISE_OR", "BITWISE_NOT", "BITWISE_XOR", "NULL", "PAIR", "NEW_PAIR", 
			"FST", "SND", "UNDERSCORE", "LOWER_LETTER", "UPPER_LETTER", "DIGIT", 
			"ESCAPED_CHAR", "CHARACTER", "INT_SIGN", "TRUE", "FALSE", "INT_LITER", 
			"BOOL_LITER", "CHAR_LITER", "STRING_LITER", "IDENT", "WHITESPACE", "EOL", 
			"NON_EOL", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'do'", "'done'", "'call'", "'call_m'", "'for'", "'break'", 
			"'continue'", "'class'", "'private'", "'public'", "'new'", "';'", "','", 
			"'#'", "'('", "')'", "'['", "']'", "'.'", "'='", "'int'", "'bool'", "'char'", 
			"'string'", "'!'", "'len'", "'ord'", "'chr'", "'*'", "'/'", "'%'", "'+'", 
			"'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", 
			"'&'", "'|'", "'~'", "'^'", "'null'", "'pair'", "'newpair'", "'fst'", 
			"'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP2", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"CALL", "CALL_IN_CLASS", "FOR", "BREAK", "CONTINUE", "CLASS", "PRIVATE", 
			"PUBLIC", "NEW", "SEMICOLON", "COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2L\u0204\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3"+
		".\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38\39\39\39\3:\3:\3;\3"+
		";\3<\3<\3=\3=\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3"+
		"A\3A\3A\3A\3B\3B\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3H\5H\u01ba"+
		"\nH\3I\3I\5I\u01be\nI\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3L\6L\u01cc\nL"+
		"\rL\16L\u01cd\3M\3M\5M\u01d2\nM\3N\3N\3N\3N\3O\3O\7O\u01da\nO\fO\16O\u01dd"+
		"\13O\3O\3O\3P\3P\3P\5P\u01e4\nP\3P\3P\3P\3P\7P\u01ea\nP\fP\16P\u01ed\13"+
		"P\3Q\6Q\u01f0\nQ\rQ\16Q\u01f1\3Q\3Q\3R\3R\3S\3S\3T\3T\7T\u01fc\nT\fT\16"+
		"T\u01ff\13T\3T\3T\3T\3T\2\2U\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085\2"+
		"\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097"+
		"D\u0099E\u009bF\u009dG\u009fH\u00a1I\u00a3J\u00a5K\u00a7L\3\2\6\13\2$"+
		"$))\62\62^^ddhhppttvv\5\2$$))^^\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0207"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2"+
		"{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2"+
		"\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\3\u00a9"+
		"\3\2\2\2\5\u00af\3\2\2\2\7\u00b3\3\2\2\2\t\u00b6\3\2\2\2\13\u00bb\3\2"+
		"\2\2\r\u00c0\3\2\2\2\17\u00c5\3\2\2\2\21\u00cc\3\2\2\2\23\u00d1\3\2\2"+
		"\2\25\u00d7\3\2\2\2\27\u00df\3\2\2\2\31\u00e2\3\2\2\2\33\u00e7\3\2\2\2"+
		"\35\u00ec\3\2\2\2\37\u00ef\3\2\2\2!\u00f5\3\2\2\2#\u00f8\3\2\2\2%\u00fd"+
		"\3\2\2\2\'\u0102\3\2\2\2)\u0109\3\2\2\2+\u010d\3\2\2\2-\u0113\3\2\2\2"+
		"/\u011c\3\2\2\2\61\u0122\3\2\2\2\63\u012a\3\2\2\2\65\u0131\3\2\2\2\67"+
		"\u0135\3\2\2\29\u0137\3\2\2\2;\u0139\3\2\2\2=\u013b\3\2\2\2?\u013d\3\2"+
		"\2\2A\u013f\3\2\2\2C\u0141\3\2\2\2E\u0143\3\2\2\2G\u0145\3\2\2\2I\u0147"+
		"\3\2\2\2K\u014b\3\2\2\2M\u0150\3\2\2\2O\u0155\3\2\2\2Q\u015c\3\2\2\2S"+
		"\u015e\3\2\2\2U\u0162\3\2\2\2W\u0166\3\2\2\2Y\u016a\3\2\2\2[\u016c\3\2"+
		"\2\2]\u016e\3\2\2\2_\u0170\3\2\2\2a\u0172\3\2\2\2c\u0174\3\2\2\2e\u0176"+
		"\3\2\2\2g\u0179\3\2\2\2i\u017b\3\2\2\2k\u017e\3\2\2\2m\u0181\3\2\2\2o"+
		"\u0184\3\2\2\2q\u0187\3\2\2\2s\u018a\3\2\2\2u\u018c\3\2\2\2w\u018e\3\2"+
		"\2\2y\u0190\3\2\2\2{\u0192\3\2\2\2}\u0197\3\2\2\2\177\u019c\3\2\2\2\u0081"+
		"\u01a4\3\2\2\2\u0083\u01a8\3\2\2\2\u0085\u01ac\3\2\2\2\u0087\u01ae\3\2"+
		"\2\2\u0089\u01b0\3\2\2\2\u008b\u01b2\3\2\2\2\u008d\u01b4\3\2\2\2\u008f"+
		"\u01b9\3\2\2\2\u0091\u01bd\3\2\2\2\u0093\u01bf\3\2\2\2\u0095\u01c4\3\2"+
		"\2\2\u0097\u01cb\3\2\2\2\u0099\u01d1\3\2\2\2\u009b\u01d3\3\2\2\2\u009d"+
		"\u01d7\3\2\2\2\u009f\u01e3\3\2\2\2\u00a1\u01ef\3\2\2\2\u00a3\u01f5\3\2"+
		"\2\2\u00a5\u01f7\3\2\2\2\u00a7\u01f9\3\2\2\2\u00a9\u00aa\7d\2\2\u00aa"+
		"\u00ab\7g\2\2\u00ab\u00ac\7i\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7p\2\2"+
		"\u00ae\4\3\2\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7p\2\2\u00b1\u00b2\7f"+
		"\2\2\u00b2\6\3\2\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7u\2\2\u00b5\b\3\2"+
		"\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7m\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba"+
		"\7r\2\2\u00ba\n\3\2\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be"+
		"\7c\2\2\u00be\u00bf\7f\2\2\u00bf\f\3\2\2\2\u00c0\u00c1\7h\2\2\u00c1\u00c2"+
		"\7t\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7g\2\2\u00c4\16\3\2\2\2\u00c5\u00c6"+
		"\7t\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9\7w\2\2\u00c9"+
		"\u00ca\7t\2\2\u00ca\u00cb\7p\2\2\u00cb\20\3\2\2\2\u00cc\u00cd\7g\2\2\u00cd"+
		"\u00ce\7z\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7v\2\2\u00d0\22\3\2\2\2\u00d1"+
		"\u00d2\7r\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7p\2\2"+
		"\u00d5\u00d6\7v\2\2\u00d6\24\3\2\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7"+
		"t\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd"+
		"\7n\2\2\u00dd\u00de\7p\2\2\u00de\26\3\2\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1"+
		"\7h\2\2\u00e1\30\3\2\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7j\2\2\u00e4\u00e5"+
		"\7g\2\2\u00e5\u00e6\7p\2\2\u00e6\32\3\2\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9"+
		"\7n\2\2\u00e9\u00ea\7u\2\2\u00ea\u00eb\7g\2\2\u00eb\34\3\2\2\2\u00ec\u00ed"+
		"\7h\2\2\u00ed\u00ee\7k\2\2\u00ee\36\3\2\2\2\u00ef\u00f0\7y\2\2\u00f0\u00f1"+
		"\7j\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7g\2\2\u00f4"+
		" \3\2\2\2\u00f5\u00f6\7f\2\2\u00f6\u00f7\7q\2\2\u00f7\"\3\2\2\2\u00f8"+
		"\u00f9\7f\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7g\2\2"+
		"\u00fc$\3\2\2\2\u00fd\u00fe\7e\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100\7n\2"+
		"\2\u0100\u0101\7n\2\2\u0101&\3\2\2\2\u0102\u0103\7e\2\2\u0103\u0104\7"+
		"c\2\2\u0104\u0105\7n\2\2\u0105\u0106\7n\2\2\u0106\u0107\7a\2\2\u0107\u0108"+
		"\7o\2\2\u0108(\3\2\2\2\u0109\u010a\7h\2\2\u010a\u010b\7q\2\2\u010b\u010c"+
		"\7t\2\2\u010c*\3\2\2\2\u010d\u010e\7d\2\2\u010e\u010f\7t\2\2\u010f\u0110"+
		"\7g\2\2\u0110\u0111\7c\2\2\u0111\u0112\7m\2\2\u0112,\3\2\2\2\u0113\u0114"+
		"\7e\2\2\u0114\u0115\7q\2\2\u0115\u0116\7p\2\2\u0116\u0117\7v\2\2\u0117"+
		"\u0118\7k\2\2\u0118\u0119\7p\2\2\u0119\u011a\7w\2\2\u011a\u011b\7g\2\2"+
		"\u011b.\3\2\2\2\u011c\u011d\7e\2\2\u011d\u011e\7n\2\2\u011e\u011f\7c\2"+
		"\2\u011f\u0120\7u\2\2\u0120\u0121\7u\2\2\u0121\60\3\2\2\2\u0122\u0123"+
		"\7r\2\2\u0123\u0124\7t\2\2\u0124\u0125\7k\2\2\u0125\u0126\7x\2\2\u0126"+
		"\u0127\7c\2\2\u0127\u0128\7v\2\2\u0128\u0129\7g\2\2\u0129\62\3\2\2\2\u012a"+
		"\u012b\7r\2\2\u012b\u012c\7w\2\2\u012c\u012d\7d\2\2\u012d\u012e\7n\2\2"+
		"\u012e\u012f\7k\2\2\u012f\u0130\7e\2\2\u0130\64\3\2\2\2\u0131\u0132\7"+
		"p\2\2\u0132\u0133\7g\2\2\u0133\u0134\7y\2\2\u0134\66\3\2\2\2\u0135\u0136"+
		"\7=\2\2\u01368\3\2\2\2\u0137\u0138\7.\2\2\u0138:\3\2\2\2\u0139\u013a\7"+
		"%\2\2\u013a<\3\2\2\2\u013b\u013c\7*\2\2\u013c>\3\2\2\2\u013d\u013e\7+"+
		"\2\2\u013e@\3\2\2\2\u013f\u0140\7]\2\2\u0140B\3\2\2\2\u0141\u0142\7_\2"+
		"\2\u0142D\3\2\2\2\u0143\u0144\7\60\2\2\u0144F\3\2\2\2\u0145\u0146\7?\2"+
		"\2\u0146H\3\2\2\2\u0147\u0148\7k\2\2\u0148\u0149\7p\2\2\u0149\u014a\7"+
		"v\2\2\u014aJ\3\2\2\2\u014b\u014c\7d\2\2\u014c\u014d\7q\2\2\u014d\u014e"+
		"\7q\2\2\u014e\u014f\7n\2\2\u014fL\3\2\2\2\u0150\u0151\7e\2\2\u0151\u0152"+
		"\7j\2\2\u0152\u0153\7c\2\2\u0153\u0154\7t\2\2\u0154N\3\2\2\2\u0155\u0156"+
		"\7u\2\2\u0156\u0157\7v\2\2\u0157\u0158\7t\2\2\u0158\u0159\7k\2\2\u0159"+
		"\u015a\7p\2\2\u015a\u015b\7i\2\2\u015bP\3\2\2\2\u015c\u015d\7#\2\2\u015d"+
		"R\3\2\2\2\u015e\u015f\7n\2\2\u015f\u0160\7g\2\2\u0160\u0161\7p\2\2\u0161"+
		"T\3\2\2\2\u0162\u0163\7q\2\2\u0163\u0164\7t\2\2\u0164\u0165\7f\2\2\u0165"+
		"V\3\2\2\2\u0166\u0167\7e\2\2\u0167\u0168\7j\2\2\u0168\u0169\7t\2\2\u0169"+
		"X\3\2\2\2\u016a\u016b\7,\2\2\u016bZ\3\2\2\2\u016c\u016d\7\61\2\2\u016d"+
		"\\\3\2\2\2\u016e\u016f\7\'\2\2\u016f^\3\2\2\2\u0170\u0171\7-\2\2\u0171"+
		"`\3\2\2\2\u0172\u0173\7/\2\2\u0173b\3\2\2\2\u0174\u0175\7@\2\2\u0175d"+
		"\3\2\2\2\u0176\u0177\7@\2\2\u0177\u0178\7?\2\2\u0178f\3\2\2\2\u0179\u017a"+
		"\7>\2\2\u017ah\3\2\2\2\u017b\u017c\7>\2\2\u017c\u017d\7?\2\2\u017dj\3"+
		"\2\2\2\u017e\u017f\7?\2\2\u017f\u0180\7?\2\2\u0180l\3\2\2\2\u0181\u0182"+
		"\7#\2\2\u0182\u0183\7?\2\2\u0183n\3\2\2\2\u0184\u0185\7(\2\2\u0185\u0186"+
		"\7(\2\2\u0186p\3\2\2\2\u0187\u0188\7~\2\2\u0188\u0189\7~\2\2\u0189r\3"+
		"\2\2\2\u018a\u018b\7(\2\2\u018bt\3\2\2\2\u018c\u018d\7~\2\2\u018dv\3\2"+
		"\2\2\u018e\u018f\7\u0080\2\2\u018fx\3\2\2\2\u0190\u0191\7`\2\2\u0191z"+
		"\3\2\2\2\u0192\u0193\7p\2\2\u0193\u0194\7w\2\2\u0194\u0195\7n\2\2\u0195"+
		"\u0196\7n\2\2\u0196|\3\2\2\2\u0197\u0198\7r\2\2\u0198\u0199\7c\2\2\u0199"+
		"\u019a\7k\2\2\u019a\u019b\7t\2\2\u019b~\3\2\2\2\u019c\u019d\7p\2\2\u019d"+
		"\u019e\7g\2\2\u019e\u019f\7y\2\2\u019f\u01a0\7r\2\2\u01a0\u01a1\7c\2\2"+
		"\u01a1\u01a2\7k\2\2\u01a2\u01a3\7t\2\2\u01a3\u0080\3\2\2\2\u01a4\u01a5"+
		"\7h\2\2\u01a5\u01a6\7u\2\2\u01a6\u01a7\7v\2\2\u01a7\u0082\3\2\2\2\u01a8"+
		"\u01a9\7u\2\2\u01a9\u01aa\7p\2\2\u01aa\u01ab\7f\2\2\u01ab\u0084\3\2\2"+
		"\2\u01ac\u01ad\7a\2\2\u01ad\u0086\3\2\2\2\u01ae\u01af\4c|\2\u01af\u0088"+
		"\3\2\2\2\u01b0\u01b1\4C\\\2\u01b1\u008a\3\2\2\2\u01b2\u01b3\4\62;\2\u01b3"+
		"\u008c\3\2\2\2\u01b4\u01b5\t\2\2\2\u01b5\u008e\3\2\2\2\u01b6\u01ba\n\3"+
		"\2\2\u01b7\u01b8\7^\2\2\u01b8\u01ba\5\u008dG\2\u01b9\u01b6\3\2\2\2\u01b9"+
		"\u01b7\3\2\2\2\u01ba\u0090\3\2\2\2\u01bb\u01be\5_\60\2\u01bc\u01be\5a"+
		"\61\2\u01bd\u01bb\3\2\2\2\u01bd\u01bc\3\2\2\2\u01be\u0092\3\2\2\2\u01bf"+
		"\u01c0\7v\2\2\u01c0\u01c1\7t\2\2\u01c1\u01c2\7w\2\2\u01c2\u01c3\7g\2\2"+
		"\u01c3\u0094\3\2\2\2\u01c4\u01c5\7h\2\2\u01c5\u01c6\7c\2\2\u01c6\u01c7"+
		"\7n\2\2\u01c7\u01c8\7u\2\2\u01c8\u01c9\7g\2\2\u01c9\u0096\3\2\2\2\u01ca"+
		"\u01cc\5\u008bF\2\u01cb\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cb"+
		"\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u0098\3\2\2\2\u01cf\u01d2\5\u0093J"+
		"\2\u01d0\u01d2\5\u0095K\2\u01d1\u01cf\3\2\2\2\u01d1\u01d0\3\2\2\2\u01d2"+
		"\u009a\3\2\2\2\u01d3\u01d4\7)\2\2\u01d4\u01d5\5\u008fH\2\u01d5\u01d6\7"+
		")\2\2\u01d6\u009c\3\2\2\2\u01d7\u01db\7$\2\2\u01d8\u01da\5\u008fH\2\u01d9"+
		"\u01d8\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc\3\2"+
		"\2\2\u01dc\u01de\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01df\7$\2\2\u01df"+
		"\u009e\3\2\2\2\u01e0\u01e4\5\u0085C\2\u01e1\u01e4\5\u0087D\2\u01e2\u01e4"+
		"\5\u0089E\2\u01e3\u01e0\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3\u01e2\3\2\2"+
		"\2\u01e4\u01eb\3\2\2\2\u01e5\u01ea\5\u0085C\2\u01e6\u01ea\5\u0087D\2\u01e7"+
		"\u01ea\5\u0089E\2\u01e8\u01ea\5\u008bF\2\u01e9\u01e5\3\2\2\2\u01e9\u01e6"+
		"\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u00a0\3\2\2\2\u01ed\u01eb\3\2"+
		"\2\2\u01ee\u01f0\t\4\2\2\u01ef\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1"+
		"\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f4\bQ"+
		"\2\2\u01f4\u00a2\3\2\2\2\u01f5\u01f6\t\5\2\2\u01f6\u00a4\3\2\2\2\u01f7"+
		"\u01f8\n\5\2\2\u01f8\u00a6\3\2\2\2\u01f9\u01fd\5;\36\2\u01fa\u01fc\5\u00a5"+
		"S\2\u01fb\u01fa\3\2\2\2\u01fc\u01ff\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fd"+
		"\u01fe\3\2\2\2\u01fe\u0200\3\2\2\2\u01ff\u01fd\3\2\2\2\u0200\u0201\5\u00a3"+
		"R\2\u0201\u0202\3\2\2\2\u0202\u0203\bT\2\2\u0203\u00a8\3\2\2\2\r\2\u01b9"+
		"\u01bd\u01cd\u01d1\u01db\u01e3\u01e9\u01eb\u01f1\u01fd\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}