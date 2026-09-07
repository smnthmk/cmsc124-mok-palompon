# [TBD]

## Creators

- Samantha F. Mok (smnthmk)
- Rafahil D. Palompon (vertigodot)

## Overview

Inspired by ASCII typography and directional notation, this general-purpose language offers a visually intuitive approach to programming by using symbols such as arrows and carets among others, to organize code. It also borrows familiar conventions from languages such as Python and Kotlin. Think if code and a flowchart had a baby that was 80% code. Designed for visual-explicit learners.

## Host language and build

- Host language: Kotlin 2.0.20
- Version metadata: build.gradle.kts
- Build: `./build.sh`
- [Anything a fresh clone needs to know.]

## Running it


| Command | What it does |
|---|---|
| `./run <file>` | [Executes a program. Available from Lab 4.] |
| `./run --lex <file>` | [Prints the token stream.] |
| `./run --parse <file>` | [Prints the parsed tree.] |
| `./run --eval <file>` | [Evaluates each expression and prints its value.] |
| `./run` | [Starts the REPL.] |


Exit codes: 0 when successful execution, 65 when syntax error, 70 when runtime error.

## File extension

`[.mp]` 

## Lexical structure

### Keywords


| Keyword | Purpose |
|---|---|
| var | declare a variable |
| funct | declare a function |
| if | conditional statement |
| else | conditional statement |
| while | executes loop while the condition is true |
| and | logical operator (true if all statements true) |
| or | logical operator (true if at least one statement true)|


### Operators


| Operator | Category | Operands | Associativity | Precedence |
|---|---|---|---|---|
| <- | assignment | binary | right | 1 |
| +< | arithmetic, assignment | binary | right | 1 |
| -< | arithmetic, assignment | binary | right | 1 |
| *< | arithmetic, assignment | binary | right | 1 |
| /< | arithmetic, assignment | binary | right | 1 |
| and | logical | binary | left | 2 |
| or | logical | binary | left | 2 |
| == | equality | binary | left | 3 |
| != | equality | binary | left | 3 |
| < | relational | binary | left | 3 |
| > | relational | binary | left | 3 |
| <= | relational | binary | left | 3 |
| >= | relational | binary | left | 3 |
| -> | function output | binary | left | 4 |
| + | arithmetic | binary | left | 5 |
| - | arithmetic | binary | left | 5 |
| * | arithmetic | binary | left | 6 |
| / | arithmetic | binary | left | 6 |
| - | negation | unary | right | 7 |


### Literals


| Kind | Syntax | Produces |
|---|---|---|
| number | 42, 3.14, -1 | integer/float value |
| string | "hello" | string value |
| boolean | true, false | boolean value |
| nil | NONE | null value |


### Identifiers

- Start characters: letters (both capital and lowercase), underscores
- Continue characters: letters, numbers, underscores
- Case-sensitive: yes
- Restrictions: matching keywords, starting with a number/symbol

### Comments

- Line comments: ^^
- Block comments: ^^^ to open and close
- Nesting: not supported
- [Harness note: comment_prefix in tests/lab*/manifest.json is set to the
  token above.]

## Whitespace and termination

- Whitespace significant: no
- Statement terminator: <>
- Block delimiters: curly braces
- Grouping delimiters: parentheses

## Token output format

```
[one line of real --tokenize output]
```

[What each field means. Frozen as of Lab 1; changes are recorded in the
changelog.]

## Grammar

```
[Your complete context-free grammar, current as of the latest activity.
Unambiguous, with precedence and associativity encoded in rule structure.]
```

## Parse output format

```
[one line of real --parse output, e.g. (+ 1.0 (* 2.0 3.0))]
```

- Groupings print as: [form]
- Numbers print as: [form]

## Semantics

### Values and types

[What runtime values exist, and how they are represented in the host
language.]

### Value printing

- Numbers: Integers are printed as whole (ex. 5), float is printed as 5.0, and 
- Nil: NONE
- Strings: Strings are enclosed by double quotes.

### Truthiness

[The complete rule. Which values are false in a condition; everything else is
true.]

### Operator semantics

- Arithmetic: Addition [+], Subtraction [-], Division [/], Multiplication [*]
- `+` on strings: Concatenation
- Mixed types: [what happens]
- Comparison: [accepted operand types]
- Equality across types: False
- Division by zero: Runtime error

### Scope and bindings

- Redeclaration in the same scope: [allowed or an error]
- Uninitialized variable holds: [value]
- Shadowing: [behavior]
- Undefined name: [static error with exit 65, or runtime error with exit 70]

### Control flow and functions

- Logical operators return: [booleans, or the operand]
- Dangling else binds to: [which if]
- Closure capture of a loop variable: [per iteration, or shared]
- Function with no return statement produces: [value]
- Arity mismatch: [message and exit code]

## Native functions


| Name | Arguments | Returns | Notes |
|---|---|---|---|
| [name] | [count and types] | [type] | [caveats] |


## Errors and diagnostics

Message format:

```
[one real static error]
[one real runtime error]
```


| Failure | Exit code |
|---|---|
| [lexical error] | 65 |
| [syntax error] | 65 |
| [runtime error] | 70 |


## Testing conventions


| Folder | Activity | Mode | Flag |
|---|---|---|---|
| tests/lab1 | Scanner | sidecar | `--tokenize` |
| tests/lab2 | Parser | sidecar | `--parse` |
| tests/lab3 | Evaluator | inline | `--eval` |
| tests/lab4 | Context | inline | none |
| tests/lab5 | Functions | inline | none |


```
[specific tests]...
```

Run locally with:

```bash
curl -sSL https://raw.githubusercontent.com/WhiteLicorice/cmsc-124-harness/v1.1/run_tests.py -o run_tests.py
./build.sh
python3 run_tests.py tests/lab1
```

## Sample code

```
[a short program]
```

Output:

```
[its output]
```

## Design rationale

[Why the language is the way it is. Cover the choices that surprised you, the
features you cut, and the decisions you reversed. Specific reasons, not
approval of your own work.]

## Known limitations

- [What doesn't work, what is unimplemented, where behavior is worse than you
  would like.]

## Changelog


| Activity | What changed in the language |
|---|---|
| Lab 1 | [entry] |

