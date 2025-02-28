package de.jplag.cpp2;

import java.io.File;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import de.jplag.AbstractParser;
import de.jplag.antlr.AbstractAntlrListener;
import de.jplag.antlr.AbstractAntlrParserAdapter;
import de.jplag.antlr.TokenCollector;
import de.jplag.cpp2.grammar.CPP14Lexer;
import de.jplag.cpp2.grammar.CPP14Parser;

/**
 * The adapter between {@link AbstractParser} and the ANTLR based parser of this language module.
 */
public class CPPParserAdapter extends AbstractAntlrParserAdapter<CPP14Parser> {
    @Override
    protected Lexer createLexer(CharStream input) {
        return new CPP14Lexer(input);
    }

    @Override
    protected CPP14Parser createParser(CommonTokenStream tokenStream) {
        return new CPP14Parser(tokenStream);
    }

    @Override
    protected ParserRuleContext getEntryContext(CPP14Parser parser) {
        return parser.translationUnit();
    }

    @Override
    protected AbstractAntlrListener createListener(TokenCollector collector, File currentFile) {
        return new CPPListener(collector, currentFile);
    }
}
