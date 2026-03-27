# MicroJava Compiler

This repository contains a complete compiler for the MicroJava programming language, a subset of Java. The compiler performs lexical, syntax, and semantic analysis, and finally generates executable bytecode for a custom MicroJava Virtual Machine.

## Features

The compiler is built in stages, each handling a specific part of the compilation process:

*   **Lexical Analysis:** Implemented using JFlex, it tokenizes the source code based on the MicroJava language specification. It recognizes keywords, identifiers, constants (integer, character, boolean), operators, and separators.

*   **Syntax Analysis:** Implemented using Java CUP, it parses the stream of tokens to build an Abstract Syntax Tree (AST). The grammar supports a rich set of features and includes error recovery mechanisms for common syntax mistakes.

*   **Semantic Analysis:** Traverses the AST to perform semantic checks, including:
    *   **Symbol Table Management:** Manages scopes for global declarations, classes, and methods.
    *   **Type Checking:** Verifies type compatibility in assignments, expressions, and method calls.
    *   **Control Flow Validation:** Ensures correct usage of `break`, `continue`, and `return` statements within their allowed contexts.
    *   **Support for Advanced Constructs:**
        *   **Enums:** Supports `enum` declarations with and without explicit integer values.
        *   **Arrays:** Manages single-dimensional arrays, including creation, element access, and the `.length` property.
        *   **Ternary Operator:** Implements the `condition ? expr1 : expr2` operator.

*   **Code Generation:** After successful semantic analysis, the compiler generates bytecode for the MicroJava runtime. Key code generation features include:
    *   Translation of arithmetic, logical, and relational expressions.
    *   Implementation of control flow statements like `if-else`, `for`, and `switch`.
    *   Short-circuit evaluation for `&&` (AND) and `||` (OR) operators.
    *   Handling of method calls, I/O operations (`read`, `print`), and memory allocation for arrays.

## Language Specification

The compiler supports a significant subset of Java, including:

*   **Data Types:** `int`, `char`, `bool`.
*   **Declarations:**
    *   Global and local variables.
    *   Constants (`const`).
    *   `enum` types.
    *   Single-dimensional arrays.
*   **Methods:**
    *   Global methods and class methods (including abstract methods).
    *   `void` or typed return values.
*   **Statements:**
    *   Assignment (`=`).
    *   Increment (`++`) and Decrement (`--`).
    *   Method calls.
    *   `if-else` conditional statements.
    *   `for` loops.
    *   `switch-case` statements with fall-through logic.
    *   `break` and `continue`.
    *   `read()` and `print()` for I/O.
    *   `return` statements.
*   **Expressions:**
    *   Arithmetic (`+`, `-`, `*`, `/`, `%`).
    *   Relational (`==`, `!=`, `>`, `<`, `>=`, `<=`).
    *   Logical (`&&`, `||`).
    *   Ternary (`? :`).
    *   Array element access (`arr[index]`) and length (`arr.length`).
    *   Object creation (`new Type`) and array creation (`new Type[size]`).

## Project Structure

*   `src/`: Main source code, including the Lexer, Parser, Semantic Analyzer, Code Generator, and AST node classes.
*   `spec/`: JFlex (`.lex`) and CUP (`.cup`) grammar and token specification files.
*   `lib/`: External libraries required for compilation (JFlex, CUP, Log4j, etc.).
*   `test/`: A comprehensive suite of MicroJava programs for testing each phase of the compiler.
*   `config/`: `log4j.xml` configuration for logging.
*   `build.xml`: Apache Ant script for building and running the project.

## How to Build and Run

The project uses Apache Ant for building and execution.

### Prerequisites

*   Java Development Kit (JDK)
*   Apache Ant

### Compilation

1.  **Generate Parser & Lexer and Compile:**
    Run the default Ant target, which generates the lexer and parser from the specification files and compiles all Java source files.

    ```bash
    ant compile
    ```

### Running the Compiler

1.  **Modify the Test Runner:** Open the `test/rs/ac/bg/etf/pp1/MJParserTest.java` file.
2.  **Set Source File:** Change the `sourceCode` file path to point to the MicroJava (`.mj`) file you want to compile. For example:
    ```java
    File sourceCode = new File("test/eJavniTestovi/test301.mj");
    ```
3.  **Run:** Execute the `MJParserTest` class from your IDE or terminal. This will compile the specified `.mj` file and generate `test/program.obj` if successful.

### Executing the Bytecode

After generating `test/program.obj`, you can run it using the provided Ant script.

1.  **Provide Input (if needed):** If the compiled program uses `read()`, place the input values, one per line, in the `input.txt` file.

2.  **Run the Object File:**
    This command will first disassemble the object file into a human-readable format and then execute it using the MicroJava runtime.

    ```bash
    ant runObj
    ```

    The output will be printed to the console.
