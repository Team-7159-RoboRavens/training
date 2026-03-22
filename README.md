# FTC Programming Training Repository

[![GitHub Codespaces](https://img.shields.io/badge/GitHub-Codespaces-blue?logo=github)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=Team-7159-RoboRavens/training)

A comprehensive training program for FIRST Tech Challenge (FTC) robotics programming, designed for complete beginners. Learn Java programming from scratch and apply it to control real robots.

## 🎯 What This Repository Contains

This repository provides a complete 4-week training curriculum for FTC teams:

- **Weeks 1-2**: Java programming foundations (no robot required)
- **Weeks 3-4**: FTC SDK robot control programming
- **Complete examples** and **hands-on exercises** for each topic
- **Presentation slides** for teaching or self-study
- **Quick reference guide** for common patterns

## 📁 Repository Structure

```
training/
├── example_code/                    # Runnable code examples
│   ├── java-foundations/           # Pure Java programming (Weeks 1-2)
│   │   ├── src/examples/           # Complete working examples
│   │   ├── src/exercises/          # Hands-on exercises (your work)
│   │   ├── build.gradle            # Gradle build configuration
│   │   └── README.md               # Setup and learning guide
│   │
│   └── ftc-sdk/                    # FTC robot control (Weeks 3-4)
│       ├── FtcRobotController/     # Official FTC SDK (submodule)
│       ├── TeamCode/               # Your robot code
│       │   ├── src/main/java/.../examples/  # Working OpModes
│       │   ├── src/main/java/.../exercises/ # Robot exercises
│       │   └── build.gradle
│       └── README.md               # FTC setup guide
│
├── slides/                         # Teaching materials
│   ├── Week1-2_Java_Foundations.md # Java basics slides
│   ├── Week3-4_FTC_SDK_Basics.md   # Robot programming slides
│   ├── Quick_Reference_Guide.md    # Syntax and patterns reference
│   └── README.md                   # Teaching guide
│
└── README.md                       # This file
```

## 🚀 Quick Start

### Option 1: GitHub Codespaces (Recommended)
1. Click the "Code" button above → "Codespaces" → "Create codespace"
2. Wait for the environment to load (Java 17, VS Code extensions ready)
3. Start with `example_code/java-foundations/` for pure Java practice

### Option 2: Local Development
1. Clone the repository:
   ```bash
   git clone https://github.com/Team-7159-RoboRavens/training.git
   cd training
   ```

2. For Java foundations (no robot needed):
   ```bash
   cd example_code/java-foundations
   # Open in VS Code or your preferred IDE
   ```

3. For FTC SDK (requires Android Studio):
   ```bash
   cd example_code/ftc-sdk
   git submodule init
   git submodule update
   # Open ftc-sdk/ in Android Studio
   ```

## 📚 Learning Path

### Weeks 1-2: Java Foundations
**Goal**: Learn Java programming without needing robot hardware

| Topic | Examples | Exercises |
|-------|----------|-----------|
| Hello World & Structure | `Ex01_HelloWorld.java` | `Exercise01_Variables.java` |
| Variables & Types | `Ex02_VariablesAndTypes.java` | - |
| Conditionals (if/else) | `Ex03_Conditionals.java` | `Exercise02_Conditionals.java` |
| Loops | `Ex04_Loops.java` | `Exercise03_Loops.java` |
| Methods | `Ex05_Methods.java` | `Exercise04_Methods.java` |
| Arrays & Strings | `Ex06_ArraysAndStrings.java` | `Exercise05_ArraysAndStrings.java` |

**Start here**: [Java Foundations Guide](example_code/java-foundations/README.md)

### Weeks 3-4: FTC SDK Basics
**Goal**: Control real FTC robots with your Java knowledge

| Topic | Examples | Exercises |
|-------|----------|-----------|
| OpMode Lifecycle | `Ch01_HelloWorldOpMode.java` | `Exercise01_Telemetry.java` |
| Gamepad Input | `Ch03_GamepadBasicMath.java` | `Exercise02_GamepadDrive.java` |
| Hardware Mapping | `Ch06_FirstHardwareOpMode.java` | - |
| Motor Control | `Ch07_MotorControlOpMode.java` | - |
| Servo Control | `Ch08_ServoControlOpMode.java` | `Exercise03_ServoToggle.java` |
| Full TeleOp | - | `Exercise04_TeleOpFull.java` |
| Autonomous | `Ch12_StateMachineAuto.java` | `Exercise05_SimpleAuto.java` |

**Prerequisites**: Complete Java foundations first
**Start here**: [FTC SDK Guide](example_code/ftc-sdk/README.md)

## 📖 Teaching Materials

- **[Week 1-2 Slides](slides/Week1-2_Java_Foundations.md)**: Java programming fundamentals
- **[Week 3-4 Slides](slides/Week3-4_FTC_SDK_Basics.md)**: Robot control programming
- **[Quick Reference Guide](slides/Quick_Reference_Guide.md)**: Syntax lookup and code patterns
- **[Teaching Guide](slides/README.md)**: How to use these materials for training

## 🛠️ Development Environment

### Java Foundations (Codespaces Ready)
- **Java 17** (pre-installed in Codespaces)
- **VS Code** with Java extensions
- **Gradle** for building and running

### FTC SDK (Requires Android Studio)
- **Android Studio** (2021.2+ recommended)
- **FTC SDK** (included as git submodule)
- **REV Control Hub** or **Android phone** for testing
- **FTC Driver Station** app

## 🎯 Learning Outcomes

By the end of this training, you'll be able to:

- ✅ Write Java programs with variables, conditionals, loops, and methods
- ✅ Understand object-oriented programming concepts
- ✅ Control FTC robot motors and servos
- ✅ Read gamepad input for driver control
- ✅ Write both TeleOp (driver-controlled) and Autonomous programs
- ✅ Debug code using telemetry and logging
- ✅ Deploy code to competition robots

## 🤝 Contributing

This training material is maintained by Team 7159 - RoboRavens. Contributions welcome!

- **Found a bug?** Open an issue
- **Have improvements?** Submit a pull request
- **Questions?** Check existing issues or create a new one

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- FIRST Tech Challenge for the robotics platform
- Official FTC SDK developers
- Championship-winning FTC teams who shared their code patterns
- The FTC community for continuous improvement and support

---

**Ready to start coding?** Begin with [Java Foundations](example_code/java-foundations/) if you're new to programming, or jump straight to [FTC SDK](example_code/ftc-sdk/) if you know Java basics.</content>
<parameter name="filePath">/workspaces/training/README.md