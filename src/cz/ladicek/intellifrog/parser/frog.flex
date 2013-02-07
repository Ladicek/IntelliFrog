package cz.ladicek.intellifrog.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static cz.ladicek.intellifrog.parser.FrogTypes.*;
import static cz.ladicek.intellifrog.parser.FrogParserDefinition.*;

%%

%unicode
%class _FrogLexer
%implements FlexLexer
%function advance
%type IElementType

%{
  public _FrogLexer() {
    this((java.io.Reader) null);
  }
%}

LineTerminator     = \r|\n|\r\n
InputCharacter     = [^\r\n]
Whitespace         = {LineTerminator} | [ \t\f]

LineComment        = "//" {InputCharacter}* {LineTerminator}?
BlockComment       = "/*" [^*] ~"*/" | "/*" "*"+ "/"
DocComment         = "/*" "*"+ [^/*] ~"*/"

Digit              = [0-9]
//Letter             = [\u0024|\u0041-\u005a|\u005f|u0061-u007a|\u00A2-\u00A5|\u00AA-\u00AA|\u00B5-\u00B5|\u00BA-\u00BA|\u00c0-\u00d6|\u00d8-\u00f6|\u00f8-\u00ff]
Letter             = [a-zA-Z]
Special            = [\._\-+@#~$%\^&]
Word               = {Letter} ({Letter}|{Digit}|{Special})*

SimpleString       = \" ( [^\r\n\"\\] | (\\ ([ntbrf'\"\\]|[0-3][0-7][0-7]) ) )* \"
MultilineString    = "##" ( [^#\\] | (\\ ([ntbrf'#\\]|[0-3][0-7][0-7]) ) )* "#"

Integer            = "-"? {Digit}+
Long               = {Integer} [lL]
Exponent           = [eE] [+\-]? {Digit}+
FloatBase          = "-"? ({Digit}+ "." {Digit}* {Exponent}?) | ("." {Digit}+ {Exponent}?) | ({Digit}+ {Exponent}?)
Float              = {FloatBase} [fF]?
Double             = {FloatBase} [dD]

Hex                = "#HEX#" [a-fA-F0-9 \n\t]* "#"
Oct                = "#OCT#" [0-7 \n\t]* "#"
Bin                = "#BIN#" [0-1 \n\t]* "#"
Dec                = "#DEC#" [0-9 \n\t]* "#"
B64                = "#B64#" [A-Za-z0-9/+ \n\t]* "#"

%%

// keywords
"APPLY"            { return FROG_APPLY; }
"ASSERT"           { return FROG_ASSERT; }
"ATTRIB"           { return FROG_ATTRIB; }
"CONSTANT"         { return FROG_CONSTANT; }
"DATA"             { return FROG_DATA; }
"HERE"             { return FROG_HERE; }
"HOST"             { return FROG_HOST; }
"LAZY"             { return FROG_LAZY; }
"NULL"             { return FROG_NULL; }
"OPTIONAL"         { return FROG_OPTIONAL; }
"PARENT"           { return FROG_PARENT; }
"PROCESS"          { return FROG_PROCESS; }
"PROPERTY"         { return FROG_PROPERTY; }
"IPROPERTY"        { return FROG_IPROPERTY; }
"ENVPROPERTY"      { return FROG_ENVPROPERTY; }
"IENVPROPERTY"     { return FROG_IENVPROPERTY; }
"ROOT"             { return FROG_ROOT; }
"TBD"              { return FROG_TBD; }
"THIS"             { return FROG_THIS; }
"VAR"              { return FROG_VAR; }

"IF"               { return FROG_IF; }
"THEN"             { return FROG_THEN; }
"ELSE"             { return FROG_ELSE; }
"FI"               { return FROG_FI; }
"SWITCH"           { return FROG_SWITCH; }
"ENDSWITCH"        { return FROG_END_SWITCH; }

"#codebase"        { return FROG_CODEBASE; }
"extends"          { return FROG_EXTENDS; }
"false"            { return FROG_FALSE; }
"#include"         { return FROG_INCLUDE; }
"#include?"        { return FROG_INCLUDE_OPTIONALLY; }
"true"             { return FROG_TRUE; }

// symbols
","                { return FROG_COMMA; }
":"                { return FROG_REFERENCE_SEPARATOR; }
";"                { return FROG_SEMICOLON; }

"("                { return FROG_LEFT_PAREN; }
")"                { return FROG_RIGHT_PAREN; }
"{"                { return FROG_LEFT_BRACE; }
"}"                { return FROG_RIGHT_BRACE; }
"["                { return FROG_LEFT_BRACKET; }
"]"                { return FROG_RIGHT_BRACKET; }
"[|"               { return FROG_START_VECTOR; }
"|]"               { return FROG_END_VECTOR; }

"--"               { return FROG_UNIQUE; }

"+"                { return FROG_OP_ADD; }
"-"                { return FROG_OP_SUB; }
"*"                { return FROG_OP_MUL; }
"/"                { return FROG_OP_DIV; }
"%"                { return FROG_OP_MOD; }
"++"               { return FROG_OP_CONCAT; }
"<>"               { return FROG_OP_APPEND; }

"=="               { return FROG_OP_EQ; }
"!="               { return FROG_OP_NE; }
">="               { return FROG_OP_GE; }
"<="               { return FROG_OP_LE; }
">"                { return FROG_OP_GT; }
"<"                { return FROG_OP_LT; }

"&&"               { return FROG_OP_AND; }
"||"               { return FROG_OP_OR; }
"x|"               { return FROG_OP_XOR; }
"!"                { return FROG_OP_NOT; }
"->"               { return FROG_OP_IMPLIES; }

// elements
{Word}             { return FROG_WORD; }

{SimpleString}     { return FROG_SIMPLE_STRING; }
{MultilineString}  { return FROG_MULTILINE_STRING; }

{Integer}          { return FROG_INTEGER; }
{Long}             { return FROG_LONG; }
{Float}            { return FROG_FLOAT; }
{Double}           { return FROG_DOUBLE; }

{Hex}              { return FROG_BYTE_ARRAY; }
{Oct}              { return FROG_BYTE_ARRAY; }
{Bin}              { return FROG_BYTE_ARRAY; }
{Dec}              { return FROG_BYTE_ARRAY; }
{B64}              { return FROG_BYTE_ARRAY; }

// other
{LineComment}      { return LINE_COMMENT; }
{BlockComment}     { return BLOCK_COMMENT; }
{DocComment}       { return DOC_COMMENT; }

{Whitespace}       { return com.intellij.psi.TokenType.WHITE_SPACE; }

.                  { return com.intellij.psi.TokenType.BAD_CHARACTER; }
