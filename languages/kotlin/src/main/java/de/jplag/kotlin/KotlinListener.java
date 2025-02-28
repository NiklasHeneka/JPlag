package de.jplag.kotlin;

import static de.jplag.kotlin.KotlinTokenType.ASSIGNMENT;
import static de.jplag.kotlin.KotlinTokenType.BREAK;
import static de.jplag.kotlin.KotlinTokenType.CATCH;
import static de.jplag.kotlin.KotlinTokenType.CATCH_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.CATCH_BODY_START;
import static de.jplag.kotlin.KotlinTokenType.CLASS_BODY_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.CLASS_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.CLASS_DECLARATION;
import static de.jplag.kotlin.KotlinTokenType.COMPANION_DECLARATION;
import static de.jplag.kotlin.KotlinTokenType.CONSTRUCTOR;
import static de.jplag.kotlin.KotlinTokenType.CONTINUE;
import static de.jplag.kotlin.KotlinTokenType.CONTROL_STRUCTURE_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.CONTROL_STRUCTURE_BODY_START;
import static de.jplag.kotlin.KotlinTokenType.CREATE_OBJECT;
import static de.jplag.kotlin.KotlinTokenType.DO_WHILE_EXPRESSION_END;
import static de.jplag.kotlin.KotlinTokenType.DO_WHILE_EXPRESSION_START;
import static de.jplag.kotlin.KotlinTokenType.ENUM_CLASS_BODY_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.ENUM_CLASS_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.ENUM_ENTRY;
import static de.jplag.kotlin.KotlinTokenType.FINALLY;
import static de.jplag.kotlin.KotlinTokenType.FINALLY_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.FINALLY_BODY_START;
import static de.jplag.kotlin.KotlinTokenType.FOR_EXPRESSION_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.FOR_EXPRESSION_END;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_BODY_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_INVOCATION;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_LITERAL_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_LITERAL_END;
import static de.jplag.kotlin.KotlinTokenType.FUNCTION_PARAMETER;
import static de.jplag.kotlin.KotlinTokenType.GETTER;
import static de.jplag.kotlin.KotlinTokenType.IF_EXPRESSION_BEGIN;
import static de.jplag.kotlin.KotlinTokenType.IF_EXPRESSION_END;
import static de.jplag.kotlin.KotlinTokenType.IMPORT;
import static de.jplag.kotlin.KotlinTokenType.INITIALIZER;
import static de.jplag.kotlin.KotlinTokenType.INITIALIZER_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.INITIALIZER_BODY_START;
import static de.jplag.kotlin.KotlinTokenType.OBJECT_DECLARATION;
import static de.jplag.kotlin.KotlinTokenType.PACKAGE;
import static de.jplag.kotlin.KotlinTokenType.PROPERTY_DECLARATION;
import static de.jplag.kotlin.KotlinTokenType.RETURN;
import static de.jplag.kotlin.KotlinTokenType.SETTER;
import static de.jplag.kotlin.KotlinTokenType.THROW;
import static de.jplag.kotlin.KotlinTokenType.TRY_BODY_END;
import static de.jplag.kotlin.KotlinTokenType.TRY_BODY_START;
import static de.jplag.kotlin.KotlinTokenType.TRY_EXPRESSION;
import static de.jplag.kotlin.KotlinTokenType.TYPE_PARAMETER;
import static de.jplag.kotlin.KotlinTokenType.VARIABLE_DECLARATION;
import static de.jplag.kotlin.KotlinTokenType.WHEN_CONDITION;
import static de.jplag.kotlin.KotlinTokenType.WHEN_EXPRESSION_END;
import static de.jplag.kotlin.KotlinTokenType.WHEN_EXPRESSION_START;
import static de.jplag.kotlin.KotlinTokenType.WHILE_EXPRESSION_END;
import static de.jplag.kotlin.KotlinTokenType.WHILE_EXPRESSION_START;
import static de.jplag.kotlin.grammar.KotlinParser.AnonymousInitializerContext;
import static de.jplag.kotlin.grammar.KotlinParser.AssignmentOperatorContext;
import static de.jplag.kotlin.grammar.KotlinParser.CallSuffixContext;
import static de.jplag.kotlin.grammar.KotlinParser.CatchBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.CatchStatementContext;
import static de.jplag.kotlin.grammar.KotlinParser.ClassBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.ClassDeclarationContext;
import static de.jplag.kotlin.grammar.KotlinParser.ClassParameterContext;
import static de.jplag.kotlin.grammar.KotlinParser.CompanionObjectContext;
import static de.jplag.kotlin.grammar.KotlinParser.ConstructorInvocationContext;
import static de.jplag.kotlin.grammar.KotlinParser.ControlStructureBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.DoWhileExpressionContext;
import static de.jplag.kotlin.grammar.KotlinParser.EnumClassBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.EnumEntryContext;
import static de.jplag.kotlin.grammar.KotlinParser.FinallyBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.FinallyStatementContext;
import static de.jplag.kotlin.grammar.KotlinParser.ForExpressionContext;
import static de.jplag.kotlin.grammar.KotlinParser.FunctionBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.FunctionDeclarationContext;
import static de.jplag.kotlin.grammar.KotlinParser.FunctionLiteralContext;
import static de.jplag.kotlin.grammar.KotlinParser.FunctionValueParameterContext;
import static de.jplag.kotlin.grammar.KotlinParser.GetterContext;
import static de.jplag.kotlin.grammar.KotlinParser.IfExpressionContext;
import static de.jplag.kotlin.grammar.KotlinParser.ImportHeaderContext;
import static de.jplag.kotlin.grammar.KotlinParser.InitBlockContext;
import static de.jplag.kotlin.grammar.KotlinParser.ObjectDeclarationContext;
import static de.jplag.kotlin.grammar.KotlinParser.PackageHeaderContext;
import static de.jplag.kotlin.grammar.KotlinParser.PrimaryConstructorContext;
import static de.jplag.kotlin.grammar.KotlinParser.PropertyDeclarationContext;
import static de.jplag.kotlin.grammar.KotlinParser.SecondaryConstructorContext;
import static de.jplag.kotlin.grammar.KotlinParser.SetterContext;
import static de.jplag.kotlin.grammar.KotlinParser.TryBodyContext;
import static de.jplag.kotlin.grammar.KotlinParser.TryExpressionContext;
import static de.jplag.kotlin.grammar.KotlinParser.TypeParameterContext;
import static de.jplag.kotlin.grammar.KotlinParser.VariableDeclarationContext;
import static de.jplag.kotlin.grammar.KotlinParser.WhenConditionContext;
import static de.jplag.kotlin.grammar.KotlinParser.WhenExpressionContext;
import static de.jplag.kotlin.grammar.KotlinParser.WhileExpressionContext;

import java.io.File;

import de.jplag.antlr.AbstractAntlrListener;
import de.jplag.antlr.TokenCollector;
import de.jplag.kotlin.grammar.KotlinParser;

public class KotlinListener extends AbstractAntlrListener {
    public KotlinListener(TokenCollector collector, File currentFile) {
        super(collector, currentFile);

        this.mapRange(PackageHeaderContext.class, PACKAGE);
        this.mapRange(ImportHeaderContext.class, IMPORT);
        this.mapEnter(ClassDeclarationContext.class, CLASS_DECLARATION);
        this.mapRange(ObjectDeclarationContext.class, OBJECT_DECLARATION);
        this.mapRange(CompanionObjectContext.class, COMPANION_DECLARATION);
        this.mapRange(TypeParameterContext.class, TYPE_PARAMETER);
        this.mapRange(PrimaryConstructorContext.class, CONSTRUCTOR);
        this.mapRange(ClassParameterContext.class, PROPERTY_DECLARATION);
        this.mapEnterExit(ClassBodyContext.class, CLASS_BODY_BEGIN, CLASS_BODY_END);
        this.mapEnterExit(EnumClassBodyContext.class, ENUM_CLASS_BODY_BEGIN, ENUM_CLASS_BODY_END);
        this.mapEnter(EnumEntryContext.class, ENUM_ENTRY);
        this.mapRange(SecondaryConstructorContext.class, CONSTRUCTOR);
        this.mapEnter(PropertyDeclarationContext.class, PROPERTY_DECLARATION);
        this.mapEnter(AnonymousInitializerContext.class, INITIALIZER);
        this.mapEnterExit(InitBlockContext.class, INITIALIZER_BODY_START, INITIALIZER_BODY_END);
        this.mapEnter(FunctionDeclarationContext.class, FUNCTION);
        this.mapEnter(GetterContext.class, GETTER);
        this.mapEnter(SetterContext.class, SETTER);
        this.mapRange(FunctionValueParameterContext.class, FUNCTION_PARAMETER);
        this.mapEnterExit(FunctionBodyContext.class, FUNCTION_BODY_BEGIN, FUNCTION_BODY_END);
        this.mapEnterExit(FunctionLiteralContext.class, FUNCTION_LITERAL_BEGIN, FUNCTION_LITERAL_END);
        this.mapEnterExit(ForExpressionContext.class, FOR_EXPRESSION_BEGIN, FOR_EXPRESSION_END);
        this.mapEnterExit(IfExpressionContext.class, IF_EXPRESSION_BEGIN, IF_EXPRESSION_END);
        this.mapEnterExit(WhileExpressionContext.class, WHILE_EXPRESSION_START, WHILE_EXPRESSION_END);
        this.mapEnterExit(DoWhileExpressionContext.class, DO_WHILE_EXPRESSION_START, DO_WHILE_EXPRESSION_END);
        this.mapEnter(TryExpressionContext.class, TRY_EXPRESSION);
        this.mapEnterExit(TryBodyContext.class, TRY_BODY_START, TRY_BODY_END);
        this.mapEnter(CatchStatementContext.class, CATCH);
        this.mapEnterExit(CatchBodyContext.class, CATCH_BODY_START, CATCH_BODY_END);
        this.mapEnter(FinallyStatementContext.class, FINALLY);
        this.mapEnterExit(FinallyBodyContext.class, FINALLY_BODY_START, FINALLY_BODY_END);
        this.mapEnterExit(WhenExpressionContext.class, WHEN_EXPRESSION_START, WHEN_EXPRESSION_END);
        this.mapEnter(WhenConditionContext.class, WHEN_CONDITION);
        this.mapEnterExit(ControlStructureBodyContext.class, CONTROL_STRUCTURE_BODY_START, CONTROL_STRUCTURE_BODY_END);
        this.mapEnter(VariableDeclarationContext.class, VARIABLE_DECLARATION);
        this.mapRange(ConstructorInvocationContext.class, CREATE_OBJECT);
        this.mapRange(CallSuffixContext.class, FUNCTION_INVOCATION);
        this.mapEnter(AssignmentOperatorContext.class, ASSIGNMENT);

        this.mapTerminal(KotlinParser.THROW, THROW);
        this.mapTerminal(KotlinParser.RETURN, RETURN);
        this.mapTerminal(KotlinParser.CONTINUE, CONTINUE);
        this.mapTerminal(KotlinParser.BREAK, BREAK);
        this.mapTerminal(KotlinParser.BREAK_AT, BREAK);
    }
}
