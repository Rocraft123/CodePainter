Yes this README is written by ChatGPT. Im a developer not a writer.
# 🎨 CodePainter

CodePainter is a lightweight, extensible syntax highlighting engine that converts source code into styled output formats such as **HTML** and **JSON**.

It is built around a token-based architecture, making it easy to add new languages and renderers.

---

## ✨ Features

- Token-based lexer system
- Context-aware token classification
- Multiple output formats
- Theme-based styling engine
- Extensible language system

---

## 📦 Supported Languages

- JavaScript (`.js`)
- Java (`.java`)

---

## 📄 Supported Output Formats

- HTML (`.html`)
- JSON (`.json`)

---

## 🚀 Quick Start

### HTML Rendering

```java
Renderer jsRenderer = new JavaScriptHtmlRenderer();
Renderer javaRenderer = new JavaHtmlRenderer();
String html = renderer.paint(sourceCode);
