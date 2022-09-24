// Generated from /Users/ana_raicu/Desktop/UNI/y2/50007/Compilers/WACC_08/src/main/antlr4/WACCLexer.g4 by ANTLR 4.9.2
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
		CALL=18, SEMICOLON=19, COMMA=20, HASH=21, OPEN_PARENTHESES=22, CLOSE_PARENTHESES=23, 
		OPEN_SQUARE=24, CLOSE_SQUARE=25, ASSIGN=26, INT=27, BOOL=28, CHAR=29, 
		STRING=30, NOT=31, LEN=32, ORD=33, CHR=34, MULT=35, DIV=36, MOD=37, PLUS=38, 
		MINUS=39, GT=40, GTE=41, LT=42, LTE=43, EQUAL=44, NOT_EQUAL=45, AND=46, 
		OR=47, NULL=48, PAIR=49, NEW_PAIR=50, FST=51, SND=52, INT_LITER=53, BOOL_LITER=54, 
		CHAR_LITER=55, STRING_LITER=56, IDENT=57, WHITESPACE=58, EOL=59, NON_EOL=60, 
		COMMENT=61;
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
			"SEMICOLON", "COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE", "CLOSE_SQUARE", "ASSIGN", "INT", "BOOL", "CHAR", "STRING", 
			"NOT", "LEN", "ORD", "CHR", "MULT", "DIV", "MOD", "PLUS", "MINUS", "GT", 
			"GTE", "LT", "LTE", "EQUAL", "NOT_EQUAL", "AND", "OR", "NULL", "PAIR", 
			"NEW_PAIR", "FST", "SND", "UNDERSCORE", "LOWER_LETTER", "UPPER_LETTER", 
			"DIGIT", "ESCAPED_CHAR", "CHARACTER", "INT_SIGN", "TRUE", "FALSE", "INT_LITER", 
			"BOOL_LITER", "CHAR_LITER", "STRING_LITER", "IDENT", "WHITESPACE", "EOL", 
			"NON_EOL", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
			"'exit'", "'print'", "'println'", "'if'", "'then'", "'else'", "'fi'", 
			"'while'", "'do'", "'done'", "'call'", "';'", "','", "'#'", "'('", "')'", 
			"'['", "']'", "'='", "'int'", "'bool'", "'char'", "'string'", "'!'", 
			"'len'", "'ord'", "'chr'", "'*'", "'/'", "'%'", "'+'", "'-'", "'>'", 
			"'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'null'", "'pair'", 
			"'newpair'", "'fst'", "'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "IS", "SKIP2", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", 
			"CALL", "SEMICOLON", "COMMA", "HASH", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE", "CLOSE_SQUARE", "ASSIGN", "INT", "BOOL", "CHAR", "STRING", 
			"NOT", "LEN", "ORD", "CHR", "MULT", "DIV", "MOD", "PLUS", "MINUS", "GT", 
			"GTE", "LT", "LTE", "EQUAL", "NOT_EQUAL", "AND", "OR", "NULL", "PAIR", 
			"NEW_PAIR", "FST", "SND", "INT_LITER", "BOOL_LITER", "CHAR_LITER", "STRING_LITER", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u01ad\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3%\3%\3&"+
		"\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3.\3/\3"+
		"/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3;\5;\u0163\n;"+
		"\3<\3<\5<\u0167\n<\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3?\6?\u0175\n?\r?"+
		"\16?\u0176\3@\3@\5@\u017b\n@\3A\3A\3A\3A\3B\3B\7B\u0183\nB\fB\16B\u0186"+
		"\13B\3B\3B\3C\3C\3C\5C\u018d\nC\3C\3C\3C\3C\7C\u0193\nC\fC\16C\u0196\13"+
		"C\3D\6D\u0199\nD\rD\16D\u019a\3D\3D\3E\3E\3F\3F\3G\3G\7G\u01a5\nG\fG\16"+
		"G\u01a8\13G\3G\3G\3G\3G\2\2H\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65i\66k\2m\2o\2q\2s\2u\2w\2y\2{\2}\67\1778\u00819\u0083"+
		":\u0085;\u0087<\u0089=\u008b>\u008d?\3\2\6\13\2$$))\62\62^^ddhhppttvv"+
		"\5\2$$))^^\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u01b0\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2"+
		"\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2"+
		"e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\3\u008f\3\2\2\2\5\u0095\3\2\2\2\7\u0099\3\2\2"+
		"\2\t\u009c\3\2\2\2\13\u00a1\3\2\2\2\r\u00a6\3\2\2\2\17\u00ab\3\2\2\2\21"+
		"\u00b2\3\2\2\2\23\u00b7\3\2\2\2\25\u00bd\3\2\2\2\27\u00c5\3\2\2\2\31\u00c8"+
		"\3\2\2\2\33\u00cd\3\2\2\2\35\u00d2\3\2\2\2\37\u00d5\3\2\2\2!\u00db\3\2"+
		"\2\2#\u00de\3\2\2\2%\u00e3\3\2\2\2\'\u00e8\3\2\2\2)\u00ea\3\2\2\2+\u00ec"+
		"\3\2\2\2-\u00ee\3\2\2\2/\u00f0\3\2\2\2\61\u00f2\3\2\2\2\63\u00f4\3\2\2"+
		"\2\65\u00f6\3\2\2\2\67\u00f8\3\2\2\29\u00fc\3\2\2\2;\u0101\3\2\2\2=\u0106"+
		"\3\2\2\2?\u010d\3\2\2\2A\u010f\3\2\2\2C\u0113\3\2\2\2E\u0117\3\2\2\2G"+
		"\u011b\3\2\2\2I\u011d\3\2\2\2K\u011f\3\2\2\2M\u0121\3\2\2\2O\u0123\3\2"+
		"\2\2Q\u0125\3\2\2\2S\u0127\3\2\2\2U\u012a\3\2\2\2W\u012c\3\2\2\2Y\u012f"+
		"\3\2\2\2[\u0132\3\2\2\2]\u0135\3\2\2\2_\u0138\3\2\2\2a\u013b\3\2\2\2c"+
		"\u0140\3\2\2\2e\u0145\3\2\2\2g\u014d\3\2\2\2i\u0151\3\2\2\2k\u0155\3\2"+
		"\2\2m\u0157\3\2\2\2o\u0159\3\2\2\2q\u015b\3\2\2\2s\u015d\3\2\2\2u\u0162"+
		"\3\2\2\2w\u0166\3\2\2\2y\u0168\3\2\2\2{\u016d\3\2\2\2}\u0174\3\2\2\2\177"+
		"\u017a\3\2\2\2\u0081\u017c\3\2\2\2\u0083\u0180\3\2\2\2\u0085\u018c\3\2"+
		"\2\2\u0087\u0198\3\2\2\2\u0089\u019e\3\2\2\2\u008b\u01a0\3\2\2\2\u008d"+
		"\u01a2\3\2\2\2\u008f\u0090\7d\2\2\u0090\u0091\7g\2\2\u0091\u0092\7i\2"+
		"\2\u0092\u0093\7k\2\2\u0093\u0094\7p\2\2\u0094\4\3\2\2\2\u0095\u0096\7"+
		"g\2\2\u0096\u0097\7p\2\2\u0097\u0098\7f\2\2\u0098\6\3\2\2\2\u0099\u009a"+
		"\7k\2\2\u009a\u009b\7u\2\2\u009b\b\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e"+
		"\7m\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7r\2\2\u00a0\n\3\2\2\2\u00a1\u00a2"+
		"\7t\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7f\2\2\u00a5"+
		"\f\3\2\2\2\u00a6\u00a7\7h\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7g\2\2\u00a9"+
		"\u00aa\7g\2\2\u00aa\16\3\2\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7g\2\2\u00ad"+
		"\u00ae\7v\2\2\u00ae\u00af\7w\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7p\2\2"+
		"\u00b1\20\3\2\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7z\2\2\u00b4\u00b5\7"+
		"k\2\2\u00b5\u00b6\7v\2\2\u00b6\22\3\2\2\2\u00b7\u00b8\7r\2\2\u00b8\u00b9"+
		"\7t\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7v\2\2\u00bc"+
		"\24\3\2\2\2\u00bd\u00be\7r\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7k\2\2\u00c0"+
		"\u00c1\7p\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7p\2\2"+
		"\u00c4\26\3\2\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7h\2\2\u00c7\30\3\2\2"+
		"\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7j\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc"+
		"\7p\2\2\u00cc\32\3\2\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0"+
		"\7u\2\2\u00d0\u00d1\7g\2\2\u00d1\34\3\2\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4"+
		"\7k\2\2\u00d4\36\3\2\2\2\u00d5\u00d6\7y\2\2\u00d6\u00d7\7j\2\2\u00d7\u00d8"+
		"\7k\2\2\u00d8\u00d9\7n\2\2\u00d9\u00da\7g\2\2\u00da \3\2\2\2\u00db\u00dc"+
		"\7f\2\2\u00dc\u00dd\7q\2\2\u00dd\"\3\2\2\2\u00de\u00df\7f\2\2\u00df\u00e0"+
		"\7q\2\2\u00e0\u00e1\7p\2\2\u00e1\u00e2\7g\2\2\u00e2$\3\2\2\2\u00e3\u00e4"+
		"\7e\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7n\2\2\u00e7"+
		"&\3\2\2\2\u00e8\u00e9\7=\2\2\u00e9(\3\2\2\2\u00ea\u00eb\7.\2\2\u00eb*"+
		"\3\2\2\2\u00ec\u00ed\7%\2\2\u00ed,\3\2\2\2\u00ee\u00ef\7*\2\2\u00ef.\3"+
		"\2\2\2\u00f0\u00f1\7+\2\2\u00f1\60\3\2\2\2\u00f2\u00f3\7]\2\2\u00f3\62"+
		"\3\2\2\2\u00f4\u00f5\7_\2\2\u00f5\64\3\2\2\2\u00f6\u00f7\7?\2\2\u00f7"+
		"\66\3\2\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fb"+
		"8\3\2\2\2\u00fc\u00fd\7d\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7q\2\2\u00ff"+
		"\u0100\7n\2\2\u0100:\3\2\2\2\u0101\u0102\7e\2\2\u0102\u0103\7j\2\2\u0103"+
		"\u0104\7c\2\2\u0104\u0105\7t\2\2\u0105<\3\2\2\2\u0106\u0107\7u\2\2\u0107"+
		"\u0108\7v\2\2\u0108\u0109\7t\2\2\u0109\u010a\7k\2\2\u010a\u010b\7p\2\2"+
		"\u010b\u010c\7i\2\2\u010c>\3\2\2\2\u010d\u010e\7#\2\2\u010e@\3\2\2\2\u010f"+
		"\u0110\7n\2\2\u0110\u0111\7g\2\2\u0111\u0112\7p\2\2\u0112B\3\2\2\2\u0113"+
		"\u0114\7q\2\2\u0114\u0115\7t\2\2\u0115\u0116\7f\2\2\u0116D\3\2\2\2\u0117"+
		"\u0118\7e\2\2\u0118\u0119\7j\2\2\u0119\u011a\7t\2\2\u011aF\3\2\2\2\u011b"+
		"\u011c\7,\2\2\u011cH\3\2\2\2\u011d\u011e\7\61\2\2\u011eJ\3\2\2\2\u011f"+
		"\u0120\7\'\2\2\u0120L\3\2\2\2\u0121\u0122\7-\2\2\u0122N\3\2\2\2\u0123"+
		"\u0124\7/\2\2\u0124P\3\2\2\2\u0125\u0126\7@\2\2\u0126R\3\2\2\2\u0127\u0128"+
		"\7@\2\2\u0128\u0129\7?\2\2\u0129T\3\2\2\2\u012a\u012b\7>\2\2\u012bV\3"+
		"\2\2\2\u012c\u012d\7>\2\2\u012d\u012e\7?\2\2\u012eX\3\2\2\2\u012f\u0130"+
		"\7?\2\2\u0130\u0131\7?\2\2\u0131Z\3\2\2\2\u0132\u0133\7#\2\2\u0133\u0134"+
		"\7?\2\2\u0134\\\3\2\2\2\u0135\u0136\7(\2\2\u0136\u0137\7(\2\2\u0137^\3"+
		"\2\2\2\u0138\u0139\7~\2\2\u0139\u013a\7~\2\2\u013a`\3\2\2\2\u013b\u013c"+
		"\7p\2\2\u013c\u013d\7w\2\2\u013d\u013e\7n\2\2\u013e\u013f\7n\2\2\u013f"+
		"b\3\2\2\2\u0140\u0141\7r\2\2\u0141\u0142\7c\2\2\u0142\u0143\7k\2\2\u0143"+
		"\u0144\7t\2\2\u0144d\3\2\2\2\u0145\u0146\7p\2\2\u0146\u0147\7g\2\2\u0147"+
		"\u0148\7y\2\2\u0148\u0149\7r\2\2\u0149\u014a\7c\2\2\u014a\u014b\7k\2\2"+
		"\u014b\u014c\7t\2\2\u014cf\3\2\2\2\u014d\u014e\7h\2\2\u014e\u014f\7u\2"+
		"\2\u014f\u0150\7v\2\2\u0150h\3\2\2\2\u0151\u0152\7u\2\2\u0152\u0153\7"+
		"p\2\2\u0153\u0154\7f\2\2\u0154j\3\2\2\2\u0155\u0156\7a\2\2\u0156l\3\2"+
		"\2\2\u0157\u0158\4c|\2\u0158n\3\2\2\2\u0159\u015a\4C\\\2\u015ap\3\2\2"+
		"\2\u015b\u015c\4\62;\2\u015cr\3\2\2\2\u015d\u015e\t\2\2\2\u015et\3\2\2"+
		"\2\u015f\u0163\n\3\2\2\u0160\u0161\7^\2\2\u0161\u0163\5s:\2\u0162\u015f"+
		"\3\2\2\2\u0162\u0160\3\2\2\2\u0163v\3\2\2\2\u0164\u0167\5M\'\2\u0165\u0167"+
		"\5O(\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167x\3\2\2\2\u0168\u0169"+
		"\7v\2\2\u0169\u016a\7t\2\2\u016a\u016b\7w\2\2\u016b\u016c\7g\2\2\u016c"+
		"z\3\2\2\2\u016d\u016e\7h\2\2\u016e\u016f\7c\2\2\u016f\u0170\7n\2\2\u0170"+
		"\u0171\7u\2\2\u0171\u0172\7g\2\2\u0172|\3\2\2\2\u0173\u0175\5q9\2\u0174"+
		"\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177~\3\2\2\2\u0178\u017b\5y=\2\u0179\u017b\5{>\2\u017a\u0178\3"+
		"\2\2\2\u017a\u0179\3\2\2\2\u017b\u0080\3\2\2\2\u017c\u017d\7)\2\2\u017d"+
		"\u017e\5u;\2\u017e\u017f\7)\2\2\u017f\u0082\3\2\2\2\u0180\u0184\7$\2\2"+
		"\u0181\u0183\5u;\2\u0182\u0181\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182"+
		"\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0187\3\2\2\2\u0186\u0184\3\2\2\2\u0187"+
		"\u0188\7$\2\2\u0188\u0084\3\2\2\2\u0189\u018d\5k\66\2\u018a\u018d\5m\67"+
		"\2\u018b\u018d\5o8\2\u018c\u0189\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018b"+
		"\3\2\2\2\u018d\u0194\3\2\2\2\u018e\u0193\5k\66\2\u018f\u0193\5m\67\2\u0190"+
		"\u0193\5o8\2\u0191\u0193\5q9\2\u0192\u018e\3\2\2\2\u0192\u018f\3\2\2\2"+
		"\u0192\u0190\3\2\2\2\u0192\u0191\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192"+
		"\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0086\3\2\2\2\u0196\u0194\3\2\2\2\u0197"+
		"\u0199\t\4\2\2\u0198\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u0198\3\2"+
		"\2\2\u019a\u019b\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\bD\2\2\u019d"+
		"\u0088\3\2\2\2\u019e\u019f\t\5\2\2\u019f\u008a\3\2\2\2\u01a0\u01a1\n\5"+
		"\2\2\u01a1\u008c\3\2\2\2\u01a2\u01a6\5+\26\2\u01a3\u01a5\5\u008bF\2\u01a4"+
		"\u01a3\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2"+
		"\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01aa\5\u0089E\2\u01aa"+
		"\u01ab\3\2\2\2\u01ab\u01ac\bG\2\2\u01ac\u008e\3\2\2\2\r\2\u0162\u0166"+
		"\u0176\u017a\u0184\u018c\u0192\u0194\u019a\u01a6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}