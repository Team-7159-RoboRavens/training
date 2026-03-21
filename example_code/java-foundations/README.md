# Java Foundations - GitHub Codespaces Ready

Learn pure Java programming **without needing a robot**. Perfect for beginners or for practicing away from the lab.

---

## 🎯 What You'll Learn

- **Variables and data types** - storing information
- **Conditionals (if/else)** - making decisions
- **Loops** - repeating code
- **Methods** - organizing code into reusable pieces
- **Arrays & Strings** - working with collections

All concepts apply directly to robot programming!

---

## 🚀 Getting Started (2 Ways)

### Way 1: GitHub Codespaces (Easiest ⭐)
Click the green "Code" button → "Codespaces" → "Create codespace on main"

The environment is ready immediately with:
- Java 17 installed
- VS Code with Java extensions
- Everything pre-configured

### Way 2: Local Development
```bash
# Install Java 17 (if not already installed)
# macOS with Homebrew:
brew install openjdk@17

# Then clone this repository
git clone <repo-url>
cd java-foundations
```

---

## 📂 Folder Structure

```
java-foundations/
├── src/
│   ├── examples/              ← Complete, runnable examples
│   │   ├── Ex01_HelloWorld.java
│   │   ├── Ex02_VariablesAndTypes.java
│   │   ├── Ex03_Conditionals.java
│   │   ├── Ex04_Loops.java
│   │   ├── Ex05_Methods.java
│   │   └── Ex06_ArraysAndStrings.java
│   │
│   └── exercises/             ← Your work goes here
│       ├── Exercise01_Variables.java
│       ├── Exercise02_Conditionals.java
│       ├── Exercise03_Loops.java
│       ├── Exercise04_Methods.java
│       └── Exercise05_ArraysAndStrings.java
│
├── build.gradle              ← Gradle configuration
└── README.md                 ← You are here
```

---

## 📖 Recommended Learning Path

### Week 1: Fundamentals

**Day 1-2: Variables**
1. Read `src/examples/Ex01_HelloWorld.java` (understand structure)
2. Read `src/examples/Ex02_VariablesAndTypes.java` (learn types)
3. Complete `Exercise01_Variables.java`

**Day 3-4: Conditionals**
1. Read `src/examples/Ex03_Conditionals.java`
2. Complete `Exercise02_Conditionals.java`

**Day 5: Loops**
1. Read `src/examples/Ex04_Loops.java`
2. Complete `Exercise03_Loops.java`

### Week 2: Advanced

**Day 1-2: Methods**
1. Read `src/examples/Ex05_Methods.java`
2. Complete `Exercise04_Methods.java`

**Day 3-5: Arrays & Strings**
1. Read `src/examples/Ex06_ArraysAndStrings.java`
2. Complete `Exercise05_ArraysAndStrings.java`

---

## ▶️ Running Code

### Option 1: Direct Run (Simplest)
In VS Code, open a Java file and click "Run" button in the top right.

### Option 2: Terminal (Traditional)

**Compile:**
```bash
cd src
javac examples/Ex01_HelloWorld.java
```

**Run:**
```bash
java -cp . examples.Ex01_HelloWorld
```

### Option 3: Using Gradle
```bash
# Build all files
./gradlew build

# Run a specific class (from root directory)
java -cp src examples.Ex01_HelloWorld
```

---

## 💡 How to Complete an Exercise

Each exercise file has TODO comments like:
```java
// TODO: Declare an int variable motorPower with value 85
```

Steps to complete:
1. Read the OBJECTIVE section at the top
2. Look at EXPECTED OUTPUT - this is your target
3. Follow each TODO comment
4. Run the code with the Run button
5. Verify output matches expected
6. Compare with example if needed

Example completion:
```java
// Before (skeleton):
public class Exercise01_Variables {
    public static void main(String[] args) {
        // TODO: Declare an int variable motorPower with value 85
    }
}

// After (completed):
public class Exercise01_Variables {
    public static void main(String[] args) {
        int motorPower = 85;
        System.out.println("Motor Power: " + motorPower);
    }
}
```

---

## 🆘 Debugging Tips

### If you get `syntax error`
- Check for missing semicolons at end of lines
- Check for matching braces `{ }`
- Look at the line number in the error message

### If you get `cannot find symbol`
- Check variable names are spelled correctly
- Check variables are declared before use
- Java is case-sensitive: `motorPower` ≠ `motorpower`

### If output doesn't match expected
- Read your code carefully
- Add extra `System.out.println()` statements to debug
- Compare with the example file

### Stuck?
1. Re-read the exercise objective
2. Check the corresponding example
3. Ask a teammate or coach

---

## 🔗 Java Keywords & Operators Quick Ref

### Data Types
```java
int numberValue = 42;           // whole numbers
double decimalValue = 3.14;     // decimal numbers
boolean trueOrFalse = true;     // true/false
String text = "Hello";          // text
```

### Operators
```java
+  - * / %     // math
== != < > <= >= // comparison
&& || !        // logic (and, or, not)
```

### Control Flow
```java
if (condition) { /* run if true */ }
else { /* run otherwise */ }

for (int i = 0; i < 10; i++) { }       // for loop
while (condition) { }                  // while loop
for (int value : array) { }            // for-each loop
```

### Methods
```java
static int add(int a, int b) {
    return a + b;
}

static void printMessage(String msg) {
    System.out.println(msg);
}
```

---

## ✅ Completion Checklist

- [ ] Week 1 exercises (Variables, Conditionals, Loops) all completed
- [ ] All output matches expected values
- [ ] Week 2 exercises (Methods, Arrays/Strings) completed
- [ ] You understand each piece of code you wrote
- [ ] Ready to move to FTC SDK!

---

## 📞 Getting Help

1. **Error messages:** Read them carefully! They point you to the problem
2. **Stuck on logic:** Look at the corresponding example
3. **VS Code shortcuts:**
   - `Ctrl+/` (Mac: `Cmd+/`) - Comment/uncomment
   - `Ctrl+Shift+F` (Mac: `Cmd+Shift+F`) - Format code
4. **Online resources:**
   - [Java Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)
   - [W3Schools Java Tutorial](https://www.w3schools.com/java/)

---

## 🎓 What's Next?

After completing all Java Foundations exercises:
1. Move to the **FTC SDK** section
2. You now know enough Java to control a real robot!
3. FTC SDK exercises build on these exact concepts

---

**Good luck! You've got this! 🚀**
